/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author HP
 */
public abstract class TableroXiangqi {
     
    public abstract boolean moverPieza(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino);

    
    protected abstract boolean esMovimientoValido(int filaInicio, int columnaInicio, int filaDestino, int columnaDestino);

    
    public abstract void inicializarPosiciones();

    
    public abstract boolean estaGeneralEnJuego();

    
    public abstract void cambiarTurno();
}
