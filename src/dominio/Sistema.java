/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author LuisPastorino, Carolina Perez
 */
public class Sistema {
    
    private ArrayList<Jugador> jugadores;
    private ArrayList<Partida> partidas;
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Partida partidaActual;
    private boolean turno = false;
    private boolean terminoPartida = false;
    private boolean silencio = false;

    public boolean isTerminoPartida() {
        return terminoPartida;
    }

    public void setTerminoPartida(boolean terminoPartida) {
        this.terminoPartida = terminoPartida;
    }

    public Sistema() {
        jugadores = new ArrayList<>();
        partidas = new ArrayList<>();
        tablero = null;
        jugador1 = null;
        jugador2 = null;
        partidaActual = null;
        jugadores.add(new Jugador("Luis Pastorino","Luis",25));
        jugadores.add(new Jugador("Sabrina Gonzalez","Sabrina",17));
    }
    
    public boolean validarAlias(String alias) {
        boolean valido = false;
        if (!alias.trim().toUpperCase().equals(""))
            if(!jugadores.contains(new Jugador("",alias.trim().toUpperCase(),1)))
                valido = true;
        return valido;
    }
    
    public int optionEval (int min, int max, String op) throws Exception{
        try {
           int opcion = Integer.parseInt(op);
           if (opcion >= min && opcion <= max)
               return opcion;
           else 
               throw new Exception("Opción inválida.");
        }
        catch (Exception e) {
            throw new Exception("Opción inválida.");
        }
    }
    
//    public void inicializarTablero(String tipoVisualizacion) {
//        tablero = new Tablero(tipoVisualizacion, this.getJugador1(), this.getJugador2());
//    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Partida> getPartidas() {
        Collections.sort(partidas);
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    public void registrarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }
    
    public void registrarPartida(Partida partida) {
        partidas.add(partida);
    }
    
    public ArrayList<Jugador> ranking() {
        ArrayList<Jugador> ranking = new ArrayList<>();
        ranking.addAll(jugadores);
        Collections.sort(ranking);
        return ranking;
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
    
    public void puntuarJugadores(Jugador jugadorGanador, Jugador jugadorPerdedor) {
        if (jugadorGanador != null && jugadorPerdedor != null) {
            jugadorGanador.registrarPartida(1);
            jugadorPerdedor.registrarPartida(-1);
        }
    }
    
    public Jugador getTurno() {
        if (!turno) { 
            return this.jugador1;
        }
        else {
            return this.jugador2;
        }
    }
    
    public void cambiarTurno() {
        this.turno = (turno)?false:true;
        this.reproducirSonido("Cuack.wav");
    }
    
    public boolean validarOpcion(String opcion, Jugador jugador, ArrayList<Integer> validarPosibleJugada) throws Exception{
        opcion = opcion.trim();
        boolean valido = false;
        try {
            if (opcion.length() == 2) {
                int posicion = Integer.parseInt(String.valueOf(opcion.charAt(0)));
                String direccion = String.valueOf(opcion.charAt(1));
                if (posicion > 0 && posicion < 9) {
                    if (validarPosibleJugada != null) {
                        if (validarPosibleJugada.contains(posicion)) 
                            if (direccion.equals("A") || direccion.equals("I") || direccion.equals("D"))
                                valido = this.validarMovimiento(opcion, jugador);
                    }
                    else {
                        if (direccion.equals("A") || direccion.equals("I") || direccion.equals("D"))
                                valido = this.validarMovimiento(opcion, jugador);
                    }
                }
            }
            else {
                if (opcion.equals("X")) {
                    this.terminarPartida(jugador);
                    valido = true;
                } 
            }            
        } catch (Exception e) {
            throw new Exception("Movimiento no válido");
        }        
        return valido;
    } 
    
     public boolean validarMovimiento(String mov, Jugador jugador) {
        Movimiento movimiento = new Movimiento(mov, jugador);
        String ficha = movimiento.getNumeroMovimiento();
        String direccion = movimiento.getDireccionMovimiento();
        int fila = 0;
        boolean esValido = false;
        //necesito la posición actual de la ficha
        ArrayList posiciónActual = partidaActual.getMovimientos();
        Jugador jug1 = partidaActual.getJugador1();
        if (jug1.equals(jugador)) {
            fila = -1;
        } else {
            fila = 1;
        }
        //recorro tablero para obtener posición de la ficha
        for (int i = 0; i < this.getTablero().getTablero().length; i++) {
            for (int j = 0; j < this.getTablero().getTablero()[0].length; j++) {
                Ficha fichaActual = this.getTablero().getTablero()[i][j];
                if (fichaActual.getJugador() != null) {
                    if (fichaActual.getJugador().equals(jugador) && fichaActual.getNro().equals(ficha)) {
                        //posición de la ficha está ocupada
                        switch (direccion) {
                            case "A":
                                if (i + fila >= 0 && i + fila <= 7) {
                                    if (this.getTablero().getTablero()[i + fila][j].getColor().equals(" "))
                                        esValido = true;
                                }
                                break;
                            case "I":
                                if (i + fila >= 0 && i + fila <= 7 && j - 1 >= 0 && j - 1 <= 8) {
                                    if(this.getTablero().getTablero()[i + fila][j - 1].getColor().equals(" "))
                                        esValido = true;
                                }
                                break;
                            case "D":
                                if (i + fila >= 0 && i + fila <= 7 && j + 1>= 0 && j + 1 <= 8) {
                                    if (this.getTablero().getTablero()[i + fila][j + 1].getColor().equals(" "))
                                        esValido = true;
                                }
                                break;
                        }
                    }
                }
            }
        }
        return esValido;
    }
    
    public void terminarPartida(Jugador jugador) {
        this.setTerminoPartida(true);
        this.turno = false;
        this.partidas.add(partidaActual);
        if (jugador == null)
            empatarPartida(this.getJugador1(), this.getJugador2());
        else {
            Jugador jugadorGanador = (jugador.equals(this.getJugador1())? this.getJugador2() : this.getJugador1());
            jugadorGanador.registrarPartida(1);
            jugador.registrarPartida(-1);
        }
        this.setJugador1(null);
        this.setJugador2(null);
        this.setPartidaActual(null);
        this.setTablero(null);        
    }

    public void empatarPartida(Jugador jugador1, Jugador jugador2) {
        if (jugador1 != null && jugador2 != null) {
            jugador1.registrarPartida(0);
            jugador2.registrarPartida(0);
        }
    }
    
    public ArrayList<Integer> realizarJugada(String mov, Jugador jugador) {
        Movimiento movimiento =  new Movimiento(mov, jugador);
        this.partidaActual.agregarMovimiento(movimiento);
        boolean encontro = false;
        ArrayList<Integer> posiblesMovimientos = new ArrayList<>();
            for (int i=0; i<this.getTablero().getTablero().length;i++) {
                for (int j=0; j<this.getTablero().getTablero()[0].length;j++) {
                    Ficha fichaActual = this.getTablero().getTablero()[i][j];
                    // se busca la ficha que se desea mover
                    if (fichaActual.getNro().equals(movimiento.getNumeroMovimiento()) && fichaActual.getJugador().equals(movimiento.getJugador())) {
                        posiblesMovimientos = this.moverFicha(fichaActual, movimiento.getDireccionMovimiento(), i, j);    
                        encontro = true;
                    }
                    if (encontro)
                        break;
                }
            }
        return posiblesMovimientos;
    }
    
    public ArrayList<Integer> moverFicha(Ficha ficha, String movimiento, int filaActual, int columnaActual) {
        this.getTablero().getTablero()[filaActual][columnaActual] = new Ficha(" ", " ");
        int upDown = (ficha.getColor().equals("ROJO"))? -1:1;
        int leftRigth = 0;
        switch (movimiento) {
            case "I":
                leftRigth = -1;
                break;
            case "D":
                leftRigth = 1;
                break;
            case "A":    
                leftRigth = 0;
                break; 
        }
        int filaNueva = filaActual + upDown;
        int columnaNueva = columnaActual + leftRigth;
        this.getTablero().getTablero()[filaNueva][columnaNueva] = ficha;   
        return this.posiblesMovimientos(ficha, filaNueva, columnaNueva);
    }
    
    public ArrayList<Integer> posiblesMovimientos (Ficha ficha, int fila, int columna) {
        ArrayList<Integer> posiblesMovimientos = new ArrayList<>(); 
        int sumaVertical = this.sumaVertical(ficha, columna);
        int sumaHorizontal = this.sumaHorizontal(ficha, fila);
        if (sumaVertical != -1)
            if (!fichaSobreBordeOpuesto(new Ficha(String.valueOf(sumaVertical),ficha.getColor())))
                posiblesMovimientos.add(sumaVertical);
        if (sumaHorizontal != -1 && !posiblesMovimientos.contains(sumaHorizontal))
            if (!fichaSobreBordeOpuesto(new Ficha(String.valueOf(sumaHorizontal),ficha.getColor())))
                posiblesMovimientos.add(sumaHorizontal);  
        ArrayList<Integer> sumaDiagonales = sumaDiagonales(ficha, fila, columna);
        for (Integer i : sumaDiagonales) {
            if (i != -1 && !posiblesMovimientos.contains(i))
                if (!fichaSobreBordeOpuesto(new Ficha(String.valueOf(i),ficha.getColor())))
                    posiblesMovimientos.add(i);
        }
        return posiblesMovimientos;
    }
    
    public int sumaHorizontal (Ficha ficha, int fila) { 
        int suma = -1;
        for (int i=0;i<this.getTablero().getTablero()[0].length;i++) {
            Ficha fichaActual = this.getTablero().getTablero()[fila][i];
            if (!fichaActual.getNro().equals(" ")) 
                suma += Integer.parseInt(fichaActual.getNro());
        }
        if (suma != -1) 
            suma++;
        if (suma == Integer.parseInt(ficha.getNro()) || suma > 8)
            suma = -1;
        return suma;
    }
    
    public int sumaVertical (Ficha ficha, int columna) { 
    int suma = -1;
        for (int i=0;i<this.getTablero().getTablero().length;i++) {
            Ficha fichaActual = this.getTablero().getTablero()[i][columna];
            if (!fichaActual.getNro().equals(" ")) 
                suma += Integer.parseInt(fichaActual.getNro());
        }
        if (suma != -1) 
            suma++;
        if (suma == Integer.parseInt(ficha.getNro()) || suma > 8)
            suma = -1;
        return suma;    
    }
    
    public ArrayList<Integer> sumaDiagonales (Ficha ficha, int fila, int columna) { 
        int sumaDiagPrincipal = -1;
        int sumaDiagSecundaria = -1;
        ArrayList<Integer> sumas = new ArrayList<>();
        int iter = 0;
        for (int i=fila -1; i>=0;i--) {
            iter++;
            if (columna - iter >= 0) {
                Ficha fichaActual = this.getTablero().getTablero()[i][columna - iter];
                if (!fichaActual.getNro().equals(" "))
                    sumaDiagPrincipal += Integer.parseInt(fichaActual.getNro());
            }                
            if (columna + iter < this.getTablero().getTablero()[0].length) {
                Ficha fichaActual = this.getTablero().getTablero()[i][columna + iter];
                if (!fichaActual.getNro().equals(" "))
                    sumaDiagSecundaria += Integer.parseInt(fichaActual.getNro());
            }
                
        }
        iter = 0;
        for (int i=fila +1; i<this.getTablero().getTablero().length;i++) {
            iter++;
            if (columna - iter >= 0) {
                Ficha fichaActual = this.getTablero().getTablero()[i][columna - iter];
                if (!fichaActual.getNro().equals(" "))
                    sumaDiagSecundaria += Integer.parseInt(fichaActual.getNro());
            }
            if (columna + iter < this.getTablero().getTablero()[0].length) {
                Ficha fichaActual = this.getTablero().getTablero()[i][columna + iter];
                if (!fichaActual.getNro().equals(" "))
                    sumaDiagPrincipal += Integer.parseInt(fichaActual.getNro());
            }               
        }
        
        if (sumaDiagPrincipal != -1) 
            sumaDiagPrincipal += Integer.parseInt(ficha.getNro()) + 1;
        if (sumaDiagPrincipal == Integer.parseInt(ficha.getNro()) || sumaDiagPrincipal > 8)
            sumaDiagPrincipal = -1;
        if (sumaDiagSecundaria != -1) 
            sumaDiagSecundaria += Integer.parseInt(ficha.getNro()) + 1;
        if (sumaDiagSecundaria == Integer.parseInt(ficha.getNro()) || sumaDiagSecundaria > 8)
            sumaDiagSecundaria = -1;
        
        sumas.add(sumaDiagPrincipal);
        sumas.add(sumaDiagSecundaria);
        
        return sumas;
    }
    
    private boolean fichaSobreBordeOpuesto (Ficha ficha) {
        boolean estaBordeOpuesto = false;
        int bordeAValidar = (ficha.getColor().equals("ROJO"))?0:this.getTablero().getTablero().length -1;
        for (int i=0; i<this.getTablero().getTablero()[0].length;i++) {   
            if (ficha.equals(this.getTablero().getTablero()[bordeAValidar][i]))
                estaBordeOpuesto = true;
        }
        return estaBordeOpuesto;
    }
    
    public void validarFinalDePartida (int cantidadMovimientos) {
        Jugador ganador = null;
        switch (this.getPartidaActual().getTipoPartida()) {
            case CANTIDAD_MOVIMIENTOS:
                if (cantidadMovimientos == this.partidaActual.getCantidadMovimientos()) {
                    ganador = contarFichasSobreBordeOpuesto();
                    if (ganador == null) 
                        this.terminarPartida(null);
                    else {
                        this.terminarPartida((ganador.equals(this.getJugador1()))?this.getJugador2():this.getJugador1());
                    }                   
                }
                break;
            case TODAS_LAS_FICHAS_AL_OTRO_LADO:
                ganador = validarFichasSobreBordeOpuesto(true);
                if (ganador != null) {
                    this.terminarPartida((ganador.equals(this.getJugador1()))?this.getJugador2():this.getJugador1());
                }
                break;
            case UNA_FICHA_AL_OTRO_LADO:
                ganador = validarFichasSobreBordeOpuesto(false);
                if (ganador != null) {
                    this.terminarPartida((ganador.equals(this.getJugador1()))?this.getJugador2():this.getJugador1());
                }    
                break;
        }
    }
    
    public Jugador contarFichasSobreBordeOpuesto () {
        int bordeInferior = this.getTablero().getTablero().length - 1;
        int bordeSuperior = 0;
        int sumaJugador1 = 0;
        int sumaJugador2 = 0;
        for (int i=0;i<this.getTablero().getTablero()[0].length;i++) {
            Ficha ficha = this.getTablero().getTablero()[bordeSuperior][i];
            if (ficha.getJugador() != null)
                if (ficha.getJugador().equals(this.getJugador1()))
                    sumaJugador1 += Integer.parseInt(ficha.getNro());
            ficha = this.getTablero().getTablero()[bordeInferior][i];
            if (ficha.getJugador() != null)
                if (ficha.getJugador().equals(this.getJugador2()))
                    sumaJugador2 += Integer.parseInt(ficha.getNro());            
        }
        if (sumaJugador1 > sumaJugador2)
            return this.getJugador1();
        else if (sumaJugador1 < sumaJugador2)
            return this.getJugador2();
        else 
            return null;
    }
    
    public Jugador validarFichasSobreBordeOpuesto (boolean validarTodas) {
        int bordeInferior = this.getTablero().getTablero().length - 1;
        int bordeSuperior = 0;
        int sumaJugador1 = 36;
        int sumaJugador2 = 36;
        boolean fichaBordeOpuestoAzul = false;
        boolean fichaBordeOpuestoRojo = false;
        Jugador ganador = null;
        for (int i=0;i<this.getTablero().getTablero()[0].length;i++) {
            Ficha ficha = this.getTablero().getTablero()[bordeSuperior][i];
            if (ficha.getJugador() != null) {
                if (ficha.getJugador().equals(this.getJugador1())) {
                    if (validarTodas)
                        sumaJugador1 -= Integer.parseInt(ficha.getNro());
                    else {
                        fichaBordeOpuestoRojo = true;
                        break;
                    }                    
                }
            }
            ficha = this.getTablero().getTablero()[bordeInferior][i];
            if (ficha.getJugador() != null) {    
                if (ficha.getJugador().equals(this.getJugador2())) {
                    if (validarTodas)
                        sumaJugador2 -= Integer.parseInt(ficha.getNro());
                    else {
                        fichaBordeOpuestoAzul = true;
                        break;                    
                    }
                } 
            }   
        }
        if (validarTodas) {
                if (sumaJugador1 == 0)
                    ganador = this.getJugador1();
                else if (sumaJugador2 == 0)
                    ganador = this.getJugador2();
                else
                    ganador = null;                    
            }
            else {
                if (fichaBordeOpuestoRojo)
                    ganador = this.getJugador1();
                else if (fichaBordeOpuestoAzul)
                    ganador = this.getJugador2();
                else if (!fichaBordeOpuestoRojo && !fichaBordeOpuestoAzul)
                    ganador = null;
            }
        return ganador;        
    }
    
    public void reproducirSonido(String nombreAudio) {
        if (!silencio) {
            AudioClip sonidoAReproducir = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/" + nombreAudio));
            sonidoAReproducir.play();
        }
    }
    
}
