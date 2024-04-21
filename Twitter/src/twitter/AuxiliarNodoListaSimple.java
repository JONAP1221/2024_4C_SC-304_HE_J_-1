package twitter;

public class AuxiliarNodoListaSimple {

    private String correo;
    private AuxiliarNodoListaSimple siguiente;

    public AuxiliarNodoListaSimple(String correo) {
        this.correo = correo;
        this.siguiente = null;
    }

    public String getCorreo() {
        return correo;
    }

    public AuxiliarNodoListaSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(AuxiliarNodoListaSimple siguiente) {
        this.siguiente = siguiente;
    }
}
