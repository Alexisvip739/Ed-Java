package estructuraslineales;


import Herramientas.TipoOrden;
import Herramientas.TipoTabla;
import entradasalida.SalidaTerminal;

import estructuraslineales.registros.Nodo;
import estructurasnolineales.Tabla2D;

/**
 * clase en donde se puede implementar la funcionalidad de una lista ligada de forma ordenada
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class ListaEncadenadaOrden extends ListaEncadenada {
    protected TipoOrden tipoOrden;

    /**
     * constructor que nos ayuda a poder inicializar las variables que vamos a utilizar para el funcionamiento de la clase
     * @param tipo
     */
    public ListaEncadenadaOrden(TipoOrden tipo){
        tipoOrden=tipo;
    }

    /**
     * metodo que verifica si  la lista esta vacia o no
     * @return
     */
    @Override
    public boolean vacia(){
        if (frente==null){
            return true;
        }else{
            return false;
        }
    }

    private void ordenaLista(){
        int cantidadElementosActual = numElementos();// obtenemos la cantidad de elementos existentes en la lista
        if (cantidadElementosActual>1){ // tiene mas de un elemento guardado

            while (true) {
                // contador que incrementara si todos los elementos estan en su lugar correspondiente
                int cont=1;
                for (int i= 0; i<cantidadElementosActual-1 ; i++){
                    Object elemento1 = obtener(i);// obtenemos cada una de las posiciones
                    Object elemento2 = obtener(i+1);// obtenemos una posicion adelante de la posicion actual
                    // obtenemos la menor longitud de caracteres de las 2 productos .
                    int cantCaracteres = (elemento1.toString().length()<elemento2.toString().length())? elemento1.toString().length():elemento2.toString().length();
                    for (int j=0;j<cantCaracteres;j++) {
                        // en codigo ASCII
                        // si el caracter del producto1 es mayor al caracter del producto2, cambian de posicion
                        if (elemento1.toString().charAt(j) > elemento2.toString().charAt(j)) {
                            super.cambiar(i + 1, elemento1);
                            super.cambiar(i, elemento2);
                            break;
                        }// si el caracter del producto1 es menor al caracter del producto2, salimos del ciclo
                        else if (elemento1.toString().charAt(j) < elemento2.toString().charAt(j)) {
                            cont++;
                            break;
                        } else // los caracteres son iguales
                            cont++;

                    }
                }
                // todos los elementos estan en su lugar, salimos del ciclo while
                if (cont>=cantidadElementosActual) break;
            }
        }
    }

    /**
     * Agrega elementos a la lista ordenada.
     * @param elemento Es el dato que se desea agregar a la lista.
     * @return regresa 1 si se agrego a la lista, en caso contrario regresa -1.
     */
    public int agregar(Object elemento) {
        if (tipoOrden.equals(TipoOrden.Ordenado)){ // Si es de forma ordenada
            if (super.agregar(elemento)==1){ // se agrego correctamente
                ordenaLista();// ordenamos la lista
                return 1;
            }else return -1;
        }else if(tipoOrden.equals(TipoOrden.Desordenado)){ // DESORDENADO
            return super.agregar(elemento);// lo agregamos a una lista ligada comun
        }
        return -1;
    }
    /**
     * Este metodo busca en la lista ligada un elemento que le pasemos como parametro
     * @param info elemento a buscar en la lista
     * @return el elemento encontrado
     */
    @Override
    public Object buscar(Object info){
        Nodo recorrer=frente;
        while(recorrer!=null && !recorrer.getDato().toString().equalsIgnoreCase(info.toString())){// recorremos posicion por posicion siempre y cuando aun no se encuetre el dato
            recorrer=recorrer.getDirMemDer();
        }
        if(recorrer==null){
            return null;
        }else{
            return recorrer.getDato();// dato encontrado
        }
    }

    /**
     * Este metodo eliminar un elemento de la lista
     * @param info elemento a borrar
     * @return elemento eliminado
     */
    @Override
    public Object eliminar(Object info){
        Object elementoBorrado=null;// variable que obtendrea el elemento eliminado
        if(vacia()==false){
            Nodo anterior=frente;
            Nodo encontrado=frente;
            while(encontrado!=null && !encontrado.getDato().toString().equalsIgnoreCase(info.toString())){// mientas aun no se encuentre el dato a eliminar
                anterior=encontrado;
                encontrado=encontrado.getDirMemDer();// recorremos
            }
            if(encontrado==null){
                return null;
            }else{// ya se encontro el dato  a eliminar
                elementoBorrado=encontrado.getDato();// asignamos el dato a la variable
                if(frente==fin){// en caso de que solamente exista un elemento en la lista
                    frente=null;// eliminamos posiciones
                    fin=null;
                }else if(fin==encontrado){// si en la primera posicoin se encontro
                    frente=frente.getDirMemDer();// recorremos la posicion
                }else if(fin==encontrado){// si se encontro al ultimo
                    anterior.setDirMemDer(null);// el dato anterior es null
                    fin=anterior;
                }else{ //cualquier otro caso, es decir, en medio
                    Nodo siguiente=encontrado.getDirMemDer();
                    anterior.setDirMemDer(siguiente);
                }
                return elementoBorrado;
            }
        }else{
            return null;
        }
    }

    /**
     * Este metodo elimina la ultima posicion de la lista ligada
     * @return elemento borrado
     */
    @Override
    public Object eliminar(){
        Object elementoBorrado=null;// asignamos una variable que almacenara el elemento a eliminar
        if(vacia()==false){
            elementoBorrado=fin.getDato();
            if(frente==fin){// si solamente hay un elemento
                //Se elimanan las posiciones
                frente=null;
                fin=null;
            }else{// en caso de que hayan mas datos
                Nodo penultimo=frente;
                while(penultimo.getDirMemDer()!=fin){// mientras el penultimo dato sea diferente de el ultimo
                    penultimo=penultimo.getDirMemDer();// recorremos posicoines
                }
                fin=penultimo;// ya que se ebcobtro entonces recorremos una posicion atras para dejar el nuevo elemento en la lista como ultimo
                fin.setDirMemDer(null);
            }
            return elementoBorrado;
        }else {
            return null;
        }
    }

    /**
     * este metodo nos permite ver la ultima posicion de la lista ligada
     * @return ultimo
     */
    @Override
    public Object verTope() {
        return fin;
    }

    /**
     * Este metodo devuelve el valor del arreglo de la posicion que le pasemos
     * @param info posicion del elemento que queremos buscar
     * @return el elemento con el id correspondiente
     */
    public Object buscarPosicion(Object info){
        if(tipoOrden.equals(TipoOrden.Ordenado)){// si se quiere buscar el valor de forma ordenada
            if (!info.equals(null)){// si la info es igual a null
                int pos=0;
                int cantidad=numElementos();// obtenemos el numero de elementos en la lista
                while (pos<=cantidad&&compareTo(info)>compareTo(obtener(pos))){// comparamos con codigo ascii cada una de la obtencion de la lista
                    pos++;
                }
                if(pos>cantidad|| compareTo(info)<compareTo(obtener(pos))){// en caso de que la info por el usuario sea menor que el nodo
                    return (pos+1)*(-1);// regresamos la operacion
                }else{
                    return pos+1;// sumamos una posicion mas
                }
            }else{
                return null;
            }
        }else {
            return null;
        }

    }
    /**
     * Este metodo te devuelve el valor de codigo ascii del objeto que se pasemos
     * @param info objeto que queremos saber el codigo ascii
     * @return el valor de codigo ascii
     */
    public int compareTo(Object info){
        return info.toString().compareTo("0");
    }

    /**
     * metodo que nos permite poder agregar un elemento pasado como paramentro en la primera posicoin pero ordenado
     * @param info
     * @return
     */
    public int agregarInicio(Object info){
        Nodo nuevoNodo=new Nodo(info);
        if(nuevoNodo!=null) { //si es diferente a null
            if (tipoOrden.equals(TipoOrden.Ordenado)) {// agregamos el dato en forma ordenada
                if (vacia() == true) { //cuando no hay nada
                    frente = nuevoNodo;
                    fin = nuevoNodo;
                }else {
                    nuevoNodo.setDirMemDer(frente);// el primer dato agregado seria ya la liga derecha
                    this.agregar(nuevoNodo);// agregamos ese dato en la lista para que se ordene
                }
                return 1;
            }else {
                return super.agregarInicio(info);
            }
        }
        return -1;
    }

    /**
     * metodo que nos ayuda a mostrar los datos agregados en la pantalla
     */
    @Override
    public void imprimir(){
        Nodo temp=frente;

        while (temp!=null){
            SalidaTerminal.consola(temp.getDato()+" -> ");
            temp=temp.getDirMemDer();
        }
        SalidaTerminal.consola("null"+"\n");
    }


    /**
     * metodo que imprime los datos de forma invertida
     */
    @Override
    public void imprimirOrdenInverso() {
        SalidaTerminal.consola( null+" <- "+fin );
        Nodo temp=fin;
        while(true){
            Nodo anterior = frente;
            if (temp==frente){
                break;
            }else{
                while(anterior.getDirMemDer()!=temp){// recorremos con un ciclo
                    anterior=anterior.getDirMemDer();// obtenemos la liga derecha
                }
                temp = anterior;
                SalidaTerminal.consola( " <- "+anterior );// obtenemos los datos anterioriores
            }
        }
    }


    /**
     * metodo que nos ayuda a  eliminar la primera posicion dentro de la lista ordendada
     * @return
     */
    public Object eliminarInicio(){
        Object elementoBorrado=null;
        if(vacia()==false){
            elementoBorrado=frente.getDato();// obtenemos el primer dato
            if(frente==fin){// si solo hay un elemento en la lista
                frente=null;
                fin=null;
            }else{
                frente=frente.getDirMemDer();//recorremos una poscion
            }
            return elementoBorrado;
        }else{
            return null;
        }
    }

    /**
     * Este metodo devuelve la cantidad de elementos en la lista ligada
     * @return numero de elementos
     */
    public int numElementos(){
        Nodo temp=frente;
        int cont=0;
        while (temp!=null){
            cont++;
            temp=temp.getDirMemDer();
        }
        return cont;
    }



    /**
     * Este metodo devuelve un arreglo con los elementos de la lista ligada
     * @return el arreglo con los datos
     */
    public ArregloDatos aArreglo(){
        ArregloDatos arreglo= new ArregloDatos(numElementos());// asignamos el tamaño
        Nodo temp=frente;
        while (temp!=null){
            arreglo.agregar(temp.getDato());// agregamos los datos a la lista de el arreglo
            temp=temp.getDirMemDer();// seguimos avanzando
        }
        return arreglo; // regresamos el arreglo
    }

    /**
     * Este metodo devuelve un arreglo con los datos que NO coinciden con los del arreglo que le pasamos
     * como parametro
     * @param arregloADescartar arreglo con los datos a comparar
     * @return un arreglo con los datos que NO son iguales
     */
    public ArregloDatos arreglo(ArregloDatos arregloADescartar){
        ArregloDatos arreglo= new ArregloDatos(numElementos());
        for(int a=0;a<arregloADescartar.cantidadElementos();a++){
            Object elemento=arregloADescartar.obtener(a);
            if(buscar(elemento)==null){// si el dato no esta dentro de la lista
                arreglo.agregar(elemento);// lo dejamos en el arreglo
            }
        }
        return arreglo;
    }

    /**
     * Este metodo introduce los datos de la lista en una matriz
     * @param renglones numero de renglones de la matriz
     * @param columnas numero de columnas
     * @return la matriz con los datos
     */
    public Tabla2D aMatriz2D(int renglones, int columnas){
        Tabla2D matriz = new Tabla2D(renglones,columnas);// asignamos las posiciones de la matriz
        ArregloDatos arreglo=aArreglo();
        int cont=0;
        for(int a=0;a<renglones;a++){
            for(int b=0;b<columnas;b++){
                if(cont>=arreglo.cantidadElementos()){
                    matriz.asignarCelda(a,b,null);// agregamos lso datos con un null
                }else{
                    matriz.asignarCelda(a,b,arreglo.obtener(cont));// agregamos los datos obteniendo los datos en ella
                }
                cont++;
            }
        }
        return matriz;
    }


    /**
     * metodo que nos permite agregar un arreglo a la lista de datos
     * @param listaDatos2
     * @return
     */
    @Override
    public boolean agregarLista(Object listaDatos2) {
        if(listaDatos2 instanceof  ArregloDatos){// verificamos de que sea un arreglo
            ArregloDatos arreglo=(ArregloDatos) listaDatos2;// lo casteamos a un arreglo
            for(int a=0;a<arreglo.cantidadElementos();a++){// recorremos el numero de elementos
                agregar(arreglo.obtener(a));// obtenemos sus posiciones junto con sus datos y despues las agregamos a la lista de datos de forma ordenada
            }return true;
        }
        return true;
    }





    /**
     * Este metodo vacia la lista ligada
     */
    public void vaciar(){
        frente=null;
        fin=null;
    }

    /**
     * Este metodo rellena la lista con el dato que le pasemos  las veces que nosotros le indiquemos
     * por parametro
     * @param elemento elemento a agregar
     * @param cantidad cantidad de veces que se va a agregar
     */
    public void rellenar(Object elemento, int cantidad){
        if (cantidad>0){
            for (int i=1;i<=cantidad;i++){// mientras sea el iterador menor que la cantidad o igual
                agregar(elemento);// seguira agregando en la lista
            }
        }
    }

    /**
     * metodo que nos permite obtener la lista actual clonada sin modificar los datos internenos de ella misma
     * @return listaClonada
     */
    @Override
    public Object clonar() {
        if (frente!=null){
            ListaEncadenadaOrden listaClonada= new ListaEncadenadaOrden(tipoOrden);// creamos una lista nueva
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
     * metodo que nos permite tener un pequeño conjunto de la lista actual
     * @param indiceInicial
     * @param indiceFinal
     * @return listaSUblista
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
     * metodo que nos dice si la lista pasada como parametro es sub lista de la lista actual
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean esSublista(ListaDatos lista2) {
        if (lista2 instanceof ListaEncadenadaOrden){
            ListaEncadenadaOrden listaNueva= (ListaEncadenadaOrden) lista2;// casteamos los datos
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
     * este metodo nos permite poder agregar todas los datos de la lista nueca en la lista actual
     * @param lista2
     * @return
     */
    @Override
    public boolean retenerLista(ListaDatos lista2) {
        if (lista2 instanceof  ListaEncadenadaOrden){
            ListaEncadenadaOrden listaNueva= (ListaEncadenadaOrden) lista2;
            vaciar();// vaciamos la lista actual
            for (int i=0;i< listaNueva.numElementos();i++){
                agregar(listaNueva.obtener(i));// agregamos los datos nuevos de la lista 2
            }
            return true;
        }
        return false;
    }


    /**
     * metodo que nos ayuda a poder asignarle un nuevo elemento en sierta posicion de la lista
     * @param indice
     * @param elemento
     * @return true || false
     */
    @Override
    public boolean insertar(int indice, Object elemento) {
        int index=0;
        Nodo recorrer=frente;
        if (indice>=0){
            while (recorrer!=null){
                if (index==indice){// si el index es igual a el indice
                    recorrer.setDato(elemento);// establecemos el elemento en el nodo recorrer

                }

                recorrer=recorrer.getDirMemDer();// seguimos avanzand
                index++;

            }
            return  true;
        }
        return false;
    }


    /**
     * metodo que nos permite cambiar una lista a otra lista
     * @param listaDatos2
     * @param listaDatos2Nuevos
     * @return true o false
     */
    @Override
    public boolean cambiarLista(ListaDatos listaDatos2, ListaDatos listaDatos2Nuevos) {
        ListaEncadenadaOrden lista2 = (ListaEncadenadaOrden)listaDatos2;
        ListaEncadenadaOrden lista2Nuevos = (ListaEncadenadaOrden)listaDatos2Nuevos;
        // verifica si tienen la misma cantidad de elementos guardados.
        if (!lista2.vacia() && lista2.numElementos() == lista2Nuevos.numElementos()){
            lista2.inicializarIterador();
            lista2Nuevos.inicializarIterador();

            boolean bandera=false; // cambiara de valor si hizo algun cambio en la lista.
            while (lista2.hayMas() && lista2Nuevos.hayMas()){
                Object elemento = lista2.obtenerSigiuente();
                Object elementoNuevo = lista2Nuevos.obtenerSigiuente();
                // contamos si es que esta el elemento en la lista.
                int contarElementosLista = contar(elemento);
                if (contarElementosLista>0){ // tiene al menos un elemento.
                    cambiar(elemento,elementoNuevo,contarElementosLista);
                    bandera=true;
                }
            }
            return bandera;
        }else
            return false;
    }

    /**
     * este metodo nos permite poder copiar los datos de una lista nueva a la lista original
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean copiarLista(ListaDatos listaDatos2) {
        if (listaDatos2 instanceof  ListaEncadenadaOrden){
            ListaEncadenadaOrden listaCopiada=(ListaEncadenadaOrden) listaDatos2;// casteamos la lista como lista ligada
            if (tipoOrden.equals(TipoOrden.Ordenado)){// en caso de que se agregue de forma ordenada
                ListaEncadenadaOrden listaNueva=new ListaEncadenadaOrden(TipoOrden.Ordenado);
                vaciar();// vaciamos los datos originales de la lista
                for (int i=0;i<listaCopiada.numElementos();i++){// recorremos los datos de esa lista
                    listaCopiada.agregar(listaCopiada.obtener(i));// agregamos los datos nuevos
                }

                listaCopiada.imprimir();
            }else {// en caso de que se agregue de forma descendente
                super.copiarLista(listaDatos2);
            }

        }
        return false;
    }


    @Override
    public void rellenar(Object elemento) {

    }


    /**
     * Este metodo devuelve el numero de veces que aparece un elemento que le pasamos como parametro
     * @param elemento elemento a comparar por la lista
     * @return numero de veces encontrado en la lista
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
     * metodo que nos permite poder eliminar la lista de arreglos agregada dentro de la lista actual
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
     * Este metodo cambiar el orden  los datos de la lista ligada
     */
    public void invertir(){
        if (tipoOrden.equals(TipoOrden.Ordenado)) { // ORDENADO A DESORDENADO
            tipoOrden = TipoOrden.Desordenado;
            super.invertir();
        }else {
            tipoOrden = TipoOrden.Ordenado;
            ordenaLista();
        }
    }

    /**
     * Este metodo cambia la informacion vieja de lista por una nueva informaion que es pasada como parametro
     * @param elementoViejo informacion vieja
     * @param elementoNuevo informacion nueva
     * @param numVeces numero de ocurrencias
     * @return true si se cambio y false si no se cambio
     */
    @Override
    public boolean cambiar(Object elementoViejo, Object elementoNuevo, int numVeces) {
        if (tipoOrden.equals(TipoOrden.Ordenado)){ //ORDENADO
            boolean bandera = super.cambiar(elementoViejo, elementoNuevo, numVeces);
            ordenaLista();
            return bandera;
        }else
            return super.cambiar(elementoViejo, elementoNuevo, numVeces);
    }

    /**
     * metodo que nos ayuda a buscar un elemento desde un arreglo de datos
     * @param elemento
     * @return arregloDatos
     */
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
     * Este metodo nos ayuda a cambiar un elemento de lista por su indice
     * @param indice indice delelemento a cambiar
     * @param info informaicon nueva a cambiar
     * @return true si se cambio y false si no se cambio
     */
    public boolean cambiar(int indice, Object info){
        if (tipoOrden.equals(TipoOrden.Ordenado)){ // ORDENADO
            boolean bandera = super.cambiar(indice, info);
            ordenaLista();
            return bandera;
        }else // DESORDENADO
            return super.cambiar(indice, info);
    }

    /**
     * Este metodo obtiene un elemento del indice que le indiquemos
     * @param indice indice del elemento a obtener
     * @return el elemento o null si no se encontro
     */
    @Override
    public Object obtener(int indice) {
        Nodo temp=frente;
        int posicion = 0;
        if(indice<numElementos()&& indice>=0){// si esta en el rango adecuado
            while (temp!= null) {
                if (posicion == indice) {// si posicion e indice son iguales
                    return temp.getDato();//regresamos el dato que se encuentra ahi
                }
                temp=temp.getDirMemDer();// seguimos avanzando
                posicion++;// asignamos una posicion mas
            }
        }return null;
    }

    /**
     * Este metodo busca un elemento en la lista y devuelve su posicion
     * @param info1 elemento a buscar
     * @return la posicion
     */
    public int buscarElemento(Object info1){
        int cont=0;
        Nodo temp=frente;
        while (temp!=null){
            if(info1.equals(temp.getDato())){
                return cont;
            }
            cont++;
            temp=temp.getDirMemDer();
        }
        return -1;
    }

    /***
     * Este metodo nos ayuda a compara una lista que pasamos por parametro con la lista actual
     * para saber si es identica
     * @param lista2 lista con los elementos a comparar
     * @return true si es identica y false si no lo es
     */
    public boolean esIgual(Object lista2){
        ListaEncadenadaOrden lista3=(ListaEncadenadaOrden) lista2;
        // los convertimos en arreglos
        ArregloDatos lista1=aArreglo();
        ArregloDatos listeDos=lista3.aArreglo();
        Boolean bandera=true;
        if(lista1.cantidadElementos()==listeDos.cantidadElementos()){
            for(int a=0;a<lista1.cantidadElementos();a++){
                if(lista1.obtener(a).equals(listeDos.obtener(a))){

                }else{
                    bandera=false;
                    break;
                }
            }

        }else{
            return false;
        }
        return bandera;
    }

    /**
     * Este metodo redimensiona la lista con el maximo que le pasemos como parametro
     * si es mayor los espacios se llenan con null y si es menor se borran los elementos sobrantes
     * @param maximo cantidad maxima de elementos
     * @return true si se redimensiono
     */
    public Object redimensionar(int maximo){
        int numElementos=numElementos();
        if(maximo>numElementos){
            int cont=maximo-numElementos;
            for(int a=0;a<cont;a++){
                agregar(null);
            }
            return true;
        }else if(maximo<numElementos){
            int cont=numElementos-maximo;
            for(int a=0;a<cont;a++){
                eliminar();
            }
            return true;
        }
        return false;
    }

    /**
     * Este metodo eliminar un elemento del indice que le pasemos
     * @param indice posicion del elemento
     * @return elemento borrado
     */
    public Object eliminar(int indice){
        return eliminar(obtener(indice));
    }


    /**
     * este metopdo nos permite poder agregar todos los datos de una tabla dentro de una lista ligada ordenada, ordenando los datos de la tabla con los de la lsita
     * @param tabla
     * @return true
     */
    public  boolean agregarTabla2D(Tabla2D tabla) {// en caso de que sean solo columnas
            for (int col = 0; col < tabla.getFilas(); col++) {// recorremos las columnas
                for (int ren = 0; ren < tabla.getColumnas(); ren++) {
                    agregar(tabla.obtenerInfo(ren, col));// agregamos las columnas a la lista
                }
            }
            return true;

    }

    /**
     * metodo que elimina los datos que estan dentro de la lista ligada que pertenecen a una tabla 2d
     * @param tabla
     * @return true o false
     */

    public  boolean EliminarTabla2D(Tabla2D tabla) {// en caso de que sean solo columnas
       if (tabla instanceof  Tabla2D){
           for (int ren = 0; ren < tabla.getFilas(); ren++) {// recorremos las columnas
               for (int col = 0; col < tabla.getColumnas(); col++) {
                   Object elemento= tabla.obtenerInfo(ren,col);
                   if (buscar(elemento)!=null){
                       eliminar(elemento);
                   }
               }
           }
           return true;
       }
        return false;

    }


    /**
     * metodo que nos permite agregar filas o columans a la lista ligada
     * @param tabla
     * @param enumTipoTabla
     * @return
     */
    public  boolean agregarTabla2D(Tabla2D tabla, TipoTabla enumTipoTabla){
        if(enumTipoTabla.equals(TipoTabla.FILA)){// en caso de que sean filas
            for(int a=0;a<tabla.getFilas();a++){
                for(int b=0;b<tabla.getColumnas();b++){
                    agregar(tabla.obtenerInfo(a,b));// agregamos las filas de la tabla
                }
            }
        }else{// en caso de que sean columnas
            for(int a=0;a<tabla.getColumnas();a++){
                for(int b=0;b<tabla.getFilas();b++){
                    agregar(tabla.obtenerInfo(b,a));// agregamos las columas de la tabla
                }
            }

            return true;
        }
        return false;


    }
    @Override
    public boolean hayMas() {
        return super.hayMas();
    }




}
