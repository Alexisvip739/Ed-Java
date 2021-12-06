package pruebas;

import Herramientas.TipoLogaritmo;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloNumerico;
import estructurasnolineales.Tabla2DNumerica;

/**
 * Clase que nos ayuda a mostrar en pantalla toda la logica que se creo respecto a la clase de tablaNumerica2d
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaTabla2DNumerico {
    public static void main(String[] args) {
        // creasion de objetos de matrices y arreglos
        Tabla2DNumerica tabla= new Tabla2DNumerica(2,2);
        Tabla2DNumerica tabla2= new Tabla2DNumerica(2,2);
        ArregloNumerico arreglo= new ArregloNumerico(4);

        //Asignacion de datos para la matriz origina de la clase tabla2dNumerica
        tabla.asignarCelda(0,0,2);
        tabla.asignarCelda(0,1,3);
        tabla.asignarCelda(0,2,4);
        tabla.asignarCelda(1,0,3);
        tabla.asignarCelda(1,1,4);
        tabla.asignarCelda(1,2,3);



        //asignacion de datos para la matriz secundaria

        tabla2.asignarCelda(0,0,9);
        tabla2.asignarCelda(0,1,3);
        tabla2.asignarCelda(1,0,6);
        tabla2.asignarCelda(1,1,3);

        //agregacion de datos para un arreglo en espesifico
        arreglo.agregar(2);
        arreglo.agregar(3);
        arreglo.agregar(2);
        arreglo.agregar(3);

        tabla.sumarEscalar(5);
        tabla.imprimirC();








    }
}
