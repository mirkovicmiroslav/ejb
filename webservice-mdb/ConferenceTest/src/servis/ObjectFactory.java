
package servis;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servis package. 
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

    private final static QName _GetAllConference_QNAME = new QName("http://rzk/", "getAllConference");
    private final static QName _GetAllConferenceResponse_QNAME = new QName("http://rzk/", "getAllConferenceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servis
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllConference }
     * 
     */
    public GetAllConference createGetAllConference() {
        return new GetAllConference();
    }

    /**
     * Create an instance of {@link GetAllConferenceResponse }
     * 
     */
    public GetAllConferenceResponse createGetAllConferenceResponse() {
        return new GetAllConferenceResponse();
    }

    /**
     * Create an instance of {@link Conference }
     * 
     */
    public Conference createConference() {
        return new Conference();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllConference }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllConference }{@code >}
     */
    @XmlElementDecl(namespace = "http://rzk/", name = "getAllConference")
    public JAXBElement<GetAllConference> createGetAllConference(GetAllConference value) {
        return new JAXBElement<GetAllConference>(_GetAllConference_QNAME, GetAllConference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllConferenceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllConferenceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://rzk/", name = "getAllConferenceResponse")
    public JAXBElement<GetAllConferenceResponse> createGetAllConferenceResponse(GetAllConferenceResponse value) {
        return new JAXBElement<GetAllConferenceResponse>(_GetAllConferenceResponse_QNAME, GetAllConferenceResponse.class, null, value);
    }

}
