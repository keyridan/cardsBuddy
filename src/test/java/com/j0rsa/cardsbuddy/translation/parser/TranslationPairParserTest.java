package com.j0rsa.cardsbuddy.translation.parser;

import com.j0rsa.cardsbuddy.DefaultData;
import com.j0rsa.cardsbuddy.translation.exceptions.ParserException;
import com.j0rsa.cardsbuddy.translation.model.TranslationPair;
import org.junit.Test;

import java.util.List;

import static com.j0rsa.cardsbuddy.DefaultData.readJson;
import static com.j0rsa.cardsbuddy.translation.parser.TranslationPairParser.parse;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslationPairParserTest {

    @Test
    public void testParseTranslationPair() throws Exception {
        String json = "[\"Prüfung\",\"exam\",null,null,1]";
        List list = readJson(json);
        TranslationPair expected = DefaultData.translationEnToDePair();

        TranslationPair translationPair = parse(list);

        assertThat(translationPair).isEqualTo(expected);
    }

    @Test(expected = ParserException.class)
    public void testParseTranslationPairWhenPairIsMissing() throws Exception {
        String json = "[123,456,null,null,1]";
        List list = readJson(json);

        parse(list);
    }

    @Test(expected = ParserException.class)
    public void testParseTranslationPairWhenPairIsNull() throws Exception {
        String json = "[null,null,null,null,1]";
        List list = readJson(json);

        parse(list);
    }
}