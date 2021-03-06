//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.29 at 10:25:38 PM MESZ 
//

package org.revager.app.model.schema;

import java.util.Calendar;
import java.util.Observable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A review session.
 * 
 * <p>
 * Java class for meetingType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="meetingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="planned-date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="planned-start" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="planned-end" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="planned-location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="protocol" type="{http://www.informatik.uni-stuttgart.de/iste/se}protocolType" minOccurs="0"/>
 *         &lt;element name="canceled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "meetingType", propOrder = { "plannedDate", "plannedStart", "plannedEnd", "plannedLocation", "protocol",
		"canceled", "comments" })
public class Meeting extends Observable {

	@XmlElement(name = "planned-date", required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "date")
	protected Calendar plannedDate;
	@XmlElement(name = "planned-start", required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter2.class)
	@XmlSchemaType(name = "time")
	protected Calendar plannedStart;
	@XmlElement(name = "planned-end", required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter3.class)
	@XmlSchemaType(name = "time")
	protected Calendar plannedEnd;
	@XmlElement(name = "planned-location", required = true)
	protected String plannedLocation;
	protected Protocol protocol;
	protected String canceled;
	@XmlElement(required = true)
	protected String comments;

	/**
	 * Gets the value of the plannedDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Calendar getPlannedDate() {
		return plannedDate;
	}

	/**
	 * Sets the value of the plannedDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlannedDate(Calendar value) {
		this.plannedDate = value;
	}

	public boolean isSetPlannedDate() {
		return (this.plannedDate != null);
	}

	/**
	 * Gets the value of the plannedStart property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Calendar getPlannedStart() {
		return plannedStart;
	}

	/**
	 * Sets the value of the plannedStart property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlannedStart(Calendar value) {
		this.plannedStart = value;
	}

	public boolean isSetPlannedStart() {
		return (this.plannedStart != null);
	}

	/**
	 * Gets the value of the plannedEnd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Calendar getPlannedEnd() {
		return plannedEnd;
	}

	/**
	 * Sets the value of the plannedEnd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlannedEnd(Calendar value) {
		this.plannedEnd = value;
	}

	public boolean isSetPlannedEnd() {
		return (this.plannedEnd != null);
	}

	/**
	 * Gets the value of the plannedLocation property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPlannedLocation() {
		return plannedLocation;
	}

	/**
	 * Sets the value of the plannedLocation property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlannedLocation(String value) {
		this.plannedLocation = value;
	}

	public boolean isSetPlannedLocation() {
		return (this.plannedLocation != null);
	}

	/**
	 * Gets the value of the protocol property.
	 * 
	 * @return possible object is {@link Protocol }
	 * 
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * Sets the value of the protocol property.
	 * 
	 * @param value
	 *            allowed object is {@link Protocol }
	 * 
	 */
	public void setProtocol(Protocol value) {
		this.protocol = value;
	}

	public boolean isSetProtocol() {
		return (this.protocol != null);
	}

	/**
	 * Gets the value of the canceled property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCanceled() {
		return canceled;
	}

	/**
	 * Sets the value of the canceled property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCanceled(String value) {
		this.canceled = value;
	}

	public boolean isSetCanceled() {
		return (this.canceled != null);
	}

	/**
	 * Gets the value of the comments property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the value of the comments property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComments(String value) {
		this.comments = value;
		setChanged();
		notifyObservers();
	}

	public boolean isSetComments() {
		return (this.comments != null);
	}

}
