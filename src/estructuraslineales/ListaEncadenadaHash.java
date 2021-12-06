package estructuraslineales;

import entradasalida.SalidaTerminal;
import estructuraslineales.registros.Nodo;
import estructuraslineales.registros.NodoHash;
import estructurasnolineales.Tabla2D;

/**
 * Esta clase nos permite poder representar el funcionamiento de todos los metodos de la tabla hash entre agregar,buscar,insertar,obtener etc
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class ListaEncadenadaHash{
    protected NodoHash frente;
    protected NodoHash fin;


    public ListaEncadenadaHash(){
        frente=null;
        fin=null;
    }
    /**
     * metodo que nos ayuda a saber si la lista esta vacia o no.
     *
     * @return true o false
     */
    public boolean vacia() {
        if (frente == null) {
            return true;
        } else return false;
    }

    /**
     * Vacía el diccionario.
     */
    public void vaciar(){
        frente=null;
        fin=null;
    }


    /**
     * Este metodo nos ayuda a poder agregar los datos de un nodo hash a la lista. recordando de que la lista no puede agregar
     * claves que sean repetidas
     *
     * @param clave
     * @param valor
     * @return true o false
     *
     */
    public boolean insertar(Object clave, Object valor) {
        NodoHash nuevoNodo = new NodoHash(clave, valor);// asignacion de el nuevo nodo a la lista

        if (nuevoNodo != null) {// verificamos de que no sea null
            if (vacia()) { // esta vacia solamente agregamos un dato
                frente = nuevoNodo;
                fin = nuevoNodo;
            } else { // hay mas
                // buscando en la lista que no sea una clave repetida a la clave pasada como argumento.
                NodoHash encontrado = frente;
                while (encontrado != null && !encontrado.getClave().toString().equals(clave.toString()))
                    encontrado = encontrado.getLigaDer();

                if (encontrado == null) { // clave unica.
                    fin.setLigaDer(nuevoNodo);
                    fin = nuevoNodo;
                } else { // son iguales, reemplazar el valor por el nuevo valor pasado en los argumento.
                    encontrado.setValor(valor);
                }
            }
            return true;
        } else return false;
    }


    /**
     * Imprime la lista diccionario completo.
     */
    public void imprimir() {
        NodoHash temp = frente;
        while (temp != null) {
            SalidaTerminal.consola("| "+temp.toString() + "| -> ");
            temp = temp.getLigaDer();
        }
        SalidaTerminal.consola("null");
    }

    /**
     * Imprime las claves.
     */
    public void imprimirClaves() {
        NodoHash temp = frente;
        while (temp != null) {
            SalidaTerminal.consola("| "+temp.getClave() + " -> ");
            temp = temp.getLigaDer();
        }
        SalidaTerminal.consola("null");
    }

    /**
     * Imprime los valores.
     */
    public void imprimirValores() {
        NodoHash temp = frente;
        while (temp != null) {
            SalidaTerminal.consola("| "+temp.getValor() + " -> ");
            temp = temp.getLigaDer();
        }
        SalidaTerminal.consola("null");
    }
    /**
     * Se usa el campo de clave para borrar el elemento.
     *
     * @param clave Dato con el que se buscara el valor a eliminar.
     * @return retorna el valor eliminado, en caso contrario regresa null.
     */
    public Object eliminar(Object clave) {

        if (vacia() == true) { //no hay datos
            return null;
        } else { //si hay datos
            NodoHash anterior = frente;
            NodoHash encontrado = frente;
            while (encontrado != null && !encontrado.getClave().toString().equalsIgnoreCase(clave.toString())) {
                anterior = encontrado;//asignamos el anterior a el dato encontrado
                // seguimos avanzando en la lsita de datos
                encontrado = encontrado.getLigaDer();
            }
            if (encontrado == null) {//no esta
                return null;
            } else { //si esta, pero en algun lado
                Object datoEliminado = encontrado.getValor(); //1
                if (frente == fin) { // unico
                    frente = null;
                    fin = null;
                } else if (encontrado == frente) { //frente
                    frente = frente.getLigaDer();
                } else if (encontrado == fin) { //final
                    anterior.setLigaDer(null);
                    fin = anterior;
                } else { //esta en medio
                    NodoHash posterior = encontrado.getLigaDer();
                    anterior.setLigaDer(posterior);
                }
                return datoEliminado;
            }
        }

    }

    /**
     * Se usa el campo de valor para borrar el elemento.
     *
     * @param valor Dato el cual se desea eliminar.
     * @return retorna el valor eliminado, en caso contrario regresa null.
     */
    public Object eliminarValor(Object valor) {

        if (vacia() == true) { //no hay datos
            return null;
        } else { //si hay datos
            NodoHash anterior = frente;
            NodoHash encontrado = frente;
            while (encontrado != null && !encontrado.getValor().toString().equalsIgnoreCase(valor.toString())) {
                anterior = encontrado;
                encontrado = encontrado.getLigaDer();// seguimos recorriendo los datos de la lista
            }
            if (encontrado == null) {//no esta el dato en la lista
                return null;
            } else { //si esta, pero en algun lado
                Object datoEliminado = encontrado.getValor(); //1
                if (frente == fin) { // unico dato en la lista
                    frente = null;
                    fin = null;
                } else if (encontrado == frente) { //el dato se encuentra a el inicio de la lista
                    frente = frente.getLigaDer();
                } else if (encontrado == fin) { //final
                    anterior.setLigaDer(null);
                    fin = anterior;
                } else { //esta en medio
                    NodoHash posterior = encontrado.getLigaDer();
                    anterior.setLigaDer(posterior);
                }
                return datoEliminado;
            }
        }

    }

    /**
     * Cuenta los elementos guardados en la lista.
     *
     * @return numElementos
     */
    public int cantidadElementos() {
        int numElementos = 0; // inicializa el contador de elementos

        if (!vacia()) { // hay algo
            NodoHash temp = frente;
            while (temp != null) {
                numElementos++;
                temp = temp.getLigaDer();
            }
        }
        return numElementos;
    }

    /**
     * Se utiliza para buscar un elemento ubicado por su clave.
     *
     * @param clave
     * @return recorrer
     */
    public Object buscar(Object clave) {
        NodoHash recorrer = frente;
        // buscando la clave en la lista.
        while (recorrer != null && !recorrer.getClave().toString().equalsIgnoreCase(clave.toString())) {
            recorrer = recorrer.getLigaDer();
        }
        if (recorrer == null) { // no lo encontro.
            return null;
        } else { // lo encontro
            return recorrer.getClave();
        }
    }

    /**
     * metodo que nos ayuda a poder buscar un elemento ubicado por su valor.
     *
     * @param valor
     * @return recorrer
     */
    public Object buscarValor(Object valor) {
        NodoHash recorrer = frente;
        // buscando el valor en la lista.
        while (recorrer != null && !recorrer.getValor().toString().equalsIgnoreCase(valor.toString())) {// recorremos la lista mientras el dato aun no se encuentre
            recorrer = recorrer.getLigaDer();//vamos recorriendo  la lista
        }
        if (recorrer == null) { // no lo encontro.
            return null;
        } else { // lo encontro.
            return recorrer.getValor();
        }
    }

    /**
     * Substituye un elemento de la lista localizado mediante la
     * clave por el nuevo valor.
     *
     * @param clave
     * @param valorNuevo
     * @return recorrer.
     */
    public boolean substituir(Object clave, Object valorNuevo) {
        NodoHash recorrer = frente;
        // buscando la clave en la lista.
        while (recorrer != null && !recorrer.getClave().toString().equalsIgnoreCase(clave.toString())) {
            recorrer = recorrer.getLigaDer();
        }
        if (recorrer == null) { // no lo encontro.
            return false;
        } else { // lo encontro.
            recorrer.setValor(valorNuevo);
            return true;
        }
    }

    /**
     * Substituye un elemento de la lista localizado mediante
     * la valor por el nuevo valor.
     *
     * @param valor
     * @param valorNuevo
     * @return true o false
     */
    public boolean substituirValor(Object valor, Object valorNuevo) {
        NodoHash recorrer = frente;
        // buscando el valor en la lista.
        while (recorrer != null && !recorrer.getValor().toString().equalsIgnoreCase(valor.toString())) {
            recorrer = recorrer.getLigaDer();
        }
        if (recorrer == null) { // no lo encontro.
            return false;
        } else { // lo encontro.
            recorrer.setValor(valorNuevo);
            return true;
        }
    }


    /**
     * Regresa la lista diccionario como una lista que contiene dos
     * arreglos, un arreglo es la lista de claves, y el otro es un arreglo que tiene la lista de valores
     * de la lista diccionario.
     * @return listaEncadenada
     */
    public ListaEncadenada aArreglos(){
        int cantidadElementosActuales = cantidadElementos(); // tamaño que tendra los arreglos.
        ArregloDatos claves = new ArregloDatos(cantidadElementosActuales);
        ArregloDatos valores= new ArregloDatos(cantidadElementosActuales);

        NodoHash temp = frente;
        while (temp!=null){ // agrega elementos a los arreglos.
            claves.agregar(temp.getClave());
            valores.agregar(temp.getValor());
            temp = temp.getLigaDer();
        }

        ListaEncadenada listaEncadenada = new ListaEncadenada();

        listaEncadenada.agregar(claves);
        listaEncadenada.agregar(valores);

        return listaEncadenada;
    }

    /**
     * Regresa la lista diccionario como una lista que contiene dos
     * listas ligadas, una es la lista de claves, y la otra es una lista que tiene los elementos con
     * los valores.
     * @return listaEncadenada
     */
    public ListaEncadenada aLista(){
        ListaEncadenada claves = new ListaEncadenada();
        ListaEncadenada valores = new ListaEncadenada();

        NodoHash temp = frente;
        while (temp!=null){ // agrega elementos a las listas
            claves.agregar(temp.getClave());
            valores.agregar(temp.getValor());
            temp = temp.getLigaDer();
        }

        ListaEncadenada listaEncadenada = new ListaEncadenada();

        listaEncadenada.agregar(claves);
        listaEncadenada.agregar(valores);

        return listaEncadenada;
    }

    /**
     * Regresa una matriz con la estructura de la lista diccionario.
     * @return clavesValores
     */
    public Tabla2D aTabla(){
        Tabla2D clavesValores = new Tabla2D(cantidadElementos(),2);

        NodoHash temp = frente;
        for (int posicion=0;  temp!=null ;posicion++){ // agrega elementos a la tabla
            clavesValores.asignarCelda(posicion,0,temp.getClave());
            clavesValores.asignarCelda(posicion,1,temp.getValor());
            temp = temp.getLigaDer();
        }

        return clavesValores;
    }


    /**
     * Obtiene el valor de la clave especificada.
     * @param clave Dato unico que buscara el valor.
     * @return elementoBuscado
     */
    public Object obtener(Object clave){
        return buscar(clave);
    }

    /**
     * Agrega todos los elementos de la
     * lista pasada como argumento a la lista actual.
     * @param lista2
     * @return true o false
     */
    public boolean agregarLista(ListaEncadenadaHash lista2){
        boolean bandera = false; // cambiara de valor indicando que agrego elementos nuevos a la lista actual.
        NodoHash temp = lista2.frente;
        while (temp!=null){
            if (buscar(temp.getClave())==null) { // no existe la clave en la lista, agrega la clave y el valor.
                insertar(temp.getClave(),temp.getValor());

            }
            temp=temp.getLigaDer();
        }
        return bandera;
    }

    /**
     * Agrega todos los elementos del arreglo pasado como argumento, donde hay arreglos
     * paralelos de clave y valor respectivamente.
     * @param arregloClaves
     * @param arregloValores
     * @return true o false
     */
    public boolean agregarArreglos(ArregloDatos arregloClaves, ArregloDatos arregloValores){
        boolean bandera = false; // cambiara de valor indicando que agrego elementos nuevos a la lista actual.
        if (arregloClaves.cantidadElementos()==arregloValores.cantidadElementos()){
            for (int posicion=0;posicion<arregloClaves.cantidadElementos();posicion++){
                if (buscar(arregloClaves.obtener(posicion))==null){ // no existe la clave en la lista, agrega la clave y el valor.
                    insertar(arregloClaves.obtener(posicion),arregloValores.obtener(posicion));
                    bandera=true;
                }
            }
        }
        return bandera;

    }

    /**
     * Agrega todos los elementos de la lista pasada como argumento, donde hay listas paralelas
     * de clave y valor respectivamente.
     * @param listaClaves
     * @param listaValores
     * @return true o false
     */
    public boolean agregarLista(ListaEncadenada listaClaves, ListaEncadenada listaValores){
        boolean bandera = false; // cambiara de valor indicando que agrego elementos nuevos a la lista actual.
        if (listaClaves.numElementos()==listaValores.numElementos()){// verificamos de que tenga la misma longitud las dos listas

            listaClaves.inicializarIterador();
            listaValores.inicializarIterador();

            while (listaClaves.hayMas()) {
                Object clave = listaClaves.obtenerSigiuente();
                Object valor = listaValores.obtenerSigiuente();

                if (buscar(clave)==null){ // no existe la clave en la lista, agrega la clave y el valor.
                    insertar(clave,valor);
                }
            }
        }
        return bandera;

    }

    /**
     * Agrega todos los elementos de la matriz pasada
     * como argumento, donde la primera columna tiene las claves y a segunda columna tiene
     * los valores.
     * @param tabla
     * @return true o false
     */
    public boolean agregarTabla(Tabla2D tabla){
        boolean bandera = false; // cambiara de valor indicando que agrego elementos nuevos a la lista actual.
        if (tabla.getColumnas()==2){ // valida que la tabla tenga unicamente 2 columnas.
            for (int posicion=0;posicion<tabla.getFilas();posicion++){
                insertar(tabla.obtenerInfo(posicion,0),tabla.obtenerInfo(posicion,1));
                bandera = true;
            }
        }
        return bandera;
    }

}
