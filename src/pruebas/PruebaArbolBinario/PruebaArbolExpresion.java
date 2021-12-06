package pruebas.PruebaArbolExpresion;

import estructurasnolineales.ArbolBinarioExpresion;

/**
 * Clase  de pruba que muestra el funcionamiento de la creacion de el arbol con expresiones
 */
public class PruebaArbolExpresion {
    public static void main(String[] args) {
        ArbolBinarioExpresion arbol= new ArbolBinarioExpresion();
        arbol.crearArbolPrioridad("((a+e)*(b+c))");
        arbol.inOrden();
    }
}
