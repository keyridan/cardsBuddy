package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;

@Data
public class TitleRow extends Row {
    @Builder
    public TitleRow(String value) {
        super(value);
    }
}
