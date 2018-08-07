package com.j0rsa.cardsbuddy.common;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class InfoTable {
    List<Title> rows = Lists.newArrayList();

    public void addRow(Title row) {
        this.rows.add(row);
    }
}
