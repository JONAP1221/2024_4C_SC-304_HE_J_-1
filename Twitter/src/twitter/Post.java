package twitter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jonathan
 */
public class Post {

    //atributos de la clase
    private String msj;
    private Usuario user;
    private String fecha;

    public Post(String msj, Usuario user) {
        this.msj = msj;
        this.user = user;
        this.fecha = obtenerFecha();
    }//final constructor lleno

    public Post() {
    }//Final constructor vacio

    //gets y sets
    public String getMsj() {
        return msj;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }//final gets y sets

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

    public static String obtenerFecha() {
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Crear un formateador de fecha y hora
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Formatear la fecha y hora actual
        String fechaHoraFormateada = fechaHoraActual.format(formateador);

        // Retornar la fecha y hora formateadas
        return fechaHoraFormateada;
    }

    @Override
    public String toString() {
        return user + "          " + fecha + "\n" + msj + "\n\n";
    }//final metodo toString

}//final de la clase
