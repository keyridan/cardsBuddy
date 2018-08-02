package com.j0rsa.cardsbuddy.controller.model;

import com.j0rsa.cardsbuddy.common.BriefInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeoBriefInfo implements BriefInfo {
    private String word;
    private String form;
    private String description;
}
