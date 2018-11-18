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
    private String tipoVisualizacion;

    public String getTipo() {
        return tipoVisualizacion;
    }

    public void setTipo(String tipo) {
        this.tipoVisualizacion = tipo;
    }

    public Ficha[][] getTablero() {
        return tablero;
    }

    public void setTablero(Ficha[][] tablero) {
        this.tablero = tablero;
    }

    public Tablero(String tipo, Jugador jugador1, Jugador jugador2) {
        this.tablero = new Ficha[8][9];
        this.tipoVisualizacion = tipo;
        int largo = tablero[0].length -1;
        for (int i=0;i<tablero.length;i++) {
            for (int j=0;j<tablero[0].length;j++) {
                if (i==0) {
                    if (j != 0) {
                        tablero[i][j] = new Ficha(String.valueOf(j), "AZUL", jugador2);
                    }
                    else {
                        tablero[i][j] = new Ficha(" ", " ");
                    }                        
                }
                else if (i == tablero.length -1){
                    if (j != tablero[0].length -1) {
                        tablero[i][j] = new Ficha(String.valueOf(largo--), "ROJO", jugador1);
                    }
                    else {
                        tablero[i][j] = new Ficha(" ", " ");
                    }
                }        
                else {
                    tablero[i][j] = new Ficha(" ", " ");
                }
            }
        }
    }   
}
