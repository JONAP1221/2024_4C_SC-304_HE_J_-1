package twitter;

import java.time.LocalDate;
import java.time.Month;
import javax.swing.JOptionPane;

public class Twitter {

    public static void main(String[] args) {
        /*
        boolean continuar = true;
        ListaDobleCircular usuarios = new ListaDobleCircular();

        try {
            while (continuar) {
                String a;
                a = JOptionPane.showInputDialog("Opciones de ingreso \n" + "1: agregar usuario \n" + "2: agregar seguidor \n" + "9: ver usuarios con los id \n" + "0: salir \n");
                int numero = Integer.parseInt(a);
                ListaSimple seguidores = new ListaSimple();

                switch (numero) {
                    case 1:
                        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
                        break;
                    case 2:
                        String b = JOptionPane.showInputDialog("Ingrese el coreo del usuario base");
                        String c = JOptionPane.showInputDialog("Ingrese el correo del ususario a seguir");

                        break;
                    case 9:
                        System.out.println(usuarios);
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
         */
        
        Pila apilarpo = new Pila();
        apilarpo.apilarpost(new Post("Balato", "Chayanne", LocalDate.EPOCH.of(4, Month.MARCH, 2000)));
        
        
    }
}
