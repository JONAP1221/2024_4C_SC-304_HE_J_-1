package twitter;

public class ListaSimple {

    private NodoListaSimple cabeza;

    public void insertar(Usuario u) {
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
                    NodoListaSimple nuevo = new NodoListaSimpleta(u);
                    cabeza.setSiguiente(nuevo);
                } else {
                    //4.para anadir algo al final del todo o en el medio
                    NodoLista auxiliar = cabeza;
                    while (auxiliar.getSiguiente() != null
                            && auxiliar.getSiguiente().getDato().getId() < u.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }

                    NodoLista otroAuxiliar = new NodoLista(p);
                    otroAuxiliar.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(otroAuxiliar);
                }
            }
        }
    }
}
