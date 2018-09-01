package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TatoebaFileReaderTest {
    @Autowired
    private TatoebaFileReader reader;

    @Test
    public void readSentences() throws Exception {
        File file = reader.readSentences();

        assertThat(file).canRead();
    }
}