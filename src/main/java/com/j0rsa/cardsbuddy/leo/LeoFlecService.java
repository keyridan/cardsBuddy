package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.Flec;
import com.j0rsa.cardsbuddy.leo.exceptions.InfoParseException;
import com.j0rsa.cardsbuddy.leo.model.flec.BodyType;
import com.j0rsa.cardsbuddy.leo.model.flec.HtmlType;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class LeoFlecService {

    private ConversionService conversionService;

    public LeoFlecService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public InfoTable requestFlec(TranslationRequest request, String flecTableUrlPart) {
        return LeoInfoSearcher.searchFlecTableIfPartExist(request, flecTableUrlPart)
                .map(flecXml -> conversionService.convert(flecXml, HtmlType.class))
                .map(flecTableObject -> convertFlec(flecTableObject.getBody()))
                .map(flec -> conversionService.convert(flec, InfoTable.class))
                .orElseThrow(InfoParseException::new);
    }

    private Flec convertFlec(BodyType body) {
        return conversionService.convert(getTab(body), Flec.class);
    }

    private Object getTab(BodyType body) {
        return body.getVerbtab() != null
                ? body.getVerbtab()
                : body.getNountabType();
    }
}
