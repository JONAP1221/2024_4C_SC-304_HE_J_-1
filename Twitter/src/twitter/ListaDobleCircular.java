package twitter;

public class ListaDobleCircular {

    private NodoListaDobleCircular cabeza;
    private NodoListaDobleCircular ultimo;

    public Usuario buscarUsuarioPorCorreo(String correo) {
        if (cabeza == null) {  // se verifica que cabeza sea diferente de null
            return null; // La lista está vacía
        } else {
            NodoListaDobleCircular auxiliar = cabeza;// se crea un auxiliar empezando en cabeza 
            do {
                Usuario usuario = (Usuario) auxiliar.getDato();// se crea una variable tipo ususario para almacenar el dato del auxiliar
                if (usuario.getEmail().equals(correo)) {// si el ussuario es el que se busca 
                    return usuario;// se devuelve el usuario 
                }
                auxiliar = auxiliar.getSiguiente();
            } while (auxiliar != cabeza); // Mientras no se haya recorrido toda la lista circular
            return null; // No se encontró el usuario
        }
    }

    public void mostrarSeguidoresDeUsuarioX(String correoUsuarioX) {

        Usuario usuarioX = buscarUsuarioPorCorreo(correoUsuarioX);// se crea una nueva variable tipo para almacenar el usuario buscado por correo
        if (usuarioX != null) {   // se verifica que el ususario que se agregar es diferente de null  
            ListaSimple seguidoresUsuarioX = usuarioX.getSeguidores(); //se crea una lista simple para en la que se almacenan los seguidores del usuario
            if (seguidoresUsuarioX != null) {   // se verifica que la lista de segudires no es  vacia
                System.out.println("Seguidores de " + usuarioX.getName() + ":"); // se imprime los seguidores de los usuarios
                NodoListaSimple nodoSeguidor = seguidoresUsuarioX.getCabeza();//se crea un nodo que almacena los seguidores de los usuarios
                while (nodoSeguidor != null) {// se verifica que los seguidores es diferente de null porque de ejecuta simepre que el usuario tenga seguidores
                    Usuario seguidor = nodoSeguidor.getUsuario(); // se  crea una variable tipo usuario llamada seguidor para mostrar el nombre de los seguidores 
                    System.out.println("- " + seguidor.getName());// se muestra el nombre de los seguidores
                    nodoSeguidor = nodoSeguidor.getSiguiente(); // se avanza al siguiente nodo
                }
            } else {
                System.out.println(usuarioX.getName() + " no tiene seguidores.");// en caso de no tener seguidores se muestra este mensaje 
            }
        } else {
            System.out.println("No se encontró ningún usuario con el correo electrónico " + correoUsuarioX);// si no se encuentra algun usuario con ese tipo de correo tira ese mensaje
        }
    }

