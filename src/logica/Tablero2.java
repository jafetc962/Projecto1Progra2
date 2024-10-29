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

public final class Tablero2 extends JFrame {
    
//--TABLEROOOOOOOOOOOOOOOOOOOOOOOOOOO----------------------------------------------------------------------------------------------------------------------------------------
    private JButton[][] botonesTablero = new JButton[10][9]; // Arreglo bidimensional de 10x9 para los botones
    private Pieza[][] piezasTablero = new Pieza[10][9]; // Arreglo bidimensional para las piezas
    Menu_Inicial mi;
    private String rutaImagenes = "/images/";
    private Logs jugadorLogueado;
    private Logs oponente;
    private Bando turnoActual = Bando.ROJO; // Turno inicial
    private JLabel turnoLabel;
    private JTextArea areaPiezasComidas1;
    private JTextArea areaPiezasComidas2;
     private Logs usermanager;
    // Variables para almacenar la posición de la pieza seleccionada
    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
    
    
    
    
    public Tablero2(Logs jugadorLogueado, Logs oponente,Menu_Inicial mi) {
        this.mi=mi;
        this.jugadorLogueado = jugadorLogueado;
        this.oponente = oponente;
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
                g.fillRect(0, getHeight() / 2 - 2, getWidth(), 4); // Dibujar el río
            }
        };
        panelTablero.setLayout(new GridLayout(10, 9, 5, 5)); // Tablero de 10x9
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Inicializar botones y agregar al tablero
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                botonesTablero[fila][columna] = new JButton();
                // Colores del tablero
                if (fila < 5) {
                    botonesTablero[fila][columna].setBackground(Color.WHITE); // Beige claro
                } else {
                    botonesTablero[fila][columna].setBackground(new Color(255, 248, 220)); // Blanco puro
                }
                // Diferenciar el castillo
                if ((fila >= 0 && fila <= 2 && columna >= 3 && columna <= 5)
                        || (fila >= 7 && fila <= 9 && columna >= 3 && columna <= 5)) {
                    botonesTablero[fila][columna].setBackground(Color.WHITE);
                }
                // Asignar las piezas en posiciones iniciales
                asignarPiezas(fila, columna);
                // Añadir el ActionListener para manejar los clics
                final int f = fila;  // Variables finales para usarlas dentro del ActionListener
                final int c = columna;
                botonesTablero[fila][columna].addActionListener(e -> manejarClick(f, c));
                // Añadir los botones al panel del tablero
                panelTablero.add(botonesTablero[fila][columna]);
            }
        }
        // Panel para los jugadores
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new GridLayout(2, 1, 0, 0));
        panelJugadores.setPreferredSize(new Dimension(300, 600));
        // Paneles para las piezas comidas
        JPanel panelPiezasComidas1 = new JPanel(new BorderLayout());
        JPanel panelPiezasComidas2 = new JPanel(new BorderLayout());
        // Crear y añadir los JLabel con los nombres de los jugadores
        JLabel nombreJugador1 = new JLabel("Piezas comidas Jugador " + jugadorLogueado.sendLogged(), JLabel.CENTER);
        JLabel nombreJugador2 = new JLabel("Piezas comidas Jugador " + oponente.sendPlayer("negro"), JLabel.CENTER);
        areaPiezasComidas1 = new JTextArea(10, 20);
        areaPiezasComidas1.setEditable(false);
        areaPiezasComidas2 = new JTextArea(10, 20);
        areaPiezasComidas2.setEditable(false);
        // Añadir los JLabel y JTextArea a sus respectivos paneles con scroll
        panelPiezasComidas1.add(nombreJugador1, BorderLayout.NORTH);
        panelPiezasComidas1.add(new JScrollPane(areaPiezasComidas1), BorderLayout.CENTER);
        panelPiezasComidas2.add(nombreJugador2, BorderLayout.NORTH);
        panelPiezasComidas2.add(new JScrollPane(areaPiezasComidas2), BorderLayout.CENTER);
        // Panel izquierdo para las opciones del juego (turnos, abandonar, etc.)
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Usamos FlowLayout para que respete el tamaño del botón
        panelOpciones.setPreferredSize(new Dimension(200, 600));
        turnoLabel = new JLabel("Turno Jugador "+turnoActual+": "+mi.lg.sendLogged());
        panelOpciones.add(turnoLabel);
        // Botón abandonar partida más pequeño
        JButton abandonarButton = new JButton("Abandonar Partida");
        abandonarButton.setBackground(Color.RED);
        abandonarButton.setForeground(Color.WHITE);
        abandonarButton.setPreferredSize(new Dimension(150, 30));  // Reducir el tamaño del botón
        
        abandonarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que deseas abandonar la partida?", "Confirmar Abandono", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    AbandonarPartida();
                }
            }
        });
        
        panelOpciones.add(abandonarButton);
        // Añadir el panel de opciones a la izquierda
        add(panelOpciones, BorderLayout.WEST);
        // Añadir los paneles de las piezas comidas a la derecha
        panelJugadores.add(panelPiezasComidas1, BorderLayout.NORTH);
        panelJugadores.add(panelPiezasComidas2, BorderLayout.SOUTH);
        // Añadir el tablero al centro y los paneles de jugadores a la derecha
        add(panelJugadores, BorderLayout.EAST);
        add(panelTablero, BorderLayout.CENTER);
        setVisible(true);
    }
    
    
    //aqui funcion para manejar el clic en un boton del tablero
    private final void manejarClick(int fila, int columna) {
        // Si no hay una pieza seleccionada, seleccionar la pieza
        if (filaSeleccionada == -1 && columnaSeleccionada == -1) {
            if (piezasTablero[fila][columna] != null && piezasTablero[fila][columna].getBando() == turnoActual) {
                filaSeleccionada = fila;
                columnaSeleccionada = columna;
                // Resaltar los posibles movimientos de la pieza seleccionada
                resaltarMovimientos(fila, columna);
            }
        } // Si ya hay una pieza seleccionada, intentar moverla
        else {
            moverPieza(filaSeleccionada, columnaSeleccionada, fila, columna);
            
            // Deseleccionar la pieza después de moverla
            restaurarColoresTablero();
            filaSeleccionada = -1;
            columnaSeleccionada = -1;
        }
    }
    //aqui una funcio para resaltar los movimientos validos de la pieza seleccionada
    private final void resaltarMovimientos(int filaOrigen, int columOrigen) {
        Pieza pieza = piezasTablero[filaOrigen][columOrigen];
        // Solo resaltar movimientos si la pieza no es nula
        if (pieza != null) {
            System.out.println("Resaltando movimientos validos para la pieza: " + pieza.getClass().getSimpleName());
            for (int fila = 0; fila < 10; fila++) {
                for (int columna = 0; columna < 9; columna++) {
                    // Solo mostrar los movimientos validos del Elefante
                    if (pieza.MovimientoPiezas(filaOrigen, columOrigen, fila, columna)) {
                        botonesTablero[fila][columna].setBackground(Color.BLUE); // Resaltar casilla en verde
                    }
                }
            }
        }
    }
    //aqui funcion para restaurar los colores originales del tablero
    private final void restaurarColoresTablero() {
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                if (fila < 5) {
                    botonesTablero[fila][columna].setBackground(Color.WHITE);
                } else {
                    botonesTablero[fila][columna].setBackground(new Color(255, 248, 220));
                }
                if ((fila >= 0 && fila <= 2 && columna >= 3 && columna <= 5)
                        || (fila >= 7 && fila <= 9 && columna >= 3 && columna <= 5)) {
                    botonesTablero[fila][columna].setBackground(Color.white);
                }
            }
        }
    }
    //aqui funcion para asignar las piezas en sus posiciones iniciales
    private final void asignarPiezas(int fila, int columna) {
        if (fila == 0 || fila == 9) {
            // Asignar las piezas principales
            if (columna == 0 || columna == 8) {
                piezasTablero[fila][columna] = new Torre1(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 1 || columna == 7) {
                piezasTablero[fila][columna] = new Caballo1(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 2 || columna == 6) {
                piezasTablero[fila][columna] = new Elefante1(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 3 || columna == 5) {
                piezasTablero[fila][columna] = new Consejero1(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 4) {
                piezasTablero[fila][columna] = new Rey1(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            }
        }
        // Asignar cañones
        if ((fila == 2 && (columna == 1 || columna == 7))
                || (fila == 7 && (columna == 1 || columna == 7))) {
            piezasTablero[fila][columna] = new Cañon1(fila == 2 ? Bando.NEGRO : Bando.ROJO, this);
        }
        // Asignar soldados
        if ((fila == 3 && columna % 2 == 0) || (fila == 6 && columna % 2 == 0)) {
            piezasTablero[fila][columna] = new Peon1(fila == 3 ? Bando.NEGRO : Bando.ROJO, this);
        }
        // Actualizar el boton con la imagen de la pieza
        if (piezasTablero[fila][columna] != null) {
            String imagen = obtenerImagenPieza(piezasTablero[fila][columna]);
            botonesTablero[fila][columna].setIcon(new ImageIcon(getClass().getResource(rutaImagenes + imagen)));
        }
    }
    //aqui funcion para obtener la imagen correspondiente a una pieza
    private final String obtenerImagenPieza(Pieza pieza) {
        if (pieza instanceof Torre1) {
            return pieza.getBando() == Bando.ROJO ? "CarroRojo.png" : "CarroNegro.png";
        }
        if (pieza instanceof Caballo1) {
            return pieza.getBando() == Bando.ROJO ? "CaballoRojo.png" : "CaballoNegro.png";
        }
        if (pieza instanceof Elefante1) {
            return pieza.getBando() == Bando.ROJO ? "ElefanteRojo.png" : "ElefanteNegro.png";
        }
        if (pieza instanceof Consejero1) {
            return pieza.getBando() == Bando.ROJO ? "OficialRojo.png" : "OficialNegro.png";
        }
        if (pieza instanceof Cañon1) {
            return pieza.getBando() == Bando.ROJO ? "CañonRojo.png" : "CañonNegro.png";
        }
        if (pieza instanceof Peon1) {
            return pieza.getBando() == Bando.ROJO ? "SoldadoRojo.png" : "SoldadoNegro.png";
        }
        if (pieza instanceof Rey1) {
            return pieza.getBando() == Bando.ROJO ? "ReyRojo.png" : "ReyNegro.png";
        }
        return "";
    }
    //aqui funcio para verificar si hay una pieza en una posición específica
    public final boolean hayPieza(int fila, int columna) {
        return piezasTablero[fila][columna] != null;
    }
    
    public Pieza obtenerPieza(int fila, int columna) {
        if (fila >= 0 && fila < piezasTablero.length && columna >= 0 && columna < piezasTablero[0].length) {
            return piezasTablero[fila][columna];
        }
        return null; // No hay pieza en esa posición
    }
    //aqui funcion para mover una pieza
   public final void moverPieza(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        

        Pieza pieza = piezasTablero[filaOrigen][columOrigen];
        Pieza piezaCapturada = piezasTablero[filaDestino][columDestino];

        if (pieza != null && pieza.MovimientoPiezas(filaOrigen, columOrigen, filaDestino, columDestino)) {
            // Realiza el movimiento temporalmente
            piezasTablero[filaDestino][columDestino] = pieza;
            piezasTablero[filaOrigen][columOrigen] = null;

            // Verificación de captura del rey (General)
            if (piezaCapturada instanceof Rey1) {
                anunciarVictoria();
                return;  // Termina el juego
            }

            // Resto de las validaciones
            if (!reyesNoPuedenVerse()) {
                piezasTablero[filaOrigen][columOrigen] = pieza;
                piezasTablero[filaDestino][columDestino] = piezaCapturada;
                JOptionPane.showMessageDialog(this, "Movimiento invalido: los generales no pueden verse en la misma fila.", "Movimiento no permitido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Actualiza la captura y la interfaz gráfica
            if (piezaCapturada != null) {
                if (turnoActual == Bando.ROJO) {
                    areaPiezasComidas1.append(jugadorLogueado.sendLogged() + " ha capturado la pieza " + piezaCapturada.getClass().getSimpleName() + "\n");
                } else {
                    areaPiezasComidas2.append(oponente.sendPlayer("negro") + " ha capturado la pieza " + piezaCapturada.getClass().getSimpleName() + "\n");
                }
            }

            String imagen = obtenerImagenPieza(pieza);
            botonesTablero[filaDestino][columDestino].setIcon(new ImageIcon(getClass().getResource(rutaImagenes + imagen)));
            botonesTablero[filaOrigen][columOrigen].setIcon(null);

            String mensajeMovimiento = (turnoActual == Bando.ROJO ? jugadorLogueado.sendLogged() : oponente.sendPlayer("negro"))
                    + " ha movido la pieza " + pieza.getClass().getSimpleName()
                    + " a la casilla (" + filaDestino + ", " + columDestino + ")\n";

            if (turnoActual == Bando.ROJO) {
                areaPiezasComidas1.append(mensajeMovimiento);
            } else {
                areaPiezasComidas2.append(mensajeMovimiento);
            }

            
            // Cambio de turno
            turnoActual = (turnoActual == Bando.ROJO) ? Bando.NEGRO : Bando.ROJO;
            turnoLabel.setText("Turno Jugador " + (turnoActual == Bando.ROJO ? "ROJO: " + jugadorLogueado.sendLogged() : "NEGRO: " + oponente.sendPlayer("negro")));
        }
    }
    //aqui funcion para obtener la fila donde esta el general de un bando
    public final int obtenerFilaGeneral(Bando bando) {
        for (int fila = 0; fila < 10; fila++) {
            for (int col = 0; col < 9; col++) {
                Pieza pieza = piezasTablero[fila][col];
                if (pieza instanceof Rey1 && pieza.getBando() == bando) {
                    return fila;
                }
            }
        }
        return -1; // No se encontro el general
    }
    
    private final void AbandonarPartida(){
        
        
                String ganador = turnoActual == Bando.ROJO ? oponente.sendPlayer("negro") : jugadorLogueado.sendLogged();
        if (turnoActual == Bando.ROJO) {
            jugadorLogueado.BuscarUsuario(jugadorLogueado.sendLogged(),0).setPuntos(3);
            mi.lg.GuardarPartida(jugadorLogueado.sendLogged(), mi.lg.sendPlayer("negro"), ganador);
        } else {
            oponente.BuscarUsuario(oponente.sendPlayer("negro"),0).setPuntos(3);
            mi.lg.GuardarPartida(jugadorLogueado.sendLogged(), mi.lg.sendPlayer("negro"), ganador);
        }
        JOptionPane.showMessageDialog(this, "El jugador " + (turnoActual == Bando.ROJO ? jugadorLogueado.sendLogged() : oponente.sendPlayer("negro")) + " ha abandonado la partida, Felicidades "+ganador + " has ganado 3 puntos ", "Partida Abandonada", JOptionPane.INFORMATION_MESSAGE);
        new MenuPrincipal(mi).setVisible(true);
        dispose();
        
    }
    
    public final boolean reyesNoPuedenVerse() {
        int filaReyRojo = -1;
        int columnaReyRojo = -1;
        int filaReyNegro = -1;
        int columnaReyNegro = -1;

        // Encuentra las posiciones de ambos reyes
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                Pieza pieza = piezasTablero[fila][columna];
                if (pieza instanceof Rey1) {
                    if (pieza.getBando() == Bando.ROJO) {
                        filaReyRojo = fila;
                        columnaReyRojo = columna;
                    } else if (pieza.getBando() == Bando.NEGRO) {
                        filaReyNegro = fila;
                        columnaReyNegro = columna;
                    }
                }
            }
        }

        //auqi Verifica si estan en la misma columna
        if (columnaReyRojo == columnaReyNegro) {
            int minFila = Math.min(filaReyRojo, filaReyNegro);
            int maxFila = Math.max(filaReyRojo, filaReyNegro);

            // Verifica si hay piezas entre los dos reyes en la misma columna
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (piezasTablero[fila][columnaReyRojo] != null) {
                    return true; // Hay una pieza entre los dos reyes, es una posición válida
                }
            }
            return false; // No hay ninguna pieza entre los reyes, posición inválida
        }
        return true; // No están en la misma columna, posición válida
    }

    private final void anunciarVictoria() {
        String ganador = (turnoActual == Bando.NEGRO) ? oponente.sendPlayer("negro") : jugadorLogueado.sendLogged();
        mi.lg.GuardarPartida(jugadorLogueado.sendLogged(), mi.lg.sendPlayer("negro"), ganador);
        JOptionPane.showMessageDialog(this, "¡ " + ganador + " HA GANADO LA PARTIDA +3 PUNTOS!", "JAQUE MATE", JOptionPane.INFORMATION_MESSAGE);

        if (turnoActual == Bando.ROJO) {
            oponente.BuscarUsuario(oponente.sendPlayer("negro"),0).setPuntos(3);
        } else {
            jugadorLogueado.BuscarUsuario(jugadorLogueado.sendLogged(),0).setPuntos(3);
        }
        
        new MenuPrincipal(mi).setVisible(true);
        this.dispose();
    }
}