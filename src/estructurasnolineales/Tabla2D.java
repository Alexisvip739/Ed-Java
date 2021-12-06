package estructurasnolineales;

import Herramientas.TipoColumna;
import Herramientas.TipoRenglon;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;

/**
 * Clase  que lleva toda la logica de programa en donde se puede crear y modificar siertas Tablas   2D
    @author Alexis Ultreras Sotelo
    @version 1.0
 */
public class Tabla2D {
    protected int renglones;
    protected int columnas;
    protected Object lote[][];

    /**
     * constructor en donde almacenamos la longitud de la matriz
     * @param reng
     * @param colum
     */
    public Tabla2D(int reng, int colum) {
        renglones = reng;
        columnas = colum;
        lote = new Object[renglones][columnas];
    }

    /**
     * constructor en donde almacenamos la longitud y un dato  que se quiera para rellenal la Tabla
     * @param reng
     * @param colum
     * @param info
     */
    public Tabla2D(int reng, int colum, Object info) {
        renglones = reng;
        columnas = colum;
        lote = new Object[renglones][columnas];
        //tendré que invocar a un método que rellene toda la matriz con datos de tipo "info"
        rellenar(info);
    }



    /**
     * este metodo nos ayuda a meter dentro de la matriz cualquier dato que nosotros queramos
     * @param info
     */
    public void rellenar(Object info) {
        for (int renglon = 0; renglon < renglones; renglon++) { //recorrer cada renglón
            for (int columna = 0; columna < columnas; columna++) { //recorrer todas las columnas de una sola fila o renglón
                lote[renglon][columna] = info;// asignamos el dato en las posiciones
            }
        }
    }

    /**
     * obtenemos todas el valor que se encuentra dentro de dicha columna y dicho renglon
     * @param renglon
     * @param columna
     * @return
     */
    public Object obtenerInfo(int renglon, int columna){

        if (validarDimension(renglon, renglones) == true && validarDimension(columna, columnas) == true) {// verificamos que las validaciones sean correctas
            return lote[renglon][columna]; // obtenemos el dato
        } else {
            return null;
        }
    }


    /**
     * Este metodo redimensiona la matriz
     * @param reng renglones de la nueva matriz
     * @param colum columnas de la nueva matriz
     */
    public void redimensionar(int reng,int colum){
        renglones=reng;
        columnas=colum;
        lote= new Object[reng][colum];
    }


    /**
     * metodo que valida las longitudes de  la tabla 2d actual
     * @param dimension
     * @param limiteDimension
     * @return true || false
     */
    private boolean validarDimension(int dimension, int limiteDimension) {
        if (dimension >= 0 && dimension < limiteDimension) { //cumple los límites
            return true;
        } else {
            return false;
        }
    }


    /**
     * asigna un valor en dicha fila y columna que se desee en la tabla 2d
     * @param fila
     * @param columna
     * @param contenido
     * @return true || false
     */
    public   boolean asignarCelda(int fila, int columna, Object contenido){
        if (validarDimension(fila, renglones) == true && validarDimension(columna, columnas) == true) { // verificamos las longitudes
            lote[fila][columna] = contenido; // asignamos el parametro en la fila y columna
            return true;
        } else {
            return false;
        }
    }

    /**
     * metodo que nos regresa la longitud de los renglones
     * @return
     */
    public int getFilas(){
        return renglones;
    }

    /*
    metodo que nos regresa la longitud de la columna
     */
    public int getColumnas(){
        return columnas;
    }

    /**
     * imprime los datos de la tabla de renglon en renglon
     */
    public  void imprimirR(){
        for (int i=0;i<renglones;i++){
            for (int j=0;j<columnas;j++){
                SalidaTerminal.consola(lote[i][j]+" ");
            }
            SalidaTerminal.consola("\n");
        }

    }



    public void imprimir(){
        //Rebanar por cada fila
        for(int fila=0;fila<renglones;fila++){
            //ahora, tenemos una tabla 2D, se imprime primero su columna
            for(int columna=0;columna<columnas;columna++){
                SalidaTerminal.consola(lote[fila][columna]+ " ");
            }
            //cuando acabe todas las columnas de la tabla 2d
            SalidaTerminal.consola("\n");
        }
    }

    /**
     * imprime los datos de la tabla de columna en columna
     */
    public void imprimirC(){
        for(int columna=0;columna<columnas;columna++) {
            for(int fila=0;fila<renglones;fila++) {
                SalidaTerminal.consola(lote[fila][columna] + " ");
            }
            SalidaTerminal.consola("\n");
        }
    }


    /**
     * invierte los datos de la tabla de forma transpuesta para dicha tabla
     * esto quiere decir que cambia las columnas por los renglones y los renglones por las columnas
     */
    public void transpuesta() {
        Tabla2D temproal = ClonarTabla2D();

        int filasTemporal = renglones;
        renglones = columnas;
        columnas = filasTemporal;
        lote = new Object[renglones][columnas]; // asignamos datos a el lote de la tabla

        for (int posicionR = 0; posicionR < renglones; posicionR++) { //recorrer cada renglón
            for (int posicionC = 0; posicionC < columnas; posicionC++) //recorrer todas las columnas de una sola fila o renglón
                lote[posicionR][posicionC] = temproal.obtenerInfo(posicionC, posicionR);
        }
    }

