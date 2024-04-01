package twitter;

public class NodoListaDobleCircular {

    private Usuario dato;
    private Usuario seguidores;
    private NodoListaDobleCircular anterior;
    private NodoListaDobleCircular siguiente;
    private Post publicaciones;

  
    
    public Usuario getDato() {
        return dato;
    }

    public void setDato(Usuario dato) {
        this.dato = dato;
    }

    public NodoListaDobleCircular getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoListaDobleCircular anterior) {
        this.anterior = anterior;
    }

    public NodoListaDobleCircular getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaDobleCircular siguiente) {
        this.siguiente = siguiente;
    }

    public NodoListaDobleCircular(Usuario dato) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public NodoListaDobleCircular(Usuario dato, Usuario seguidores, NodoListaDobleCircular anterior, NodoListaDobleCircular siguiente, Post publicaciones) {
        this.dato = dato;
        this.seguidores = seguidores;
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.publicaciones = publicaciones;
    }

    public NodoListaDobleCircular() {
    }

    @Override
    public String toString() {
        return dato.toString();
    }

    public Post getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(Post publicaciones) {
        this.publicaciones = publicaciones;
    }

    public Usuario getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Usuario seguidores) {
        this.seguidores = seguidores;
    }

}
