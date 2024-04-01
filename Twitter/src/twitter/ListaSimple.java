package twitter;

public class ListaSimple {

    private NodoListaSimple cabeza;

   

    public void insertarSeguidor(Usuario u) {
        // 1. La lista está vacía.
        if (cabeza == null) {
            cabeza = new NodoListaSimple(u);
        } else {
            // 2. Se añade algo antes de la cabeza.
            if (u.getHash() < cabeza.getUsuario().getHash()) {
                NodoListaSimple nuevoNodo = new NodoListaSimple(u);
                nuevoNodo.setSiguiente(cabeza);
                cabeza = nuevoNodo;
            } else {
                // 3. Insertar después de la cabeza.
                if (cabeza.getSiguiente() == null) {
                    NodoListaSimple nuevo = new NodoListaSimple(u);
                    cabeza.setSiguiente(nuevo);
                } else {
                    // 4. Insertar al final o en medio.
                    NodoListaSimple actual = cabeza;
                    while (actual.getSiguiente() != null && actual.getSiguiente().getUsuario().getHash() < u.getHash()) {
                        actual = actual.getSiguiente();
                    }
                    NodoListaSimple nuevoNodo = new NodoListaSimple(u);
                    nuevoNodo.setSiguiente(actual.getSiguiente());
                    actual.setSiguiente(nuevoNodo);
                }
            }
        }
    }

    public void eliminarSeguidor(int id) {
        NodoListaSimple actual = cabeza;
        NodoListaSimple anterior = null;
        while (actual != null) {
            if (actual.getUsuario().getHash() == id) {
                if (actual == cabeza) {
                    cabeza = cabeza.getSiguiente();
                    actual.setSiguiente(null);
                    break;
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                    actual.setSiguiente(null);
                    break;
                }
            } else {
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }
    }

    public boolean existeSeguidor(Usuario usuario) {
        NodoListaSimple actual = cabeza;
        while (actual != null) {
            if (actual.getUsuario().getHash() == usuario.getHash()) {
                return true; // El usuario es seguidor
            }
            actual = actual.getSiguiente();
        }
        return false; // El usuario no es seguidor
    }

    public NodoListaSimple getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoListaSimple cabeza) {
        this.cabeza = cabeza;
    }
    
    
}
