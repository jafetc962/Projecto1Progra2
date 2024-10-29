package logica;

public class Rey1 extends Fichas {

        private Xiangqui tablero;

    /**
     *
     * @param bando
     * @param tablero
     */
    public Rey1(Equipo bando, Xiangqui tablero) {
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
        int diffFila = Math.abs(filaDestino - filaOrigen);
        int diffColumnas = Math.abs(columDestino - columOrigen);
        if ((diffFila == 1 && diffColumnas == 0) || (diffFila == 0 && diffColumnas == 1)) {
            if (bando == Equipo.ROJO) {
                if (filaDestino >= 7 && filaDestino <= 9 && columDestino >= 3 && columDestino <= 5) {
                    return true;
                }
            } else if (bando == Equipo.NEGRO) {
                if (filaDestino >= 0 && filaDestino <= 2 && columDestino >= 3 && columDestino <= 5) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
