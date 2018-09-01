package com.j0rsa.cardsbuddy.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoTable {
    List<Title> rows = Lists.newArrayList();

    public void addRow(Title row) {
        this.rows.add(row);
    }
}
