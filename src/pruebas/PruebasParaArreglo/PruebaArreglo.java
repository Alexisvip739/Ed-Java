package pruebas;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;

/**
 * Clase prueba en donde se mandan llamar todos los metodos de la clase ArregloDatos
 */
public class PruebaArreglo {
    public static void main(String argumentos[]){


        ArregloDatos arreglo1=new ArregloDatos(4);
        ArregloDatos arreglo2=new ArregloDatos(4);

        arreglo1.agregar("A");
        arreglo1.agregar("A");
        arreglo1.agregar("A");
        arreglo1.agregar("D");

        arreglo2.agregar("E");
        arreglo2.agregar("F");
        arreglo2.agregar("G");
        arreglo2.agregar("H");

        SalidaTerminal.consola(arreglo1.insertar(2,"H")+"\n");
        arreglo1.imprimir();




    }
}
