package twitter;

import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
// Clase Pila
public class Pila {

    private NodoPila cima;

    public Pila() {
        this.cima = null;
    }

    public boolean esVacia() {
        return cima == null;
    }

    public NodoPila getCima() {
        return cima;
    }

    public void apilar(Arbol post) {// funcino para apilar los post de los usuarios
        NodoPila nuevo = new NodoPila(post);// se inicializa una variable tipo nodoPila 
        if (esVacia()) {// si es vacia
            cima = nuevo;// se pone en la cima
        } else {// de no estar vacio 
            nuevo.setSiguiente(cima);// se pone despues de la cima, osea sobre
            cima = nuevo;// y se actiaza el valor de cima 
        }
    }

    public void mostrarPila() {// para mostar la pila 
        NodoPila nodoActual = cima;//  se crea una variable tipo nodo pila y se inicializa como la cabeza 
        while (nodoActual != null) {// si la pila no esta vacia 
            if (nodoActual.getArbol() != null) {//  si hay un post en la pila 
                System.out.println(nodoActual.getArbol().getRoot().getMensaje()); // Mostrar el mensaje del post
            }
            nodoActual = nodoActual.getSiguiente();// se actualiza la referencia al siguente nodo hasta llegar al final 
        }
    }

    public void mostrarPilaConMensajes(ListaDobleCircular usuarios) {// para mostar el mensaje 
        NodoPila nodoActual = cima;// se crea un puntero como la cima de la pila 
        while (nodoActual != null) {// siempre y cuando esta no este vacia 
            nodoActual.getArbol().mostrar(nodoActual.getArbol(), usuarios);// se muesta dicho mensaje del nodo 
            nodoActual = nodoActual.getSiguiente();// se avanza al siguente nodo 
        }
    }

    public void eliminarPilaConMensajes(Usuario a, ListaDobleCircular usuarios) {// funcion para eliminar la pila de mensajes 
        NodoPila nodoActual = cima;// se crea una variable como la cima de la pila 
        Arbol elimina = null;// se crea una variable como null
        while (nodoActual != null) {// siempre y cuando la pila no este vacia
            elimina = nodoActual.getArbol().eliminarPost(nodoActual.getArbol(), a, usuarios);// eilimana la cual es la varibale es igual a se setea como el valro de ciha variabe
            if (elimina != null) {// si esta varibale es distinta de nulla
                desapilar(elimina);// se despaila dicha variable o nodo
            }//final if
            nodoActual = nodoActual.getSiguiente();// se avanza al sigueinte
        }
    }

    public void desapilar(Arbol post) {// funcion de desaplar el cual recibe un arbol como parametro 
        if (!esVacia()) {// si es distinta de vacio 
            NodoPila nodoActual = cima;// se crea un puntero como cima y otro como null 
            NodoPila nodoAnterior = null;// se crea un puntero null
            while (nodoActual != null) {// si el nodo actual no es vacio o null
                if (nodoActual.getArbol() == post) {// se obtiene el post del mismo 
                    if (nodoAnterior == null) { // Si el nodo a desapilar es el primero
                        cima = nodoActual.getSiguiente();// se setia la cima como el siguente del que extraimos 
                    }
                    else {// despues de, osea en caso de si tenet algo en esa variable
                        nodoAnterior.setSiguiente(nodoActual.getSiguiente());//se avanza al sigeunte en la referencia 
                    }
                    JOptionPane.showMessageDialog(null, "Post eliminado correctamente.");
                    return;// se miestra que el post se desapilo de manera correcta
                }
                nodoAnterior = nodoActual;// se refresca la informacionn del nodo 
                nodoActual = nodoActual.getSiguiente();// se avanza en el puntero en caso de no aver etrado en el else
            }
            JOptionPane.showMessageDialog(null, "El post no se encontró en la pila");// se muestra en caso de que el post no este en la pila 
        }
        else {
            JOptionPane.showMessageDialog(null, "La pila está vacía");// en caso de que la pila este vacia 
        }
    }

    public Arbol desapilar() {// funcion de devolviendo arbol como tal 
        if (!esVacia()) {// si es distinto de vacio 
            Arbol postDesapilado = cima.getArbol();// se setia coom el dato de la cima 
            cima = cima.getSiguiente();// se avanza el puntero 
            JOptionPane.showMessageDialog(null, "Elemento extraído");// se muestra que el elmento se extrajo
            return postDesapilado;// se devielve el desapilar
        } else {
            JOptionPane.showMessageDialog(null, "La pila está vacía");
            return null;
        }
    }

    public ListaSimple obtener(ListaSimple allArbols) {// se crea una funcion para obetner en una lista simple// para el feed
        NodoPila aux = cima;// se crea una varibae como la cima 
        if (!esVacia()) {// en caso de que no este vacia
            while (aux != null) {// si la variable no es nulla
                Arbol post = aux.getArbol();// se optiene el arbol de la variable del puntero 
                allArbols.insertarPost(post);//se inserta el post en la listya simple
                aux = aux.getSiguiente();// se avanz al sigueinte en hasta llegar a null
            }
        }
        return allArbols;// se anade el post a el array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pila:\n");
        NodoPila actual = cima;
        while (actual != null) {
            sb.append(actual.toString()).append("\n"); // Aquí podrías usar el toString de NodoPila
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    public String pilaPostsToString() {
        StringBuilder sb = new StringBuilder();
        NodoPila current = cima;
        while (current != null) {
            sb.append(current.getArbol().toString()).append("\n");
            current = current.getSiguiente();
        }
        return sb.toString();
    }

}
