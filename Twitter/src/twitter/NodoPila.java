
package twitter;

/**
 *
 * @author Jonathan
 */
public class NodoPila {
    //atributos de la clase
    private Arbol mensaje;
    private NodoPila siguiente;
    private Arbol post; //¿¿¿¿¿ ni idea porq

    public NodoPila() {
        this.siguiente = null;
    }//final constructor

    //gets and sets
    public NodoPila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente;
    }
    
    
    public Arbol getmensaje() {
        return mensaje;
    }

    public void setmensaje(Arbol mensaje) {
        this.mensaje = mensaje;
    }//final gets and sets

    @Override
    public String toString() {
        return ""+mensaje;
    }
}//final de la clase
