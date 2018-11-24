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
public class Tablero {
    
    private Ficha[][] tablero;
    
    public Ficha[][] getTablero() {
        return tablero;
    }

    public void setTablero(Ficha[][] tablero) {
        this.tablero = tablero;
    }

    public Tablero() {
        tablero = new Ficha[8][9];
    } 
}
