/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis;

/**
 *
 * @author msanhuezal
 */
public class Encuentro {
    
    String anio;
    String superficie; // Hard, Clay, Grass
    String ronda; // W, S, Q, R16, R32, R64, R128  

    public Encuentro(String anio, String superficie, String ronda) {
        this.anio = anio;
        this.superficie = superficie;
        this.ronda = ronda;
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
    
}
