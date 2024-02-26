package twitter;
//By Brandon Vargas

import java.time.LocalDate;

public class Usuario { // se crean las variables de la calse ususario 

    private String name;
    private String lastName;
    private String email;
    private int id;
    private LocalDate registrationDate;

    public Usuario() {// se crea un constructor vacio 
    }

    public Usuario(String name, String lastName, String email, int id, LocalDate registrationDate) {// se crea un constructor super cargado
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.registrationDate = registrationDate;
    }

    public String getName() { // se crean los seters y geters 
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void createUser() { //se crean las funciones necesarias segun el diagrama de clases

    }

    public void deleteUser() {

    }

    public void changeInfo() {

    }

    public void seeNext() {

    }

    public void followUser(Usuario u) {

    }

    public void unfollowUser(Usuario u) {

    }

    @Override
    public String toString() {// se crea el toString
        return "Usuario{" + "name=" + name + ", lastName=" + lastName + ", email=" + email + ", id=" + id + ", registrationDate=" + registrationDate + '}';
    }

}
