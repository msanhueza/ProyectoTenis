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
 * 
 * La clase permite modelar un partido de tenis al que se ha enfrentado un determinado jugador
 */
public class Partido {

    private String campeonato;
    private String ano;
    private String superficie;
    private String ronda;
    private String oponente;
    private boolean ganador;    
    
    /**
     * 
     * @param campeonato se refiere al campeonato que disputo el jugador (nombre)
     * @param ano se refiere al año en que juego el partido el jugador
     * @param superficie se refiere a la superficie de la cancha en la que jugo el jugador
     * @param ronda se refiere a la ronda (semis, final, etc) correspondiente al partido que jugo
     * @param oponente se refiere al nombre del jugador oponente (nombre + apellido)
     * @param ganador se refiere al resultado final del jugador con su oponente (true/false)
     */
    public Partido(String campeonato, String ano, String superficie, String ronda, String oponente, boolean ganador) {
        this.campeonato = campeonato;
        this.ano = ano;
        this.superficie = superficie;
        this.ronda = ronda;
        this.oponente = oponente;
        this.ganador = ganador;
    }

    public String getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(String campeonato) {
        this.campeonato = campeonato;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
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

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }
    

    
}
