package estructurasnolineales;


import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloPila;
import estructuraslineales.registros.NodoDoble;

/**
 * clase que crea un arbol con expresiones
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class ArbolBinarioExpresion extends ArbolBinario {


    /**
     * Metodo que nos permite poder crear un arbol mediante las expresiones algebraicas
     * verificando de que existan parentesis
     * @param expresion
     */
    public void crearArbolPrioridad(String expresion){
        ArregloPila PilaExpresion = new ArregloPila(expresion.length());// creamos la pila

      if(expresion.charAt(0)=='('){// en caso contrario , generaremos el arbol
            NodoDoble nodoActual = new NodoDoble("");
            raiz=nodoActual;
            crearPrioridad(expresion, 0, raiz, PilaExpresion);
      }else{
          SalidaTerminal.consola("expresion no valida");
      }
    }

    /**
     * metodo que crea todo el arbol de  priorodad recursivamente
     * @param expresion
     * @param limite
     * @param NodoRiz
     * @param PilaExpresion
     */

    private void crearPrioridad(String expresion, int limite, NodoDoble NodoRiz, ArregloPila PilaExpresion){
        if(limite < expresion.length()) {// verificamos que este en limite
            char token = expresion.charAt(limite);// obtenemos posicion por posicion los datos
            if (token == '(') {// si la primera posicion es parentecis o la posicion que sea
                NodoDoble nuevoNodo = new NodoDoble(token);// creamos  un nodo con ese dato
                if (NodoRiz.getDirMemIzq() == null) {// si el siguiente es null
                    NodoRiz.setDirMemIzq(nuevoNodo);// asignamos null
                } else {
                    NodoRiz.setDirMemDer(nuevoNodo);
                }
                NodoRiz = nuevoNodo;// asignamos un nuevo nodo con valor
                if (NodoRiz.getDato() != null) {
                    PilaExpresion.poner(nuevoNodo);// lo agregamos a la pila el dato
                }
            } else if (herramientas.matematicas.ExpresionAritmetica.esOperando(token) && token != ' ' && token != '(') {// si es operando
                NodoDoble nuevoNodo = new NodoDoble(token);// creamos el nodo
                if (nuevoNodo != null) {
                    if (NodoRiz.getDirMemIzq() == null) {
                        NodoRiz.setDirMemIzq(nuevoNodo);// asignamos null
                    } else {
                        NodoRiz.setDirMemDer(nuevoNodo);
                    }
                }
            } else if (!herramientas.matematicas.ExpresionAritmetica.esOperando(token) && token != ')' && token != ' ') {// si no es operando
                Object elemento = PilaExpresion.quitar();// lo quitamos de la pila de arreglos
                NodoRiz = (NodoDoble) elemento;// creamos un nodo con ese dato
                if (NodoRiz != null) {
                    NodoRiz.setDato(token);// establecemos a el nodo el token seleccionado
                }
            }
            crearPrioridad(expresion, limite + 1, NodoRiz, PilaExpresion);// una vez recorrido todo generamos el metodo recursivo para que vaya recorriendo toda la expresion
        }
    }

    /**
     * metodo privado que nos ayuda a imprimir los datos de el arbol de una forma
     * in orden
     */
    public void inOrden(){
        inOrden(raiz);
    }

    private void inOrden(NodoDoble subRaiz){
        //IRD (izquierda, raiz, derecha)
        if(subRaiz!=null){ //hay alguna subRaiz válida
            //I
            inOrden(subRaiz.getDirMemIzq());
            //R
            SalidaTerminal.consola(subRaiz.getDato() + " ");
            //D
            inOrden(subRaiz.getDirMemDer());
        } //en caso contrario, es deci la subRaiz es nula, no hacemos nada, CASO BASE
    }
}
