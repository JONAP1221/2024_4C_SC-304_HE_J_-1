package twitter;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.ArrayList;

public class Twitter {

    public static void main(String[] args) {

        boolean continuar = true;
        ListaDobleCircular usuarios = new ListaDobleCircular();
        usuarios.insertaMejorado(new Usuario("aaa", "Aaa", 14));
        usuarios.insertaMejorado(new Usuario("bbb", "Bbb", 12));
        usuarios.insertaMejorado(new Usuario("ccc", "Ccc", 1416));

        while (continuar) {
            try {
                String a = JOptionPane.showInputDialog("Opciones de ingreso \n"
                        + "1: agregar usuario \n"
                        + "2: agregar seguidor \n"
                        + "3: ver seguidores de un usuario \n"
                        + "4: crear y enviar post \n"
                        + "5: Mostrar post de usuario \n"
                        + "6: Mostrar el feed de un usuario \n"
                        + "9: ver usuarios con los id \n"
                        + "0: salir \n");
                if (a != null && !a.isEmpty()) { // Verificar si la cadena no está vacía
                    int numero = Integer.parseInt(a);

                    switch (numero) {
                        case 1:
                            usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
                            break;

                        case 2:
                            String correoUsuarioBase = JOptionPane.showInputDialog("Ingrese el correo de su usuario");
                            String correoUsuarioSeguir = JOptionPane.showInputDialog("Ingrese el correo de a quien usted quiere seguir:");

                            Usuario usuarioBase = usuarios.buscarUsuarioPorCorreo(correoUsuarioBase);
                            Usuario usuarioSeguir = usuarios.buscarUsuarioPorCorreo(correoUsuarioSeguir);

                            if (usuarioBase == null || usuarioSeguir == null) {
                                JOptionPane.showMessageDialog(null, "Uno o ambos usuarios no existen.");
                            } else if (usuarioBase.getEmail().equals(usuarioSeguir.getEmail())) {
                                JOptionPane.showMessageDialog(null, "No te puedes seguir a ti mismo.");
                            } else if (usuarioBase.getSeguidores().existeSeguidor(usuarioSeguir)) {
                                JOptionPane.showMessageDialog(null, "Este usuario ya sigue a " + correoUsuarioSeguir);
                            } else if (usuarioBase != null && usuarioSeguir != null) {
                                usuarioBase.getSeguidores().insertarSeguidor(usuarioSeguir);
                                JOptionPane.showMessageDialog(null, "Usuario " + correoUsuarioBase + " ahora sigue a " + correoUsuarioSeguir);
                            } else {
                                JOptionPane.showMessageDialog(null, "Uno o ambos usuarios no existen.");
                            }
                            break;

                        case 3:
                            String correoUsuario = JOptionPane.showInputDialog("Ingrese el correo del usuario del cual desea ver los seguidores:");
                            Usuario usuario = usuarios.buscarUsuarioPorCorreo(correoUsuario);
                            if (usuario != null) {
                                ListaSimple seguidoresUsuario = usuario.getSeguidores();
                                if (seguidoresUsuario != null) {
                                    NodoListaSimple cabezaSeguidores = seguidoresUsuario.getCabeza();
                                    if (cabezaSeguidores != null) {
                                        StringBuilder seguidoresTexto = new StringBuilder();
                                        seguidoresTexto.append("El ususario ").append(usuario.getName()).append(" sigue a las personas:\n");
                                        NodoListaSimple nodoSeguidor = cabezaSeguidores;
                                        while (nodoSeguidor != null) {
                                            Usuario seguidor = nodoSeguidor.getUsuario();
                                            seguidoresTexto.append("- ").append(seguidor.getName()).append("\n");
                                            nodoSeguidor = nodoSeguidor.getSiguiente();
                                        }
                                        JOptionPane.showMessageDialog(null, seguidoresTexto.toString());
                                    } else {
                                        JOptionPane.showMessageDialog(null, usuario.getName() + " no tiene seguidores.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, usuario.getName() + " no tiene seguidores.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoUsuario);
                            }
                            break;

                        case 4:
                            String correoDestino = JOptionPane.showInputDialog("Ingrese el correo del usuario bajo el cual desea subir un post:");// se pide el ususario
                            Usuario usuarioDestino = usuarios.buscarUsuarioPorCorreo(correoDestino);// se busca por correo 
                            if (usuarioDestino != null) {// si el ususario existe 
                                String mensajePost = JOptionPane.showInputDialog("Ingrese el mensaje del post:");// se pide el texto del post 
                                LocalDate fechaActual = LocalDate.now();// se toma la fecha del momento a la hora de publicar el post 
                                Post nuevoPost = new Post(mensajePost, usuarioDestino);// se crea un nuevo post
                                usuarioDestino.getPilaPosts().apilar(nuevoPost, mensajePost);// se apila el post 
                                JOptionPane.showMessageDialog(null, "Post creado y publicado bajo el correo: " + correoDestino);// se muetra que se creo el post bajo el destino 
                            } else {// en caso de no haber ningun correo 
                                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoDestino);
                            }
                            break;
                        case 5:
                            String correoUsuarioa = JOptionPane.showInputDialog("Ingrese el correo del usuario del cual desea ver los posts:");// se pide el correo del ususario 
                            Usuario usuarioa = usuarios.buscarUsuarioPorCorreo(correoUsuarioa);// se busca el ususario en la lista doble
                            if (usuarioa != null) {// de haberlo
                                Pila pilaPosts = usuarioa.getPilaPosts();// de la pila se saca un post
                                if (!pilaPosts.esVacia()) {// si hay algo en ese ndood // es un tipo de metodo recursivo
                                    JOptionPane.showMessageDialog(null, "Posts de " + usuarioa.getName() + ":");//se muestran los post
                                    pilaPosts.mostrarPilaConMensajes(); // se muestran los post 
                                } else {
                                    JOptionPane.showMessageDialog(null, "El usuario no tiene posts.");// en caso de == null ser verdad se notifica 
                                }
                            } else {// en caso de que no se encontrase un correo 
                                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoUsuarioa);
                            }
                            break;
                        case 6:// en este caso 6 se almacenan los post del usuario y sus seguidoires en un array 
                            String correoUsuarioConsulta = JOptionPane.showInputDialog("Ingrese el correo del usuario para consultar sus posts:");// se crea una variable tipo string para pedir informacion por medio de un joptions
                            Usuario usuarioConsulta = usuarios.buscarUsuarioPorCorreo(correoUsuarioConsulta);// se crea una variable tipo usuario para usar la varable anterior para buscar este correo en los nodos de la ldb 
                            if (usuarioConsulta != null) {// si es distinto a null 
                                ArrayList<Post> allPosts = new ArrayList<>();// se crea un array vacio 
                                // se crea una "pila/nodo" en el cual se almacena el post del ususario base 
                                Pila pilaPostsUsuario = usuarioConsulta.getPilaPosts();
                                allPosts = pilaPostsUsuario.obtener(allPosts);
                                ListaSimple seguidoresUsuarioConsulta = usuarioConsulta.getSeguidores();// de la lista simple del ussuario se saca un seguidor 
                                if (seguidoresUsuarioConsulta != null) {// en caso de haberlos y no estar vacia 
                                    NodoListaSimple nodoSeguidor = seguidoresUsuarioConsulta.getCabeza();
                                    while (nodoSeguidor != null) {// se crea un siclo que recorre hasta estar vacia 
                                        Usuario seguidor = nodoSeguidor.getUsuario();// se crea una variale tipo ususario apra almacenar el seguidor 
                                        Pila pilaPostsSeguidor = seguidor.getPilaPosts();// de la pila de ese usuario sacamos los post
                                        while (!pilaPostsSeguidor.esVacia()) {// si la pila de este ususario no esa vacia 
                                            Post postSeguidor = pilaPostsSeguidor.desapilar();// se desapila 
                                            allPosts.add(postSeguidor);// se anade al aaray 
                                        }
                                        nodoSeguidor = nodoSeguidor.getSiguiente();// se vanza al sigueinte seguidor 
                                    }
                                }
                                if (!allPosts.isEmpty()) {// si el array no esta vacio 
                                    JOptionPane.showMessageDialog(null, "Posts de " + usuarioConsulta.getName() + " y usuarios seguidos:");// el feed del ususario 
                                    for (Post post : allPosts) {// se recorren el array
                                        JOptionPane.showMessageDialog(null, post);// se imprime
                                    }
                                } else {// en caso de que no hayan post en el array 
                                    JOptionPane.showMessageDialog(null, "No hay posts para mostrar.");// se notifica 
                                }
                            } else {// no hay ningun usuario con ese correo 
                                JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoUsuarioConsulta);
                            }
                            break;

                        case 9:
                            JOptionPane.showConfirmDialog(null, usuarios);// se miestran los usuarios creados 
                            break;
                        case 0:
                            continuar = false;// se sale del menu 
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
                            break;
                    }//final switch
                }
            }//Final del try
            catch (Exception e) {
                System.out.println("Error capa 8: " + e.getMessage());
            }//final del catch
        }//final del while
    }//final del main
}//final de la clase
