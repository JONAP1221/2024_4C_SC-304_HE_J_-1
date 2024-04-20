package twitter;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static twitter.Twitter.usuarios;

public class Grafo {

    public void agregarUsusario() {// se se desea crear un ususario se instacia el mismo medinate preguntas para obtener los datos 
        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
    }

    public void eliminarUsuario() {// para eiliminar un ususario de la lista doble circ 
        JOptionPane.showMessageDialog(null, "A continuacion, seleccione un usuario para eliminar.");
        Usuario usuarioBase = seleccionarUsuario();// se busca en la lista doble mediante la funcion 
        if (usuarios.existe(usuarioBase)) {// si el usuario existe en la lista doble 
            usuarios.eliminar(usuarioBase);// se elimina el usuario de la lista doble 
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + usuarioBase + "se ha eliminado.");// se muestra el mensaje que el ussuario ha sido eilimnado nunto a el usuario 
        } else {// de no encontrarse al ussuario
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + usuarioBase + "no existe.");// se miestra que el ususario no exitse junto a dicho ususario 
        }
    }

    public void cambiarNombre() {// funcion para cambiar nombre
        JOptionPane.showMessageDialog(null, "A continuacion, seleccione un usuario para cambiarle el nombre.");
        Usuario usuarioBase = seleccionarUsuario();// se busca en la lista doble mediante la funcion 
        usuarios.modificaNombre(usuarioBase);// se modifica el nombre
    }

    public void cambiarEdad() {// funcino para cambiar la edad
        JOptionPane.showMessageDialog(null, "A continuacion, seleccione un usuario para cambiarle la edad.");
        Usuario usuarioBase = seleccionarUsuario();// se busca en la lista doble mediante la funcion 
        usuarios.modificaEdad(usuarioBase);// se modifica la edad
    }

    public static Usuario seleccionarUsuario() {
        return usuarios.buscarUsuarioPorCorreo(Usuario.dropdown());
    }//final del metodo seleccionarUsuario

    public static void guardarUsuariosCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            NodoListaDobleCircular auxiliar = usuarios.getCabeza();
            Usuario usuario = auxiliar.getDato();
            String linea = usuario.getEmail() + "," + usuario.getName() + "," + usuario.getAge() + "," + usuario.getSeguidores().seguidoresToString() + "," + usuario.getPilaPosts().pilaPostsToString();
            writer.write(linea);
            writer.newLine();
            auxiliar = auxiliar.getSiguiente();
            // Escribir cada usuario en una línea del archivo CSV
            while (auxiliar != usuarios.getCabeza()) {
                usuario = auxiliar.getDato();
                linea = usuario.getEmail() + "," + usuario.getName() + "," + usuario.getAge() + "," + usuario.getSeguidores().seguidoresToString() + "," + usuario.getPilaPosts().pilaPostsToString();
                writer.write(linea);
                writer.newLine();
                auxiliar = auxiliar.getSiguiente();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarUsuariosDesdeCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 6) { // Se espera que haya 6 campos en cada línea
                    String email = campos[0];
                    String nombre = campos[1];
                    int edad = Integer.parseInt(campos[2]);
                    int hash = Integer.parseInt(campos[3]);
                    // Puedes crear tus propias estructuras para seguidores y pila de posts
                    // Aquí estoy asumiendo que son representados como strings en el CSV
                    String seguidores = campos[4];
                    String pilaPosts = campos[5];
                    Usuario usuario = new Usuario(email, nombre, edad);
                    usuario.setHash(hash);
                    // Aquí necesitas procesar seguidores y pila de posts según tu implementación
                    usuarios.insertaMejorado(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
