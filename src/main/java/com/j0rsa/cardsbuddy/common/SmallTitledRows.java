package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
@Builder
public class SmallTitledRows {
    @Builder.Default
    private List<InfoData> infoData = Lists.newArrayList();
    private String value;

    public void addData(InfoData data) {
        this.infoData.add(data);
    }
}
