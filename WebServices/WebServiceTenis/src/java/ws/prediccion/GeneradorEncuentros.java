/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.prediccion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author msanhuezal
 */
public class GeneradorEncuentros {
    public static HashMap<String, InfoDesempeno> desempeno;
    public static ArrayList<String> jugadores;
    public static HashMap<String, ArrayList<InfoEncuentro>> encuentros;
    public static int acesMin, acesMax;
    
    public GeneradorEncuentros (String auxSuperficie, String csvDesempeno, String csvEncuentros) {
        acesMin = Integer.MAX_VALUE;
        acesMax = Integer.MIN_VALUE;
        String superficie = auxSuperficie;
        String desempenoCsv = csvDesempeno;
        String encuentrosCsv = csvEncuentros;
        desempeno = new HashMap<>();
        jugadores = new ArrayList<>();
        encuentros = new HashMap<>();
        desempeno = leerDesempenoJugadores(desempenoCsv); // se carga el desempeño y la lista de jugadores TOP 200 del 2012
        encuentros = leerEncuentrosJugadores(superficie, encuentrosCsv);
    }   

    /**
     * El metodo se encarga de determinar la cantidad de victorias presentes en un conjunto de encuentros
     * @param encuentros se refiere al conjunto de datos que contiene los encuentros
     * @return la cantidad de victorias
     */    
    public int calcularVictorias(ArrayList<InfoEncuentro> encuentros){
        int tamano = encuentros.size();
        int contador = 0;
        for(int i=0; i<tamano; i++){
            if(encuentros.get(i).getResultado().equals("W")){
                contador++;
            }
        }
        return contador;
    }
    
