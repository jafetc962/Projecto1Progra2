/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import igu.MenuPrincipal;
import igu.Menu_Inicial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public final class Xiangqui extends JFrame {
    
//--TABLEROOOOOOOOOOOOOOOOOOOOOOOOOOO----------------------------------------------------------------------------------------------------------------------------------------
    private JButton[][] arregloBotones = new JButton[10][9]; 
    private Fichas[][] TableroP = new Fichas[10][9]; 
    Menu_Inicial mi;
    private String RutaI = "/images/";
    private Logs Logged;
    private Logs rival;
    private Equipo TActual = Equipo.ROJO; 
    private JLabel mostrarTurno;
    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
    private JTextArea piezasComidas;
    private JTextArea piezasComidas2;
     private Logs manejarLg;
    
    
    
    
    
    
    public Xiangqui(Logs jugadorLogueado, Logs oponente,Menu_Inicial mi) {
        this.mi=mi;
        this.Logged = jugadorLogueado;
        this.rival = oponente;
        setTitle("Tablero");
        setSize(1323, 745);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Panel principal para el tablero
        JPanel panelTablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(0, getHeight() / 2 - 2, getWidth(), 4); 
            }
        };
        panelTablero.setLayout(new GridLayout(10, 9, 5, 5)); 
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                arregloBotones[fila][columna] = new JButton();
                if (fila < 5) {
                    arregloBotones[fila][columna].setBackground(Color.WHITE); 
                } else {
                    arregloBotones[fila][columna].setBackground(new Color(255, 248, 220));
                }
                
                if ((fila >= 0 && fila <= 2 && columna >= 3 && columna <= 5)
                        || (fila >= 7 && fila <= 9 && columna >= 3 && columna <= 5)) {
                    arregloBotones[fila][columna].setBackground(Color.WHITE);
                }
                
                fAsign(fila, columna);
                
                final int f = fila; 
                final int c = columna;
                arregloBotones[fila][columna].addActionListener(e -> Clickboton(f, c));
                
                panelTablero.add(arregloBotones[fila][columna]);
            }
        }
        
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new GridLayout(2, 1, 0, 0));
        panelJugadores.setPreferredSize(new Dimension(300, 600));
       
        JPanel panelPiezasComidas1 = new JPanel(new BorderLayout());
        JPanel panelPiezasComidas2 = new JPanel(new BorderLayout());
        
        JLabel nombreJugador1 = new JLabel("Piezas comidas Jugador " + jugadorLogueado.sendLogged(), JLabel.CENTER);
        JLabel nombreJugador2 = new JLabel("Piezas comidas Jugador " + oponente.sendPlayer("negro"), JLabel.CENTER);
        piezasComidas = new JTextArea(10, 20);
        piezasComidas.setEditable(false);
        piezasComidas2 = new JTextArea(10, 20);
        piezasComidas2.setEditable(false);
        
        panelPiezasComidas1.add(nombreJugador1, BorderLayout.NORTH);
        panelPiezasComidas1.add(new JScrollPane(piezasComidas), BorderLayout.CENTER);
        panelPiezasComidas2.add(nombreJugador2, BorderLayout.NORTH);
        panelPiezasComidas2.add(new JScrollPane(piezasComidas2), BorderLayout.CENTER);
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        panelOpciones.setPreferredSize(new Dimension(200, 600));
        mostrarTurno = new JLabel("Turno Jugador "+TActual+": "+mi.lg.sendLogged());
        panelOpciones.add(mostrarTurno);
        JButton abandonarButton = new JButton("Abandonar Partida");
        abandonarButton.setBackground(Color.RED);
        abandonarButton.setForeground(Color.WHITE);
        abandonarButton.setPreferredSize(new Dimension(150, 30));  
        abandonarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que deseas abandonar la partida?", "Confirmar Abandono", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    Rendirse();
                }
            }
        });
        
        panelOpciones.add(abandonarButton);
        add(panelOpciones, BorderLayout.WEST);
        panelJugadores.add(panelPiezasComidas1, BorderLayout.NORTH);
        panelJugadores.add(panelPiezasComidas2, BorderLayout.SOUTH);
        add(panelJugadores, BorderLayout.EAST);
        add(panelTablero, BorderLayout.CENTER);
        setVisible(true);
    }
    
    
    private final void Clickboton(int fila, int columna) {
        if (filaSeleccionada == -1 && columnaSeleccionada == -1) {
            if (TableroP[fila][columna] != null && TableroP[fila][columna].getBando() == TActual) {
                filaSeleccionada = fila;
                columnaSeleccionada = columna;
                colorMov(fila, columna);
            }
        } 
        else {
            moveF(filaSeleccionada, columnaSeleccionada, fila, columna);
            
            TabNormalidad();
            filaSeleccionada = -1;
            columnaSeleccionada = -1;
        }
    }
    
    private final void TabNormalidad() {
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                if (fila < 5) {
                    arregloBotones[fila][columna].setBackground(Color.WHITE);
                } else {
                    arregloBotones[fila][columna].setBackground(new Color(255, 248, 220));
                }
                if ((fila >= 0 && fila <= 2 && columna >= 3 && columna <= 5)
                        || (fila >= 7 && fila <= 9 && columna >= 3 && columna <= 5)) {
                    arregloBotones[fila][columna].setBackground(Color.white);
                }
            }
        }
    }
    private final void colorMov(int filaOrigen, int columOrigen) {
        Fichas pieza = TableroP[filaOrigen][columOrigen];
        if (pieza != null) {
            System.out.println("Resaltando movimientos validos para la pieza: " + pieza.getClass().getSimpleName());
            for (int fila = 0; fila < 10; fila++) {
                for (int columna = 0; columna < 9; columna++) {
                     if (pieza.fichasMovements(filaOrigen, columOrigen, fila, columna)) {
                        arregloBotones[fila][columna].setBackground(Color.BLUE); 
                    }
                }
            }
        }
    }
    private final void fAsign(int fila, int columna) {
        if (fila == 0 || fila == 9) {
            if (columna == 0 || columna == 8) {
                TableroP[fila][columna] = new Torre1(fila == 0 ? Equipo.NEGRO : Equipo.ROJO, this);
            } else if (columna == 1 || columna == 7) {
                TableroP[fila][columna] = new Caballo1(fila == 0 ? Equipo.NEGRO : Equipo.ROJO, this);
            } else if (columna == 2 || columna == 6) {
                TableroP[fila][columna] = new Elefante1(fila == 0 ? Equipo.NEGRO : Equipo.ROJO, this);
            } else if (columna == 3 || columna == 5) {
                TableroP[fila][columna] = new Consejero1(fila == 0 ? Equipo.NEGRO : Equipo.ROJO, this);
            } else if (columna == 4) {
                TableroP[fila][columna] = new Rey1(fila == 0 ? Equipo.NEGRO : Equipo.ROJO, this);
            }
        }
        if ((fila == 2 && (columna == 1 || columna == 7))
                || (fila == 7 && (columna == 1 || columna == 7))) {
            TableroP[fila][columna] = new Cañon1(fila == 2 ? Equipo.NEGRO : Equipo.ROJO, this);
        }
        if ((fila == 3 && columna % 2 == 0) || (fila == 6 && columna % 2 == 0)) {
            TableroP[fila][columna] = new Peon1(fila == 3 ? Equipo.NEGRO : Equipo.ROJO, this);
        }
        if (TableroP[fila][columna] != null) {
            String imagen = pedirImage(TableroP[fila][columna]);
            arregloBotones[fila][columna].setIcon(new ImageIcon(getClass().getResource(RutaI + imagen)));
        }
    }
    private final String pedirImage(Fichas pieza) {
        if (pieza instanceof Torre1) {
            return pieza.getBando() == Equipo.ROJO ? "CarroRojo.png" : "CarroNegro.png";
        }
        if (pieza instanceof Caballo1) {
            return pieza.getBando() == Equipo.ROJO ? "CaballoRojo.png" : "CaballoNegro.png";
        }
        if (pieza instanceof Elefante1) {
            return pieza.getBando() == Equipo.ROJO ? "ElefanteRojo.png" : "ElefanteNegro.png";
        }
        if (pieza instanceof Consejero1) {
            return pieza.getBando() == Equipo.ROJO ? "OficialRojo.png" : "OficialNegro.png";
        }
        if (pieza instanceof Cañon1) {
            return pieza.getBando() == Equipo.ROJO ? "CañonRojo.png" : "CañonNegro.png";
        }
        if (pieza instanceof Peon1) {
            return pieza.getBando() == Equipo.ROJO ? "SoldadoRojo.png" : "SoldadoNegro.png";
        }
        if (pieza instanceof Rey1) {
            return pieza.getBando() == Equipo.ROJO ? "ReyRojo.png" : "ReyNegro.png";
        }
        return "";
    }
    public final boolean hayPieza(int fila, int columna) {
        return TableroP[fila][columna] != null;
    }
    
    public Fichas buscarF(int fila, int columna) {
        if (fila >= 0 && fila < TableroP.length && columna >= 0 && columna < TableroP[0].length) {
            return TableroP[fila][columna];
        }
        return null; 
    }
   
    
    public final int obtainBando(Equipo bando) {
        for (int fila = 0; fila < 10; fila++) {
            for (int col = 0; col < 9; col++) {
                Fichas pieza = TableroP[fila][col];
                if (pieza instanceof Rey1 && pieza.getBando() == bando) {
                    return fila;
                }
            }
        }
        return -1; 
    }
    
    private final void Rendirse(){
        
        
                String ganador = TActual == Equipo.ROJO ? rival.sendPlayer("negro") : Logged.sendLogged();
        if (TActual == Equipo.ROJO) {
            Logged.BuscarUsuario(Logged.sendLogged(),0).setPuntos(3);
            mi.lg.GuardarPartida(Logged.sendLogged(), mi.lg.sendPlayer("negro"), ganador);
        } else {
            rival.BuscarUsuario(rival.sendPlayer("negro"),0).setPuntos(3);
            mi.lg.GuardarPartida(Logged.sendLogged(), mi.lg.sendPlayer("negro"), ganador);
        }
        JOptionPane.showMessageDialog(this, "El jugador " + (TActual == Equipo.ROJO ? Logged.sendLogged() : rival.sendPlayer("negro")) + " ha abandonado la partida, Felicidades "+ganador + " has ganado 3 puntos ", "Partida Abandonada", JOptionPane.INFORMATION_MESSAGE);
        new MenuPrincipal(mi).setVisible(true);
        dispose();
        
    }
    
    public final boolean rNpV() {
        int filaReyRojo = -1;
        int columnaReyRojo = -1;
        int filaReyNegro = -1;
        int columnaReyNegro = -1;

        
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                Fichas pieza = TableroP[fila][columna];
                if (pieza instanceof Rey1) {
                    if (pieza.getBando() == Equipo.ROJO) {
                        filaReyRojo = fila;
                        columnaReyRojo = columna;
                    } else if (pieza.getBando() == Equipo.NEGRO) {
                        filaReyNegro = fila;
                        columnaReyNegro = columna;
                    }
                }
            }
        }

       
        if (columnaReyRojo == columnaReyNegro) {
            int minFila = Math.min(filaReyRojo, filaReyNegro);
            int maxFila = Math.max(filaReyRojo, filaReyNegro);

            
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (TableroP[fila][columnaReyRojo] != null) {
                    return true;
                }
            }
            return false; 
        }
        return true;
    }

    private final void Victoria() {
        String ganador = (TActual == Equipo.NEGRO) ? rival.sendPlayer("negro") : Logged.sendLogged();
        mi.lg.GuardarPartida(Logged.sendLogged(), mi.lg.sendPlayer("negro"), ganador);
        JOptionPane.showMessageDialog(this, "¡ " + ganador + " HA GANADO LA PARTIDA +3 PUNTOS!", "JAQUE MATE", JOptionPane.INFORMATION_MESSAGE);

        if (TActual == Equipo.ROJO) {
            rival.BuscarUsuario(rival.sendPlayer("negro"),0).setPuntos(3);
        } else {
            Logged.BuscarUsuario(Logged.sendLogged(),0).setPuntos(3);
        }
        
        new MenuPrincipal(mi).setVisible(true);
        this.dispose();
    }
    public final void moveF(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        

        Fichas pieza = TableroP[filaOrigen][columOrigen];
        Fichas piezaCapturada = TableroP[filaDestino][columDestino];

        if (pieza != null && pieza.fichasMovements(filaOrigen, columOrigen, filaDestino, columDestino)) {
            TableroP[filaDestino][columDestino] = pieza;
            TableroP[filaOrigen][columOrigen] = null;

            if (piezaCapturada instanceof Rey1) {
                Victoria();
                return; 
            }

           
            if (!rNpV()) {
                TableroP[filaOrigen][columOrigen] = pieza;
                TableroP[filaDestino][columDestino] = piezaCapturada;
                JOptionPane.showMessageDialog(this, "Movimiento invalido: los generales no pueden verse en la misma fila.", "Movimiento no permitido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            if (piezaCapturada != null) {
                if (TActual == Equipo.ROJO) {
                    piezasComidas.append(Logged.sendLogged() + " ha capturado la pieza " + piezaCapturada.getClass().getSimpleName() + "\n");
                } else {
                    piezasComidas2.append(rival.sendPlayer("negro") + " ha capturado la pieza " + piezaCapturada.getClass().getSimpleName() + "\n");
                }
            }

            String imagen = pedirImage(pieza);
            arregloBotones[filaDestino][columDestino].setIcon(new ImageIcon(getClass().getResource(RutaI + imagen)));
            arregloBotones[filaOrigen][columOrigen].setIcon(null);

            String mensajeMovimiento = (TActual == Equipo.ROJO ? Logged.sendLogged() : rival.sendPlayer("negro"))
                    + " ha movido la pieza " + pieza.getClass().getSimpleName()
                    + " a la casilla (" + filaDestino + ", " + columDestino + ")\n";

            if (TActual == Equipo.ROJO) {
                piezasComidas.append(mensajeMovimiento);
            } else {
                piezasComidas2.append(mensajeMovimiento);
            }

            
         
            TActual = (TActual == Equipo.ROJO) ? Equipo.NEGRO : Equipo.ROJO;
            mostrarTurno.setText("Turno Jugador " + (TActual == Equipo.ROJO ? "ROJO: " + Logged.sendLogged() : "NEGRO: " + rival.sendPlayer("negro")));
        }
    }
}