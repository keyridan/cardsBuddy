package com.j0rsa.cardsbuddy.domain.service;

import com.j0rsa.cardsbuddy.domain.model.FileLoadHistory;
import com.j0rsa.cardsbuddy.domain.repository.FileLoadHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileLoadHistoryService {
    private final FileLoadHistoryRepository repository;

    @Autowired
    public FileLoadHistoryService(FileLoadHistoryRepository repository) {
        this.repository = repository;
    }

    public FileLoadHistory create() {
        UUID uuid = UUID.randomUUID();
        FileLoadHistory history = FileLoadHistory.builder()
                .starDate(LocalDateTime.now())
                .id(uuid)
                .build();
        return repository.save(history);
    }

    public void end(FileLoadHistory history) {
        history.setEndDate(LocalDateTime.now());
        repository.save(history);
    }
}
