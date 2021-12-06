package estructuraslineales;

import Herramientas.TipoOrden;

/**
 * Esta clase representa toda la funcionalidad que hace el arerglo para poder ordenar sus datos
 * @author  Alexis Ultreras Sotelo
 * @version  1.0
 */
public class ArreglOrdenado extends  ArregloDatos{
    private int maximo;
    private TipoOrden orden;

    /**
     * Contructor de la clase donde se lleva como parametros el tipo de orden que se desea a aordenar
     * y el maximo de datos
     * @param maximo
     * @param orden
     */
    public ArreglOrdenado(int maximo, TipoOrden orden) {
        super(maximo);
        this.orden = orden;
    }


    /**
     *
     * @param info
     * @return info
     */
    public int compareTo(Object info){// sirve para comparar varios Strings, int entr otros mas
        return  info.toString().compareTo("0");//La informacion mandada la compara con un "0"
    }


    /**
     *este metodo nos agrega un nuevo valor a el arreglo, pero de forma ordenada
     * @param elemento
     * @return regresa posicion o -1 en caso de no agregarlo
     */
    @Override
    public int agregar(Object elemento) {
        if (!lleno()){
            if (orden.equals(TipoOrden.Ordenado)){
                // no esta lleno
                Integer posicion = (Integer)buscar(elemento);
                if (posicion<0 || posicion==null){ // no esta el dato, entonces se puede agregar
                    posicion*=(-1);// cuando no existe el dato se multiplica por -1 para que se haga positivo
                    posicion-=1;
                    tope+=1;
                    for (int mov = tope; mov>=(posicion+1);mov--)
                        lote[mov] = lote[mov-1];
                    lote[posicion]=elemento;
                    return posicion;
                }
            }else {
                Integer posicion = (Integer)super.buscar(elemento);
                if (posicion==null) // no existe, podemos agregarlo
                    return super.agregar(elemento);
            }
        }
        return -1;
    }

    /**
     * Elimina un dato agregado dentro de la lista de arreglos
     * @param info
     * @return infoBorrada
     */
    @Override
    public Object eliminar(Object info){
        Integer PosicionEncontrada =(Integer) buscar(info); // se castea el dato a entero al momento de buscarlo y se asigna a una variable
        if(PosicionEncontrada>=0 && PosicionEncontrada<=tope){
            PosicionEncontrada=PosicionEncontrada-1;// aumenta una posicion cada vez  que se hace la condicion
            tope=tope-1;
            Object infoBorrada=lote[PosicionEncontrada]; // obtenemos la info Borrada en su posicion
            for (int mov=PosicionEncontrada;mov<=tope;mov++ ){
                lote[mov]=lote[mov+1];
            }
            return  infoBorrada;
        }else {
            return null;
        }
    }

    /**
     * busca el elemento ordenado dado por el usuario
     * @param elemento
     * @return si no se encuentra regresa la posicion que deberia de ir en negativo y si esta regresa su posicion en positivo
     */
    @Override
    public Object buscar(Object elemento){
        int posicion=0;
        while (posicion<=tope && elemento.toString().charAt(0) > lote[posicion].toString().charAt(0)){
            posicion+=1;
        }if (posicion > tope || elemento.toString().charAt(0) < lote[posicion].toString().charAt(0)){
            return (posicion+1)*(-1); // No se encontro
        }else {
            return posicion+1; // Se encontro
        }
    }



    /**
     * Cambia el dato que este en la lista por otro nuevo por el usuario
     * @param indice
     * @param elemento
     * @return true || false
     */
    @Override
    public boolean cambiar(int indice, Object elemento) {
        if (enLimites(indice)){ // verifica si esta dentro del rango del tamaño de la lista
            if (orden.equals(TipoOrden.Ordenado)){ // es de tipo orden ASC
                if (elemento.toString().charAt(0) < lote[indice+1].toString().charAt(0)){ // el caracter es menor que el elemento siguiente del arreglo
                    lote[indice]=elemento;
                }else {
                    int posicion =indice ;
                    // Recorremos los elementos del arreglo
                    while (posicion<tope && elemento.toString().charAt(0) > lote[posicion].toString().charAt(0)) {
                        lote[posicion] = lote[posicion+1];
                        posicion++;
                    }
                    lote[posicion]=elemento;
                }return true;
            }else return super.cambiar(indice,elemento);
        } else return false;
    }

