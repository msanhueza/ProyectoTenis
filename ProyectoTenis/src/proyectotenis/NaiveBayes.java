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

/**
 *
 * @author msanhuezal
 */
public class NaiveBayes {
    
    public static double[] wMedia, wVarianza, lMedia, lVarianza;
    public static ArrayList<Double> atrW1, atrW2, atrW3, atrW4, atrW5, atrW6, atrW7, atrW8, atrW9, atrW10, 
                                    atrW11, atrW12, atrW13, atrW14, atrW15, atrW16, atrW17, atrW18;     
    public static ArrayList<Double> atrL1, atrL2, atrL3, atrL4, atrL5, atrL6, atrL7, atrL8, atrL9, atrL10,
                                    atrL11, atrL12, atrL13, atrL14, atrL15, atrL16, atrL17, atrL18;
    public static double probW, probL;
    public static Match[] matchesTest;
    
    public static void main(String[] args) {
        int N = 18;
        
        probW       = 0.5;
        probL       = 0.5;
        
        matchesTest = new Match[1+1];
        
        wMedia      = new double[N+1];
        wVarianza   = new double[N+1];
        lMedia      = new double[N+1];
        lVarianza   = new double[N+1];     
        
        atrW1 = new ArrayList<>();
        atrW2 = new ArrayList<>();
        atrW3 = new ArrayList<>();
        atrW4 = new ArrayList<>();
        atrW5 = new ArrayList<>();
        atrW6 = new ArrayList<>();
        atrW7 = new ArrayList<>();
        atrW8 = new ArrayList<>();
        atrW9 = new ArrayList<>();
        atrW10 = new ArrayList<>();
        atrW11 = new ArrayList<>();
        atrW12 = new ArrayList<>();
        atrW13 = new ArrayList<>();
        atrW14 = new ArrayList<>();
        atrW15 = new ArrayList<>();
        atrW16 = new ArrayList<>();
        atrW17 = new ArrayList<>();
        atrW18 = new ArrayList<>();        
        
        atrL1 = new ArrayList<>();
        atrL2 = new ArrayList<>();
        atrL3 = new ArrayList<>();
        atrL4 = new ArrayList<>();
        atrL5 = new ArrayList<>();
        atrL6 = new ArrayList<>();
        atrL7 = new ArrayList<>();
        atrL8 = new ArrayList<>();
        atrL9 = new ArrayList<>(); 
        atrL10 = new ArrayList<>();
        atrL11 = new ArrayList<>();
        atrL12 = new ArrayList<>();
        atrL13 = new ArrayList<>();
        atrL14 = new ArrayList<>();
        atrL15 = new ArrayList<>();
        atrL16 = new ArrayList<>();
        atrL17 = new ArrayList<>();
        atrL18 = new ArrayList<>();        
        
        cargarPartidos();
        inicializarMediaVarianza();
        cargarPartidosDePrueba();
        clasificarPartido(matchesTest[0]);
        
    }
    
