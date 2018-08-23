package com.j0rsa.cardsbuddy.integration.info.leo;

import com.j0rsa.cardsbuddy.controller.model.leo.LeoInfo;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.j0rsa.cardsbuddy.DefaultData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LeoInfoServiceTest {
    @Autowired
    private LeoInfoService leoInfoService;

    @Test
    public void testDeNoun() {
        LeoInfo expected = defaultInfoNoun().build();
        final TranslationRequest testTranslationRequest = requestFromDeToEn().build();

        LeoInfo result = leoInfoService.search(testTranslationRequest);

        assertThat(result).isEqualToIgnoringGivenFields(expected, "table");
    }

    @Test
    public void testDeVerb() {
        LeoInfo expected = defaultInfoVerb().build();
        final TranslationRequest testTranslationRequest = requestFromDeToEn()
                .word("lesen").build();

        LeoInfo result = leoInfoService.search(testTranslationRequest);

        assertThat(result).isEqualToIgnoringGivenFields(expected, "table");
    }

    @Test
    public void testDeWordWithSpace() {
        LeoInfo expected = defaultInfoVerbWithSpace().build();
        final TranslationRequest testTranslationRequest = requestFromDeToEn()
                .word("zu lesen").build();

        LeoInfo result = leoInfoService.search(testTranslationRequest);

        assertThat(result).isEqualTo(expected);
    }

}