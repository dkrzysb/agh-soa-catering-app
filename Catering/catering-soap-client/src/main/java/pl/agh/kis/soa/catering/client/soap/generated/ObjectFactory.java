
package pl.agh.kis.soa.catering.client.soap.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.kis.soa.catering.client.soap.generated package. 
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

    private final static QName _AddMenuItemToCategory_QNAME = new QName("http://interfaces.soap.server.soa.kis.agh.pl/", "addMenuItemToCategory");
    private final static QName _AddMenuItemToCategoryResponse_QNAME = new QName("http://interfaces.soap.server.soa.kis.agh.pl/", "addMenuItemToCategoryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.kis.soa.catering.client.soap.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddMenuItemToCategory }
     * 
     */
    public AddMenuItemToCategory createAddMenuItemToCategory() {
        return new AddMenuItemToCategory();
    }

    /**
     * Create an instance of {@link AddMenuItemToCategoryResponse }
     * 
     */
    public AddMenuItemToCategoryResponse createAddMenuItemToCategoryResponse() {
        return new AddMenuItemToCategoryResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMenuItemToCategory }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddMenuItemToCategory }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.server.soa.kis.agh.pl/", name = "addMenuItemToCategory")
    public JAXBElement<AddMenuItemToCategory> createAddMenuItemToCategory(AddMenuItemToCategory value) {
        return new JAXBElement<AddMenuItemToCategory>(_AddMenuItemToCategory_QNAME, AddMenuItemToCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMenuItemToCategoryResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddMenuItemToCategoryResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.server.soa.kis.agh.pl/", name = "addMenuItemToCategoryResponse")
    public JAXBElement<AddMenuItemToCategoryResponse> createAddMenuItemToCategoryResponse(AddMenuItemToCategoryResponse value) {
        return new JAXBElement<AddMenuItemToCategoryResponse>(_AddMenuItemToCategoryResponse_QNAME, AddMenuItemToCategoryResponse.class, null, value);
    }

}
