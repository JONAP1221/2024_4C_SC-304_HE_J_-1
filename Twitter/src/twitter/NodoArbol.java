
package twitter;

/**
 *
 * @author Jonathan
 */
public class NodoArbol {
    //atributos de la clase
    private Post mensaje; //el mensaje principal
    private NodoArbol izquierdo; //estos son
    private NodoArbol derecho;   //las respuestas
 
    public NodoArbol(Post mensaje) {
        this.mensaje = mensaje;
        this.izquierdo = null;
        this.derecho = null;
    }//final constructor vacio
    
    //hay que ver
    public Post getMensaje() {
        return mensaje;
    }

    public void setMensaje(Post elemento) {
        this.mensaje = elemento;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return "Mensaje=" + mensaje;
    }
}//final de la clase
