/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotenis;

/**
 *
 * @author msanhuezal
 */
public class Match {
    
    int atributoLocal1; //posicion ranking mundial
    int atributoLocal2; //partidos ganados
    int atributoLocal3; //partidos perdidos
    int atributoLocal4; //total aces
    double atributoLocal5; //porcentaje de primeros servicios ganados
    double atributoLocal6; //porcentaje de juegos ganados con su servicio
    double atributoLocal7; //Porcentaje de break-point ganados
    double atributoLocal8; //Porcentaje de juegos ganados con devolución
    double atributoLocal9; //Racha no consecutiva
    int atributoVisita1; //posicion ranking mundial
    int atributoVisita2; //partidos ganados
    int atributoVisita3; //partidos perdidos
    int atributoVisita4; //total aces
    double atributoVisita5; //porcentaje de primeros servicios ganados
    double atributoVisita6; //porcentaje de juegos ganados con su servicio
    double atributoVisita7; //Porcentaje de break-point ganados
    double atributoVisita8; //Porcentaje de juegos ganados con devolución
    double atributoVisita9; //Racha no consecutiva
    String resultado; //resultado    

    public Match(int atributoLocal1, int atributoLocal2, int atributoLocal3, int atributoLocal4, double atributoLocal5, double atributoLocal6, double atributoLocal7, double atributoLocal8, double atributoLocal9, int atributoVisita1, int atributoVisita2, int atributoVisita3, int atributoVisita4, double atributoVisita5, double atributoVisita6, double atributoVisita7, double atributoVisita8, double atributoVisita9, String resultado) {
        this.atributoLocal1 = atributoLocal1;
        this.atributoLocal2 = atributoLocal2;
        this.atributoLocal3 = atributoLocal3;
        this.atributoLocal4 = atributoLocal4;
        this.atributoLocal5 = atributoLocal5;
        this.atributoLocal6 = atributoLocal6;
        this.atributoLocal7 = atributoLocal7;
        this.atributoLocal8 = atributoLocal8;
        this.atributoLocal9 = atributoLocal9;
        this.atributoVisita1 = atributoVisita1;
        this.atributoVisita2 = atributoVisita2;
        this.atributoVisita3 = atributoVisita3;
        this.atributoVisita4 = atributoVisita4;
        this.atributoVisita5 = atributoVisita5;
        this.atributoVisita6 = atributoVisita6;
        this.atributoVisita7 = atributoVisita7;
        this.atributoVisita8 = atributoVisita8;
        this.atributoVisita9 = atributoVisita9;
        this.resultado = resultado;
    }

    public int getAtributoLocal1() {
        return atributoLocal1;
    }

    public void setAtributoLocal1(int atributoLocal1) {
        this.atributoLocal1 = atributoLocal1;
    }

    public int getAtributoLocal2() {
        return atributoLocal2;
    }

    public void setAtributoLocal2(int atributoLocal2) {
        this.atributoLocal2 = atributoLocal2;
    }

    public int getAtributoLocal3() {
        return atributoLocal3;
    }

    public void setAtributoLocal3(int atributoLocal3) {
        this.atributoLocal3 = atributoLocal3;
    }

    public int getAtributoLocal4() {
        return atributoLocal4;
    }

    public void setAtributoLocal4(int atributoLocal4) {
        this.atributoLocal4 = atributoLocal4;
    }

    public double getAtributoLocal5() {
        return atributoLocal5;
    }

    public void setAtributoLocal5(double atributoLocal5) {
        this.atributoLocal5 = atributoLocal5;
    }

    public double getAtributoLocal6() {
        return atributoLocal6;
    }

    public void setAtributoLocal6(double atributoLocal6) {
        this.atributoLocal6 = atributoLocal6;
    }

    public double getAtributoLocal7() {
        return atributoLocal7;
    }

    public void setAtributoLocal7(double atributoLocal7) {
        this.atributoLocal7 = atributoLocal7;
    }

    public double getAtributoLocal8() {
        return atributoLocal8;
    }

    public void setAtributoLocal8(double atributoLocal8) {
        this.atributoLocal8 = atributoLocal8;
    }

    public double getAtributoLocal9() {
        return atributoLocal9;
    }

    public void setAtributoLocal9(double atributoLocal9) {
        this.atributoLocal9 = atributoLocal9;
    }

    public int getAtributoVisita1() {
        return atributoVisita1;
    }

    public void setAtributoVisita1(int atributoVisita1) {
        this.atributoVisita1 = atributoVisita1;
    }

    public int getAtributoVisita2() {
        return atributoVisita2;
    }

    public void setAtributoVisita2(int atributoVisita2) {
        this.atributoVisita2 = atributoVisita2;
    }

    public int getAtributoVisita3() {
        return atributoVisita3;
    }

    public void setAtributoVisita3(int atributoVisita3) {
        this.atributoVisita3 = atributoVisita3;
    }

    public int getAtributoVisita4() {
        return atributoVisita4;
    }

    public void setAtributoVisita4(int atributoVisita4) {
        this.atributoVisita4 = atributoVisita4;
    }

    public double getAtributoVisita5() {
        return atributoVisita5;
    }

    public void setAtributoVisita5(double atributoVisita5) {
        this.atributoVisita5 = atributoVisita5;
    }

    public double getAtributoVisita6() {
        return atributoVisita6;
    }

    public void setAtributoVisita6(double atributoVisita6) {
        this.atributoVisita6 = atributoVisita6;
    }

    public double getAtributoVisita7() {
        return atributoVisita7;
    }

    public void setAtributoVisita7(double atributoVisita7) {
        this.atributoVisita7 = atributoVisita7;
    }

    public double getAtributoVisita8() {
        return atributoVisita8;
    }

    public void setAtributoVisita8(double atributoVisita8) {
        this.atributoVisita8 = atributoVisita8;
    }

    public double getAtributoVisita9() {
        return atributoVisita9;
    }

    public void setAtributoVisita9(double atributoVisita9) {
        this.atributoVisita9 = atributoVisita9;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}
