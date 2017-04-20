//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.29 at 10:25:38 PM MESZ 
//


package org.revager.app.model.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This complex type contains a list of
 *             aspects.
 * 
 * <p>Java class for aspectsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aspectsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aspect" type="{http://www.informatik.uni-stuttgart.de/iste/se}aspectType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aspectsType", propOrder = {
    "aspects"
})
public class Aspects {

    @XmlElement(name = "aspect", required = true)
    protected List<Aspect> aspects;

    /**
     * Gets the value of the aspects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aspects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAspects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Aspect }
     * 
     * 
     */
    public List<Aspect> getAspects() {
        if (aspects == null) {
            aspects = new ArrayList<Aspect>();
        }
        return this.aspects;
    }

    public boolean isSetAspects() {
        return ((this.aspects!= null)&&(!this.aspects.isEmpty()));
    }

    public void unsetAspects() {
        this.aspects = null;
    }

}
