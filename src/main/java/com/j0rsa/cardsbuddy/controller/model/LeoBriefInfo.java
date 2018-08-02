package com.j0rsa.cardsbuddy.controller.model;

import com.j0rsa.cardsbuddy.common.Info;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeoBriefInfo implements Info {
    private String word;
    private FlectBrief flectBrief;
}
