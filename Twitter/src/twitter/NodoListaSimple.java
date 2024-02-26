
package twitter;

public class NodoListaSimple {
    private Usuario dato;
    private NodoListaSimple siguiente;

    public NodoListaSimple(Usuario dato) {
        this.dato = dato;
    }

    public Usuario getDato() {
        return dato;
    }

    public void setDato(Usuario dato) {
        this.dato = dato;
    }

    public NodoListaSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaSimple siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
