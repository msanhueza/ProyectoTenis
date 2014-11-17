/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis.parseadorAtp;

/**
 *
 * @author msanhuezal
 */
public class Jugador {

    private String nombre;
    private String pais;
    private int puntos;
    private String enlace;    
    
    public Jugador(String nombre, String pais, int puntos, String enlace) {
        this.nombre = nombre;
        this.pais = pais;
        this.puntos = puntos;
        this.enlace = enlace;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
    
    public void imprimir(){
        System.out.println(this.getNombre() + " " + this.getPais() + " " + this.getPuntos() + " " + this.getEnlace());
    }
    

    
}
