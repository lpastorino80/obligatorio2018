package dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * @author LuisPastorino 208076
 * @author CarolinaPérez 235750
 * 
 */
public class Obligatorio1P22018 {
    
    public static Sistema sistema = new Sistema();
    public static boolean terminoPartida = false;
    
    public static void main(String[] args) {
        try {
            // variables
            Scanner lector = new Scanner(System.in);
            boolean salirMenu = false;            
            // menu principal
            while (!salirMenu) {
                terminoPartida = false;
//                sistema.getListaMovimientos().clear(); 
//                sistema.setJugador1(null);
//                sistema.setJugador2(null);
                System.out.println("\n************************************************************");
                System.out.println("                       MENÚ PRINCIPAL                       ");
                System.out.println("************************************************************");
                System.out.print("\n1) Registro de jugadores.");
                System.out.print("\n2) Jugar.");
                System.out.print("\n3) Replicar partida.");
                System.out.print("\n4) Ranking.");
                System.out.print("\n5) Terminar.");
                System.out.print("\n\nIngrese una opción: "); 
                int opcionMenu = sistema.optionEval(1, 5, lector.nextLine());                
                // se evalua la opción ingresada
                switch (opcionMenu) {
                    case 1:
                        registroJugador();
                        break;
                    case 2:
                        jugar();
                        break;
                    case 3:
                        replicarPartida();
                        break;
                    case 4:
                        ranking();
                        break;
                    case 5:
                        // se sale de la aplicación
                        salirMenu = true;
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("\n" + Ficha.ROJO + e.getMessage() + Ficha.NEGRO + "\n");
            main(args);
        }        
    }
    
    public static void registroJugador(){
        Scanner leer = new Scanner(System.in);
        System.out.println("\n************************************************************");
        System.out.println("                    REGISTRO DE JUGADOR                     ");
        System.out.println("************************************************************");
        System.out.print("\nIngrese el nombre: ");
        String nombre = leer.nextLine();       
        System.out.print("Ingrese el alias: ");        
        String alias = leer.nextLine();
        boolean aliasVal = sistema.validarAlias(alias);
        while(!aliasVal){
            System.out.print("Ingrese el alias: ");
            alias = leer.nextLine();
            aliasVal = sistema.validarAlias(alias);
        }        
        System.out.print("Ingrese el edad: ");
        String edad = leer.nextLine();
        boolean edadVal = Jugador.validarEdad(edad);
        while(!edadVal){
            System.out.print("Ingrese la edad: ");
            edad = leer.nextLine();
            edadVal = Jugador.validarEdad(edad);
        }
        int edadNum = Integer.parseInt(edad);
        Jugador jugador = new Jugador(nombre.toUpperCase(),alias.toUpperCase(),edadNum);
        sistema.registrarJugador(jugador);
        System.out.println(Ficha.VERDE + "\nEl jugador se registró correctamente!" + Ficha.NEGRO);
    }
    
   public static void jugar() throws Exception {
        try {
            seleccionarJugadores();
            seleccionarTipoPartida();
            seleccionarTipoTablero();
            comenzarPartida();
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static void seleccionarJugadores() throws Exception{
        try{
            ArrayList<Jugador> jugadores = new ArrayList<>();
            jugadores.addAll(sistema.getJugadores());
            Jugador jugador1 = seleccionarUnJugador(jugadores);
            jugadores.remove(jugador1);
            Jugador jugador2 = seleccionarUnJugador(jugadores);
            sistema.setJugador1(jugador1);
            sistema.setJugador2(jugador2);
        }
        catch (Exception e) {
            throw new Exception("No se pudo seleccionar el jugador.");
        }
    }
    
    public static Jugador seleccionarUnJugador(ArrayList<Jugador> listaJugadores) throws Exception{
        Scanner lector = new Scanner(System.in);
        int opcionJug = 0;
        boolean valido = false;
        System.out.println("\n************************************************************");
        System.out.println("                   SELECCIONAR UN JUGADOR                   ");
        System.out.println("************************************************************");
        while (!valido) {
            try{
                System.out.println("\n"); 
                if (!listaJugadores.isEmpty()) {
                    for (int i = 0; i < listaJugadores.size(); i++) {
                        System.out.println(i + 1 + " - " + listaJugadores.get(i));
                    }
                    System.out.print("\nSeleccione un jugador: ");
                    int largo = listaJugadores.size();
                    opcionJug = sistema.optionEval(1, largo, lector.nextLine());     
                    valido = true;
                } else {
                    System.out.println("\n");
                    System.out.println(Ficha.ROJO + "No hay jugadores registrados." + Ficha.NEGRO);
                }
            }
            catch (Exception e) {
                System.out.println(Ficha.ROJO + "\n" + e.getMessage() + Ficha.NEGRO);
            }
        }
        return listaJugadores.get(opcionJug - 1);
    }
    
    public static void replicarPartida(){
        Scanner lector = new Scanner(System.in);
        System.out.println("\n************************************************************");
        System.out.println("                      REPLICAR PARTIDA                      ");
        System.out.println("************************************************************\n");
        int index = 1;
        int partidaSeleccionada = 0;
        boolean partidasValida = false;
        if (sistema.getPartidas().isEmpty())
            System.out.println(Ficha.ROJO + "No hay partidas registradas aún" + Ficha.NEGRO);
        else {
            for(Partida partida : sistema.getPartidas()){
                System.out.println(index++ + ") " + partida);
            }                
            while (!partidasValida) {  
                try {
                System.out.print("\nSeleccione una partida: " );
                partidaSeleccionada = sistema.optionEval(1, sistema.getPartidas().size(), lector.nextLine());
                partidasValida = true;
                } catch (Exception e) {
                    System.out.print(Ficha.ROJO + "\n" + e.getMessage() + Ficha.NEGRO + "\n");
                }
            }
            partidaSeleccionada--;
            System.out.println("\n************************************************************");
            System.out.println("                         REPLICANDO                         ");
            System.out.println("************************************************************\n");
            Partida partida = sistema.getPartidas().get(partidaSeleccionada);
            sistema.setJugador1(partida.getJugador1());
            sistema.setJugador2(partida.getJugador2());
            Partida partidaActual = null; 
            if (partida.getTipoPartida().equals(TipoPartida.CANTIDAD_MOVIMIENTOS)) 
                partidaActual = new Partida(partida.getHora(),partida.getJugador1(),partida.getJugador2(),partida.getTipoPartida(),new ArrayList<Movimiento>(),partida.getCantidadMovimientos());
            else
                partidaActual = new Partida(partida.getHora(),partida.getJugador1(),partida.getJugador2(),partida.getTipoPartida(),new ArrayList<Movimiento>());
            sistema.setPartidaActual(partidaActual);
            sistema.inicializarTablero("NORMAL");
            for (Movimiento movimiento : partida.getMovimientos()) {
                System.out.print("\n" + movimiento.getJugador().getAlias() + ": " + movimiento.getMovimiento());
                sistema.realizarJugada(movimiento.getMovimiento(), movimiento.getJugador());
                mostrarTablero(sistema.getTablero());
                lector.nextLine();
            }
            System.out.println(Ficha.VERDE + "Fin de la partida." + Ficha.NEGRO);
        }        
    }
    
    public static void ranking(){
        System.out.println("\n************************************************************");
        System.out.println("                          RANKING                           ");
        System.out.println("************************************************************\n");
        ArrayList<Jugador> jugadores = sistema.ranking();
        int index = 1;
        for(Jugador jug:jugadores){
            System.out.println(index++ + ") " + jug.mostrarPartidas());
        }
    }
    
    public static void seleccionarTipoPartida() {
        Scanner lector = new Scanner(System.in);
        System.out.println("\n************************************************************");
        System.out.println("                      TIPO DE PARTIDA                       ");
        System.out.println("************************************************************");
        System.out.println("\n1)Cantidad de movimientos");
        System.out.println("2)Primera ficha al lado opuesto");
        System.out.println("3)Todas las fichas al lado opuesto");
        System.out.print("\nSeleccione una opción: ");
        int opcion = 0;
        try {
        opcion = sistema.optionEval(1, 3, lector.nextLine());        
        } catch (Exception e) {
            System.out.print("\n" + Ficha.ROJO +  e.getMessage() + "\n" + Ficha.NEGRO);
            seleccionarTipoPartida();
        }
        TipoPartida tipo = null;
        int cantidadMovimientos = -1;
        switch (opcion) {
            case 1:
                tipo = TipoPartida.CANTIDAD_MOVIMIENTOS;
                cantidadMovimientos = pedirCantidadMovimientos();
                break;
            case 2:
                tipo = TipoPartida.UNA_FICHA_AL_OTRO_LADO;
                break;
            case 3:
                tipo = TipoPartida.TODAS_LAS_FICHAS_AL_OTRO_LADO;
                break;                
        }
        Partida nuevaPartida;
        if (cantidadMovimientos == -1)
            nuevaPartida = new Partida(new Date(), sistema.getJugador1(), sistema.getJugador2(), tipo, new ArrayList<Movimiento>());
        else
            nuevaPartida = new Partida(new Date(), sistema.getJugador1(), sistema.getJugador2(), tipo, new ArrayList<Movimiento>(), cantidadMovimientos);
        sistema.setPartidaActual(nuevaPartida);
    }
    
    public static int pedirCantidadMovimientos () {
        Scanner lector = new Scanner(System.in);
        int cantidad = -1;
        try {
            System.out.print("\nCantidad total de movimientos: ");
            cantidad = sistema.optionEval(1, Integer.MAX_VALUE, lector.nextLine());            
        } catch (Exception e) {
            System.out.print(Ficha.ROJO + "\n" + e.getMessage() + Ficha.NEGRO + "\n");
            pedirCantidadMovimientos();
        }
        return cantidad;
    }
    
    public static void seleccionarTipoTablero() {
        Scanner lector = new Scanner(System.in);
        System.out.println("\n************************************************************");
        System.out.println("                      TIPO DE TABLERO                       ");
        System.out.println("************************************************************");
        System.out.println("\n1)Normal");
        System.out.println("2)Reducido");
        System.out.print("\nSeleccione una opción: ");
        int opcion = 0;
        try {
        opcion = sistema.optionEval(1, 2, lector.nextLine());        
        } catch (Exception e) {
            System.out.print("\n" + Ficha.ROJO +  e.getMessage() + "\n" + Ficha.NEGRO);
            seleccionarTipoTablero();
        }
        sistema.inicializarTablero((opcion == 1)? "NORMAL":"REDUCIDO");
    }
    
    public static void mostrarTablero(Tablero tablero) {
        String tipo = tablero.getTipo();
        System.out.print("\n");
        for (int i = 0; i < tablero.getTablero().length; i++) {
            // borde superior
            if (tipo.equalsIgnoreCase("NORMAL"))
                System.out.println("+-+-+-+-+-+-+-+-+-+");          
            // se recorre cada celda de cada fila
            for (int j = 0; j < tablero.getTablero()[0].length; j++) {
                Ficha ficha = tablero.getTablero()[i][j];
                if (tipo.equalsIgnoreCase("NORMAL")) {
                    System.out.print("|");
                    if (!" ".equals(ficha.getNro())) 
                        if (ficha.getColor().equalsIgnoreCase("AZUL"))                      
                            System.out.print(Ficha.AZUL + ficha.getNro() + Ficha.NEGRO);
                        else
                            System.out.print(Ficha.ROJO + ficha.getNro() + Ficha.NEGRO);
                    else
                        System.out.print(" ");                    
                } else if (tipo.equalsIgnoreCase("REDUCIDO")) {
                    System.out.print(" ");
                    if (!" ".equals(ficha.getNro()))
                        if (ficha.getColor().equalsIgnoreCase("AZUL"))                      
                            System.out.print(Ficha.AZUL + ficha.getNro() + Ficha.NEGRO);
                        else
                            System.out.print(Ficha.ROJO + ficha.getNro() + Ficha.NEGRO);
                    else
                        System.out.print("-");                    
                }                
            }
            if (tipo.equalsIgnoreCase("NORMAL"))
                    System.out.print("|\n");
                            
                else
                    System.out.print(" \n");
        }
        // borde inferior
        if (tipo.equalsIgnoreCase("NORMAL"))
            System.out.println("+-+-+-+-+-+-+-+-+-+\n");
        else
            System.out.println("");
    }
    
    public static void mostrarPosiblesMovimientos(ArrayList<Integer> lista) {
        String posiblesMovimientos = "";
        if (!lista.isEmpty()) {
            for (Integer i : lista) {
                posiblesMovimientos += String.valueOf(i) + ", ";
            }
        posiblesMovimientos = posiblesMovimientos.substring(0,(posiblesMovimientos.length()-2));
        }
        if (!posiblesMovimientos.equals(""))
            System.out.print(Ficha.VERDE + "\nPosibles movimientos: " + posiblesMovimientos + Ficha.NEGRO +"\n");   
    }
    
    public static void comenzarPartida() { 
        Scanner lector = new Scanner(System.in);
        sistema.setTerminoPartida(false);
        ArrayList<Integer> posiblesMovimientos;
        int cantidadMovimientosTotales = 0;
        System.out.println("\n************************************************************");
        System.out.println("                          A JUGAR!                          ");
        System.out.println("************************************************************");
        mostrarTablero(sistema.getTablero());
        while (!sistema.isTerminoPartida()) {
            try {                
                System.out.print(Ficha.VERDE + "Turno de " + sistema.getTurno().getAlias() + Ficha.NEGRO +": ");
                String movimiento = lector.nextLine();
                boolean opcion = sistema.validarOpcion(movimiento, sistema.getTurno(), null);
                // se valida que la opción ingresada sea válida
                if (opcion) {
                    // se valida que sea un movimiento y no una opcion de juego (VERR, VERN, X)
                    if (movimiento.length() == 2) {
                        posiblesMovimientos = sistema.realizarJugada(movimiento, sistema.getTurno());
                        cantidadMovimientosTotales++;
                        sistema.validarFinalDePartida(cantidadMovimientosTotales);
                        if (sistema.isTerminoPartida())
                            break;
                        mostrarTablero(sistema.getTablero());
                        mostrarPosiblesMovimientos(posiblesMovimientos);
                        while (!posiblesMovimientos.isEmpty()) {
                            System.out.print(Ficha.VERDE + "Turno de " + sistema.getTurno().getAlias() + Ficha.NEGRO +": ");
                            movimiento = lector.nextLine();
                            opcion = sistema.validarOpcion(movimiento, sistema.getTurno(), posiblesMovimientos);
                            if (opcion) {
                                if (movimiento.length() == 2) {
                                    posiblesMovimientos = sistema.realizarJugada(movimiento, sistema.getTurno());
                                    cantidadMovimientosTotales++;
                                    sistema.validarFinalDePartida(cantidadMovimientosTotales);
                                    if (sistema.isTerminoPartida())
                                        break;
                                    mostrarTablero(sistema.getTablero());
                                    mostrarPosiblesMovimientos(posiblesMovimientos);
                                } else if (movimiento.equals("VERR") || movimiento.equals("VERN"))
                                    mostrarTablero(sistema.getTablero());                                
                            } else 
                                System.out.print(Ficha.ROJO + "Movimiento no válido" + Ficha.NEGRO + "\n"); 
                        }
                        sistema.cambiarTurno();
                    } else if (movimiento.equals("VERR") || movimiento.equals("VERN"))
                        mostrarTablero(sistema.getTablero());
                } else 
                    System.out.print(Ficha.ROJO + "Movimiento no válido" + Ficha.NEGRO + "\n");                 
            } catch (Exception e) {
                System.out.print(Ficha.ROJO + e.getMessage() + Ficha.NEGRO + "\n"); 
            }
        }        
    }
}