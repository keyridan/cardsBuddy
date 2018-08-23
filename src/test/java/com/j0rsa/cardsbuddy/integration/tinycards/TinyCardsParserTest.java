package com.j0rsa.cardsbuddy.integration.tinycards;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TinyCardsParserTest {
    @Test
    public void testParseHeader() {
        List<String> headers = Lists.newArrayList(
                "jwt_token=big.test.value; Domain=some.com; Expires=Mon, 01-Jan-2121 01:01:01 GMT; Max-Age=31540000.0; Path=/",
                "session=big.session.test.value; HttpOnly; Path=/"
        );

        Map<String, String> parsedHeaders = TinyCardsParser.parseHeaders(headers);

        assertThat(parsedHeaders).hasSize(7);
        assertThat(parsedHeaders).containsAllEntriesOf(expectedMap());
    }

    @Test
    public void testParseAuthenticationHeader() {
        List<String> headers = Lists.newArrayList(
                "jwt_token=big.test.value; Domain=some.com; Expires=Mon, 01-Jan-2121 01:01:01 GMT; Max-Age=31540000.0; Path=/",
                "session=big.session.test.value; HttpOnly; Path=/"
        );

        Map<String, String> parsedHeaders = TinyCardsParser.parseAuthenticationHeaders(headers);

        assertThat(parsedHeaders).hasSize(2);
        assertThat(parsedHeaders).containsAllEntriesOf(expectedAuthenticationMap());
    }

    private Map<String, String> expectedMap() {
        Map<String, String> expectedMap = expectedAuthenticationMap();
        expectedMap.put("tinycards_Domain", "Domain=some.com");
        expectedMap.put("tinycards_Max-Age", "Max-Age=31540000.0");
        expectedMap.put("tinycards_Path", "Path=/");
        expectedMap.put("tinycards_HttpOnly", "HttpOnly=true");
        return expectedMap;
    }

    private Map<String, String> expectedAuthenticationMap() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("tinycards_jwt_token", "jwt_token=big.test.value");
        expectedMap.put("tinycards_session", "session=big.session.test.value");
        return expectedMap;
    }

}