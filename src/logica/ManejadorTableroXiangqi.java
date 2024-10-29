/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author HP
 */
import java.net.URL;
import javax.swing.ImageIcon;

public class ManejadorTableroXiangqi extends TableroXiangqi {

    protected String[][] tablero;
    protected String turnoActual;
    protected boolean juegoActivo;
    protected ImageIcon[][] imagenes; // Arreglo para las imágenes

    public ManejadorTableroXiangqi() {
        // Inicializa el tablero
        super();
        this.tablero = new String[10][9]; // Inicializa el arreglo para el tablero
        this.imagenes = new ImageIcon[10][9];
        cargarImagenes();
        inicializarPosiciones();
        this.turnoActual = "Rojo"; 
        this.juegoActivo = true;
         // Inicializa el arreglo de imágenes
         // Llama a inicializarPosiciones para colocar las piezas
    }

    @Override
    public void inicializarPosiciones() {
        // Inicializar posiciones de las piezas Rojo
        tablero[0][4] = "General Rojo";  
        tablero[0][3] = "Consejero Rojo";  
        tablero[0][5] = "Consejero Rojo";  
        tablero[0][2] = "Elefante Rojo";  
        tablero[0][6] = "Elefante Rojo";  
        tablero[0][1] = "Caballo Rojo";  
        tablero[0][7] = "Caballo Rojo";  
        tablero[0][0] = "Carro Rojo";  
        tablero[0][8] = "Carro Rojo";  
        tablero[1][1] = "Cañón Rojo";  
        tablero[1][7] = "Cañón Rojo";  
        tablero[2][0] = "Soldado Rojo";  
        tablero[2][2] = "Soldado Rojo";  
        tablero[2][4] = "Soldado Rojo";  
        tablero[2][6] = "Soldado Rojo";  
        tablero[2][8] = "Soldado Rojo";  

        // Inicializar posiciones de las piezas Negro
        tablero[9][4] = "General Negro";  
        tablero[9][3] = "Consejero Negro";  
        tablero[9][5] = "Consejero Negro";  
        tablero[9][2] = "Elefante Negro";  
        tablero[9][6] = "Elefante Negro";  
        tablero[9][1] = "Caballo Negro";  
        tablero[9][7] = "Caballo Negro";  
        tablero[9][0] = "Carro Negro";  
        tablero[9][8] = "Carro Negro";  
        tablero[8][1] = "Cañón Negro";  
        tablero[8][7] = "Cañón Negro";  
        tablero[7][0] = "Soldado Negro";  
        tablero[7][2] = "Soldado Negro";  
        tablero[7][4] = "Soldado Negro";  
        tablero[7][6] = "Soldado Negro";  
        tablero[7][8] = "Soldado Negro";  

        // Cargar las imágenes de las piezas
        cargarImagenes();
    }

    public void cargarImagenes() {
        if (imagenes == null) {
        System.err.println("El arreglo de imágenes no está inicializado.");
        return; // Evitar que se ejecute el resto del método si imágenes es null
    }
        
        
        // Cargar imágenes de las piezas rojas
        imagenes[0][4] = cargarImagen("/images/GeneralRojo.png");
        imagenes[0][3] = cargarImagen("/images/OficialRojo.png");
        imagenes[0][5] = cargarImagen("/images/OficialRojo.png");
        imagenes[0][2] = cargarImagen("/images/ElefanteRojo.png");
        imagenes[0][6] = cargarImagen("/images/ElefanteRojo.png");
        imagenes[0][1] = cargarImagen("/images/CaballoRojo.png");
        imagenes[0][7] = cargarImagen("/images/CaballoRojo.png");
        imagenes[0][0] = cargarImagen("/images/CarroRojo.png");
        imagenes[0][8] = cargarImagen("/images/CarroRojo.png");
        imagenes[1][1] = cargarImagen("/images/CañonRojo.png");
        imagenes[1][7] = cargarImagen("/images/CañonRojo.png");
        imagenes[2][0] = cargarImagen("/images/SoldadoRojo.png");
        imagenes[2][2] = cargarImagen("/images/SoldadoRojo.png");
        imagenes[2][4] = cargarImagen("/images/SoldadoRojo.png");
        imagenes[2][6] = cargarImagen("/images/SoldadoRojo.png");
        imagenes[2][8] = cargarImagen("/images/SoldadoRojo.png");

        // Cargar imágenes de las piezas negras
        imagenes[9][4] = cargarImagen("/images/GeneralNegro.png");
        imagenes[9][3] = cargarImagen("/images/OficialNegro.png");
        imagenes[9][5] = cargarImagen("/images/OficialNegro.png");
        imagenes[9][2] = cargarImagen("/images/ElefanteNegro.png");
        imagenes[9][6] = cargarImagen("/images/ElefanteNegro.png");
        imagenes[9][1] = cargarImagen("/images/CaballoNegro.png");
        imagenes[9][7] = cargarImagen("/images/CaballoNegro.png");
        imagenes[9][0] = cargarImagen("/images/CarroNegro.png");
        imagenes[9][8] = cargarImagen("/images/CarroNegro.png");
        imagenes[8][1] = cargarImagen("/images/CañonNegro.png");
        imagenes[8][7] = cargarImagen("/images/CañonNegro.png");
        imagenes[7][0] = cargarImagen("/images/SoldadoNegro.png");
        imagenes[7][2] = cargarImagen("/images/SoldadoNegro.png");
        imagenes[7][4] = cargarImagen("/images/SoldadoNegro.png");
        imagenes[7][6] = cargarImagen("/images/SoldadoNegro.png");
        imagenes[7][8] = cargarImagen("/images/SoldadoNegro.png");
    }

