package com.j0rsa.cardsbuddy.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servicedataType", propOrder = {
        "value"
})
public class ServicedataType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "entries")
    protected String entries;
    @XmlAttribute(name = "hostName")
    protected String hostName;
    @XmlAttribute(name = "login")
    protected String login;
    @XmlAttribute(name = "nick")
    protected String nick;
    @XmlAttribute(name = "osName")
    protected String osName;
    @XmlAttribute(name = "queries")
    protected String queries;
    @XmlAttribute(name = "swName")
    protected String swName;
    @XmlAttribute(name = "timeUsed")
    protected String timeUsed;
}
