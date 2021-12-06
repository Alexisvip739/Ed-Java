package estructuraslineales;

import Herramientas.TipoTabla;
import entradasalida.SalidaTerminal;
import estructuraslineales.registros.Nodo;
import estructurasnolineales.Tabla2D;
import javafx.scene.control.Tab;

/**
 * Clase en donde se encuentra toda la funcionalidad de los metodos de Lista Ligada junto con los metodos recursivos de cada uno de ellos
 * @author Alexis Ultreras Sotelo
 * @version 2.0
 */
public class ListaEncadenada implements ListaDatos {
    protected Nodo frente;// inicio de el nodo
    protected Nodo fin;// ultima posicion de el nodo

    protected Nodo iterador;

    /**
     * constructor que inicializa los datos de la lista ligada
     */
    public ListaEncadenada() {
        frente = null;
        fin = null;
    }


    /**
     * verifica de que la lsita ligada esta vacia o no
     *
     * @return true o false
     */
    @Override
    public boolean vacia() {
        if (frente == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Inserta al final de la lista un elemento proporcionado.
     *
     * @param elemento Es el dato que se desea agregar a la lista.
     * @return Regresa un 1 en caso de que se agregue el elemento, o -1 en caso contrario.
     */
    @Override
    public int agregar(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento); //1
        if (nuevoNodo != null) { //si hay espacio
            if (vacia() == true) { //a
                frente = nuevoNodo;//2
                fin = nuevoNodo;
            } else { //b y c
                fin.setDirMemDer(nuevoNodo);//2
                fin = nuevoNodo;//3
            }
            return 1;
        } else { //no hay espacio
            return -1;
        }
    }

    @Override
    public Object buscar(Object elemento) {
        Nodo recorrer = frente;
        while (recorrer != null && !recorrer.getDato().toString().equalsIgnoreCase(elemento.toString())) {
            recorrer = recorrer.getDirMemDer();

        }
        if (recorrer == null) {
            return null;
        } else {
            return recorrer.getDato();
        }
    }

    /**
     * (terminado)
     * metodo que manda llamar el metodo recursivo de buscarRR1
     *
     * @param info
     * @return
     */
    public Object buscarRR(Object info) {
        Nodo temp = frente;
        return buscarRR1(info, temp);
    }

    /**
     * busca una info de forma recursiva, y si la encuentra imprime e el dato
     *
     * @param info
     * @param temp
     * @return
     */
    private Object buscarRR1(Object info, Nodo temp) {
        if (temp != null && !temp.getDato().toString().equalsIgnoreCase(info.toString())) {
            return buscarRR1(info, temp.getDirMemDer());
        } else if (temp == null) {
            return null;
        } else {
            return temp.getDato();
        }

    }

    /**
     * metodo que nos ayuda a poder eliminar un dato pasando como dato un objeto elemento
     *
     * @param elemento
     * @return
     */
    @Override
    public Object eliminar(Object elemento) {
        Object elementoBorrado = null;
        if (vacia() == false) { //no está vacía, hay algo
            Nodo anterior = frente;
            Nodo encontrado = frente;
            while (encontrado != null && !encontrado.getDato().toString().equalsIgnoreCase(elemento.toString())) {
                anterior = encontrado;
                encontrado = encontrado.getDirMemDer();
            }
            if (encontrado == null) { //no existe el dato encontrado
                return null;
            } else { //si existe,
                elementoBorrado = encontrado.getDato();// obtenemos el dato
                if (frente == fin) { // el dato es unico
                    frente = null; // se borra el elemento y se hace null
                    fin = null;
                } else if (frente == encontrado) { // el dato es el  primero
                    frente = frente.getDirMemDer();
                } else if (fin == encontrado) { // el dato es el último
                    anterior.setDirMemDer(null);
                    fin = anterior;
                } else { //cualquier otro caso, es decir, en medio se borra el dato
                    Nodo siguiente = encontrado.getDirMemDer();
                    anterior.setDirMemDer(siguiente);//2
                }
                return elementoBorrado;
            }
        } else { // si no hace nada es por que la lista esta vacia
            return null;
        }
    }

    /**
     * metodo que nos manda llamar a el metodo recursivio de eliminar RR1
     *
     * @param elemento
     * @return
     */
    public Object eliminarRR(Object elemento) {
        return eliminarRR1(elemento, frente);
    }

    /**
     * Método en donde elimina un elemento
     *
     * @param elemento elemento a eliminar
     * @return elemento borrado
     */
    private Object eliminarRR1(Object elemento, Nodo temporal) {
        Object elementoBorrado = null;
        if (vacia() == false) { //hay algo
            if (temporal != null) {
                if (temporal.getDirMemDer().getDato().equals(elemento)) {
                    elementoBorrado = temporal.getDirMemDer().getDato();
                    temporal.setDirMemDer(temporal.getDirMemDer().getDirMemDer());
                    return elementoBorrado;
                } else {
                    return eliminarRR1(elemento, temporal.getDirMemDer());
                }
            } else {
                return null;
            }
        } else { //vacía  a)
            return null;
        }
    }


    /**
     * elimina el dato que esta a final de la lista ligada
     *
     * @return
     */
    @Override
    public Object eliminar() {
        Object datoEliminado = null;

        if (vacia() == true) { //a, esta vacia
            return null;
        } else { //hay algo
            datoEliminado = fin.getDato();//1
           if (frente == fin) { //b, único
                //datoEliminado = fin.getDato();//1
                frente = null;//2
                fin = null;//2
            } else { //c, varios elementos
                //datoEliminado = fin.getDato();//1
                //buscar el penultimo
                Nodo penultimo = frente;
                while (penultimo.getDirMemDer() != fin) {
                    penultimo = penultimo.getDirMemDer();  //i=i+1
                }
                fin = penultimo;//2
                fin.setDirMemDer(null);//3
            }
            return datoEliminado;
        } //
    }

    /**
     * Este metodo eliminar el ultimo elemento de la lista
     *
     * @return elemento borrado
     */
    public Object eliminarRR() {
        return eliminarRR1(frente, fin.getDato());
    }

    /**
     * Este metodo elimina el ultimo elemento de la lista
     *
     * @param nodo Primer nodo
     * @param info informacion Borrada Ultimo
     * @return el elemento borrado
     */
    private Object eliminarRR1(Nodo nodo, Object info) {
        Object elementoBorrado = null;
        if (vacia() == false) { //hay algo
            elementoBorrado = fin.getDato(); //1
            if (frente == fin) { //único  b)
                //elementoBorrado=ultimo.getInfo(); //1
                frente = null;//2
                fin = null;
            } else { //hay varios  c)
                //elementoBorrado=ultimo.getInfo(); //1
                if (nodo.getDirMemDer() != fin) {
                    nodo = nodo.getDirMemDer();
                    return eliminarRR1(nodo, info);
                }
                fin = nodo;//2
                fin.setDirMemDer(null);//3
            }
            return elementoBorrado;
        } else { //vacía  a)
            return null;
        }
    }

    /**
     * Inserta al inicio de la lista un elemento proporcionado.
     *
     * @param elemento Es el dato que se desea agregar a la lista.
     * @return Regresa un 1 en caso de que se agregue el elemento, o -1 en caso contrario.
     */
    public int agregarInicio(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento); //1
        if (nuevoNodo != null) { //si hay espacio
            if (vacia() == true) { //a
                frente = nuevoNodo;//2
                fin = nuevoNodo;
            } else {  //b
                nuevoNodo.setDirMemDer(frente);//2
                frente = nuevoNodo;//3
            }
            return 1;
        } else { //no hay espacio
            return -1;
        }
    }


