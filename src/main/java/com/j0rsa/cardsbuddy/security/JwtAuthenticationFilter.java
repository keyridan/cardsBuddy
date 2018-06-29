package com.j0rsa.cardsbuddy.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        tokenProvider.getJwtFromRequest()
                .filter(jwt -> hasText(jwt) && tokenProvider.isValidToken(jwt))
                .map(tokenProvider::getUsernameFromJWT)
                .map(customUserDetailsService::loadUserByUsername)
                .map(this::authenticationTokenFromUser)
                .map(updateAuthenticationTokenDetailsWith(request))
                .ifPresent(this::updateContextWithAuthenticationToken);

        filterChain.doFilter(request, response);
    }

    private Function<UsernamePasswordAuthenticationToken, UsernamePasswordAuthenticationToken> updateAuthenticationTokenDetailsWith(HttpServletRequest request) {
        return token -> updateAuthenticationDetails(token, request);
    }

    private UsernamePasswordAuthenticationToken updateAuthenticationDetails(UsernamePasswordAuthenticationToken token, HttpServletRequest request) {
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return token;
    }

    private void updateContextWithAuthenticationToken(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private UsernamePasswordAuthenticationToken authenticationTokenFromUser(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
