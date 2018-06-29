package com.j0rsa.cardsbuddy.controller.model;

import lombok.Data;

import java.util.Date;

@Data
public class JwtAuthenticationToken {
    private String accessToken;
    private String tokenType = "Bearer";
    private Date validUntil;

    public JwtAuthenticationToken(String accessToken, Date validUntil) {
        this.accessToken = accessToken;
        this.validUntil = validUntil;
    }
}
