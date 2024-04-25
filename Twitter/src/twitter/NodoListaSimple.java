package twitter;

public class NodoListaSimple {

    private NodoListaSimple siguiente;
    private Arbol post;
    private String correo;

    public NodoListaSimple(Usuario usuario) {
        this.correo = usuario.getEmail();
        this.siguiente = null;
    }

    public String getCorreo() {
        return correo;
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


    public NodoListaSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaSimple siguiente) {
        this.siguiente = siguiente;
    }
}
