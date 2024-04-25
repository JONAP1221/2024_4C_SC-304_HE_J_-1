package twitter;

import java.awt.Image;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Grafo {

    public void agregarUsuario(ListaDobleCircular usuarios) {// se se desea crear un ususario se instacia el mismo medinate preguntas para obtener los datos 
        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"),
                JOptionPane.showInputDialog("Ingrese su nombre"),
                Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
    }//final del metodo agregarUsuario

    public void eliminarUsuario(Usuario u, ListaDobleCircular usuarios) {// para eiliminar un ususario de la lista doble circ 
        if (usuarios.existe(u)) {// si el usuario existe en la lista doble 
            usuarios.eliminar(u);// se elimina el usuario de la lista doble 
            JOptionPane.showMessageDialog(null, "El ususario con el correo: "
                    + u + "se ha eliminado.");// se muestra el mensaje que el ussuario ha sido eilimnado nunto a el usuario 
        } else {// de no encontrarse al ussuario
            JOptionPane.showMessageDialog(null, "El ususario con el correo: "
                    + u + "no existe.");// se miestra que el ususario no exitse junto a dicho ususario 
        }//final else
    }//final del metofo eliminarUsuario

    public void cambiarNombre(Usuario u, ListaDobleCircular usuarios) {// funcion para cambiar nombre
        usuarios.modificaNombre(u);// se modifica el nombre
    }//final del metodo cambiarNombre

    public void cambiarEdad(Usuario u, ListaDobleCircular usuarios) {// funcino para cambiar la edad
        usuarios.modificaEdad(u);// se modifica la edad
    }//final del metodo cambiarEdad

    public static Usuario seleccionarUsuario(ListaDobleCircular usuarios) { //se muestra un dropdown con los usuario
        return usuarios.buscarUsuarioPorCorreo(Usuario.dropdown(usuarios)); //de la lista circularDoble
    }//final del metodo seleccionarUsuario

    private static void guardarUsuariosCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        //Obtiene la listaDobleCircular con los usuarios para almacennar la info de estos y guardarlos
        //en el documento (la ruta es "data/nombreArchivo.csv")
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            //en caso de que no exista el archivo este se crea, de existir se modifica
            //este crea el BufferedWriter que es para una escritura más eficiente en el archivo
            NodoListaDobleCircular auxiliar = usuarios.getCabeza(); //se obtiene laa cabeza de la lista
            Usuario usuario = auxiliar.getDato(); //obtenemos el usuario
            String linea = usuario.getEmail() + "," + usuario.getName() //y la linea sera
                    + "," + usuario.getAge() + "," + usuario.getHash() //a info del usuario
                    + "," + usuario.getFechaRegistro();
            writer.write(linea); //se escribe la linea en el archivo
            writer.newLine(); //se salta de linea para dividir la info del siguiente usuario
            auxiliar = auxiliar.getSiguiente(); //se obtiene el siguiente usuario
            while (auxiliar != usuarios.getCabeza()) { //ahora recorremos la lista doble completa
                usuario = auxiliar.getDato(); //y obtenemos lo mismo que con cabeza
                linea = usuario.getEmail() + "," + usuario.getName() //para el resto de los nodos de la lista
                        + "," + usuario.getAge() + "," + usuario.getHash()
                        + "," + usuario.getFechaRegistro();
                writer.write(linea);
                writer.newLine();
                auxiliar = auxiliar.getSiguiente();
            }//final while
        } catch (IOException e) {
            e.printStackTrace(); //en caso de un error
        }//final catch
    }//final del metodo guardarUsuariosCSV

    private static void cargarUsuariosDesdeCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        //se obtiene la listaCircularDoble con los usuarios y se da la ruta del archivo
        //para cargar los datos y guardarlos en la lista
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            //este instancia el BufferedReader para mayor eficiencia a la hora de leer el archivo
            String linea; //definimos linea como String, esta contiene la info en formato de texto de los usuarios
            while ((linea = reader.readLine()) != null) { //mientras que la linea no sea null
                String[] campos = linea.split(","); //los campos se dividen por las comas ",", ya definidas 
                //a la hora de guardar la informacion de los usuarios
                if (campos.length == 5) { //mientras que los campos esten completamente llenos
                    String email = campos[0]; //el primer valor de el campo es el email
                    String nombre = campos[1]; //el segundo el nombre
                    int edad = Integer.parseInt(campos[2]); //el tercero la edad
                    int hash = Integer.parseInt(campos[3]); //el cuarto el hash
                    String fechaRegistro = (campos[4]);
                    Usuario usuario = new Usuario(email, nombre, edad); //se crea el usuario con la info recibida
                    usuario.setHash(hash); //se modifica el hash para que coincida
                    usuario.setFechaRegistro(fechaRegistro);
                    usuarios.insertaMejorado(usuario); //se inserta el usuario en la lista
                }//final if
            }//final while
        } catch (IOException e) {
            e.printStackTrace(); //en caso de un error
        }//final catch
    }//final del metodo cargarUsuariosDesdeCSV

    private static void guardarPostDeUsuarios(ListaDobleCircular usuarios, String nombreArchivo) {
        //de igual manera se obtiene la lista de usuarios y la ruta del archivo donde se guardaran
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            //el BufferedWriter es más eficiente a la hora de escribir en el documento
            NodoListaDobleCircular auxiliar = usuarios.getCabeza(); //se obtiene la cabeza
            Usuario usuario = auxiliar.getDato(); //se obtiene el usuario del nodo
            Pila pilaPosts = usuario.getPilaPosts(); //se obtiene la pila con los post del usuario
            if (!pilaPosts.esVacia()) { //si no esta vacia
                NodoPila nodoPost = pilaPosts.getCima(); //se obtiene la cima
                while (nodoPost != null) { //mientras que el nodo no sea null
                    NodoArbol post = nodoPost.getArbol().getRoot(); //se obtiene el nodo del arbol
                    String linea = usuario.getEmail() + "," //la linea contendra todos los datos del post
                            + post.getMensaje().getMsj() //el email, el mensaje
                            + "," + post.getMensaje().getFecha(); // y la feccha
                    if (post.getIzquierdo() != null) { //en caso de haber una respuesta en el nodo izquierdo
                        linea = linea + "," //se agregara a la linea los datos de este
                                + post.getIzquierdo().getMensaje().getUser() //el usuario
                                + "," + post.getIzquierdo().getMensaje().getMsj() + "," //el mensaje
                                + post.getIzquierdo().getMensaje().getFecha(); //la fecha de publicacion
                    }//final if
                    if (post.getDerecho() != null) { // en caso de tambien haver una respuesta
                        linea = linea + "," //en el nodo derecho del arbol, se agrega a la linea 
                                + post.getDerecho().getMensaje().getUser() //el usario
                                + "," + post.getDerecho().getMensaje().getMsj() + "," //mensaje
                                + post.getDerecho().getMensaje().getFecha(); //y fecha
                    }//final if
                    writer.write(linea); //se escribe la linea en el archivo plano
                    writer.newLine(); //se salta de linea para el siguiente arbol del usario
                    nodoPost = nodoPost.getSiguiente(); //se avanza en la pila
                }//final while
            }//final if
            auxiliar = auxiliar.getSiguiente(); //ahora con el resto de usuarios
            while (auxiliar != usuarios.getCabeza()) { //mientras que no sea cabeza otra vez
                usuario = auxiliar.getDato(); //se obtiene al usuario
                pilaPosts = usuario.getPilaPosts(); //se obtiene la pila del usuario
                if (!pilaPosts.esVacia()) { //si no es vacia 
                    NodoPila nodoPost = pilaPosts.getCima(); //se obtiene el nodo cima
                    while (nodoPost != null) { //mientras que el nodo no sea null
                        NodoArbol post = nodoPost.getArbol().getRoot(); //se obtiene el nodo del arbol
                        String linea = usuario.getEmail() //la linea contiene el email 
                                + "," + post.getMensaje().getMsj() + "," //el mensaje
                                + post.getMensaje().getFecha(); //la fecha
                        if (post.getIzquierdo() != null) { //en caso de tener respuesta izquierda
                            linea = linea + "," //la linea tambien contendra
                                    + post.getIzquierdo().getMensaje().getUser() //usuario que responde 
                                    + "," + post.getIzquierdo().getMensaje().getMsj() //el mensaje
                                    + "," + post.getIzquierdo().getMensaje().getFecha(); //la fecha
                        }//final if
                        if (post.getDerecho() != null) { //en caso de tener respuesta izquierda
                            linea = linea + "," //la linea tambien contendra
                                    + post.getDerecho().getMensaje().getUser() //usuario que responde 
                                    + "," + post.getDerecho().getMensaje().getMsj() //el mensaje
                                    + "," + post.getDerecho().getMensaje().getFecha();//la fecha
                        }//final if
                        writer.write(linea); //se escribe la linea en el documento
                        writer.newLine(); //se salta de linea para el siguiente post
                        nodoPost = nodoPost.getSiguiente(); //se obtiene el siguiente post
                    }//final while
                }//final if
                auxiliar = auxiliar.getSiguiente(); //se sigue con el siguiente nodo de la lista
            }//final while
        } catch (IOException e) {
            e.printStackTrace(); //en caso de errores
        }//final catch
    }//final del metodo guardarPostDeUsuarios

    private static void cargarPostDeUsuarios(ListaDobleCircular usuarios, String nombreArchivo) {
        //para cargar los post de los usuarios se obtiene la lista de estos
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            //el BufferedReader es más eficiente a la hora de leer el archivo con los datos
            String linea; //la linea es la que contiene la informacion de los post
            while ((linea = reader.readLine()) != null) { //en caso de que no sea null
                String[] datos = linea.split(","); //se divide la info por la coma ","
                //esta ya fue definida en el guardarPost
                if (datos.length >= 3) { //en caso de tener un post
                    String email = datos[0]; //el campo 1 es el email
                    String mensaje = datos[1]; //el segndo es el mensaje
                    String fecha = datos[2]; //el tercero la fecha
                    Usuario usuario = usuarios.buscarUsuarioPorCorreo(email); //buscamos al usuario
                    //en la lista para verificar que existe
                    if (usuario != null) { //si existe
                        Arbol arbol = new Arbol(); //se crea el arbol
                        Post post = new Post(mensaje, usuario); //se crea el post segun la info
                        post.setFecha(fecha); //se cambia la fecha por la de la info
                        arbol.agregar(post); //se agrega el post al arbol
                        if (datos.length >= 6) { //en caso de tener una respuesta
                            Usuario r1 = usuarios.buscarUsuarioPorCorreo(datos[3]); //se obtiene el email del cuarto campo
                            arbol.responder(r1, usuario, arbol.getRoot(), datos[4], datos[5], usuarios);
                            //se agrega la respuesta al arbol segun la info del aarchivo
                        }//final if
                        if (datos.length == 9) { //en caso de tener 2 respuestas
                            Usuario r2 = usuarios.buscarUsuarioPorCorreo(datos[6]); //el setimo dato seria el correo del usuario
                            arbol.responder(r2, usuario, arbol.getRoot(), datos[7], datos[8], usuarios);
                            //se agrega la respuesta al arbol segun la info del aarchivo
                        }//final if
                        usuario.getPilaPosts().apilar(arbol); //se apila el arbol en la pila del usuario
                    }//final if
                }//final if
            }//final while
        } catch (IOException e) {
            e.printStackTrace(); //en caso de errores
        }//final catch
    }//final del metodo cargarPostDeUsuarios

    private static void guardarSeguidoresCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        //se obtiene la lista de usuarios para guardar a quienes sigue en el archivo plano
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            //el BufferedWriter escribe de manera más eficiente en el archivo
            NodoListaDobleCircular auxiliar = usuarios.getCabeza(); //se obtiene la cabeza
            Usuario usuario = auxiliar.getDato(); //se obtiene el usuario del nodo 
            String linea = usuario.getEmail(); //el primer dato es el usuario que le pertenece la lista
            //esto para facilitar la asignacion de esta lista al momento de cargar el archivo
            ListaSimple listaSeguidores = usuario.getSeguidores(); //se obtiene la lista de seguidores del usuario
            if (listaSeguidores != null) { //si no es null 
                NodoListaSimple nodoSeguidor = listaSeguidores.getCabeza(); //se obtiene la cabeza
                while (nodoSeguidor != null) { //mientras que no sea null
                    linea += "," + nodoSeguidor.getCorreo(); //escribe el correo 
                    nodoSeguidor = nodoSeguidor.getSiguiente(); //pasa al siguiente nodo
                }//final while
            }//final if
            writer.write(linea); //escribe la linea en el documento
            writer.newLine(); //se salta de linea para la siguiente lista
            auxiliar = auxiliar.getSiguiente(); //se obtiene el siguiente usuario
            while (auxiliar != usuarios.getCabeza()) { //mientras que no sea cabeza otra vez
                usuario = auxiliar.getDato(); //obtiene el usuario
                linea = usuario.getEmail(); //la linea sera el correo del mismo usuario
                //ya se menciono el porque más arriba en este metodo
                listaSeguidores = usuario.getSeguidores(); //se obtiene la lista de seguidores del usuario
                if (listaSeguidores != null) { //mientras que no sea null
                    NodoListaSimple nodoSeguidor = listaSeguidores.getCabeza(); //se obtiene la cabeza de la lista
                    while (nodoSeguidor != null) { //mientras no sea null
                        linea += "," + nodoSeguidor.getCorreo(); //obtiene el correo del nodo
                        nodoSeguidor = nodoSeguidor.getSiguiente(); //avanza al siguiente
                    }//final while 
                }//final if
                writer.write(linea); //escribe la lista en formato plano en el archivo 
                writer.newLine(); //salta de linea para la siguiente lista
                auxiliar = auxiliar.getSiguiente(); //se obtiene el siguiente usuario
            }//final while
        } catch (IOException e) {
            e.printStackTrace(); //en caso de errores
        }//final catch
    }//final del metodo guardarSeguidoresCSV

    private static void cargarSeguidoresDesdeCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        //se obtiene la lista de usuarios y la ruta del archivo con los datos de la lista de seguidores
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            //el BufferedReader es más eficiente a la hora de leer el archivo 
            String linea; //la linea contendra la informacion de la lista
            while ((linea = reader.readLine()) != null) { //mientras que la linea no sea null
                String[] datos = linea.split(","); //se divide la info por la coma ","
                if (datos.length >= 1) { //en caso de tener 1 o más datos
                    String email = datos[0]; //el primer dato sea a quien le pertenece la lista
                    Usuario usuario = usuarios.buscarUsuarioPorCorreo(email); //se busca al usuario por el correo
                    if (usuario != null) { //en caso de existir
                        ListaSimple listaSeguidores = new ListaSimple(); //se crea su lista
                        for (int i = 1; i < datos.length; i++) { //se recorre los demás datos
                            Usuario user = usuarios.buscarUsuarioPorCorreo(datos[i]); //se busca al usuario
                            if (user != null) { // en caso de existor
                                listaSeguidores.insertarSeguidor(user, usuarios); // inserta al usuario a la lista
                            }//final if
                        }//final for
                        usuario.setSeguidores(listaSeguidores); //se le asigna la lista al usuario correspondiente
                    }//final if
                }//final if
            }//final while
        } catch (IOException e) {
            e.printStackTrace();
        }//final catch
    }//final del metodo cargarSeguidoresDesdeCSV

    public static void cargarDatos(ListaDobleCircular listaUsuarios) { //solo llama a las funciones que cargan los datos
        try {
            Grafo.cargarUsuariosDesdeCSV(listaUsuarios, "data/UsuariosTwitter.csv");
            Grafo.cargarPostDeUsuarios(listaUsuarios, "data/PostTwitter.csv");
            Grafo.cargarSeguidoresDesdeCSV(listaUsuarios, "data/SeguidoresTwitter.csv");
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }//final catch
    }//final del metodo cargarDatos

    public static void guardarDatos(ListaDobleCircular listaUsuarios) { //llamma a las funciones encargadas de guardar la info
        try {
            Grafo.guardarUsuariosCSV(listaUsuarios, "data/UsuariosTwitter.csv");
            Grafo.guardarPostDeUsuarios(listaUsuarios, "data/PostTwitter.csv");
            Grafo.guardarSeguidoresCSV(listaUsuarios, "data/SeguidoresTwitter.csv");
        } catch (Exception e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }//final catch
    }//final del metodo guardarDatos

    public static boolean salirDelPrograma() {

        // Cargar la imagen
        ImageIcon originalIcon = new ImageIcon("data/elmae.jpg"); //establece la ruta de donde se obtiene la imagen
        Image img = originalIcon.getImage(); //obtiene la imagen de la ruta establecida
        Image scaledImg = img.getScaledInstance(700, 700, Image.SCALE_SMOOTH); // Cambia la escala a la imagen
        ImageIcon scaledIcon = new ImageIcon(scaledImg); //es la escala a la que se muestra la imagen
        JOptionPane.showMessageDialog(null, "", "Saliendo de la app", JOptionPane.PLAIN_MESSAGE, scaledIcon); //muestra la imagen con los parametros antes definidos
        return false;
    }//final del metodo salirDelPrograma
    
    public static void start(){ //para ver el menu principal
        ListaDobleCircular listaUsuarios = new ListaDobleCircular(); //esta lista contendra a los usuarios del programa
        Grafo g = new Grafo(); //inicializamos el grafo
        Grafo.cargarDatos(listaUsuarios); //cargamos los datos del archivo csv
        boolean continuar = true; //esta variable es para mostrar el menu (switch)
        while (continuar) { //mientras sea true
            try { //para evitar errores de diferentes formatos
                String opcion = JOptionPane.showInputDialog("Opciones de ingreso \n"
                        + "1: Agregar usuario \n"
                        + "2: Modificar nombre de un usuario \n"
                        + "3: Modificar edad de un usuario \n"
                        + "4: Eliminar un usuario \n"
                        + "5: Mostrar usuarios  \n"
                        + "6: Agregar seguidor \n"
                        + "7: Eliminar un seguidor \n"
                        + "8: Mostrar a quien sigue el usuario xxx \n"
                        + "9: Publicar un post \n"
                        + "10: Eliminar un post \n"
                        + "11: Mostrar los post de un usuario \n"
                        + "12: Mostrar el feed de un usuario \n"
                        + "0: salir \n"); //las diferentes opciones para ver
                if (opcion != null && !opcion.isEmpty()) { // Verificar si la cadena no está vacía
                    int numero = Integer.parseInt(opcion); //pasa el string a int
                    switch (numero) { //accede a la opcion ingresada
                        case 1 -> g.agregarUsuario(listaUsuarios);
                        case 2 -> g.cambiarNombre(Grafo.seleccionarUsuario(listaUsuarios), listaUsuarios);
                        case 3 -> g.cambiarEdad(Grafo.seleccionarUsuario(listaUsuarios), listaUsuarios);
                        case 4 -> g.eliminarUsuario(Grafo.seleccionarUsuario(listaUsuarios),listaUsuarios);
                        case 5 -> JOptionPane.showMessageDialog(null, listaUsuarios);
                        case 6 -> Usuario.insertarSeguidor(Grafo.seleccionarUsuario(listaUsuarios), Grafo.seleccionarUsuario(listaUsuarios), listaUsuarios);
                        case 7 -> Usuario.eliminarSeguidor(Grafo.seleccionarUsuario(listaUsuarios), Grafo.seleccionarUsuario(listaUsuarios));
                        case 8 -> Usuario.verSeguidores(Grafo.seleccionarUsuario(listaUsuarios),listaUsuarios);
                        case 9 -> Usuario.crearPost(Grafo.seleccionarUsuario(listaUsuarios));
                        case 10 -> Usuario.eliminarPost(Grafo.seleccionarUsuario(listaUsuarios),listaUsuarios);
                        case 11 -> Usuario.mostrarPost(Grafo.seleccionarUsuario(listaUsuarios),listaUsuarios);
                        case 12 -> Usuario.mostrarFeedlista(Grafo.seleccionarUsuario(listaUsuarios),listaUsuarios);
                        case 0 -> continuar = Grafo.salirDelPrograma();
                        default -> JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione una opción válida.");
                    }//final switch
                }//final if
            } catch (Exception e) {
                System.out.println("Error capa 8: " + e.getMessage());
            }//final catch
        }//final while
        Grafo.guardarDatos(listaUsuarios); //guarda la informacion nueva en el archivo csv corresondiente al finalizar el programa
    }//final del metodo start

}//final de la clase
