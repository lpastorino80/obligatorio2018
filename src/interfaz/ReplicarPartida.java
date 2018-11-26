/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import dominio.Ficha;
import dominio.Jugador;
import dominio.Movimiento;
import dominio.Partida;
import dominio.Sistema;
import dominio.Tablero;
import dominio.TipoPartida;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

/**
 *
 * @author Caro
 */
public class ReplicarPartida extends javax.swing.JFrame {

    private Sistema sistema;
    private Ficha[][] tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador turno;
    private int contadorTurno = 0;
    private String fichaAnterior = "";
    private Partida partidaSel;
    private Movimiento mov;
    private int indiceMov = -1;

    private boolean contadorMovimientos = false;
    private int cantidadMovimientosTotales = 0;

    /**
     * Creates new form ReplicarPartida
     */
    public ReplicarPartida() {
        initComponents();
    }

    public ReplicarPartida(java.awt.Frame parent, boolean modal, Sistema sis) {
        initComponents();
        this.sistema = sis;

        //Datos de prueba
        //Acá debe venir una partida real
        Jugador jug1 = new Jugador("Caro", "Caro", 30);
        Jugador jug2 = new Jugador("Bruno", "Bruno", 27);
        TipoPartida tipo = TipoPartida.UNA_FICHA_AL_OTRO_LADO;
        ArrayList<Movimiento> movs = new ArrayList<>();
        movs.add(new Movimiento("8A", jug1));
        movs.add(new Movimiento("8D", jug1));
        movs.add(new Movimiento("1A", jug2));
        movs.add(new Movimiento("6I", jug1));
        Partida part = new Partida(new Date(), jug1, jug2, tipo, movs);
        this.sistema.getPartidas().add(part);

        this.setSize(new Dimension(650, 650));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        DefaultComboBoxModel opcion = new DefaultComboBoxModel();
        try {
            if (sistema.getPartidas().size() > 0) {
                for (Partida partida : sistema.getPartidas()) {
                    opcion.addElement(partida);
                }
                cmbPartidas.setModel(opcion);
                cmbPartidas.setSelectedIndex(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (sistema.getTablero() == null) {
            sistema.setTablero(new Tablero());
        }

        this.tablero = sistema.getTablero().getTablero();
        this.panelRepPartida.setLayout(new GridLayout(8, 9));
        int largo = tablero[0].length - 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                JButton jButton = new JButton();
                jButton.addActionListener(new ReplicarPartida.ListenerBotonRep(i, j));
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
                panelRepPartida.add(ficha.getBoton());
            }
        }
    }

//    public void actualizarTurno() {
//        lbljug.setText(sistema.getTurno().getAlias());
//    }
    
    //Método que actualiza el tablero en cada jugada
    public void mostrarTablero() {
        panelRepPartida.removeAll();
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
                panelRepPartida.add(ficha.getBoton());
            }
        }
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

        panelRepPartida = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cmbPartidas = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbljugador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        panelRepPartida.setBackground(new java.awt.Color(204, 204, 204));
        panelRepPartida.setLayout(null);
        getContentPane().add(panelRepPartida);
        panelRepPartida.setBounds(90, 120, 450, 450);

        jButton1.setText("Siguiente movimiento");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(90, 590, 210, 40);

        jButton2.setText("Retomar partida");
        getContentPane().add(jButton2);
        jButton2.setBounds(320, 590, 220, 40);

        cmbPartidas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPartidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPartidasActionPerformed(evt);
            }
        });
        getContentPane().add(cmbPartidas);
        cmbPartidas.setBounds(90, 20, 330, 30);

        jButton3.setText("Cargar partida");
        jButton3.setActionCommand("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(437, 20, 103, 30);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Turno de: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(270, 70, 80, 40);

        lbljugador.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        lbljugador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(lbljugador);
        lbljugador.setBounds(370, 70, 170, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        indiceMov++;              
        Movimiento mov = partidaSel.getMovimientos().get(indiceMov);        
        //sistema.realizarJugada(mov.getMovimiento(), mov.getJugador());
        sistema.getPartidaActual().agregarMovimiento(mov);
        this.moverFicha(tablero[Integer.parseInt(fichaAnterior.substring(0,1))][Integer.parseInt(fichaAnterior.substring(1,2))], tablero[fila][columna], sistema.getTurno(),Integer.parseInt(fichaAnterior.substring(0,1)),Integer.parseInt(fichaAnterior.substring(1,2)),fila,columna);                        
        this.cantidadMovimientosTotales++;
        mostrarTablero();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbPartidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPartidasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPartidasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cmbPartidas.setEnabled(false);
        partidaSel = (Partida) cmbPartidas.getSelectedItem();
        sistema.setJugador1(partidaSel.getJugador1());
        sistema.setJugador2(partidaSel.getJugador2());
        Partida partidaActual = null;
        if (partidaSel.getTipoPartida().equals(TipoPartida.CANTIDAD_MOVIMIENTOS)) {
            partidaActual = new Partida(partidaSel.getHora(), partidaSel.getJugador1(), partidaSel.getJugador2(), partidaSel.getTipoPartida(), new ArrayList<Movimiento>(), partidaSel.getCantidadMovimientos());
        } else {
            partidaActual = new Partida(partidaSel.getHora(), partidaSel.getJugador1(), partidaSel.getJugador2(), partidaSel.getTipoPartida(), new ArrayList<Movimiento>());
        }
        sistema.setPartidaActual(partidaActual);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ReplicarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReplicarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReplicarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReplicarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RepetirPartida dialog = new RepetirPartida(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbPartidas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbljugador;
    private javax.swing.JPanel panelRepPartida;
    // End of variables declaration//GEN-END:variables

    private class ListenerBotonRep implements ActionListener {

        private int x;
        private int y;

        public ListenerBotonRep(int i, int j) {
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
        
    }

    public void moverFicha(Ficha fichaOrigen, Ficha fichaDestino, Jugador jugador) {
        fichaDestino.setColor(fichaOrigen.getColor());
        fichaDestino.setNro(fichaOrigen.getNro());
        fichaDestino.setJugador(jugador);
        fichaOrigen.setColor(" ");
        fichaOrigen.setNro(" ");
        fichaOrigen.setJugador(null);
        //return sistema.posiblesMovimientos(fichaDestino, filaDestino, columnaDestino);
    }
    
    
}
