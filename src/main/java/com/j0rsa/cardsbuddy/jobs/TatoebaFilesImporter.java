package com.j0rsa.cardsbuddy.jobs;

import com.j0rsa.cardsbuddy.SystemConstants;
import com.j0rsa.cardsbuddy.domain.model.FileLoadHistory;
import com.j0rsa.cardsbuddy.domain.service.FileLoadHistoryService;
import com.j0rsa.cardsbuddy.integration.info.tatoeba.TatoebaLoadDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TatoebaFilesImporter {
    private final TatoebaLoadDataService loadDataService;
    private final FileLoadHistoryService historyService;

    public TatoebaFilesImporter(TatoebaLoadDataService loadDataService, FileLoadHistoryService historyService) {
        this.loadDataService = loadDataService;
        this.historyService = historyService;
    }

    @Scheduled(cron = SystemConstants.FILES_IMPORTER_CRON)
    public void importFiles() throws IOException {
        FileLoadHistory loadHistory = historyService.create();
        loadDataService.loadSentencesData(loadHistory.getId());
        loadDataService.loadLinksData(loadHistory.getId());
        historyService.end(loadHistory);
    }
}
