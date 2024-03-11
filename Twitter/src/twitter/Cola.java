
package twitter;

/**
 *
 * @author Jonathan
 */
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

    public void encolar(Usuario user) {
        NodoCola nuevoNodo = new NodoCola(user);
        if (esVacia()) {//si esta vacia se agrega
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        }//final if 
        else {//si ya tenia un elemento antes lo pone de ultimo
            ultimo.setSiguiente(nuevoNodo);
            ultimo = nuevoNodo;
        }//final else
    }//final metodo encolar
    
    public NodoCola desencolar()
    {
        NodoCola actual = primero;
        
        if(primero != null)
        {
            primero = primero.getSiguiente();
            actual.setSiguiente(null);
        }
        
        
        
        return actual;
    }//final del metodo desencolar
    
    public void showFeed(){
        
        
        //mostrar la visualizacion de mensajes
        
    }//Final del metodo showFeed

    @Override
    public String toString() {
        String s = "";
        if (!esVacia()) {
            NodoCola aux = primero;
            while (aux != null) {
                s = s + aux.getUser()+ "\n";
                aux = aux.getSiguiente();
            }//Final while
        }//final if
        return s;
    }//final metodo toString
    
}//final de la clase
