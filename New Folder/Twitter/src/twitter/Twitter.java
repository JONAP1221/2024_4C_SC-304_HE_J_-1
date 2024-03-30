package twitter;

import javax.swing.JOptionPane;

public class Twitter {

    public static void main(String[] args) {

        boolean continuar = true;
        ListaDobleCircular usuarios = new ListaDobleCircular();

        try {
            while (continuar) {
                String a;
                a = JOptionPane.showInputDialog("Opciones de ingreso \n" + "1: agregar usuario \n" + "2: agregar seguidor \n" + "9: ver usuarios con los id \n" + "0: salir \n");
                int numero = Integer.parseInt(a);

                switch (numero) {
                    case 1:
                        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
                        break;
                    case 2:
                        ListaSimple seguidores = new ListaSimple();
                        String x = JOptionPane.showInputDialog("el correo de su usuario");

                        //seguidores.insertarSeguidor(b);
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

}
