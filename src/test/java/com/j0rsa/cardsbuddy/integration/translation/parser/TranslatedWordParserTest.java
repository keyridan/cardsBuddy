package com.j0rsa.cardsbuddy.integration.translation.parser;

import com.j0rsa.cardsbuddy.integration.translation.exceptions.ParserException;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslatedWord;
import org.junit.Test;

import java.util.List;

import static com.j0rsa.cardsbuddy.DefaultData.readJson;
import static com.j0rsa.cardsbuddy.integration.translation.parser.TranslatedWordParser.parse;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslatedWordParserTest {
    @Test
    public void testParseTranslatedWord() throws Exception {
        String json = "[\"Prüfung\",[\"examination\",\"test\",\"audit\",\"testing\",\"check\",\"exam\"],null,0.35656098,\"die\",2]";
        List list = readJson(json);
        TranslatedWord expected = TranslatedWord.builder().article("die").word("Prüfung").build();

        TranslatedWord translatedWord = parse(list);

        assertThat(translatedWord).isEqualTo(expected);
    }

    @Test
    public void testParseTranslatedWordWhenArticleIsAbsent() throws Exception {
        String json = "[\"test\",[\"Test\",\"Prüfung\",\"Probe\",\"Versuch\",\"Untersuchung\",\"Kriterium\"],[15246,34330],0.11396374]";
        List list = readJson(json);
        TranslatedWord expected = TranslatedWord.builder().word("test").build();

        TranslatedWord translatedWord = parse(list);

        assertThat(translatedWord).isEqualTo(expected);
    }

    @Test(expected = ParserException.class)
    public void testParseTranslatedWordWhenWordIsNotString() throws Exception {
        String json = "[123,[\"Test\",\"Prüfung\",\"Probe\",\"Versuch\",\"Untersuchung\",\"Kriterium\"],[15246,34330],0.11396374]";
        List list = readJson(json);

        parse(list);
    }

    @Test(expected = ParserException.class)
    public void testParseTranslatedWordWhenWordIsMissing() throws Exception {
        String json = "[[\"Test\",\"Prüfung\",\"Probe\",\"Versuch\",\"Untersuchung\",\"Kriterium\"],[15246,34330],0.11396374]";
        List list = readJson(json);

        parse(list);
    }
}