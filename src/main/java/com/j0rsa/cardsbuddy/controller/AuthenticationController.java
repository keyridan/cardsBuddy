package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.security.JwtAuthenticationService;
import com.j0rsa.cardsbuddy.integration.tinycards.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(jwtAuthenticationService.generateNewToken(loginRequest));
    }
}
