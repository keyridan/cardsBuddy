package com.j0rsa.cardsbuddy.common;

import lombok.Builder;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
@Builder
public class InfoData {
    private String value;
    @Builder.Default
    private List<Highlight> highlights = Lists.newArrayList();
    private String title;
}
