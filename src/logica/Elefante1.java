package logica;

public class Elefante1 extends Pieza {

    private Tablero2 tablero;
    public Elefante1(Bando bando, Tablero2 tablero) {
        super(bando);
        this.tablero = tablero;
    }
    @Override
    public boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        
        //aqui se verifica la diferencia de filas y columnas para movimiento diagonal exacto de 2 casillas
        int diffFilas = Math.abs(filaDestino - filaOrigen);// representa los saltos de linea
        int diffColumnas = Math.abs(columDestino - columOrigen);
        //aqui el elefante solo se mueve en una diagonal exacta de 2 casillas
        if (diffFilas == 2 && diffColumnas == 2) {
            //aqui se verifica el limite del rio, el Elefante Rojo no puede ir mas alla de la fila 4 y el Elefante Negro no puede ir más alla de la fila 5
            if (bando == Bando.ROJO && filaDestino < 4) { 
                
                return false; // Bloquea el cruce del rio
            }
            if (bando == Bando.NEGRO && filaDestino > 5) {
                
                return false; // Bloquea el cruce del rio
            }
            //aqui se verificaa si hay una pieza bloqueando en la casilla intermedia
            int filaIntermedia = (filaOrigen + filaDestino) / 2;
            int columIntermedia = (columOrigen + columDestino) / 2;
            System.out.println("Verificando casilla intermedia: (" + filaIntermedia + ", " + columIntermedia + ")");
            
            // Si hay una pieza en la casilla intermedia, el movimiento es bloqueado
            if (tablero.hayPieza(filaIntermedia, columIntermedia)) {
                System.out.println("Movimiento bloqueado, hay una pieza en la casilla intermedia.");
                return false;
            }
            
            //aqui se verifica si hay una pieza del mismo bando en el destino del elefante
            Pieza piezaDestino=tablero.obtenerPieza(filaDestino, columDestino);
            if(piezaDestino!=null){
                //aqui se verifica que no sea del mismo bando
                if(piezaDestino.getBando()==this.getBando()){
                    return false;
                    
                }
            }
            // Si todas las condiciones se cumplen, el movimiento es válido
           
            return true; // Movimiento válido
        }
        // Si no es una diagonal exacta de 2 casillas, el movimiento es inválido
        
        return false; // Movimiento inválido
    }
}