    /**
     * clona los datos de la tabla original por otra matriz con los datos clonados
     * de la misma tabla original
     * @return matrizClonada
     */
    public Tabla2D ClonarTabla2D(){
        Tabla2D matrizClonada = new Tabla2D( renglones,columnas );
        for (int renglon=0; renglon<renglones ; renglon++) {
            for (int columna=0 ; columna<columnas ; columna++){
                matrizClonada.asignarCelda( renglon,columna, obtenerInfo( renglon,columna ) );// asigna los datos de la tabla original a el clon con los datos de la tabla original
            }
        }return matrizClonada;
    }


    /**
     * verifica de que dos tablas de la misma longitud tengan los mismos datos informaticos
     * tanto en su posicion en que se encuentran
     * @param matriz2
     * @return true o false
     */
    public  boolean EsIgual(Tabla2D matriz2){
        if (getFilas()== matriz2.getFilas() && getColumnas()== matriz2.getColumnas()){// verificamos los limites de la tabla
            for (int i=0;i<getFilas();i++){
                for (int j=0;j<getColumnas();j++){
                    if (!lote[i][j].toString().equalsIgnoreCase(matriz2.obtenerInfo(i,j).toString())){ // en caso de que no sean iguales regresa false
                        return false;
                    }
                }
            }
            return true; // si son iguales
        }
        return false;

    }

    /**
     * asigna un vector en la columanda de la tabla origianl
     * @param numFilas
     * @param info
     * @return true o galse
     */
    public boolean vectorColumna(int numFilas, Object info) {
       if (numFilas<=columnas &&numFilas>0 ){
           for (int i=0; i < numFilas; i++) { // recorremos las filas  mandadas como parametros
               for (int j = 0; j < columnas; j++) {
                   lote[i][j]= info; // asigamos la informacion

               }
           }
       }

        return false;
    }

    /**
     * agregamos un vector con filas dentro de la tabla original
     * @param numFilas
     * @param info
     * @return true o false
     */
    public boolean VectorFila(int numFilas, Object info) {
        if (numFilas<=renglones &&numFilas>0 ){
            for (int i=0; i < renglones; i++) {
                for (int j = 0; j < numFilas; j++) {// recorremos el numero de filas
                    lote[i][j]= info; // asignamos el dato

                }
            }
        }

        return false;
    }

