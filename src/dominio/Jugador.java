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
public class Jugador implements Comparable<Jugador>, Serializable{
    
    private String nombre;
    private String alias;
    private int edad;
    private int partidasGanadas;
    private int partidasEmpatadas;
    private int partidasPerdidas;    

    public Jugador(String nombre, String alias, int edad) {
        this.nombre = nombre;
        this.alias = alias;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasEmpatadas() {
        return partidasEmpatadas;
    }

    public void setPartidasEmpatadas(int partidasEmpatadas) {
        this.partidasEmpatadas = partidasEmpatadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    } 

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Alias: " + alias + ", Edad:" + edad;
    }
    
    public String mostrarPartidas() {
        return "Jugador: " + this.getAlias() + ", partidas ganadas: " + this.partidasGanadas + ", partidas empatadas: " + this.partidasEmpatadas + ", partidas perdidas: " + this.partidasPerdidas;
    }

    @Override
    public boolean equals (Object o) {
        return this.getAlias().equalsIgnoreCase(((Jugador)o).getAlias());
    }

    @Override
    public int compareTo(Jugador j) {
        return j.getPartidasGanadas() - this.getPartidasGanadas();
    }
    
    public static boolean validarEdad (String edad) {
        try {
            int edadEntero = Integer.parseInt(edad);
            if (edadEntero >= 1 && edadEntero <=100) 
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }        
    }
    
    public void registrarPartida(int partida) {
        if (partida == 1)
            this.partidasGanadas++;
        else if (partida == 0)
            this.partidasEmpatadas++;
        else if (partida == -1)
            this.partidasPerdidas++;
    }
    
    
    
    
    
    
    
    
    
    
}
