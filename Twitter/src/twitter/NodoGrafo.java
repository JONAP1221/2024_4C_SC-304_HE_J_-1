package twitter;

/**
 *
 * @author Jonathan
 */
public class NodoGrafo {

    private Usuario user;
    private NodoGrafo enco;

    public NodoGrafo() {
    }

    public NodoGrafo(Usuario user, NodoGrafo enco) {
        this.user = user;
        this.enco = enco;
    }

    //hay que ver

   

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public NodoGrafo getEnco() {
        return enco;
    }

    public void setEnco(NodoGrafo enco) {
        this.enco = enco;
    }
    
     @Override
    public String toString() {
        return "NodoGrafo{" + "user=" + user + ", enco=" + enco + '}';
    }
    
}//final de la clase
