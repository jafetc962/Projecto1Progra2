package logica;

public class Caballo1 extends Pieza {

    private Tablero2 tablero;

    public Caballo1(Bando bando, Tablero2 tablero) {
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
        // Movimiento en  L 2 pasos en una direccion y 1 en la otra o viceversa
        int diffFilas = Math.abs(filaDestino - filaOrigen);
        int diffColumnas = Math.abs(columDestino - columOrigen);

        //aqui el Math abs me ayuda para la distancia , ejem El valor absoluto es la distancia de un numero a 0 o un numero sin signo, por lo que el resultado siempre sera positivo.
        //aqui se comprueba el patron de L
        if ((diffFilas == 2 && diffColumnas == 1) || (diffFilas == 1 && diffColumnas == 2)) {
            //aqui se verifica si el movimiento esta bloqueado
            if (diffFilas == 2) {
                //aqui movimiento vertical  comprobar si hay una pieza en la casilla intermedia verticalmente
                int filaIntermedia = (filaDestino + filaOrigen) / 2;
                if (tablero.hayPieza(filaIntermedia, columOrigen)) {
                    return false; // Movimiento bloqueado
                }
            } else {
                //aqui movimiento horizontal comprobar si hay una pieza en la casilla intermedia horizontalmente
                int columIntermedia = (columDestino + columOrigen) / 2;
                if (tablero.hayPieza(filaOrigen, columIntermedia)) {
                    return false; // Movimiento bloqueado
                }
            }
            // Si no esta bloqueado, el movimiento es valido
            return true;
        }
        return false; // Movimiento invalido
    }
}
