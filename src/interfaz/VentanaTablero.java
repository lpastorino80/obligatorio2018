/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Ficha;
import dominio.Jugador;
import dominio.Movimiento;
import dominio.Sistema;
import dominio.Tablero;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author Caro
 */
public class VentanaTablero extends javax.swing.JFrame implements Observer {

    private Sistema sistema;
    private Ficha[][] tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private boolean contadorMovimientos = false;
    private String fichaAnterior = "";
    private int cantidadMovimientosTotales = 0;
    private ArrayList<String> posiblesJugadas = new ArrayList<>();
    private ArrayList<Integer> posiblesMovimientos = new ArrayList<>();

    public int getCantidadMovimientosTotales() {
        return cantidadMovimientosTotales;
    }

    public void setCantidadMovimientosTotales(int cantidadMovimientosTotales) {
        this.cantidadMovimientosTotales = cantidadMovimientosTotales;
    }

    public boolean getContadorMovimientos() {
        return contadorMovimientos;
    }

    public void setContadorMovimientos(boolean contadorMovimientos) {
        this.contadorMovimientos = contadorMovimientos;
    }

    public VentanaTablero() {
        initComponents();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable obs, Object obj) {
        JOptionPane.showMessageDialog(this, "Se terminó la partida", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        Index index = new Index(sistema);
        index.setVisible(true);
        this.dispose();
    }

    public VentanaTablero(Sistema sistema) {
        initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.sistema = sistema;
        sistema.addObserver(this);
        jugador1 = sistema.getJugador1();
        jugador2 = sistema.getJugador2();
        actualizarTurno();
        tablero = sistema.getTablero().getTablero();
        //Tamaño por defecto de la ventana (JFrame) al abrirlo
        this.setSize(new Dimension(600, 600));
        panelJuego.setLayout(new GridLayout(8, 9));
        int largo = tablero[0].length - 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                JButton jButton = new JButton();
                jButton.addActionListener(new VentanaTablero.ListenerBoton(i, j));
                Ficha ficha = new Ficha(" ", " ", jButton);
                if (i == 0) {
                    if (j != 0) {
                        ficha = new Ficha(String.valueOf(j), "AZUL", jugador2, jButton);
                        tablero[i][j] = ficha;
                    } else {
                        tablero[i][j] = ficha;
                    }
                } else if (i == tablero.length - 1) {
                    if (j != tablero[0].length - 1) {
                        ficha = new Ficha(String.valueOf(largo--), "ROJO", jugador1, jButton);
                        tablero[i][j] = ficha;
                    } else {
                        tablero[i][j] = ficha;
                    }
                } else {
                    tablero[i][j] = ficha;
                }
                ficha.getBoton().setForeground(Color.WHITE);
                ficha.getBoton().setText(ficha.getNro());
                switch (ficha.getColor()) {
                    case "ROJO":
                        jButton.setBackground(Color.RED);
                        break;
                    case "AZUL":
                        jButton.setBackground(Color.BLUE);
                        break;
                    default:
                        jButton.setBackground(Color.WHITE);
                        break;
                }
                panelJuego.add(ficha.getBoton());
            }
        }
    }

    //Método que actualiza el tablero en cada jugada
    public void mostrarTablero() {
        panelJuego.removeAll();
//        panelJuego.setLayout(new GridLayout(8, 9));
//        JButton jButton = new JButton();
//        jButton.addActionListener(new VentanaTablero.ListenerBoton(0, 0));
//        Ficha ficha = new Ficha(" ", " ", jButton);        
//        panelJuego.add(ficha.getBoton());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                Ficha ficha = tablero[i][j];
                Color fondo;
                String texto = " ";
                if (ficha.getColor() != " ") {
                    fondo = (ficha.getColor() == "ROJO") ? Color.RED : Color.BLUE;
                    texto = ficha.getNro();
                    ficha.getBoton().setBackground(fondo);
                    ficha.getBoton().setText(texto);
                } else {
                    ficha.getBoton().setBackground(null);
                    ficha.getBoton().setText(texto);
                }
                panelJuego.add(ficha.getBoton());
            }
        }
