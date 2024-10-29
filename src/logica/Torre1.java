package logica;

public class Torre1 extends Fichas {

    private Xiangqui tablero;
    public Torre1(Equipo bando, Xiangqui tablero) {
        super(bando);
        this.tablero = tablero;
    }
    @Override
    public boolean fichasMovements(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        //aqui se verifica si hay una pieza del mismo bando en el destino del elefante
        Fichas piezaDestino = tablero.buscarF(filaDestino, columDestino);
        if (piezaDestino != null) {
            //aqui se verifica que no sea del mismo bando
            if (piezaDestino.getBando() == this.getBando()) {
                return false;
            }
        }
        //aqui se verifica si el movimiento es horizontal o vertical
        if (filaOrigen == filaDestino || columOrigen == columDestino) {
            //aqui se comprueba si hay piezas en el camino
            if (!caminoBloqueado(filaOrigen, columOrigen, filaDestino, columDestino)) {
                return true; //aqui movimiento valido si no hay piezas bloqueando
            }
        }
        return false; //aqui mvimiento invalido
    }
    //aqui una funcion para verificar si el camino esta bloqueado
    private boolean caminoBloqueado(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        //aqui movimiento en la misma fila (horizontal)
        if (filaOrigen == filaDestino) {
            int minCol = Math.min(columOrigen, columDestino);
            int maxCol = Math.max(columOrigen, columDestino);
            for (int col = minCol + 1; col < maxCol; col++) {
                if (tablero.hayPieza(filaOrigen, col)) {
                    return true; // Hay una pieza bloqueando el camino
                }
            }
        }
        // Movimiento en la misma columna (vertical)
        if (columOrigen == columDestino) {
            int minFila = Math.min(filaOrigen, filaDestino);
            int maxFila = Math.max(filaOrigen, filaDestino);
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (tablero.hayPieza(fila, columOrigen)) {
                    return true; // Hay una pieza bloqueando el camino
                }
            }
        }
        return false; // No hay piezas bloqueando el camino
    }
}