package twitter;

import javax.swing.JOptionPane;
import static twitter.Twitter.usuarios;

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
            String post = inOrdenR(root);
            if (post != "") {
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(post + "\n\n 1- Responder     2- Siguiente"));//mostrar el inOrder Recursivo
                switch (opcion) {
                    case 1:
                        if (root.getDerecho() != null && root.getIzquierdo() != null) {
                            JOptionPane.showMessageDialog(null, "Se ha alcanzado el numero de respuestas máximo");
                            mostrar(arbol);
                        }//final if
                        else {
                            JOptionPane.showMessageDialog(null, "A continuacion, seleccione el usuario que esta respondiendo.");
                            Usuario usuarioa = Grafo.seleccionarUsuario();// se busca en la lista doble mediante la funcion 
                            if (usuarioa != null) {// de haberlo
                                arbol.responder(usuarioa, arbol.getRoot().getMensaje().getUser(), root);
                                mostrar(arbol);
                            }//final if
                            else {
                                JOptionPane.showMessageDialog(null, "No se ha encontrado al usuario");
                            }//final else
                        }//final else
                        break;
                    case 2:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Elija una opción valida.");
                }//final switch
            }//final if 
        }//final if
        else {
            JOptionPane.showMessageDialog(null, "El arbol está vacío");
        }//final else
    }//final del metodo mostrar

    public Arbol eliminarPost(Arbol arbol, Usuario usuarioa) {
        Arbol post = null;
        if (!esVacio()) { //si no es vacio
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(inOrdenR(root) + "\n\n 1- Eliminar Post     2- Siguiente"));//mostrar el inOrder Recursivo
            switch (opcion) {
                case 1:
                    if (usuarioa != null) {// de haberlo
                        return arbol;
                    }//final if 
                    else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado al usuario");
                    }//final else
                    break;
                case 2:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Elija una opción valida.");
            }//final switch
        }//final if 
        else {
            JOptionPane.showMessageDialog(null, "El arbol está vacío");
        }//final else
        return post;
    }//final del metodo mostrar

    public String inOrdenR(NodoArbol root) {
        String resultado = "";
        if (root != null && comparar(root)) {
            resultado += root.getMensaje();
            if (root.getIzquierdo() != null && comparar(root.getIzquierdo())) {
                resultado += "------";
            }//final if
            resultado += inOrdenR(root.getIzquierdo());
            if (root.getDerecho() != null && comparar(root.getDerecho())) {
                resultado += "------";
            }//Final if
            resultado += inOrdenR(root.getDerecho());
        }//final if
        return resultado;
    }//final del metodo inOrderR

    private boolean comparar(NodoArbol post) {
        Usuario usuario = post.getMensaje().getUser();
        boolean x = false;
        NodoListaDobleCircular cabeza = usuarios.getCabeza();
        if (cabeza != null) {
            NodoListaDobleCircular aux = cabeza;
            if (aux.getDato() == usuario) {
                x = true;
            }//final if
            aux = aux.getSiguiente();
            while (aux != cabeza) {
                if (aux.getDato() == usuario) {
                    x = true;
                }//final if
                aux = aux.getSiguiente();
            }//final while
        }//final if
        return x;
    }//final del metodo actualizarListaSeguidores

    @Override
    public String toString() {
        return root.getMensaje().getUser().getName() + root;
    }

}//Final de la clase
