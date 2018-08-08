package com.j0rsa.cardsbuddy.info.leo;

import com.j0rsa.cardsbuddy.common.InfoProvider;
import com.j0rsa.cardsbuddy.common.InfoService;
import com.j0rsa.cardsbuddy.controller.model.leo.LeoInfo;
import com.j0rsa.cardsbuddy.info.leo.exceptions.InfoNotFoundException;
import com.j0rsa.cardsbuddy.info.leo.model.info.SideType;
import com.j0rsa.cardsbuddy.info.leo.model.info.XmlInfo;
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

    private ConversionService conversionService;
    private LeoFlecService leoFlecService;

    public LeoInfoService(ConversionService conversionService, LeoFlecService leoFlecService) {
        this.conversionService = conversionService;
        this.leoFlecService = leoFlecService;
    }

    @Override
    public LeoInfo search(TranslationRequest request) {
        return LeoInfoSearcher.search(request)
                .map(xmlInfo -> conversionService.convert(xmlInfo, XmlInfo.class))
                .map(XmlInfo::getFirstEntryFirstSectionSidesIfExist)
                .flatMap(findFirstSideForFromLanguage(request.getFromLanguage()))
                .map(sideType -> LeoInfo.builder()
                        .description(sideType.getDescription())
                        .url(LeoUrlFactory.createUrl(request))
                        .title(sideType.getWordType())
                        .table(leoFlecService.requestFlec(request, sideType.flectTable()))
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

    @Override
    public InfoProvider.Code providerCode() {
        return InfoProvider.Code.LEO;
    }

    private Function<List<SideType>, Optional<SideType>> findFirstSideForFromLanguage(Language.Code fromLanguage) {
        return list -> list.stream().filter(sideIsForFromLanguage(fromLanguage)).findFirst();
    }

    private Predicate<SideType> sideIsForFromLanguage(Language.Code fromLanguage) {
        return sideType -> fromLanguage.lowerCaseEqual(sideType.getLang());
    }

}
