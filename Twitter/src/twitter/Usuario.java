package twitter;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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

    public static void insertarSeguidor(Usuario base, Usuario destino, ListaDobleCircular usuarios) {// funcion para insertar un seguidor mediante el correo 
        if (base == null || destino == null) {// ambos ususarios deben de tener iformacion 
            JOptionPane.showMessageDialog(null, "Uno o ambos usuarios no existen.");
        } else if (base.getEmail().equals(destino.getEmail())) {// si es el mimso se muestra el menaje de errror ya que no te puedes seguir a ti mismo 
            JOptionPane.showMessageDialog(null, "No te puedes seguir a ti mismo.");
        } else if (base.getSeguidores().existeSeguidor(destino)) {// se no ser ninguno de los casoso y ya ser un seguidor 
            JOptionPane.showMessageDialog(null, "Este usuario ya sigue a " + destino.getEmail());// se muestra en mensaje de errror 
        } else {// estpo es asi ya qeu 
            if (!destino.getPilaPosts().esVacia()) {// si la pila no esta vacia de post no se puede seguir a una persona 
                base.getSeguidores().insertarSeguidor(destino, usuarios);//se anade el correo del usuario a la lista de seguidores
                JOptionPane.showMessageDialog(null, "Usuario " + base.getEmail() + " ahora sigue a " + destino.getEmail());
            } else {
                JOptionPane.showMessageDialog(null, "No puedes seguir a un usuario si no tiene post. ");
            }

        }
    }

    public static void verSeguidores(Usuario base, ListaDobleCircular usuarios) {// func para ver los seguidores de un ususario 
        if (base != null) {// si la base osea el usuario no es null 
            ListaSimple seguidoresUsuario = base.getSeguidores();// se crea una variable sipo lista simple 
            if (seguidoresUsuario != null) {// si la lista no esta vacia 
                NodoListaSimple cabezaSeguidores = seguidoresUsuario.getCabeza();// se guarda la cabeza 
                if (cabezaSeguidores != null) {//si se tiene algun dato y no es null
                    StringBuilder seguidoresTexto = new StringBuilder();// se crea una variable string builder 
                    seguidoresTexto.append("El usuario ").append(base.getName()).append(" sigue a las personas:\n");
                    while (cabezaSeguidores != null) {// si la variable no es vacia ni null
                        Usuario seguidor = usuarios.buscarUsuarioPorCorreo(cabezaSeguidores.getCorreo());// se busca el ususario ,mediante el correo 
                        if (comparar(cabezaSeguidores, usuarios)) {
                            seguidoresTexto.append("- ").append(seguidor.getName()).append("\n");// se anade el seguidor para ser mostrado 
                        }//final if
                        else {
                            base.getSeguidores().eliminarSeguidor(seguidor);// si ya no se suigue al ususario se refresaca la lista
                        }//final else
                        cabezaSeguidores = cabezaSeguidores.getSiguiente();// se avanza 
                    }
                    JOptionPane.showMessageDialog(null, seguidoresTexto.toString());
                } else {// en caso de no tener seguidores
                    JOptionPane.showMessageDialog(null, base.getName() + " no tiene seguidores.");
                }
            } else {
                JOptionPane.showMessageDialog(null, base.getName() + " no tiene seguidores.");
            }
        } else {// en caso de no encontrar un usuario 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico ");
        }
    }//final del emtodo verSeguidores

    private static boolean comparar(NodoListaSimple nodoSeguidor, ListaDobleCircular usuarios) {// se compara el nodo 
        boolean x = false;// se crea una variable booleana instanciada falsa
        NodoListaDobleCircular cabeza = usuarios.getCabeza();//se crea una variable cabeza 
        if (cabeza != null) {// si la cabeza no esta vacia 
            NodoListaDobleCircular aux = cabeza;// se crea un nodo igualado a la vabeza 
            if (aux.getDato().getEmail().equals(nodoSeguidor.getCorreo())) {// si es el msimo osea si abos tienen la misma informacion 
                x = true;// se devuelve verdad
            }//final if
            aux = aux.getSiguiente();// se avanza al sigueinte nodo 
            while (aux != cabeza) {// sioempre y cuando no se vuelva a la vabeza 
                if (aux.getDato().getEmail().equals(nodoSeguidor.getCorreo())) {// si es el msimo osea si abos tienen la misma informacion 
                    x = true;// se devuelve verdad
                }//final if
                aux = aux.getSiguiente();// se avanza al sigueinte nodo 
            }//final while
        }//final if
        return x;
    }//final del metodo actualizarListaSeguidores

    public static void eliminarSeguidor(Usuario base, Usuario destino) {// para eliminar un seguidor 
        if (base.getEmail().equals(destino.getEmail())) {// si el correo no es el mismo, 
            JOptionPane.showMessageDialog(null, "no te sigues a ti mismo por lo tanto no te puedes eliminar");
        } else if (base.getSeguidores().noExisteSeguidor(destino)) {// si no se sigue a el ususario 
            JOptionPane.showMessageDialog(null, "No sigues a este usuario");
        } else if (base.getSeguidores().existeSeguidor(destino)) {// de si seguirlo de elimina de la lista de seguidores 
            base.getSeguidores().eliminarSeguidor(destino);// se elimina 
            JOptionPane.showMessageDialog(null, "Ya no sigues al usuario" + destino.getEmail());
        }
    }//final del metodo actualizarListaSeguidores

    public static void crearPost(Usuario base) {
        if (base != null) {// si el ususario existe  
            Arbol nuevoArbol = new Arbol();// se crea un arbol 
            Post x = Post.newPost(base);// se crea una variable post con la info del ususario
            if (x != null) {// si esta no es nula 
                nuevoArbol.agregar(x);// se agreaga el post 
                base.getPilaPosts().apilar(nuevoArbol);// se apila el post 
                JOptionPane.showMessageDialog(null, "Post creado y publicado bajo el correo: " + base.getEmail());// se muetra que se creo el post bajo el destino 
            }
        } else {// en caso de no haber ningun correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico. ");
        }
    }

    public static void mostrarPost(Usuario base, ListaDobleCircular usuarios) {
        if (base != null) {// de haberlo
            Pila pilaPostUsuario = base.getPilaPosts();// de la pila se saca un post
            if (!pilaPostUsuario.esVacia()) {// si hay algo en ese nodo // es un tipo de metodo recursivo
                JOptionPane.showMessageDialog(null, "Posts de " + base.getName() + ":");//se muestran los post
                pilaPostUsuario.mostrarPilaConMensajes(usuarios); // se muestran los post 
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no tiene posts.");// en caso de == null ser verdad se notifica 
            }
        } else {// en caso de que no se encontrase un correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico ");
        }
    }

    public static void eliminarPost(Usuario base, ListaDobleCircular usuarios) {
        if (base != null) {// de haberlo
            Pila pilaPosts = base.getPilaPosts();// de la pila se saca un post
            if (!pilaPosts.esVacia()) {// si hay algo en ese nodo // es un tipo de metodo recursivo
                JOptionPane.showMessageDialog(null, "Cargando post de " + base.getName() + "...");//se muestran los post
                pilaPosts.eliminarPilaConMensajes(base, usuarios);// se muestran los post 
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no tiene posts.");// en caso de == null ser verdad se notifica 
            }
        } else {// en caso de que no se encontrase un correo 
            JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con el correo electrónico ");
        }
    }

    public static void mostrarFeedlista(Usuario base, ListaDobleCircular usuarios) {//funcion para ostrar el feed osea el de todo los ususarios que sigue y el 
        Cola colaPost = new Cola();// se crea una variable de tipo cola para implementar los post 
        ListaSimple listaFeed = new ListaSimple();;//se crea una lista simple para implementar los post 
        Pila pilaPostsUsuario = base.getPilaPosts();// de la pila se optionene los post del usuario base 
        listaFeed = pilaPostsUsuario.obtener(listaFeed);// la lista se implementa desde la pila 
        ListaSimple seguidoresUsuarioConsulta = base.getSeguidores();//se crea otra variable 
        if (seguidoresUsuarioConsulta != null) {// si el seguidoresUsuarioConsulta no es null 
            NodoListaSimple nodoSeguidor = seguidoresUsuarioConsulta.getCabeza();// se optiene la cabeza de la lista 
            while (nodoSeguidor != null) {// si este no es null
                Usuario seguidor = usuarios.buscarUsuarioPorCorreo(nodoSeguidor.getCorreo());// se busca el ususario por el correo 
                Pila pilaPostsSeguidor = seguidor.getPilaPosts();//de la pila se optionee lso post de lso seguidores de dicho ussuarios 
                listaFeed = pilaPostsSeguidor.obtener(listaFeed);// se anadena a lista simple 
                nodoSeguidor = nodoSeguidor.getSiguiente();// SE AVANZA 
            }
            if (listaFeed.getCabeza() != null) {//  si la lista no esta vacia 
                NodoListaSimple aux = listaFeed.getCabeza();// se empieza por la cabeza 
                while (aux != null) {// si no es null 
                    colaPost.encolar(aux.getPost());// SE ENCOLA 
                    aux = aux.getSiguiente();// se avanza al siguiente 
                }
            }
        }
        if (!colaPost.esVacia()) {// si no es vacia se mutesrta 
            JOptionPane.showMessageDialog(null, "Posts de " + base.getName() + " y usuarios seguidos:");
            colaPost.desencolar(usuarios);
        } else {// de no haber post se muestra eso 
            JOptionPane.showMessageDialog(null, "No hay posts para mostrar.");
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
        return "Nombre: " + name + " | Edad: " + age + " | Correo: " + email + " | Fecha registro: " + fechaRegistro+"\n";
    }

    public static String dropdown(ListaDobleCircular usuarios) { //para seleccionar a un usuario
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
