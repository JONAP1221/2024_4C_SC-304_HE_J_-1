package twitter;

public class ListaSimple {

    private NodoListaSimple cabeza;

    public void insertar(UsuarioSimple u) {
        //1. La lista está vacía.
        if (cabeza == null) // si la lista esta vacia 
        {
            cabeza = new NodoListaSimple(u);// se anade algo a la cabeza
        } else {
            //2.se anade algo antes de la cabeza
            if (u.getId() < cabeza.getDato().getId()) {//se inserta algo antes de la cabeza
                NodoListaSimple auxiliar = new NodoListaSimple(u);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            } else {
                //3.Insertar despues de la cabeza
                if (cabeza.getSiguiente() == null) {
                    NodoListaSimple nuevo = new NodoListaSimple(u);
                    cabeza.setSiguiente(nuevo);
                } else {
                    //4.para anadir algo al final del todo o en el medio
                    NodoListaSimple auxiliar = cabeza;
                    while (auxiliar.getSiguiente() != null
                            && auxiliar.getSiguiente().getDato().getId() < u.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }

                    NodoListaSimple otroAuxiliar = new NodoListaSimple(u);
                    otroAuxiliar.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(otroAuxiliar);
                }
            }
        }
    }//final del metodo insertar
    
        public void elimina(int id) {
        NodoListaSimple aux = cabeza;
        NodoListaSimple anterior = null;
        while (aux != null) {
            if (aux.getDato().getId() == id) {
                if (aux == cabeza) {
                    cabeza = cabeza.getSiguiente();
                    aux.setSiguiente(null);
                    break;
                }//final if
                else {
                    anterior.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(null);
                    break;
                }//final else
            }//final if
            else {
                anterior = aux;
                aux = aux.getSiguiente();
            }//final else
        }//final while
    }//final del metodo elimina
}
