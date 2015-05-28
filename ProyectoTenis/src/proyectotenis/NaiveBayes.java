/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis;

import java.io.IOException;

/**
 *
 * @author msanhuezal
 */
public class NaiveBayes {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String csvPartidos = "src/datos/partidos.csv";
        ClasificadorNaiveBayes clasificadorNB = new ClasificadorNaiveBayes(csvPartidos);
        ClasificadorKnn clasificadorKnn = new ClasificadorKnn(csvPartidos, 12);
    }    
    
}
