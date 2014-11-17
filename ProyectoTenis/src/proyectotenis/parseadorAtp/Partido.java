/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis.parseadorAtp;

/**
 *
 * @author msanhuezal
 */
public class Partido {

    private String campeonato;
    private String ano;
    private String superficie;
    private String ronda;
    private String oponente;
    private boolean ganador;    
    
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
