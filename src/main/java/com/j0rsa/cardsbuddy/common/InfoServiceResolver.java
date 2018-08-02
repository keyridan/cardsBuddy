package com.j0rsa.cardsbuddy.common;

import com.j0rsa.cardsbuddy.translation.model.Language;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoServiceResolver {
    private List<InfoService> infoServices;

    public InfoServiceResolver(List<InfoService> infoServices) {
        this.infoServices = infoServices;
    }

    public List<InfoService> resolve(Language.Code language) {
        return infoServices.stream()
                .filter(service -> service.languages().contains(language))
                .collect(Collectors.toList());
    }
}
