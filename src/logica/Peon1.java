package logica;

public class Peon1 extends Fichas {

    private Xiangqui tablero;
    public Peon1(Equipo bando, Xiangqui tablero) {
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
        if (bando == Equipo.ROJO) {
            if (filaOrigen >= 5) {
                if (filaDestino == filaOrigen - 1 && columDestino == columOrigen) {
                    return true;
                }
            } else {
                 if ((filaDestino == filaOrigen - 1 && columDestino == columOrigen)
                        || 
                        (filaDestino == filaOrigen && Math.abs(columDestino - columOrigen) == 1)) { // Moverse horizontalmente
                    return true;
                }
            }
        }
        if (bando == Equipo.NEGRO) {
            if (filaOrigen <= 4) {
                 if (filaDestino == filaOrigen + 1 && columDestino == columOrigen) {
                    return true;
                }
            } else {
                if ((filaDestino == filaOrigen + 1 && columDestino == columOrigen)
                        || 
                        (filaDestino == filaOrigen && Math.abs(columDestino - columOrigen) == 1)) { // Moverse horizontalmente
                    return true;
                }
            }
        }
        
        return false; 
    }
}