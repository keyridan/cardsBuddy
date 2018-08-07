package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class InfoRow extends Row {
    private List<String> highLights;

    @Builder
    public InfoRow(String value, List<String> highLights) {
        super(value);
        this.highLights = highLights;
    }
}
