package com.j0rsa.cardsbuddy.common;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class InfoTable {
    List<Row> rows = Lists.newArrayList();

    public void addRow(Row row) {
        this.rows.add(row);
    }
}
