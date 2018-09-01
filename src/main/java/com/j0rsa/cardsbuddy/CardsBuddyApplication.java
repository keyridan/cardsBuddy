package com.j0rsa.cardsbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CardsBuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsBuddyApplication.class, args);
    }
}
