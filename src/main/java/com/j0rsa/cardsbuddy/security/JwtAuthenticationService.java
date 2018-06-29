package com.j0rsa.cardsbuddy.security;

import com.j0rsa.cardsbuddy.controller.model.JwtAuthenticationToken;
import com.j0rsa.cardsbuddy.tinycards.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public JwtAuthenticationService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public JwtAuthenticationToken generateNewToken(LoginRequest loginRequest) {
        return tokenProvider.getJwtFromRequest()
                .map(jwtFromRequest ->
                        tokenProvider.mayBeRefreshed(jwtFromRequest) ?
                                updateExistedSession(jwtFromRequest) :
                                tokenFrom(jwtFromRequest)
                )
                .orElseGet(() -> registerNewSession(loginRequest));
    }

    private JwtAuthenticationToken updateExistedSession(String jwtFromRequest) {
        String newToken = tokenProvider.generateTokenFromJwt(jwtFromRequest);
        return tokenFrom(newToken);
    }

    private JwtAuthenticationToken registerNewSession(LoginRequest loginRequest) {
        TinyAuthenticationToken authentication = (TinyAuthenticationToken) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getIdentifier(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UUID randomToken = UUID.randomUUID();
        String jwt = tokenProvider.generateToken(randomToken, authentication);
        return tokenFrom(jwt);
    }

    private JwtAuthenticationToken tokenFrom(String jwtFromRequest) {
        return new JwtAuthenticationToken(jwtFromRequest, tokenProvider.getExpiration(jwtFromRequest));
    }
}
