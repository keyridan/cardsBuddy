package com.j0rsa.cardsbuddy.controller.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.j0rsa.cardsbuddy.common.Info;
import com.j0rsa.cardsbuddy.common.InfoProvider;
import com.j0rsa.cardsbuddy.common.InfoTable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TatoebaInfo implements Info {
    private final InfoProvider.Code type = InfoProvider.Code.TATOEBA;
    private String title;
    private String description;
    private String url;
    private InfoTable table;
}
