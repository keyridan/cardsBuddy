package com.j0rsa.cardsbuddy.controller.model;


import com.j0rsa.cardsbuddy.common.Info;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeoInfo implements Info {
    private LeoBriefInfo leoBriefInfo;
    private String flectTable;
}
