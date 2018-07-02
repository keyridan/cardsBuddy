package com.j0rsa.cardsbuddy.controller.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class JwtAuthenticationToken {
    private String accessToken;
    private String tokenType = "Bearer";
    private LocalDateTime validUntil;

    public JwtAuthenticationToken(String accessToken, LocalDateTime validUntil) {
        this.accessToken = accessToken;
        this.validUntil = validUntil;
    }
}
