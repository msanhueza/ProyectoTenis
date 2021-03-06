
package ws.prediccion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PredecirPartido", targetNamespace = "http://prediccion.ws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PredecirPartido {


    /**
     * 
     * @param tipoSuperficie
     * @param jugador1
     * @param jugador2
     * @return
     *     returns java.lang.String
     * @throws InterruptedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "predecir", targetNamespace = "http://prediccion.ws/", className = "ws.prediccion.Predecir")
    @ResponseWrapper(localName = "predecirResponse", targetNamespace = "http://prediccion.ws/", className = "ws.prediccion.PredecirResponse")
    @Action(input = "http://prediccion.ws/PredecirPartido/predecirRequest", output = "http://prediccion.ws/PredecirPartido/predecirResponse", fault = {
        @FaultAction(className = InterruptedException_Exception.class, value = "http://prediccion.ws/PredecirPartido/predecir/Fault/InterruptedException")
    })
    public String predecir(
        @WebParam(name = "jugador1", targetNamespace = "")
        String jugador1,
        @WebParam(name = "jugador2", targetNamespace = "")
        String jugador2,
        @WebParam(name = "tipoSuperficie", targetNamespace = "")
        int tipoSuperficie)
        throws InterruptedException_Exception
    ;

}
