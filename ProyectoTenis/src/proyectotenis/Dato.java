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
public class Dato {
    public static HashMap<String, Jugador> desempeno;
    public static ArrayList<String> jugadores;
    public static HashMap<String, ArrayList<Encuentro>> encuentros;
    
    public static void main(String[] args) {
        int N = 200;
        String superficie = "All";
        desempeno = new HashMap<>();
        jugadores = new ArrayList<>();
        encuentros = new HashMap<>();
        desempeno = leerDesempenoJugadores("src/datos/desempeno2013.csv"); // se carga el desempeño y la lista de jugadores TOP 200 del 2012
        encuentros = leerEncuentrosJugadores(superficie);
        crearFormatoSalida(superficie);
        //debiera actualizar la lista de jugadores que tienen partidos jugados
        for(int i=0; i<jugadores.size(); i++){
            if(encuentros.get(jugadores.get(i)) == null){
                jugadores.remove(jugadores.get(i));
            }
        }
        System.out.println("cant " + jugadores.size());
    }   
    
    public static int calcularVictorias(ArrayList<Encuentro> encuentros){
        int tamano = encuentros.size();
        int contador = 0;
        for(int i=0; i<tamano; i++){
            if(encuentros.get(i).getResultado().equals("W")){
                contador++;
            }
        }
        return contador;
    }
    
    public static int calcularDerrotas(ArrayList<Encuentro> encuentros){
        int tamano = encuentros.size();  
        int contador = 0;
        for(int i=0; i<tamano; i++){
            if(encuentros.get(i).getResultado().equals("L")){
                contador++;
            }            
        }        
        return contador;
    }    
    
    public static double calcularRacha(ArrayList<Encuentro> encuentros){
          int tamano = encuentros.size();
          int contador = 0;
          if(tamano>=6){
                for(int i=0; i<6; i++){
                    String resultado = encuentros.get(i).getResultado();
                    if(resultado.equals("W")){
                        contador++;
                    }
                }              
          }
          else{
                for(int i=0; i<tamano; i++){
                    String resultado = encuentros.get(i).getResultado();
                    if(resultado.equals("W")){
                        contador++;
                    }
                }                              
          }

          double denominador = 6;
          double calculo = contador / denominador;
          calculo = Math.round(calculo * 100.0) / 100.0;
          //System.out.println(calculo);
          return calculo;
          
//        int resultado = 0;
//        int tamano = encuentros.size();
//        String objetivo = encuentros.get(0).resultado;
//        System.out.println("entro " + objetivo);
//        int contador = 1;
//        for(int i=0; i<tamano; i++){
//            Encuentro e = encuentros.get(i);
//            if(e.getResultado().equals(objetivo)){
//                if(objetivo.equals("W")){
//                    resultado++;
//                }
//                else{
//                    resultado--;
//                }
//                contador++;
//            }
//            else{
//                return resultado;
//            }
//            
//            if(contador == 6){
//                return resultado;
//            }
//            contador++;
//        }
//        return resultado;
//        return 3;
    }
    
