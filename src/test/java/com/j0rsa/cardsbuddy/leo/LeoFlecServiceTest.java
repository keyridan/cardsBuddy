package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.DefaultData;
import com.j0rsa.cardsbuddy.common.InfoData;
import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.common.SmallTitledRows;
import com.j0rsa.cardsbuddy.common.Title;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String titleRowValue = "Konjunktiv";
        String smallTitleRowValue = "Konjunktiv I/II - Futur II";

        InfoData infoData1 = InfoData.builder()
                .value("er/sie/es werde/würde gelesen haben")
                .highLights(list("ge", "esen"))
                .build();
        InfoData infoData2 = InfoData.builder()
                .value("du last/lasest")
                .highLights(list("ast", "asest"))
                .build();
        InfoTable table = leoFlecService.requestFlec(testRequest, verbUrlPart);

        assertThat(table.getRows()).extracting(Title::getValue).contains(titleRowValue);
        assertThat(extractedTitledRow(table)).extracting(SmallTitledRows::getValue).contains(smallTitleRowValue);
        assertThat(extractedInfoData(table)).contains(infoData1, infoData2);
    }

    @Test
    public void requestNounFlec() {
        String titleRowValue = "Deklination - bestimmt";
        String smallTitleRowValue = "Plural";
        InfoData infoData1 = InfoData.builder()
                .title("Genitiv")
                .value("der Prüfung")
                .highLights(Lists.newArrayList())
                .build();
        InfoData infoData2 = InfoData.builder()
                .title("Dativ")
                .value("den Prüfungen")
                .highLights(list("en"))
                .build();
        InfoTable table = leoFlecService.requestFlec(testRequest, nounUrlPart);

        assertThat(table.getRows()).extracting(Title::getValue).contains(titleRowValue);
        assertThat(extractedTitledRow(table)).extracting(SmallTitledRows::getValue).contains(smallTitleRowValue);
        assertThat(extractedInfoData(table)).contains(infoData1, infoData2);
    }

    private List<InfoData> extractedInfoData(InfoTable table) {
        return extractedTitledRows(table)
                .flatMap(titledRows -> titledRows.getInfoData().stream())
                .collect(Collectors.toList());
    }

    private Stream<SmallTitledRows> extractedTitledRows(InfoTable table) {
        return table.getRows().stream()
                .flatMap(title -> title.getRows().stream());
    }

    private List<SmallTitledRows> extractedTitledRow(InfoTable table) {
        return extractedTitledRows(table)
                .collect(Collectors.toList());
    }

    private ArrayList<String> list(String... values) {
        return Lists.newArrayList(values);
    }
}