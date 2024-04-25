package twitter;

public class Cola {

    //atributos de la clase
    private NodoCola primero;
    private NodoCola ultimo;
    private Arbol dato;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }//final constructor vacio

    public boolean esVacia() {
        return primero == null;
    }//final metodo esVacia

    public void encolar(Arbol p) {//funcion para conelar 
        NodoCola nuevoNodo = new NodoCola(p);
        if (esVacia()) {//si esta vacia se agrega
            primero = nuevoNodo;// se crea un nuevo nodo con el valor de cabeza
            ultimo = nuevoNodo;// se crea un se crea un nodo con el valor del ultimos, ambos son el mismo ya que es lo primero que se anade a la red
        }//final if 
        else {//si ya tenia un elemento antes lo pone de ultimo
            ultimo.setSiguiente(nuevoNodo);// recordar que en una cola se anade desde atras, por ello se setea com el siguiente del que ya estaba 
            ultimo = nuevoNodo;// refrescamos el puntero del ultimo a el nuevo nodo que metimos 
        }//final else
    }//final metodo encolar

    public void desencolar(ListaDobleCircular usuarios) {// funcion de desencolar
        if (!esVacia()) {// en caso de que no este vacia y esto se verifica mediante !esVacia
            while (primero != null) {// si tenemos algo en el primer nodo 
                primero.getPost().mostrar(primero.getPost(), usuarios);// se muestra el post de dicho modo 
                primero = primero.getSiguiente();// se vanza al sigueinte nodo 
            }//final while
        }//final if 
    }//final del metodo desencolar

    @Override
    public String toString() {
        String s = "";
        if (!esVacia()) {
            NodoCola aux = primero;
            while (aux != null) {
                s = s + aux.getUser() + "\n";
                aux = aux.getSiguiente();
            }//Final while
        }//final if
        return s;
    }//final metodo toString

}//final de la clase
