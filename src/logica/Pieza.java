/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author HP
 */
public abstract class Pieza {
    protected Bando bando;
    public Pieza(Bando bando) {
        this.bando = bando;
    }
    // funcion abstracta que se usara con polimorfismo para las 7 piezas 
    public abstract boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino);
    public Bando getBando() {
        return bando;
    }
}