   public void mostrarPilasDeUsuarios(ListaDobleCircular usuarios) {
    NodoListaDobleCircular actual = usuarios.getCabeza();
    StringBuilder resultado = new StringBuilder();

    while (actual != null) {
        Usuario usuario = actual.getDato();
        resultado.append("Usuario: ").append(usuario.getName()).append("\n");
        Pila pilaPosts = usuario.getPilaPosts();
        
        if (!pilaPosts.esVacia()) {
            resultado.append("Posts:\n");
            NodoPila nodoPost = pilaPosts.getCima();
            while (nodoPost != null) {
                Post post = nodoPost.getPost(); // Asumiendo que tienes un método getPost() en tu clase NodoPila para obtener el post
                resultado.append(post.toString()).append("\n");
                nodoPost = nodoPost.getSiguiente();
            }
        } else {
            resultado.append("El usuario no tiene posts.\n");
        }
        
        actual = actual.getSiguiente();
    }

    System.out.println(resultado.toString());
}


    
    public void insertaMejorado(Usuario u) {
        //Paso 1, de la presentación
        if (cabeza == null) { // en dado caso que la  este vacia 
            cabeza = new NodoListaDobleCircular(u); // seriamos el nodo como la cabeza
            ultimo = cabeza; // setiamos la misma cabeza como la cola
            cabeza.setAnterior(ultimo); //setiamos que el anterior de la cabeza es el ultimo osea la misma cabeza
            cabeza.setSiguiente(ultimo);// setiamos que el siguente de la cabeza es el ultimo que es la cabeza
            ultimo.setSiguiente(cabeza);// setiamos que el siguente del ultimo es cabeza 
            ultimo.setAnterior(cabeza);// setiamos que el anterior del ultimo es la cabeza 
        } else {
            if (cabeza.getDato().getHash() > u.getHash()) {// en caso de tener un id menor a la cabeza osea una nueva cabeza 
                NodoListaDobleCircular aux = new NodoListaDobleCircular(u);// setiamos una varible aux com oel nodo a ingresarr (p)
                aux.setSiguiente(cabeza);// a ser menor setiamos que el siguente del mismo es la cabeza
                cabeza.setAnterior(aux);// setiamos que el anterior de la cabeza es es el nuevo aux
                cabeza = aux;// setiamos el nuevo aux como la nueva cabeza
                cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
            } else {
                if (u.getHash() > ultimo.getDato().getHash()) {// ingresar algo despues del ultimo ( un nuevo ultimo) 
                    NodoListaDobleCircular aux = new NodoListaDobleCircular(u);// setiamos una varible aux com oel nodo a ingresarr (p)
                    aux.setAnterior(ultimo);// setiamos que el anterior del aux es el ultimo 
                    ultimo.setSiguiente(aux);//refrescamos el sigiuente del ultimo como el nuevo aux 
                    ultimo = aux;// refrescamos el ultimo como el aux que creamos
                    ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
                    cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                } else {
                    if (ultimo.getDato().getHash() > u.getHash() && ultimo.getAnterior().getDato().getHash() < u.getHash()) {  // en caso de insertar antes del ultimo 
                        NodoListaDobleCircular aux = new NodoListaDobleCircular(u);// setiamos una varible aux com oel nodo a ingresarr (p)
                        ultimo.getAnterior().setSiguiente(aux);// setiamos la refenrencia del anterior al ultimo 
                        aux.setAnterior(ultimo.getAnterior().getAnterior());//setia,os el anterior del nodo nuevo 
                        ultimo.setAnterior(aux);// refrescamos el anterior del ultimo 
                        aux.setSiguiente(ultimo);// setiamos el siguenre del del aux osea el ultimo por la funcion  
                    } else {
                        NodoListaDobleCircular aux = cabeza;
                        NodoListaDobleCircular auxU = ultimo;
                        while (aux.getDato().getHash() <= u.getHash() && u.getHash() <= auxU.getDato().getHash()) { // la condicino de parada es cuando el id sea mayor a el aux y si el id es menor al auxU 
                            aux = aux.getSiguiente();// avanzamos al siguiente aux
                            auxU = auxU.getAnterior();//resoresedmos de auxU
                        }
                        aux = aux.getAnterior();// ponemos esto porque para que se ejecute el if necesitamos el el aux sea menor al id de p
                        if (aux.getDato().getHash() < u.getHash() && aux.getSiguiente().getDato().getHash() > u.getHash()) {// verificamos si debemos incertarlo de la cabeza hacia adelante 
                            NodoListaDobleCircular temp = new NodoListaDobleCircular(u);//creamos un nuevo nodo el cual insertaremos 
                            temp.setAnterior(aux);// se setea la referencia del anterior de temp 
                            temp.setSiguiente(aux.getSiguiente());// se seta la referencia del siguiente de temp
                            temp.getSiguiente().setAnterior(temp);// se setea el anterior del auntiuo sigueinte de aux
                            aux.setSiguiente(temp);// setiamos el nuevo sigeuinte de aux e
                        } else if (auxU.getDato().getHash() < u.getHash() && auxU.getSiguiente().getDato().getHash() > u.getHash()) { // este funca
                            NodoListaDobleCircular temp = new NodoListaDobleCircular(u); // se crea el nuevo nodo 
                            temp.setAnterior(auxU); // setiamos que el anterior del nuevo nodo es la posicion en la que paramos
                            temp.setSiguiente(auxU.getSiguiente()); // el sigueinte de ese noso es el siguinete de la posicion en la que paramos
                            auxU.setSiguiente(temp);// setiamos que el sigueinte la posicion en la que paramos es el nuevo nodo 
                            aux.getSiguiente().getSiguiente().setAnterior(temp);//setiamos el nuevo nodo como el anetior del antiguo siguente de la posicion en la que paramos
                        }
                    }
                }
            }
        }

    }

