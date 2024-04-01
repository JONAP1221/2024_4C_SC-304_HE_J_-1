package twitter;

public class Usuario {

    private String email;
    private String name;
    private int age;
    private int hash;
    private ListaSimple seguidores;
    private Pila pilaPosts; // Agregar atributo para la pila de posts

    public Usuario(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.hash = Math.abs(this.email.hashCode());
        this.seguidores = new ListaSimple(); // Inicializamos la lista de seguidores
        this.pilaPosts = new Pila(); // Inicializamos la pila de posts
    }

    public Pila getPilaPosts() {
        return pilaPosts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public ListaSimple getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(ListaSimple seguidores) {
        this.seguidores = seguidores;
    }

    @Override
    public String toString() {
        return " El usuario con el correo :" + email + ", se llama:" + name + ", y tiene una edad de:" + age + ", el id del usuario es:" + hash;
    }

}
