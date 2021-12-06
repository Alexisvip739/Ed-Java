package estructuraslineales;

import com.sun.org.apache.bcel.internal.generic.I2F;
import entradasalida.SalidaTerminal;
import estructuraslineales.registros.Nodo;
import estructuraslineales.registros.NodoDoble;

/**
 * clase que implementa todos los metodos para la funcionalidad de la lista doble
 * @author Alexis Ulteras Sotelo
 * @version 1.0
 */
public class ListaEncadenadaDoble implements ListaDatos {
    protected NodoDoble frente;
    protected NodoDoble fin;
    protected NodoDoble iterador;

    public ListaEncadenadaDoble() {
        frente = null;
        fin = null;
    }

    /**
     * metodo que verifica si la lista esta llena o no
     *
     * @return
     */
    public boolean vacia() {
        if (frente == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * metodo que inicializa el iterador para poder obtener los datos siguientes
     */

    public void Iteradorinicio() {
        iterador = frente;

    }

    /**
     * metodo que inicializa el iterador de al finañ para poder obtener los datos de la izquierda
     */

    public void IteradorFinal() {
        iterador = fin;
    }


    /**
     * metodo que nos permite poder agregar un dato dentro de la lista encadenada
     *
     * @param elemento Es el dato que se desea agregar a la lista.
     * @return 1 o -1
     */
    public int agregar(Object elemento) {
        NodoDoble nuevoNodo = new NodoDoble(elemento); //1
        if (nuevoNodo == null) { //esta lleno
            return -1;
        } else { //no esta lleno
            if (vacia() == true) { //a, vacia
                frente = nuevoNodo; //2
                fin = nuevoNodo;
            } else { //hay varios, b
                fin.setDirMemDer(nuevoNodo);//2
                nuevoNodo.setDirMemIzq(fin);//3
                fin = nuevoNodo;//4
            }
            return 1;
        }
    }

    /**
     * metodo que nos permite poder agregar un dato al inicio de la lista encadenada
     *
     * @param elemento
     * @return
     */
    public int agergarInicio(Object elemento) {
        NodoDoble nuevoNodo = new NodoDoble(elemento); //1
        if (nuevoNodo != null) { //si hay espacio
            if (vacia() == true) {
                frente = nuevoNodo;
                fin = nuevoNodo;
            } else {  //hay mas elementos
                nuevoNodo.setDirMemDer(frente);//asignamos la liga derecha como el frente
                frente.setDirMemIzq(nuevoNodo);// asignamos la liga izquierda como el nuevo nodo de el frente
                frente = nuevoNodo;

            }
            return 1;
        } else { //no hay espacio
            return -1;
        }
    }

    /**
     * metodo que nos permite saber el ultimo dato agregado en la lista
     *
     * @return fin
     */
    @Override
    public Object verTope() {
        return fin;
    }

    /**
     * metodo que nos permite poder mostrar en pantalla los datos que se encuentran en la lista de datos actual
     */
    public void imprimir() {
        if (vacia() == false) { //hay algo
            SalidaTerminal.consola("null <- "); //1
            NodoDoble temporal = frente;
            while (temporal != fin) {
                SalidaTerminal.consola(temporal.getDato() + "<-->"); //2
                temporal = temporal.getDirMemDer();
            }
            SalidaTerminal.consola(temporal.getDato() + " -> null");//3
        } else { //no hay nada
            SalidaTerminal.consola("null");
        }
    }

    /**
     * metodo que nos permite mostrar de forma inversa los datos agregados en la lista de datos actual
     */
    public void imprimirOrdenInverso() {
        if (vacia() == false) { //hay algo
            NodoDoble temporal = fin;
            SalidaTerminal.consola("null -> ");
            while (temporal != null) {// mientras el nodo no sea null
                SalidaTerminal.consola(temporal.getDato() + " <--> "); //2
                temporal = temporal.getDirMemIzq();// mostramos los datos que estan a asu izquierda
            }
            SalidaTerminal.consola("null");//3
        } else { //no hay nada
            SalidaTerminal.consola("null");
        }

    }


    /**
     * metodo de buscar un dato que se encuentre dentro de la lista de datos
     *
     * @param elemento
     * @return
     */
    public Object buscar(Object elemento) {
        NodoDoble temp = frente;
        while (temp != null && !temp.toString().equalsIgnoreCase(elemento.toString())) {// mientras aun no se encuentre el dato a buscar
            temp = temp.getDirMemDer();// seguimos avanzando
        }
        if (temp == null) {
            return null;
        } else {// si se encontro
            return temp.getDato();// regresamos el dato
        }

    }


    /**
     * metodo que nos permite buscar los datos pero ahora empezando desde la ultima posicion
     *
     * @param elemento
     * @return temp
     */
    public Object buscarDesdeFinal(Object elemento) {
        NodoDoble temp = fin;
        while (temp != null && !temp.toString().equalsIgnoreCase(elemento.toString())) {
            temp = temp.getDirMemIzq();// recorremos las posiciones de la izqquierda
        }
        if (temp == null) {
            return null;
        } else {
            return temp.getDato();// obtenemos el dato si es que se encontro
        }
    }

    /**
     * este metodo nos permite poder agregar datos a una lista dependiendo de si son objetos String o numeros
     *
     * @return retornarListaDeListas
     */
    public ListaEncadenadaDoble objetosDeLista() {
        ListaEncadenadaDoble enteros = new ListaEncadenadaDoble();// lista que almacenara numeros
        ListaEncadenadaDoble cadenas = new ListaEncadenadaDoble();// lista que almacenara cadenas
        ListaEncadenadaDoble objectos = new ListaEncadenadaDoble();// lista que almacenara objectos
        Iteradorinicio();// inicializamos los iteradores
        while (hayMas()) {
            Object elemento = obtenerSigiuenteFrente();// obtenemos la siguiente posicion
            if (elemento instanceof Number) {// en caso de que sea un numero
                enteros.agregar(elemento);// agregamos los numeros
            } else if (elemento instanceof String) {// igual con los strings
                cadenas.agregar(elemento);

            } else {
                objectos.agregar(elemento);// igual con los objetos
            }
        }
        ListaEncadenadaDoble retornarListaDeListas = new ListaEncadenadaDoble();// creamos una nueva lista ligada para almacenar la lista ligada con los datos
        retornarListaDeListas.agregar(enteros);
        retornarListaDeListas.agregar(cadenas);
        retornarListaDeListas.agregar(objectos);

        if (retornarListaDeListas!=null){
            //recorremos la lista uno para poder obtener las listas agregadas y posteriormente sus datos de la listas 2
            for (int i=0;i<retornarListaDeListas.NumElementos();i++){
                Object listaConListas=retornarListaDeListas.obtener(i);
                ListaEncadenadaDoble listaConDatos=(ListaEncadenadaDoble) listaConListas;
                listaConDatos.imprimir();

            }


        }
        return null;
    }

    /**
     * metodo que nos permite poder eliminar la primera posicion de la lista de datos
     *
     * @return elementoBorrado
     */
    public Object eliminarInicio() {
        Object elementoBorrado = null;
        if (vacia() == false) {
            elementoBorrado = frente.getDato();
            if (frente == fin) {
                frente = null;//2
                fin = null;
            } else {
                frente = frente.getDirMemDer();
                frente.setDirMemIzq(null);
            }
            return elementoBorrado;
        } else {
            return null;
        }
    }


    /**
     * metodo que nos permite poder borrar cualquier dato de la lista de datos
     *
     * @param elemento
     * @return elementoBorrado
     */
    @Override
    public Object eliminar(Object elemento){

        if(vacia()==true){ //no hay datos
            return null;
        }else{ //si hay datos
            NodoDoble encontrado=frente;
            while(encontrado!=null && !encontrado.toString().equalsIgnoreCase(elemento.toString())){
                encontrado=encontrado.getDirMemDer();
            }
            if(encontrado==null){//no esta
                return null;
            }else{ //si esta, pero en algun lado
                Object datoEliminado=encontrado.getDato(); //1
                if(frente==fin){ // unico
                    frente=null;
                    fin=null;
                }else if(encontrado==frente){ //frente
                    frente=frente.getDirMemDer();
                    frente.setDirMemIzq(null);
                }else if(encontrado==fin){ //final
                    fin = fin.getDirMemIzq();
                    fin.setDirMemDer(null);
                }else{ //esta en medio
                    // guarda el elemento a eliminar en un temp
                    NodoDoble temp = encontrado;
                    // temp tendra en la liga derecha lo que tenga el elemento encontrado su liga derecha
                    temp = temp.getDirMemIzq();
                    temp.setDirMemDer(encontrado.getDirMemDer());
                    // temp tendra en la liga izquierda lo que tenga el elemento encontrado su liga izquierda
                    temp = encontrado;
                    temp = temp.getDirMemDer();
                    temp.setDirMemIzq(encontrado.getDirMemIzq());
                }
                return datoEliminado;
            }
        }

    }

    /**
     * metodo que nos permite saber si una lista encadenada es igual a la lista actual en sus posiciones
     *
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean esIgual(Object listaDatos2) {
        if (listaDatos2 instanceof ListaEncadenadaDoble) {
            ListaEncadenadaDoble listaNueva = (ListaEncadenadaDoble) listaDatos2;// casteamos la lista pasada como objeto a lista ligada
            NodoDoble temp = frente;
            NodoDoble tempNuevo = listaNueva.frente;
            int contador = 0;
            while (temp != null && tempNuevo != null) {
                if (!temp.toString().equalsIgnoreCase(tempNuevo.toString())) {// si es diferente al dato a buscar
                    contador++;// aumentamos una posicion mas
                }
                temp = temp.getDirMemDer();// seguimos avanzando
                tempNuevo = tempNuevo.getDirMemDer();// seguimos avanzando
            }
            if (contador == 0) {// si nunca se fue aumentando la variable entonces todos los datos son iguales
                return true;
            }
        }
        return false;
    }

    /**
     * metodo que nos ayuda a obtener cualquier elemento de la posicion por medio de un indice
     *
     * @param indice
     * @return temp
     */
    @Override
    public Object obtener(int indice) {
        NodoDoble temp = frente;
        int posicion = 0;
        if (indice < NumElementos() && indice >= 0) {// si esta en el rango adecuado
            while (temp != null) {
                if (posicion == indice) {// si posicion e indice son iguales
                    return temp.getDato();//regresamos el dato que se encuentra ahi
                }
                temp = temp.getDirMemDer();// seguimos avanzando
                posicion++;// asignamos una posicion mas
            }
        }
        return null;
    }

    /**
     * metodo que nos permite poder cambiar un elemento viejo por un elemento nuevo un numero de veces que se repitan
     *
     * @param elementoViejo
     * @param elementoNuevo
     * @param numVeces
     * @return true o false
     */
    @Override
    public boolean cambiar(Object elementoViejo, Object elementoNuevo, int numVeces) {
        if (elementoNuevo != null && numVeces > 0) {
            boolean bandera = false; // bandera que cambiara de valor si se hizo algun cambio.
            NodoDoble temp = frente;
            int contador = 0;
            while (temp != null && contador < numVeces) {
                if (temp.getDato().toString().equalsIgnoreCase(elementoViejo.toString())) {// en caso de que sean iguales los datos
                    temp.setDato(elementoNuevo); // reemplazamos el dato
                    bandera = true;
                    contador++;
                }
                temp = temp.getDirMemDer();// seguimos avanzando
            }
            return bandera;
        } else {
            return false;
        }

    }

    /**
     * metodo que nos ayuda a buscar la posicion de un elemento y agregarla en un arrerglo de datos
     *
     * @param elemento
     * @return
     */
    @Override
    public ArregloDatos buscarValores(Object elemento) {
        ArregloDatos arregloDatos = new ArregloDatos(NumElementos() + 1);
        int contador = 0;
        if (!vacia()) {
            NodoDoble actual = frente;
            while (actual != null) {// mientras el dato actual sea diferente de null
                if (actual.getDato().equals(elemento)) {// si actual es igual a el elemento
                    arregloDatos.agregar(contador + 1);// agregamos el valor
                }
                actual = actual.getDirMemDer();// seguimos avanzando
                contador++;
            }
        }
        return arregloDatos;
    }

    /**
     * metodo que nos permite poder eliminar la ultima posicion de los datos de la lista encadenada
     *
     * @return
     */
    public Object eliminar() {
        if (vacia() == true) { //no hay nada, a
            return null;
        } else { //si hay algo
            Object elementoBorrado = fin.getDato();//1
            if (frente == fin) { //b, unico
                frente = null; //2
                fin = null;
            } else { //c, har varios
                fin = fin.getDirMemIzq();//2
                fin.setDirMemDer(null);//3
            }
            return elementoBorrado;
        }
    }

    /**
     * metodo que nos permite poder agregar una lista de arreglos a una lista encadenada de datos
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean agregarLista(Object listaDatos2) {
        if (listaDatos2 instanceof ArregloDatos){
            ArregloDatos ListaArreglo=(ArregloDatos) listaDatos2;
            for (int i=0;i<NumElementos();i++){// recorremos la lista actual de los datos de la lista
                Object obtenerDatos=ListaArreglo.obtener(i);// obtenemos las posiciones de la lista de arreglo
                if (obtenerDatos!=null){// en caso de que sea null
                    agregar(obtenerDatos);// agregamos los datos
                }
            }
            return true;
        }
        return false;
    }

    /**
     * metodo que nos ayuda a poder invertir los datos de la lista actual
     */
    @Override
    public void invertir() {
        PilaListaEncadenada pila = new PilaListaEncadenada();// declaramos nuestra pila
        if (vacia() == false) { //hay algo
            NodoDoble temporal = frente;// obtenemos el frente de los datos
            while (temporal!= fin) {
                pila.poner(temporal.getDato());// ponemos la posicion de los datos en la pila
                temporal = temporal.getDirMemDer();
            }
            pila.poner(temporal.getDato());

            temporal = frente;
            while (temporal!= fin) {
                temporal.setDato(pila.quitar());
                temporal = temporal.getDirMemDer();
            }
            temporal.setDato(pila.quitar());
        } else { //no hay nada
            SalidaTerminal.consola("null");
        }
    }

    /**
     * metodo que nos ayuda a poder vaciar todos los datos que se encuentran en la lista actual
     */
    @Override
    public void vaciar() {
        frente=null;
        fin=null;
    }

    /**
     * metodo que nos ayuda a poder contar todos los elementos existentes dentro de la lista siempre y cuando estos se repitan
     * @param elemento
     * @return i-> numero de datos contados
     */
    @Override
    public int contar(Object elemento) {
        NodoDoble recorrer = frente;
        int i = 0;
        while (recorrer != null && recorrer.getDato().toString().equalsIgnoreCase(elemento.toString())) {// cuando el dato sea igual al elemento
            if(recorrer.getDato()==elemento){// en caso de que sea igual
                i++;// aumentamos posicion
            }
            recorrer = recorrer.getDirMemDer();
        }
        return i;// regresamos el numero de veces que esta dentro de la lista
    }

    /**
     * elimina todos los datos que estan agregados en la lista actual que son de un arreglo de datos
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean eliminarLista(Object listaDatos2) {
        if (listaDatos2 instanceof ArregloDatos){
            ArregloDatos listaArreglo= (ArregloDatos)listaDatos2;
            for (int i=0;i<NumElementos();i++){
                Object elementosArreglo= listaArreglo.obtener(i);// obtenemos todas las posiciones de los datos
                Object elementoABuscar=buscar(elementosArreglo);// buscamos los datos para saber si existen en la lista
                if (elementoABuscar!=null){// en caso de que si existan
                    eliminar(elementosArreglo);// los eliminamos de la lista actual
                }
            }
            return true;
        }
        return false;
    }

    /**
     * metodo que nos ayuda a poder rellenar un numero de veces un dato en espesifico
     * @param elemento
     * @param cantidad
     */
    @Override
    public void rellenar(Object elemento, int cantidad) {
        if (elemento!=null){
            int contador=0;
            if (cantidad > NumElementos()) {// verificamos que cantidad sea mayor a numero de elementos de la lista
                cantidad = NumElementos();
                for (int a = 0; a < NumElementos(); a++)// recorremos el numero de elementos
                    agregar( elemento);// agregamos los datos
            } else {
                for (int a = 0; a < cantidad; a++)
                    agregar( elemento);
            }
        }
    }



    /**
     * metodo que nos permite clonar todos los datos de la lista actual si modificar nada de ellos
     * @return  listaClonada
     */

    @Override
    public Object clonar() {
        ListaEncadenadaDoble listaClonada= new ListaEncadenadaDoble();// creamos una lista nueva
        NodoDoble temp= frente;
        while (temp!=null){
            listaClonada.agregar(temp);// agregamos la lista actual con sus elementos a la copia
            temp=temp.getDirMemDer();// seguimos avanzando
        }
        return listaClonada;
    }

    /**
     * metodo que nos permite poder obtener un pequeño subconjutno de datos que son de la lista actual de datos
     * @param indiceInicial
     * @param indiceFinal
     * @return  listaSubLista
     */
    @Override
    public Object subLista(int indiceInicial, int indiceFinal) {
        ArregloDatos arregloDatos= new ArregloDatos(indiceFinal);// creamos un  arreglo
        if (indiceInicial>=0 && indiceFinal>=indiceInicial ){
            ListaEncadenadaDoble listaSubLista= new ListaEncadenadaDoble();// geberamos una nueva lista
            for (int i=0;i<NumElementos();i++){// sacamos la longitud de los elementos
                arregloDatos.agregar(obtener(i));// los agregamos a el arreglo de datos
            }
            arregloDatos.subLista(indiceInicial,indiceFinal);// creamos la sublista de inicio a fin
            for (int i=0;i<arregloDatos.cantidadElementos();i++){
                listaSubLista.agregar(arregloDatos.obtener(i));// agregamos esos datos en una lista ligada
            }
            listaSubLista.imprimir();// imprimimos los datos
        }
        return null;
    }

    /**
     * metodo que nos permite saber si una pequeña lista es igual a los datos que estan en la lista actual de elementos
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean esSublista(ListaDatos lista2) {
        if (lista2 instanceof ListaEncadenadaDoble){
            ListaEncadenadaDoble listaNueva= (ListaEncadenadaDoble) lista2;// casteamos los datos
            int contador=0;
            for (int i=0;i<listaNueva.NumElementos();i++){// recorremos la longitud
                if (buscar(listaNueva.obtener(i))!=null){// obtenemos posicion por posicion
                    contador++;
                }
            }
            if (listaNueva.NumElementos()==contador){// si el contador es iguala  el numero de elementos de la lista
                return true;// quiere decir que si es una sublista
            }
        }
        return false;
    }

    /**
     * metodo que nos permite poder sacar todos los datos actuales y colocarle los datos nuevos que se encuentran en la lista 2 pasados como parametros
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean retenerLista(ListaDatos lista2) {
        if (lista2 instanceof  ListaEncadenadaDoble){
            ListaEncadenadaDoble listaNueva= (ListaEncadenadaDoble) lista2;// casteamos la lista de datos 2 a una lista encadenada
            vaciar();// vaciamos la lista actual
            for (int i=0;i< listaNueva.NumElementos();i++){// recorremos los elementos que hay dentro de la lista nueva
                agregar(listaNueva.obtener(i));// agregamos los datos nuevos de la lista 2
            }
            return true;
        }
        return false;
    }

    /**
     * este metodo nos ayuda a poder cambiar un dato en el indice que corresponde por el nuevo elemento
     * @param indice
     * @param elemento
     * @return true o false
     */
    @Override
    public boolean insertar(int indice, Object elemento) {
        int cantidadElementosActuales = NumElementos();

        if (indice>=0 && indice<=cantidadElementosActuales){
            if (indice>cantidadElementosActuales){ // agregar en la ultima posicion.
                agregar(elemento);
            }else if (indice==0){  // agregar primera posicion.
                agergarInicio(elemento);
            }else { // agregar en otra posicion.        indice = 2
                NodoDoble temp = frente;
                for (int posicion=1;posicion<indice;posicion++)
                    temp = temp.getDirMemDer();// seguimos avanzando

                NodoDoble nuevoNodo=new NodoDoble(elemento);// creamos un nuevo nodo
                // se obtiene el Nodo de la posicion.
                NodoDoble aux = temp.getDirMemDer();
                // se agrega el elemento nuevo.
                temp.setDirMemDer( nuevoNodo);
                nuevoNodo.setDirMemIzq(temp);
                // pasamos al nodo nuevo
                temp = temp.getDirMemDer();
                // agregamos el nodo que contiene los demas nodos
                temp.setDirMemDer(aux);
                aux.setDirMemIzq(nuevoNodo);
            }return true;
        }else
            return false;
    }

    /**
     * metodo que nos permite poder cambiar datos de una lista nueva hacia otra lista de datos nueva pasadas como parametros
     * @param listaDatos2
     * @param listaDatos2Nuevos
     * @return true o false
     */
    @Override
    public boolean cambiarLista(ListaDatos listaDatos2, ListaDatos listaDatos2Nuevos) {
        ListaEncadenadaDoble lista2 = (ListaEncadenadaDoble)listaDatos2;
        ListaEncadenadaDoble lista2Nuevos = (ListaEncadenadaDoble)listaDatos2Nuevos;
        // verifica si tienen la misma cantidad de elementos guardados.
        if (!lista2.vacia() && lista2.NumElementos() == lista2Nuevos.NumElementos()){
            lista2.Iteradorinicio();
            lista2Nuevos.Iteradorinicio();

            boolean bandera=false; // cambiara de valor si hizo algun cambio en la lista.
            while (lista2.hayMas() && lista2Nuevos.hayMas()){
                Object elemento = lista2.obtenerSigiuenteFrente();
                Object elementoNuevo = lista2Nuevos.obtenerSigiuenteFrente();
                // contamos si es que esta el elemento en la lista.
                int contarElementosLista = contar(elemento);
                if (contarElementosLista!=0){ // tiene al menos un elemento.
                    cambiar(elemento,elementoNuevo,contarElementosLista);
                    bandera=true;
                }
            }
            return bandera;
        }else
            return false;
    }


    /**
     * metodo que nos permite copiar los datos originales de una lsita nueva a la lista actual de los datos
     * @param listaDatos2
     * @return listaCopiada
     */
    @Override
    public boolean copiarLista(ListaDatos listaDatos2) {
        if (listaDatos2 instanceof  ListaEncadenadaDoble){
            ListaEncadenadaDoble listaCopiada=(ListaEncadenadaDoble) listaDatos2;// casteamos la lista como lista ligada
            ListaEncadenadaDoble listaNueva=new ListaEncadenadaDoble();
            vaciar();// vaciamos los datos originales de la lista
            for (int i=0;i<listaCopiada.NumElementos();i++){// recorremos los datos de esa lista
                listaNueva.agregar(listaCopiada.obtener(i));// agregamos los datos nuevos
            }

            listaNueva.imprimir();
        }
        return false;
    }

    @Override
    public void rellenar(Object elemento) {

    }

    @Override
    public Object regresarFrente() {
        return null;
    }

    @Override
    public Object regresarFin() {
        return null;
    }

    /**
     * este metodo nos permite saber cuandos datos existen dentro de la  lista actual de elementos encadenados
     * @return contador
     */
    public int NumElementos(){
        NodoDoble recorrer = frente;
        int contador=0;
        while (recorrer!=null){
            contador++;
            recorrer=recorrer.getDirMemDer();
        }
        return contador;
    }


    /**
     * Indica si el iterador aun no es null.
     * @return regresa true si el iterador aun no es null, en caso contrario ergresa false.
     */
    public boolean hayMas(){
        if (iterador!=null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Inicializado iterador, retorna el elemento guardado en la lista y pasa al siguiente.
     * @return regresa el elemento actual, en caso contrario regresa null.
     */
    public Object obtenerSigiuenteFrente(){
        if(hayMas()==true){
            Object elemento=iterador.getDato();
            iterador=iterador.getDirMemDer();
            return elemento;
        }else{
            return null;
        }
    }

    /**
     * Inicializado iterador, retorna el elemento guardado en la lista y pasa al siguiente.
     * @return regresa el elemento actual, en caso contrario regresa null.
     */
    public Object obtenerSiguienteFin(){
        if(hayMas()==true){
            Object elemento=iterador.getDato();
            iterador=iterador.getDirMemIzq();// obtenemos el dato siguiente
            return elemento;
        }else{
            return null;
        }
    }



}
