
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

    private final static QName _GetInfoForCountry_QNAME = new QName("http://countries.milan.jovic.dmi.uns.ac.rs/", "getInfoForCountry");
    private final static QName _GetInfoForCountryResponse_QNAME = new QName("http://countries.milan.jovic.dmi.uns.ac.rs/", "getInfoForCountryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servis
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetInfoForCountry }
     * 
     */
    public GetInfoForCountry createGetInfoForCountry() {
        return new GetInfoForCountry();
    }

    /**
     * Create an instance of {@link GetInfoForCountryResponse }
     * 
     */
    public GetInfoForCountryResponse createGetInfoForCountryResponse() {
        return new GetInfoForCountryResponse();
    }

    /**
     * Create an instance of {@link CountryDetailsPOJO }
     * 
     */
    public CountryDetailsPOJO createCountryDetailsPOJO() {
        return new CountryDetailsPOJO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoForCountry }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoForCountry }{@code >}
     */
    @XmlElementDecl(namespace = "http://countries.milan.jovic.dmi.uns.ac.rs/", name = "getInfoForCountry")
    public JAXBElement<GetInfoForCountry> createGetInfoForCountry(GetInfoForCountry value) {
        return new JAXBElement<GetInfoForCountry>(_GetInfoForCountry_QNAME, GetInfoForCountry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoForCountryResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoForCountryResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://countries.milan.jovic.dmi.uns.ac.rs/", name = "getInfoForCountryResponse")
    public JAXBElement<GetInfoForCountryResponse> createGetInfoForCountryResponse(GetInfoForCountryResponse value) {
        return new JAXBElement<GetInfoForCountryResponse>(_GetInfoForCountryResponse_QNAME, GetInfoForCountryResponse.class, null, value);
    }

}
