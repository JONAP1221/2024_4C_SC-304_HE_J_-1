package twitter;

public class NodoListaSimple {

    private Usuario usuario;
    private NodoListaSimple siguiente;
    private Arbol post;

    public NodoListaSimple(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = null;
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
