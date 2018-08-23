package com.j0rsa.cardsbuddy.integration.info.leo.model.info;

import lombok.Data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reprType", propOrder = {
        "content"
})
public class ReprType extends ContentContainer {
    @XmlElementRefs({
            @XmlElementRef(name = "domain", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "flecttabref", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sr", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "small", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "m", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "content"
    })
    public static class M extends ContentContainer {

        @XmlElementRef(name = "b", type = JAXBElement.class, required = false)
        @XmlMixed
        protected List<Serializable> content;
        @XmlAttribute(name = "t")
        protected String t;

        /**
         * Gets the value of the content property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link String }
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }

        /**
         * Gets the value of the t property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getT() {
            return t;
        }

        /**
         * Sets the value of the t property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setT(String value) {
            this.t = value;
        }

    }
}
