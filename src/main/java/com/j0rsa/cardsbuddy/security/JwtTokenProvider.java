package com.j0rsa.cardsbuddy.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expirationInMs}")
    private long jwtExpirationInMs;

    @Value("${app.jwt.refreshBeforeMs}")
    private long refreshBeforeMs;

    public String generateToken(UUID tokenId, TinyAuthenticationToken authentication) {

        String userId = (String) authentication.getPrincipal();
        Map<String, Object> headers = authentication.getHeaders()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (String) entry.getValue()));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setId(tokenId.toString())
                .setSubject(userId)
                .setHeaderParams(headers)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateTokenFromJwt(String jwtFromRequest) {
        UUID tokenIdFromJWT = UUID.fromString(getTokenIdFromJWT(jwtFromRequest));
        String usernameFromJWT = getUsernameFromJWT(jwtFromRequest);
        Map<String, String> headers = getHeadersFromJWT(jwtFromRequest);

        return generateToken(tokenIdFromJWT, new TinyAuthenticationToken(usernameFromJWT, headers));
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public String getTokenIdFromJWT(String token) {
        Claims claims = getClaims(token);
        return claims.getId();
    }

    public Map<String, String> getHeadersFromJWT(String token) {
        Set<Map.Entry<String, String>> set = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getHeader()
                .entrySet();
        return set.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public Optional<String> getJwtFromRequest() {
        return Optional.ofNullable(requestAuthorizationHeader())
                .filter(bearerToken -> StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
                .map(bearerTokenValue());
    }

    private Function<String, String> bearerTokenValue() {
        return bearerToken -> bearerToken.substring(7, bearerToken.length());
    }

    private String requestAuthorizationHeader() {
        return getRequest().getHeader("Authorization");
    }

    private HttpServletRequest getRequest() {
        final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest();
    }

    public LocalDateTime getExpiration(String token) {
        Claims claims = getClaims(token);
        Date in = claims.getExpiration();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return ldt;
    }

    public boolean mayBeRefreshed(String token) {
        return getExpiration(token).isAfter(refreshAvailableDate());
    }

    private LocalDateTime refreshAvailableDate() {
        return LocalDateTime.now().plusNanos(refreshBeforeMs * 1000000);
    }
}
