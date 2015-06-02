/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis.parseadorAtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author msanhuezal
 */

/**
 * 
 * La clase permite ejecutar algunos metodos encargados de extraer datos desde un sitio web
 */
public class ParseadorAtp {

static ArrayList<Jugador> jugadores = new ArrayList<>();
    
public static void main(String[] args) throws IOException {
        //obtenerPartidos("Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=pa&");
        //obtenerDatos200(2012);
        //obtenerJugadores(2012);
        estadisticaDesempenoJugadores(2013, 3);
        //estadisticaH2H("D643", "N409"); //djokovic vs nadal
        //estadisticaH2H("F324", "T786"); //federer vs tsonga
    }

/**
 * El metodo es un ejemplo de como extraer el código fuente de una pagina web
 * @return el código fuente de la página evaluada
 * @throws IOException 
 */
private static String obtenerContenidoHtml() throws IOException {
    URL url = new URL("http://www.atpworldtour.com/Share/Event-Draws.aspx?e=580&y=2014");
    URLConnection uc = url.openConnection();
    uc.connect();
    //Creamos el objeto con el que vamos a leer
    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
    String inputLine;
    String contenido = "";
    while ((inputLine = in.readLine()) != null) {
           contenido += inputLine + "\n";
    }
    in.close();
    return contenido;
}    

/**
 * El metodo se encarga de obtener todos los partidos a los que se ha enfrentado un determinado jugador
 * @param enlace se refiere a la URL que permite visualizar todos los partidos a los que se ha enfrentado un jugador
 * @throws MalformedURLException
 * @throws IOException 
 */
private static void obtenerPartidos(String enlace, int ano) throws MalformedURLException, IOException{
        
        String anoEvaluado = ano+"";
        URL url = new URL("http://www.atpworldtour.com/" + enlace + "y="+anoEvaluado+"&m=s&e=0#");
        URLConnection uc = url.openConnection();
        uc.connect();    
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        String contenido = "";
        String contenido2 = "";
        int i = 0;
        int j = 0;
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.contains("<p class=\"bioPlayActivityInfo\"><a href=\"/Tennis/Tournaments") || inputLine.contains("<p class=\"bioPlayActivityInfo\"><strong>")){ //campeonato, año, superficie
                contenido = "";
                contenido += inputLine.substring(inputLine.indexOf("<strong>")+8, inputLine.indexOf("</strong>")) + ";";
                contenido += anoEvaluado + ";";
                String[] aux = inputLine.split(";");
                contenido += aux[aux.length-4] + ";";            
            }
            if(inputLine.contains("<td>")){
                if(i>5){
                    if(j == 0){ //ronda
                        contenido2 += inputLine.substring(inputLine.indexOf("<td>")+4, inputLine.indexOf("</td>")) + ";";
                    }
                    else if(j == 1){ //oponente
                        if(inputLine.contains("<td>Bye")){
                            contenido2 += "Bye" + ";";
                        }
                        else{
                            contenido2 += TranformarNombreApellido2(inputLine.substring(inputLine.indexOf("aspx")+6, inputLine.indexOf("</a>"))) + ";";
                        }
                    }
                    else if(j == 3){//ganador o no
                        contenido2 += inputLine.substring(inputLine.indexOf("<td>")+4, inputLine.indexOf("&nbsp"));
                        System.out.println(contenido + contenido2);
                        contenido2 = "";
                    }
                    j = (j != 4)? j=j+1 : 0;
                }                    
                i++;
            }  
        }
        in.close();    
        //return p;
    
}


/**
 * El metodo permite obtener información de los primeros 1000 jugadores del circuito atp para una determinada temporada
 * @throws IOException 
 */
