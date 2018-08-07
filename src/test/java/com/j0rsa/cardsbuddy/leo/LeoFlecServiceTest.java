package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.DefaultData;
import com.j0rsa.cardsbuddy.common.InfoRow;
import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.common.SmallTitleRow;
import com.j0rsa.cardsbuddy.common.TitleRow;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeoFlecServiceTest {
    private String verbUrlPart = "?kvz=4dkrADn71HeCbYC86wUP8lumz53RnXJXdzl4UKot5DMyOsAoG5LNgyvnKX31aZKT5xiLFr7PFOHS66AZLfUqAhVHKW1Vr2SiUsl-xjmd0UbnLUXP3oLth0xAre1Vb0QFFSg7dS2xgqeESvDZ3VIt1N5Rqc3wajMlxylQlS1tAXFVvqZ-HVI4JI7A_r4HzsLyh_kdYKpbobAEeUJ";
    private String nounUrlPart = "?kvz=4dkrADn71HeCbYC86wSPgvtmz_-hDVHy5CibUOpNQIZTe1ZZGmUtAhtX_GpzYS_A17lakI3t0XCHPUXfnFFdVQ7BXG1WSwNFZ5xrwLe7hDF0u_N_mnLNUk0g75wQ_PLk0gkrwB2UkPeiDQZ_j-QrFgmhea2WPbOEht7NBlAAIy2";
    private TranslationRequest testRequest = DefaultData.requestFromDeToEn().build();

    @Autowired
    private LeoFlecService leoFlecService;

    @Test
    public void requestVerbFlec() {
        TitleRow titleRow = TitleRow.builder()
                .value("Konjunktiv")
                .build();
        SmallTitleRow smallTitleRow = SmallTitleRow.builder()
                .value("Konjunktiv I/II - Futur II")
                .build();
        InfoRow infoRow1 = InfoRow.builder()
                .value("er/sie/es werde/würde gelesen haben")
                .highLights(list("ge", "esen"))
                .build();
        InfoRow infoRow2 = InfoRow.builder()
                .value("du last/lasest")
                .highLights(list("ast", "asest"))
                .build();
        InfoTable table = leoFlecService.requestFlec(testRequest, verbUrlPart);

        assertThat(table.getRows()).contains(titleRow, smallTitleRow, infoRow1, infoRow2);
    }

    @Test
    public void requestNounFlec() {
        TitleRow titleRow = TitleRow.builder()
                .value("Deklination - bestimmt")
                .build();
        SmallTitleRow smallTitleRow = SmallTitleRow.builder()
                .value("Plural")
                .build();
        InfoRow infoRow1 = InfoRow.builder()
                .title("Genitiv")
                .value("der Prüfung")
                .highLights(Lists.newArrayList())
                .build();
        InfoRow infoRow2 = InfoRow.builder()
                .title("Dativ")
                .value("den Prüfungen")
                .highLights(list("en"))
                .build();
        InfoTable table = leoFlecService.requestFlec(testRequest, nounUrlPart);

        assertThat(table.getRows()).contains(titleRow, smallTitleRow, infoRow1, infoRow2);
    }

    private ArrayList<String> list(String... values) {
        return Lists.newArrayList(values);
    }
}