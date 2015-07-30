/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.prediccion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * La clase implementa una serie de metodos necesarios para ejecutar el clasificador Naive Bayes
 * @author msanhuezal
 */
public class ClasificadorNaiveBayes {

    public static int cantidadAtributos;
    public static int cantidadPartidos;
    
    public static double probVictoria, probDerrota; // probabiliad de ser Victoria o Derrota
    public static ArrayList<ArrayList<Double>> victorias; // arrayList con los atributos de victorias
    public static ArrayList<ArrayList<Double>> derrotas; // arrayList con los atributos de derrotas
    public static ArrayList<EncuentroTenis> juegos; // arrayList que contiene todos los enfrentamientos (atributos y clase)
    
    public static ArrayList<Double> mediaVictorias; // ArrayList que contiene las medias de cada uno de los atributos de partidos ganados
    public static ArrayList<Double> varianzaVictorias; //ArrayList que contiene las varianzas de cada uno de los atributos de partidos ganados
    
    public static ArrayList<Double> mediaDerrotas; // ArrayList que contiene las medias de cada uno de los atributos de partidos perdidos
    public static ArrayList<Double> varianzaDerrotas; //ArrayList que contiene las varianzas de cada uno de los atributos de partidos perdidos   
    
    public static ArrayList<EncuentroTenis> test; // lista partidos de prueba
    
    public ClasificadorNaiveBayes(String csvPartidos) {
        cantidadAtributos = 0;
        cantidadPartidos = 0;
        
        probVictoria      = 0.5;
        probDerrota       = 0.5;        
        
        victorias = new ArrayList<>();
        derrotas = new ArrayList<>();
        juegos = new ArrayList<>();
        
        mediaVictorias = new ArrayList<>();
        varianzaVictorias = new ArrayList<>();
        mediaDerrotas = new ArrayList<>();
        varianzaDerrotas = new ArrayList<>();
        
        test = new ArrayList<>();        
        
        cargarPartidos(csvPartidos);
        calcularMediasVarianzas();
        //System.out.println(validacionCruzada(10));
       
    }
    
    /**
     * Metodo que evalua los resultados del analisis estadistico
     * @param iteraciones cantidad de repeticiones para el calculo del valor que se quiere obtener
     * @return el promedio de los elementos bien clasificados
     */    
    public double validacionCruzada(int iteraciones){
        
        int middle;
        middle = (juegos.size() % 2 == 0)? juegos.size() / 2 : (juegos.size() - 1) / 2;
        //agregar los valores al array test    
        double promedio = 0;
        for(int j=0; j<iteraciones; j++){
            Collections.shuffle(juegos);
            for(int i = middle; i<juegos.size(); i++){
                test.add(juegos.get(i));
            }                
            double result;
            int count = 0;            
            for(int i = 0; i<test.size(); i++){
                String resultado = clasificarPartido(test.get(i));
                if(resultado.equals(test.get(i).getResultado())){
                    count++;
                }            
            }
            result = (double) count/test.size() * 100;
            promedio += result;
            test.clear();
        }
        return (double) promedio/iteraciones;
    }
    