    /**
     * permite que se pueda crear/redimensionar/substituir la matriz actual por
     * una pasada como argumento que se le puede dar
     *
     * @param matriz2d
     * @return true
     */
    public boolean redifinirMatriz2D(Tabla2D matriz2d) {
        renglones = matriz2d.getFilas();
        columnas = matriz2d.getColumnas();
        lote = new Object[renglones][columnas]; // asignamos los datos de el arreglo nuevo a la tabla lote
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                lote[i][j] = matriz2d.obtenerInfo(i,j); // obtenemos los datos y los asignamos a el lote original con sus respectivas dimenciones
            }
        }
        return true;
    }


    /**
     * Agrega los renglones de valores con  Arreglo
     *
     * @param arreglo
     * @return
     */
    public boolean agregarRenglon(ArregloDatos arreglo) {
        if (arreglo.cantidadElementos() == renglones) { // verificamos las longitudes

            for (int i = 0; i < columnas; i++) {
                for (int j = 0; j < renglones; j++) {
                    lote[i][j] = arreglo.obtener(j); // agrega el renglon a la tabla original con sus datos de el arreglo original
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Agrega las columnas de valores con  Arreglo
     *
     * @param arreglo
     * @return true
     */
    public boolean agregarColumna(ArregloDatos arreglo) {
        if (arreglo.cantidadElementos() == columnas) { // verificamos las longitudes
            for (int i = 0; i < columnas; i++) {
                for (int j = 0; j < renglones; j++) {
                    lote[i][j] = arreglo.obtener(i); // asignamos los datos a la columna indicada
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Permite agregar una tabla nueva a la tabla actual
     *
     * @param tabla2 TDA Tabla2D con elementos a agregar a la tabla actual.
     * @return true o false
     */
    public boolean agregarTablaxColumna(Tabla2D tabla2) {
        if (validarDimension(tabla2.getFilas(),renglones) && validarDimension(tabla2.getColumnas(),columnas)){// verificamos las longitudes
            for (int posicionR=0;posicionR<tabla2.getFilas();posicionR++) {
                for (int posicionC = 0; posicionC < tabla2.getColumnas(); posicionC++)
                    lote[posicionR][posicionC]=tabla2.obtenerInfo(posicionC,posicionR);//asgrega la tabla pasada como paramentro a la tabla original obteniedo los datos
            }return true;
        }else return false;
    }

    /**
     * Permite agregar una tabla nueva a la tabla actual
     *
     * @param tabla2 TDA Tabla2D con elementos a agregar a la tabla actual.
     * @return regresa true si se pudieron agregar elementos, en caso contrario regresa false.
     */
    public boolean agregarTablaxRenglon(Tabla2D tabla2) {
        if (validarDimension(tabla2.getFilas(),renglones) && validarDimension(tabla2.getColumnas(),columnas)){// verificamos dimenciones de columna y renglones
            for (int posicionR=0;posicionR<tabla2.getFilas();posicionR++) {
                for (int posicionC = 0; posicionC < tabla2.getColumnas(); posicionC++)
                    lote[posicionR][posicionC]=tabla2.obtenerInfo(posicionR,posicionC);//asgrega la tabla pasada como paramentro a la tabla original obteniedo los datos
            }return true;
        }else return false;
    }
    /**
     * elimina lo que estan en los renglones
     *
     * @param indice
     * @return true
     */
    public boolean quitarFila(int indice) {
       if (indice<= renglones && indice<=columnas){// verificamos las dimenciones que no se sobresalgan de el rango actual
           for (int i = 0; i < getFilas(); i++) {
               for (int j = 0; j < getColumnas(); j++) {
                   if (i == indice) {
                       lote[indice][j] = null; // quita los datos de la tabla y los pone como null, asignando que ya no existen

                   }

               }

           }
           return true;
       }
       return false;
    }

    /**
     * metodo que quita una columna por medio de el indice en que esta
     * @param indice
     * @return true o false
     */
    public boolean quitarColumna(int indice) {
        if (indice<=renglones && indice<=columnas){
            for (int i = 0; i < getFilas(); i++) {
                for (int j = 0; j < getColumnas(); j++) {
                    if (j == indice) {
                        lote[i][indice] = null; // asigna la columa encontrada como null, desapareciendo los datos
                    }

                }

            }
            return true;
        }
        return false;
    }

    /**
     * crea una matriz 3d por medio de un arreglo pasado como parametro
     * @param matriz
     * @return matriz3d
     */
    public Tabla3D matriz3D(ArregloDatos matriz) {
        Tabla3D matriz3d = new Tabla3D(getFilas(), getColumnas(), matriz.cantidadElementos()); // verificamos las longitudes de el metodo

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                for (int a = 0; a < matriz.cantidadElementos(); a++) {
                    matriz3d.asignarCelda(i, j, a, matriz.obtener(i));// obtenemos la informacion y la pasamios como dato junto con sus
                    // renglones, columnas, profundidad y datos
                }
            }
        }
        return matriz3d;
    }



    /**
     * Eliminar una columna de una tabla.
     * @param tipoCol TipoColumna es un enumerado para columna izquierda o derecha.
     * @return regresa true si se pudo eliminar la columna, en caso contrario regresa false.
     */
    public boolean quitarColuma(TipoColumna tipoCol) {
        if (columnas>1){
            Tabla2D temporal = ClonarTabla2D();
            columnas = columnas-1;
            lote=new Object[renglones][columnas];// asignamos el rango de el lote

            if (tipoCol.equals(TipoColumna.IZQUIERDA)){
                for (int i=0;i<renglones;i++){// recorremos renglones
                    for (int j=0;j<columnas;j++)// recorrecomos columnas
                        lote[i][j]=temporal.obtenerInfo(i,j+1);// aumentamos un valor en la columna para obtener el valor de la izquierda
                }return true;

            }else {
                for (int i=0;i<renglones;i++){
                    for (int j=0;j<columnas;j++)
                        lote[i][j]=temporal.obtenerInfo(i,j);
                }return true;

            }
        }return false;
    }
    /**
     * Eliminar una fila de una tabla.
     *
     * @param tipoFila TipoFila es un enumerado para fila superior o inferior.
     * @return regresa true si se pudo eliminar la fila, en caso contrario regresa false.
     */
    public boolean quitarFila(TipoRenglon tipoFila) {
        if (renglones>1){
            Tabla2D temporal = ClonarTabla2D();
            renglones = renglones-1;
            lote=new Object[renglones][columnas];

            if (tipoFila.equals(TipoRenglon.SUPERIOR)){ // en caso de que sea de forma superior
                for (int i=0;i<renglones;i++){
                    for (int j=0;j<columnas;j++)
                        lote[i][j]=temporal.obtenerInfo(i+1,j);// aumentamos un dato mas a el arreglo para poder obtener el rango superior con los datos
                }return true;

            }else { // en caso de que sea inferior

                for (int i=0;i<renglones;i++){
                    for (int j=0;j<columnas;j++)
                        lote[i][j]=temporal.obtenerInfo(i,j); // RECORREMOS EL DATO OBTENIENDO  SU INFORMACION EN LA PARTE INFERIOR
                }return true;

            }
        }return false;
    }

    public void diagonal(Object elemento){
        if(renglones==columnas){
            for(int diag=0;diag<renglones;diag++){
                lote[diag][diag]=elemento;
            }
        }else{ //hacer lo que corresponda,

        }
    }
}
