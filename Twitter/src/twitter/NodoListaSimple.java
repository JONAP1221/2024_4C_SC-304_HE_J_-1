
package twitter;

public class NodoListaSimple {
    private UsuarioSimple user;
    private NodoListaSimple siguiente;

    public NodoListaSimple(UsuarioSimple dato) {
        this.user = dato;
    }

    public UsuarioSimple getDato() {
        return user;
    }

    public void setDato(UsuarioSimple dato) {
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
