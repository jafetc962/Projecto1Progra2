package logica;

public class Consejero1 extends Fichas {

    private Xiangqui tablero;
    public Consejero1(Equipo bando, Xiangqui tablero) {
        super(bando);
        this.tablero = tablero;
    }
    @Override
    public boolean fichasMovements(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        if (Math.abs(filaDestino - filaOrigen) == 1 && Math.abs(columDestino - columOrigen) == 1) {
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