    /**
     * metodo que nos muestra la ultima posicion de la lista
     *
     * @return
     */
    @Override
    public Object verTope() {
        return fin;
    }


    /**
     * metodo que nos imprime los datos que estan ingresados en la lista
     * para despues mostrarlos
     */
    @Override
    public void imprimir() {
        Nodo temp = frente;
        while (temp != null) {// mientas el nodo sea diferente a null
            SalidaTerminal.consola(temp.getDato() + " -> ");// imprimimos posicion
            temp = temp.getDirMemDer();// seguimos recorriendo
        }
        SalidaTerminal.consola("null");
    }

    public void imprimirRR() {
        ImprimirRR1(frente);
    }

    private void ImprimirRR1(Nodo temporal) {
        Nodo temp = temporal;
        if (temp != null) {
            SalidaTerminal.consola(temp.getDato() + " -> ");
            ImprimirRR1(temporal.getDirMemDer());
        } else {
            SalidaTerminal.consola("null");
        }
    }

    /**
     * metodo que nos ayuda a obtener los datos de forma inversa
     */
    @Override
    public void imprimirOrdenInverso() {
        SalidaTerminal.consola(null + " <- " + fin);
        Nodo temp = fin;
        while (true) {
            Nodo anterior = frente;
            if (temp == frente) {
                break;
            } else {
                while (anterior.getDirMemDer() != temp) {// recorremos con un ciclo
                    anterior = anterior.getDirMemDer();// obtenemos la liga derecha
                }
                temp = anterior;
                SalidaTerminal.consola(" <- " + anterior);// obtenemos los datos anterioriores
            }
        }
    }
    public void imprimirOIRR(){
        imprimirRROI(frente);
    }

    /**
     * este metod  imprime los datos de forma recursiva
     * @param temp
     */
    private  void imprimirRROI(Nodo temp){
        if (temp!= null){
            imprimirRROI(temp.getDirMemDer());

            SalidaTerminal.consola(temp.getDato()+" -> ");


        }
        if(frente.equals(fin)){
            SalidaTerminal.consola(fin.getDato()+" ");
        }




    }


    /**
     * metodo que nos ayuda a encontrar un elemento dentro de la lista ligada pasado
     * como parametro
     * @param elemento
     * @return recorrer
     */


    /**
     * metodo que nos hace convertir una lista ligada a un arreglo de datos
     *
     * @return
     */
    public ArregloDatos aArreglo() {
        Nodo iterador = frente;
        int numeroElementosLista = numElementos();// obtenemos el numero de elementos
        ArregloDatos listaArreglo = new ArregloDatos(numeroElementosLista);// lo asignamos como una longitud para el arreglo de datos
        while (iterador != null) {
            listaArreglo.agregar(iterador.getDato());// agregamos todos los datos uno por uno
            iterador = iterador.getDirMemDer();// seguimos aumentando el siguiente valor
        }
        return listaArreglo; //regresamos la lista
    }


    /**
     * este metodo nos permite poder madnar llamara a el metodo recursivo de aArreglo1
     * @return
     */
    public ArregloDatos aArregloRR(){
        ArregloDatos arregloDatos=new ArregloDatos(numElementos());
        return aArregloRR1(arregloDatos,frente);
    }

    /**
     * Método que guarda los elementos de la lista en un arreglo.
     * @return Arreglo con los elementos de la lista.
     */
    private ArregloDatos aArregloRR1(ArregloDatos arreglo, Nodo temporal){
        ArregloDatos aArreglo = (ArregloDatos) arreglo;// casteamos el arreglo de datos
        Nodo temp = (Nodo) temporal;// creamos un nodo temporal
        if(temp != null){
            aArreglo.agregar(temp.getDato());
            return aArregloRR1(arreglo, temp.getDirMemDer());// recorremos la lista cada vez que se argeguen datos a el arreglo
        }else{
            return aArreglo;
        }
    }

    /**
     * metodo que nos permite guardar todos los elementos de una lista a un arreglo siempre
     * y cuando no sean iguales a los que contiene el arreglo pasado como argumento
     * @param arregloADescartar
     * @return arregloADescartar
     */
    public ArregloDatos arreglo(ArregloDatos arregloADescartar){
        Nodo actual=frente;
        while (actual!=null){
            if(arregloADescartar.buscar(actual.getDato()) == null){// en caso de que el datos a buscar sea null
                arregloADescartar.agregar(actual.getDato());// lo agregamos a el arreglo
            }
            actual=actual.getDirMemDer();// seguimos avanzando en la lista dato por dato
        }
        return arregloADescartar;
    }

    /**
     * Metodo que permite mandar llamar a el metodo recursivo arregloRR1  con sus parametros a inicializar para su ejecucion
     * @param arregloADescartar
     * @return
     */
    public ArregloDatos arregloRR(ArregloDatos arregloADescartar){
        //Creamos el arreglo a retornar
        ArregloDatos listaArreglo = aArreglo();
        return aArregloRR(arregloADescartar,listaArreglo,0);
    }

