package pruebas;

import Herramientas.TipoOrden;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArreglOrdenado;

/**
 * Clase prueba que implementa todo de ArregloOrdenado para mostrarlo en pantalla
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaArregloOrdenado {
    public static void main(String[] args) {

        ArreglOrdenado arreglOrdenado= new ArreglOrdenado(5, TipoOrden.Desordenado );
        ArreglOrdenado arreglOrdenado2= new ArreglOrdenado(5, TipoOrden.Ordenado);
        ArreglOrdenado subLista= new ArreglOrdenado(5, TipoOrden.Ordenado);
        ArreglOrdenado arreglNumerico= new ArreglOrdenado(6, TipoOrden.Ordenado);
//AGREGAMOS DATOS PARA CADA UNO DE LOS ARREGLOS  CON SU RESPECTIVO ORDEN CREADO
        arreglOrdenado.agregar("A");
        arreglOrdenado.agregar("B");
        arreglOrdenado.agregar("Z");
        arreglOrdenado.agregar("L");
        arreglOrdenado.agregar("E");


        arreglOrdenado2.agregar("A");
        arreglOrdenado2.agregar("B");
        arreglOrdenado2.agregar("Z");
        arreglOrdenado2.agregar("T");


        subLista.agregar("Z");
        subLista.agregar("M");
        subLista.agregar("I");
        subLista.agregar("F");

        arreglNumerico.agregar(1);
        arreglNumerico.agregar(3);
        arreglNumerico.agregar(4);
        arreglNumerico.agregar(2);
        /**
         * aqui se pueden hacer las validaciones necesarias para saber si los datos son correctos y muestran lo correcto
         */
        SalidaTerminal.consola("Eliminar B: "+ arreglOrdenado.esSublista(subLista)+"\n");
        SalidaTerminal.consola("\n");
        arreglOrdenado.imprimir();
    }
}
