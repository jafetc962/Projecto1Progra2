/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Calendar;

/**
 *
 * @author HP
 */
public class Partidas {
    
    String logueado;
    String oponente;
    String ganador;
    Calendar fechaPartida;      
            
    public Partidas(){
        
    }
    
    public Partidas(String logged,String oponente,String winner,Calendar date){
        logueado=logged;
        this.oponente=oponente;
        ganador=winner;
        fechaPartida=date;
    }

    public String getLogueado() {
        return logueado;
    }

    public String getOponente() {
        return oponente;
    }

    public String getGanador() {
        return ganador;
    }

    public Calendar getFechaPartida() {
        return fechaPartida;
    }
    
    
    
}
