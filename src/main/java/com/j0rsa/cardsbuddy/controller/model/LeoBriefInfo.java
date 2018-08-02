package com.j0rsa.cardsbuddy.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeoBriefInfo {
    private String word;
    private FlectBrief flectBrief;
}
