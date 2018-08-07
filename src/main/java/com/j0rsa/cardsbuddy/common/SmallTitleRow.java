package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;

@Data
public class SmallTitleRow extends Row {

    @Builder
    public SmallTitleRow(String value) {
        super(value);
    }
}
