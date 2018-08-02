package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.common.InfoService;
import com.j0rsa.cardsbuddy.controller.model.LeoBriefInfo;
import com.j0rsa.cardsbuddy.controller.model.LeoInfo;
import com.j0rsa.cardsbuddy.leo.exceptions.InfoNotFoundException;
import com.j0rsa.cardsbuddy.leo.model.SideType;
import com.j0rsa.cardsbuddy.leo.model.XmlInfo;
import com.j0rsa.cardsbuddy.translation.model.Language;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.assertj.core.util.Lists;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class LeoInfoService implements InfoService<LeoInfo> {

    private XmlParser xmlParser;
    private ConversionService conversionService;

    public LeoInfoService(XmlParser xmlParser, ConversionService conversionService) {
        this.xmlParser = xmlParser;
        this.conversionService = conversionService;
    }

    @Override
    public LeoInfo search(TranslationRequest request) {
        return LeoInfoSearcher.search(request)
                .map(xmlParser::fromXml)
                .map(XmlInfo::getFirstEntryFirstSectionSidesIfExist)
                .flatMap(findFirstSideForFromLanguage(request.getFromLanguage()))
                .map(sideType -> conversionService.convert(sideType, LeoBriefInfo.class))
                .map(leoBriefInfo -> LeoInfo.builder()
                        .briefInfo(leoBriefInfo)
                        .url(LeoUrlFactory.createUrl(request))
                        .build()
                )
                .orElseThrow(InfoNotFoundException::new);
    }

    @Override
    public List<Language.Code> languages() {
        return Lists.newArrayList(
                Language.Code.EN,
                Language.Code.DE,
                Language.Code.FR,
                Language.Code.ES,
                Language.Code.IT,
                Language.Code.RU,
                Language.Code.PT,
                Language.Code.PL
        );
    }

    private Function<List<SideType>, Optional<SideType>> findFirstSideForFromLanguage(Language.Code fromLanguage) {
        return list -> list.stream().filter(sideIsForFromLanguage(fromLanguage)).findFirst();
    }

    private Predicate<SideType> sideIsForFromLanguage(Language.Code fromLanguage) {
        return sideType -> fromLanguage.lowerCaseEqual(sideType.getLang());
    }

}
