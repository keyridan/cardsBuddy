package com.j0rsa.cardsbuddy.integration.translation.model;

import lombok.Getter;
import org.assertj.core.util.Lists;

import java.util.Optional;

public class Language {
    public enum Code {
        AUTO("auto", "Detect language", ""),
        AF("af", "Afrikaans", "afr"),
        SQ("sq", "Albanian", "sqi"),
        AM("am", "Amharic", "amh"),
        AR("ar", "Arabic", "ara"),
        HY("hy", "Armenian", "hye"),
        AZ("az", "Azerbaijani", "aze"),
        EU("eu", "Basque", "eus"),
        BE("be", "Belarusian", "bel"),
        BN("bn", "Bengali", "ben"),
        BS("bs", "Bosnian", "bos"),
        BG("bg", "Bulgarian", "bul"),
        CA("ca", "Catalan", "cat"),
        CEB("ceb", "Cebuano", "ceb"),
        NY("ny", "Chichewa", "nya"),
        ZHCN("zh-cn", "Chinese Simplified", "cmn"),
        ZHTW("zh-tw", "Chinese Traditional", "cmn"),
        CO("co", "Corsican", "cos"),
        HR("hr", "Croatian", "hrv"),
        CS("cs", "Czech", "ces"),
        DA("da", "Danish", "dan"),
        NL("nl", "Dutch", "nld"),
        EN("en", "English", "eng"),
        EO("eo", "Esperanto", "epo"),
        ET("et", "Estonian", "est"),
        TL("tl", "Filipino", "fil"),
        FI("fi", "Finnish", "fin"),
        FR("fr", "French", "fra"),
        FY("fy", "Frisian", "fry"),
        GL("gl", "Galician", "glg"),
        KA("ka", "Georgian", "kat"),
        DE("de", "German", "deu"),
        EL("el", "Greek", "ell"),
        GU("gu", "Gujarati", "guj"),
        HT("ht", "Haitian Creole", "hat"),
        HA("ha", "Hausa", "hau"),
        HAW("haw", "Hawaiian", "haw"),
        IW("iw", "Hebrew", "heb"),
        HI("hi", "Hindi", "hin"),
        HMN("hmn", "Hmong", "hmn"),
        HU("hu", "Hungarian", "hun"),
        IS("is", "Icelandic", "isl"),
        IG("ig", "Igbo", "ibo"),
        ID("id", "Indonesian", "ind"),
        GA("ga", "Irish", "gle"),
        IT("it", "Italian", "ita"),
        JA("ja", "Japanese", "jpn"),
        JW("jw", "Javanese", "jav"),
        KN("kn", "Kannada", "kan"),
        KK("kk", "Kazakh", "kaz"),
        KM("km", "Khmer", "khm"),
        KO("ko", "Korean", "kor"),
        KU("ku", "Kurdish (Kurmanji)", "kur"),
        KY("ky", "Kyrgyz", "kir"),
        LO("lo", "Lao", "lao"),
        LA("la", "Latin", "lat"),
        LV("lv", "Latvian", "lav"),
        LT("lt", "Lithuanian", "lit"),
        LB("lb", "Luxembourgish", "ltz"),
        MK("mk", "Macedonian", "mkd"),
        MG("mg", "Malagasy", "mlg"),
        MS("ms", "Malay", "msa"),
        ML("ml", "Malayalam", "mal"),
        MT("mt", "Maltese", "mlt"),
        MI("mi", "Maori", "mri"),
        MR("mr", "Marathi", "mar"),
        MN("mn", "Mongolian", "mon"),
        MY("my", "Myanmar (Burmese)", "mya"),
        NE("ne", "Nepali", "nep"),
        NO("no", "Norwegian", "nor"),
        PS("ps", "Pashto", "pus"),
        FA("fa", "Persian", "fas"),
        PL("pl", "Polish", "pol"),
        PT("pt", "Portuguese", "por"),
        MA("ma", "Punjabi", "pan"),
        RO("ro", "Romanian", "ron"),
        RU("ru", "Russian", "rus"),
        SM("sm", "Samoan", "smo"),
        GD("gd", "Scots Gaelic", "gla"),
        SR("sr", "Serbian", "srp"),
        ST("st", "Sesotho", "sot"),
        SN("sn", "Shona", "sna"),
        SD("sd", "Sindhi", "snd"),
        SI("si", "Sinhala", "sin"),
        SK("sk", "Slovak", "slk"),
        SL("sl", "Slovenian", "slv"),
        SO("so", "Somali", "som"),
        ES("es", "Spanish", "spa"),
        SU("su", "Sundanese", "sun"),
        SW("sw", "Swahili", "swa"),
        SV("sv", "Swedish", "swe"),
        TG("tg", "Tajik", "tgk"),
        TA("ta", "Tamil", "tam"),
        TE("te", "Telugu", "tel"),
        TH("th", "Thai", "tha"),
        TR("tr", "Turkish", "tur"),
        UK("uk", "Ukrainian", "ukr"),
        UR("ur", "Urdu", "urd"),
        UZ("uz", "Uzbek", "uzb"),
        VI("vi", "Vietnamese", "vie"),
        CY("cy", "Welsh", "cym"),
        XH("xh", "Xhosa", "xho"),
        YI("yi", "Yiddish", "yid"),
        YO("yo", "Yoruba", "yor"),
        ZU("zu", "Zulu", "zul");

        @Getter
        private String value;
        @Getter
        private String iso639_3Value;
        @Getter
        private String name;

        Code(String value, String name, String iso639_3Value) {
            this.value = value;
            this.name = name;
            this.iso639_3Value = iso639_3Value;
        }

        public static Optional<Code> of(String value) {
            return Lists.newArrayList(values())
                    .stream()
                    .filter(code -> code.value.equals(value))
                    .findFirst();
        }

        public String lowerCaseValue() {
            return value != null
                    ? value.toLowerCase()
                    : "";
        }

        public boolean lowerCaseEqual(String lang) {
            return lowerCaseValue().equals(lang);
        }
    }
}
