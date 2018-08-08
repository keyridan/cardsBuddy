package com.j0rsa.cardsbuddy.info.leo.model.flec.noun;

import com.j0rsa.cardsbuddy.info.leo.model.flec.ExplanationType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.MoodType;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nountabType", propOrder = {
        "gender",
        "explanation",
        "mood"
})
public class NountabType {

    @XmlElement(required = true)
    protected String gender;
    @XmlElement(required = true)
    protected ExplanationType explanation;
    protected List<MoodType> mood;
    @XmlAttribute(name = "title")
    protected String title;
}
