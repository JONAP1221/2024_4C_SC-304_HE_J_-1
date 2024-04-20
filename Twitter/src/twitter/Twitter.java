package twitter;

import javax.swing.JOptionPane;

public class Twitter {

    public static ListaDobleCircular usuarios = new ListaDobleCircular();
    public static Usuario user = new Usuario();
    public static Grafo g = new Grafo();

    public static void main(String[] args) {
        //Grafo.cargarUsuariosDesdeCSV(usuarios, "data/UsuariosTwitter.csv");
        
        usuarios.insertaMejorado(new Usuario("aaa", "Aaa", 14));
        usuarios.insertaMejorado(new Usuario("bbb", "Bbb", 12));
        usuarios.insertaMejorado(new Usuario("ccc", "Ccc", 20));

        boolean continuar = true;
        while (continuar) {
            try {
                String a = JOptionPane.showInputDialog("Opciones de ingreso \n"
                        + "1: Agregar usuario \n"
                        + "2: Modificar nombre de un usuario \n"
                        + "3: Modificar edad de un usuario \n"
                        + "4: Eliminar un usuario \n"
                        + "5: Mostrar usuarios  \n"
                        + "1: Manejo de usuarios \n"
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

                        case 1:
                            g.agregarUsusario();
                            break;
                        case 2:
                            g.cambiarNombre();

                            break;
                        case 3:
                            g.cambiarEdad();
                            break;
                        case 4:
                            g.eliminarUsuario();
                            break;
                        case 5:
                            JOptionPane.showConfirmDialog(null, usuarios);
                            break;

                        case 6:
                            user.insertarSeguidor();
                            break;
                        case 7:
                            user.eliminarSeguidor();
                            break;
                        case 8:// no funca 
                            user.verSeguidores();
                            break;
                        case 9:
                            user.crearPost();
                            break;
                        case 10:
                            user.eliminarPost();  //Pendinte realizar funcion
                            break;
                        case 11:
                            user.mostrarPost();
                            break;
                        case 12:
                            user.mostrarFeed();
                            break;
                        case 0:
                            continuar = false;// se sale del menu 
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
                            break;
                    }//final switch
                }
            }//Final del try
            catch (Exception e) {
                System.out.println("Error capa 8: " + e.getMessage());
            }//final del catch
        }//final del while
        //Grafo.guardarUsuariosCSV(usuarios, "data/UsuariosTwitter.csv");
    }//final del main
}//final de la clase
