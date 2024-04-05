package twitter;

/**
 *
 * @author Jonathan
 */
// Clase NodoPila
public class NodoPila {

    private Arbol post;
    private String mensaje;
    private NodoPila siguiente;

    public NodoPila(Arbol post) {
        this.post = post;
        this.siguiente = null;
    }

    public NodoPila() {
    }

    public NodoPila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente;
    }

    public Arbol getArbol() {
        return post;
    }

    public void setArbol(Arbol post) {
        this.post = post;
    }

    @Override
    public String toString() {
        if (post != null) {
            return "Mensaje: " + mensaje + ", Arbol: " + post.toString();
        } else {
            return "Mensaje: " + mensaje + ", Arbol: [null]";
        }
    }
}
