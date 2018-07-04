package com.j0rsa.cardsbuddy.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final SecurityService securityService;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, SecurityService securityService) {
        this.tokenProvider = tokenProvider;
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        tokenProvider.getJwtFromRequest()
                .filter(jwt -> hasText(jwt) && tokenProvider.isValidToken(jwt))
                .ifPresent(jwt -> securityService.updateContextToken(jwt, request));

        filterChain.doFilter(request, response);
    }
}
