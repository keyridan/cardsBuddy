package com.j0rsa.cardsbuddy.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlectBrief {
    private String flectForm;
    private String flectDescription;
}
