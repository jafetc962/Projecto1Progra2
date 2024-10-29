/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author HP
 */
public final class ManejadorPiezasXiangqi extends ManejadorTableroXiangqi {

    public String[][] tablero;

    // Constructor para inicializar el tablero
    public ManejadorPiezasXiangqi() {
        super();
        tablero = new String[10][9]; // Inicializa el tablero con las dimensiones correspondientes
        inicializarPosiciones(); // Llama a inicializarPosiciones para colocar las piezas
    }

    public void inicializarPosiciones() {
        super.inicializarPosiciones();
        mostrarTablero(); // Muestra el tablero con las posiciones iniciales
    }
    
    public void mostrarTablero() {
        if (tablero == null) {
        System.err.println("El tablero no está inicializado.");
        return; // Salir del método si el tablero es nulo
    }
        
        System.out.println("Tablero de Xiangqi:");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                String pieza;
                if (tablero[i][j] != null) {
                    pieza = tablero[i][j]; // Si hay una pieza, usar su nombre
                } else {
                    pieza = "⚪"; // Usar un símbolo para espacios vacíos
                }
                System.out.printf("%-20s", pieza); // Alinear la salida
            }
            System.out.println(); // Nueva línea después de cada fila
        }
        System.out.println(); // Espacio adicional después de mostrar el tablero
    }

    @Override
    public boolean moverPieza(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino) {
        // Lógica específica para manejar el movimiento de acuerdo con la pieza
        String pieza = tablero[filaInicio][columnaInicio];

        // Verificar si la pieza existe y es del jugador actual
        if (pieza == null || !pieza.startsWith(turnoActual)) {
            return false; // No hay pieza o no es del jugador actual
        }

        // Determinar el tipo de pieza y aplicar las restricciones
        switch (pieza.split(" ")[0]) {
            case "General":
                return moverGeneral(filaInicio, columnaInicio, filaDestino, columnaDestino);
            case "Soldado":
                return moverSoldado(filaInicio, columnaInicio, filaDestino, columnaDestino);
            // Agregar casos para otras piezas como "Caballo", "Carro", "Cañón", etc.
            default:
                return false; // Pieza desconocida
        }
    }

    private boolean moverGeneral(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino) {
        // Restricciones de movimiento del General
        // Puede moverse una celda en cualquier dirección, pero debe estar en la zona del General
        if ((filaDestino == filaInicio && Math.abs(columnaDestino - columnaInicio) == 1) || 
            (columnaDestino == columnaInicio && Math.abs(filaDestino - filaInicio) == 1)) {
            return filaDestino >= 0 && filaDestino <= 2 && columnaDestino >= 3 && columnaDestino <= 5; // Restricciones de la zona del General
        }
        return false; // Movimiento inválido
    }

    private boolean moverSoldado(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino) {
        // Restricciones de movimiento del Soldado
        if (filaDestino == filaInicio + 1 && columnaDestino == columnaInicio) {
            return true; // Puede avanzar una celda hacia adelante
        }
        // Si el Soldado ya ha cruzado el río, puede moverse lateralmente
        if (filaInicio >= 5) {
            return (filaDestino == filaInicio && Math.abs(columnaDestino - columnaInicio) == 1);
        }
        return false; // Movimiento inválido
    }

    // Puedes agregar más métodos para otras piezas aquí

    public static void main(String[] args) {
        ManejadorPiezasXiangqi manejador = new ManejadorPiezasXiangqi();
        // Puedes probar la lógica del juego aquí
        manejador.mostrarTablero(); // Muestra el tablero inicial
    }

    public String[][] getTablero() {
        // Retorna el tablero actual
        return tablero;
    }
}

