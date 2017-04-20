//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.29 at 10:25:38 PM MESZ 
//


package org.revager.app.model.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                  A catalog of aspects.
 *                
 * 
 * <p>Java class for catalogExchangeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="catalogExchangeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aspects" type="{http://www.informatik.uni-stuttgart.de/iste/se}aspectsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "catalogExchangeType", propOrder = {
    "description",
    "aspects"
})
public class Catalog {

    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected Aspects aspects;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Gets the value of the aspects property.
     * 
     * @return
     *     possible object is
     *     {@link Aspects }
     *     
     */
    public Aspects getAspects() {
        return aspects;
    }

    /**
     * Sets the value of the aspects property.
     * 
     * @param value
     *     allowed object is
     *     {@link Aspects }
     *     
     */
    public void setAspects(Aspects value) {
        this.aspects = value;
    }

    public boolean isSetAspects() {
        return (this.aspects!= null);
    }

}