    public void eliminar(Usuario u) {
        NodoListaDobleCircular aux = cabeza;// se crea un auxilar para la cabeza de la lista
        NodoListaDobleCircular auxU = ultimo; // se crea otro auxiliar para el ultimo de la lista
        if (u.getHash() == aux.getDato().getHash()) {  //se verifica que el id del usuario es el mismo de la cabeza 
            cabeza = cabeza.getSiguiente(); //se actualiza la referencia de cabeza al siguiente de ella
            ultimo.setSiguiente(cabeza); // se actualiza el siguiente del ultimo
            cabeza.setAnterior(ultimo);  // se actualiza el anterior de cabeza
            return;
        }
        if (u.getHash() == auxU.getDato().getHash()) {  // se verifica que el id del usuario sea igual al del ultimo
            ultimo = ultimo.getAnterior(); // se actualiza la referencia del ultimmo a el anterior del mismo
            ultimo.setSiguiente(cabeza); //se actualiza el siguiente del ultimo
            cabeza.setAnterior(ultimo); // se actualiza el anterior a cabeza
            return;
        }
        aux = cabeza.getSiguiente(); // Empezamos desde el segundo nodo
        while (aux != cabeza) { // Continuamos hasta que volvamos a la cabeza
            if (aux.getDato().getHash() == u.getHash()) { // se verifica estar en la posicion deseada a eliminar 
                aux.getAnterior().setSiguiente(aux.getSiguiente());// se actualiza la la posicion de la referencia
                aux.getSiguiente().setAnterior(aux.getAnterior()); // se antualiza la posicion del anterior
                return;
            }
            aux = aux.getSiguiente(); //
        }
    }

    public void modifica(Usuario u) {
        NodoListaDobleCircular auxiliar = cabeza; // se empieza por a  cabeza
        while (auxiliar.getSiguiente() != cabeza)// se detiene cuando el siguiete sea cabeza es decir cuando de una vuelta
        {
            if (auxiliar.getDato().getHash() == u.getHash())// se eldato del aux y el id del aux es el mismo que el que se busca 
            {
                auxiliar.getDato().setName(u.getName());// se cambia la informacion 
                break;
            } else {
                auxiliar = auxiliar.getSiguiente();//de no ser el mismo id se pasa al sigueinte nodo en la lisa 
            }
        }
    }

    public NodoListaDobleCircular getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoListaDobleCircular cabeza) {
        this.cabeza = cabeza;
    }

    public NodoListaDobleCircular getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoListaDobleCircular ultimo) {
        this.ultimo = ultimo;
    }
    

    @Override
    public String toString() {
        String respuesta = "Lista doble circular: \n";
        if (cabeza != null) {
            NodoListaDobleCircular aux = cabeza;
            respuesta += aux.toString() + "\n";
            aux = aux.getSiguiente();
            while (aux != cabeza) {
                respuesta += aux.toString() + "\n";
                aux = aux.getSiguiente();
            }
        } else {
            respuesta += "Vacía";
        }
        return respuesta;
    }

}
