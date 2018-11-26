/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;

/**
 *
 * @author LuisPastorino, Carolina Perez
 */
public class Movimiento implements Serializable{
    
//    private Ficha ficha;
//    private String posInicial;
//    private String posFinal;
      private String movimiento;
      private Jugador jugador;
      
      

//    public Movimiento(Ficha ficha, String posInicial, String posFinal) {
//        this.ficha = ficha;
//        this.posInicial = posInicial;
//        this.posFinal = posFinal;
//    }
//
//    public Ficha getFicha() {
//        return ficha;
//    }
//
//    public void setFicha(Ficha ficha) {
//        this.ficha = ficha;
//    }
//
//    public String getPosInicial() {
//        return posInicial;
//    }
//
//    public void setPosInicial(String posInicial) {
//        this.posInicial = posInicial;
//    }
//
//    public String getPosFinal() {
//        return posFinal;
//    }
//
//    public void setPosFinal(String posFinal) {
//        this.posFinal = posFinal;
//    }
//
//    @Override
//    public String toString() {
//        return "Ficha:" + ficha.getNro() + ", Posición inicial: " + posInicial + ", Posición final: " + posFinal;
//    }

    public Movimiento(String movimiento, Jugador jugador) {
        this.movimiento = movimiento;
        this.jugador = jugador;
    }
    
    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public String getNumeroMovimiento () {
        return this.getMovimiento().substring(0, 1);
    }
    
    public String getDireccionMovimiento () {
        return this.getMovimiento().substring(1, 2);
    }
    
    
    
    
}
