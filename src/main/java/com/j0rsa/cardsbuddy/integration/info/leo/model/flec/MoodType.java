package com.j0rsa.cardsbuddy.integration.info.leo.model.flec;

import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.noun.VariantType;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.verb.TenseType;
import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moodType", propOrder = {
        "tense",
        "variant"
})
public class MoodType {

    protected List<TenseType> tense = Lists.newArrayList();
    protected List<VariantType> variant = Lists.newArrayList();
    @XmlAttribute(name = "title")
    protected String title;
}
