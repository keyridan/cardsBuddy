package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class TatoebaFileReader {
    private final static String TMP_PATH_TEMPLATE = "/tmp/%s";
    private String sentencesFileUrl;
    private String linksFileUrl;

    public TatoebaFileReader(@Value("${api.tatoeba.sentences}") final String sentencesFileUrl,
                             @Value("${api.tatoeba.links}") final String linksFileUrl) {
        this.sentencesFileUrl = sentencesFileUrl;
        this.linksFileUrl = linksFileUrl;
    }

    public File readSentences() throws IOException {
        return readFile(sentencesFileUrl);
    }

    public File readLinks() throws IOException {
        return readFile(linksFileUrl);
    }

    private File readFile(String fileUrl) throws IOException {
        String[] url = fileUrl.split("//");
        String pathName = String.format(TMP_PATH_TEMPLATE, url[url.length - 1]);
        File file = FileUtils.getFile(pathName);
        FileUtils.copyURLToFile(new URL(fileUrl), file, 1000, 1000000000);

        return file;
    }
}
