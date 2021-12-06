package pruebas;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ArregloNumerico;

/**
 * clase que permite mostrar el resultado de cada  uno de los metodos implementados en la clase de Arreglo Numerico
 * @author Alexis Ultreas Soltelo
 * @version  1.0
 */
public class PruebaArregloNumerico {
    public static void main(String[] args) {
        ArregloNumerico arreglo= new ArregloNumerico(3);
        ArregloNumerico arreglo2= new ArregloNumerico(3);
        ArregloDatos arregloNumerico= new ArregloDatos(3);

        arregloNumerico.agregar(5);
        arregloNumerico.agregar(6);
        arregloNumerico.agregar(10);


        arreglo.agregar(9);
        arreglo.agregar(5);
        arreglo.agregar(3);

        arreglo2.agregar(2);
        arreglo2.agregar(9);
        arreglo2.agregar(7);


        SalidaTerminal.consola(arreglo.sumarEscalar(arreglo2)+"\n");




    }
}
