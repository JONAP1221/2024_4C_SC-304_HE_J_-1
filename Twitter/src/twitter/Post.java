package twitter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class Post {

    //atributos de la clase
    private String msj;
    private String user;
    private String fecha;

    public Post(String msj, Usuario user) {
        this.msj = msj;
        this.user = user.getEmail();
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

    public String getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user.getEmail();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }//final gets y sets

    public static Post newPost(Usuario user) {
        String userInput = JOptionPane.showInputDialog("Qué estás pensando, " + user.getName() + "?");
        int charCount = userInput.replaceAll("\\s", "").length(); // Contar caracteres excluyendo los espacios

        if (charCount > 100) {
            JOptionPane.showMessageDialog(null, "Has excedido el límite de 100 caracteres.");
            return null; // Retorna null para indicar que no se creó el post
        } else {
            return new Post(userInput, user);
        }
    }

    public static Post respuesta(Usuario user, Usuario original) {
        String respuesta = JOptionPane.showInputDialog("Respondiendo al post de " + original.getName() + ".");
        int charCount = respuesta.replaceAll("\\s", "").length(); // Contar caracteres excluyendo los espacios

        if (charCount > 100) {
            JOptionPane.showMessageDialog(null, "Has excedido el límite de 100 caracteres.");
            return null; // Retorna null para indicar que no se creó el post
        } else {
            return new Post(respuesta, user);
        }
    }//final del metodo newPost
    
    public static Post respuesta(Usuario user, Usuario original, String respuesta) {
        int charCount = respuesta.replaceAll("\\s", "").length(); // Contar caracteres excluyendo los espacios

        if (charCount > 100) {
            JOptionPane.showMessageDialog(null, "Has excedido el límite de 100 caracteres.");
            return null; // Retorna null para indicar que no se creó el post
        } else {
            return new Post(respuesta, user);
        }
    }//final del metodo newPost
    

    public void deletePost(Usuario user, Post post) {

        //user.getListaPost.eliminar(post);
    }//final del metodo deletePost

    public void replyPost() {

        //Este metodo esta ya implementado en la clase Arbol
    }//Final del metodo replyPost

    public static String obtenerFecha() {
        LocalDateTime fecha = LocalDateTime.now(); //la variable fecha tiene la fecha actual
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //formato contiene el formato en el que se representara la fecha
        String fechaHora = fecha.format(formato); // fechaHora es la fecha actual con el formato establecido arriba
        return fechaHora; // devuelve la fecha formateada
    }//Final del metodo obtenerFecha

    public static LocalDateTime compararFecha(String fechaTexto) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Define el formato
        LocalDateTime fecha = LocalDateTime.parse(fechaTexto, formato); // Convierte el String a LocalDateTime
        System.out.println("Fecha parseada: " + fecha);
        return fecha;
    }//final del metodo comparar fecha

    @Override
    public String toString() {
        return user + "          " + fecha + "\n" + msj + "\n\n";
    }//final metodo toString

}//final de la clase
