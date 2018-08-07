package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InfoData {
    private String value;
    private List<String> highLights;
    private String title;
}
