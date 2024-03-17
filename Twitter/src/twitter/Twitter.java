package twitter;

import javax.swing.JOptionPane;

public class Twitter {

    public static void main(String[] args) {

        /*//hola
        JOptionPane.showMessageDialog(null, "Welcome to Twitter", "Twitter",
                JOptionPane.INFORMATION_MESSAGE);
        
        
        JOptionPane.showInputDialog("Cuenta");
        
        Arbol a = new Arbol();
        System.out.println(a.esVacio());
         */
        int opcion = 0;
        Post nombre;
        Usuario a = null;
        Arbol arbcreado = new Arbol();
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "1. Agregar nodo\n"
                        + "2. Recorrer el árbol INORDEN\n"
                        + "3. Salir \n"
                        + "Elige una opción...: ", "Árbol binario de texto",
                         JOptionPane.QUESTION_MESSAGE));
                switch (opcion) {
                    case 1:
                        nombre = new Post(JOptionPane.showInputDialog("Mensaje: "), a);
                        arbcreado.agregar(nombre);
                        break;
                    case 2:
                        if (!arbcreado.esVacio()) {
                            System.out.println();
                            arbcreado.inOrdenR(arbcreado.getRoot());
                        } else {
                            JOptionPane.showInputDialog(null, "El árbol esta vacio", "¡Cuidado!",
                                     JOptionPane.QUESTION_MESSAGE);
                        }
                        break;
                    case 3:
                        JOptionPane.showInputDialog(null, "Aplicación finalizada ", "Fin",
                                 JOptionPane.QUESTION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showInputDialog(null, "Opción incorrecta", "¡Cuidado!",
                                 JOptionPane.QUESTION_MESSAGE);
                        break;

                }
            } catch (NumberFormatException n) {
                if ("null".equals(n.getMessage())) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Error" + n.getMessage());
                }
            }
        } while (opcion != 3);
    }//final del main
}//final de la clase
