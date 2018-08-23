package com.j0rsa.cardsbuddy.integration.info.leo.model.info;

import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sectionType", propOrder = {
        "entry",
})
public class SectionType {

    protected List<EntryType> entry = Lists.newArrayList();
    @XmlAttribute(name = "sctCount")
    protected String sctCount;
    @XmlAttribute(name = "sctDirectCnt")
    protected String sctDirectCnt;
    @XmlAttribute(name = "sctName")
    protected String sctName;
    @XmlAttribute(name = "sctTitle")
    protected String sctTitle;
    @XmlAttribute(name = "sctTotalCnt")
    protected String sctTotalCnt;
    @XmlAttribute(name = "sctnum")
    protected String sctnum;

    List<SideType> getFirstEntrySidesIfExist() {
        return entry.isEmpty()
                ? Lists.newArrayList()
                : entry.get(0).getSide();
    }
}
