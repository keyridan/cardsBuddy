package com.j0rsa.cardsbuddy.info.leo.model.flec.verb;

import com.j0rsa.cardsbuddy.info.leo.model.info.ContentContainer;
import com.j0rsa.cardsbuddy.info.leo.model.info.ValueContainer;
import org.assertj.core.util.Lists;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;


/**
 * <p>Java class for verbType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="verbType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="ppron">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="g" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="opt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ending">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="pc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pobj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rpron" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="object" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aux" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="raux" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pref">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="pc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verbType", propOrder = {
        "content"
})
public class VerbType extends ContentContainer {

    @XmlElementRefs({
            @XmlElementRef(name = "ending", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "prep", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "rpron", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "aux", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "ppron", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "raux", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "pref", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "part", type = JAXBElement.class, required = false)
    })
    protected List<Serializable> content = Lists.newArrayList();

    /**
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link VerbType.Ending }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link VerbType.Ppron }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link VerbType.Pref }{@code >}
     */
    @Override
    public List<Serializable> getContent() {
        return this.content;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="pc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Ending implements ValueContainer {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "pc")
        protected String pc;
        @XmlAttribute(name = "o")
        protected String o;


        @Override
        public String parseValues() {
            return getValue();
        }

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the pc property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getPc() {
            return pc;
        }

        /**
         * Sets the value of the pc property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setPc(String value) {
            this.pc = value;
        }

        /**
         * Gets the value of the o property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getO() {
            return o;
        }

        /**
         * Sets the value of the o property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setO(String value) {
            this.o = value;
        }
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="g" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="opt" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Ppron implements ValueContainer {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "g")
        protected String g;
        @XmlAttribute(name = "opt")
        protected String opt;

        @Override
        public String parseValues() {
            return opt != null
                    ? optValue()
                    : value;
        }

        private String optValue() {
            return value != null
                    ? String.format("(%s)", value)
                    : null;
        }

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the g property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getG() {
            return g;
        }

        /**
         * Sets the value of the g property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setG(String value) {
            this.g = value;
        }

        /**
         * Gets the value of the opt property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getOpt() {
            return opt;
        }

        /**
         * Sets the value of the opt property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setOpt(String value) {
            this.opt = value;
        }
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="pc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Pref implements ValueContainer {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "pc")
        protected String pc;

        public String parseValues() {
            return getValue();
        }

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the pc property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getPc() {
            return pc;
        }

        /**
         * Sets the value of the pc property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setPc(String value) {
            this.pc = value;
        }

    }

}
