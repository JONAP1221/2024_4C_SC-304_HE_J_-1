package twitter;

public class NodoListaSimple {

    private Usuario usuario;
    private NodoListaSimple siguiente;
    private Arbol post;
    private String correo;

    public NodoListaSimple(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = null;
    }
 public String getCorreo() {
        return correo;
    }

    public NodoListaSimple(String correo) {
        this.correo = correo;
    }

    public NodoListaSimple(NodoListaSimple siguiente, String correo) {
        this.siguiente = siguiente;
        this.correo = correo;
    }

    public NodoListaSimple(Arbol post) {
        this.post = post;
        this.siguiente = null;
    }

    public Arbol getPost() {
        return post;
    }

    public void setPost(Arbol post) {
        this.post = post;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public NodoListaSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaSimple siguiente) {
        this.siguiente = siguiente;
    }
}
