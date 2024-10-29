/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.time.LocalDateTime;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Logs {
    //atributos
    Usuarios[] users;
    String x;
    Usuarios[] ranking ;
    String jugadorRojo;
    String jugadorNegro;
    Partidas[] partidas;
    
    //constructor
    public Logs(){
       users= new Usuarios[100];
       ranking = new Usuarios[users.length];
       partidas = new Partidas[100];
    }
    
    
    //anadir
public void AddUsuario(String nombre, String contra, LocalDateTime date) {
        System.out.println("Intentando registrar usuario: " + nombre);
        if (BuscarUsuario(nombre,0) != null) {
            JOptionPane.showMessageDialog(null, "El usuario ya existe, no se registró.");
            return;
        }
        
        
        Usuarios nuevaPersona = new Usuarios(nombre, contra, date);
        
        
        for (int j = 0; j < users.length; j++) {
            if (users[j] == null) {
                users[j] = nuevaPersona;
               JOptionPane.showMessageDialog(null,"Usuario registrado: " + nombre);
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario, no hay espacio.");
    }

    // buscar recursiva
    public Usuarios BuscarUsuario(String apodo, int index) {
    if (users == null || index >= users.length) {
        return null;
    }

    // Verificar si el usuario actual es el buscado
    Usuarios usuario = users[index];
    if (usuario != null && usuario.getUsername().equalsIgnoreCase(apodo)) {
        return usuario;
    }

    // Continuar buscando en el siguiente índice
    return BuscarUsuario(apodo, index + 1);
}
    
    public void userLogged(String user){
        x=user;
    }
    public String sendLogged(){
        return x;
    }     
    
   //borrar recursiva
    public void borrarCuenta(String apodo, int index) {
    if (index >= users.length) {
        return;
    }

    if (users[index] != null && users[index].getUsername().equalsIgnoreCase(apodo)) {
        users[index] = null;
    }

    borrarCuenta(apodo, index + 1);
}
    
    
    
    public Usuarios[] Ranking(){
    
    
    
    
    for (int i = 0; i < users.length; i++) {
        ranking[i] = users[i];
    }

    
    for (int i = 0; i < ranking.length - 1; i++) {
        for (int j = 0; j < ranking.length - i - 1; j++) {
           
            if (ranking[j] != null && ranking[j+1] != null && ranking[j].getPuntos() < ranking[j+1].getPuntos()) {
                Usuarios temp = ranking[j];
                ranking[j] = ranking[j+1];
                ranking[j+1] = temp;
            }
        }
    }

   
    return ranking;
}
    
    public Usuarios[] obtenerNombresUsuarios(){
        return users;
    }
    
    
    public void jugadores(String jugadorInv){
        jugadorRojo=sendLogged();
        jugadorNegro=jugadorInv;
    }
    
    
    public String sendPlayer(String equipo){
        if(equipo.equalsIgnoreCase("rojo")){
            return jugadorRojo;
        }if(equipo.equalsIgnoreCase("negro")){
        return jugadorNegro;
        }
       return null; 
    }
    public void GuardarPartida(String logueado,String Oponente, String Ganador){
        Calendar fecha=Calendar.getInstance();
        Partidas partida=new Partidas(logueado,Oponente,Ganador,fecha);
        for (int i = 0; i < partidas.length; i++) {
            if(partidas[i]==null){
                partidas[i]=partida;
                return;
            }
        }
    }
    
    public Partidas[] printP(String logged) {
        Partidas[] print = new Partidas[100];
        int j = 0; 

        for (int i = 0; i < partidas.length; i++) {
            
            if (partidas != null && partidas[i] != null) {
               
                if (partidas[i].getLogueado().equalsIgnoreCase(logged) || partidas[i].getOponente().equalsIgnoreCase(logged)) {
                    if (j < print.length) {  
                        print[j] = partidas[i];
                        j++;
                    } else {
                        break; 
                    }
                }
            }
        }
        return print;
    }
}

    
