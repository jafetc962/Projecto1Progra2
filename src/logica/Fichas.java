/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author HP
 */
public abstract class Fichas {
    protected Equipo bando;
    public Fichas(Equipo bando) {
        this.bando = bando;
    }
    public abstract boolean fichasMovements(int filaOrigen, int columOrigen, int filaDestino, int columDestino);
    public Equipo getBando() {
        return bando;
    }
}
