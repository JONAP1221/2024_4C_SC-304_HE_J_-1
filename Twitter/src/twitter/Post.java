package twitter;

import java.time.LocalDate;

public class Post {

    //atributos de la clase
    private String msj;
    private Usuario user;
    private LocalDate fecha;
    private String mensaje;

    private Usuario autor;

    public LocalDate getFecha() {
        return fecha;
    }

    public Post(String msj, Usuario user, LocalDate fecha) {
        this.msj = msj;
        this.user = user;
        this.fecha = fecha;
        this.mensaje = mensaje;

    }//final constructor lleno

    public Post() {
    }//Final constructor vacio

    //gets y sets
    public String getMsj() {
        return msj;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void newPost() {

        //Lo demás para crear un nuevo post 
        //y asignarselo al usuario correspondiente
    }//final del metodo newPost

    public void deletePost(Post post) {

        //lo necesario para eliminar el post
    }//final del metodo deletePost

    public void replyPost() {

        //lo necesario para responder a un post
        //recordar que solo se pueden hacer 2 respuestas por post
        //si ya hay 2 mostrar un mensaje de error
    }//Final del metodo replyPost

    @Override
    public String toString() {
        return "Post{" + "msj=" + msj + ", user=" + user + ", fecha=" + fecha + '}';
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}//final de la clase