    /**
     * metodo que nos permite guardar todos los elementos de una lista a un arreglo siempre
     *  y cuando no sean iguales a los que contiene el arreglo pasado como argumento
     * @param arregloADescartar
     * @param listaArreglo
     * @param posIni
     * @return listaArreglo
     */
    private ArregloDatos aArregloRR(ArregloDatos arregloADescartar, ArregloDatos listaArreglo, int posIni){
        if(posIni<arregloADescartar.cantidadElementos()){// Asignamos la cantidad de elementos
            Object elementoActualDecart = arregloADescartar.obtener(posIni);//obtenemos la posicion de los datos
            Object elementoEncontrado = buscar(elementoActualDecart);// buscamos el dato

            if(elementoEncontrado!=null){//Si encontró el elemento a descartar lo vamos a quitar del arreglor resultante
                listaArreglo.eliminar(elementoEncontrado);
            }
            return aArregloRR(arregloADescartar,listaArreglo,posIni+1);
        }
        return listaArreglo;
    }

    /**
     * metodo que nos permite poder agregar la lista a una matriz 2d
     * @param renglones
     * @param columnas
     * @return matriz2D
     */
    public Tabla2D aMatriz2D(int renglones, int columnas){
        Tabla2D matriz2D=new Tabla2D(renglones,columnas,null);// asignamos la capasidad de la matriz
        Nodo temp=frente;// obtenemos el frente
        int i=0;
        int j=0;
        while (temp!=null){
            if(j <= columnas && j<=renglones){
                matriz2D.asignarCelda(i,j,temp.getDato());// rellenamos la matriz el numero de veces que se este encontrando un dato
                j++;
            }else{
                i++;
                j=0;
            }
            temp=temp.getDirMemDer();// seguimos avanzando
        }
        return matriz2D;
    }

    /**
     * metodo que manda llamar a el metodo recursivo para poder comvertir lista en matriz
     * @param renglones
     * @param columnas
     * @return  resultado de la matriz obtenida
     */
    public Tabla2D aMatriz2DRR(int renglones, int columnas){
        if(renglones>0 && columnas>0){//Validamos que los renglones y las columnas sean validas

            //Creamos la matriz resultante
            Tabla2D matriz = new Tabla2D(renglones, columnas);
            return aMatriz2DRR(0,0,frente,renglones,columnas, matriz);

        }
        return null;
    }

    /**
     * metodo que convierte una lista ligada en una matriz de datos recursiva
     * @param renglones
     * @param columnas
     * @param primero
     * @param nReng
     * @param nCol
     * @param matriz
     * @return matriz
     */
    private Tabla2D aMatriz2DRR(int renglones, int columnas,  Nodo primero, int nReng, int nCol, Tabla2D matriz){
        if(primero!=null && renglones+1<=nReng){
            matriz.asignarCelda(renglones,columnas,primero.getDato());

            if(columnas==nCol-1){//Se completó un renglón
                columnas=0;
                renglones++;
            }else{
                columnas++;
            }
            primero = primero.getDirMemDer();// obtenemos la siguiente posicion
            return aMatriz2DRR(renglones,columnas,primero,nReng,nCol,matriz);
        }
        return matriz;
    }





    /**
     * metodo que nos ayuda  a poder agregar una lista ligada nueva a la lista actual
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean agregarLista(Object listaDatos2) {
        return  true;
    }


    /**
     * metodo que nos ayuda a poder clonar la lista actual
     * @return listaCloanda
     */
    @Override
    public Object clonar() {
        ListaEncadenada listaClonada= new ListaEncadenada();// creamos una lista nueva
        if (listaClonada!=null){
            Nodo temp= frente;
            while (temp!=null){
                listaClonada.agregar(temp);// agregamos la lista actual con sus elementos a la copia
                temp=temp.getDirMemDer();// seguimos avanzando
            }
            listaClonada.imprimir();
        }
        return null;
    }

    /**
     * metodo recursivo que nos ayuda a poder mandar llamar el metodo de ClonarRR1
     * @return listaClonada
     */
    public  Object clonarRR(){
        ListaEncadenada listaClonada= new ListaEncadenada();
        if (listaClonada!=null){
            clonarRR1(listaClonada,frente);
            listaClonada.imprimir();
        }
        return null;
    }
    public  Object clonarRR1(ListaEncadenada listaClonada,Nodo inicio){
        if (inicio!=null){
            listaClonada.agregar(inicio);
            clonarRR1(listaClonada,inicio.getDirMemDer());
        }
        return null;

    }

    /**
     * metodo que nos permite agregar filas o columans a la lista ligada
     * @param tabla
     * @param enumTipoTabla
     * @return
     */
    public  boolean agregarTabla2D(Tabla2D tabla, TipoTabla enumTipoTabla){
        if(enumTipoTabla.equals(TipoTabla.COLUMNA)){// en caso de que sean solo columnas
            for(int col=0;col<tabla.getColumnas();col++) {// recorremos las columnas
                for(int ren=0;ren<tabla.getFilas();ren++){
                    agregar(tabla.obtenerInfo(col,ren));// agregamos las columnas a la lista
                }
            }
            return true;
        }else {
            for(int ren=0;ren<tabla.getFilas();ren++) {// recorremos las columnas
                for(int col=0;col<tabla.getColumnas();col++){
                    agregar(tabla.obtenerInfo(col,ren));// agregamos las columnas a la lista
                }
            }
        }
            return false;

    }

    /**
     * metodo que regresa el resultado de la recursion para obtener los datos de una matriz
     * @param matriz
     * @param enumTipoMatriz
     * @return true o false
     */
    public boolean agregarMatriz2DRR(Tabla2D matriz, TipoTabla enumTipoMatriz){
        if(enumTipoMatriz.equals(TipoTabla.FILA)){
            return agregarMatriz2DRenglonRR(matriz,0,0,matriz.getFilas(), matriz.getColumnas());
        }else{//Agregamos por columna
            return agregarMatriz2DColumnaRR(matriz,0,0,matriz.getFilas(), matriz.getColumnas());
        }
    }

    /**
     * metodo que nos permite poder agregar los datos de una fila a  la lista actual
     * con recursino
     * @param matriz
     * @param reng
     * @param col
     * @param numR
     * @param numC
     * @return true o false
     */
    private boolean agregarMatriz2DRenglonRR(Tabla2D matriz, int reng, int col, int numR, int numC){
        //Agregamos el elemento a nuestra lista ligada
        Object elemento = matriz.obtenerInfo(reng,col);
        agregar(elemento);
        if(reng!=(numR-1) || col!=(numC-1)){//Mientras existan más renglones o columnas
            if(col!=(numC-1)){
                col++;
            }else{
                col=0;
                reng++;
            }
            return agregarMatriz2DRenglonRR(matriz,reng,col,numR,numC);// generamos el metodo recursivo
        }
        return true;
    }

