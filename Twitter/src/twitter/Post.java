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

    /*public static Post newPost(Usuario user) {//pasar a usuario?
        return new Post(JOptionPane.showInputDialog("Qué estás pensando," + user.getName() + "?"), user);

    }//final del metodo newPost*/
    public static Post newPost(Usuario user) {
        String userInput = JOptionPane.showInputDialog("Qué estás pensando, " + user.getName() + "?");
        String[] words = userInput.split("\\s+");
        int wordCount = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;
            }
        }
        if (wordCount > 3) {
            JOptionPane.showMessageDialog(null, "Has excedido el límite de 100 palabras.");
            return null; // Retorna null para indicar que no se creó el post
        } else {
            return new Post(userInput, user);
        }
    }

    public static Post respuesta(Usuario user, Usuario original) {
        String respuesta = JOptionPane.showInputDialog("Respondiendo al post de " + original.getName() + ".");

        // Verificar si la longitud de la respuesta es mayor a 100 caracteres
        if (respuesta.length() > 100) {
            // Si la respuesta excede los 100 caracteres, truncarla a 100 caracteres
            respuesta = respuesta.substring(0, 100);
        }

        return new Post(respuesta, user);

        // Otras partes de tu clase
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
        return user.getName() + "          " + fecha + "\n" + msj + "\n\n";
    }//final metodo toString

}//final de la clase
