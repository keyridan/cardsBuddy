package com.j0rsa.cardsbuddy.common;

import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
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

    public List<InfoService> resolve(TranslationRequest request) {
        return request.getInfoTypes().isEmpty()
                ? Lists.newArrayList()
                : filterServices(request);
    }

    private List<InfoService> filterServices(TranslationRequest request) {
        return infoServices.stream()
                .filter(isServiceProvideInfoAndSupportLanguage(request))
                .collect(Collectors.toList());
    }

    private Predicate<InfoService> isServiceProvideInfoAndSupportLanguage(TranslationRequest request) {
        return isServiceProvideInfo(request.getInfoTypes()).and(isServiceSupportLanguage(request));
    }

    private Predicate<InfoService> isServiceProvideInfo(List<InfoProvider.Code> infoTypes) {
        return service -> infoTypes.contains(service.providerCode());
    }

    private Predicate<InfoService> isServiceSupportLanguage(TranslationRequest request) {
        return service -> service.canBeProvided(request);
    }
}
