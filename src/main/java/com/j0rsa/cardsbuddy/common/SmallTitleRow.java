package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class SmallTitleRow extends Row {

    private List<InfoData> infoData = Lists.newArrayList();

    @Builder
    public SmallTitleRow(String value) {
        super(value);
    }

    public void addData(InfoData data) {
        this.infoData.add(data);
    }
}