    private ImageIcon cargarImagen(String ruta) {
        URL resource = getClass().getResource(ruta);
        if (resource != null) {
            return new ImageIcon(resource);
        } else {
            System.err.println("No se pudo cargar la imagen: " + ruta);
            return null; // O podrías lanzar una excepción
        }
    }
    
    public ImageIcon getImagenPieza(String pieza) {
        switch (pieza) {
            case "GeneralRojo": return cargarImagen("/images/GeneralRojo.png");
            case "OficialRojo": return cargarImagen("/images/OficialRojo.png");
            case "ElefanteRojo": return cargarImagen("/images/ElefanteRojo.png");
            case "CaballoRojo": return cargarImagen("/images/CaballoRojo.png");
            case "CarroRojo": return cargarImagen("/images/CarroRojo.png");
            case "CañonRojo": return cargarImagen("/images/CañonRojo.png");
            case "SoldadoRojo": return cargarImagen("/images/SoldadoRojo.png");

            case "GeneralNegro": return cargarImagen("/images/GeneralNegro.png");
            case "OficialNegro": return cargarImagen("/images/OficialNegro.png");
            case "ElefanteNegro": return cargarImagen("/images/ElefanteNegro.png");
            case "CaballoNegro": return cargarImagen("/images/CaballoNegro.png");
            case "CarroNegro": return cargarImagen("/images/CarroNegro.png");
            case "CañonNegro": return cargarImagen("/images/CañonNegro.png");
            case "SoldadoNegro": return cargarImagen("/images/SoldadoNegro.png");

            default: return null;
        }
    }
    
    public String getPieza(int fila, int columna) {
        // Devuelve el nombre de la pieza en una posición específica
        return (imagenes[fila][columna] != null) ? "PiezaExistente" : null; 
    }

    @Override
    public boolean moverPieza(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino) {
        // Verificar si el tablero está inicializado
        if (tablero == null) {
            System.err.println("El tablero no está inicializado.");
            return false; 
        }

        if (!juegoActivo) {
            System.out.println("El juego no está activo.");
            return false; 
        }

        if (esMovimientoValido(filaInicio, columnaInicio, filaDestino, columnaDestino)) {
            actualizarPosicion(filaInicio, columnaInicio, filaDestino, columnaDestino);
            cambiarTurno(); 
            return true;
        }
        return false;
    }

    @Override
    protected boolean esMovimientoValido(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino) {
        // Verificar si el tablero está inicializado
        if (tablero == null) {
            System.err.println("El tablero no está inicializado.");
            return false; 
        }

        String pieza = tablero[filaInicio][columnaInicio];
        if (pieza == null) {
            System.err.println("No hay pieza en la posición de inicio.");
            return false; // No hay pieza en la posición de inicio
        }

        if (pieza.startsWith(turnoActual)) {
            return tablero[filaDestino][columnaDestino] == null; 
        }

        System.err.println("No se puede mover la pieza del jugador contrario.");
        return false; // No se puede mover la pieza del jugador contrario
    }

    @Override
    public boolean estaGeneralEnJuego() {
        // Verificar si el tablero está inicializado
        if (tablero == null) {
            System.err.println("El tablero no está inicializado.");
            return false; 
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] != null && tablero[i][j].contains("General " + turnoActual)) {
                    return true; // El General está en juego
                }
            }
        }
        juegoActivo = false; 
        return false;
    }

    @Override
    public void cambiarTurno() {
        turnoActual = turnoActual.equals("Rojo") ? "Negro" : "Rojo";
    }

    private void actualizarPosicion(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino) {
        tablero[filaDestino][columnaDestino] = tablero[filaInicio][columnaInicio]; 
        tablero[filaInicio][columnaInicio] = null; 
    }

    public void mostrarTablero() {
        // Método para mostrar el estado del tablero (para depuración)
        if (tablero == null) {
            System.err.println("El tablero no está inicializado.");
            return; 
        }

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print((tablero[i][j] != null ? tablero[i][j] : "Vacío") + "\t");
            }
            System.out.println();
        }
    }
    
    

    public boolean isReyCapturado(String jugadorActual) {
        String reyABuscar = jugadorActual.equals("Rojo") ? "GeneralNegro" : "GeneralRojo";

        // Recorrer el tablero en busca del rey oponente
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (reyABuscar.equals(tablero[i][j])) {
                    return false; // El rey aún está en juego
                }
            }
        }
        return true; // El rey ha sido capturado
    }
}