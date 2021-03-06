//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.29 at 10:25:38 PM MESZ 
//

package org.revager.app.model.schema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * The protocol of a review session.
 * 
 * 
 * <p>
 * Java class for protocolType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="protocolType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="attendee" type="{http://www.informatik.uni-stuttgart.de/iste/se}attendeeReferenceType" maxOccurs="unbounded"/>
 *         &lt;element name="finding" type="{http://www.informatik.uni-stuttgart.de/iste/se}findingType" maxOccurs="unbounded"/>
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
@XmlType(name = "protocolType", propOrder = { "date", "start", "end", "location", "attendeeReferences", "findings",
		"comments" })
public class Protocol extends Observable {

	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter5.class)
	@XmlSchemaType(name = "date")
	protected Calendar date;
	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter6.class)
	@XmlSchemaType(name = "time")
	protected Calendar start;
	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter7.class)
	@XmlSchemaType(name = "time")
	protected Calendar end;
	@XmlElement(required = true)
	protected String location;
	@XmlElement(name = "attendee", required = true)
	protected List<AttendeeReference> attendeeReferences;
	@XmlElement(name = "finding", required = true)
	protected List<Finding> findings = new ArrayList<>();
	@XmlElement(required = true)
	protected String comments;

	/**
	 * Gets the value of the date property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Sets the value of the date property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDate(Calendar value) {
		this.date = value;
	}

	public boolean isSetDate() {
		return (this.date != null);
	}

	/**
	 * Gets the value of the start property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Calendar getStart() {
		return start;
	}

	/**
	 * Sets the value of the start property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStart(Calendar value) {
		this.start = value;
	}

	public boolean isSetStart() {
		return (this.start != null);
	}

	/**
	 * Gets the value of the end property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Calendar getEnd() {
		return end;
	}

	/**
	 * Sets the value of the end property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEnd(Calendar value) {
		this.end = value;
	}

	public boolean isSetEnd() {
		return (this.end != null);
	}

	/**
	 * Gets the value of the location property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the value of the location property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLocation(String value) {
		this.location = value;
	}

	public boolean isSetLocation() {
		return (this.location != null);
	}

	/**
	 * Gets the value of the attendeeReferences property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the attendeeReferences property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAttendeeReferences().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link AttendeeReference }
	 * 
	 * 
	 */
	public List<AttendeeReference> getAttendeeReferences() {
		if (attendeeReferences == null) {
			attendeeReferences = new ArrayList<AttendeeReference>();
		}
		return this.attendeeReferences;
	}

	public boolean isSetAttendeeReferences() {
		return ((this.attendeeReferences != null) && (!this.attendeeReferences.isEmpty()));
	}

	public void unsetAttendeeReferences() {
		this.attendeeReferences = null;
	}

	/**
	 * Gets the value of the findings property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the findings property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFindings().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Finding }
	 */
	public List<Finding> getFindings() {
		return this.findings;
	}

	public boolean isSetFindings() {
		return ((this.findings != null) && (!this.findings.isEmpty()));
	}

	public void unsetFindings() {
		this.findings = null;
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

	public boolean addFinding(Finding finding) {
		if (!findings.contains(finding)) {
			findings.add(finding);
			setChanged();
			notifyObservers(finding);
			return true;
		}
		return false;
	}
	
	public void removeFinding(Finding finding) {
		findings.remove(finding);
	}
	
}
