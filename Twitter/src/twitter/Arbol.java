package twitter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

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

    public NodoArbol getRoot() {
        return root;
    }

    public void setRoot(NodoArbol root) {
        this.root = root;
    }

    public boolean esVacio() {
        return root == null;//metodo para saber si el arbol está vacío
    }//Final metodo esVacio

    public void agregar(Post mensaje) {
        NodoArbol nuevo = new NodoArbol(mensaje);
        if (esVacio()) {
            root = nuevo;
        }//final if 
        else {
            NodoArbol aux = root;
            NodoArbol padre;
            while(true){
              padre = aux;
              if(padre.getIzquierdo() == null){
                  aux = aux.getIzquierdo();
                  if(aux == null){
                      padre.setIzquierdo(nuevo);
                      return;
                  }
              }else{
                  aux = aux.getDerecho();
                  if(aux == null){
                      padre.setDerecho(nuevo);
                      return;
                  }
              }
          }
        }//final else
    }//final metodo agregar

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

    public void inOrdenR(NodoArbol root) {
        if (root != null) {
            System.out.print(root.getMensaje());
            if (root.getIzquierdo() != null || root.getDerecho() != null) {
                System.out.print("-----");
            }//final if
            inOrdenR(root.getIzquierdo());
            if (root.getIzquierdo() != null || root.getDerecho() != null) {
                System.out.print("-----");
            }//final if
            inOrdenR(root.getDerecho());
        }//final if
    }//final del metodo inOrderR
}//Final de la clase
