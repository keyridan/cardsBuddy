package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class InfoRow extends Row {
    private List<String> highLights;
    private String title;

    @Builder
    private InfoRow(String value, List<String> highLights, String title) {
        super(value);
        this.highLights = highLights;
        this.title = title;
    }
}
