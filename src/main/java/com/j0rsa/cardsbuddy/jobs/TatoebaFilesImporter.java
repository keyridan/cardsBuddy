package com.j0rsa.cardsbuddy.jobs;

import com.j0rsa.cardsbuddy.SystemConstants;
import com.j0rsa.cardsbuddy.integration.info.tatoeba.TatoebaLoadDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TatoebaFilesImporter {
    private final TatoebaLoadDataService loadDataService;

    public TatoebaFilesImporter(TatoebaLoadDataService loadDataService) {
        this.loadDataService = loadDataService;
    }

    @Scheduled(cron = SystemConstants.FILES_IMPORTER_CRON)
    public void importFiles() throws IOException {
        loadDataService.loadSentencesData();
        loadDataService.loadLinksData();
    }
}
