package com.j0rsa.cardsbuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.function.Function;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    Function<String, String> takeJwt = jwt -> jwt;

    @Autowired
    public SecurityServiceImpl(JwtTokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    public Map<String, String> headers() {
        return tokenProvider.getJwtFromRequest()
                .map(tokenProvider::getHeadersFromJWT)
                .orElseThrow(IllegalStateException::new);
    }

    public String userId() {
        return tokenProvider.getJwtFromRequest()
                .map(tokenProvider::getUsernameFromJWT)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public UsernamePasswordAuthenticationToken updateContextToken(UsernamePasswordAuthenticationToken token) {
        SecurityContextHolder.getContext().setAuthentication(token);
        return token;
    }

    @Override
    public void updateContextToken(String jwt, HttpServletRequest request) {
        takeJwt.andThen(tokenProvider::getUsernameFromJWT)
                .andThen(customUserDetailsService::loadUserByUsername)
                .andThen(this::authenticationTokenFromUser)
                .andThen(updateAuthenticationTokenDetailsWith(request))
                .andThen(this::updateContextToken)
                .apply(jwt);
    }

    private Function<UsernamePasswordAuthenticationToken, UsernamePasswordAuthenticationToken> updateAuthenticationTokenDetailsWith(HttpServletRequest request) {
        return token -> updateAuthenticationDetails(token, request);
    }

    private UsernamePasswordAuthenticationToken updateAuthenticationDetails(UsernamePasswordAuthenticationToken token, HttpServletRequest request) {
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return token;
    }

    private UsernamePasswordAuthenticationToken authenticationTokenFromUser(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
