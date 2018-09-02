package com.j0rsa.cardsbuddy.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class FileLoadHistory {
    @Id
    private UUID id;
    private LocalDateTime starDate;
    private LocalDateTime endDate;
}
