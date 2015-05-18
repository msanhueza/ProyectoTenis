/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import static proyectotenis.Dato.jugadores;

/**
 *
 * @author msanhuezal
 */
public class ProyectoTenis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cargarPartidos();
    }
    
    public static ArrayList<Match> cargarPartidos(){
        ArrayList<Match> partidos = new ArrayList<>();
        String fileToParse = "src/datos/partidos.csv";
        BufferedReader fileReader = null;
        int contador = 0;
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            fileReader = new BufferedReader(new FileReader(fileToParse));
             
            //leemos el archivo linea por linea
            while ((line = fileReader.readLine()) != null) 
            { 
                //consigue todos los token disponibles en linea
                String[] tokens = line.split(DELIMITER);
                int atributoLocal1      = Integer.parseInt(tokens[0]); //posicion ranking mundial
                int atributoLocal2      = Integer.parseInt(tokens[1]); //partidos ganados
                int atributoLocal3      = Integer.parseInt(tokens[2]); //partidos perdidos
                int atributoLocal4      = Integer.parseInt(tokens[3]); //total aces
                double atributoLocal5   = Double.parseDouble(tokens[4]); //porcentaje de primeros servicios ganados
                double atributoLocal6   = Double.parseDouble(tokens[5]); //porcentaje de juegos ganados con su servicio
                double atributoLocal7   = Double.parseDouble(tokens[6]); //Porcentaje de break-point ganados
                double atributoLocal8   = Double.parseDouble(tokens[7]); //Porcentaje de juegos ganados con devolución
                double atributoLocal9   = Double.parseDouble(tokens[8]); //Racha no consecutiva
                int atributoVisita1     = Integer.parseInt(tokens[9]); //posicion ranking mundial
                int atributoVisita2     = Integer.parseInt(tokens[10]); //partidos ganados
                int atributoVisita3     = Integer.parseInt(tokens[11]); //partidos perdidos
                int atributoVisita4     = Integer.parseInt(tokens[12]); //total aces
                double atributoVisita5  = Double.parseDouble(tokens[13]); //porcentaje de primeros servicios ganados
                double atributoVisita6  = Double.parseDouble(tokens[14]); //porcentaje de juegos ganados con su servicio
                double atributoVisita7  = Double.parseDouble(tokens[15]); //Porcentaje de break-point ganados
                double atributoVisita8  = Double.parseDouble(tokens[16]); //Porcentaje de juegos ganados con devolución
                double atributoVisita9  = Double.parseDouble(tokens[17]); //Racha no consecutiva
                String resultado        = tokens[18]; //resultado        
                
                Match match = new Match(atributoLocal1, atributoLocal2, atributoLocal3, atributoLocal4, atributoLocal5, atributoLocal6, atributoLocal7, atributoLocal8, atributoLocal9, atributoVisita1, atributoVisita2, atributoVisita3, atributoVisita4, atributoVisita5, atributoVisita6, atributoVisita7, atributoVisita8, atributoVisita9, resultado);
                partidos.add(match);

            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   
        return partidos;
    }
}
