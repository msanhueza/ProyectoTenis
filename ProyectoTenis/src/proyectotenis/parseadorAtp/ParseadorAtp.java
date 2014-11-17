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

/**
 *
 * @author msanhuezal
 */
public class ParseadorAtp {

    static ArrayList<Jugador> jugadores = new ArrayList<>();
    
public static void main(String[] args) throws IOException {
        //obtenerPartidos("Tennis/Players/Top-Players/Rafael-Nadal.aspx?t=pa&");
        obtenerDatos200();
        //estadisticaDesempenoJugadores(2014, 0);
        //estadisticaH2H("D643", "N409"); //djokovic vs nadal
        //estadisticaH2H("F324", "T786"); //federer vs tsonga
    }
    
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

private static void obtenerPartidos(String enlace) throws MalformedURLException, IOException{
    //ArrayList<Partido> p = new ArrayList<>();
    ArrayList<String> anos = new ArrayList<>();
    anos.add("2013");
    for(int x=0; x<anos.size(); x++){
        URL url = new URL("http://www.atpworldtour.com/" + enlace + "y="+anos.get(x)+"&m=s&e=0#");
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
                contenido += anos.get(x) + ";";
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
                            contenido2 += limpiarNombreApellido2(inputLine.substring(inputLine.indexOf("aspx")+6, inputLine.indexOf("</a>"))) + ";";
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
}

private static void obtenerDatos200() throws IOException {
    ArrayList<String> rank = new ArrayList<>();
    rank.add("1"); //primeros 100
    //rank.add("101"); //101-200
    for(int i=0; i<rank.size(); i++){
        URL url = new URL("http://www.atpworldtour.com/Rankings/Singles.aspx?d=30.12.2013&r="+rank.get(i)+"&c=#");
        String link = "http://www.atpworldtour.com";
        URLConnection uc = url.openConnection();
        uc.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        int num = 0;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.contains("<a href=\"/Tennis/Players") && num != 3) {
                if(num == 0){
                    contenido += limpiarNombreApellido(inputLine.substring(inputLine.indexOf("aspx")+6, inputLine.indexOf("</a>"))) + ";"; //nombre
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
                String pais = jug[1];
                int puntos = Integer.parseInt(jug[2]);
                String enlace = jug[3];
                Jugador j = new Jugador(nombre, pais, puntos, enlace); //creo el jugador
                jugadores.add(j); // agrego al jugador
                //obtenerPartidos(enlace);
                
                contenido = "";
                num = 0;
            }

        }
        in.close();
    }    

} 

private static String limpiarValorPorcentual(String valor){
    String aux = valor.substring(0, valor.length()-1);
    double nuevoValor = Double.parseDouble(aux) / 100;
    return nuevoValor + "";
}

private static String limpiarNombreApellido(String valor){
    String nombre = valor.substring(valor.indexOf(";")+1, valor.length());
    String apellido = valor.substring(0, valor.indexOf(","));
    return nombre + " " + apellido;
}

private static String limpiarNombreApellido2(String valor){
    String apellido = valor.substring(valor.indexOf(";")+1, valor.length());
    String nombre = valor.substring(0, valor.indexOf("&nbsp"));
    return nombre + " " + apellido;
}

private static String limpiarValores(String jugador){
    String[] datosJugador = jugador.split(";");
    jugador = "";
    for(int i=0; i<datosJugador.length; i++){
        if(i == 0 || i == 1 || i == 2 || i == 5 || i == 7){
            jugador += datosJugador[i] + ";";
        }    
        else if(i == 3 || i == 4){
            String[] aux = datosJugador[i].split("-");
            jugador += aux[0] + ";";
            jugador += aux[1] + ";";
        }
        else if(i == 6 || i == 8){
            jugador += datosJugador[i].replace(",", ".") + ";";
        }
        else if(i>=9){
            if(i != 15){
                jugador += limpiarValorPorcentual(datosJugador[i]) + ";";
            }
            else{
                String[] aux = datosJugador[i].split("-");
                jugador += limpiarValorPorcentual(aux[0]) + ";";
                jugador += limpiarValorPorcentual(aux[1]) + ";";               
            }
        }
    }
    jugador = jugador.substring(0, jugador.length()-1);
    return jugador;
}

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
                            inputLine = (i==3)? (limpiarNombreApellido(inputLine)) : (inputLine + ";");
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
