package twitter;

public class Usuario {

    private String email;
    private String name;
    private int age;
    private int hash;

    public String getEmail() {
        return email;
    }

    public Usuario(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.hash = Math.abs(this.email.hashCode());
    }

    public Usuario() {
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

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", name=" + name + ", age=" + age + ", hash=" + hash + '}';
    }

   

}
