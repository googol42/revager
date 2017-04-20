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
 * 
 *           Used for referencing aspects by their IDs.
 *         
 * 
 * <p>Java class for aspectsIdType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aspectsIdType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aspect-id" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aspectsIdType", propOrder = {
    "aspectIds"
})
public class AspectsIds {

    @XmlElement(name = "aspect-id", required = true)
    protected List<String> aspectIds;

    /**
     * Gets the value of the aspectIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aspectIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAspectIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAspectIds() {
        if (aspectIds == null) {
            aspectIds = new ArrayList<String>();
        }
        return this.aspectIds;
    }

    public boolean isSetAspectIds() {
        return ((this.aspectIds!= null)&&(!this.aspectIds.isEmpty()));
    }

    public void unsetAspectIds() {
        this.aspectIds = null;
    }

}
