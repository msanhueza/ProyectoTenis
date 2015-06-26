/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.prediccion;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author msanhuezal
 */
@WebService(serviceName = "PredecirPartido")
public class PredecirPartido {

    /**
     * Web service operation
     * @param jugador1
     * @param jugador2
     * @return 
     */
    @WebMethod(operationName = "predecir")
    public ArrayList<String> predecir(@WebParam(name = "jugador1") String jugador1, @WebParam(name = "jugador2") String jugador2, @WebParam(name = "tipoSuperficie") int tipoSuperficie) throws InterruptedException {
        ArrayList<String> resultados = new ArrayList<>();
        int superficie = tipoSuperficie; // 1 Arcilla, 2 Cemento, 3 Pasto
        String csvDesempeno = "";
        String csvPartidos = "";
        String csvEncuentros = "";
        Game g;
        if(tipoSuperficie == 1){
            csvDesempeno = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\desempenoClay2012.csv";
            csvPartidos = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\partidosClay2012.csv";
            csvEncuentros = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\encuentros2012.csv";
            Dato d = new Dato("Clay", csvDesempeno, csvEncuentros);
            g = d.crearFormatoSalida(jugador1, jugador2);
        }
        else if(tipoSuperficie == 2){
            csvDesempeno = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\desempenoHard2012.csv";
            csvPartidos = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\partidosHard2012.csv";       
            csvEncuentros = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\encuentros2012.csv";
            Dato d = new Dato("Hard", csvDesempeno, csvEncuentros);
            g = d.crearFormatoSalida(jugador1, jugador2);            
        }
        else{
            csvDesempeno = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\desempenoGrass2012.csv";
            csvPartidos = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\partidosGrass2012.csv";        
            csvEncuentros = "C:\\Users\\msanhuezal\\Documents\\NetBeansProjects\\WebServiceTenis\\src\\java\\ws\\prediccion\\datos\\encuentros2012.csv";            
            Dato d = new Dato("Grass", csvDesempeno, csvEncuentros);
            g = d.crearFormatoSalida(jugador1, jugador2);            
        }
         
        ClasificadorNaiveBayes cnb = new ClasificadorNaiveBayes(csvPartidos);
        if(g == null){
            return null;
        }
        String r = cnb.clasificarPartido(g);

        if(r.equals("W")){
            resultados.add("");
        }
        else{
            resultados.add("");
            resultados.add("");
        }
        return resultados;
    }

}
