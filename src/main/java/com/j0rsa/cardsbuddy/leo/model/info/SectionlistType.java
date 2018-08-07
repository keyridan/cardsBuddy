package com.j0rsa.cardsbuddy.leo.model.info;

import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sectionlistType", propOrder = {
        "section"
})
public class SectionlistType {

    protected List<SectionType> section = Lists.newArrayList();
    @XmlAttribute(name = "sectionsort")
    protected String sectionsort;

    List<SideType> getFirstEntryFirstSectionSidesIfExist() {
        return section.isEmpty()
                ? Lists.newArrayList()
                : section.get(0).getFirstEntrySidesIfExist();
    }
}
