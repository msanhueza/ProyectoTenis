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
    
    public static ArrayList<Game> listaPrueba; // lista partidos de prueba
    public static ArrayList<Game> listaEntrenamiento; // lista partidos de entrenamiento
    
    public ClasificadorKnn(String auxCsvPartidos, int k) throws IOException{
        String csvPardidos = auxCsvPartidos;
        listaJuegos = new ArrayList<>();
        distancias = new ArrayList<>();
        
        listaEntrenamiento = new ArrayList<>();
        listaPrueba = new ArrayList<>();
        
        cargarPartidos(csvPardidos);
        System.out.println(validacionCruzada(5, k));        
    }
    
    /**
     * Metodo que evalua los resultados del analisis estadistico
     * @param iteraciones cantidad de repeticiones para el calculo del valor que se quiere obtener
     * @param k vecinos mas cercanos a evaluar
     * @return el promedio de los elementos bien clasificados
     */
    public double validacionCruzada(int iteraciones, int k){
        double promedio = 0;
        for(int z=0; z<iteraciones; z++){
            Collections.shuffle(listaJuegos);
            int middle;
            int total = 1000;
            middle = (listaJuegos.size() % 2 == 0)? total / 2 : (total - 1) / 2;
            //agregar los valores al array train
            for(int i = 0; i<middle; i++){
                listaEntrenamiento.add(listaJuegos.get(i));
            }
            //agregar los valores al array test
            for(int i = middle; i<total; i++){
                listaPrueba.add(listaJuegos.get(i));
            }            
            int count = 0;
            for(int i = 0; i<listaPrueba.size(); i++){
                for(int j = 0; j<listaEntrenamiento.size(); j++){
                    double distance = calcularDistancia(listaPrueba.get(i), listaEntrenamiento.get(j) );
                    distancias.add(new ObjetivoDistancia(distance,  listaEntrenamiento.get(i).getResultado()));
                }
                String prediccion = calcularResultado(distancias, k);
                if(prediccion.equals(listaPrueba.get(i).getResultado())){
                    count++;
                }

            }
            double result = (double) count/listaPrueba.size() * 100;
            promedio += result;
            distancias.clear();        
            listaEntrenamiento.clear();
            listaPrueba.clear();
        }
        return promedio/iteraciones;
          
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
        
        return calcularResultado(distancias, k);

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

    /**
     * El metodo permite obtener el resultado del partido W victoria, L derrota
     * @param listaJuegos
     * @param k
     * @return 
     */
    private String calcularResultado(ArrayList<ObjetivoDistancia> d, int k) {
        //ordenar de Menor a Mayor
        Collections.sort(distancias);
        int cantidadVictorias, cantidadDerrotas;
        cantidadVictorias = 0;
        cantidadDerrotas = 0;
        String prediccion = "";
        for(int x=0; x<k; x++){
            if(distancias.get(x).getResultado().equals("W")){
                cantidadVictorias++;
            }
            else{
                cantidadDerrotas++;
            }
        }        
        if(cantidadVictorias > cantidadDerrotas){
            prediccion = "W";
        }
        else if(cantidadVictorias < cantidadDerrotas){
            prediccion = "L";
        }
        else{
            Random r = new Random();
            boolean gano = r.nextBoolean();
            String resultado = (gano == true)? "W" : "L";
            prediccion = resultado;
        } 
        return prediccion;
    }
    
}
