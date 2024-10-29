package logica;

public class Rey1 extends Pieza {

        private Tablero2 tablero;

    /**
     *
     * @param bando
     * @param tablero
     */
    public Rey1(Bando bando, Tablero2 tablero) {
        super(bando);
        this.tablero = tablero;
    }
    @Override
    public boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        Pieza piezaDestino = tablero.obtenerPieza(filaDestino, columDestino);
        if (piezaDestino != null) {
            //aqui se verifica que no sea del mismo bando
            if (piezaDestino.getBando() == this.getBando()) {
                return false;
            }
        }
        //aqui verifica que el movimiento es de una casilla en horizontal o verical
        int diffFila = Math.abs(filaDestino - filaOrigen);
        int diffColumnas = Math.abs(columDestino - columOrigen);
        if ((diffFila == 1 && diffColumnas == 0) || (diffFila == 0 && diffColumnas == 1)) {
            //aqui se verifica que el movimiento se mantiene dentro del palacio
            if (bando == Bando.ROJO) {
                //aqui el general rojo debe permanecer entre las filas 7 y 9, y las columnas 3 y 5
                if (filaDestino >= 7 && filaDestino <= 9 && columDestino >= 3 && columDestino <= 5) {
                    return true;//aqui movimiento valido
                }
            } else if (bando == Bando.NEGRO) {
                //aqui el general negro debe permanecer entre las filas 0 y2, y columnas 3 y5 
                if (filaDestino >= 0 && filaDestino <= 2 && columDestino >= 3 && columDestino <= 5) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
