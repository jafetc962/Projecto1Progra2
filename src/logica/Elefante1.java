package logica;

public class Elefante1 extends Fichas {

    private Xiangqui tablero;
    public Elefante1(Equipo bando, Xiangqui tablero) {
        super(bando);
        this.tablero = tablero;
    }
    @Override
    public boolean fichasMovements(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        
        int diffFilas = Math.abs(filaDestino - filaOrigen);
        int diffColumnas = Math.abs(columDestino - columOrigen);
        if (diffFilas == 2 && diffColumnas == 2) {
            if (bando == Equipo.ROJO && filaDestino < 4) { 
                
                return false; 
            }
            if (bando == Equipo.NEGRO && filaDestino > 5) {
                
                return false; 
            }
            
            int filaIntermedia = (filaOrigen + filaDestino) / 2;
            int columIntermedia = (columOrigen + columDestino) / 2;
            System.out.println("Verificando casilla intermedia: (" + filaIntermedia + ", " + columIntermedia + ")");
            
            
            if (tablero.hayPieza(filaIntermedia, columIntermedia)) {
                System.out.println("Movimiento bloqueado, hay una pieza en la casilla intermedia.");
                return false;
            }
            
            Fichas piezaDestino=tablero.buscarF(filaDestino, columDestino);
            if(piezaDestino!=null){
                
                if(piezaDestino.getBando()==this.getBando()){
                    return false;
                    
                }
            }
            
           
            return true; 
        }
        
        return false; 
    }
}
