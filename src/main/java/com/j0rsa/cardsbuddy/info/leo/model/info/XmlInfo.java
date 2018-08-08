package com.j0rsa.cardsbuddy.info.leo.model.info;

import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xmlInfo", propOrder = {
        "sectionlist"
})
public class XmlInfo {

    @XmlElement(required = true)
    protected SectionlistType sectionlist;

    public List<SideType> getFirstEntryFirstSectionSidesIfExist() {
        return sectionlist != null
                ? sectionlist.getFirstEntryFirstSectionSidesIfExist()
                : Lists.newArrayList();
    }
}
