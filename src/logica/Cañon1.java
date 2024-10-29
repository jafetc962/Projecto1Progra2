package logica;

public class Cañon1 extends Fichas {

    private Xiangqui tablero;

    public Cañon1(Equipo bando, Xiangqui tablero) {
        super(bando);
        this.tablero = tablero;
    }

    @Override
    public boolean fichasMovements(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        
        Fichas piezaDestino = tablero.buscarF(filaDestino, columDestino);
        if (piezaDestino != null) {
            
            if (piezaDestino.getBando() == this.getBando()) {
                return false;
            }
        }
        
        if (filaOrigen == filaDestino || columOrigen == columDestino) {
           
            if (!tablero.hayPieza(filaDestino, columDestino)) {
                return !cBlock(filaOrigen, columOrigen, filaDestino, columDestino);
            } else {
                return piezaCapturableconSalto(filaOrigen, columOrigen, filaDestino, columDestino);
            }
        }
        return false; 
    }

    private boolean cBlock(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        
        if (filaOrigen == filaDestino) {
            int minCol = Math.min(columOrigen, columDestino);
            int maxCol = Math.max(columOrigen, columDestino);
            for (int col = minCol + 1; col < maxCol; col++) {
                if (tablero.hayPieza(filaOrigen, col)) {
                    return true; 
                }
            }
        }
        if (columOrigen == columDestino) {
            int minFila = Math.min(filaOrigen, filaDestino);
            int maxFila = Math.max(filaOrigen, filaDestino);
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (tablero.hayPieza(fila, columOrigen)) {
                    return true; 
                }
            }
        }
        return false; 
    }

    private boolean piezaCapturableconSalto(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        int piezasIntermedias = 0;
        if (filaOrigen == filaDestino) {
            int minCol = Math.min(columOrigen, columDestino);
            int maxCol = Math.max(columOrigen, columDestino);
            for (int col = minCol + 1; col < maxCol; col++) {
                if (tablero.hayPieza(filaOrigen, col)) {
                    piezasIntermedias++;
                }
            }
        }
        if (columOrigen == columDestino) {
            int minFila = Math.min(filaOrigen, filaDestino);
            int maxFila = Math.max(filaOrigen, filaDestino);
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (tablero.hayPieza(fila, columOrigen)) {
                    piezasIntermedias++;
                }
            }
        }
        return piezasIntermedias == 1 && tablero.hayPieza(filaDestino, columDestino);
    }
}
