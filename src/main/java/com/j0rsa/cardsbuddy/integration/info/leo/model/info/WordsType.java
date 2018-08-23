package com.j0rsa.cardsbuddy.integration.info.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wordsType", propOrder = {
        "word"
})
public class WordsType {

    protected List<String> word;

    String getFirstWord() {
        return word.isEmpty()
                ? null
                : word.get(0);
    }
}