    /**
     * Cambia el elemento   que ya estaba agregado por un  elemento nuevo solamente una vez
     * @param elementoViejo
     * @param elementoNuevo
     * @param numVeces
     * @return true o false
     */
    @Override
    public boolean cambiar(Object elementoViejo, Object elementoNuevo, int numVeces) {
        Integer posicion = (Integer)super.buscar(elementoViejo);
        if (posicion!=null){
            return cambiar(posicion,elementoNuevo);
        }return false;
    }


    /**
     * voltea los datos de el arreglo a como estaban originalmente
     */
    @Override
    public void invertir() {
        if(orden.equals(TipoOrden.Ordenado)){

            ArregloDatos datosInvertidos= new ArregloDatos(tope+1);
            for(int b=tope;b>=0;b--)datosInvertidos.agregar(lote[b]); // agregamos los datos a el el segundo arreglo Ordenafo
            for(int a=0;a<=tope;a++)lote[a]=datosInvertidos.lote[a];
        }else{
            orden=TipoOrden.Ordenado;
            ArregloDatos copia=new ArregloDatos(tope+1);
            int lim=tope;
            for(int a=0;a<=lim;a++){
                copia.agregar(lote[a]);// asignamos una copia para poder agregar los datos de el arreglo orignal
            }
            vaciar();
            tope=-1;
            for(int a=0;a<=lim;a++){
                agregar(copia.lote[a]); // aqui es donde se invierten los datos agregados
            }
        }

    }


    /**
     * metodo que rellena los datos de un arreglo ordenado de forma numerica
     * @param elemento
     */
    @Override
    public void rellenar(Object elemento){

    }





    /**
     * este metodo agrega una nueva lista
     * @param listaDatos2
     * @return true || false
     */

    public boolean agregarLista(ListaDatos listaDatos2) {
        if (listaDatos2 instanceof ArreglOrdenado){ // verifica si el elemento enviado es una ArregloOrdenada
            ArreglOrdenado arreglo2 = (ArreglOrdenado) listaDatos2;
            // recorremos todos los elementos del arreglo2
            for (int i=0;i<arreglo2.cantidadElementos();i++){

                if (!lleno()) agregar(arreglo2.obtener(i));
                else break; // se lleno
            }return true;
        }return false;
    }


    /**
     * Desordena todos los datos de el arreglo actual
     * @return lista
     */
    public ListaDatos arregloDesordenado(){
        ArregloDatos lista = new ArregloDatos(CAPACIDAD);
        int contador = tope;
        for(int i=0; i<= tope/2; i++){ // parte el arreglo en dos para poder agregar la mitad de el arreglo
            lista.agregar(lote[i]);
            if(contador > i){
                lista.agregar(lote[contador]);// agregamos la otra parte de el arreglo
            }
            contador--;
        }
        return lista;
    }

