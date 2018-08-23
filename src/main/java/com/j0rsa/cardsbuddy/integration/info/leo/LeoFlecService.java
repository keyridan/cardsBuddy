package com.j0rsa.cardsbuddy.integration.info.leo;

import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.Flec;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlec;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlec;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.BodyType;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.HtmlType;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
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
                .orElse(null);
    }

    private Flec convertFlec(BodyType body) {
        return body.getVerbtab() != null
                ? conversionService.convert(body.getVerbtab(), VerbFlec.class)
                : conversionService.convert(body.getNountab(), NounFlec.class);
    }

}
