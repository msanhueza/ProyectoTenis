
package ws.prediccion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.prediccion package. 
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

    private final static QName _PredecirResponse_QNAME = new QName("http://prediccion.ws/", "predecirResponse");
    private final static QName _Predecir_QNAME = new QName("http://prediccion.ws/", "predecir");
    private final static QName _InterruptedException_QNAME = new QName("http://prediccion.ws/", "InterruptedException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.prediccion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Predecir }
     * 
     */
    public Predecir createPredecir() {
        return new Predecir();
    }

    /**
     * Create an instance of {@link InterruptedException }
     * 
     */
    public InterruptedException createInterruptedException() {
        return new InterruptedException();
    }

    /**
     * Create an instance of {@link PredecirResponse }
     * 
     */
    public PredecirResponse createPredecirResponse() {
        return new PredecirResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PredecirResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://prediccion.ws/", name = "predecirResponse")
    public JAXBElement<PredecirResponse> createPredecirResponse(PredecirResponse value) {
        return new JAXBElement<PredecirResponse>(_PredecirResponse_QNAME, PredecirResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Predecir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://prediccion.ws/", name = "predecir")
    public JAXBElement<Predecir> createPredecir(Predecir value) {
        return new JAXBElement<Predecir>(_Predecir_QNAME, Predecir.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterruptedException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://prediccion.ws/", name = "InterruptedException")
    public JAXBElement<InterruptedException> createInterruptedException(InterruptedException value) {
        return new JAXBElement<InterruptedException>(_InterruptedException_QNAME, InterruptedException.class, null, value);
    }

}
