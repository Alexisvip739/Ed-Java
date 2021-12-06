package pruebas;

import entradasalida.SalidaTerminal;
import estructuraslineales.ListaEncadenada;
import estructuraslineales.ListaEncadenadaHash;
import estructurasnolineales.ArbolBinario;
import estructurasnolineales.ArbolBinarioAritmetico;
/**
 * Clase que nos ayuda a poder mandar llamara cada uno de los metodos funcionales de arbol binario y arbol aritmetico
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 *
 */
public class PruebaArbolBinario {

    public static  void  main(String args[]) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.crearArbol();
        arbol.inOrden();
        SalidaTerminal.consola(" \n");
        arbol.tipoNodo("D");

       /**
        *   ArbolBinarioAritmetico arbol2= new ArbolBinarioAritmetico();
        *        arbol2.crearArbol();
        *        arbol2.Sustituir();
        *        SalidaTerminal.consola("\n");
        *        arbol2.inOrden();
        *        SalidaTerminal.consola(" \n");
        *        arbol2.postOrden();
        *        SalidaTerminal.consola(" \n");
        *        arbol2.preOrden();
        *        SalidaTerminal.consola(" \n");
        *        ListaEncadenada lista=arbol2.obtenerOperadores();
        *        ListaEncadenada list2=arbol2.obtenerOperandos();
        *        lista.imprimir();
        *        SalidaTerminal.consola("\n");
        *        list2.imprimir();
        */
       

    }
}
