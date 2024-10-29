package logica;

public class Cañon1 extends Pieza {

    private Tablero2 tablero;

    public Cañon1(Bando bando, Tablero2 tablero) {
        super(bando);
        this.tablero = tablero;
    }

    @Override
    public boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        //aqui se verifica si hay una pieza del mismo bando en el destino del elefante
        Pieza piezaDestino = tablero.obtenerPieza(filaDestino, columDestino);
        if (piezaDestino != null) {
            //aqui se verifica que no sea del mismo bando
            if (piezaDestino.getBando() == this.getBando()) {
                return false;
            }
        }
        // Verificar si el movimiento es horizontal o vertical
        if (filaOrigen == filaDestino || columOrigen == columDestino) {
            // Si no hay pieza en la posición de destino, solo moverse como la torre
            if (!tablero.hayPieza(filaDestino, columDestino)) {
                return !caminoBloqueado(filaOrigen, columOrigen, filaDestino, columDestino);
            } else {
                // Si hay una pieza en la posición destino, debe saltar una pieza intermedia
                return piezaCapturableconSalto(filaOrigen, columOrigen, filaDestino, columDestino);
            }
        }
        return false; // Movimiento inválido si no es horizontal o vertical
    }

    private boolean caminoBloqueado(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        // Movimiento horizontal
        if (filaOrigen == filaDestino) {
            int minCol = Math.min(columOrigen, columDestino);
            int maxCol = Math.max(columOrigen, columDestino);
            // Verificar si hay piezas entre el origen y el destino
            for (int col = minCol + 1; col < maxCol; col++) {
                if (tablero.hayPieza(filaOrigen, col)) {
                    return true; // Hay una pieza bloqueando el camino
                }
            }
        }
        // Movimiento vertical
        if (columOrigen == columDestino) {
            int minFila = Math.min(filaOrigen, filaDestino);
            int maxFila = Math.max(filaOrigen, filaDestino);
            // Verificar si hay piezas entre el origen y el destino
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (tablero.hayPieza(fila, columOrigen)) {
                    return true; // Hay una pieza bloqueando el camino
                }
            }
        }
        return false; // No hay piezas bloqueando el camino
    }

    private boolean piezaCapturableconSalto(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        int piezasIntermedias = 0;
        // Movimiento horizontal
        if (filaOrigen == filaDestino) {
            int minCol = Math.min(columOrigen, columDestino);
            int maxCol = Math.max(columOrigen, columDestino);
            // Contar las piezas entre el origen y el destino
            for (int col = minCol + 1; col < maxCol; col++) {
                if (tablero.hayPieza(filaOrigen, col)) {
                    piezasIntermedias++;
                }
            }
        }
        // Movimiento vertical
        if (columOrigen == columDestino) {
            int minFila = Math.min(filaOrigen, filaDestino);
            int maxFila = Math.max(filaOrigen, filaDestino);
            // Contar las piezas entre el origen y el destino
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (tablero.hayPieza(fila, columOrigen)) {
                    piezasIntermedias++;
                }
            }
        }
        // El cañón solo puede capturar si hay exactamente una pieza entre medio
        return piezasIntermedias == 1 && tablero.hayPieza(filaDestino, columDestino);
    }
}
