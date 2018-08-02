package com.j0rsa.cardsbuddy.converters;

import com.j0rsa.cardsbuddy.controller.model.FlectBrief;
import com.j0rsa.cardsbuddy.controller.model.LeoBriefInfo;
import com.j0rsa.cardsbuddy.leo.model.SideType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SideTypeToLeoBriefInfoConverter implements Converter<SideType, LeoBriefInfo> {

    @Override
    public LeoBriefInfo convert(SideType sideType) {
        FlectBrief flectBrief = FlectBrief.builder()
                .flectDescription(sideType.getFlectDescription())
                .flectForm(sideType.getFlectForm())
                .build();
        return LeoBriefInfo.builder()
                .flectBrief(flectBrief)
                .word(sideType.getFirstWord())
                .build();
    }
}
