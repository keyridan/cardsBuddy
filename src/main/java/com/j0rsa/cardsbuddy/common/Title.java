package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
@Builder
public class Title {
    private String value;
    @Builder.Default
    private List<SmallTitledRows> rows = Lists.newArrayList();

    public void addRows(SmallTitledRows rows) {
        this.rows.add(rows);
    }
}
