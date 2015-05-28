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
public class ObjetivoDistancia implements Comparable<ObjetivoDistancia>{

    private double distance;
    private String resultado;
    
    public ObjetivoDistancia(double distance, String resultado) {
        this.distance = distance;
        this.resultado = resultado;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistancia(double distance) {
        this.distance = distance;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado= resultado;
    }    
    /**
     * Si la distancia es igual a la del argumento retorna 0
     * Si la distancia es menor que la del argumento retorna -1
     * Si la distancia es mas grande que la del argumento retorna 1
     * @param obj objeto a comparar
     * @return -1, 0, 1
     */
    @Override
    public int compareTo(ObjetivoDistancia obj) {
        
        if(this.distance > obj.getDistance()){
            return 1;
        }
        else if(this.distance == obj.getDistance()){
            return 0;
        }
        else{
            return -1;
        }
    }
    
}

