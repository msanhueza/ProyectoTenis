/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis.parseadorAtp;

/**
 *
 * @author msanhuezal
 */

/**
 * La clase permite modelar un jugador con algunos de sus atributos
 */
public class Jugador {

    private String nombre;
    private String pais;
    private int puntos;
    private String enlace;    
    
    /**
     * 
     * @param nombre se refiere al nombre del jugador (nombre + apellido)
     * @param pais se refiere al país de nacimiento del jugador
     * @param puntos se refiere a los puntos del ranking atp del jugador
     * @param enlace  se refiere al enlace web que contiene todos los partidos que el jugador ha enfrentado en una temporada
     */
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
