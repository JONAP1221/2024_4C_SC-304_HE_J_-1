package twitter;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
// Clase Pila
public class Pila {

    private NodoPila cima;

    public Pila() {
        this.cima = null;
    }

    public boolean esVacia() {
        return cima == null;
    }

    public NodoPila getCima() {
        return cima;
    }

    public void apilar(Arbol post) {// funcino para apilar los post de los usuarios
        NodoPila nuevo = new NodoPila(post);// se inicializa una variable tipo nodoPila 
        if (esVacia()) {// si es vacia
            cima = nuevo;// se pone en la cima
        } else {// de no estar vacio 
            nuevo.setSiguiente(cima);// se pone despues de la cima, osea sobre
            cima = nuevo;// y se actiaza el valor de cima 
        }
    }

    public void mostrarPila() {// para mostar la pila 
        NodoPila nodoActual = cima;//  se crea una variable tipo nodo pila y se inicializa como la cabeza 
        while (nodoActual != null) {// si la pila no esta vacia 
            if (nodoActual.getArbol() != null) {//  si hay un post en la pila 
                System.out.println(nodoActual.getArbol().getRoot().getMensaje()); // Mostrar el mensaje del post
            }
            nodoActual = nodoActual.getSiguiente();// se actualiza la referencia al siguente nodo hasta llegar al final 
        }
    }

    public void mostrarPilaConMensajes() {
        NodoPila nodoActual = cima;
        while (nodoActual != null) {
            nodoActual.getArbol().mostrar(nodoActual.getArbol());
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public void eliminarPilaConMensajes(Usuario a) {
        NodoPila nodoActual = cima;
        Arbol elimina = null;
        while (nodoActual != null) {
            elimina = nodoActual.getArbol().eliminarPost(nodoActual.getArbol(), a);
            if (elimina != null) {
                desapilar(elimina);
            }//final if
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public void desapilar(Arbol post) {
        if (!esVacia()) {
            NodoPila nodoActual = cima;
            NodoPila nodoAnterior = null;
            while (nodoActual != null) {
                if (nodoActual.getArbol() == post) {
                    if (nodoAnterior == null) { // Si el nodo a desapilar es el primero
                        cima = nodoActual.getSiguiente();
                    }//final if 
                    else {
                        nodoAnterior.setSiguiente(nodoActual.getSiguiente());
                    }//final else
                    JOptionPane.showMessageDialog(null, "Post eliminado correctamente.");
                    return;
                }//final if
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.getSiguiente();
            }//final while
            JOptionPane.showMessageDialog(null, "El post no se encontró en la pila");
        }//final if 
        else {
            JOptionPane.showMessageDialog(null, "La pila está vacía");
        }//final else
    }//final del metodo desapilar

    public Arbol desapilar() {
        if (!esVacia()) {
            Arbol postDesapilado = cima.getArbol();
            cima = cima.getSiguiente();
            JOptionPane.showMessageDialog(null, "Elemento extraído");
            return postDesapilado;
        } else {
            JOptionPane.showMessageDialog(null, "La pila está vacía");
            return null;
        }
    }

    public ArrayList<Arbol> obtener(ArrayList<Arbol> allArbols) {
        NodoPila aux = cima;
        if (!esVacia()) {
            while (aux != null) {
                Arbol post = aux.getArbol();
                allArbols.add(post);
                aux = aux.getSiguiente();
            }//final del while
        }//final if
        return allArbols;// se anade el post a el array;
    }//final del metodo obtener

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pila:\n");
        NodoPila actual = cima;
        while (actual != null) {
            sb.append(actual.toString()).append("\n"); // Aquí podrías usar el toString de NodoPila
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
    
    public String pilaPostsToString() {
    StringBuilder sb = new StringBuilder();
    NodoPila current = cima;
    while (current != null) {
        sb.append(current.getArbol().toString()).append("\n");
        current = current.getSiguiente();
    }
    return sb.toString();
}

}