    /**
     * metodo recursivo que nos permite poder agregar las columnas de una matriz a una lista ligada de datos
     * @param matriz
     * @param reng
     * @param col
     * @param numR
     * @param numC
     * @return true o false
     */
    private boolean agregarMatriz2DColumnaRR(Tabla2D matriz, int reng, int col, int numR, int numC){
        //Agregamos el elemento a nuestra lista ligada
        Object elemento = matriz.obtenerInfo(reng,col);
        agregar(elemento);//Agregamos el elemento a la lista
        if(reng!=(numR-1) || col!=(numC-1)){//Mientras existan más renglones o columnas
            if(reng!=(reng-1)){
                reng++;
            }else{
                reng=0;
                col++;
            }
            return agregarMatriz2DRenglonRR(matriz,reng,col,numR,numC);
        }
        return true;
    }

    /**
     * metodo que nos ayuda a poder sacar todos los datos de la lista y limpiarlos
     */
    @Override
    public void vaciar() {
        fin=null;
        frente=null;

    }



    /**
     * rellena un numero de veces un elemento en espesifico dentro de la lista encadenada
     * @param elemento
     * @param cantidad
     */
    @Override
    public void rellenar(Object elemento, int cantidad) {
        if (elemento!=null){
            int contador=0;
            if (cantidad > numElementos()) {// verificamos que cantidad sea mayor a numero de elementos de la lista
                cantidad = numElementos();
                for (int a = 0; a < numElementos(); a++)// recorremos el numero de elementos
                    agregar( elemento);// agregamos los datos
            } else {
                for (int a = 0; a < cantidad; a++)
                    agregar( elemento);
            }
        }
    }

    /**
     * metodo que nos permite poder llamar a el metodo recursivo de rellenarRR1
     * @param elemento
     * @param cantidad
     */
    public void rellenarRR(Object elemento, int cantidad){
        rellenarRR1(elemento,cantidad,0);
    }

    /**
     * metodo recursivo que nos permite rellenar posiciones solas en una lsita ligada de datos
     * @param elemento
     * @param cantidad
     * @param contador
     */
    private   void rellenarRR1(Object elemento,int cantidad,int contador){
        if (elemento!=null){
            if (cantidad > numElementos()) {// verificamos que cantidad sea mayor a numero de elementos de la lista
                cantidad = numElementos();
                if (contador< numElementos()){// verificamos que el contador sea menor a el numero de elementos
                    agregar( elemento);// agregamos los datos
                    rellenarRR1(elemento,cantidad,contador+1);
                }// recorremos el numero de elementos
            } else {
                if (contador< cantidad){
                    agregar( elemento);
                    rellenarRR1(elemento,cantidad,contador+1);
                }

            }
        }
    }


