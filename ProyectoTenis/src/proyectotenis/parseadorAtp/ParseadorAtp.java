/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis.parseadorATP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author msanhuezal
 */

/**
 * 
 * La clase permite ejecutar algunos metodos encargados de extraer datos desde un sitio web oficial de la
 * Asociaciones de Tenistas Profesionales (ATP)
 */
public class ParseadorATP {

    
public static void main(String[] args) throws IOException {
        //obtenerResultadosEncuentros(2012, 100);    
        //obtenerJugadores(2012, 100);
        //obtenerDesempenoJugadoresTop200(2012, 0);
        //obtenerHistorialEncuentros("D643", "N409"); //(djokovic vs nadal) F324 federer, T786 tsonga
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
 * @param enlace se refiere a la URL que permite obtener todos los partidos a los que se ha enfrentado un jugador
 * @param ano se refiere al año de los partidos que se obtendran
 * @throws MalformedURLException
 * @throws IOException 
 */
private static void obtenerPartidos(String enlace, int ano) throws MalformedURLException, IOException{
        
        String anoEvaluado = ano+"";
        URL url = new URL("http://es.atpworldtour.com/" + enlace + "y="+anoEvaluado+"&m=s&e=0#");
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
                String auxSuperficie = aux[aux.length-4];
                if(auxSuperficie.equals("Arcilla")){
                    contenido += "Clay" + ";";            
                }
                else if(auxSuperficie.equals("Pasto")){
                    contenido += "Grass" + ";";            
                }
                else{ // cemento
                    contenido += "Hard" + ";";            
                }           
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
}


/**
 * El metodo permite obtener información de jugadores del circuito atp para una determinada temporada
 * @param ano se refiere al año de la informacion de los jugadores que se quiere obtener
 * @param topJugadores se refiere a la cantidad de jugadores que se quiere obtener
 * @return
 * @throws IOException 
 */
private static HashMap<String, Integer> obtenerJugadores(int ano, int topJugadores) throws IOException {
    //1-top 100 / 101- top 200 / 201- top 300 / 301- top 400 / 401- top 500
    ArrayList<String> rank = new ArrayList( Arrays.asList("1", "101", "201", "301", "401", "501"));
    String fecha = "";
    if(ano == 2012){
        fecha = "31.12.2012";
    }
    else if(ano == 2013){
        fecha = "30.12.2013";
    }
    else if(ano == 2014){
        fecha = "29.12.2014";
    }
    int cantidadJugadores = (topJugadores/100);
    int contador = 0;
    HashMap<String, Integer> jugadores = new HashMap<>();
    for(int i=0; i<cantidadJugadores; i++){
        URL url;
        url = new URL("http://es.atpworldtour.com/Rankings/Singles.aspx?d="+fecha+"&r="+rank.get(i)+"&c=#");
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
  * @param ano se refiere al año de la informacion de los jugadores que se quiere obtener
 * @param topJugadores se refiere a la cantidad de jugadores que se quiere obtener* 
 * @throws IOException 
 */
private static void obtenerResultadosEncuentros(int ano, int topJugadores) throws IOException {
    //1-top 100 / 101- top 200 / 201- top 300 / 301- top 400 / 401- top 500
    ArrayList<String> rank = new ArrayList( Arrays.asList("1", "101", "201", "301", "401", "501"));
    String fecha = "";
    if(ano == 2012){
        fecha = "31.12.2012";
    }
    else if(ano == 2013){
        fecha = "30.12.2013";
    }
    else if(ano == 2014){
        fecha = "29.12.2014";
    }
    int cantidadJugadores = (topJugadores/100);
    for(int i=0; i<topJugadores; i++){
        URL url;
        url = new URL("http://es.atpworldtour.com/Rankings/Singles.aspx?d="+fecha+"&r="+rank.get(i)+"&c=#");

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
                int puntos = Integer.parseInt(jug[2].replace(".", ""));
                String enlace = jug[3];
                obtenerPartidos(enlace, ano);
                System.out.println("");
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
 * @return el valor porcentual normalizado
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
private static void obtenerHistorialEncuentros(String idJug1, String idJug2) throws MalformedURLException, IOException{
    URL url = new URL("http://es.atpworldtour.com/Players/Head-To-Head.aspx?pId=" + idJug1 + "&oId=" + idJug2);
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
private static void obtenerDesempenoJugadoresTop200(int ano, int superficie) throws MalformedURLException, IOException{
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
