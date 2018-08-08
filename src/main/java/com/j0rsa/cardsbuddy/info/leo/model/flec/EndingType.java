
package com.j0rsa.cardsbuddy.info.leo.model.flec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for endingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="endingType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="pc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "endingType", propOrder = {
    "value"
})
public class EndingType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "pc")
    protected String pc;
    @XmlAttribute(name = "o")
    protected String o;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the pc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPc() {
        return pc;
    }

    /**
     * Sets the value of the pc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPc(String value) {
        this.pc = value;
    }

    /**
     * Gets the value of the o property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getO() {
        return o;
    }

    /**
     * Sets the value of the o property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setO(String value) {
        this.o = value;
    }

}