    /**
     * * El metodo se encarga de determinar la cantidad de derrotas presentes en un conjunto de encuentros
     * @param encuentros se refiere al conjunto de datos que contiene los encuentros
     * @return la cantidad de derrotas
     */    
    public int calcularDerrotas(ArrayList<InfoEncuentro> encuentros){
        int tamano = encuentros.size();  
        int contador = 0;
        for(int i=0; i<tamano; i++){
            if(encuentros.get(i).getResultado().equals("L")){
                contador++;
            }            
        }        
        return contador;
    }    
    
    
    /**
     * El metodo se encarga de calcular la racha no consecutiva normalizada
     * @param encuentros se refiere al conjunto de datos que contiene los encuentros
     * @return el valor normalizado de la racha no consecutiva
     */    
    public double calcularRacha(ArrayList<InfoEncuentro> encuentros){
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
          calculo = Math.round(calculo * 100.0) / 100.0; // para redondear a dos decimales
          //System.out.println(calculo);
          return calculo;
          
    }

    
    /**
     * El metodo se encarga de leer un archivo csv que contiene informacion referente al desempeño de jugadores
     * @param ruta se refiere a la ruta del archivo a leer
     * @return un HashMap con el nombre del jugador e informacion sobre su desempeño
     */    
    public HashMap<String, InfoDesempeno> leerDesempenoJugadores(String ruta) {
        HashMap<String, InfoDesempeno> desempeno = new HashMap<String, InfoDesempeno>();
        String fileToParse = ruta;
        BufferedReader fileReader = null;
        int contador = 0;
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            URL stockURL = new URL(fileToParse);
            fileReader = new BufferedReader(new InputStreamReader(stockURL.openStream()));
             
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
                String atributo8 = tokens[7]; //total aces
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
                InfoDesempeno j = new InfoDesempeno(atributo1, atributo2, atributo3, atributo4, atributo5, atributo6, atributo7, atributo8, atributo9, atributo10, atributo11, atributo12, atributo13, atributo14, atributo15, atributo16, atributo17, atributo18, atributo19, atributo20);
                jugadores.add(atributo2);
                //calculo el menor y mayor valor de aces para posteriormente normalizar
                int totalAces = Integer.parseInt(j.getAtributo8());
                if(totalAces < acesMin){
                    acesMin = totalAces;
                }
                if(totalAces > acesMax){
                    acesMax = totalAces;
                }
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

    
    /**
     * El metodo se encarga de leer los encuentros de tenis para una determinada superficie
     * @param auxSupercifie se refiere al tipo de superficie de la cancha
     * @param partidosCsv se refiere a la ubicacion del archivo a leer
     * @return 
     */    
    public HashMap<String, ArrayList<InfoEncuentro>> leerEncuentrosJugadores(String auxSupercifie, String partidosCsv) {

        HashMap<String, ArrayList<InfoEncuentro>> encuentros = new HashMap<String, ArrayList<InfoEncuentro>>();
        String fileToParse = partidosCsv;
        BufferedReader fileReader = null;
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            URL stockURL = new URL(fileToParse);
            fileReader = new BufferedReader(new InputStreamReader(stockURL.openStream()));
             
            //leemos el archivo linea por linea
            //line = fileReader.readLine(); //para que no empiece de la primera linea
            while ((line = fileReader.readLine()) != null) 
            {
                //consigue todos los token disponibles en linea
                String[] tokens = line.split(DELIMITER);
                if(tokens.length == 4){ // entonces es el jugador
                    String jugador1 = tokens[0];
                    if(jugadores.contains(jugador1)){
                        ArrayList<InfoEncuentro> partidos = new ArrayList<>();
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
                                           InfoEncuentro e = new InfoEncuentro(anio, superficie, ronda, oponente, resultado);
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

    
    /**
     * El metodo se encarga de transformar los datos de un determinado jugador en formato csv
     * @param jugador nombre del jugador evaluado
     * @return atributos de un jugador en formato csv
     */    
    public String formatoJugador(String jugador){           
        String formato = "";
        InfoDesempeno j = desempeno.get(jugador);
        int atr1 = Integer.parseInt(j.getAtributo1());
        ArrayList<InfoEncuentro> encuentrosJug = new ArrayList<>();
        encuentrosJug = encuentros.get(jugador); 
        int totalVictorias = calcularVictorias(encuentrosJug);
        int totalDerrotas = calcularDerrotas(encuentrosJug);
        double porcVictorias = (double) totalVictorias/(totalVictorias+totalDerrotas);
        porcVictorias = (Math.round(porcVictorias * 100.0) / 100.0); //redondeado a dos decimales
        int totalAces = Integer.parseInt(j.getAtributo8());
        int acesNum = totalAces-acesMin;
        int acesDen = acesMax-acesMin;
        double acesNorm = (double) acesNum / acesDen;
        acesNorm = (Math.round(acesNorm * 100.0) / 100.0);
        formato =  agruparRanking(atr1) + ";" + porcVictorias + ";" + acesNorm + ";" 
                +j.getAtributo13() + ";" +j.getAtributo15() + ";" +j.getAtributo16() + ";" +j.getAtributo20() + ";" +
                calcularRacha(encuentrosJug);
        return formato;
    }
    
    
    /**
     * El metodo se encarga de transformar los encuentros al formato csv requerido
     * @param auxSuperficie se refiere al tipo de superficie de la cancha
     */    
    public EncuentroTenis crearFormatoSalida(String jugador1, String jugador2) {
        ArrayList<Double> atributos = new ArrayList<>();
        String resultado = "W";
        String aux = "";
        EncuentroTenis g;
        if(jugadores.contains(jugador1) && jugadores.contains(jugador2)){
            aux = formatoJugador(jugador1) + ";" + formatoJugador(jugador2) + ";" + "W";
            String[] tokens = aux.split(";");
            for(int i=0; i<tokens.length-1; i++){
                String elem = tokens[i];
                Double d = Double.parseDouble(elem);
                atributos.add(d);
            }
            g = new EncuentroTenis(atributos, resultado);
            return g;            
        }
        return null;

    }
    
    /**
     * El metodo se encarga de normalizar el ranking de un jugador
     * @param rank se refiere al ranking de un jugador
     * @return el ranking normalizado de un jugador
     */    
    public String agruparRanking(int rank){
        String resultado = "";
        double r;
        if(rank >=1 && rank <=10){
            r = (double) 1/5;
        }
        else if (rank >= 11 && rank <= 25){
            r = (double) 2/5;
        }
        else if (rank >= 26 && rank <= 50){
            r = (double) 3/5;
        }
        else if (rank >= 51 && rank <= 100){
            r = (double) 4/5;            
        }
        else{
            r = (double) 5/5;
        }
        resultado = (Math.round(r * 100.0) / 100.0)+""; // para redondear a dos decimales
        return resultado;
    } 
    
}