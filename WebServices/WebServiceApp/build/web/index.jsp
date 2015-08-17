<%-- 
    Author     : msanhuezal
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>   

    <head>
        
    <style>
    body {
        background-image: url("images/ball.jpg");
    }
    </style>        
        
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <!-- Para que funcione el responsive en celulares -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Predictor Tenis</title>

    
    <!-- CSS y JS para JQuery -->
    <link href='http://fonts.googleapis.com/css?family=Michroma' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/scaffolds.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/homepage.css.scss">   
    
    <%-- autocompletar --%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

    <%-- efecto errores --%>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    
     
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
      
    <script type="text/javascript" src="js/rainbow.min.js"></script>
    <script type="text/javascript" src="js/jquery-asPieProgress.js"></script>
    <script type="text/javascript" src="js/jugadores.js"></script>
      
    </head>
    <body>

    <!-- header -->
    <div id="top-nav" class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="http://54.191.84.23:8080/WebServiceApp/">Predictor Tenis ATP (V 1.0)</a>
            </div>
        </div>
        <!-- /container -->
    </div>
    <!-- /Header -->

    <div class="container-fluid">
	<div class="row">
		<div class="col-sm-12">

                    <div  class="container-fluid">
                            <div class="row">

                                    <div class="col-sm-4">			
                                        <div class="panel panel-darkgreen">
                                                    <div class="panel-heading">
                                                            <h4>La Aplicación</h4>
                                                    </div>
                                                    <div class="panel-body panel-green">
                                                        <p>
                                                            Esta aplicación utiliza el clasificador Naive Bayes para realizar la predicción de victorias entre 
                                                            jugadores de la Asociación de Tenistas Profesionales (ATP).<br/><br/>

                                                            Cabe destacar que la predicción se realiza en base al historial de resultados y desempeño de cada 
                                                            jugador durante la temporada 2012.<br/><br/>

                                                            Para utilizar el sistema, basta con rellenar los campos de texto con los nombres de los jugadores 
                                                            que se desean evaluar, indicar la superficie donde jugarán y presionar el botón "Predecir" para obtener 
                                                            el resultado final del encuentro.
                                                        </p>
                                                    </div>
                                        </div>
                                    </div>


                                    <div class="col-sm-8">
                                            <div class="panel panel-darkorange">
                                                <div class="panel-heading">
                                                    <h4> Quién Ganará? </h4>
                                                </div>
                                                <div class="panel-body panel-orange">
                                                    <div class="col-sm-12">

                                                        <div id="error"></div>


                                                        <form action="index.jsp" method="POST">
                                                            <div class="col-sm-6">
                                                                <div class="panel panel-darkorange">
                                                                    <div class="panel-heading">
                                                                         <h3 class="panel-title"><center>JUGADOR 1</center></h3>
                                                                    </div>
                                                                    <div class="panel-body panel-orange">
                                                                        <div class="input-group input-group-lg">
                                                                            <input id="tags1" type="text" name="player1" class="form-control" placeholder="Nombre Jugador 1" aria-describedby="sizing-addon2">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div> 

                                                            <div class="col-sm-6">
                                                                <div class="panel panel-darkorange">
                                                                    <div class="panel-heading">
                                                                        <h3 class="panel-title"><center>JUGADOR 2</center></h3>
                                                                    </div>
                                                                    <div class="panel-body panel-orange">
                                                                        <div class="input-group input-group-lg">
                                                                            <input id="tags2" type="text" name="player2" class="form-control" placeholder="Nombre Jugador 2" aria-describedby="sizing-addon2">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div> 
                                                            <div  class="col-md-12">
                                                                <h5>TIPO DE SUPERFICIE</h5>
                                                                <div class="radio">
                                                                  <label><input type="radio" name="tipoSuperficie" value="Arcilla" checked="checked" /> Arcilla </label>
                                                                  <label><input type="radio" name="tipoSuperficie" value="Cemento" /> Cemento </label>
                                                                  <label><input type="radio" name="tipoSuperficie" value="Pasto" /> Pasto </label>
                                                                </div>                              
                                                            </div>
                                                            <center><button class="btn btn-primary" onclick="jQuery('#reload').load(' #reload');" />Predecir</button></center>
                                                        </form></br> 
    <div id="reload">                                                        
    <%-- start web service invocation --%>
    <%
    try {
	ws.prediccion.PredecirPartido_Service service = new ws.prediccion.PredecirPartido_Service();
	ws.prediccion.PredecirPartido port = service.getPredecirPartidoPort();
	 // TODO initialize WS operation arguments here
	java.lang.String jugador1 = request.getParameter("player1");
	java.lang.String jugador2 = request.getParameter("player2");
        String auxSuperficie = request.getParameter("tipoSuperficie");
        int tipoSuperficie;
        String superficieJuego = "";
        if(auxSuperficie.equals("Arcilla")){
            tipoSuperficie = 1;
            superficieJuego = "A R C I L L A";
        }
        else if(auxSuperficie.equals("Cemento")){
            tipoSuperficie = 2;
            superficieJuego = "C E M E N T O";
        }
        else{ //Pasto
            tipoSuperficie = 3;
            superficieJuego = "P A S T O";
        }
	// TODO process result here
	String result = port.predecir(jugador1, jugador2, tipoSuperficie);
        if(!jugador1.isEmpty() && !jugador2.isEmpty()){
            if(!jugador1.equals(jugador2)){
                String nombreJugador1 = jugador1;
                String nombreJugador2 = jugador2;
                String resultadoJugador1 = "";
                String resultadoJugador2 = "";
                if(!result.equals("SIN INFORMACION")){
                    if(result.equals("W")){
                        resultadoJugador1 = "GANADOR";
                        resultadoJugador2 = "PERDEDOR";
                    }
                    else{
                        resultadoJugador1 = "PERDEDOR";
                        resultadoJugador2 = "GANADOR";                    
                    }
                    
                    String resultado = "<div class=\"col-sm-12\">"+
                                        "<div class=\"panel panel-darkorange\">"+
                                            "<div class=\"panel-heading\">"+
                                                 "<h3 class=\"panel-title\"><center>Superficie:  "+superficieJuego+"</center></h3>"+
                                            "</div>"+
                                            "<div class=\"panel-body panel-orange\">"+
                                                        "<div class=\"col-sm-6\">" +
                                                            "<center><h3>"+nombreJugador1+"</h3></center><br/>"+
                                                            "<center><h5>("+resultadoJugador1+")</h5></center><br/>"+
                                                        "</div>" +   
                                                        "<div class=\"col-sm-6\">" +
                                                            "<center><h3>"+nombreJugador2+"</h3></center><br/>"+
                                                            "<center><h5>("+resultadoJugador2+")</h5></center><br/>"+
                                                        "</div>" + 
                                            "</div>"+
                                        "</div>"+
                                    "</div>";                    
                    out.println(resultado);   
                }
                else{
                    String cod = "<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">" +
                                 "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>" +
                                 "<strong>Error!</strong> No existe información para uno de los jugadores ingresados." + "</div>";
                    out.println(cod);                    
                }
                
            }
            else{
                  String cod = "<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">" +
                               "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>" +
                               "<strong>Error!</strong> Debe ingresar jugadores diferentes." + "</div>";
                  out.println(cod);
            }
        }
        else{
            String cod = "<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">" +
                         "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>" +
                         "<strong>Error!</strong> Debe ingresar el nombre de los jugadores que desea evaluar." + "</div>";
            out.println(cod);
            
            
           
        }
	
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%> 
    </div>
                                                    </div>
                                                </div>
                                            </div>   
                                    <div class="col-md-1"></div>
                                    </div>        
                            </div>
                    </div>
                </div>
        </div>
    </div>

</body>
</html>
