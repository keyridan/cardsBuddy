package com.j0rsa.cardsbuddy.translation.model;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public abstract class Response {
    private List<String> errorMessages = Lists.newArrayList();
}
