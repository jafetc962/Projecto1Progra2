/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


import java.time.LocalDateTime;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class InterfazSistema {
    private Logs logs;

    public InterfazSistema() {
        logs = new Logs();
    }

    // Métodos de usuarios
    public void registrarUsuario(String nombre, String contra) {
        LocalDateTime fechaRegistro = LocalDateTime.now();
        logs.AddUsuario(nombre, contra, fechaRegistro);
    }

    public Usuarios buscarUsuario(String nombre) {
        return logs.BuscarUsuario(nombre, 0);
    }

    public void borrarUsuario(String nombre) {
        logs.borrarCuenta(nombre, 0);
    }

    public Usuarios[] obtenerRankingUsuarios() {
        return logs.Ranking();
    }

    public void listarUsuarios() {
        Usuarios[] usuarios = logs.obtenerNombresUsuarios();
        StringBuilder lista = new StringBuilder("Usuarios:\n");
        for (Usuarios usuario : usuarios) {
            if (usuario != null) {
                lista.append(usuario.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    // Métodos de inicio de sesión
    public void iniciarSesion(String nombre) {
        Usuarios usuario = buscarUsuario(nombre);
        if (usuario != null) {
            logs.userLogged(nombre);
            JOptionPane.showMessageDialog(null, "Usuario logueado: " + nombre);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    public String obtenerUsuarioLogueado() {
        return logs.sendLogged();
    }

    // Métodos de partidas
    public void guardarPartida(String oponente, String ganador) {
        String usuarioLogueado = obtenerUsuarioLogueado();
        if (usuarioLogueado != null) {
            logs.GuardarPartida(usuarioLogueado, oponente, ganador);
        } else {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión para guardar una partida.");
        }
    }

    public Partidas[] mostrarPartidas(String usuario) {
        return logs.printP(usuario);
    }

    public void listarPartidas(String usuario) {
        Partidas[] partidas = mostrarPartidas(usuario);
        StringBuilder lista = new StringBuilder("Partidas de " + usuario + ":\n");
        for (Partidas partida : partidas) {
            if (partida != null) {
                lista.append(partida.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
}
