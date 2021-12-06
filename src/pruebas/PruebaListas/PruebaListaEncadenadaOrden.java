package pruebas;

import Herramientas.TipoOrden;
import Herramientas.TipoTabla;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ListaEncadenadaOrden;
import estructurasnolineales.Tabla2D;

/**
 * clase que  muestra la funcionalidad de la clase lista encadenada orden.
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaListaEncadenadaOrden {
    public static void main(String[] args) {
        ListaEncadenadaOrden listaEncadenadaOrden = new ListaEncadenadaOrden(TipoOrden.Ordenado);
        ListaEncadenadaOrden listaEncadenadaOrden2 = new ListaEncadenadaOrden(TipoOrden.Ordenado);
        ListaEncadenadaOrden listaEncadenadaOrden3 = new ListaEncadenadaOrden(TipoOrden.Ordenado);
        ArregloDatos arregloDatos= new ArregloDatos(4);
        Tabla2D tabla = new Tabla2D(2,2);

        tabla.asignarCelda(0,0,"F");
        tabla.asignarCelda(0,1,"Z");
        tabla.asignarCelda(1,0,"D");
        tabla.asignarCelda(1,1,"E");

        arregloDatos.agregar("H");
        arregloDatos.agregar("I");
        arregloDatos.agregar("O");
        arregloDatos.agregar("M");

        listaEncadenadaOrden.agregar("L");
        listaEncadenadaOrden.agregar("I");
        listaEncadenadaOrden.agregar("A");
        listaEncadenadaOrden.agregar("B");



        listaEncadenadaOrden2.agregar("H");
        listaEncadenadaOrden2.agregar("P");
        listaEncadenadaOrden2.agregar("A");
        listaEncadenadaOrden2.agregar("Z");

        listaEncadenadaOrden3.agregar("L");
        listaEncadenadaOrden3.agregar("N");
        listaEncadenadaOrden3.agregar("D");
        listaEncadenadaOrden3.agregar("X");

        listaEncadenadaOrden2.clonar();
        listaEncadenadaOrden2.imprimir();





    }
}
