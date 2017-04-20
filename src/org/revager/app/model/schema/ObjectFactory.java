//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.29 at 10:25:38 PM MESZ 
//


package org.revager.app.model.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.revager.app.model.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Review_QNAME = new QName("http://www.informatik.uni-stuttgart.de/iste/se", "review");
    private final static QName _Aspects_QNAME = new QName("http://www.informatik.uni-stuttgart.de/iste/se", "aspects");
    private final static QName _Catalog_QNAME = new QName("http://www.informatik.uni-stuttgart.de/iste/se", "catalog");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.revager.app.model.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Meeting }
     * 
     */
    public Meeting createMeeting() {
        return new Meeting();
    }

    /**
     * Create an instance of {@link Finding }
     * 
     */
    public Finding createFinding() {
        return new Finding();
    }

    /**
     * Create an instance of {@link Review }
     * 
     */
    public Review createReview() {
        return new Review();
    }

    /**
     * Create an instance of {@link Severities }
     * 
     */
    public Severities createSeverities() {
        return new Severities();
    }

    /**
     * Create an instance of {@link AspectsIds }
     * 
     */
    public AspectsIds createAspectsIds() {
        return new AspectsIds();
    }

    /**
     * Create an instance of {@link Attendee }
     * 
     */
    public Attendee createAttendee() {
        return new Attendee();
    }

    /**
     * Create an instance of {@link AttendeeReference }
     * 
     */
    public AttendeeReference createAttendeeReference() {
        return new AttendeeReference();
    }

    /**
     * Create an instance of {@link Aspects }
     * 
     */
    public Aspects createAspects() {
        return new Aspects();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link Catalog }
     * 
     */
    public Catalog createCatalog() {
        return new Catalog();
    }

    /**
     * Create an instance of {@link Aspect }
     * 
     */
    public Aspect createAspect() {
        return new Aspect();
    }

    /**
     * Create an instance of {@link Protocol }
     * 
     */
    public Protocol createProtocol() {
        return new Protocol();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Review }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.informatik.uni-stuttgart.de/iste/se", name = "review")
    public JAXBElement<Review> createReview(Review value) {
        return new JAXBElement<Review>(_Review_QNAME, Review.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Aspects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.informatik.uni-stuttgart.de/iste/se", name = "aspects")
    public JAXBElement<Aspects> createAspects(Aspects value) {
        return new JAXBElement<Aspects>(_Aspects_QNAME, Aspects.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Catalog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.informatik.uni-stuttgart.de/iste/se", name = "catalog")
    public JAXBElement<Catalog> createCatalog(Catalog value) {
        return new JAXBElement<Catalog>(_Catalog_QNAME, Catalog.class, null, value);
    }

}