    public static void imprimir(double[] aux){
        for(int i=0; i<aux.length; i++){
            System.out.print(aux[i] + " ");
        }
        System.out.println("");
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

                double atributoW1      = Double.parseDouble(tokens[0]); //posicion ranking mundial
                double atributoW2      = Double.parseDouble(tokens[1]); //partidos ganados
                double atributoW3      = Double.parseDouble(tokens[2]); //partidos perdidos
                double atributoW4      = Double.parseDouble(tokens[3]); //total aces
                double atributoW5      = Double.parseDouble(tokens[4]); //porcentaje de primeros servicios ganados
                double atributoW6      = Double.parseDouble(tokens[5]); //porcentaje de juegos ganados con su servicio
                double atributoW7      = Double.parseDouble(tokens[6]); //Porcentaje de break-point ganados
                double atributoW8      = Double.parseDouble(tokens[7]); //Porcentaje de juegos ganados con devolución
                double atributoW9      = Double.parseDouble(tokens[8]); //Racha no consecutiva
                double atributoL1      = Double.parseDouble(tokens[9]); //posicion ranking mundial
                double atributoL2      = Double.parseDouble(tokens[10]); //partidos ganados
                double atributoL3      = Double.parseDouble(tokens[11]); //partidos perdidos
                double atributoL4      = Double.parseDouble(tokens[12]); //total aces
                double atributoL5      = Double.parseDouble(tokens[13]); //porcentaje de primeros servicios ganados
                double atributoL6      = Double.parseDouble(tokens[14]); //porcentaje de juegos ganados con su servicio
                double atributoL7      = Double.parseDouble(tokens[15]); //Porcentaje de break-point ganados
                double atributoL8      = Double.parseDouble(tokens[16]); //Porcentaje de juegos ganados con devolución
                double atributoL9      = Double.parseDouble(tokens[17]); //Racha no consecutiva
                String resultado       = tokens[18]; //resultado
                
                if(resultado.equals("W")){ // si es caracteristica W (victoria)
                    atrW1.add(atributoW1);
                    atrW2.add(atributoW2);
                    atrW3.add(atributoW3);
                    atrW4.add(atributoW4);
                    atrW5.add(atributoW5);
                    atrW6.add(atributoW6);
                    atrW7.add(atributoW7);
                    atrW8.add(atributoW8);
                    atrW9.add(atributoW9);
                    atrW10.add(atributoL1);
                    atrW11.add(atributoL2);
                    atrW12.add(atributoL3);
                    atrW13.add(atributoL4);
                    atrW14.add(atributoL5);
                    atrW15.add(atributoL6);
                    atrW16.add(atributoL7);
                    atrW17.add(atributoL8);
                    atrW18.add(atributoL9);                     
                }
                else{ // si es caracteristica L (derrota)
                    atrL1.add(atributoW1);
                    atrL2.add(atributoW2);
                    atrL3.add(atributoW3);
                    atrL4.add(atributoW4);
                    atrL5.add(atributoW5);
                    atrL6.add(atributoW6);
                    atrL7.add(atributoW7);
                    atrL8.add(atributoW8);
                    atrL9.add(atributoW9);
                    atrL10.add(atributoL1);
                    atrL11.add(atributoL2);
                    atrL12.add(atributoL3);
                    atrL13.add(atributoL4);
                    atrL14.add(atributoL5);
                    atrL15.add(atributoL6);
                    atrL16.add(atributoL7);
                    atrL17.add(atributoL8);
                    atrL18.add(atributoL9);                    
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
        return partidos;
    }

    private static void inicializarMediaVarianza() {

        wMedia[0] = calcularMedia(atrW1);
        wMedia[1] = calcularMedia(atrW2);                
        wMedia[2] = calcularMedia(atrW3);                 
        wMedia[3] = calcularMedia(atrW4);   
        wMedia[4] = calcularMedia(atrW5); 
        wMedia[5] = calcularMedia(atrW6); 
        wMedia[6] = calcularMedia(atrW7); 
        wMedia[7] = calcularMedia(atrW8); 
        wMedia[8] = calcularMedia(atrW9); 
        wMedia[9] = calcularMedia(atrW10);
        wMedia[10] = calcularMedia(atrW11);
        wMedia[11] = calcularMedia(atrW12);
        wMedia[12] = calcularMedia(atrW13);
        wMedia[13] = calcularMedia(atrW14);
        wMedia[14] = calcularMedia(atrW15);
        wMedia[15] = calcularMedia(atrW16);
        wMedia[16] = calcularMedia(atrW17);
        wMedia[17] = calcularMedia(atrW18);
        
        wVarianza[0] = calcularVarianza(wMedia[0], atrW1);
        wVarianza[1] = calcularVarianza(wMedia[1], atrW2);
        wVarianza[2] = calcularVarianza(wMedia[2], atrW3);
        wVarianza[3] = calcularVarianza(wMedia[3], atrW4);
        wVarianza[4] = calcularVarianza(wMedia[4], atrW5);
        wVarianza[5] = calcularVarianza(wMedia[5], atrW6);
        wVarianza[6] = calcularVarianza(wMedia[6], atrW7);
        wVarianza[7] = calcularVarianza(wMedia[7], atrW8);
        wVarianza[8] = calcularVarianza(wMedia[8], atrW9);
        wVarianza[9] = calcularVarianza(wMedia[9], atrW10);
        wVarianza[10] = calcularVarianza(wMedia[10], atrW11);
        wVarianza[11] = calcularVarianza(wMedia[11], atrW12);
        wVarianza[12] = calcularVarianza(wMedia[12], atrW13);
        wVarianza[13] = calcularVarianza(wMedia[13], atrW14);
        wVarianza[14] = calcularVarianza(wMedia[14], atrW15);
        wVarianza[15] = calcularVarianza(wMedia[15], atrW16);
        wVarianza[16] = calcularVarianza(wMedia[16], atrW17);
        wVarianza[17] = calcularVarianza(wMedia[17], atrW18);
                
        lMedia[0] = calcularMedia(atrL1); 
        lMedia[1] = calcularMedia(atrL1); 
        lMedia[2] = calcularMedia(atrL1); 
        lMedia[3] = calcularMedia(atrL1); 
        lMedia[4] = calcularMedia(atrL1); 
        lMedia[5] = calcularMedia(atrL1); 
        lMedia[6] = calcularMedia(atrL1); 
        lMedia[7] = calcularMedia(atrL1);                
        lMedia[8] = calcularMedia(atrL1);   
        lMedia[9] = calcularMedia(atrL10); 
        lMedia[10] = calcularMedia(atrL11); 
        lMedia[11] = calcularMedia(atrL12); 
        lMedia[12] = calcularMedia(atrL13); 
        lMedia[13] = calcularMedia(atrL14); 
        lMedia[14] = calcularMedia(atrL15); 
        lMedia[15] = calcularMedia(atrL16); 
        lMedia[16] = calcularMedia(atrL17); 
        lMedia[17] = calcularMedia(atrL18); 
        
        lVarianza[0] = calcularVarianza(lMedia[0], atrL1);
        lVarianza[1] = calcularVarianza(lMedia[1], atrL2);
        lVarianza[2] = calcularVarianza(lMedia[2], atrL3);
        lVarianza[3] = calcularVarianza(lMedia[3], atrL4);
        lVarianza[4] = calcularVarianza(lMedia[4], atrL5);
        lVarianza[5] = calcularVarianza(lMedia[5], atrL6);
        lVarianza[6] = calcularVarianza(lMedia[6], atrL7);
        lVarianza[7] = calcularVarianza(lMedia[7], atrL8);
        lVarianza[8] = calcularVarianza(lMedia[8], atrL9);
        lVarianza[9] = calcularVarianza(lMedia[9], atrL10);
        lVarianza[10] = calcularVarianza(lMedia[10], atrL11);
        lVarianza[11] = calcularVarianza(lMedia[11], atrL12);
        lVarianza[12] = calcularVarianza(lMedia[12], atrL13);
        lVarianza[13] = calcularVarianza(lMedia[13], atrL14);
        lVarianza[14] = calcularVarianza(lMedia[14], atrL15);
        lVarianza[15] = calcularVarianza(lMedia[15], atrL16);
        lVarianza[16] = calcularVarianza(lMedia[16], atrL17);
        lVarianza[17] = calcularVarianza(lMedia[17], atrL18);
        
    }
    
    public static Double calcularMedia(ArrayList<Double> elementos){
        double media,suma = 0.0;
        for(double i : elementos){
            suma += i;
        }
        media = suma/elementos.size();
        return media;
    }     
    
    public static Double calcularVarianza(double mediaCalculada, ArrayList<Double> elementos){
        double media = mediaCalculada;
        double varianza,temp = 0.0;

        for(double i : elementos){
            temp += Math.pow((i-media), 2);
        }
        varianza = temp/(elementos.size()-1);

        return varianza;
    }    
    
    public static Double calcularProbabilidadCondicional(double xi, double auxMedia, double auxVarianza){
        double resultado = 0;    
        double media = auxMedia; //5.855
        double varianza = auxVarianza; //0.035033
        double pi = Math.PI;
        
        double calculo1 = 1/Math.sqrt(2*pi*varianza);
        double calculo2 = ( -1* (Math.pow((xi-media), 2)) ) / (2*varianza);
        resultado = calculo1 * Math.exp(calculo2);

        return resultado;
    }    

    public static void cargarPartidosDePrueba() {
        Match m1 = new Match(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "W");
        matchesTest[0] = m1;
    }

    private static void clasificarPartido(Match matchesTest) {
        double resultadoW = 1.0;
        double resultadoL = 1.0;
        double evidencia;
        int total = matchesTest.getAtributos().length-1;
        double[] match = matchesTest.getAtributos();
        
        resultadoW = resultadoW * probW;
        resultadoL = resultadoL * probL;
        
        for(int i=0; i<total ; i++){
            resultadoW *= calcularProbabilidadCondicional(match[i], wMedia[i], wVarianza[i]);
            resultadoL *= calcularProbabilidadCondicional(match[i], lMedia[i], lVarianza[i]);
        }
        evidencia = resultadoW + resultadoL;
        
        if(resultadoW > resultadoL){
            System.out.println("GANO W --> " + resultadoW/evidencia + " || " + resultadoL);
        }
        else{
            System.out.println("PERDIO L --> " + resultadoW/evidencia + " || " + resultadoL);
        }
        }
        
    }
    