    /**
     *  Verifica de que la sea  una
     * sublista o subconjunto de la lista actual
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean esSublista(ListaDatos lista2){

        if(lista2 instanceof ArreglOrdenado){
            boolean resultado = true;
            for(int pos=0; pos<((ArreglOrdenado) lista2).cantidadElementos(); pos++){
                if(super.buscar(((ArreglOrdenado) lista2).obtener(pos))==null){
                    resultado = false;
                }
            }
            return resultado;
        }else{
            return false;
        }



    }


    /**
     * Debe dejar en la lista actual solo los elementos que se encuentran
     * en listaDatos2.
     * @param lista2
     * @return true o false
     */
    @Override
    public boolean retenerLista(ListaDatos lista2){
       boolean bandera=super.retenerLista(lista2); // verificamos la lista con u true o un false
       if (bandera){
           ArregloDatos arregloDatos=(ArregloDatos) lista2; // casteamos el objeto a ArregloDatos
           for(int i=0;i<cantidadElementos();i++){
               eliminar(obtener(i)); // dentro de la lista actua borramos los datos
           }
           for(int i=0;i<arregloDatos.cantidadElementos();i++){ // recorremos el arreglo nuevo
               agregar(arregloDatos.obtener(i)); // ya que borramos los datos de el arreglo orignal agregamos los datos de la nueva lista
           }
       }
       return bandera;
    }

    /**
     * metodo donde nos indica  el cambio de la lista actual por otra lista
     * @param listaDatos2
     * @param listaDatos2Nuevos
     * @return true o false
     */
    @Override
    public boolean cambiarLista(ListaDatos listaDatos2, ListaDatos listaDatos2Nuevos) {
        if (listaDatos2 instanceof ArreglOrdenado && listaDatos2Nuevos instanceof ArreglOrdenado){
            if (orden.equals(TipoOrden.Desordenado)){ // verifica si es ESTA En el orden Desordenado
                ArreglOrdenado arreglo2 = (ArreglOrdenado)listaDatos2; // CASTEAMOS UNA VARIABLE A TIPO ARREGLO ORDENADO
                ArreglOrdenado arregloNuevo = (ArreglOrdenado)listaDatos2Nuevos;
                if (arreglo2.cantidadElementos() == arregloNuevo.cantidadElementos()){ // verifica si tienen la misma cantidad
                    Object temp;
                    Object tempNuevo;

                    for (int i = 0;i<arreglo2.cantidadElementos();i++){
                        temp=arreglo2.obtener(i);// obtenemos la posicion
                        Integer posicionNuevo = (Integer)super.buscar(temp); // obtenemos el valor entero de la busqueda de el dato
                        if (posicionNuevo!=null){ // si lo encontro

                            tempNuevo = arregloNuevo.obtener(i);
                            System.out.println(tempNuevo);
                            lote[posicionNuevo] = tempNuevo;
                        }
                    }return true;
                } // los arreglos enviados no tiene la misma cantidad
            }// el arreglo actual no es DES
        }return false; // no son arregloOrdenado
    }


    /**
     * Inserta un valor por medio de su indice de donde se quiere cambiar el dato
     * @param indice
     * @param info
     * @return
     */
    @Override
    public boolean insertar(int indice, Object info) {
        if (orden.equals(TipoOrden.Ordenado)){
            if(indice<=tope){
                eliminar(lote[indice]);// elimina en la posicion indice el elemento que esta ahi
                agregar(info); // agregamos de forma ordenada
                return true;
            }else{
                return false;
            }

        }
        return false;



    }


    /**
     * copea los datos de la lista2 hacia la lista2
     * @param listaDatos2
     * @return true o false
     */
    @Override
    public boolean copiarLista(ListaDatos listaDatos2){
        if(listaDatos2 instanceof  ArreglOrdenado){
                ArreglOrdenado arreglo2= (ArreglOrdenado) listaDatos2;
                 if (arreglo2.orden()==orden && arreglo2.cantidadElementos()==cantidadElementos()){ // veficicamos las longitudes de los arreglos y de que forma vienen
                    for (int i=0;i<cantidadElementos();i++){
                        eliminar(i); // eliminamos los datos que esten dentro de el arreglo actual

                    }
                    for (int j=0;j<arreglo2.cantidadElementos();j++){
                        agregar(arreglo2.obtener(j)); // agregamos los datos en el arreglo
                    }

                }

        }
        return false;

    }

    /**
     * metodo que nos regresa el orden que se desea, ya sea ordenada o desordenada
     * @return
     */
    private TipoOrden orden(){
        return orden;
    }






}