//        ficha = new Ficha(" ", " ", jButton);
//        panelJuego.add(ficha.getBoton());
        this.validate();
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelJuego = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbljugador = new javax.swing.JLabel();
        lblPosiblesMovimientos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panelJuego.setBackground(new java.awt.Color(204, 204, 204));
        panelJuego.setName("panelJuego"); // NOI18N
        panelJuego.setLayout(null);
        getContentPane().add(panelJuego);
        panelJuego.setBounds(80, 100, 450, 400);

        jButton1.setText("Terminar turno");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(80, 510, 450, 40);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Turno de: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(180, 10, 80, 40);

        lbljugador.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        lbljugador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(lbljugador);
        lbljugador.setBounds(270, 10, 240, 40);

        lblPosiblesMovimientos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(lblPosiblesMovimientos);
        lblPosiblesMovimientos.setBounds(80, 60, 450, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!posiblesMovimientos.isEmpty()) {
            posiblesMovimientos.clear();
            borrarPosiblesMovimientos();
            fichaAnterior = "";
            sistema.cambiarTurno();
            actualizarTurno();
        } else 
            JOptionPane.showMessageDialog(this, "No se puede pasar el turno aún", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaTablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPosiblesMovimientos;
    private javax.swing.JLabel lbljugador;
    private javax.swing.JPanel panelJuego;
    // End of variables declaration//GEN-END:variables

    private class ListenerBoton implements ActionListener {

        private int x;
        private int y;

        public ListenerBoton(int i, int j) {
            // en el constructor se almacena la fila y columna que se presionó
            x = i;
            y = j;
        }

        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método
            clickBoton(x, y);
        }
    }

    private void clickBoton(int fila, int columna) {
        if (!sistema.isTerminoPartida()) {
            boolean opcion;
            if (contadorMovimientos == false) {                
                if (this.posiblesMovimientos.isEmpty()) {
                    if (validarFichaJugador(fila, columna)) {
                        fichaAnterior = fila + "" + columna; 
                        contadorMovimientos = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Movimiento inválido", "Error", JOptionPane.ERROR_MESSAGE); 
                        fichaAnterior = "";
                    }
                } else {
                    Ficha fichaSeleccionada = tablero[fila][columna];
                    if (!this.posiblesMovimientos.contains(Integer.parseInt(fichaSeleccionada.getNro()))) {
                        JOptionPane.showMessageDialog(this, "Movimiento inválido", "Error", JOptionPane.ERROR_MESSAGE);
                        fichaAnterior = "";
                    } else {
                        if (validarFichaJugador(fila, columna)) {
                            fichaAnterior = fila + "" + columna;  
                            contadorMovimientos = true;
                            //this.posiblesMovimientos.clear();
                        } else {
                            JOptionPane.showMessageDialog(this, "Movimiento inválido", "Error", JOptionPane.ERROR_MESSAGE);  
                            fichaAnterior = "";
                        }
                    }
                }
            } else {
                contadorMovimientos = false;
                try {
                    String movimiento = construirMovimiento(fila, columna);
                    opcion = sistema.validarOpcion(movimiento, sistema.getTurno(), null);
                    if (opcion) {
                        Movimiento mov = new Movimiento(movimiento, sistema.getTurno());
                        sistema.getPartidaActual().agregarMovimiento(mov);
                        posiblesMovimientos = this.moverFicha(tablero[Integer.parseInt(fichaAnterior.substring(0,1))][Integer.parseInt(fichaAnterior.substring(1,2))], tablero[fila][columna], sistema.getTurno(),Integer.parseInt(fichaAnterior.substring(0,1)),Integer.parseInt(fichaAnterior.substring(1,2)),fila,columna);                        
                        this.cantidadMovimientosTotales++;
                        mostrarTablero();
                        sistema.validarFinalDePartida(cantidadMovimientosTotales);
                        if (this.posiblesMovimientos.isEmpty()) {                            
                            sistema.cambiarTurno();
                            actualizarTurno();
                            borrarPosiblesMovimientos();
                        }
                        else 
                            mostrarPosiblesMovimientos(posiblesMovimientos);
                    } else 
                        JOptionPane.showMessageDialog(this, "Movimiento inválido", "Error", JOptionPane.ERROR_MESSAGE);
                    fichaAnterior = ""; 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Movimiento inválido", "Error", JOptionPane.ERROR_MESSAGE);
                    fichaAnterior = "";                    
                }
            }
        } 
    }

    public ArrayList<Integer> moverFicha(Ficha fichaOrigen, Ficha fichaDestino, Jugador jugador, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) { 
        fichaDestino.setColor(fichaOrigen.getColor());
        fichaDestino.setNro(fichaOrigen.getNro());
        fichaDestino.setJugador(jugador);       
        fichaOrigen.setColor(" ");
        fichaOrigen.setNro(" ");
        fichaOrigen.setJugador(null);
        return sistema.posiblesMovimientos(fichaDestino, filaDestino, columnaDestino);
    }
    
    public void mostrarPosiblesMovimientos(ArrayList<Integer> lista) {
        String posiblesMovimientos = "";
        if (!lista.isEmpty()) {
            for (Integer i : lista) {
                posiblesMovimientos += String.valueOf(i) + ", ";
            }
        posiblesMovimientos = posiblesMovimientos.substring(0,(posiblesMovimientos.length()-2));
        }
        if (!posiblesMovimientos.equals(""))
            lblPosiblesMovimientos.setText("Posibles movimientos: " + posiblesMovimientos);  
    }
    
    public void borrarPosiblesMovimientos () {
        lblPosiblesMovimientos.setText("");
    }
    
    private String construirMovimiento(int fila, int columna) {
        Ficha ficha = tablero[fila][columna];
        String movimiento = null;
        int filaFichaAnterior = Integer.parseInt(this.fichaAnterior.substring(0, 1));
        int columnaFichaAnterior = Integer.parseInt(this.fichaAnterior.substring(1, 2));
        Ficha fichaAnterior = tablero[filaFichaAnterior][columnaFichaAnterior];
        int color = (fichaAnterior.getColor().equals("AZUL")) ? -1 : 1;        
        if (filaFichaAnterior - fila == color) {
            if(columnaFichaAnterior - columna == 1)
                movimiento = fichaAnterior.getNro() + "I";
            if(columnaFichaAnterior - columna == -1)
                movimiento = fichaAnterior.getNro() + "D";
            if(columnaFichaAnterior - columna == 0)
                movimiento = fichaAnterior.getNro() + "A";
        }
        return movimiento; 
    }

