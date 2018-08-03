package com.j0rsa.cardsbuddy.common;

import com.j0rsa.cardsbuddy.translation.model.Language;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class InfoServiceResolver {
    private List<InfoService> infoServices;

    public InfoServiceResolver(List<InfoService> infoServices) {
        this.infoServices = infoServices;
    }

    public List<InfoService> resolve(Language.Code language, List<InfoProvider.Code> infoTypes) {
        return infoTypes.isEmpty()
                ? Lists.newArrayList()
                : filterServices(language, infoTypes);
    }

    private List<InfoService> filterServices(Language.Code language, List<InfoProvider.Code> infoTypes) {
        return infoServices.stream()
                .filter(isServiceProvideInfoAndSupportLanguage(infoTypes, language))
                .collect(Collectors.toList());
    }

    private Predicate<InfoService> isServiceProvideInfoAndSupportLanguage(List<InfoProvider.Code> infoTypes, Language.Code language) {
        return isServiceProvideInfo(infoTypes).and(isServiceSupportLanguage(language));
    }

    private Predicate<InfoService> isServiceProvideInfo(List<InfoProvider.Code> infoTypes) {
        return service -> infoTypes.contains(service.providerCode());
    }

    private Predicate<InfoService> isServiceSupportLanguage(Language.Code language) {
        return service -> service.languages().contains(language);
    }
}
