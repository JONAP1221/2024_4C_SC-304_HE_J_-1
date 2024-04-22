package twitter;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static twitter.Twitter.usuarios;

public class Grafo {

    public void agregarUsusario() {// se se desea crear un ususario se instacia el mismo medinate preguntas para obtener los datos 
        usuarios.insertaMejorado(new Usuario(JOptionPane.showInputDialog("Ingrese su Correo"), JOptionPane.showInputDialog("Ingrese su nombre"), Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"))));
    }

    public void eliminarUsuario(Usuario u) {// para eiliminar un ususario de la lista doble circ 
        if (usuarios.existe(u)) {// si el usuario existe en la lista doble 
            usuarios.eliminar(u);// se elimina el usuario de la lista doble 
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + u + "se ha eliminado.");// se muestra el mensaje que el ussuario ha sido eilimnado nunto a el usuario 
        } else {// de no encontrarse al ussuario
            JOptionPane.showMessageDialog(null, "El ususario con el correo: " + u + "no existe.");// se miestra que el ususario no exitse junto a dicho ususario 
        }
    }

    public void cambiarNombre(Usuario u) {// funcion para cambiar nombre
        usuarios.modificaNombre(u);// se modifica el nombre
    }

    public void cambiarEdad(Usuario u) {// funcino para cambiar la edad
        usuarios.modificaEdad(u);// se modifica la edad
    }

    public static Usuario seleccionarUsuario() {
        return usuarios.buscarUsuarioPorCorreo(Usuario.dropdown());
    }//final del metodo seleccionarUsuario

    private static void guardarUsuariosCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            NodoListaDobleCircular auxiliar = usuarios.getCabeza();
            Usuario usuario = auxiliar.getDato();
            String linea = usuario.getEmail() + "," + usuario.getName() + "," + usuario.getAge() + "," + usuario.getHash();
            writer.write(linea);
            writer.newLine();
            auxiliar = auxiliar.getSiguiente();
            while (auxiliar != usuarios.getCabeza()) {
                usuario = auxiliar.getDato();
                linea = usuario.getEmail() + "," + usuario.getName() + "," + usuario.getAge() + "," + usuario.getHash();
                writer.write(linea);
                writer.newLine();
                auxiliar = auxiliar.getSiguiente();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarUsuariosDesdeCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    String email = campos[0];
                    String nombre = campos[1];
                    int edad = Integer.parseInt(campos[2]);
                    int hash = Integer.parseInt(campos[3]);
                    Usuario usuario = new Usuario(email, nombre, edad);
                    usuario.setHash(hash);
                    usuarios.insertaMejorado(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarPostDeUsuarios(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            NodoListaDobleCircular auxiliar = usuarios.getCabeza();
            Usuario usuario = auxiliar.getDato();
            Pila pilaPosts = usuario.getPilaPosts();
            if (!pilaPosts.esVacia()) {
                NodoPila nodoPost = pilaPosts.getCima();
                while (nodoPost != null) {
                    NodoArbol post = nodoPost.getArbol().getRoot();
                    String linea = usuario.getEmail() + "," + post.getMensaje().getMsj() + "," + post.getMensaje().getFecha();
                    if (post.getIzquierdo() != null) {
                        linea = linea + "," + post.getIzquierdo().getMensaje().getUser() + "," + post.getIzquierdo().getMensaje().getMsj() + "," + post.getIzquierdo().getMensaje().getFecha();
                    }//final if
                    if (post.getDerecho() != null) {
                        linea = linea + "," + post.getDerecho().getMensaje().getUser() + "," + post.getDerecho().getMensaje().getMsj() + "," + post.getDerecho().getMensaje().getFecha();
                    }//final if
                    writer.write(linea);
                    writer.newLine();
                    nodoPost = nodoPost.getSiguiente();
                }
            }
            auxiliar = auxiliar.getSiguiente();
            while (auxiliar != usuarios.getCabeza()) {
                usuario = auxiliar.getDato();
                pilaPosts = usuario.getPilaPosts();
                if (!pilaPosts.esVacia()) {
                    NodoPila nodoPost = pilaPosts.getCima();
                    while (nodoPost != null) {
                        NodoArbol post = nodoPost.getArbol().getRoot();
                        String linea = usuario.getEmail() + "," + post.getMensaje().getMsj() + "," + post.getMensaje().getFecha();
                        if (post.getIzquierdo() != null) {
                            linea = linea + "," + post.getIzquierdo().getMensaje().getUser()+ "," + post.getIzquierdo().getMensaje().getMsj() + "," + post.getIzquierdo().getMensaje().getFecha();
                        }//final if
                        if (post.getDerecho() != null) {
                            linea = linea + "," + post.getDerecho().getMensaje().getUser() + "," + post.getDerecho().getMensaje().getMsj() + "," + post.getDerecho().getMensaje().getFecha();
                        }//final if
                        writer.write(linea);
                        writer.newLine();
                        nodoPost = nodoPost.getSiguiente();
                    }
                }
                auxiliar = auxiliar.getSiguiente();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarPostDeUsuarios(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    String email = datos[0];
                    String mensaje = datos[1];
                    String fecha = datos[2];
                    Usuario usuario = usuarios.buscarUsuarioPorCorreo(email);
                    if (usuario != null) {
                        Arbol arbol = new Arbol();
                        Post post = new Post(mensaje, usuario);
                        post.setFecha(fecha);
                        arbol.agregar(post);
                        if (datos.length >= 6) {
                            Usuario r1 = usuarios.buscarUsuarioPorCorreo(datos[3]);
                            arbol.responder(r1, usuario, arbol.getRoot(), datos[4], datos[5]);
                        }//final if
                        if (datos.length == 9) {
                            Usuario r2 = usuarios.buscarUsuarioPorCorreo(datos[6]);
                            arbol.responder(r2, usuario, arbol.getRoot(), datos[7], datos[8]);
                        }//final if
                        usuario.getPilaPosts().apilar(arbol);
                    } else {
                        System.out.println("No se encontró un usuario con el email: " + email);
                    }
                } else {
                    System.out.println("Formato incorrecto en la línea del archivo CSV: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarSeguidoresCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            NodoListaDobleCircular auxiliar = usuarios.getCabeza();
            Usuario usuario = auxiliar.getDato();
            String linea = usuario.getEmail();
            AuxiliarListaSimple listaSeguidores = usuario.getSeguidores();
            if (listaSeguidores != null) {
                AuxiliarNodoListaSimple nodoSeguidor = listaSeguidores.getCabeza();
                while (nodoSeguidor != null) {
                    linea += "," + nodoSeguidor.getCorreo();
                    nodoSeguidor = nodoSeguidor.getSiguiente();
                }
            }
            writer.write(linea);
            writer.newLine();
            auxiliar = auxiliar.getSiguiente();
            while (auxiliar != usuarios.getCabeza()) {
                usuario = auxiliar.getDato();
                linea = usuario.getEmail();
                listaSeguidores = usuario.getSeguidores();
                if (listaSeguidores != null) {
                    AuxiliarNodoListaSimple nodoSeguidor = listaSeguidores.getCabeza();
                    while (nodoSeguidor != null) {
                        linea += "," + nodoSeguidor.getCorreo();
                        nodoSeguidor = nodoSeguidor.getSiguiente();
                    }
                }
                writer.write(linea);
                writer.newLine();
                auxiliar = auxiliar.getSiguiente();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarSeguidoresDesdeCSV(ListaDobleCircular usuarios, String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 1) {
                    String email = datos[0];
                    Usuario usuario = usuarios.buscarUsuarioPorCorreo(email);
                    if (usuario != null) {
                        AuxiliarListaSimple listaSeguidores = new AuxiliarListaSimple();
                        for (int i = 1; i < datos.length; i++) {
                            listaSeguidores.insertarSeguidor(new Usuario(datos[i], "", 0)); // Crear un usuario temporal con el correo
                        }
                        usuario.setSeguidores(listaSeguidores);
                    } else {
                        System.out.println("No se encontró un usuario con el email: " + email);
                    }
                } else {
                    System.out.println("Formato incorrecto en la línea del archivo CSV: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos() {
        try {
            Grafo.cargarUsuariosDesdeCSV(usuarios, "data/UsuariosTwitter.csv");
            Grafo.cargarPostDeUsuarios(usuarios, "data/PostTwitter.csv");
            Grafo.cargarSeguidoresDesdeCSV(usuarios, "data/SeguidoresTwitter.csv");
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }//final del metodo cargarDatos

    public static void guardarDatos() {
        try {
            Grafo.guardarUsuariosCSV(usuarios, "data/UsuariosTwitter.csv");
            Grafo.guardarPostDeUsuarios(usuarios, "data/PostTwitter.csv");
            Grafo.guardarSeguidoresCSV(usuarios, "data/SeguidoresTwitter.csv");
        } catch (Exception e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }//final catch
    }
}