private static HashMap<String, Integer> obtenerJugadores(int ano) throws IOException {
    ArrayList<String> rank = new ArrayList<>();
    rank.add("1"); //primeros 100
    rank.add("101"); //101-200
    rank.add("201"); //201-300
    rank.add("301"); //301-400
    rank.add("401"); //401-500
    rank.add("501"); //501-600
    rank.add("601"); //601-700
    rank.add("701"); //701-800
    rank.add("801"); //801-900    
    rank.add("901"); //901-1000
    String fecha2012 = "31.12.2012";
    int contador = 0;
    HashMap<String, Integer> jugadores = new HashMap<>();
    for(int i=0; i<rank.size(); i++){
        URL url;
        url = new URL("http://www.atpworldtour.com/Rankings/Singles.aspx?d="+fecha2012+"&r="+rank.get(i)+"&c=#");
        URLConnection uc = url.openConnection();
        uc.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        int num = 0;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.contains("<a href=\"/Tennis/Players") && num != 3) {
                if(num == 0){
                    contenido += TransformarNombreApellido(inputLine.substring(inputLine.indexOf("aspx")+6, inputLine.indexOf("</a>"))) + ";"; //nombre
                    contenido += inputLine.substring(inputLine.length()-4, inputLine.length()-1) + ";"; //pais
                }
                else if(num == 1){
                    contenido += inputLine.substring(inputLine.indexOf("rb\">")+4, inputLine.indexOf("</a>")).replace(",", "") + ";"; //puntos
                }
                else{
                    contenido += inputLine.substring(inputLine.indexOf("href")+6, inputLine.indexOf("pa&")+3); //link partidos
                }                
                num++;
            }
            if(num == 3){ //agregar jugador
                String[] jug = contenido.split(";");
                String nombre = jug[0];
                //String pais = jug[1];
                //int puntos = Integer.parseInt(jug[2]);
                //String enlace = jug[3];
                //Jugador j = new Jugador(nombre, pais, puntos, enlace); //creo el jugador
                System.out.println(nombre);
                jugadores.put(nombre , contador );
                contador++;
                contenido = "";
                num = 0;
            }

        }
        in.close();
    }    

    return jugadores;
} 

/**
 * El metodo permite obtener información de los primeros 200 jugadores del circuito atp para una determinada temporada
 * @throws IOException 
 */
private static void obtenerDatos200(int ano) throws IOException {
    ArrayList<String> rank = new ArrayList<>();
    rank.add("1"); //primeros 100
    rank.add("101"); //101-200
    String fecha2012 = "31.12.2012";
    String fecha2013 = "30.12.2013";
    for(int i=0; i<rank.size(); i++){
        URL url;
        if(ano == 2012){
            url = new URL("http://www.atpworldtour.com/Rankings/Singles.aspx?d="+fecha2012+"&r="+rank.get(i)+"&c=#");
        }
        else{
            url = new URL("http://www.atpworldtour.com/Rankings/Singles.aspx?d="+fecha2013+"&r="+rank.get(i)+"&c=#");
        }
        URLConnection uc = url.openConnection();
        uc.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        int num = 0;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.contains("<a href=\"/Tennis/Players") && num != 3) {
                if(num == 0){
                    contenido += TransformarNombreApellido(inputLine.substring(inputLine.indexOf("aspx")+6, inputLine.indexOf("</a>"))) + ";"; //nombre
                    contenido += inputLine.substring(inputLine.length()-4, inputLine.length()-1) + ";"; //pais
                }
                else if(num == 1){
                    contenido += inputLine.substring(inputLine.indexOf("rb\">")+4, inputLine.indexOf("</a>")).replace(",", "") + ";"; //puntos
                }
                else{
                    contenido += inputLine.substring(inputLine.indexOf("href")+6, inputLine.indexOf("pa&")+3); //link partidos
                }                
                num++;
            }
            if(num == 3){ //agregar jugador
                System.out.println(contenido);
                String[] jug = contenido.split(";");
                String nombre = jug[0];
                String pais = jug[1];
                int puntos = Integer.parseInt(jug[2]);
                String enlace = jug[3];
                Jugador j = new Jugador(nombre, pais, puntos, enlace); //creo el jugador
                jugadores.add(j); // agrego al jugador
                obtenerPartidos(enlace, ano);
                contenido = "";
                num = 0;
            }

        }
        in.close();
    }    

} 

