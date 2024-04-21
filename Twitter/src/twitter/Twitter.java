package twitter;

import javax.swing.JOptionPane;

public class Twitter {

    public static ListaDobleCircular usuarios = new ListaDobleCircular();
    public static Usuario user = new Usuario();
    public static Grafo g = new Grafo();

    public static void main(String[] args) {

        Grafo.cargarDatos();
        boolean continuar = true;
        while (continuar) {
            try {
                String a = JOptionPane.showInputDialog("Opciones de ingreso \n"
                        + "1: Agregar usuario \n"
                        + "2: Modificar nombre de un usuario \n"
                        + "3: Modificar edad de un usuario \n"
                        + "4: Eliminar un usuario \n"
                        + "5: Mostrar usuarios  \n"
                        + "6: Agregar seguidor \n"
                        + "7: Eliminar un seguidor \n"
                        + "8: Mostrar a quien sigue el usuario xxx \n"
                        + "9: Publicar un post \n"
                        + "10: Eliminar un post \n"
                        + "11: Mostrar los post de un usuario \n"
                        + "12: Mostrar el feed de un usuario \n"
                        + "0: salir \n");
                if (a != null && !a.isEmpty()) { // Verificar si la cadena no está vacía
                    int numero = Integer.parseInt(a);
                    switch (numero) {
                        case 1 -> g.agregarUsusario();
                        case 2 -> g.cambiarNombre(Grafo.seleccionarUsuario());
                        case 3 -> g.cambiarEdad(Grafo.seleccionarUsuario());
                        case 4 -> g.eliminarUsuario(Grafo.seleccionarUsuario());
                        case 5 -> JOptionPane.showMessageDialog(null, usuarios);
                        case 6 -> user.insertarSeguidor(Grafo.seleccionarUsuario(), Grafo.seleccionarUsuario());
                        case 7 -> user.eliminarSeguidor(Grafo.seleccionarUsuario(), Grafo.seleccionarUsuario());
                        case 8 -> user.verSeguidores(Grafo.seleccionarUsuario());
                        case 9 -> user.crearPost(Grafo.seleccionarUsuario());
                        case 10 -> user.eliminarPost(Grafo.seleccionarUsuario());
                        case 11 -> user.mostrarPost(Grafo.seleccionarUsuario());
                        case 12 -> user.mostrarFeedlista(Grafo.seleccionarUsuario());
                        case 0 -> continuar = false;
                        default -> JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error capa 8: " + e.getMessage());
            }
        }
        Grafo.guardarDatos();
    }//final del main

}//final de la clase