//    private void marcarPosiblesMovimientos(int fila, int columna) {
//        Ficha ficha = tablero[fila][columna];
//        Ficha posibleMovimiento;
//        int color = (ficha.getColor().equals("ROJO")) ? -1 : 1;
//        Border bordePosibleMovimiento = new LineBorder(Color.GREEN, 5);
//        String movimiento = ficha.getNro() + "A";
//        if (sistema.validarMovimiento(movimiento, sistema.getTurno())) {
//            posibleMovimiento = tablero[fila + color][columna];
//            //posibleMovimiento.getBoton().setBorder(bordePosibleMovimiento);
//            this.posiblesJugadas.add((fila + color) + "" + columna + "/" + movimiento);
//        }
//        movimiento = ficha.getNro() + "D";
//        if (sistema.validarMovimiento(movimiento, sistema.getTurno())) {
//            posibleMovimiento = tablero[fila + color][columna + 1];
//            //posibleMovimiento.getBoton().setBorder(bordePosibleMovimiento);
//            this.posiblesJugadas.add((fila + color) + "" + (columna + 1) + "/" + movimiento);
//        }
//        movimiento = ficha.getNro() + "I";
//        if (sistema.validarMovimiento(movimiento, sistema.getTurno())) {
//            posibleMovimiento = tablero[fila + color][columna - 1];
//            posibleMovimiento.getBoton().setBorder(bordePosibleMovimiento);
//            this.posiblesJugadas.add((fila + color) + "" + (columna - 1) + "/" + movimiento);
//        }
//    }
    private boolean validarFichaJugador(int fila, int columna) {
        Ficha fichaSeleccionada = tablero[fila][columna];
        boolean valido = false;
        if (fichaSeleccionada.getJugador() != null) {
            if (fichaSeleccionada.getJugador().equals(sistema.getTurno())) {
                valido = true;
            }
        }
        return valido;
    }

    public void actualizarTurno() {
        lbljugador.setText(sistema.getTurno().getAlias());
    }

    private void desmarcarPosiblesMovimientos() {
        try {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 9; j++) {
                    //System.out.println(tablero[i][j].getNro() + tablero[i][j].getColor());
                    tablero[i][j].getBoton().setBorder(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
