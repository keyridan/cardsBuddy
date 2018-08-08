package com.j0rsa.cardsbuddy.info.leo.model.info;

import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ffsynlistType", propOrder = {
        "side"
})
public class FfsynlistType {

    protected List<SideType> side = Lists.newArrayList();
    @XmlAttribute(name = "word")
    protected String word;
}
