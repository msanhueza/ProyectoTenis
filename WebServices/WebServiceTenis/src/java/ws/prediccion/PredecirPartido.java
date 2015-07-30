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
    public String predecir(@WebParam(name = "jugador1") String jugador1, @WebParam(name = "jugador2") String jugador2, @WebParam(name = "tipoSuperficie") int tipoSuperficie) throws InterruptedException {
        ArrayList<String> resultados = new ArrayList<>();
        int superficie = tipoSuperficie; // 1 Arcilla, 2 Cemento, 3 Pasto
        String csvDesempeno = "";
        String csvPartidos = "";
        String csvEncuentros = "";
        EncuentroTenis g;
        if(tipoSuperficie == 1){
            csvDesempeno = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/desempenoClay2012.csv";
            csvPartidos = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/partidosClay2012.csv";
            csvEncuentros = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/encuentros2012.csv";
            GeneradorEncuentros d = new GeneradorEncuentros("Clay", csvDesempeno, csvEncuentros);
            g = d.crearFormatoSalida(jugador1, jugador2);
        }
        else if(tipoSuperficie == 2){
            csvDesempeno = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/desempenoHard2012.csv";
            csvPartidos = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/partidosHard2012.csv";       
            csvEncuentros = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/encuentros2012.csv";
            GeneradorEncuentros d = new GeneradorEncuentros("Hard", csvDesempeno, csvEncuentros);
            g = d.crearFormatoSalida(jugador1, jugador2);            
        }
        else{
            csvDesempeno = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/desempenoGrass2012.csv";
            csvPartidos = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/partidosGrass2012.csv";        
            csvEncuentros = "https://raw.githubusercontent.com/msanhueza/ProyectoTenis/master/Datos/ServicioWeb/encuentros2012.csv";            
            GeneradorEncuentros d = new GeneradorEncuentros("Grass", csvDesempeno, csvEncuentros);
            g = d.crearFormatoSalida(jugador1, jugador2);            
        }
         
        ClasificadorNaiveBayes cnb = new ClasificadorNaiveBayes(csvPartidos);
        String resultado = "";
        if(g == null){
            return "SIN INFORMACION";
        }
        String r = cnb.clasificarPartido(g);

        if(r.equals("W")){
            resultado = "W";
        }
        else{
            resultado = "L";
        }
        return resultado;
    }

}
