
package twitter;

/**
 *
 * @author Jonathan
 */
public class NodoCola {
    //atributos de la clase
    private Usuario user;
    private NodoCola atras;

    public NodoCola(Usuario user) {
        this.user = user;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public NodoCola getSiguiente() {
        return atras;
    }

    public void setSiguiente(NodoCola atras) {
        this.atras = atras;
    }

    @Override
    public String toString() {
        return "NodoCola{" + "dato=" + user + ", atras=" + atras + '}';
    }

    
}//final de la clase
