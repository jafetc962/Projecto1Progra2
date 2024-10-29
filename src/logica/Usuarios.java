/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class Usuarios {
    
    //atributos
    private String username;
    private String password;
    private int puntos;
    private LocalDateTime fecha;
    private boolean activo;
    
    //constructores
    public Usuarios(){
        
    }
    public Usuarios(String nombre,String contra,LocalDateTime date){
        username=nombre;
        password=contra;
        puntos=0;
        fecha=date;
        activo=true;
    }
   
    //gets
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public boolean isActivo() {
        return activo;
    }
    
    //sets

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public void setPuntos(int puntos){
        this.puntos+=puntos;
    }
        
    
    
    
}
