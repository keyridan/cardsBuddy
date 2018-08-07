package com.j0rsa.cardsbuddy.common;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SmallTitleRow.class, name = "SMALL_TITLE"),
        @JsonSubTypes.Type(value = TitleRow.class, name = "TITLE"),
})
public abstract class Row {
    private String value;
}
