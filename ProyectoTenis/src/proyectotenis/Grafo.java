/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author msanhuezal
 */
public class Grafo {
    
    int N;
    ArrayList<Encuentro>[][] matrizJugadores; //representan las victorias de los jugadores
    HashMap<String, Integer> jugadores = new HashMap<>();
    
    public Grafo(){
        N = 200; // los primeros N jugadores
        matrizJugadores = new ArrayList[N+1][N+1];
        jugadores = obtenerJugadores2012CSV(N);        
        for(int i=0; i<N+1; i++){
            for(int j=0; j<N+1; j++){
                ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
                matrizJugadores[i][j] = encuentros;
            }
        }
        generarGrafo();
        // novak (0) vs murray (2) se enfrentaron en 7 oportunidades
        int f = jugadores.get("Novak Djokovic");
        int c = jugadores.get("Andy Murray");
        System.out.println(matrizJugadores[f][c].size());
        System.out.println(matrizJugadores[c][f].size());
        
      
    }
    
    public HashMap<String, Integer> obtenerJugadores2012CSV(int cantidad){
        //archivo que se necesita parsear
        String fileToParse = "src/datos/jugadores2012.csv";
        BufferedReader fileReader = null;
        int contador = 0;
        HashMap<String, Integer> jugadores = new HashMap<>();
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            fileReader = new BufferedReader(new FileReader(fileToParse));
             
            //leemos el archivo linea por linea
            //line = fileReader.readLine(); //para que no empiece de la primera linea
            while ((line = fileReader.readLine()) != null) 
            {
                //consigue todos los token disponibles en linea
                String[] tokens = line.split(DELIMITER);
                String nombreJugador = tokens[0];
                jugadores.put(nombreJugador, contador);
                contador++;                
                if(contador >= cantidad){
                    break;
                }
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
        return jugadores;
    }
    
    public void generarGrafo(){
        //archivo que se necesita parsear
        String fileToParse = "src/datos/encuentros2012.csv";
        BufferedReader fileReader = null;
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            fileReader = new BufferedReader(new FileReader(fileToParse));
             
            //leemos el archivo linea por linea
            //line = fileReader.readLine(); //para que no empiece de la primera linea
            while ((line = fileReader.readLine()) != null) 
            {
                //consigue todos los token disponibles en linea
                String[] tokens = line.split(DELIMITER);
                if(tokens.length == 4){ // entonces es el jugador
                    String jugador1 = tokens[0];
                    if(jugadores.containsKey(jugador1)){
                        while ((line = fileReader.readLine()) != null){
                            String[] tokens2 = line.split(DELIMITER);
                            if(tokens2.length == 6){ // el detalle del encuentro
                                String jugador2 = tokens2[4];
                                if(jugadores.containsKey(jugador2)){
                                    String victoria = tokens2[5];
                                    if(victoria.equals("W")){
                                        String anio = tokens2[1];
                                        String superficie= tokens2[2];
                                        String ronda = tokens2[3];
                                        Encuentro encuentro = new Encuentro(anio, superficie, ronda);
                                        int fila = jugadores.get(jugador1);
                                        int columna = jugadores.get(jugador2);
                                        matrizJugadores[fila][columna].add(encuentro);
                                    }
                                }
                            }
                            else{
                                break;
                            }
                        }
                    }  
                }
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
    }
    
    
}



    



