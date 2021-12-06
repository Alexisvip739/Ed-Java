package estructurasnolineales;

import Herramientas.TipoColumna;
import Herramientas.TipoLogaritmo;
import Herramientas.TipoRenglon;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ArregloNumerico;

/**
 * Clase en donde se implementa todos los metodos para hacer funcionar a la clase
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Tabla2DNumerica extends Tabla2D {

    /**
     * constructoe de la tabla en donde solamente se asignal las longitudes de cada uno
     * @param reng
     * @param colum
     */
    public Tabla2DNumerica(int reng, int colum) {
        super(reng, colum);
    }

    /**
     * constructor que ademas de dar la longitud de la tabla
     * @param reng
     * @param colum
     * @param info
     */
    public Tabla2DNumerica(int reng, int colum, Object info) {
        super(reng, colum, info);
        super.rellenar(info);
    }

    /**
     * Agrega datos solamente numericos, en caso de que se quiera agregar un dato Object no numero, arrojara un error
     * @param renglon
     * @param columna
     * @param info
     * @return true o false
     */
    public boolean agregarNumero(int renglon, int columna, Object info) {
        if (EsNumerico(info.toString())) {// verificamos de que sea numero
            double dato = Double.parseDouble(info.toString());
            asignarCelda(renglon, columna, dato);// asignamos una posicion en dicha tabla
            return true;// regresamos verdad en caso de que si se haya agregado el dato
        } else {
            return false;

        }

    }

    /**
     * verifica de que los datos a agregar sean numericos
     * @param info
     * @return true o false
     */
    public boolean EsNumerico(Object info) {
        try {
            Double.parseDouble(info.toString());// casteamos el dato a double
            return true; // verificasion de que si es un dato numerico
        } catch (NumberFormatException nfe) {
            return false;// no es dato numerico
        }
    }

    /**
     * multiplica toda la matriz en cada posicion por un dato en espesifico
     * @param escalar
     * @return true o false
     */

    public boolean PorEscalar(Number escalar) {
        double numeroEscalar = Double.parseDouble(escalar.toString()); // pasa el dato original a double
        if (EsNumerico(escalar.doubleValue())) {
            for (int i = 0; i < getFilas(); i++) {
                for (int j = 0; j < getColumnas(); j++) {
                    Double Numerico_nuevo = (Double.parseDouble(lote[i][j].toString())) * escalar.doubleValue(); // multiplica la posicion que se de por el escalar
                    lote[i][j] = Numerico_nuevo;// asignamos el dato a la tabla
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Multiplicar un escalar para cada elemento de la tabla2d.
     *
     * @param escalares TDA ArregloNumeros con escalares a multiplicar con cada elemento de la tabla.
     * @return regresa true si se pudo hacer la operacion para cada contenido de la tabla, en caso contrarieo regresa false.
     */
    public boolean xEscalares(ArregloNumerico escalares) {
        if (escalares.cantidadElementos() == renglones * columnas) {
            int posicionEscalar = 0;
            for (int posFila = 0; posFila < getFilas(); posFila++) {
                for (int posColumna = 0; posColumna < getColumnas(); posColumna++) {
                    double contenidoNuevo = (double) escalares.obtener(posicionEscalar) * Double.parseDouble(obtenerInfo(posFila, posColumna).toString());// multiplicamos cada una de las posiciones de dicha tabla y arreglo
                    lote[posFila][posColumna] = contenidoNuevo;// asignamos el dato a la tabla
                    posicionEscalar++;
                }
            }
            return true;
        } else return false;
    }

    /**
     * metodo que nos ayuda a sumar cada una de las posiciones de la tabla por un dato en especial
     * @param escalar
     * @return
     */
    public boolean sumarEscalar(Number escalar) {
        double numeroEscalar = Double.parseDouble(escalar.toString()); // pasa el dato original a double
        if (EsNumerico(escalar.doubleValue())) {
            for (int i = 0; i < getFilas(); i++) {
                for (int j = 0; j < getColumnas(); j++) {
                    Double Numerico_nuevo = (Double.parseDouble(lote[i][j].toString())) + escalar.doubleValue(); // sumamos la posicion que se de por el escalar
                    lote[i][j] = Numerico_nuevo; // asignamos el dato  a el lote
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Este metodo nos ayuda a calular la suma de unnarreglo dado como parametro y sumarlo en la misma posicion que se
     * encuentra en la tabla original, tomando encuanta que estos sean de la misma longitd
     * @param escalares
     * @return true o false
     */
    public boolean SumarEscalar(ArregloNumerico escalares) {
        if (escalares.cantidadElementos() == renglones * columnas) {
            int posicionEscalar = 0;

            for (int posFila = 0; posFila < getFilas(); posFila++) {
                for (int posColumna = 0; posColumna < getColumnas(); posColumna++) {
                    double contenidoNuevo = (double) escalares.obtener(posicionEscalar) + Double.parseDouble(obtenerInfo(posFila, posColumna).toString()); // sumamos la posiicon de el lote, con las posiciones correspondientes de el arreglo

                    lote[posFila][posColumna] = contenidoNuevo;// asignamos los datos en su posicion
                    posicionEscalar++;
                }
            }
            return true;
        } else return false;
    }

    /**
     * Multiplicar por otra tabla2d.
     * @param tabla2 TDA Tabla2DNumeros que cada elemento guardado se multiplicara con la tabla actual.
     * @return true o false.
     */
    public boolean multiplicar(Tabla2DNumerica tabla2){
        if (renglones == tabla2.getColumnas() && columnas == tabla2.getFilas()){ // la cantidad de filas es igual a la de
            // columnas de tabla2 y la contidad de columnas es la misma de filas de tabla2
            Tabla2D nuevaTabla = new Tabla2D(renglones,tabla2.getColumnas());

            for (int posFila=0;posFila<nuevaTabla.getFilas();posFila++){
                for (int posColumna=0;posColumna<nuevaTabla.getColumnas();posColumna++){
                    double suma = 0.0;
                    for (int posicion=0;posicion<columnas;posicion++)
                        suma+= (Double.parseDouble(obtenerInfo(posFila,posicion).toString()) * Double.parseDouble(tabla2.obtenerInfo(posicion,posColumna).toString()));// sumamos los datod de
                    //las posiciones de el la matriz origina y la matriz agregada por el usuario
                    nuevaTabla.asignarCelda(posFila,posColumna,suma);
                }
            }redifinirMatriz2D(nuevaTabla);
            return true;
        }else return false;
    }

    /**
     * Suma las posiciones predeterminadas y correspondientes de una tabla con la tabla origianl
     * @param tabla
     * @return
     */
    public boolean SumarTabla(Tabla2DNumerica tabla) {
        if (tabla.renglones == renglones && tabla.columnas == columnas) {
            for (int i = 0; i < getFilas(); i++) {
                for (int j = 0; j < getColumnas(); j++) {
                    Double obtenerTabalOriginal = Double.parseDouble(obtenerInfo(i, j).toString());// obtenemos datos de la tabla original
                    Double obtenerTabal2 = Double.parseDouble(tabla.obtenerInfo(i, j).toString()); // obtenemos datos de la tabla2
                    asignarCelda(i, j, obtenerTabalOriginal + obtenerTabal2);//// sumamos posiciones
                }
            }
            return true;

        }
        return false;
    }


    /**
     * elevamos a la potencia las posiciones de la tabla con el numero a escalar que se de
     * dependiente de el usuario
     * @param escalar
     * @return
     */
    public  boolean potenciaExE(Number escalar){
        Double numeroEscalar = Double.parseDouble(escalar.toString()); // pasa el dato original a double
        if (EsNumerico(escalar.doubleValue())) {
            for (int i = 0; i < getFilas(); i++) {
                for (int j=0;j<getColumnas();j++){
                    Double Numerico_nuevo = (Math.pow(Double.parseDouble(lote[i][j].toString()),escalar.doubleValue()));//obtenemos los valores de duicha potencia
                    lote[i][j] = Numerico_nuevo;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Este metodo nos ayuda a calclar un logaritmo en base 10 o 2 de la tabla respectiva, este metodo se hace por medio de una clase enum
     * @param tipoLogaritmo
     * @return
     */
    public boolean aplicarLog(TipoLogaritmo tipoLogaritmo){
            if (tipoLogaritmo.equals(TipoLogaritmo.BASE10)){
                for (int i=0;i<renglones;i++){
                    for (int j=0;j<columnas;j++){
                        asignarCelda(i,j,(Math.log10(Double.parseDouble(obtenerInfo(i,j).toString()))));// asiganmos las celdas correspondientes con el resultado de el log
                    }
                }

            }else {
                for (int i=0;i<renglones;i++){
                    for (int j=0;j<columnas;j++){
                        asignarCelda(i,j,(Math.log(Double.parseDouble(obtenerInfo(i,j).toString()))));// asiganmos las celdas correspondientes con el resulatado de el log en base 2
                    }
                }
            }
            return false;

    }



    /**
     * Determinar la potencia de una matriz.
     * @param exponente numero natural al cual va a potenciar a la tabla actual.
     * @return true o false
     */
    public  boolean potencia(int exponente){
        if ( exponente>=1){
            for(int i=0;i<columnas;i++){
                for(int j=0;j<renglones;j++){
                    double contenidoNuevo = Math.pow(Double.parseDouble(obtenerInfo(j,i).toString()),exponente-1);// obtenemos la potencia de dicho valor las veces que se deban de repetir
                    lote[j][i]=contenidoNuevo; //asignamos el valor
                }
            }
            return true;
        }else return false;
    }

    /**
     * Este metodo nos ayuda a verificas si la tabla de matrices tiene 0s de forma diagonal superior en dicho arreflo
     * @return true o false
     */
    public  boolean  esDiagonalSup(){
        if(columnas==renglones){
            for(int a=0;a<renglones;a++){
                if(a==renglones-1){
                    if(Double.parseDouble(obtenerInfo(a-1,(renglones-a)-1).toString())==0.0 &&Double.parseDouble(obtenerInfo(a,(renglones-a)).toString())==0.0){// obteenemos las posiciones de los datos superiores de la matriz
                        return true;// en caso de que tenga seros regresa un true
                    }else{
                        return false;
                    }
                }
            }
            return false;
        }else{
            return false;
        }
    }

    /**
     * este metodo nos ayuda a  crear una matriz con un dato numerico en forma diagonal
     * @param contenido
     * @return true o flase
     */
    public boolean matrizDiagonal(Number contenido){
        if(contenido instanceof Number){
            for (int columna = 0; columna < columnas; columna++) {
                asignarCelda(columna, columna,contenido);// asigna el dato nuevo a las celdas
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * Este metodo nos ayuda a identificar si los datos son de forma diafonal inferior o no
     *
     * @return true o false
     * */
    public  boolean  esDiagonalInf(){
            if(columnas==renglones){
                for(int a=0;a<renglones;a++){
                    if(a==0){
                        if(Double.parseDouble(obtenerInfo(a,(renglones-a)-2).toString())==0.0 &&Double.parseDouble(obtenerInfo(a+1,(renglones-a)-1).toString())==0.0){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
                return false;
            }else{
                return false;
            }

    }
    /**
     * Este metodo devuelve una copia de la matriz
     * @return objeto de tipo matriz2D
     */
    public Tabla2DNumerica clonar(){
        Tabla2DNumerica copia=new Tabla2DNumerica(renglones,columnas);// damos la longitud de los renglones y las columnas
        for(int a=0;a<renglones;a++){
            for(int b=0;b<columnas;b++){
                copia.asignarCelda(a,b,lote[a][b]);// compiamos los datos que se le asignen a la celda de la matriz
            }
        }
        return copia;
    }


    /**
     * Redimensionar (sumando) una tabla2d por renglones a la mitad.
     * @return regresa true si se pudo redimensionar, en caso contrario regresa false.
     */
    public boolean doblarColumnas(){
        boolean bandera=false;
        if (renglones>=4){
            bandera=true;
            for (int posicion=0;posicion<columnas;posicion++)
                lote[1][posicion]= Double.parseDouble(lote[0][posicion].toString())+Double.parseDouble(lote[1][posicion].toString());// obtenemos las posiciones de los datos
            for (int posicion=0;posicion<columnas;posicion++)
                lote[renglones-2][posicion]= Double.parseDouble(lote[renglones-1][posicion].toString())+Double.parseDouble(lote[renglones-2][posicion].toString());
            quitarFila(TipoRenglon.INFERIOR);// quitamos el dato con la ayuda de tipoRenglon de formaInferior
            quitarFila(TipoRenglon.SUPERIOR);// quitamos los datos con la ayuda de tipoRenglon de fomra supeopr
        }else if (renglones>=2){
            bandera=true;
            for (int posicion=0;posicion<columnas;posicion++)
                lote[0][posicion]= Double.parseDouble(lote[0][posicion].toString())+Double.parseDouble(lote[1][posicion].toString());
            quitarFila(TipoRenglon.INFERIOR);
        }
        return bandera;
    }

    /**
     * Redimensionar (sumando) una tabla2d por columnas a la mitad.
     * @return regresa true si se pudo redimensionar, en caso contrario regresa false.
     */
    public boolean doblarFilas(){
        boolean bandera=false;
        if (columnas>=4){
            bandera=true;
            for (int posicion=0;posicion<renglones;posicion++)
                lote[posicion][1]= Double.parseDouble(lote[posicion][0].toString())+Double.parseDouble(lote[posicion][1].toString());
            //recorremos los datos que se encuentran en las posiciones de los renglones
            for (int posicion=0;posicion<renglones;posicion++)
                lote[posicion][columnas-2]= Double.parseDouble(lote[posicion][columnas-1].toString())+Double.parseDouble(lote[posicion][columnas-2].toString());
            quitarColuma(TipoColumna.DERECHA);// quitamos las colmnas que se encuentran de derecha
            quitarColuma(TipoColumna.IZQUIERDA);// y de izquerda tambien
        }else if (columnas>=2){// en caso de que las columnas sean mayor O IGUAL A 2
            bandera=true;
            for (int posicion=0;posicion<renglones;posicion++)
                lote[posicion][0]= Double.parseDouble(lote[posicion][0].toString())+Double.parseDouble(lote[posicion][1].toString());// al igual que los demas obtenemos los datos correspondientes
            quitarColuma(TipoColumna.DERECHA);
        }return bandera;
    }











}
