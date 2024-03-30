package twitter;

public class NodoListaDobleCircular {

    private Usuario dato;
    private NodoListaDobleCircular anterior;
    private NodoListaDobleCircular siguiente;

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

    public NodoListaDobleCircular() {
    }

    @Override
    public String toString() {
        return dato.toString();
    }

}
