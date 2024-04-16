package twitter;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import twitter.NodoListaDobleCircular;
import static twitter.Twitter.usuarios;

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

    public Usuario() {
    }

    public void setSeguidores(ListaSimple seguidores) {
        this.seguidores = seguidores;
    }

    public void insertarSeguidor() {
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
    }

    public void verSeguidores() {
        String correoUsuario = JOptionPane.showInputDialog("Ingrese el correo del usuario del cual desea ver los seguidores:");
        Usuario usuario = usuarios.buscarUsuarioPorCorreo(correoUsuario);
        if (usuario != null) {
            ListaSimple seguidoresUsuario = usuario.getSeguidores();
            if (seguidoresUsuario != null) {
                NodoListaSimple cabezaSeguidores = seguidoresUsuario.getCabeza();
                if (cabezaSeguidores != null) {
                    StringBuilder seguidoresTexto = new StringBuilder();
                    seguidoresTexto.append("El usuario ").append(usuario.getName()).append(" sigue a las personas:\n");
                    NodoListaSimple nodoSeguidor = cabezaSeguidores;
                    while (nodoSeguidor != null) {
                        if (comparar(nodoSeguidor)) {
                            Usuario seguidor = nodoSeguidor.getUsuario();
                            seguidoresTexto.append("- ").append(seguidor.getName()).append("\n");
                        }//final if
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
    }//final del emtodo verSeguidores

    private boolean comparar(NodoListaSimple nodoSeguidor) {
        boolean x = false;
        NodoListaDobleCircular cabeza = usuarios.getCabeza();
        if (cabeza != null) {
            NodoListaDobleCircular aux = cabeza;
            aux = aux.getSiguiente();
            while (aux != cabeza) {
                if (aux.getDato() == nodoSeguidor.getUsuario()) {
                    x = true;
                }//final if
                aux = aux.getSiguiente();
            }//final while
        }//final if
        return x;
    }//final del metodo actualizarListaSeguidores

    public void eliminarSeguidor() {
        String correoUsuarioBase = JOptionPane.showInputDialog("Ingrese el correo de su usuario");
        String correoUsuarioEliminar = JOptionPane.showInputDialog("Ingrese el correo del usuasrio que desea dejar de seguir:");

        Usuario usuarioBase = usuarios.buscarUsuarioPorCorreo(correoUsuarioBase);
        Usuario usuarioEliminar = usuarios.buscarUsuarioPorCorreo(correoUsuarioEliminar);

        if (usuarioBase.getEmail().equals(usuarioEliminar.getEmail())) {
            JOptionPane.showMessageDialog(null, "no te sigues a ti mismo por lo tanto no te puedes eliminar");
        } else if (usuarioBase.getSeguidores().noExisteSeguidor(usuarioEliminar)) {
            JOptionPane.showMessageDialog(null, "No sigues a este usuario");
        } else if (usuarioBase.getSeguidores().existeSeguidor(usuarioEliminar)) {
            usuarioBase.getSeguidores().eliminarSeguidor(usuarioEliminar);
            JOptionPane.showMessageDialog(null, "Ya no sigues al usuario" + usuarioEliminar.getEmail());
        }
    }//final del metodo actualizarListaSeguidores

    public void crearPost() {
        String correoDestino = JOptionPane.showInputDialog("Ingrese el correo del usuario bajo el cual desea subir un post:");// se pide el ususario
        Usuario usuarioDestino = usuarios.buscarUsuarioPorCorreo(correoDestino);// se busca por correo 
        if (usuarioDestino != null) {// si el ususario existe  
            Arbol nuevoArbol = new Arbol();
            Post x = Post.newPost(usuarioDestino);
            nuevoArbol.agregar(x);
            usuarioDestino.getPilaPosts().apilar(nuevoArbol);// se apila el post 
            JOptionPane.showMessageDialog(null, "Post creado y publicado bajo el correo: " + correoDestino);// se muetra que se creo el post bajo el destino 
        } else {// en caso de no haber ningun correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoDestino);
        }
    }

    public void mostrarPost() {
        String correoUsuarioa = JOptionPane.showInputDialog("Ingrese el correo del usuario del cual desea ver los posts:");// se pide el correo del ususario 
        Usuario usuarioa = usuarios.buscarUsuarioPorCorreo(correoUsuarioa);// se busca el ususario en la lista doble
        if (usuarioa != null) {// de haberlo
            Pila pilaPosts = usuarioa.getPilaPosts();// de la pila se saca un post
            if (!pilaPosts.esVacia()) {// si hay algo en ese nodo // es un tipo de metodo recursivo
                JOptionPane.showMessageDialog(null, "Posts de " + usuarioa.getName() + ":");//se muestran los post
                pilaPosts.mostrarPilaConMensajes(); // se muestran los post 
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no tiene posts.");// en caso de == null ser verdad se notifica 
            }
        } else {// en caso de que no se encontrase un correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoUsuarioa);
        }
    }

    public void mostrarFeed() {
        String correoUsuarioConsulta = JOptionPane.showInputDialog("Ingrese el correo del usuario para consultar sus posts:");// se crea una variable tipo string para pedir informacion por medio de un joptions
        Usuario usuarioConsulta = usuarios.buscarUsuarioPorCorreo(correoUsuarioConsulta);// se crea una variable tipo usuario para usar la varable anterior para buscar este correo en los nodos de la ldb 
        if (usuarioConsulta != null) {// si es distinto a null 
            ArrayList<Arbol> allPosts = new ArrayList<>();// se crea un array vacio 
            // se crea una "pila/nodo" en el cual se almacena el post del usuario base 
            Pila pilaPostsUsuario = usuarioConsulta.getPilaPosts();
            allPosts = pilaPostsUsuario.obtener(allPosts);//se agregan los Post de la pila al array
            ListaSimple seguidoresUsuarioConsulta = usuarioConsulta.getSeguidores();// de la lista simple del ussuario se saca un seguidor 
            if (seguidoresUsuarioConsulta != null) {// en caso de haberlos y no estar vacia 
                NodoListaSimple nodoSeguidor = seguidoresUsuarioConsulta.getCabeza();
                while (nodoSeguidor != null) {// se crea un siclo que recorre hasta estar vacia 
                    Usuario seguidor = nodoSeguidor.getUsuario();// se crea una variale tipo ususario apra almacenar el seguidor 
                    Pila pilaPostsSeguidor = seguidor.getPilaPosts();// de la pila de ese usuario sacamos los post
                    allPosts = pilaPostsSeguidor.obtener(allPosts);
                    nodoSeguidor = nodoSeguidor.getSiguiente();// se vanza al sigueinte seguidor 
                }//final while
            }//final if
            if (!allPosts.isEmpty()) {// si el array no esta vacio 
                JOptionPane.showMessageDialog(null, "Posts de " + usuarioConsulta.getName() + " y usuarios seguidos:");// el feed del ususario 
                for (Arbol post : allPosts) {// se recorren el array
                    post.mostrar(post);
                }//final for
            } else {// en caso de que no hayan post en el array 
                JOptionPane.showMessageDialog(null, "No hay posts para mostrar.");// se notifica 
            }
        } else {// no hay ningun usuario con ese correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico " + correoUsuarioConsulta);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return " El usuario con el correo :" + email + ", se llama:" + name + ", y tiene una edad de:" + age + ", el id del usuario es:" + hash;
    }

}
