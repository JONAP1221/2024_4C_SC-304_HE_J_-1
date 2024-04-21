package twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListaSimple {

    private NodoListaSimple cabeza;

    public void insertarSeguidor(Usuario u) {
        if (cabeza == null) {// en caos de que la lista esta vacia 
            cabeza = new NodoListaSimple(u);//se crea el primer nodo como la cabeza
        } else {// en caso de querer meter algo antes que la cabeza
            if (u.getHash() < cabeza.getUsuario().getHash()) {// se verifica que sea el caso mediante la comparacion de lo hash
                NodoListaSimple nuevoNodo = new NodoListaSimple(u);// se settea que el nuevo nodo pasa a aser la cabeza 
                nuevoNodo.setSiguiente(cabeza);// se rfresca la referencia del nodo
                cabeza = nuevoNodo;
            } else {// en caso de inssertar despues de la cabeza
                if (cabeza.getSiguiente() == null) {// en caso de que solo este la cabeza
                    NodoListaSimple nuevo = new NodoListaSimple(u);// se crea la referencia del nodo
                    cabeza.setSiguiente(nuevo);// se coloca el nuevo nodo justo despues de la cabeza
                } else {// en caos de necesitar insertar en el centro 
                    NodoListaSimple actual = cabeza;// se crea un nodo para empezar por la cabeza 
                    while (actual.getSiguiente() != null && actual.getSiguiente().getUsuario().getHash() < u.getHash()) {// si se esta en la posicion corecta 
                        actual = actual.getSiguiente();// se pide la referencia del actual al siguiente 
                    }
                    NodoListaSimple nuevoNodo = new NodoListaSimple(u);// se cre un nuevo nodo 
                    nuevoNodo.setSiguiente(actual.getSiguiente());//se actualiza la informacino del nuevo nodo a la nueva informacion 
                    actual.setSiguiente(nuevoNodo);// se setea como el nuevo siguiente del nodo menor a donde queremos insertarlo 
                }
            }
        }
    }

    public void insertarPost(Arbol p) {
        if (cabeza == null) {
            cabeza = new NodoListaSimple(p);
        } else {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            try {
                Date fechaNueva = formato.parse(p.getRoot().getMensaje().getFecha());

                if (fechaNueva.before(formato.parse(cabeza.getPost().getRoot().getMensaje().getFecha()))) {
                    NodoListaSimple nuevoNodo = new NodoListaSimple(p);
                    nuevoNodo.setSiguiente(cabeza);
                    cabeza = nuevoNodo;
                } else {
                    NodoListaSimple actual = cabeza;
                    while (actual.getSiguiente() != null && formato.parse(actual.getSiguiente().getPost().getRoot().getMensaje().getFecha()).compareTo(fechaNueva) < 0) {
                        actual = actual.getSiguiente();
                    }
                    NodoListaSimple nuevoNodo = new NodoListaSimple(p);
                    nuevoNodo.setSiguiente(actual.getSiguiente());
                    actual.setSiguiente(nuevoNodo);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminarSeguidor(Usuario u) {// funcino para eliminar un seguidor de la lista simple de cada ususario 
        NodoListaSimple actual = cabeza;// se crea una referencia como cabeza
        NodoListaSimple anterior = null;// se crea una referencia como null para eliminar el espacion
        while (actual != null) {// si se tiene algo osea si se sigue a alguien 
            if (actual.getUsuario() == u) {// se busca el usuario
                if (actual == cabeza) {// si el que se borra es la cabeza 
                    cabeza = cabeza.getSiguiente();// se refresca la cabeza al sisguiente seguidor 
                    actual.setSiguiente(null);// se elimina la referencia del que fue la cabeza
                    break;// se sale de la funcion 
                } else {// en caso de no ser la cabeza
                    anterior.setSiguiente(actual.getSiguiente());// nos bincamos el nodo a eliinar
                    actual.setSiguiente(null);// se eilimina la referencia del siguienrte del nodo eliminado 
                    break;// se detiene la ejecucuion 
                }
            } else {// en caso de no seysar donde se queire 
                anterior = actual;
                actual = actual.getSiguiente();// se avanza al sisguiente nodo de la lista 
            }
        }
    }

    public boolean existeSeguidor(Usuario usuario) {// funcion para ver si un seguir existe en la lsita simple de cada ususario 
        NodoListaSimple actual = cabeza;// se empiza por la cabeza mediante un puntero tipo nodo 
        while (actual != null) {// si hay informacino y no esta vacia 
            if (actual.getUsuario().getHash() == usuario.getHash()) {// y si se esta en la posicion correcta osea se encontro al ususario 
                return true; // se devuelve un 1 o sea si se encontro al ussuario deseado 
            }
            actual = actual.getSiguiente();// se avanza en caso de no estar donde se requiere 
        }
        return false; // en caso de llegar al final de la lista y no encontrar al ussuario se regresa 0 o false 
    }

    public boolean noExisteSeguidor(Usuario usuario) {// funcino que verifica que el seguidor no este 
        NodoListaSimple actual = cabeza;//se empiza por la cabeza mediante un puntero tipo nodo 
        while (actual != null) {// si hay informacino y no esta vacia 
            if (actual.getUsuario().getHash() == usuario.getHash()) {// y si se esta en la posicion correcta osea se encontro al ususario 
                return false; //si se encuentra al ussuario se devuleve un 0 osea si se sigue 
            }
            actual = actual.getSiguiente();// se avanza en la lista para visitarla por completo
        }
        return true; // en caso de que n olo siga osea que no se encunentre en la lista se devuelve true que es lo que se busca como un 1
    }

    public NodoListaSimple getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoListaSimple cabeza) {
        this.cabeza = cabeza;
    }

    public String seguidoresToString() {
        StringBuilder sb = new StringBuilder();
        NodoListaSimple current = cabeza;
        while (current != null) {
            sb.append(current.getUsuario().toString()).append(" -> ");
            current = current.getSiguiente();
        }
        return sb.toString();
    }

    @Override
    public String toString() { //simplemente recorre la lista
        NodoListaSimple aux = cabeza;
        String s = "";
        while (aux != null) {
            s = s + aux.getUsuario().getEmail() + "\n";
            aux = aux.getSiguiente();
        }//final while
        return s;
    }
}
