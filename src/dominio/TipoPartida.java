/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Luis Pastorino, Carolina Perez
 */
public enum TipoPartida {
    CANTIDAD_MOVIMIENTOS, UNA_FICHA_AL_OTRO_LADO, TODAS_LAS_FICHAS_AL_OTRO_LADO;
    
    public static String getString(TipoPartida tipo) {
        switch(tipo) {
            case CANTIDAD_MOVIMIENTOS:
                return "Cantidad de movimientos";
            case TODAS_LAS_FICHAS_AL_OTRO_LADO:
                return "Todas las fichas sobre borde opuesto";
            case UNA_FICHA_AL_OTRO_LADO:
                return "Primera ficha sobre borde opuesto";
        }
        return null;
    }
}
