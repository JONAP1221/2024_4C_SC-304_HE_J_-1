package twitter;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.ArrayList;

public class Twitter {

    public static void main(String[] args) {

        boolean continuar = true;
        ListaDobleCircular usuarios = new ListaDobleCircular();
        ListaSimple seguidores = new ListaSimple();
        usuarios.insertaMejorado(new Usuario("aaa", "Aaa", 14));
        usuarios.insertaMejorado(new Usuario("bbb", "Bbb", 12));
        usuarios.insertaMejorado(new Usuario("ccc", "Ccc", 1416));

        Pila pilapost = new Pila();

        //try {
        while (continuar) {
            String a;
            a = JOptionPane.showInputDialog("Opciones de ingreso \n" + "1: agregar usuario \n" + "2: agregar seguidor \n" + "3: ver seguidores de un usuario \n" + "4: crear y enviar post \n" + "5: Mostrar post de usuario \n" + "9: ver usuarios con los id \n" + "0: salir \n");
            if (a != null && !a.isEmpty()) { // Verificar si la cadena no está vacía
                int numero = Integer.parseInt(a);

                switch (numero) {
                    case 1:
                        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
                        break;

                    case 2:
                        String correoUsuarioBase = JOptionPane.showInputDialog("Ingrese el coreo de su usuario");
                        String correoUsuarioSeguir = JOptionPane.showInputDialog("Ingrese el correo de a quien usted quiere seguir:");

                        Usuario usuarioBase = usuarios.buscarUsuarioPorCorreo(correoUsuarioBase);
                        Usuario usuarioSeguir = usuarios.buscarUsuarioPorCorreo(correoUsuarioSeguir);

                        if (usuarioBase == null || usuarioSeguir == null) {
                            JOptionPane.showMessageDialog(null, "Uno o ambos usuarios no existen.");
                        } else if (usuarioBase.getEmail().equals(usuarioSeguir.getEmail())) {
                            System.out.println("No te puedes seguir a ti mismo.");
                        } else if (usuarioBase.getSeguidores().existeSeguidor(usuarioSeguir)) {
                            System.out.println("Este usuario ya sigue a " + correoUsuarioSeguir);
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
                        String correoDestino = JOptionPane.showInputDialog("Ingrese el correo del usuario al que desea enviar un post:");
                        Usuario usuarioDestino = usuarios.buscarUsuarioPorCorreo(correoDestino);
                        if (usuarioDestino != null) {
                            String mensajePost = JOptionPane.showInputDialog("Ingrese el mensaje del post:");
                            LocalDate fechaActual = LocalDate.now();
                            Post nuevoPost = new Post(mensajePost, usuarioDestino, fechaActual);

                            // Apilar el nuevo post en la pila, incluyendo el mensaje
                            usuarioDestino.getPilaPosts().apilar(nuevoPost, mensajePost);

                            JOptionPane.showMessageDialog(null, "Post creado y enviado a " + correoDestino);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoDestino);
                        }
                        break;
                    // En el case 5
                    case 5:
                        String correoUsuarioa = JOptionPane.showInputDialog("Ingrese el correo del usuario del cual desea ver los posts:");
                        Usuario usuarioa = usuarios.buscarUsuarioPorCorreo(correoUsuarioa);
                        if (usuarioa != null) {
                            Pila pilaPosts = usuarioa.getPilaPosts();
                            if (!pilaPosts.esVacia()) {
                                System.out.println("Posts de " + usuarioa.getName() + ":");
                                pilaPosts.mostrarPilaConMensajes(); // Método para mostrar la pila con mensajes
                            } else {
                                System.out.println("El usuario no tiene posts.");
                            }
                        } else {
                            System.out.println("No se encontró ningún usuario con el correo electrónico " + correoUsuarioa);
                        }
                        break;
                    case 6:// en este caso 6 se almacenan los post del usuario y sus seguidoires en un array 
                        String correoUsuarioConsulta = JOptionPane.showInputDialog("Ingrese el correo del usuario para consultar sus posts:");// se crea una variable tipo string para pedir informacion por medio de un joptions
                        Usuario usuarioConsulta = usuarios.buscarUsuarioPorCorreo(correoUsuarioConsulta);// se crea una variable tipo usuario para usar la varable anterior para buscar este correo en los nodos de la ldb 
                        if (usuarioConsulta != null) {// si es distinto a null 
                            ArrayList<Post> allPosts = new ArrayList<>();// se crea un array vacio 
                            // se crea una "pila/nodo" en el cual se almacena el post del ususario base 
                            Pila pilaPostsUsuario = usuarioConsulta.getPilaPosts();
                            while (!pilaPostsUsuario.esVacia()) {// mientras la pila no este vacia lo sacamos y se anade al array 
                                Post postUsuario = pilaPostsUsuario.desapilar();// se anade al array por medio del metodo desapilar 
                                allPosts.add(postUsuario);// se anade el post a el array
                            }// como ya tenemos los pots del ususario pasamos con los de las cuentas que este sigue 
                            ListaSimple seguidoresUsuarioConsulta = usuarioConsulta.getSeguidores();
                            if (seguidoresUsuarioConsulta != null) {
                                NodoListaSimple nodoSeguidor = seguidoresUsuarioConsulta.getCabeza();
                                while (nodoSeguidor != null) {
                                    Usuario seguidor = nodoSeguidor.getUsuario();
                                    Pila pilaPostsSeguidor = seguidor.getPilaPosts();
                                    while (!pilaPostsSeguidor.esVacia()) {
                                        Post postSeguidor = pilaPostsSeguidor.desapilar();
                                        allPosts.add(postSeguidor);
                                    }
                                    nodoSeguidor = nodoSeguidor.getSiguiente();
                                }
                            }

                            // Imprimir los posts
                            if (!allPosts.isEmpty()) {
                                System.out.println("Posts de " + usuarioConsulta.getName() + " y usuarios seguidos:");
                                for (Post post : allPosts) {
                                    System.out.println(post);
                                }
                            } else {
                                System.out.println("No hay posts para mostrar.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoUsuarioConsulta);
                        }
                        break;

                    case 9:
                        JOptionPane.showConfirmDialog(null, usuarios);
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
            }
        }

    }
}
