package twitter;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.ArrayList;

public class Twitter {

    public static ListaDobleCircular usuarios = new ListaDobleCircular();
    public static Usuario user = new Usuario();
    public static Grafo g = new Grafo();

    public static void main(String[] args) {

        usuarios.insertaMejorado(new Usuario("aaa", "Aaa", 14));
        usuarios.insertaMejorado(new Usuario("bbb", "Bbb", 12));
        usuarios.insertaMejorado(new Usuario("ccc", "Ccc", 1416));

        boolean continuar = true;
        while (continuar) {
            try {
                String a = JOptionPane.showInputDialog("Opciones de ingreso \n"
                        + "1: agregar usuario \n"
                        + "2: agregar seguidor \n"
                        + "3: ver seguidores de un usuario \n"
                        + "4: crear y enviar post \n"
                        + "5: Mostrar post de usuario \n"
                        + "6: Mostrar el feed de un usuario \n"
                        + "7: Eliminar un usueario \n"
                        + "8: Eliminar un seguidor \n"
                        + "9: ver usuarios con los id \n"
                        + "0: salir \n");
                if (a != null && !a.isEmpty()) { // Verificar si la cadena no está vacía
                    int numero = Integer.parseInt(a);
                    switch (numero) {
                        case 1:
                            g.agregarUsusario();
                            break;
                        case 2:
                            user.insertarSeguidor();
                            break;
                        case 3:
                            user.verSeguidores();
                            break;
                        case 4:
                            user.crearPost();
                            break;
                        case 5:
                            user.mostrarPost();
                            break;
                        case 6:
                            user.mostrarFeed();
                            break;
                        case 7:// no funca 
                            g.eilimiarUsuario();
                            break;
                        case 8:
                            user.eliminarSeguidor();
                            break;
                        case 9:
                            JOptionPane.showConfirmDialog(null, usuarios);
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
    }//final del main
}//final de la clase
