package pruebas;

import Herramientas.TipoTabla;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ListaEncadenada;
import estructurasnolineales.Tabla2D;

/**
 * clase que nos ayuda a poder mandar llamar los metodos para asi poder mostrarlos en la pantalla
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaListaEncadenada {
    public static void main(String[] argumentos) {
        //creamos un objeto de tipo lista, arreglo y matriz para poder implementar algunos metodos
        ListaEncadenada lista = new ListaEncadenada();
        ArregloDatos arreglo= new ArregloDatos(4);
        Tabla2D tabla2D= new Tabla2D(2,2);
        tabla2D.asignarCelda(0,0,"A");
        tabla2D.asignarCelda(0,1,"B");
        tabla2D.asignarCelda(1,0,"C");
        tabla2D.asignarCelda(1,1,"D");
        arreglo.agregar("P");
        arreglo.agregar("H");
        arreglo.agregar("Q");
        arreglo.agregar("L");

        lista.agregar("H");
        lista.agregar("Z");
        lista.agregar("M");
        lista.agregar("A");



        SalidaTerminal.consola(lista.agregarLista(arreglo)+"\n");
        lista.imprimir();














    }
}
