
package ws.prediccion;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InterruptedException", targetNamespace = "http://prediccion.ws/")
public class InterruptedException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InterruptedException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InterruptedException_Exception(String message, InterruptedException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InterruptedException_Exception(String message, InterruptedException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ws.prediccion.InterruptedException
     */
    public InterruptedException getFaultInfo() {
        return faultInfo;
    }

}
