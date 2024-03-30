package twitter;

public class ListaDobleCircular {

    private NodoListaDobleCircular nodo;
    private NodoListaDobleCircular cabeza;
    private NodoListaDobleCircular ultimo;

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
        NodoListaDobleCircular aux = cabeza;
        NodoListaDobleCircular auxU = ultimo;

        // Caso donde el nodo a eliminar es la cabeza
        if (u.getHash() == aux.getDato().getHash()) {
            cabeza = cabeza.getSiguiente();
            ultimo.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
            return;
        }

        // Caso donde el nodo a eliminar es el último
        if (u.getHash() == auxU.getDato().getHash()) {
            ultimo = ultimo.getAnterior();
            ultimo.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
            return;
        }

        // Caso donde el nodo a eliminar está en medio de la lista
        aux = cabeza.getSiguiente(); // Empezamos desde el segundo nodo
        while (aux != cabeza) { // Continuamos hasta que volvamos a la cabeza
            if (aux.getDato().getHash() == u.getHash()) {
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                return;
            }
            aux = aux.getSiguiente();
        }
    }

    public void impirmirUltimo() { // metodo para que imprima el ultimo 
        NodoListaDobleCircular aux = ultimo;
        System.out.println(aux);
    }

    public void impirmirCabeza() { // metodo para que imprima el cabeza 
        NodoListaDobleCircular aux = cabeza;
        System.out.println(aux);
    }

    public void ImprimirSiguienteDelUltimo() { // metodo para que imprima el ultimo 
        NodoListaDobleCircular aux = ultimo;
        aux = aux.getSiguiente();
        System.out.println(aux);
    }

    public Usuario extrae(int id) {
        Usuario u = null; // creamos la variable p vacia para retornarla si se enceuntra el id deseado 
        if (cabeza != null) { // se continua si cabeza tiene algun dato 
            if (cabeza.getDato().getHash() == id) { // si el datro es cabeza se continua con lo siguiente 
                u = cabeza.getDato();
                cabeza = cabeza.getSiguiente();// setiamos el sigueinte de cabeza la nueva cabeza 
                cabeza.setAnterior(ultimo);// refrescamos la referencia del ultimo 
                ultimo.setSiguiente(cabeza);//refrescamos la referrencia del primero 
                System.out.println("Esto es lo que se extrae" + u); // se meustra lo que se extrae 
            } else if (ultimo.getDato().getHash() == id) {// vemos si el que extraemos es el ultimo 
                ultimo = ultimo.getAnterior();
                ultimo.setSiguiente(cabeza);
                cabeza.setAnterior(ultimo);
                System.out.println("Esto es lo que se extrae" + u); // se meustra lo que se extrae 
            } else {
                NodoListaDobleCircular aux = cabeza; // creamos un puntero llamado aux que empieza en cabeza 
                while (aux.getSiguiente() != cabeza && cabeza.getDato().getHash() == id)// se detiene cuando el siguiete sea cabeza es decir cuando de una vuelta y se encuentre el id deseado 
                {
                    aux = aux.getSiguiente();
                    if (aux.getSiguiente() != cabeza && cabeza.getDato().getHash() == id) {// se detiene cuando el siguiete sea cabeza es decir cuando de una vuelta y se encuentre el id deseado 
                        u = aux.getAnterior().getDato();
                        aux.setSiguiente(aux.getSiguiente().getSiguiente());
                        aux.setAnterior(aux.getAnterior());
                        System.out.println("Esto es lo que se extrae" + u); // se meustra lo que se extrae 
                    }
                }
            }
        }
        return u;
    }

    public boolean existe(int id) {
        boolean respuesta = false;// se setea la variable respuesta a falsa por defecto 
        NodoListaDobleCircular auxiliar = cabeza;// se define al auxiliar como la cabeza para iniciar 
        while (auxiliar != null) {// se verifica que no este vacia la fila
            if (auxiliar.getDato().getHash() == id) {// se compara el dato de la posicion de aux con el id ingresado
                respuesta = true;// si esto es verdaddero se cambia la variable respuesta a verdadera
                break;
            } else {// de no cumplirse se avanza a la siguiente posicion
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return respuesta; // se decuelve la respuesta una vez terminada
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

    /*@Override
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
    }*/
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
