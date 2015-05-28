/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis;

import java.util.ArrayList;

/**
 *
 * @author msanhuezal
 */
public class Game {

    public ArrayList<Double> atributos;
    public String resultado;
    
    public Game(ArrayList<Double> atributos, String resultado) {
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
