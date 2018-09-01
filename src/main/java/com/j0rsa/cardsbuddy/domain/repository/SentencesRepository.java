package com.j0rsa.cardsbuddy.domain.repository;

import com.j0rsa.cardsbuddy.domain.model.Sentences;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SentencesRepository
        extends CrudRepository<Sentences, Long>, QuerydslPredicateExecutor<Sentences> {
}
