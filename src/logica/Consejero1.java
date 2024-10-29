package logica;

public class Consejero1 extends Pieza {

    private Tablero2 tablero;
    public Consejero1(Bando bando, Tablero2 tablero) {
        super(bando);
        this.tablero = tablero;
    }
    @Override
    public boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        //aqui se verifica si el movimiento es diagonal 1 casilla en cada direccion
        if (Math.abs(filaDestino - filaOrigen) == 1 && Math.abs(columDestino - columOrigen) == 1) {
            // Verificar si se mantiene dentro del palacio
            if (bando == Bando.ROJO) {
                // El oficial rojo debe permanecer entre las filas 7 y 9, y columnas 3 y 5
                if (filaDestino >= 7 && filaDestino <= 9 && columDestino >= 3 && columDestino <= 5) {
                    return true; //aqui movimiento valido
                }
            } else if (bando == Bando.NEGRO) {
                // El oficial negro debe permanecer entre las filas 0 y 2, y columnas 3 y 5
                if (filaDestino >= 0 && filaDestino <= 2 && columDestino >= 3 && columDestino <= 5) {
                    return true; //aqui Movimiento valido
                }
            }
        }
        return false; //aqui Movimiento invalido
    }
    
    
}
