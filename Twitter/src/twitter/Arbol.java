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

    public void responder(Post mensaje, Arbol post) {
        NodoArbol nuevo = new NodoArbol(mensaje); //se establece una variable para la respuesta (Post)
        nuevo.getMensaje().setMsj("      " + nuevo.getMensaje().getMsj());
        NodoArbol aux = post.getRoot(); //este esta conectado al mensaje principal
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

    public void mostrar() {
        if (!esVacio()) { //si no es vacio
            inOrdenR(root);//mostrar el inOrder Recursivo
        }//final if 
        else {
            JOptionPane.showMessageDialog(null, "El arbol está vacío");
        }//final else
    }//final del metodo mostrar

    public void inOrdenR(NodoArbol root) {
        //que despiche hice, al rato veo zzzz
        if (root != null) {
            System.out.print(root.getMensaje());
            if (root.getIzquierdo() != null) {
                System.out.print("------");
            }//final if
            inOrdenR(root.getIzquierdo());
            if (root.getDerecho() != null) {
                System.out.print("------");
            }//final if
            inOrdenR(root.getDerecho());
        }//final if
    }//final del metodo inOrderR
}//Final de la clase
