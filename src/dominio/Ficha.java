/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author LuisPastorino, Carolina Perez
 */
public class Ficha {
    
    public static final String ROJO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";
    public static final String VERDE = "\u001B[32m";
    public static final String NEGRO = "\u001B[30m";
    private String nro;
    private String color;
    private Jugador jugador;

    public Ficha(String nro, String color, Jugador jugador) {
        this.nro = nro;
        this.color = color;
        this.jugador = jugador;
    }

    public Ficha(String nro, String color) {
        this.nro = nro;
        this.color = color;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return "Nro: " + nro + ", Color: " + color + ", Jugador:" + jugador.getAlias();
    }
    
    @Override
    public boolean equals (Object o) {
        if (this.getNro().equals(((Ficha)o).getNro()) && this.getJugador().equals(((Ficha)o).getJugador()))
            return true;
        else 
            return false;
    }
    
    
    
    
}
