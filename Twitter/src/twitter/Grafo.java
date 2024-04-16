package twitter;

import javax.swing.JOptionPane;
import static twitter.Twitter.usuarios;

public class Grafo {

     public void agregarUsusario() {// se se desea crear un ususario se instacia el mismo medinate preguntas para obtener los datos 
        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
    }

    public void eliminarUsuario() {// para eiliminar un ususario de la lista doble circ 
        String correoUsuarioBase = JOptionPane.showInputDialog("Ingrese el correo de su usuario");// se pide el correo del ususario 
        Usuario usuarioBase = usuarios.buscarUsuarioPorCorreo(correoUsuarioBase);// se busca en la lista doble mediante la funcion 

        if (usuarios.existe(usuarioBase)) {// si el usuario existe en la lista doble 
            usuarios.eliminar(usuarioBase);// se elimina el usuario de la lista doble 
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + usuarioBase + "se ha eliminado.");// se muestra el mensaje que el ussuario ha sido eilimnado nunto a el usuario 
        } else {// de no encontrarse al ussuario
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + usuarioBase + "no existe.");// se miestra que el ususario no exitse junto a dicho ususario 
        }
    }
    
     public void cambiarNombre() {// funcion para cambiar nombre
        String correoUsuarioBase = JOptionPane.showInputDialog("Ingrese el correo de su usuario");// se pide el correo del usuario a modificar 
        Usuario usuarioBase = usuarios.buscarUsuarioPorCorreo(correoUsuarioBase);// se busca a el ususario medinate la funcion 
        usuarios.modificaNombre(usuarioBase);// se modifica el nombre
    }

    public void cambiarEdad() {// funcino para cambiar la edad
        String correoUsuarioBase = JOptionPane.showInputDialog("Ingrese el correo de su usuario");// se pide el correo del usuario a modificar 
        Usuario usuarioBase = usuarios.buscarUsuarioPorCorreo(correoUsuarioBase);// se busca a el ususario medinate la funcion 
        usuarios.modificaEdad(usuarioBase);// se modifica la edad
    }
}
