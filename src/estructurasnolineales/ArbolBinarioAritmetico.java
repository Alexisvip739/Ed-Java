package estructurasnolineales;

import entradasalida.EntradaTerminal;
import entradasalida.SalidaTerminal;
import estructuraslineales.ListaEncadenada;
import estructuraslineales.registros.NodoDoble;

/**
 * CLASE QUE NOS AYUDA A PODER DAR REPRESENTACION Y CREAR UN ARBOL BINARIO CON EXPRECIONES ARITMETICAS
 * @version 1.0
 * @author Alexis Ultreras Sotelo
 */
public class ArbolBinarioAritmetico extends  ArbolBinario{
    protected ListaEncadenada lista;
    protected ArbolBinario arbol;

    /**
     * Constructor.
     */
    public void ArbolBinarioAritmetico(){
        arbol = new ArbolBinario();
        arbol.crearArbol();
    }

    /**
     * metodo que imprime los datos en forma inOrden
     */
    public void inOrden(){
        inOrden(raiz);
    }

    private void inOrden(NodoDoble subRaiz){
        //IRD
        if(subRaiz!=null){ //hay alguna subRaiz válida
            //I
            inOrden(subRaiz.getDirMemIzq());
            //R
            SalidaTerminal.consola(subRaiz.getDato() + " ");
            //D
            inOrden(subRaiz.getDirMemDer());
        } //en caso contrario, es deci la subRaiz es nula, no hacemos nada, CASO BASE
    }

    /**
     * metodo que puede imprimir de forma recursiva los datos en pre Orden
     */
    public void preOrden(){
        preOrden(raiz);
    }

    private void preOrden(NodoDoble subRaiz){
        //RID
        if(subRaiz!=null){ //hay alguna subRaiz válida
            //R
            SalidaTerminal.consola(subRaiz.getDato() + " ");
            //I
            preOrden(subRaiz.getDirMemIzq());
            //D
            preOrden(subRaiz.getDirMemDer());
        } //en caso contrario, es deci la subRaiz es nula, no hacemos nada, CASO BASE
    }

    /**
     * metodo que nos ayuda a poder imprimir los datos recursivos en postOrden
     */
    public void postOrden(){
        postOrden(raiz);
    }
    private void postOrden(NodoDoble subRaiz){
        //IDR
        if(subRaiz!=null){ //hay alguna subRaiz válida
            //I
            postOrden(subRaiz.getDirMemIzq());
            //D
            postOrden(subRaiz.getDirMemDer());
            //R
            SalidaTerminal.consola(subRaiz.getDato() + " ");
        } //en caso contrario, es deci la subRaiz es nula, no hacemos nada, CASO BASE
    }

    /**
     * Este metodo recorre el arbol binario y devuelve los operadores que contenga
     * @return lista ligada con los operadores
     */
    public ListaEncadenada obtenerOperadores(){
        lista=new ListaEncadenada();// creamos nuestra lista
        return obtenerOperadores1(raiz,lista);// la asignamos a el metodo recursivo
    }

    /**
     * Este metodo Recursivo recorre el arbol binario y devuelve una lista con los operadores
     * @param subRaiz raiz
     * @param lista lista con los elementos
     * @return lista de los elementos
     */
    private ListaEncadenada obtenerOperadores1(NodoDoble subRaiz, ListaEncadenada lista){
        //IRD
        if(subRaiz!=null) {
            obtenerOperadores1(subRaiz.getDirMemIzq(),lista);// seguimos avanzando
            obtenerOperadores1(subRaiz.getDirMemDer(),lista); // seguimos avanzando
            if(!herramientas.matematicas.ExpresionAritmetica.esOperando(subRaiz.getDato().toString().charAt(0)))lista.agregar(subRaiz.getDato());
            // en caso de que cada posicion de los datos no sea un operando entonces asignaremos los operadores en la lista
            return lista;
        } else{
            return null;
        }
    }

    /**
     * Este metodo recorre el arbol binario y devuelve los operandos que contenga
     * @return lista ligada con los operadores
     */
    public ListaEncadenada obtenerOperandos(){
        ListaEncadenada lista=new ListaEncadenada();// asignamos una nueva lista de datos
        return obtenerOperandos1(raiz,lista);// mandamos llamar el metodo recursivo raiz
    }
    /**
     * Este metodo Recursivo recorre el arbol binario y devuelve una lista con los operandos
     * @param subRaiz raiz
     * @param lista lista con los elementos
     * @return lista de los elementos
     */
    private ListaEncadenada obtenerOperandos1(NodoDoble subRaiz,ListaEncadenada lista){
        if(subRaiz!=null) {
            obtenerOperandos1(subRaiz.getDirMemIzq(),lista);// seguimos avanzando
            obtenerOperandos1(subRaiz.getDirMemDer(),lista);// seguimos avanzando

            if(herramientas.matematicas.ExpresionAritmetica.esOperando(subRaiz.getDato().toString().charAt(0)))lista.agregar(subRaiz.getDato());
                // en caso de que ahora los datos sean un operando entonces los guardamos en la lista de datos para posteriormente
            // poder imprimirlos
            return lista;
        } else{
            return null;
        }
    }



    /**
     * Método que permite sustituir las variables a valores numéricos en el árbol.
     */
    public void Sustituir(){
        Sustituir(raiz);
    }

    /**
     *Método privado que sustituye las variables por valores numéricos.
     * @param subRaiz raíz con la información existente.
     */
    private void Sustituir(NodoDoble subRaiz){
        if(subRaiz!=null) {
            String sRaiz = String.valueOf(subRaiz);
            char suRaiz =sRaiz.charAt(0);// obtenemos el elemento que se va a cambiar
            if( herramientas.matematicas.ExpresionAritmetica.esOperando(suRaiz)){// si es un operando se puede cambiar
                SalidaTerminal.consola("Dame la información de la raíz "+subRaiz.getDato()+": ");// asignamos el dato
                String info= EntradaTerminal.consolaCadena();// obtenemos el dato
                subRaiz.setDato(info);// establecemos el dato
            }
            Sustituir(subRaiz.getDirMemIzq());// recorremos los datos para que busque nuevamente
            Sustituir(subRaiz.getDirMemDer());
        } //sería nulo, no hago nada
    }




}
