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

    public void apilar(Post post, String mensaje) {
        NodoPila nuevo = new NodoPila(post, mensaje);
        if (esVacia()) {
            cima = nuevo;
        } else {
            nuevo.setSiguiente(cima);
            cima = nuevo;
        }
    }

    public void mostrarPila() {
        NodoPila nodoActual = cima;
        while (nodoActual != null) {
            if (nodoActual.getPost() != null) {
                System.out.println(nodoActual.getPost().getMsj()); // Mostrar el mensaje del post
            }
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public void mostrarPilaConMensajes() {
        NodoPila nodoActual = cima;
        while (nodoActual != null) {
            System.out.println(nodoActual.toString()); // Muestra el nodo completo (mensaje y post)
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public Post desapilar() {
        if (!esVacia()) {
            Post postDesapilado = cima.getPost();
            cima = cima.getSiguiente();
            JOptionPane.showMessageDialog(null, "Elemento extraído");
            return postDesapilado;
        } else {
            JOptionPane.showMessageDialog(null, "La pila está vacía");
            return null;
        }
    }

    public ArrayList<Post> obtener(ArrayList<Post> allPosts) {
        NodoPila aux = cima;
        if (!esVacia()) {
            while (aux != null) {
                Post post = aux.getPost();
                allPosts.add(post);
                aux = aux.getSiguiente();
            }//final del while
        } else {
            JOptionPane.showMessageDialog(null, "La pila está vacía");
        }//final else
        return allPosts;// se anade el post a el array;
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
}
