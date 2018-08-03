package com.j0rsa.cardsbuddy.controller.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.j0rsa.cardsbuddy.common.Info;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeoInfo implements Info {
    private String title;
    private String description;
    private String url;
    private String table;
}
