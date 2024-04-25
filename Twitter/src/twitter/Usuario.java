package twitter;

//import java.util.ArrayList;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static twitter.Twitter.usuarios;

public class Usuario {

    private String email;
    private String name;
    private int age;
    private int hash;
    private ListaSimple seguidores;
    private Pila pilaPosts; // Agregar atributo para la pila de posts
    private String fechaRegistro;

    public Usuario(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.hash = Math.abs(this.email.hashCode());
        this.seguidores = new ListaSimple(); // Inicializamos la lista de seguidores
        this.pilaPosts = new Pila(); // Inicializamos la pila de posts
        this.fechaRegistro = Post.obtenerFecha();
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public void insertarSeguidor(Usuario base, Usuario destino) {
        if (base == null || destino == null) {
            JOptionPane.showMessageDialog(null, "Uno o ambos usuarios no existen.");
        } else if (base.getEmail().equals(destino.getEmail())) {
            JOptionPane.showMessageDialog(null, "No te puedes seguir a ti mismo.");
        } else if (base.getSeguidores().existeSeguidor(destino)) {
            JOptionPane.showMessageDialog(null, "Este usuario ya sigue a " + destino.getEmail());
        } else {

            if (!destino.getPilaPosts().esVacia()) {
                base.getSeguidores().insertarSeguidor(destino);
                JOptionPane.showMessageDialog(null, "Usuario " + base.getEmail() + " ahora sigue a " + destino.getEmail());
            } else {
                JOptionPane.showMessageDialog(null, "No puedes seguir a un usuario si no tiene post. ");
            }

        }
    }

    public void verSeguidores(Usuario base) {
        if (base != null) {
            ListaSimple seguidoresUsuario = base.getSeguidores();
            if (seguidoresUsuario != null) {
                NodoListaSimple cabezaSeguidores = seguidoresUsuario.getCabeza();
                if (cabezaSeguidores != null) {
                    StringBuilder seguidoresTexto = new StringBuilder();
                    seguidoresTexto.append("El usuario ").append(base.getName()).append(" sigue a las personas:\n");
                    while (cabezaSeguidores != null) {
                        Usuario seguidor = usuarios.buscarUsuarioPorCorreo(cabezaSeguidores.getCorreo());
                        if (comparar(cabezaSeguidores)) {

                            seguidoresTexto.append("- ").append(seguidor.getName()).append("\n");
                        }//final if
                        else {
                            base.getSeguidores().eliminarSeguidor(seguidor);
                        }//final else
                        cabezaSeguidores = cabezaSeguidores.getSiguiente();
                    }
                    JOptionPane.showMessageDialog(null, seguidoresTexto.toString());
                } else {
                    JOptionPane.showMessageDialog(null, base.getName() + " no tiene seguidores.");
                }
            } else {
                JOptionPane.showMessageDialog(null, base.getName() + " no tiene seguidores.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico ");
        }
    }//final del emtodo verSeguidores

    private boolean comparar(NodoListaSimple nodoSeguidor) {
        boolean x = false;
        NodoListaDobleCircular cabeza = usuarios.getCabeza();
        if (cabeza != null) {
            NodoListaDobleCircular aux = cabeza;
            if (aux.getDato().getEmail().equals(nodoSeguidor.getCorreo())) {
                x = true;
            }//final if
            aux = aux.getSiguiente();
            while (aux != cabeza) {
                if (aux.getDato().getEmail().equals(nodoSeguidor.getCorreo())) {
                    x = true;
                }//final if
                aux = aux.getSiguiente();
            }//final while
        }//final if
        return x;
    }//final del metodo actualizarListaSeguidores

    public void eliminarSeguidor(Usuario base, Usuario destino) {
        if (base.getEmail().equals(destino.getEmail())) {
            JOptionPane.showMessageDialog(null, "no te sigues a ti mismo por lo tanto no te puedes eliminar");
        } else if (base.getSeguidores().noExisteSeguidor(destino)) {
            JOptionPane.showMessageDialog(null, "No sigues a este usuario");
        } else if (base.getSeguidores().existeSeguidor(destino)) {
            base.getSeguidores().eliminarSeguidor(destino);
            JOptionPane.showMessageDialog(null, "Ya no sigues al usuario" + destino.getEmail());
        }
    }//final del metodo actualizarListaSeguidores

    public void crearPost(Usuario base) {
        if (base != null) {// si el ususario existe  
            Arbol nuevoArbol = new Arbol();
            Post x = Post.newPost(base);
            if (x != null) {
                nuevoArbol.agregar(x);
                base.getPilaPosts().apilar(nuevoArbol);// se apila el post 
                JOptionPane.showMessageDialog(null, "Post creado y publicado bajo el correo: " + base.getEmail());// se muetra que se creo el post bajo el destino 
            }
        } else {// en caso de no haber ningun correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico. ");
        }
    }

    public void mostrarPost(Usuario base) {
        if (base != null) {// de haberlo
            Pila pilaPostUsuario = base.getPilaPosts();// de la pila se saca un post
            if (!pilaPostUsuario.esVacia()) {// si hay algo en ese nodo // es un tipo de metodo recursivo
                JOptionPane.showMessageDialog(null, "Posts de " + base.getName() + ":");//se muestran los post
                pilaPostUsuario.mostrarPilaConMensajes(); // se muestran los post 
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no tiene posts.");// en caso de == null ser verdad se notifica 
            }
        } else {// en caso de que no se encontrase un correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico ");
        }
    }

    public void eliminarPost(Usuario base) {
        if (base != null) {// de haberlo
            Pila pilaPosts = base.getPilaPosts();// de la pila se saca un post
            if (!pilaPosts.esVacia()) {// si hay algo en ese nodo // es un tipo de metodo recursivo
                JOptionPane.showMessageDialog(null, "Cargando post de " + base.getName() + "...");//se muestran los post
                pilaPosts.eliminarPilaConMensajes(base);// se muestran los post 
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no tiene posts.");// en caso de == null ser verdad se notifica 
            }
        } else {// en caso de que no se encontrase un correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico ");
        }
    }

    public void mostrarFeedlista(Usuario base) {
        Cola colaPost = new Cola();
        ListaSimple listaFeed = new ListaSimple();
        Pila pilaPostsUsuario = base.getPilaPosts();
        listaFeed = pilaPostsUsuario.obtener(listaFeed);
        ListaSimple seguidoresUsuarioConsulta = base.getSeguidores();
        if (seguidoresUsuarioConsulta != null) {
            NodoListaSimple nodoSeguidor = seguidoresUsuarioConsulta.getCabeza();
            while (nodoSeguidor != null) {
                Usuario seguidor = Twitter.usuarios.buscarUsuarioPorCorreo(nodoSeguidor.getCorreo());
                Pila pilaPostsSeguidor = seguidor.getPilaPosts();
                listaFeed = pilaPostsSeguidor.obtener(listaFeed);
                nodoSeguidor = nodoSeguidor.getSiguiente();
            }
            if (listaFeed.getCabeza() != null) {
                NodoListaSimple aux = listaFeed.getCabeza();
                while (aux != null) {
                    colaPost.encolar(aux.getPost());

                    aux = aux.getSiguiente();

                }

            }
        }

        if (!colaPost.esVacia()) {
            JOptionPane.showMessageDialog(null, "Posts de " + base.getName() + " y usuarios seguidos:");
            colaPost.desencolar();
        } else {
            JOptionPane.showMessageDialog(null, "No hay posts para mostrar.");
        }
    }/*
    public void mostrarFeedlista(Usuario base) {
        Cola colaFeed = new Cola();
        Pila pilaPostsUsuario = base.getPilaPosts();
        listaFeed = pilaPostsUsuario.obtener(listaFeed);
        AuxiliarListaSimple seguidoresUsuarioConsulta = base.getSeguidores();
        if (seguidoresUsuarioConsulta != null) {
            NodoAuxiliarListaSimple nodoSeguidor = seguidoresUsuarioConsulta.getCabeza();
            while (nodoSeguidor != null) {
                Usuario seguidor = nodoSeguidor.getUsuario();
                Pila pilaPostsSeguidor = seguidor.getPilaPosts();
                listaFeed = pilaPostsSeguidor.obtener(listaFeed);
                nodoSeguidor = nodoSeguidor.getSiguiente();
            }
        }
        if (listaFeed.getCabeza() != null) {
            JOptionPane.showMessageDialog(null, "Posts de " + base.getName() + " y usuarios seguidos:");
            NodoAuxiliarListaSimple nodoActual = listaFeed.getCabeza(); // Declaración y asignación de nodoActual
            while (nodoActual != null) {
                Arbol post = nodoActual.getPost();
                post.mostrar(post);
                nodoActual = nodoActual.getSiguiente();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay posts para mostrar.");
        }
    }
     */


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
        return "Nombre: " + name + " | Edad: " + age + " | Correo: " + email + " | Fecha registro: " + fechaRegistro+"\n";
    }

    public static String dropdown() { //para seleccionar a un usuario
        JComboBox<String> usuariosDropdown = new JComboBox<>(); //el JComboBox nos permite agupar valores y mostrarlos en el JoptionPane
        NodoListaDobleCircular auxiliar = usuarios.getCabeza(); //se recorre la lista de usuarios
        if (usuarios.getCabeza() != null) {
            Usuario usuario = auxiliar.getDato(); // un obtenemos el usuario del nodo aux
            usuariosDropdown.addItem(usuario.getEmail()); //agregamos al dropdown unicamente el correo del usuario
            auxiliar = auxiliar.getSiguiente(); //vamos al siguiente nodo
            // Agregar el correo de cada usuario al JComboBox
            while (auxiliar != usuarios.getCabeza()) { //mientras sea diferente de cabeza (ya que es circular)
                usuario = auxiliar.getDato(); //lo mismo que la cabeza, obtenemos el dato
                usuariosDropdown.addItem(usuario.getEmail()); //insertamos el emaiñ
                auxiliar = auxiliar.getSiguiente(); //avanzamos
            }//final while

            JOptionPane.showMessageDialog(null, usuariosDropdown, "Seleccione un usuario", JOptionPane.QUESTION_MESSAGE); // se muestra el JComboBox con los correos
            String correoSeleccionado = (String) usuariosDropdown.getSelectedItem(); //cuando se da a Ok, se obtiene el correo
            return correoSeleccionado; //se devuelve el correo para hacer las otras opciones
        }
        return "";
    }//final del metodo dropdown

}//final de la clase
