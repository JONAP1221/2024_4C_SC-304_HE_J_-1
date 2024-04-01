package twitter;

/**
 *
 * @author Jonathan
 */
// Clase NodoPila
public class NodoPila {

    private Post post;
    private String mensaje;
    private NodoPila siguiente;

    public NodoPila(Post post, String mensaje) {
        this.post = post;
        this.mensaje = mensaje;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        if (post != null) {
            return "Mensaje: " + mensaje + ", Post: " + post.toString();
        } else {
            return "Mensaje: " + mensaje + ", Post: [null]";
        }
    }
}
