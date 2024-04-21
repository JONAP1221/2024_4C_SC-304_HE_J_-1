
package twitter;

/**
 *
 * @author Jonathan
 */
public class NodoCola {
    //atributos de la clase
    private Usuario user;
    private NodoCola atras;
    private Arbol post;

    public NodoCola getAtras() {
        return atras;
    }

    public NodoCola(Arbol post) {
        this.post = post;
    }

    
    public void setAtras(NodoCola atras) {
        this.atras = atras;
    }

    public Arbol getPost() {
        return post;
    }

    public void setPost(Arbol post) {
        this.post = post;
    }
    
    

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
