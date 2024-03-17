package twitter;

import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class Arbol {
    //atributos de la clase

    private NodoArbol root;
    
    //hay que ver
    

    public Arbol() {
        this.root = null;
    }

    public boolean esVacio() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    public void agregar() {
        Post d = new Post();
        d.setMsj((JOptionPane.
                showInputDialog(null, "Digite el mensaje:")));
        NodoArbol nuevo = new NodoArbol();
        nuevo.setMensaje(d);
        if (esVacio()) {
            root = nuevo;
        } else {
            agregarNuevo(root, nuevo);
        }
    }

    public void agregarNuevo(NodoArbol raiz, NodoArbol nuevo) {
        if (nuevo.getMensaje().getFecha().equals(raiz.getMensaje().getFecha()) || nuevo.getMensaje().getFecha().isBefore(raiz.getMensaje().getFecha())) {
            if (raiz.getIzquierdo() == null) {
                raiz.setIzquierdo(nuevo);
            } else {
                agregarNuevo(raiz.getIzquierdo(), nuevo);
            }
        } else {
            if (raiz.getDerecho() == null) {
                raiz.setDerecho(nuevo);
            } else {
                agregarNuevo(raiz.getDerecho(), nuevo);
            }
        }
    }

    public void eliminarTodo() {
        if (!esVacio()) {
            root = null;
        } else {
            JOptionPane.showMessageDialog(null, "¡No se puede eliminar, árbol vacío!");
        }
    }

    public void mostrar() {
        if (!esVacio()) {
            inOrdenR(root);
        } else {
            JOptionPane.showMessageDialog(null, "¡No se puede mostrar, árbol vacío!");
        }
    }

    public void inOrdenR(NodoArbol raiz) {
        if (raiz != null) {
            inOrdenR(raiz.getIzquierdo());
            System.out.print( raiz.getMensaje() + "  ");
            inOrdenR(raiz.getDerecho());
        }
    }
}//Final de la clase