/**
 * El metodo permite transformar un valor porcentual del tipo 98% a 0.98
 * @param valor se refiere al valor porcentual sin transformar
 * @return 
 */
private static String TransformarValorPorcentual(String valor){
    String aux = valor.substring(0, valor.length()-1);
    double nuevoValor = Double.parseDouble(aux) / 100;
    return nuevoValor + "";
}

/**
 * El metodo permite transformar el nombre de un jugador a un formato determinado
 * @param valor se refiere al nombre del jugador sin transformar
 * @return el nombre del jugador en el formato adecuado
 */
private static String TransformarNombreApellido(String valor){
    String nombre = valor.substring(valor.indexOf(";")+1, valor.length());
    String apellido = valor.substring(0, valor.indexOf(","));
    return nombre + " " + apellido;
}

/**
 * El metodo permite transformar el nombre de un jugador a un formato determinado
 * @param valor se refiere al nombre del jugador sin transformar
 * @return el nombre del jugador en el formato adecuado
 */
private static String TranformarNombreApellido2(String valor){
    String apellido = valor.substring(valor.indexOf(";")+1, valor.length());
    String nombre = valor.substring(0, valor.indexOf("&nbsp"));
    return nombre + " " + apellido;
}

/**
 * El metodo permite limpiar algunos atributos correspondientes a un jugador
 * @param jugador corresponde a todos los atributos de un jugador separados por ";"
 * @return todos los atributos de un jugador, separados por ";" y en el formato adecuado
 */
private static String limpiarValores(String jugador){
    String[] datosJugador = jugador.split(";");
    jugador = "";
    for(int i=0; i<datosJugador.length; i++){
        if(i == 0 || i == 1 || i == 2 || i == 5 || i == 7){ // si no hay que hacer cambios
            jugador += datosJugador[i] + ";";
        }    
        else if(i == 3 || i == 4){ // si el valor esta compuesto de 2 atributos separados por un "-"
            String[] aux = datosJugador[i].split("-");
            jugador += aux[0] + ";";
            jugador += aux[1] + ";";
        }
        else if(i == 6 || i == 8){ // si el valor cuenta con una "," se reemplaza por un "."
            jugador += datosJugador[i].replace(",", ".") + ";";
        }
        else if(i>=9){
            if(i != 15){ // si el valor corresponde a un valor porcentual
                jugador += TransformarValorPorcentual(datosJugador[i]) + ";";
            }
            else{ // si el valor corresponde a dos valores porcentuales separados por un "-"
                String[] aux = datosJugador[i].split("-");
                jugador += TransformarValorPorcentual(aux[0]) + ";";
                jugador += TransformarValorPorcentual(aux[1]) + ";";               
            }
        }
    }
    jugador = jugador.substring(0, jugador.length()-1);
    return jugador;
}

/**
 * El metodo permite obtener información sobre los encuentros entre dos jugadores de tenis
 * @param idJug1 se refiere al ID del jugador en el sitio web
 * @param idJug2 se refiere al ID del jugador en el sitio web
 * @throws MalformedURLException
 * @throws IOException 
 */
