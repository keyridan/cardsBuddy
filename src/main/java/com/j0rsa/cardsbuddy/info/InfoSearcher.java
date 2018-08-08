package com.j0rsa.cardsbuddy.info;

import com.j0rsa.cardsbuddy.common.Info;
import com.j0rsa.cardsbuddy.common.InfoService;
import com.j0rsa.cardsbuddy.common.InfoServiceResolver;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import io.vavr.CheckedFunction0;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InfoSearcher {
    private final static String INFO_SERVICE_ERROR_TEMPLATE = "%s: %s";
    private InfoServiceResolver infoServiceResolver;

    public InfoSearcher(InfoServiceResolver infoServiceResolver) {
        this.infoServiceResolver = infoServiceResolver;
    }

    public Tuple2<List<String>, List<Info>> search(TranslationRequest request) {
        List<String> mesages = Lists.newArrayList();

        List<Info> infos = Try.of(resolveInfoService(request))
                .onFailure(e -> log.error("Error", e))
                .onFailure(e -> mesages.add(e.toString()))
                .map(searchInfosAndAddErrorMessages(request, mesages))
                .getOrElse(Lists.newArrayList());
        return new Tuple2<>(mesages, infos);
    }

    private CheckedFunction0<List<InfoService>> resolveInfoService(TranslationRequest request) {
        return () -> infoServiceResolver.resolve(request.getFromLanguage(), request.getInfoTypes());
    }

    private Function<List<InfoService>, List<Info>> searchInfosAndAddErrorMessages(TranslationRequest request, List<String> mesages) {
        Function<InfoService, Optional<Info>> infoServiceTuple2Function
                = service -> searchInfo(mesages, service, request);
        return infoServices -> infoServices.stream()
                .map(infoServiceTuple2Function)
                .map(optionalValue -> optionalValue.orElse(null))
                .collect(Collectors.toList());
    }

    private Optional<Info> searchInfo(List<String> mesages, InfoService infoService, TranslationRequest request) {
        return Try.of(() -> infoService.search(request))
                .onFailure(e -> log.error("Error", e))
                .onFailure(e -> mesages.add(errorMessage(infoService, e.toString())))
                .toJavaOptional();
    }

    private String errorMessage(InfoService infoService, String error) {
        return String.format(INFO_SERVICE_ERROR_TEMPLATE, infoService.providerCode(), error);
    }
}
