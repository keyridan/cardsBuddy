package com.j0rsa.cardsbuddy;

import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.UUID;

@Data
public class TestData {
    private String id;
    private String email;
    private String password;
    private UUID deckId;
    private String testTinyCardsJwt;
    private String testTinyCardsSession;

    public static TestData loadFrom(String filename) throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream in = TestData.class
                .getResourceAsStream(String.format("/%s", filename))) {
            return yaml.loadAs(in, TestData.class);
        }
    }
}
