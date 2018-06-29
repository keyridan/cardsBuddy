package com.j0rsa.cardsbuddy.translation.parser;

import com.j0rsa.cardsbuddy.translation.model.Translation;
import org.junit.Test;

import static com.j0rsa.cardsbuddy.DefaultData.translationFromDeToEn;
import static com.j0rsa.cardsbuddy.DefaultData.translationFromEnToDe;
import static com.j0rsa.cardsbuddy.translation.parser.TranslationParser.parseTranslatedWord;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslationParserTest {

    @Test
    public void testParseFromDeToEn() throws Exception {
        String json = "[[[\"exam\",\"Prüfung\",null,null,1]],[[\"noun\",[\"examination\",\"test\",\"audit\",\"testing\",\"check\",\"exam\",\"inspection\",\"checking\",\"consideration\",\"trial\",\"proof\",\"assay\",\"perusal\",\"quiz\"],[[\"examination\",[\"Prüfung\",\"Untersuchung\",\"Überprüfung\",\"Examen\",\"Vernehmung\",\"Durchsicht\"],[15246,66176],0.34023288],[\"test\",[\"Test\",\"Prüfung\",\"Probe\",\"Versuch\",\"Untersuchung\",\"Kriterium\"],[15246,34330],0.11396374],[\"audit\",[\"Prüfung\",\"Revision\",\"Rechnungsprüfung\",\"Buchprüfung\",\"Kassenprüfung\",\"Bücherrevision\"],null,0.066995822],[\"testing\",[\"Testen\",\"Prüfung\",\"Erprobung\"],[66176],0.059123605],[\"check\",[\"Scheck\",\"Prüfung\",\"Kontrolle\",\"Überprüfung\",\"Rechnung\",\"Karo\"],[24550],0.046770621],[\"exam\",[\"Prüfung\",\"Examen\",\"Klausur\"],[15246],0.040005032],[\"inspection\",[\"Inspektion\",\"Prüfung\",\"Kontrolle\",\"Überprüfung\",\"Untersuchung\",\"Besichtigung\"],null,0.020432571],[\"checking\",[\"Überprüfung\",\"Kontrolle\",\"Prüfung\",\"Abfertigung\",\"Zügelung\"],null,0.018031681],[\"consideration\",[\"Berücksichtigung\",\"Prüfung\",\"Betracht\",\"Überlegung\",\"Rücksicht\",\"Beachtung\"],null,0.0058540297],[\"trial\",[\"Versuch\",\"Prozess\",\"Prüfung\",\"Gericht\",\"Verhandlung\",\"Probe\"],[34330],0.0040867715],[\"proof\",[\"Beweis\",\"Nachweis\",\"Korrektur\",\"Probe\",\"Prüfung\",\"Ausweis\"],null,0.00034611137],[\"assay\",[\"Probe\",\"Prüfung\"],[24550],0.00023787863],[\"perusal\",[\"Lektüre\",\"Kenntnisnahme\",\"Prüfung\",\"sorgfältige Durchsicht\"],null,3.0719129e-05],[\"quiz\",[\"Quiz\",\"Prüfung\",\"Ratespiel\"],null,2.841055e-05]],\"Prüfung\",1]],\"de\"]";

        testParse(json, translationFromDeToEn().build());
    }

    @Test
    public void testParseFromEnToDe() throws Exception {
        String json = "[[[\"Prüfung\",\"exam\",null,null,1]],[[\"noun\",[\"Prüfung\",\"Examen\",\"Klausur\"],[[\"Prüfung\",[\"examination\",\"test\",\"audit\",\"testing\",\"check\",\"exam\"],null,0.35656098,\"die\",2],[\"Examen\",[\"exam\",\"examination\",\"finals\"],null,0.021413151,\"das\",3],[\"Klausur\",[\"exam\",\"enclosure\",\"cloister\",\"seclusion\",\"conclave\",\"paper\"],null,0.0089263292,\"die\",2]],\"exam\",1]],\"en\"]";

        testParse(json, translationFromEnToDe().build());
    }

    private void testParse(String json, Translation expectedTranslation) {
        Translation translation = parseTranslatedWord(json);

        assertThat(translation).isEqualTo(expectedTranslation);
    }
}