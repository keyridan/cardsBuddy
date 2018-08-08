
package com.j0rsa.cardsbuddy.info.leo.model.flec.noun;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nounType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nounType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="art" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="die"/>
 *               &lt;enumeration value="der"/>
 *               &lt;enumeration value="den"/>
 *               &lt;enumeration value="eine"/>
 *               &lt;enumeration value="einer"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ending">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value=""/>
 *               &lt;enumeration value="en"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nounType", propOrder = {
    "art",
    "ending"
})
public class NounType {

    protected String art;
    @XmlElement(required = true)
    protected String ending;

    /**
     * Gets the value of the art property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArt() {
        return art;
    }

    /**
     * Sets the value of the art property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArt(String value) {
        this.art = value;
    }

    /**
     * Gets the value of the ending property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnding() {
        return ending;
    }

    /**
     * Sets the value of the ending property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnding(String value) {
        this.ending = value;
    }

}
