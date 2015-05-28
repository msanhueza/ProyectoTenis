/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * La clase implementa una serie de metodos necesarios para ejecutar el clasificador Knn
 * @author msanhuezal
 */
public class ClasificadorKnn {
    
    public static ArrayList<Game> listaJuegos; // lista de todos los juegos
    public static ArrayList<ObjetivoDistancia> distancias; // lista ObjectoDistancia que guarda la distancia y el resultado del partido
    
    public ClasificadorKnn(String auxCsvPartidos, int k) throws IOException{
        String csvPardidos = auxCsvPartidos;
        listaJuegos = new ArrayList<>();
        distancias = new ArrayList<>();
        
        cargarPartidos(csvPardidos);
        listaJuegos = disarray(listaJuegos);
        
        ArrayList<Double> aux1 = new ArrayList<>();
        for(int i=0; i<18; i++){
            aux1.add(1.0);
        }
        Game aux = new Game(aux1, "W");        
        clasificarPartido(aux, k);
        
    }

/**
    * El metodo se encarga de leer el archivo csv que contiene informacion de los partidos (atributos de desempeño de los 2 jugadores y resultado del encuentro)
    * @param csvPartidos ruta del archivo csv que contiene informacion de los partidos de tenis
    */ 
   public void cargarPartidos(String csvPartidos) throws FileNotFoundException, IOException{        
        String fileToParse = csvPartidos;
        BufferedReader fileReader = null;
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            fileReader = new BufferedReader(new FileReader(fileToParse));
            
            line = fileReader.readLine();
            //leemos el archivo linea por linea (omitiendo el encabezado)
            while ((line = fileReader.readLine()) != null) 
            { 
                //consigue todos los token disponibles en linea (atributos)
                String[] tokens = line.split(DELIMITER);
                int totalAtributos = tokens.length;
                String resultado = tokens[totalAtributos-1];
                ArrayList<Double> atributos = new ArrayList<>();
                for(int i=0; i<(totalAtributos-1); i++){
                    Double atrib = Double.parseDouble(tokens[i]);
                    atributos.add(atrib);
                }
                
                Game g = new Game(atributos, resultado);
                listaJuegos.add(g);

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
   
   /**
    * El metodo se encarga de desordenar todos los partidos que se encuentran en la listaJuegos
    * @param listaJuegos lista con todos los partidos a evaluar
    * @return lista con todos los partidos a evaluar de manera desordenada
    */
    private static ArrayList<Game> disarray(ArrayList<Game> listaJuegos) {
        int total = listaJuegos.size();
        Random r = new Random();
        Game auxGame;
        int index1 = r.nextInt(total);
        int index2 = r.nextInt(total);    
        for(int i=0; i<total; i++){
            do{
                index1 = r.nextInt(total);
                index2 = r.nextInt(total); 
            }while(index1 == index2);
            auxGame = listaJuegos.get(index1);
            listaJuegos.set(index1, listaJuegos.get(index2));
            listaJuegos.set(index2, auxGame);               
        }
        return listaJuegos;
    }

    /**
     * El metodo se encarga de clasificar un determinado partido
     * @param aux partido que se quiere clasificar
     * @param k parametro de cercania para determinar las clases mas cercanas
     * @return 
     */
    private String clasificarPartido(Game aux, int k) {
        int total = listaJuegos.size();
        for(int i=0; i<total; i++){
            double distancia = calcularDistancia(aux, listaJuegos.get(i));
            distancias.add(new ObjetivoDistancia(distancia, listaJuegos.get(i).getResultado()));
        }
        Collections.sort(distancias);
        
        int cantidadVictorias, cantidadDerrotas;
        cantidadVictorias = 0;
        cantidadDerrotas = 0;
        for(int i=0; i<k; i++){
            if(distancias.get(i).getResultado().equals("W")){
                cantidadVictorias++;
            }
            else{
                cantidadDerrotas++;
            }
        }        
        if(cantidadVictorias > cantidadDerrotas){
            System.out.println("V");
            return "V";
        }
        else if(cantidadVictorias < cantidadDerrotas){
            System.out.println("D");
            return "D";
        }
        else{
            Random r = new Random();
            boolean gano = r.nextBoolean();
            String resultado = (gano == true)? "V" : "D";
            System.out.println(resultado);
            return resultado;
        }
        
        
    }

    /**
     * El metodo se encarga de calcular la distancia euclideana entre todos los atributos de dos partidos
     * @param aux partido 1 
     * @param game partido 2
     * @return la distancia euclideana de todos los atributos de los partidos
     */
    private double calcularDistancia(Game aux, Game game) {
        int total = game.getAtributos().size();
        double resultado = 0;
        for(int i=0; i<total; i++){
            double diferencia = aux.getAtributos().get(i) - game.getAtributos().get(i);
            resultado += Math.pow(diferencia, 2);
        }
        return Math.sqrt(resultado);
    }
    
}
