/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author LuisPastorino, Carolina Perez
 */
public class Partida implements Comparable<Partida>, Serializable{
    
    private Date hora;
    private Jugador jugador1;
    private Jugador jugador2;
    private TipoPartida tipoPartida;
    private ArrayList<Movimiento> movimientos;
    private int cantidadMovimientos;
    
    public Partida() {
        this.hora = hora;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tipoPartida = tipoPartida;
        this.movimientos = movimientos;
    }
    
    public Partida(Date hora, Jugador jugador1, Jugador jugador2, TipoPartida tipoPartida, ArrayList<Movimiento> movimientos) {
        this.hora = hora;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tipoPartida = tipoPartida;
        this.movimientos = movimientos;
    }
    
    public Partida(Date hora, Jugador jugador1, Jugador jugador2, TipoPartida tipoPartida, ArrayList<Movimiento> movimientos, int cantidad) {
        this.hora = hora;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tipoPartida = tipoPartida;
        this.movimientos = movimientos;
        this.cantidadMovimientos = cantidad;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public TipoPartida getTipoPartida() {
        return tipoPartida;
    }

    public void setTipoPartida(String tipoPartida) {
        this.tipoPartida = TipoPartida.valueOf(tipoPartida);
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
    public int getCantidadMovimientos() {
        return cantidadMovimientos;
    }

    public void setCantidadMovimientos(int cantidadMovimientos) {
        this.cantidadMovimientos = cantidadMovimientos;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fechaYHora = formato.format(this.getHora());
        return "Fecha y hora: " + fechaYHora + ", Jugador 1: " + this.getJugador1().getAlias() + ", Jugador 2: " + this.getJugador2().getAlias();
    }
    
    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    @Override
    public int compareTo(Partida o) {
        return o.getHora().compareTo(this.getHora());
    }
}
