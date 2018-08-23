
package com.j0rsa.cardsbuddy.integration.info.leo.model.flec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for explanationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="explanationType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="expid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "explanationType", propOrder = {
    "value"
})
public class ExplanationType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "expid")
    protected String expid;

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
     * Gets the value of the expid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpid() {
        return expid;
    }

    /**
     * Sets the value of the expid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpid(String value) {
        this.expid = value;
    }

}
