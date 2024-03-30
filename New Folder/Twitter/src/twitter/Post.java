
package twitter;

import java.time.LocalDate;

/**
 *
 * @author Jonathan
 */
public class Post {
    //atributos de la clase
    private String msj;
    private Usuario user;
    private LocalDate fecha;

    public Post(String msj, Usuario user, LocalDate fecha) {
        this.msj = msj;
        this.user = user;
        this.fecha = fecha;
    }

 

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }//final gets y sets
    
    public void newPost(){
        
    //Lo demás para crear un nuevo post 
    //y asignarselo al usuario correspondiente
        
    }//final del metodo newPost
    
    public void deletePost(Post post){
        
        //lo necesario para eliminar el post
        
    }//final del metodo deletePost
    
    public void replyPost(){
        
        //lo necesario para responder a un post
        //recordar que solo se pueden hacer 2 respuestas por post
        //si ya hay 2 mostrar un mensaje de error
        
    }//Final del metodo replyPost

    @Override
    public String toString() {
        return "Post{" + "msj=" + msj + ", Usuario=" + user + ", fecha=" + fecha + '}';
    }
    
}//final de la clase
