package com.j0rsa.cardsbuddy.controller.model.leo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.j0rsa.cardsbuddy.common.Info;
import com.j0rsa.cardsbuddy.common.InfoProvider;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeoInfo implements Info {
    private final InfoProvider.Code type = InfoProvider.Code.LEO;
    private String title;
    private String description;
    private String url;
    private String table;
}
