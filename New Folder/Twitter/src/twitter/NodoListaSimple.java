
package twitter;

public class NodoListaSimple {
    private Usuario user;
    private NodoListaSimple siguiente;

    public NodoListaSimple(Usuario dato) {
        this.user = dato;
    }

    public Usuario getDato() {
        return user;
    }

    public void setDato(Usuario dato) {
        this.user = dato;
    }

    public NodoListaSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaSimple siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoListaSimple{" + "user=" + user + ", siguiente=" + siguiente + '}';
    }
    
    
}
