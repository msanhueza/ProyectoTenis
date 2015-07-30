/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.prediccion;

/**
 *
 * @author msanhuezal
 * 
 * La clase contiene informacion sobre un encuentro de tenis
 */
public class InfoEncuentro {
    
    String anio; // temporada en la que se jugó el partido
    String superficie; // Hard, Clay, Grass
    String ronda; // W, S, Q, R16, R32, R64, R128  
    String oponente; // nombre del oponente
    String resultado; // resultado W victoria, L derrota

    public InfoEncuentro(String anio, String superficie, String ronda) {
        this.anio = anio;
        this.superficie = superficie;
        this.ronda = ronda;
    }
    
    public InfoEncuentro(String anio, String superficie, String ronda, String oponente, String resultado) {
        this.anio = anio;
        this.superficie = superficie;
        this.ronda = ronda;
        this.oponente = oponente;
        this.resultado = resultado;
    }    

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }    
    
    public String getOponente() {
        return oponente;
    }

    public void setOponente(String oponente) {
        this.oponente = oponente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }    
    
}
