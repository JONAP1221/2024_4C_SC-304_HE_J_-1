package twitter;

import javax.swing.JOptionPane;
import static twitter.Twitter.usuarios;

public class Grafo {

    public void agregarUsusario() {
        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
    }

    public void eilimiarUsuario() {
        String correoUsuarioBase = JOptionPane.showInputDialog("Ingrese el correo de su usuario");
        Usuario usuarioBase = usuarios.buscarUsuarioPorCorreo(correoUsuarioBase);

        
        if (usuarios.existe(usuarioBase)) {
            usuarios.eliminar(usuarioBase);
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + usuarioBase + "se ha eliminado.");

        } else {
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + usuarioBase + "no existe.");

        }
    }
}
