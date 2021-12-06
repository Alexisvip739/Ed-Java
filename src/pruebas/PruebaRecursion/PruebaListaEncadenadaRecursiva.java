package pruebas.PruebaRecursion;

import Herramientas.TipoTabla;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ListaEncadenada;
import estructurasnolineales.Tabla2D;

/**
 * clase que muestra el reslutado de todos los metodos de lista ligada recursivo
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaListaEncadenadaRecursiva {
    public static void main(String[] args) {
        ListaEncadenada lista = new ListaEncadenada();
        ListaEncadenada lista2 = new ListaEncadenada();
        ArregloDatos arreglo = new ArregloDatos(4);
        Tabla2D tabla2D = new Tabla2D(2, 2);
        tabla2D.asignarCelda(0,0,"A");
        tabla2D.asignarCelda(0,1,"B");
        tabla2D.asignarCelda(1,0,"C");
        tabla2D.asignarCelda(1,1,"D");
        arreglo.agregar("W");
        arreglo.agregar("H");
        arreglo.agregar("Q");
        arreglo.agregar("L");

        lista.agregar("P");
        lista.agregar("A");
        lista.agregar("H");
        lista.agregar("H");
        lista.agregar("W");
        lista.agregar("X");

        lista2.agregar("P");
        lista2.agregar("L");
        lista2.agregar("H");
        lista2.agregar("L");
        lista2.agregar("W");
        lista2.agregar("X");

        lista.invertirRR();
       lista.imprimir();



    }

}