    /**
     * cuenta las veces que un dato en la lista ligada se esta repitiendo
     * @param elemento
     * @return i
     */
    @Override
    public int contar(Object elemento) {
        Nodo recorrer = frente;
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
     * metodo  de contar los elementos repetidos que estan dentro de una lista ligada
     * @param elemento
     * @return contador
     */
    public  int contarRR(Object elemento){
        return contarRR1(elemento,frente,0);
    }

    /**
     * metodo recursivo que nos permite obtener el contador de elementos repetidos en una lista
     * @param info
     * @param temporal
     * @param contador
     * @return contador
     */
    private int contarRR1(Object info, Nodo temporal, int contador) {
        if(temporal!=null){
            if(temporal.getDato().equals(info)) {// verificamos de que los datos sean iguales
                contador++;// aumentamos una posicion
            }
            return contarRR1(info,temporal.getDirMemDer(),contador);// seguimos avanzando en el metodo recursivo la siguiente posicion
        }else{
            return contador;
        }
    }


    /**
     * metodo que nos ayuda a poder modificar la lista actual imprimida por un orden inverso
     */
    @Override
    public void invertir() {
        Nodo FrenteInicio=frente;
        Nodo nuevoFrente= new Nodo(fin.getDato());
        frente=nuevoFrente;// se asigna el nuevo frente que empezara en inverso
        Nodo temp=fin;// asignamos final
        while (temp!=FrenteInicio){
            Nodo anterior=FrenteInicio;
            while ( anterior.getDirMemDer()!=temp){
                anterior=anterior.getDirMemDer();// aumentamos posicion
            }
            nuevoFrente.setDirMemDer(new Nodo(anterior.getDato()));// asignamos la posicion al nuevo frente de la lista invertida
            nuevoFrente=nuevoFrente.getDirMemDer();
            temp=anterior;
        }
        fin=nuevoFrente;
    }

    /**
     * metodo que nos ayuda a poder cambiar el orden de los datos de una manera recursiva
     */
    public void invertirRR(){
        if (!vacia()){
            Nodo auxFrente = frente;// asignamos frente
            Nodo nuevoFrente = new Nodo(fin.getDato());// creamos un nuevo nodo con el fin
            frente = nuevoFrente;// asignamos el frente
            invertirRR(fin,auxFrente,frente,auxFrente);
        }
    }

    /**
     * metodo recursivo en donde crea la lista invertida con los datos
     * @param primero
     * @param ultimo
     * @param nuevoFrente
     * @param auxFrente
     */
    private void invertirRR(Nodo primero, Nodo ultimo, Nodo nuevoFrente, Nodo auxFrente){
        if (primero!=auxFrente) {
            if (ultimo.getDirMemDer() != primero)// verificamos de que la liga derecha de primero no sea primero
                invertirRR(primero, ultimo.getDirMemDer(), nuevoFrente,auxFrente);// generamos el metodo recursivo
            else {
                nuevoFrente.setDirMemDer(new Nodo (ultimo.getDato()));
                invertirRR(ultimo, auxFrente, nuevoFrente.getDirMemDer(),auxFrente);
            }
        }else {
            fin=nuevoFrente;
        }
    }


    /**
     * metodo que nos ayuda a poder cambiar un dato viejo por uno nuevo algun numero de veces que se repita si es que esta muchas veces
     * @param elementoViejo
     * @param elementoNuevo
     * @param numVeces
     * @return true o false
     */
    @Override
    public boolean cambiar(Object elementoViejo, Object elementoNuevo, int numVeces) {
        if (elementoNuevo !=null && elementoNuevo !=null){
            int count = 1;
            for (int i=0;i<numVeces;i++){// recorremos el numero de veces que se va a repetir
                Object elemento=obtener(count);// obtenemos el elemento
                if (elemento!=null){
                    insertar(count,elementoNuevo);// insertamos ese elemento el numero de veces
                    count++;
                }

            }
            return true;
        }
        return false;
    }

    /**
     * metodo que nos permite poder cambiar datos de una lista ligadaa un numero de veces
     * @param elementoViejo
     * @param elementoNuevo
     * @param numVeces
     * @return true o false
     */
    public  boolean cambiarRR(Object elementoViejo, Object elementoNuevo, int numVeces){
        return cambiarRR1(elementoViejo,frente,numVeces,elementoNuevo);
    }

    public  boolean cambiarRR1(Object elementoViejo,Nodo cont,int pos,Object infoNueva){
        if(cont.equals(fin)){
            if(fin.getDato().equals(elementoViejo)){
                fin.setDato(infoNueva);
                return true;
            }else{
                return false;
            }
        }else{
            if(cont.getDato().equals(elementoViejo)){
                cont.setDato(infoNueva);
                return true;
            }else{
                return cambiarRR1(elementoViejo,cont.getDirMemDer(),pos+1,infoNueva);
            }
        }

    }

    /**
     * Metodo que nos ayuda a poder retirar un elemento cambiandolo por otro en una posicion
     * determinada
     * @param indice
     * @param info
     * @return true o false
     */
    public boolean cambiar(int indice, Object info){
        int index=0;
        Nodo recorrer=frente;
        while (recorrer!=null){
            if (index==indice){// si el indice de el dato es igual a el index
                recorrer.setDato(info);// establecemos ahi el nuevo dato
            }
            recorrer=recorrer.getDirMemDer();
            index++;
        }
        return  true;
    }

    public Object cambiarRR(int indice,Object infoNueva){
        return cambiarRR1(indice,frente,0,infoNueva);
    }
    /**
     * Este metodo cambia la informacion de un elemento por su indice
     * @param indice indice
     * @param cont primer nodo
     * @param pos posicion 0
     * @param infoNueva informacion nueva
     * @return true si se cambio y false si no
     */
    private Object cambiarRR1(int indice,Nodo cont,int pos,Object infoNueva){
        if(cont.equals(fin)){
            if(pos==indice){
                fin.setDato(infoNueva);
                return true;
            }else{
                return false;
            }
        }else{
            if(indice==pos){
                cont.setDato(infoNueva);
                return true;
            }else{
                return cambiarRR1(indice,cont.getDirMemDer(),pos+1,infoNueva);
            }
        }
    }


    /**
     * Obtenemos la posicion de un elemento agregado de la lista por medio de un indice
     * @param indice
     * @return temp
     */
    @Override
    public Object obtener(int indice) {
        Nodo temp=frente;
        int posicion = 0;
        while (temp!= null && posicion!=indice-1) {
           temp=temp.getDirMemDer();
           if (temp!=null){
               posicion++;
           }
        }
        if (temp!=null){
            return temp.getDato();
        }else {
            return null;
        }
    }



    /**
     * Este metodo obtiene un elemento por su posicion
     * @param indice posicion
     * @return elemento o null si no esta
     */
    public Object obtenerRR(int indice){
        return obtenerRR1(indice,frente,0);
    }
    /**
     * Este metodo obtiene un elemento por su posicion
     * @param indice idice
     * @param cont Nodo primero
     * @param pos pos 0
     * @return elemento encontado o null si no se encontro
     */
    private Object obtenerRR1(int indice,Nodo cont,int pos){
        if(cont.equals(fin)){// verificamos que frente sea igual a  fin
            if(pos==indice){// si es igual
                return fin.getDato();
            }else{
                return null;
            }
        }else{
            if(indice==pos){
                return cont.getDato();
            }else{
                return obtenerRR1(indice,cont.getDirMemDer(),pos+1);// hacemos el metodo recursivo
            }
        }
    }


    /**
     * veridica de que una lista pasada como objeto es igual a la lista actual
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean esIgual(Object listaDatos2) {
        if (listaDatos2 instanceof  ListaEncadenada){
            ListaEncadenada listaNueva=(ListaEncadenada) listaDatos2;// casteamos la lista pasada como objeto a lista ligada
            Nodo temp=frente;
            Nodo tempNuevo=listaNueva.frente;
            int contador=0;
            while (temp!=null && tempNuevo!=null ){
                if (!temp.toString().equalsIgnoreCase(tempNuevo.toString())){// si es diferente al dato a buscar
                    contador++;// aumentamos una posicion mas
                }
                temp=temp.getDirMemDer();// seguimos avanzando
                tempNuevo=tempNuevo.getDirMemDer();// seguimos avanzando

            }
            if (contador==0){// si nunca se fue aumentando la variable entonces todos los datos son iguales
                return true;
            }

        }

        return false;
    }



    /**
     * metodo recursivo que nos permite saber si una lista es igual a otra
     * @param listaDatos2
     * @return true o false
     */

    public boolean esIgualRR(Object listaDatos2){
        if (listaDatos2 instanceof ListaEncadenada){
            ListaEncadenada lista2 = (ListaEncadenada)listaDatos2;
            if (lista2.numElementos() == numElementos()){//verificamos las longitudes de los datos
                inicializarIterador();// iniciamos iterador
                lista2.inicializarIterador();// iniciamos iterador de la segunda lista
                return esIgualRR(lista2);
            }else
                return false;
        }else
            return false;
    }

    /**
     * metodo recursivo que nos indica si la lista pasada como parametro es igual a la origina o no
     * @param lista2
     * @return true o false
     */
    private boolean esIgualRR(ListaEncadenada lista2){
        if (hayMas() && lista2.hayMas()) {
            if (!obtenerSigiuente().toString().equalsIgnoreCase(lista2.obtenerSigiuente().toString())) // son diferentes
                return false;
            return esIgual(lista2);
        }return true;
    }

    /**
     * metodo que nos permite poder hacer mas pequeño la lista actual de elementos
     * @param maximo
     * @return true
     */
    public Object redimensionar(int maximo){
        if(numElementos()>maximo){
            Nodo actual=frente;
            int contador=0;
            while (contador<maximo-1){// generamos un ciclo con el maximo de datos
                actual=actual.getDirMemDer();// seguimos avanzando
                contador++;
            }
            actual.setDirMemDer(null);// agregamos null cuando termine el ciclo
            fin=actual;
            return true;
        }else{
            for(int i=0;i<maximo-numElementos();i++){// generamos la redimencion de los datos
                agregar(null);// agregamos null
            }
            return true;
        }
    }

    /**
     * redimensiona el tamaño del arreglo al nuevo tamaño indicado por máximo.
     * Si el tamaño es menor, los elementos sobrantes deben ser eliminados.
     * @param maximo Nueva capacidad del arreglo.
     */
    public void redimensionarRR(int maximo){
        int cantidadElementos = numElementos();
        if (maximo==0){
            vaciar();
        }else if (maximo>cantidadElementos)
            rellenar("null",maximo-cantidadElementos);
        else if (maximo<cantidadElementos){
            Nodo temp = frente;
            redimensionarRR(1,maximo,temp);
        }
    }
    private void redimensionarRR(int iterador, int maximo, Nodo temp){
        if (iterador==maximo){
            temp.setDirMemDer(null);
            fin = temp;
        }else
            redimensionarRR(iterador+1, maximo, temp.getDirMemDer());
    }




    /**
     * metodo que nos ayuda a poder eliminar un elemento mediante su indice
     * @param indice
     * @return
     */
    public Object eliminar(int indice){
        Object elementoAeliminar=null;
        if (indice>=0){
            for (int i=0;i<numElementos();i++){/// recorremos los elementos de la lista ligada
                if (i==indice){// si es igual el indice y la posicion
                    elementoAeliminar=obtener(indice);// obtenemos ese indice
                    eliminar(elementoAeliminar);// lo eleminamos
                }

            }
            return elementoAeliminar ;
        }
        return null;
    }

    /**
     * metodo que nos permite poder eliminar una posicion de la lista pasando un indice como objeto de forma recursiva
     * @param indice
     * @return elementoAeliminar
     */
    public  Object eliminarRR(int indice){
        return eliminarRR1(indice,0);
    }

    public  Object eliminarRR1(int indice, int contador){
        Object elementoAeliminar=null;
        if (indice>=0){
            if (contador<numElementos()){// verificamos que el contador sea menort
                if (contador==indice){// en caso de que sean iguales
                    elementoAeliminar=obtener(indice);// obtenemos la posicion que esta ahi
                    eliminar(elementoAeliminar);
                    eliminarRR1(indice,contador+1);
                }
            }
            return elementoAeliminar ;
        }
        return null;
    }


    /**
     * metodo que nos ayuuda a identificar cuantos elementos estan agregados en la lista
     * @return contador
     */
    public int numElementos(){
        Nodo recorrer = frente;
        int contador=0;
        while (recorrer!=null){
            contador++;
            recorrer=recorrer.getDirMemDer();
        }
        return contador;
    }



    /**
     * metodo que nos ayuuda a identificar cuantos elementos estan agregados en la lista
     * @return contador
     */
    public int numElementosRR(){
        return numElementosRR(frente,0);
    }

    /**
     * metodo que devuelve la longitud de  datos existentes en la lista
     * @param primero
     * @param contador
     * @return
     */
    private int numElementosRR(Nodo primero, int contador){
        if(primero!=null){
            primero = primero.getDirMemDer();
            contador += 1;
            return numElementosRR(primero,contador);
        }
        return contador;//Caso base ya no hay elementos
    }





    @Override
    public ArregloDatos buscarValores(Object elemento) {
        ArregloDatos arregloDatos = new ArregloDatos(numElementos()+1);
        int contador=0;
        if(!vacia()){
            Nodo actual=frente;
            while(actual!=null){// mientras el dato actual sea diferente de null
                if(actual.getDato().equals(elemento)){// si actual es igual a el elemento
                    arregloDatos.agregar(contador+1);// agregamos el valor
                }
                actual=actual.getDirMemDer();// seguimos avanzando
                contador++;
            }
        }

        return  arregloDatos;
    }

    /**
     * metodo recursivo que nos permite poder obtener la posicion de un elemento que sea igual
     * esta posicion sera almacenada en un arreglo
     * @param info
     * @return valores
     */
    public ArregloDatos buscarValoresRR(Object info){
        ArregloDatos valores = new ArregloDatos(numElementos());
        return buscarValoresRR(info,frente,valores,0);
    }

    private ArregloDatos buscarValoresRR(Object info, Nodo primero, ArregloDatos valores,int contador){
        if(primero!=null){

            if(primero.getDato().equals(info)){//Son iguales
                valores.agregar(contador);// agregamos posicicion
            }
            primero = primero.getDirMemDer();
            return buscarValoresRR(info,primero,valores,contador+1);// iteramos el contador hasta que encuentre un elemento
        }
        valores.imprimir();
        return valores;
    }



    /**
     * metodo que nos ayuda a poder eliminar la primera posicion de la lista
     * @return datoEliminado
     */
    public Object eliminarInicio(){
        Object datoEliminado=null;

        if (vacia()==true){
            return null;
        }else{   //si hay algo
            datoEliminado=frente.getDato();
            if(frente==fin){  // único
                frente=null;
                fin=null;
            }else{ //varios
                frente=frente.getDirMemDer();
            }
            return datoEliminado;
        }
    }



    /**
     * eliminamos la lista agregada dentro de la lista actual
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean eliminarLista(Object listaDatos2) {
        if (listaDatos2 instanceof  ArregloDatos){
            ArregloDatos listaEliminar=(ArregloDatos) listaDatos2;// casteamos los datos de la lista 2 a una lista encadenada
            for (int i=0;i<listaEliminar.cantidadElementos();i++){// sacamos el numero de datos que esta en la lista 2
                Object elemento=listaEliminar.obtener(i);// obtenemos esos datos de la lista
                if (buscar(elemento)!=null){
                    eliminar(elemento);// el dato obtenido lo elimina de la lista actual
                }

            }
            return true;
        }
        return false;
    }

    /**
     * metodo que nos permite poder eliminar una lista agregada en forma recursiva dentro de la lista
     * @param listaDatos2
     * @return
     */
    public boolean eliminarListaRR(Object listaDatos2){
        if (listaDatos2 instanceof ListaEncadenada){
            ListaEncadenada lista2 = (ListaEncadenada)listaDatos2;
            lista2.inicializarIterador();
            return eliminarListaRR(lista2);
        }else
            return false;
    }

    private boolean eliminarListaRR(ListaEncadenada lista){
        if (lista.hayMas()){
            if (eliminar(lista.obtenerSigiuente())!=null){
                eliminarListaRR(lista);
                return true;
            }else
                return eliminarListaRR(lista);
        }else
            return false;
    }

    /**
     * genera un conjunto de datos espesificados dentro de la lista ligada actual
     *
     * @param indiceInicial
     * @param indiceFinal
     * @return ListaSublista
     */
    @Override
    public Object subLista(int indiceInicial, int indiceFinal) {
        ArregloDatos arregloDatos= new ArregloDatos(indiceFinal);// creamos un  arreglo
       if (indiceInicial>=0 && indiceFinal>=indiceInicial ){
           ListaEncadenada listaSubLista= new ListaEncadenada();// geberamos una nueva lista
           for (int i=0;i<numElementos();i++){// sacamos la longitud de los elementos
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
     * METODO QUE NOS PERMITE PODER OBTENER UN CONJUNTO MINIMO DE DATOS QUE ESTAN DENTRO DE LA LISTA LIGADA DE FORMA RECURSIVA
     * @param indiceInicial
     * @param indiceFinal
     * @return subLista
     */
    public Object subListaRR(int indiceInicial, int indiceFinal) {
        int limite = numElementos()-1;
        if(indiceInicial >= 0 && indiceInicial <= limite && indiceFinal <= limite && indiceFinal >= indiceInicial){//Validamos que los indices sean valids
            //Creamos la lista resultante
            ListaEncadenada subLista = new ListaEncadenada();// Creamos una lista
            Nodo iterador= obtenerNodoRR(indiceInicial,0,frente);// mamdamos a el metodo los parametros necesarios
            return subListaRR(indiceInicial,indiceFinal,iterador,subLista);
        }
        return null;
    }

    /**
     * metodo recursivo que obtiene conjunto de elementos
     * @param indiceInicial
     * @param indiceFinal
     * @param iterador
     * @param subLista
     * @return subLista
     */
    private Object subListaRR(int indiceInicial, int indiceFinal, Nodo iterador, ListaEncadenada subLista) {
        if(indiceInicial<=indiceFinal){
            Object elemento = iterador.getDato();// obtenemos el dato de el nodo iterador
            subLista.agregar(elemento);// agregamos el dato
            iterador = iterador.getDirMemDer();// seguimos avanzando en la lista
            return subListaRR(indiceInicial+1, indiceFinal, iterador, subLista);
        }
        subLista.imprimir();
        return subLista;
    }


    /**
     * metodo privado que nos ayuda a obtener nodo por nodo mediante los indices que se mandan como parametro
     * @param indice
     * @param posIni
     * @param primero
     * @return primero o null
     */
    private Nodo obtenerNodoRR(int indice, int posIni, Nodo primero){
        if(indice>=0 && indice<numElementos()){// mientras el indice sea mayor o igual a 0 y menor a los elementos
            if(posIni<indice){// mientras sea menor
                primero = primero.getDirMemDer();
                return obtenerNodoRR(indice,posIni+1,primero);
            }
            return primero;
        }
        return null;
    }

    /**
     * este metodo verifica si la lista agregada es una pequeña lista igual a la lista actual
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean esSublista(ListaDatos lista2) {
        if (lista2 instanceof ListaEncadenada){
            ListaEncadenada listaNueva= (ListaEncadenada) lista2;// casteamos los datos
            int contador=0;
            for (int i=0;i<listaNueva.numElementos();i++){// recorremos la longitud
                if (buscar(listaNueva.obtener(i))!=null){// obtenemos posicion por posicion
                    contador++;
                }
            }
            if (listaNueva.numElementos()==contador){// si el contador es iguala  el numero de elementos de la lista
                return true;// quiere decir que si es una sublista
            }
        }
        return false;
    }

    /**
     * metodo recursivo que nos permite saber si los elementos de otra lista son los mismos que la original
     * @param listaDatos2
     * @return true o false
     */
    public boolean esSubListaRR(ListaDatos listaDatos2){
        if (listaDatos2 instanceof ListaEncadenada){
            ListaEncadenada lista2 = (ListaEncadenada)listaDatos2;// casteamos la lista

            if (lista2.vacia() && vacia()) return true;// si estan las dos vacias regresamos que simon, si son iguales
            else if (!lista2.vacia() && vacia()) return false;
            else if (!lista2.vacia() && !vacia()){
                lista2.inicializarIterador();// inicializamos el iterador
                return (esSubListaRR(lista2)==lista2.numElementos())?true:false;
            }else
                return false;
        }else
            return false;
    }

    /**
     * metodo recursivo que nos indica saber si las dos listas si son iguales o no
     * @param listaEncadenada
     * @return true o false
     */
    private int esSubListaRR(ListaEncadenada listaEncadenada){
        if (listaEncadenada.hayMas()){// en caso de que haya mas
            if (buscar(listaEncadenada.obtenerSigiuente())!=null)// buscamos la siguiente posicion
                return esSubListaRR(listaEncadenada)+1;
            else
                return esSubListaRR(listaEncadenada);
        }else {
            return 0;
        }

    }

    /**
     * este metodo agrega los datos que estan solo en la lista2 a la lista actual
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean retenerLista(ListaDatos lista2) {
        if (lista2 instanceof  ListaEncadenada){
            ListaEncadenada listaNueva= (ListaEncadenada) lista2;
            vaciar();// vaciamos la lista actual
            for (int i=0;i< listaNueva.numElementos();i++){
                agregar(listaNueva.obtener(i));// agregamos los datos nuevos de la lista 2
            }
            return true;
        }
        return false;
    }

    /**
     * este metodo agrega los datos que estan solo en la lista2 a la lista actual pero de forma recursiva
     * @param listaDatos2
     * @return
     */
    public boolean retenerListaRR(ListaDatos listaDatos2){
        if (listaDatos2 instanceof ListaEncadenada){
            ListaEncadenada lista2 = (ListaEncadenada)listaDatos2;// casteamos el dato a lista encadenada
            ListaEncadenada temp = new ListaEncadenada();// creamos una lista temporal

            inicializarIterador();// iniciamos el iterador de la lista orginal
            retenerListaRR(lista2,temp);

            if (!temp.vacia()){
                vaciar();// vaciamos los datos
                temp.inicializarIterador();// iniciamos el iterador de la lista 2
                agregarElementosLista(temp);
                return true;
            }else
                return false;

        }else
            return false;
    }

    /**
     * metodo recursivo que nos permite agregar todos los datos quye estan en la lista dos
     * de forma recursiva y mandando el resultado a la  el metodo de arriba
     * @param lista2
     * @param temp
     */
    private void retenerListaRR(ListaEncadenada lista2, ListaEncadenada temp){
        if (hayMas()){// en caso de haya mas
            Object elemento = obtenerSigiuente();// obtendremos el dato siguiente
            if (lista2.buscar(elemento)!=null) temp.agregar(elemento);// buscamos y agregamos
            retenerListaRR(lista2, temp);
        }
    }

    public void agregarElementosLista(ListaEncadenada lista){

            if (hayMas()==true){
                agregar(lista.obtenerSigiuente());
            }


    }
    /**
     * este metodo nos ayuda a poder colocar un elemento en cualquier indice que exista dentro de la lista
     * @param indice
     * @param elemento
     * @return true o false
     */
    @Override
    public boolean insertar(int indice, Object elemento) {
        int cantidadElementosActuales = numElementos();

        if (indice>=0 && indice<=cantidadElementosActuales){
            if (indice>cantidadElementosActuales){ // agregar en la ultima posicion.
                agregar(elemento);
            }else if (indice==0){  // agregar primera posicion.
                agregarInicio(elemento);
            }else { // agregar en otra posicion.        indice = 2
                Nodo temp = frente;
                for (int posicion=1;posicion<indice;posicion++)
                    temp = temp.getDirMemDer();

                // se obtiene el Nodo de la posicion.
                Nodo aux = temp.getDirMemDer();
                // se agrega el elemento nuevo.
                temp.setDirMemDer( new Nodo(elemento));
                // pasamos al nodo nuevo
                temp = temp.getDirMemDer();
                // agregamos el nodo que contiene los demas nodos
                temp.setDirMemDer(aux);

            }return true;
        }else
            return false;
    }

    /**
     * metodo que nos ayuda a insertar un dato en un indice espesifico de la lista
     * @param indice
     * @param elemento
     * @return
     */
    public boolean insertarRR(int indice, Object elemento){
        int cantidadElementosActuales = numElementos();
        if (indice>=0 && indice<=cantidadElementosActuales){
            if (indice>cantidadElementosActuales){ // agregar en la ultima posicion.
                agregar(elemento);
            }else if (indice==0){  // agregar primera posicion.
                agregarInicio(elemento);
            }else { // agregar en otra posicion.        indice = 2
                insertarRR(1,indice,elemento,frente);
            }return true;
        }else
            return false;
    }

    private void insertarRR(int iterador, int indice,Object elemento,Nodo temp){
        if (iterador<indice){
            insertarRR(iterador+1,indice,elemento,temp.getDirMemDer());
        }else {
            // se obtiene el Nodo de la posicion.
            Nodo aux = temp.getDirMemDer();
            // se agrega el elemento nuevo.
            temp.setDirMemDer( new Nodo(elemento));
            // pasamos al nodo nuevo
            temp = temp.getDirMemDer();
            // agregamos el nodo que contiene los demas nodos
            temp.setDirMemDer(aux);
        }
    }


    /**
     * metodo que nos permite cambiar una lista a otra lista
     * @param listaDatos2
     * @param listaDatos2Nuevos
     * @return true o false
     */
    @Override
    public boolean cambiarLista(ListaDatos listaDatos2, ListaDatos listaDatos2Nuevos) {
        ListaEncadenada lista = (ListaEncadenada) listaDatos2;
        ListaEncadenada lista2 = (ListaEncadenada) listaDatos2;
        if(lista.numElementos() == lista2.numElementos()){// verificamos de que sea de la misma logitud de datos
            for(int pos=1; pos<=lista.numElementos(); pos++){// recorremos los elementos
                cambiar(lista.obtener(pos),lista.obtener(pos),1);// cambiamos las posiciones obtenidas en las listas
            }
            return true;
        }
        return false;
    }

    /**
     * este metodo nos permite poder copiar los datos de una lista nueva a la lista original
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean copiarLista(ListaDatos listaDatos2) {
        if (listaDatos2 instanceof  ListaEncadenada){
            ListaEncadenada listaCopiada=(ListaEncadenada) listaDatos2;// casteamos la lista como lista ligada
            ListaEncadenada listaNueva=new ListaEncadenada();
            vaciar();// vaciamos los datos originales de la lista
            for (int i=0;i<listaCopiada.numElementos();i++){// recorremos los datos de esa lista
                listaCopiada.agregar(listaCopiada.obtener(i));// agregamos los datos nuevos
            }

            listaCopiada.imprimir();
        }
        return false;
    }

    /**
     * metodo que nos permite poder agregar todos los datos de la lista 2 en la lista 1 haciendo una copia de la lista 2
     * @param listaDatos2
     * @return true o false
     */
    public boolean copiarLitsaRR(ListaDatos listaDatos2){
        if (listaDatos2 instanceof ListaEncadenada){
            ListaEncadenada lista2 = (ListaEncadenada)listaDatos2;// casteamos la lista

            if (lista2.numElementos() == numElementos()){// verificamos las longitudes de los datos
                vaciar();// vaciamos los datos originales
                lista2.inicializarIterador();// inicializamos el iterador de la lista pasada como parametro
                agregarElementosLista(lista2);// generamos el metodo recursivo
                return true;
            }else
                return false;
        }else
            return false;
    }

    @Override
    public void rellenar(Object elemento) {

    }

    /**
     * Inicializado iterador, retorna el elemento guardado en la lista y pasa al siguiente.
     * @return regresa el elemento actual, en caso contrario regresa null.
     */
    public Object obtenerSigiuente(){
        if(hayMas()==true){
            Object elemento=iterador.getDato();
            iterador=iterador.getDirMemDer();
            return elemento;
        }else{
            return null;
        }
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
    public Object regresarUltimo(){
        if(vacia()==false){ //que si haya algo
            return fin.getDato();
        }else { //está vacía
            return null;
        }
    }
    public Object regresarPrimero(){
        if(vacia()==false){ //que si haya algo
            return frente.getDato();
        }else { //está vacía
            return null;
        }
    }
    /**
     * Este metodo devuelve un arreglo con los elementos de la lista
     * @return Arreglo
     */
    public ArregloDatos obtenerArreglo(){
        Nodo temp=frente;
        ArregloDatos arreglo= new ArregloDatos(numElementos());
        while (temp!=null){
            arreglo.agregar(temp.getDato());
            temp=temp.getDirMemDer();
        }
        return arreglo;
    }
    public boolean hayElementos(){
        if (iterador==null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Inicializa un iterador para recorrer los elementos de la lista.
     */
    public void inicializarIterador(){
        iterador=frente;
    }

    @Override
    public Object regresarFrente(){
        if(vacia()==false){ //que si haya algo
            return frente.getDato();
        }else{   //no hay nada
            return null;
        }
    }


    @Override
    public Object regresarFin(){
        if(vacia()==false){ //que si haya algo
            return fin.getDato();
        }else{   //no hay nada
            return null;
        }
    }

    public Object obtenerElemento(){
        if(hayElementos()==true){
            Object elementoExtraido=iterador.getDato();
            iterador=iterador.getDirMemDer();
            return elementoExtraido;
        }else{ //no hay elementos
            return null;
        }
    }
}
