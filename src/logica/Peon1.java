package logica;

public class Peon1 extends Pieza {

    private Tablero2 tablero;
    public Peon1(Bando bando, Tablero2 tablero) {
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
        //aqui movimiento del soldado rojo (se mueve hacia arriba en el tablero)
        if (bando == Bando.ROJO) {
            if (filaOrigen >= 5) {
                //aui antes de cruzar el rio solo puede moverse hacia adelante
                if (filaDestino == filaOrigen - 1 && columDestino == columOrigen) {
                    return true;
                }
            } else {
                //aqui despues de cruzar el rio: puede moverse hacia adelante o horizontalmente
                if ((filaDestino == filaOrigen - 1 && columDestino == columOrigen)
                        || // Avanzar
                        (filaDestino == filaOrigen && Math.abs(columDestino - columOrigen) == 1)) { // Moverse horizontalmente
                    return true;
                }
            }
        }
        //aqui movimiento del soldado negro (se mueve hacia abajo en el tablero)
        if (bando == Bando.NEGRO) {
            if (filaOrigen <= 4) {
                //aqui antes de cruzar el rio: solo puede moverse hacia adelante
                if (filaDestino == filaOrigen + 1 && columDestino == columOrigen) {
                    return true;
                }
            } else {
                //aqui despues de cruzar el rio puede moverse hacia adelante o horizontalmente
                if ((filaDestino == filaOrigen + 1 && columDestino == columOrigen)
                        || // Avanzar
                        (filaDestino == filaOrigen && Math.abs(columDestino - columOrigen) == 1)) { // Moverse horizontalmente
                    return true;
                }
            }
        }
        
        return false; //aqui movimiento invalido
    }
}