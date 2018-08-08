package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Highlight {
    private String value;
    private String wordPart;
}