    public static ArrayList<String> obtenerJugadores2012CSV(int cantidad, String ruta){
        //archivo que se necesita parsear
        String fileToParse = ruta;
        BufferedReader fileReader = null;
        int contador = 0;
        ArrayList<String> jugadores = new ArrayList<>();
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            fileReader = new BufferedReader(new FileReader(fileToParse));
             
            //leemos el archivo linea por linea
            line = fileReader.readLine(); //para que no empiece de la primera linea
            while ((line = fileReader.readLine()) != null) 
            {
                //consigue todos los token disponibles en linea
                String[] tokens = line.split(DELIMITER);
                String nombreJugador = tokens[1];
                jugadores.add(nombreJugador);
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

    private static HashMap<String, Jugador> leerDesempenoJugadores(String ruta) {
        HashMap<String, Jugador> desempeno = new HashMap<String, Jugador>();
        String fileToParse = ruta;
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
            line = fileReader.readLine(); //para que no empiece de la primera linea
            while ((line = fileReader.readLine()) != null) 
            { 
                //consigue todos los token disponibles en linea
                String[] tokens = line.split(DELIMITER);
                String atributo1 = tokens[0];
                String atributo2 = tokens[1];
                String atributo3 = tokens[2];
                String atributo4 = tokens[3];
                String atributo5 = tokens[4];
                String atributo6 = tokens[5];
                String atributo7 = tokens[6];
                String atributo8 = tokens[7];
                String atributo9 = tokens[8];
                String atributo10 = tokens[9];
                String atributo11 = tokens[10];
                String atributo12 = tokens[11];
                String atributo13 = tokens[12];
                String atributo14 = tokens[13];
                String atributo15 = tokens[14];
                String atributo16 = tokens[15];
                String atributo17 = tokens[16];
                String atributo18 = tokens[17];
                String atributo19 = tokens[18];
                String atributo20 = tokens[19];
                //System.out.println(atributo2);
                Jugador j = new Jugador(atributo1, atributo2, atributo3, atributo4, atributo5, atributo6, atributo7, atributo8, atributo9, atributo10, atributo11, atributo12, atributo13, atributo14, atributo15, atributo16, atributo17, atributo18, atributo19, atributo20);
                jugadores.add(atributo2);
                desempeno.put(atributo2, j);
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
        return desempeno;
    }

    private static HashMap<String, ArrayList<Encuentro>> leerEncuentrosJugadores(String auxSupercifie) {

        HashMap<String, ArrayList<Encuentro>> encuentros = new HashMap<String, ArrayList<Encuentro>>();
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
                    if(jugadores.contains(jugador1)){
                        ArrayList<Encuentro> partidos = new ArrayList<>();
                        encuentros.put(jugador1, partidos);
                        while ((line = fileReader.readLine()) != null){
                            String[] tokens2 = line.split(DELIMITER);
                            if(tokens2.length == 6){ // el detalle del encuentro
                                String jugador2 = tokens2[4];
                                if(jugadores.contains(jugador2)){
                                    
                                        
                                        String anio = tokens2[1];
                                        String superficie= tokens2[2];
                                        String ronda = tokens2[3];
                                        String oponente = tokens2[4];
                                        String resultado = tokens2[5];
                                        if(superficie.equals(auxSupercifie) || auxSupercifie.equals("All")){
                                           Encuentro e = new Encuentro(anio, superficie, ronda, oponente, resultado);
                                           encuentros.get(jugador1).add(e);
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
        return encuentros;
    }

    private static String formatoJugador(String jugador){
        String formato = "";
        Jugador j = desempeno.get(jugador);
        int atr1 = Integer.parseInt(j.getAtributo1());
        ArrayList<Encuentro> encuentrosJug = new ArrayList<>();
        encuentrosJug = encuentros.get(jugador);       
        formato =  agruparRanking(atr1) + ";" + calcularVictorias(encuentrosJug) + ";" + calcularDerrotas(encuentrosJug) + ";" +j.getAtributo8() + ";" 
                +j.getAtributo13() + ";" +j.getAtributo15() + ";" +j.getAtributo16() + ";" +j.getAtributo20() + ";" +
                calcularRacha(encuentrosJug);
        return formato;
    }
    
    
    
    private static void crearFormatoSalida(String auxSuperficie) {
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
                    if(jugadores.contains(jugador1)){
                        while ((line = fileReader.readLine()) != null){
                            String[] tokens2 = line.split(DELIMITER);
                            if(tokens2.length == 6){ // el detalle del encuentro
                                String jugador2 = tokens2[4];
                                if(jugadores.contains(jugador2)){
                                    String resultado = tokens2[5];
                                    String superficie = tokens2[2];
                                    if(encuentros.get(jugador1) != null && encuentros.get(jugador2) != null){
                                        if(superficie.equals(auxSuperficie) || auxSuperficie.equals("All")){
                                            System.out.println(formatoJugador(jugador1) + ";" +
                                                           formatoJugador(jugador2) + ";"+ 
                                                           resultado);                                             
                                        }
   
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
    
    private static String agruparRanking(int rank){
        String resultado = "";
        if(rank >=1 && rank <=10){
            resultado = "1";
        }
        else if (rank >= 11 && rank <= 25){
            resultado = "2";
        }
        else if (rank >= 26 && rank <= 50){
            resultado = "3";
        }
        else if (rank >= 51 && rank <= 100){
            resultado = "4";
        }
        else{
            resultado = "5";
        }
        return resultado;
    }
    
    private static String agruparRacha(int racha){
        String resultado = "";
        if(racha >= -6 && racha <= -4){
            resultado = "PESIMA";
        }
        else if (racha >= -3 && racha <= 0){
            resultado = "MALA";
        }
        else if (racha >= 1 && racha <= 3){
            resultado = "BUENA";
        }
        else{
            resultado = "EXCELENTE";
        }
        return resultado;
    }    
    
}
