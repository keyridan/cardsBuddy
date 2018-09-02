package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class TatoebaLoadDataService {
    private final TatoebaFileReader tatoebaFileReader;
    private final TatoebaFileDataImporter fileDataImporter;

    @Autowired
    public TatoebaLoadDataService(TatoebaFileReader tatoebaFileReader, TatoebaFileDataImporter fileDataImporter) {
        this.tatoebaFileReader = tatoebaFileReader;
        this.fileDataImporter = fileDataImporter;
    }

    public void loadSentencesData(UUID updateId) throws IOException {
        File file = tatoebaFileReader.readSentences();
        fileDataImporter.importSentencesData(file, updateId);
    }

    public void loadLinksData(UUID updateId) throws IOException {
        File file = tatoebaFileReader.readLinks();
        fileDataImporter.importLinksData(file, updateId);
    }
}