   /**
    * El metodo se encarga de leer el archivo csv que contiene informacion de los partidos (atributos de desempeño de los 2 jugadores y resultado del encuentro)
    * @param csvPartidos ruta del archivo csv que contiene informacion de los partidos de tenis
    */ 
   public void cargarPartidos(String csvPartidos){        
        String fileToParse = csvPartidos;
        BufferedReader fileReader = null;
         
        //delimitador del archivo csv;
        final String DELIMITER = ";";
        try
        {
            String line = "";
            //crea el archivo lector
            URL stockURL = new URL(fileToParse);
            fileReader = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            
            line = fileReader.readLine();
            int total = (line.split(DELIMITER).length)-1;
            cantidadAtributos = total;
            //System.out.println("CANTIDAD --->" + total);
            
            //inicializamos los arrayList que guardaran los valores de cada atributo para victorias y derrotas
            for(int i=0; i<total; i++){
                ArrayList<Double> listaAtributosV = new ArrayList<>();
                victorias.add(listaAtributosV);
                ArrayList<Double> listaAtributosD = new ArrayList<>();
                derrotas.add(listaAtributosD);  
            }
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
                
                EncuentroTenis g = new EncuentroTenis(atributos, resultado);
                juegos.add(g);
                
                //se agrega cada uno de los atributos al arrayList que corresponde de victorias o derrotas
                if(resultado.equals("W")){ // si el resultado W (victoria)
                    for(int i=0; i<(totalAtributos-1); i++){
                        victorias.get(i).add(atributos.get(i));
                    }
                }
                else{ // si el resultado L (derrota)
                    for(int i=0; i<(totalAtributos-1); i++){
                        derrotas.get(i).add(atributos.get(i));
                    }                            
                }
            }
            cantidadPartidos = juegos.size();
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
    * El metodo se encarga de ejecutar el calculo de medias y varianzas para cada uno de los atributos de los partidos
    * y se guardan en arrayList de medias victoria / derrota, y varianzas victoria / derrota
    */
    private void calcularMediasVarianzas() {

        int total = victorias.size(); // se calcula el total de atributos
        
        //se calculan todas las medias y varianzas para cada uno de los atributos i en donde los partidos fueron victorias
        for(int i=0; i<total; i++){
            Double media = calcularMedia(victorias.get(i));
            mediaVictorias.add(media);
            Double varianza = calcularVarianza(mediaVictorias.get(i), victorias.get(i));
            varianzaVictorias.add(varianza);
        }

        //se calculan todas las medias y varianzas para cada uno de los atributos i en donde los partidos fueron derrotas
        for(int i=0; i<total; i++){
            Double media = calcularMedia(derrotas.get(i));
            mediaDerrotas.add(media);     
            Double varianza = calcularVarianza(mediaDerrotas.get(i), derrotas.get(i));
            varianzaDerrotas.add(varianza); 
        }      
         
        
    }
    
    /**
     * El metodo se encarga de calcular la media de un conjunto de datos
     * @param elementos conjunto de datos a los que se desea calcular la media
     * @return la media del conjunto de datos ingresados
     */
    public Double calcularMedia(ArrayList<Double> elementos){
        double media,suma = 0.0;
        for(double i : elementos){
            suma += i;
        }
        media = suma/elementos.size();
        return media;
    }     
    
    /**
     * El metodo se encarga de calcular la varianza de un conjunto de datos
     * @param mediaCalculada media del conjunto de datos ingresados
     * @param elementos conjunto de datos a los que se desea calcular la varianza
     * @return la varianza del conjunto de datos ingresados
     */
    public Double calcularVarianza(double mediaCalculada, ArrayList<Double> elementos){
        double media = mediaCalculada;
        double varianza,temp = 0.0;

        for(double i : elementos){
            temp += Math.pow((i-media), 2);
        }
        varianza = temp/(elementos.size()-1);

        return varianza;
    }    
    
    /**
     * El metodo se encarga de calcular la probabilidad condicional
     * @param xi valor del atributo i evaluado
     * @param auxMedia media del conjunto de atributos i evaluados
     * @param auxVarianza varianza del conjunto de atributos i evaluados
     * @return la probabilidad condicional P(a/b)
     */
    public Double calcularProbabilidadCondicional(double xi, double auxMedia, double auxVarianza){
        double resultado = 0;    
        double media = auxMedia;
        double varianza = auxVarianza;
        double pi = Math.PI;
        
        double calculo1 = 1/Math.sqrt(2*pi*varianza);
        double calculo2 = ( -1* (Math.pow((xi-media), 2)) ) / (2*varianza);
        resultado = calculo1 * Math.exp(calculo2);

        return resultado;
    }    

    /**
     * El metodo se encarga de clasificar un partido en particular calculando la probabilidad a posteriori
     * para cada resultado (victoria y derrota) 
     * @param game partido que se desea clasificar
     */
    public String clasificarPartido(EncuentroTenis game) {
        double resultadoW = 1.0;
        double resultadoD = 1.0;
        double evidencia;
        
        resultadoW = resultadoW * probVictoria;
        resultadoD = resultadoD * probDerrota;
        
        int totalVictorias = victorias.size();
        int totalDerrotas = derrotas.size();
        for(int i=0; i<game.atributos.size();i++){
            //System.out.println("---> " + game.atributos.get(i));
        }
        //System.out.println("---> " + game.resultado);
        
        for(int i=0; i<totalVictorias ; i++){
            Double mediaVictory = mediaVictorias.get(i);
            Double varianzaVictory = varianzaVictorias.get(i);
            resultadoW *= calcularProbabilidadCondicional(game.getAtributos().get(i), mediaVictory, varianzaVictory);
        }
        for(int j=0; j<totalDerrotas ; j++){        
            Double mediaDefeat = mediaDerrotas.get(j);
            Double varianzaDefeat = varianzaDerrotas.get(j);            
            resultadoD *= calcularProbabilidadCondicional(game.getAtributos().get(j), mediaDefeat, varianzaDefeat);            
        }
        evidencia = resultadoW + resultadoD;
        
        if(resultadoW > resultadoD){
            //System.out.println("GANO V --> " + resultadoW/evidencia + " || " + resultadoD);
            //System.out.println("V");
            return "W";
        }
        else{
            //System.out.println("PERDIO D --> " + resultadoW/evidencia + " || " + resultadoD);
            //System.out.println("D");
            return "L";
        }
        }
        
    }
    

