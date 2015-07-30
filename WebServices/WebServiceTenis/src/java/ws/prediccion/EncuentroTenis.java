/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.prediccion;

import java.util.ArrayList;

/**
 *
 * @author msanhuezal
 * 
 * La clase modela un encuentro de tenis
 */
public class EncuentroTenis {

    public ArrayList<Double> atributos; // atributos referentes a información del desempeño de los dos jugadores a evaluar
    public String resultado; // resultado del encuentro entre los dos jugadores a evaluar
    
    public EncuentroTenis(ArrayList<Double> atributos, String resultado) {
        this.atributos = atributos;
        this.resultado = resultado;
    }
    
    public ArrayList<Double> getAtributos() {
        return atributos;
    }

    public void setAtributos(ArrayList<Double> atributos) {
        this.atributos = atributos;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }    
    
}
