package twitter;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Twitter {

    public static ListaDobleCircular usuarios = new ListaDobleCircular();
    public static Usuario user = new Usuario();
    public static Grafo g = new Grafo();

    public static void main(String[] args) {
        Grafo.cargarUsuariosDesdeCSV(usuarios, "data/UsuariosTwitter.csv");

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
                            Post.compararFecha("14/04/2024 10:30:15");
                            g.agregarUsusario();
                            break;
                        case 2:
                            g.cambiarNombre(usuarioBase());
                            break;
                        case 3:
                            g.cambiarEdad(usuarioBase());
                            break;
                        case 4:
                            g.eliminarUsuario(usuarioBase());
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, usuarios);
                            break;
                        case 6:
                            user.insertarSeguidor(usuarioBase(), ususarioDestino());
                            break;
                        case 7:
                            user.eliminarSeguidor(usuarioBase(), ususarioDestino());
                            break;
                        case 8:
                            user.verSeguidores(usuarioBase());
                            break;
                        case 9:
                            user.crearPost(usuarioBase());
                            break;
                        case 10:
                            user.eliminarPost(usuarioBase());
                            break;
                        case 11:
                            user.mostrarPost(usuarioBase());
                            break;
                        case 12:
                            user.mostrarFeedlista(usuarioBase());
                            break;
                        case 0:
                            continuar = false; // Se sale del menú

                            // Cargar la imagen
                            ImageIcon originalIcon = new ImageIcon("C:\\Users\\camer\\OneDrive\\Escritorio\\vaca\\elmae.jpg");

                            // Redimensionar la imagen
                            Image img = originalIcon.getImage();
                            Image scaledImg = img.getScaledInstance(600, 600, Image.SCALE_SMOOTH); // Cambia 200, 200 a las dimensiones deseadas
                            ImageIcon scaledIcon = new ImageIcon(scaledImg);

                            // Mostrar la imagen redimensionada en el cuadro de diálogo
                            JOptionPane.showMessageDialog(null, "", "Saliendo del app", JOptionPane.PLAIN_MESSAGE, scaledIcon);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error capa 8: " + e.getMessage());
            }
        }
        Grafo.guardarUsuariosCSV(usuarios, "data/UsuariosTwitter.csv");
    }

    public static Usuario usuarioBase() {
        JOptionPane.showMessageDialog(null, "A continuacion, seleccione su usuario.");
        return Grafo.seleccionarUsuario();
    }

    public static Usuario ususarioDestino() {
        JOptionPane.showMessageDialog(null, "A continuacion, seleccione el correo del ususario destino.");
        return Grafo.seleccionarUsuario();
    }
}
