package com.j0rsa.cardsbuddy.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.j0rsa.cardsbuddy.common.BriefInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeoBriefInfo implements BriefInfo {
    private String word;
    private String form;
    private String description;
}
