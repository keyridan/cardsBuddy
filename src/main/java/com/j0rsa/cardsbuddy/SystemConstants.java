package com.j0rsa.cardsbuddy;

public class SystemConstants {
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String ACCEPT_HEADER = "Accept";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTEXT_URI = "context_uri";

    public static final String DECK_CARDS_TEMPLATE = "decks/%s?attribution=true&expand=true";
    public static final String FACT_TEMPLATE = "facts";
    public static final String TINY_CARDS_COOKIE_HEADER = "Set-Cookie";
    public static final String TINY_CARDS_PREFIX = "tinycards_";

    public static final String WRONG_CODE_EXCEPTION_MESSAGE = "Unable to get token with received code";
    public static final String FILES_IMPORTER_CRON = "0 0 0 1 * ?";
}