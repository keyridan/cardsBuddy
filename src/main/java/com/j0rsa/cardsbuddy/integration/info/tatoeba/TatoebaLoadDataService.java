package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class TatoebaLoadDataService {
    private final TatoebaFileReader tatoebaFileReader;
    private final TatoebaFileDataImporter fileDataImporter;

    @Autowired
    public TatoebaLoadDataService(TatoebaFileReader tatoebaFileReader, TatoebaFileDataImporter fileDataImporter) {
        this.tatoebaFileReader = tatoebaFileReader;
        this.fileDataImporter = fileDataImporter;
    }

    public void loadSentencesData() throws IOException {
        File file = tatoebaFileReader.readSentences();
        fileDataImporter.importSentencesData(file);
    }

    public void loadLinksData() throws IOException {
        File file = tatoebaFileReader.readLinks();
        fileDataImporter.importLinksData(file);
    }
}
