package com.j0rsa.cardsbuddy.domain.repository;

import com.j0rsa.cardsbuddy.domain.model.FileLoadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FileLoadHistoryRepository
        extends MongoRepository<FileLoadHistory, UUID> {
}