private static void estadisticaH2H(String idJug1, String idJug2) throws MalformedURLException, IOException{
    URL url = new URL("http://www.atpworldtour.com/Players/Head-To-Head.aspx?pId=" + idJug1 + "&oId=" + idJug2);
    URLConnection uc = url.openConnection();
    uc.connect();
    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
    String inputLine;
    String contenido = "";
    int param = 0;
    boolean flag = false;
    while ((inputLine = in.readLine()) != null) {
           if(inputLine.contains("<td><strong>")){ //datos de los partidos año, torneo, superficie, ronda, ganador
               if(param == 4 && !flag){
                   System.out.println(contenido.substring(0, contenido.length()-1)); //salto linea estadistica general
                   flag = true;
                   contenido = "";
               }
               int i = 0;
               inputLine = inputLine.substring(inputLine.indexOf("<strong>")+8, inputLine.length()-14); //año
               contenido += inputLine + ";";               
               while(i != 4 && (inputLine = in.readLine()) != null){
                   if(inputLine.contains("<td>")){
                       if(inputLine.contains("<td><a href=")){
                            inputLine = inputLine.substring(inputLine.indexOf("<strong>")+8, inputLine.indexOf("</strong>"));
                            inputLine = (i==3)? (TransformarNombreApellido(inputLine)) : (inputLine + ";");
                            contenido += inputLine;
                                                      
                       }
                       else{ // superficie o ronda
                           inputLine = inputLine.substring(inputLine.indexOf(">")+1, inputLine.length()-5);
                           contenido += inputLine + ";";
                           
                       }
                       i++;
                   }
               }
               System.out.println(contenido); //imprimo los resultados de cada partido
               contenido = "";
           }
           else{ // si son los jugadores o sus victorias
                if(param != 4 ){
                        if((inputLine.contains("<a href=\"/Tennis/Players") || inputLine.contains("<a href=\"/en/Tennis/Players"))){ // nombre de los 2 jugadores
                            String nombre = inputLine.substring(inputLine.indexOf("fName")+7, inputLine.indexOf("</span><span"));
                            String apellido = inputLine.substring(inputLine.indexOf("lName")+7, inputLine.indexOf("</span></a>"));
                            inputLine = nombre + " " + apellido;
                            contenido += inputLine + ";";
                            param++;
                        }
                        else if(inputLine.contains("<p class=\"module-h2h-wins\">")){
                            inputLine = inputLine.substring(inputLine.indexOf(">")+1, inputLine.length()-4);
                            contenido += inputLine + ";";
                            param++;
                        }      
                }
           }
    }    
    in.close();   
}

/**
 * El metodo permite obtener información sobre el desempeño de un jugador para un año y superficie determinada
 * @param ano se refiere al año de la temporada a evaluar
 * @param superficie se refiere al tipo de cancha en la que jugo el jugador: Todas (0), Arcilla (1), Pasto (2) y Asfalto (3)
 * @throws MalformedURLException
 * @throws IOException 
 */
private static void estadisticaDesempenoJugadores(int ano, int superficie) throws MalformedURLException, IOException{
    URL url = new URL("http://es.atpworldtour.com/Rankings/Top-Matchfacts.aspx?y=" + ano + "&s=" + superficie + "#");
    URLConnection uc = url.openConnection();
    uc.connect();
    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream())); //Creamos el objeto con el que vamos a leer
    String inputLine;
    String jugador = "";
    int indice = 0;
    while ((inputLine = in.readLine()) != null) {
           if(inputLine.contains("<td class=")){
               inputLine = (!inputLine.contains("<td class=\"col1\""))? inputLine.substring(inputLine.indexOf(">")+1, inputLine.length()-5) : inputLine.substring(inputLine.indexOf(">")+1, inputLine.length());
               indice++;
               jugador += inputLine + ";";
           }
           if(inputLine.contains("<a href=\"/Tennis/Players") || inputLine.contains("<a href=\"/en/Tennis/Players")){ // nombre del jugador
                inputLine = inputLine.substring(inputLine.indexOf(">")+1, inputLine.length()-9);
                indice++;
                jugador += inputLine + ";";
           }
           if(indice == 17){ // si tenemos todos los atributos del jugador
                indice = 0;
                jugador = limpiarValores(jugador);
                System.out.println(jugador); //imprimo el jugador parseado
                jugador = "";
            }
    }
    in.close();  
}
}
