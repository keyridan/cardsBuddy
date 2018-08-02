package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.controller.model.LeoBriefInfo;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
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
        LeoBriefInfo expected = defaultBriefInfoNoun().build();
        final TranslationRequest testTranslationRequest = requestFromDeToEn().build();

        LeoBriefInfo leoBriefInfo = leoInfoService.search(testTranslationRequest);

        assertThat(leoBriefInfo).isEqualTo(expected);
    }

    @Test
    public void testDeVerb() {
        LeoBriefInfo expected = defaultBriefInfoVerb().build();
        final TranslationRequest testTranslationRequest = requestFromDeToEn()
                .word("lesen").build();

        LeoBriefInfo leoBriefInfo = leoInfoService.search(testTranslationRequest);

        assertThat(leoBriefInfo).isEqualTo(expected);
    }

}