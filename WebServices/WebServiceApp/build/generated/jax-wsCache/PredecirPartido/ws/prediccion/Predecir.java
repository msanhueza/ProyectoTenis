
package ws.prediccion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para predecir complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="predecir">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jugador1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jugador2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoSuperficie" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "predecir", propOrder = {
    "jugador1",
    "jugador2",
    "tipoSuperficie"
})
public class Predecir {

    protected String jugador1;
    protected String jugador2;
    protected int tipoSuperficie;

    /**
     * Obtiene el valor de la propiedad jugador1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJugador1() {
        return jugador1;
    }

    /**
     * Define el valor de la propiedad jugador1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJugador1(String value) {
        this.jugador1 = value;
    }

    /**
     * Obtiene el valor de la propiedad jugador2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJugador2() {
        return jugador2;
    }

    /**
     * Define el valor de la propiedad jugador2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJugador2(String value) {
        this.jugador2 = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoSuperficie.
     * 
     */
    public int getTipoSuperficie() {
        return tipoSuperficie;
    }

    /**
     * Define el valor de la propiedad tipoSuperficie.
     * 
     */
    public void setTipoSuperficie(int value) {
        this.tipoSuperficie = value;
    }

}
