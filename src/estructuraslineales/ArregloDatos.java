package estructuraslineales;

import Herramientas.NumTipo;
import entradasalida.SalidaTerminal;
import estructuraslineales.registros.Nodo;

/**
 * @autor Alexis Ultreras Sotelo
 * @version 4.0
 * clase que agrega toda la implementacion de VectorDatos junto con los metodos recursivos que se deben de implementar en ellos
 */
public class ArregloDatos implements VectorDatos {
    protected int CAPACIDAD;
    protected int tope;
    protected Object lote[];

    public ArregloDatos(int capacidad) {
        CAPACIDAD = capacidad;
        lote = new Object[CAPACIDAD];
        tope = -1;
    }

    @Override
    public boolean vacia() {
        if (tope == -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean lleno() {
        if (tope == (CAPACIDAD - 1)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int agregar(Object elemento) {
        if (lleno() == false) {  //esta llena la lista
            tope = tope + 1;
            lote[tope] = elemento;
            return tope;
        } else {   //no esta llena la lista
            return -1;
        }
    }

    public void imprimir() {
        for (int posicion = 0; posicion <= tope; posicion++) {
            SalidaTerminal.consola(lote[posicion] + "\n");
        }
    }

    /**
     * metodo que imprime el resutlado de la salida de  los lotes
     */
    public void imprimirRR(){
        imprimirRR1(0);
    }
    /**
     * metodo recursivo que permite poder mostrar en pantalla los datos asignados en un arreglo
     * @param posicion
     */
    private void imprimirRR1(int posicion){
        if (posicion<=tope){
            SalidaTerminal.consola(lote[posicion]+"\n");
            imprimirRR1(posicion+1);
        }

    }

    public void imprimirOrdenInverso() {
        for (int posicion = tope; posicion >= 0; posicion--) {
            SalidaTerminal.consola(lote[posicion] + "\n");
        }
    }


    /**
     * metodo que snos ayuda a mostrar el resultado recursivo de los elementos en orden inverso
     */
    public void imprimirOrdenInversoRR() {
        int posicion = tope;
        imprimirOrdenInversoRR1(posicion);
    }

    private void imprimirOrdenInversoRR1(int posicion) {
        if(posicion>=0){
            SalidaTerminal.consola(lote[posicion]+ "\n");
            imprimirOrdenInversoRR1(posicion-1);
        }
    }
    @Override
    public Object verTope(){
        if (vacia()==false){
            return lote[tope];
        }else{
            return null;
        }
    }


    public Object buscar(Object elemento) {
        int posicion = 0;
        while (posicion <= tope && !elemento.toString().equalsIgnoreCase(lote[posicion].toString())) {
            posicion++;
        }
        if (posicion > tope) { //significa que no lo encontro
            return null;
        } else {
            return posicion;
        }
    }

    /**
     * metod que nos ayuda a llamar a el metodo recursivo
     * @param info
     * @return fin
     */
    public Object buscarRR(Object info) {
        return this.buscarRR1(info, this.cantidadElementos()-1);
    }

    /**
     * metodo recursivo que nos permite poder obtener la posicion en donde se encuentra un elemento dentro de una lista de arreglo
     * @param info
     * @param fin
     * @return fin
     */
    private Object buscarRR1(Object info, int fin) {
        if (fin == -1) {
            return -1;
        } else {
            return info.equals(this.lote[fin]) ? fin : this.buscarRR1(info, fin - 1);
        }
    }


    /**
     * metodo de busqueda para encontrar un dato en espesifico
     *
     * @param elemento
     * @return elementoBorrado
     */
    public Object eliminar(Object elemento) {
        Integer posicion = (Integer) buscar(elemento);
        if (posicion != null) { //si esta
            Object elementoBorrado = lote[posicion]; // se asigna el dato a borrar
            tope = tope - 1;// recorre una posiciono menos
            for (int i = posicion; i <= tope; i++) {
                lote[i] = lote[i + 1];// agrega el dato de una posicion mas
            }
            return elementoBorrado;
        } else { //no esta
            return null;
        }
    }


    /**
     * metodo que nos ayuda a poder mostrar el resultado recursivo de los elementos eliminados mediante la informacion
     * @param info
     * @return eliminarDato
     */
    public Object eliminarRR(Object info){
        return eliminarRR1(info,cantidadElementos()-1);
    }
    /**
     * elimina el valor de el arreglo y despues lo manda  a eliminarRR1
     * @param info
     * @param lim
     * @return eliminar
     */
    private   Object eliminarRR1(Object info, int lim){
        if(lim==-1){
            return null;
        }else if(info.equals(lote[lim])){
            tope=tope-1;
            Object eliminarDato= lote[lim];
            for (int i=lim;i<=tope;i++){// recorremos la lista
                lote[i]=lote[i+1];// asignamos una posicion adelante

            }
            return  eliminarDato;
        }else {
            return eliminarRR1(info,lim-1);
        }
    }

    /**
     * verifica si es igual un Objeto de tipo Arreglo con el original
     *
     * @param listaDatos2
     * @return false o true
     */
    @Override
    public boolean esIgual(Object listaDatos2) {
        if (listaDatos2 instanceof ArregloDatos) { // verifica que si la lista es un Objeto de tipo  arreglo
            boolean igual = true;
            for (int posicion = 0; posicion <= tope; posicion++) {
                if (!lote[posicion].toString().equals(((ArregloDatos) listaDatos2).obtener(posicion).toString())) {
                    igual = false;
                }
            }
            return igual;
        } else {
            return false;
        }
    }

    /**
     * Metodo que nos ayuda a poder invocar a el metodo recursivo de es igual
     * @param listaDatos
     * @return true o false
     */
    public  boolean esIgualRR(Object listaDatos){
        return esIgualRR1(listaDatos,cantidadElementos() -1);
    }

    /**
     * metodo recursivo de es igual que nos permite poder verificar si una lista de datos es igual a la original
     * @param listaDatos2
     * @param topeDato
     * @return true o false
     */
    private   boolean esIgualRR1(Object listaDatos2,int topeDato){
        if (listaDatos2 instanceof ArregloDatos) { // verifica que si la lista es un Objeto de tipo  arreglo
            boolean igual = true;
           if (topeDato<=CAPACIDAD && topeDato>=0){// verificamos que el dato que sera recursivo no sobre paso los limites
               if (!lote[topeDato].toString().equalsIgnoreCase(((ArregloDatos)listaDatos2).obtener(topeDato).toString())){// verificamos que sea distinto los datos
                   igual=false;
               }
               esIgualRR1(listaDatos2,topeDato-1);// hacemos el metodo recursimo siempre y cuando no se encuentre el dato que no sea igual
           }
           return igual;// regresamos ya true cuando nos demos cuenta que los datos son todos iguales
        }
        return false;
    }

    /**
     * obtiene un dato por medio de su indice
     *
     * @param indice
     * @return lote[indice] ( indice encontrado)
     */
    @Override
    public Object obtener(int indice){
        if(enLimites(indice)){
            return lote[indice];
        }else{
            return null;
        }
    }


    /**
     * sustituye un elemento viejo por uno nuemo con los numeros de veces que se hizo
     *
     * @param elementoViejo
     * @param elementoNuevo
     * @param numVeces
     * @return true o false
     */
    @Override
    public boolean cambiar(Object elementoViejo, Object elementoNuevo, int numVeces) {
        int count = 0;
        if (numVeces <= tope && numVeces > 0) {
            for (int vueltas = 0; vueltas <= numVeces; vueltas++) {
                Integer posicion = (Integer) buscar(elementoViejo);
                if (posicion != null) { // si esta en la lista
                    lote[posicion] = elementoNuevo;
                    count = count + 1;
                } else break;
            }
            // Devuelve true si count es diferente a cero, sino regres false
            return (count != 0) ? true : false;
        } else {
            return false;
        }
    }

    public boolean cambiarRR(Object elementoViejo, Object elementoNuevo, int numVeces, int numCambios) {
        Integer posicion=(Integer)buscar(elementoViejo);
        if(numCambios < numVeces && posicion != null ){
            lote[posicion]= elementoNuevo;
            return cambiarRR(elementoViejo,elementoNuevo,numVeces,numCambios+1);
        }else{
            return true;
        }
    }



    /**
     * busca en el arreglo el elemento y se agrega la posicion en donde se encontro dentro de un nuevo arreglo
     *
     * @param elemento
     * @return ocurrencias
     */
    @Override
    public ArregloDatos buscarValores(Object elemento) {
        ArregloDatos ocurrencias = new ArregloDatos(contar(elemento)); // la longitud de los datos sera los elementos agregados a el mismo arreglo
        for (int i = 0; i < cantidadElementos(); i++) {
            Object DatoPosicion = obtener(i);// obtiene la posicion de el dato a buscar
            if (DatoPosicion.toString().equalsIgnoreCase(elemento.toString())) {
                ocurrencias.agregar(i);// agrega la posicion de el dato encontrado al nuevo arreglo
            }

        }
        ocurrencias.imprimir();

        return null;
    }

    public ArregloDatos buscarValoresRR(Object elemento) {
        return buscarValoresRR1(new ArregloDatos(tope+1),elemento,0);
    }
    private ArregloDatos buscarValoresRR1(ArregloDatos indices, Object info, int posicion) {
        if(posicion <= tope){
            if (info.equals(lote[posicion])){
                indices.agregar(posicion);}
            return buscarValoresRR1(indices,info,posicion+1);
        }
        return indices;
    }


    /**
     * elimina la ultima posicion de el arreglo
     *
     * @return info Borrada
     */
    @Override
    public Object eliminar() {
        if (vacia() == false) {
            Object infoBorrada = lote[tope];//saca el ultimo dato de el arreglo
            tope = tope - 1;
            return infoBorrada;
        } else return null;
    }

    /**
     * establece una nueva lista de datos dentro de el mismo arreglo que se usa
     *
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean agregarLista(Object listaDatos2) {
        if (listaDatos2 instanceof ArregloDatos) {
            ArregloDatos arreglo2 = (ArregloDatos) listaDatos2;
            for (int posicion = 0; posicion < arreglo2.cantidadElementos(); posicion++) {
                if (!lleno()) {
                    agregar(arreglo2.obtener(posicion)); // agrega las posiciones de los datos de el segundo arreglo a el arreglo original
                } else break;
            }
            return true;
        }
        return false;
    }

    /**
     * Método para agregar una lista en otra pero de forma recursiva
     * @param listaDatos2 arreglo a agregar.
     * @return booleano dependiendo si se pudo o no agregar.
     */
    public boolean agregarListaRR(Object listaDatos2){
        //Verifica si es un ArregloDato
        if (listaDatos2 instanceof ArregloDatos){
            ArregloDatos arreglo2 =(ArregloDatos) listaDatos2;
            agregarElementosArreglo(0,arreglo2);
            return true;
        }return false;
    }



    /**
     * voltea los datos de el arreglo a como estaban originalmente
     */
    @Override
    public void invertir() {
        ArregloDatos datosInvertidos= new ArregloDatos(tope+1);
        for(int b=tope;b>=0;b--){
            datosInvertidos.agregar(lote[b]);
        }
        for(int a=0;a<=tope;a++){
            lote[a]=datosInvertidos.lote[a];
        }
    }



    public void invertirRR() {
        invertirRR(0,tope);
    }

    private void invertirRR(int posIni, int posFin){
        if(posIni<Math.floor(tope+1/2)){
            Object elmentoFinal = lote[posFin];
            lote[posFin] = lote[posIni];
            lote[posIni] = elmentoFinal;
            invertirRR(posIni+1, posFin-1);
        }
    }



    /**
     * limpia el arreglo donde se almacenan
     * los datos
     */
    @Override
    public void vaciar() {
        tope = -1;

    }

    /**
     * cuenta cuantas veces se repite un elemento en el arreglo
     *
     * @param elemento
     * @return contar
     */
    @Override
    public int contar(Object elemento) {
        int contar = 0;
        for (int i = 0; i <= tope; i++) {
            if (elemento == lote[i]) {
                contar++;
            }
        }
        return contar;
    }

    public int contarRR(Object elemento){
        return contarRR(elemento,0,0);
    }
    /**
     * Método para contar cuantos elementos hay en el arreglo según la información
     * @param info elemento a contar
     * @return un entero de las veces que se encuentra el elemento.
     */
    public int contarRR(Object info,int posicion, int contador) {
        if(posicion <=tope){
            if(lote[posicion].equals(info)){
                contador +=1;
            }
            return contarRR(info,posicion+1,contador);
        }else{
            return contador;
        }
    }



    /**
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean eliminarLista(Object listaDatos2) {
        if (listaDatos2 instanceof ArregloDatos) {//verifica  de que sea un objeto de tipo arreglo
            ArregloDatos lista2 = (ArregloDatos) listaDatos2;
            //Recorremos la lista2 para eliminar sus elementos
            for (int pos = 0; pos <= lista2.cantidadElementos() - 1; pos++) {
                Object elementoActual = lista2.obtener(pos);// obtiene las posiciones de la lista 2
                eliminar(elementoActual); // elimina los datos de la lista original
                if (vacia()) {//Ya está vacia la lista
                    break;
                }
            }
            return true;
        } else {//Es no es un ArregloDatos
            return false;
        }

    }

    public boolean eliminarListaRR(Object listaDatos2){
        if (listaDatos2 instanceof ArregloDatos){
            ArregloDatos arreglo2 = (ArregloDatos)listaDatos2;
            return eliminarListaRR(arreglo2,0);
        }else return false;
    }

    private boolean eliminarListaRR(ArregloDatos arreglo, int iterador){
        if (iterador<=tope){
            if (arreglo.eliminar(lote[iterador])!=null){
                eliminarListaRR(arreglo,iterador+1);
                return true;
            }else {
                return eliminarListaRR(arreglo,iterador+1);
            }
        }
        return false;
    }


    /**
     * rellena los datos espesificando sui cantidad y el elemento que se rellenara dentro de el arreglo
     *
     * @param elemento
     * @param cantidad
     */
    @Override
    public void rellenar(Object elemento, int cantidad){
        int maximo=(cantidad>CAPACIDAD ? CAPACIDAD : cantidad );
        for(int pos=0;pos<maximo;pos++){
            agregar(elemento);
        }
    }
    /**
     * Método para llenar el arreglo según la información y cantidad,
     * @param elemento elelemto a agregar
     * @param cantidad número de veces a agregar.
     */
    public void rellenarRR(Object elemento, int cantidad) {
        if(tope < CAPACIDAD && cantidad >0){
            agregar(elemento);
            rellenarRR(elemento, cantidad-1);
        }
    }

    /**
     * clona todo el arreglo que se usa en la clase
     *
     * @return listaCopia
     */
    @Override
    public Object clonar() {
        ArregloDatos listaCopia = new ArregloDatos(cantidadElementos() + 1);
        for (int a = 0; a <= tope; a++) listaCopia.agregar(lote[a]);
        return listaCopia;
    }

    /**
     * Método para clonar la lista actual a otra lista.
     * @return una copia del arreglo.
     */
    public Object clonarRR(){
        return clonarRR(new ArregloDatos(capacidad()),0);
    }

    private Object clonarRR(ArregloDatos arreglo, int iterador){
        if (iterador<=tope){// verificamos de que sea menor a le tope
            arreglo.agregar(lote[iterador]);
            return clonarRR(arreglo,iterador);
        }
        return arreglo;
    }


    /**
     * muestra solamente los datos de el arreglo que se mencionan por medio de su inicio y hasta que dato se debe de mostrar (final)
     *
     * @param indiceInicial
     * @param indiceFinal
     * @return null en caso de no entrar a los datos, en caso de entrar imprimie los datos de la sub lista
     */
    @Override
    public Object subLista(int indiceInicial, int indiceFinal) {
        int resta = indiceFinal - indiceInicial;
        Object regresarLista[] = new Object[(resta)]; // CREA LA LONGITUD DE EL ARREGLO MEDIANTE LA RESTA DE LOS DATOS inidices
        int Contador = 0;
        if (indiceInicial >= 0) {
            if (indiceInicial < indiceFinal) {
                if (indiceFinal <= CAPACIDAD) {
                    for (int i = indiceInicial; i < indiceFinal; i++) {// recorre los datos solo en le rango de inicio y final
                        regresarLista[Contador] = lote[i];// ese rango es agregado a una nueva lista
                        //SalidaTerminal.consola(regresarLista[Contador] + "\n");
                        // muestra los datos de esa misma lista, donde es el rango de inicio y final
                        Contador = Contador + 1;


                    }
                }
            }

        }
        return null;
    }

    public Object subListaRR(int indiceInicial, int indiceFinal){
        if(indiceInicial >= 0 && indiceInicial <= tope && indiceFinal <= tope && indiceFinal >= indiceInicial){//Validamos los limites
            ArregloDatos subLista =  new ArregloDatos((indiceFinal+1)-indiceInicial);//Creamos el sub arreglo
            return subListaRR(indiceInicial,indiceFinal,subLista);
        }
        return null;
    }

    private Object subListaRR(int indiceInicial, int indiceFinal, ArregloDatos subLista){
        if(indiceInicial<=indiceFinal){
            subLista.agregar(lote[indiceInicial]);//Agregamos el dato
            return subListaRR(indiceInicial+1,indiceFinal,subLista);
        }
        subLista.imprimir();
        return subLista;//Caso base devlovemos la lista
    }

        /**
         * regresa el maximo de el arreglo
         *
         * @return CAPACIDAD
         */
    public int capacidad() {
        return CAPACIDAD;
    }

    /**
     * MUESTRA LA CANTIDAD DE DATOS QUE SE ENCUENTRAN EN EL ARREGLO
     *
     * @return TOPE+1
     */
    public int cantidadElementos() {
        return tope + 1; // tope es el limite de el arreglo( hasta donde existen datos)
    }

    /**
     * cambia la posicion de un elemento en espesifico por otro dado mediante el indicee
     *
     * @param indice
     * @param elemento
     * @return true o false
     */
    @Override
    public boolean cambiar(int indice, Object elemento){
        if (indice>=0 && indice<=CAPACIDAD){
            lote[indice]=elemento;
            return true;
        }else{
            return false;
        }
    }


    /**
     * cambia los datos de el arreglo mediante un arreglo nuevo donde indica las posiciones que se cambiaran y un arreglo elementoNuevo
     * donde este nos inidca los elementos que se guardaran en esa posicion
     *
     * @param indicesBusqueda
     * @param elementosNuevos
     * @return true o false
     */
    @Override
    public boolean cambiarArregloDatos(ArregloDatos indicesBusqueda, ArregloDatos elementosNuevos) {
        if (indicesBusqueda.cantidadElementos() == elementosNuevos.cantidadElementos()) {
            if (!indicesBusqueda.vacia() && !elementosNuevos.vacia()) {// mientras no este vacia
                for (int i = 0; i < indicesBusqueda.cantidadElementos(); i++) {
                    cambiar((Integer) indicesBusqueda.obtener(i), (Integer) elementosNuevos.obtener(i));// cambia las posiciones de los adtos por los elementos nuevos en el arreglo
                }
                return true;
            }
        }

        return false;
    }



    public boolean cambiarListaRR(ArregloDatos arreglo2, ArregloDatos arregloNuevos, int iterador){
        if (iterador<=arreglo2.tope){
            Integer posicion = (Integer)buscar(arreglo2.obtener(iterador));
            if (posicion!=null){
                lote[posicion] = arregloNuevos.obtener(iterador);
                cambiarListaRR(arreglo2,arregloNuevos,iterador+1);
                return true;
            }else
                return cambiarListaRR(arreglo2,arregloNuevos,iterador+1);
        }else
            return false;
    }


    /**
     * elimina un dato mediante la posicion que se le de
     *
     * @param indice
     * @return elementoBorrado
     */
    @Override
    public Object eliminar(int indice) {
        if (indice < CAPACIDAD) { // en caso de que el indice sea mayos se sale de inmediato
            Object elemntoBorrado = lote[indice];
            if (buscar(elemntoBorrado) != null) {
                tope = tope - 1;
                for (int movimiento = indice; movimiento <= tope; movimiento++) {// recorre el limite de el arreglo  mediante el indice
                    lote[movimiento] = lote[movimiento + 1];
                }
                return elemntoBorrado;


            }
        } else {
            return null;
        }
        return null;
    }

    public Object eliminarRR(int indice){
        if((CAPACIDAD - 1) >= indice && indice >= 0){//Valida que el indice sea valido
            return reacomodarRR(indice,lote[indice]);
        }
        return null;
    }

    private Object reacomodarRR(int indice, Object elementoBorrado){
        if(indice<tope){
            lote[indice] = lote[indice+1];
            return reacomodarRR(indice+1,elementoBorrado);
        }
        tope = tope - 1;
        return elementoBorrado;
    }
    /**
     * agranda la vista de los datos en el arreglo o achica los datos mediante un rango maximo
     *
     * @param maximo
     * @return lote (arreglo)
     */
    @Override
    public Object redimensionar(int maximo) {
        ArregloDatos copia =new ArregloDatos(cantidadElementos());
        for (int a=0;a<=tope;a++)copia.agregar(lote[a]);
        int lim=tope;
        if(maximo<(tope+1)){// si es mas chico
            lote= new Object[maximo];
            tope=-1;
            CAPACIDAD=maximo;
            for (int a=0;a<maximo;a++){
                agregar(copia.lote[a]);
            }
        }else{
            lote= new Object[maximo];
            tope=-1;
            CAPACIDAD=maximo;
            for (int a=0;a<lim;a++){
                agregar(copia.lote[a]);
            }
        }
        return lote;


    }

    /**
     * metodo que manda llamar a un metodo recursivo para obtener la redimencion con un tamaño minimo
     * @param maximo
     * @return tamaño de la redimencion
     */
    public Object redimensionarRR(int maximo) {
        ArregloDatos temp = (ArregloDatos) clonar();//Creamos un arreglo temporal (El que vamos a devolver)
        int tempLimit = tope;//Creamos un limite temporal
        tope = -1;//Restablecemos el limite a -1
        lote =  new Object[maximo];//creamos el nuevo arreglo con las nuevas dimensiones
        CAPACIDAD = maximo;//Restablecemos el maximo
        return redimencionarRR(0,temp,tempLimit);
    }

    /**
     * Metodo privado que genera los datos redimencionandos en un arreglo de datos nuevo
     * @param posInicio
     * @param datos
     * @param limiteDatos
     * @return datos
     */
    private Object redimencionarRR(int posInicio, ArregloDatos datos, int limiteDatos){
        if(posInicio<=limiteDatos){//Aun hay datos
            Object elemento = datos.obtener(posInicio);
            agregar(elemento);
            return redimencionarRR(posInicio+1,datos,limiteDatos);
        }
        return datos;
    }


    /**
     * Metodo que nos comprueba si alguina lista es sunconjunto a la actual
     *
     * @param lista2
     * @return
     */
    @Override
    public boolean esSublista(ListaDatos lista2) {
        ArregloDatos arreglo2 = (ArregloDatos) lista2;
        int cont = 0;
        for (int i = 0; i < arreglo2.cantidadElementos(); i++) {
            if (buscar(arreglo2.obtener(i)) != null) {
                cont++;
            }
        }
        if (arreglo2.cantidadElementos() == cont) return true; // regresamos true si la busqueda fue correcta

        return false;

    }

    /**
     * metodo que manda a llamar el metodo recursico de es sublista rr1
     * @param listaDatos2
     * @return true o false
     */
    public boolean esSublistaRR(ListaDatos listaDatos2) {
        return esSublistaRR1(listaDatos2,0);
    }

    /**
     * metodo recursivo que nos ayuda a saber si una lista de datos pequeña tenga los mismos datos  que
     * el arreglo original
     * @param listaDatos2
     * @param pos
     * @return true o false
     */
    private boolean esSublistaRR1(ListaDatos listaDatos2, int pos) {
        if(!((ArregloDatos)listaDatos2).obtener(pos).equals(lote[pos])){// verificamos que no sean iguales los metodos
            return false;
        }else if(pos>((ArregloDatos)listaDatos2).cantidadElementos()) {
            return true;
        }else{
            return esSublistaRR1(listaDatos2, pos+1);// pos sera el iterador
        }
    }

    /**
     * este metodo agrega los datos que estan solo en la lista2 a la lista actual
     *
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean retenerLista(ListaDatos lista2) {
        if (!lista2.vacia()) {
            ArregloDatos arregloLista = new ArregloDatos(cantidadElementos());
            for (int i = 0; i < cantidadElementos(); i++) {
                eliminar(obtener(i)); // elimina los datos de el arreglo original
            }
            for (int i = 0; i < arregloLista.cantidadElementos(); i++) {
                agregar(arregloLista.obtener(i)); // agrega los datod de el arreglo nuevo
            }
            return true;
        }
        return false;
    }

    /**
     * Este metodo nos ayuda a cambiar el valor por medio de su indice
     *
     * @param indice
     * @param elemento
     * @return true o false
     */
    @Override
    public boolean insertar(int indice, Object elemento) {
        if (!lleno() && enLimites(indice)) { // Se puede insertar mas elementos
            tope += 1;
            // recorremos todos los elementos de lugar derecho.
            for (int movs = tope; movs >= indice; movs--) {
                lote[movs] = lote[movs - 1];
            }
            cambiar(indice, elemento);
            return true;
        }
        return false; // lista llena
    }

    /**
     * metodo recursivo que nos ayuda a poder cambiar un elemento dependiendo de su indice en donde se encuentre
     *
     * @param indice
     * @param info
     * @param pos
     * @return true o false
     */
    public boolean insertarRR(int indice, Object info, int pos){
        if(indice<=tope && tope < CAPACIDAD-1){
            if(pos  == indice){// verificamos de que sea igual la longitud
                tope = tope + 1;// aumentamos posicion en tope
                cambiarPosicionesA(pos + 1, tope);// mandamos llamar el metodo recursivo
                lote[pos] = info;// asignamos en la posicion  la info nueva
                return true;
            }else{
                return insertarRR(indice, info,pos+1);
            }
        }else {
            return false;
        }
    }

    /**
     * metodo recursico que nos ayuda a insertar un dato mandandolo llamar como parametro
     * @param posicion
     * @param pos
     */
    private void cambiarPosicionesA(int posicion, int pos){
        if(posicion<= pos){
            lote[pos] = lote[pos-1];// asignamos una nueva posicion
            cambiarPosicionesA(posicion,pos-1);
        }
    }



    /**
     * cambia los datos de un arreglo dado por el arreglo actual
     *
     * @param listaDatos2
     * @param listaDatos2Nuevos
     * @return true o false
     */
    @Override
    public boolean cambiarLista(ListaDatos listaDatos2, ListaDatos listaDatos2Nuevos) {
        ArregloDatos lista2original = (ArregloDatos) listaDatos2;
        ArregloDatos lista2nueva = (ArregloDatos) listaDatos2Nuevos;

        if (lista2original.cantidadElementos() == lista2nueva.cantidadElementos()) {
            for (int i = 0; i < lista2original.cantidadElementos(); i++) {
                Object obtenerElemento = lista2original.obtener(i);
                Integer posicionNuevo = (Integer) lista2original.buscar(obtenerElemento); // obtenemos el valor entero de la busqueda de el dato
                if (posicionNuevo != null) { // si lo encontro
                    lista2original.cambiar(posicionNuevo, lista2nueva.obtener(i));// cambia los datos  de lista2nueva hacia la origianl return true;
                }

            }
            return true;
        }
        return false;
    }


    /**
     * metodo que nos ayuda a poder obtener el resultado de el cambio de listas
     * con metodo recursivos
     * @param listaDatos2
     * @param listaDatos2Nuevos
     * @return true o false
     */
    public boolean cambiarListaRR(ListaDatos listaDatos2, ListaDatos listaDatos2Nuevos) {
        if(!vacia()){
            return cambiarListaRR1((ArregloDatos) listaDatos2,(ArregloDatos) listaDatos2Nuevos, 0);
        }else{
            return false;
        }
    }

    /**
     * metodo recursivo que nos ayuda a poder cambiar los datos iguales en la lista de arreglos
     * @param lista
     * @param listaDatos2Nuevos
     * @param pos
     * @return true o false
     */
    private  boolean cambiarListaRR1(ArregloDatos lista, ArregloDatos listaDatos2Nuevos, int pos){
        if(pos<=lista.cantidadElementos()){// en caso de que la posicoin sea menor o igual
            cambiar(lista.obtener(pos),listaDatos2Nuevos.obtener(pos), pos+1 );
            return cambiarListaRR1(lista, listaDatos2Nuevos, pos+1);
        }else{
            return true;
        }
    }


    public int getLimite(){
        return tope;
    }
    /**
     * metodo que copea una lista apartir de la lista actual
     *
     * @param listaDatos2
     * @return
     */
    @Override
    public boolean copiarLista(ListaDatos listaDatos2) {
        if (((ArregloDatos) listaDatos2).cantidadElementos() == cantidadElementos()) {
            vaciar();
            for (int pos = 0; pos < ((ArregloDatos) listaDatos2).cantidadElementos(); pos++) {
                agregar(listaDatos2.obtener(pos));// obtenemos  la posicion de el dato y la agregamos a el lote en tal posicion
                // (para poder agregarlo debemos de poder castear primero el dato )
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * metodo que nos ayuda a poder pasar los datos de el nuevo arreglo a el arreglo orignal
     * @param listaDatos2
     * @return true o false
     */
    public boolean copiarListaRR(ListaDatos listaDatos2){
        if (listaDatos2 instanceof ArregloDatos){
            ArregloDatos arreglo2 = (ArregloDatos)listaDatos2;
            if (arreglo2.tope == tope){// verificamos de que sean de el mismo tope
                vaciar();// vaciamos los datos
                agregarElementosArreglo(0,arreglo2);// agregmos elementos con el metodo de agregar
                return true;
            }else
                return false;
        }else
            return false;
    }

    /**
     * metodo privado que nos ayuda a poder agregar todos los datos de un arreglo a  el arreglo original
     * @param iterador
     * @param arregloDatos
     */
    private void agregarElementosArreglo(int iterador, ArregloDatos arregloDatos){
        if (!lleno()){
            agregar(arregloDatos.obtener(iterador));// agregamos los datos
            agregarElementosArreglo(iterador+1,arregloDatos);// generamos el metodo recursivo
        }
    }


    /**
     * Método que rellena una lista a partir de un elemento.
     *
     * @param elemento
     */
    @Override
    public void rellenar(Object elemento) {
        for (int pos = tope + 1; pos < CAPACIDAD; pos++) {
            agregar(elemento);
        }

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
     * metodo que rellena el arreglo mientras no este lleno con un mismo elemento
     * @param elemento
     */
    public void rellenarRR(Object elemento){
        if (!lleno()){// en caso de que este vacio
            agregar(elemento);// agregamos el elemento
            rellenarRR(elemento);
        }
    }





    /**
     * metodo que nos ayuda a regresar  un arreglo conteniendo los elementos del arreglo
     *  actual que se obtienen del arreglo de índices arregloIndices
     * @param arregloIndices
     * @return
     */
    public ArregloDatos subLista(ArregloNumerico arregloIndices){
        ArregloDatos arregloRetornar = new ArregloDatos(cantidadElementos());
        if (!arregloIndices.vacia() && !vacia()){ // verificamos de que los dos arerglos no esten vacios
            for (int posicion=0;posicion<arregloIndices.cantidadElementos();posicion++){
                double posicionArreglo=(double) (arregloIndices.obtener(posicion)); // obtenemos la posicion de cada uno de los datos
                if (enLimites( (int) posicionArreglo)) arregloRetornar.agregar(obtener( (int) posicionArreglo)); // agregamos los datos
            }
        }return arregloRetornar;
    }


    protected boolean enLimites(int indice){
        if(indice>=0 && indice<=tope){
            return true;
        }else{
            return false;
        }
    }




}
