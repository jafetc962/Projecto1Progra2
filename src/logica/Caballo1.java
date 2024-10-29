package logica;

public class Caballo1 extends Fichas {

    private Xiangqui tablero;

    public Caballo1(Equipo bando, Xiangqui tablero) {
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
        int diffFilas = Math.abs(filaDestino - filaOrigen);
        int diffColumnas = Math.abs(columDestino - columOrigen);

        
        if ((diffFilas == 2 && diffColumnas == 1) || (diffFilas == 1 && diffColumnas == 2)) {
            if (diffFilas == 2) {
                int filaIntermedia = (filaDestino + filaOrigen) / 2;
                if (tablero.hayPieza(filaIntermedia, columOrigen)) {
                    return false; 
                }
            } else {
                int columIntermedia = (columDestino + columOrigen) / 2;
                if (tablero.hayPieza(filaOrigen, columIntermedia)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
