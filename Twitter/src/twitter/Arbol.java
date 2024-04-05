package twitter;

import javax.swing.JOptionPane;
import twitter.NodoArbol;

/**
 *
 * @author Jonathan
 */
public class Arbol {

    //atributos de la clase
    private NodoArbol root; //crea el nodo root de arbol

    public Arbol() {
        this.root = null;//inicializa el nodo creado como nulo
    }

    //get y set
    public NodoArbol getRoot() {
        return root;
    }

    public void setRoot(NodoArbol root) {
        this.root = root;
    }//final get y set

    public boolean esVacio() {
        return root == null;//metodo para saber si el arbol está vacío
    }//Final metodo esVacio

    public void agregar(Post mensaje) {
        //seguir comentando esta shit zzz
        NodoArbol nuevo = new NodoArbol(mensaje); //se establece una variable para el nuevo mensaje (Post)
        if (esVacio()) {//si el arbol esta vacio
            root = nuevo; // se crea el Mensaje principal
        }//final if 
        else { // si no (Estas son las respuestas)
            nuevo.getMensaje().setMsj("      " + nuevo.getMensaje().getMsj());
            NodoArbol aux = root; //este esta conectado al mensaje principal
            NodoArbol padre; // otra variable de apoyo para la respuesta a la respuesta
            while (true) {
                padre = aux;  //igualamos ambas variables
                if (padre.getIzquierdo() == null) {
                    aux = aux.getIzquierdo();
                    if (aux == null) {
                        padre.setIzquierdo(nuevo);
                        return;
                    }//Final if
                }//final if
                else {
                    aux = aux.getDerecho();
                    if (aux == null) {
                        padre.setDerecho(nuevo);
                        return;
                    }//final if
                }//final else
            }//final while
        }//final else
    }//final metodo agregar

    public void responder(Usuario user, Usuario post, NodoArbol root) {
        Post mensaje = Post.respuesta(user, root.getMensaje().getUser());
        NodoArbol nuevo = new NodoArbol(mensaje); //se establece una variable para la respuesta (Post)
        nuevo.getMensaje().setMsj("      " + nuevo.getMensaje().getMsj());
        NodoArbol aux = root; //este esta conectado al mensaje principal
        NodoArbol padre; // otra variable de apoyo para la respuesta a la respuesta
        while (true) {
            padre = aux;  //igualamos ambas variables
            if (padre.getIzquierdo() == null) {
                aux = aux.getIzquierdo();
                if (aux == null) {
                    padre.setIzquierdo(nuevo);
                    return;
                }//Final if
            }//final if
            else {
                aux = aux.getDerecho();
                if (aux == null) {
                    padre.setDerecho(nuevo);
                    return;
                }//final if
            }//final else
        }//final while
    }//final else

    public void mostrar(Arbol arbol) {
        if (!esVacio()) { //si no es vacio
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(inOrdenR(root)+ "\n\n 1- Responder     2- Siguiente"));//mostrar el inOrder Recursivo
            switch (opcion) {
                case 1:
                    String correoUsuarioa = JOptionPane.showInputDialog("Ingrese el correo del usuario que esta respondiendo:");// se pide el correo del ususario 
                    Usuario usuarioa = Twitter.usuarios.buscarUsuarioPorCorreo(correoUsuarioa);// se busca el ususario en la lista doble
                    if (usuarioa != null) {// de haberlo
                        arbol.responder(usuarioa, arbol.getRoot().getMensaje().getUser(), root);
                        mostrar(arbol);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado al usuario");
                    }
                    break;
                case 2:
                    break;

                default:
                    throw new AssertionError();
            }//final switch
        }//final if 
        else {
            JOptionPane.showMessageDialog(null, "El arbol está vacío");
        }//final else
    }//final del metodo mostrar

    public String inOrdenR(NodoArbol root) {
        String resultado = "";
        if (root != null) {
            resultado += root.getMensaje();
            if (root.getIzquierdo() != null) {
                resultado += "------";
            }//final if
            resultado += inOrdenR(root.getIzquierdo());
            if (root.getDerecho() != null) {
                resultado += "------";
            }//Final if
            resultado += inOrdenR(root.getDerecho());
        }//final if
        return resultado;
    }//final del metodo inOrderR

}//Final de la clase
