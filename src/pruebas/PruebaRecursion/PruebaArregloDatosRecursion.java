package pruebas.PruebaRecursion;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;

/**
 * clase que implementa toda la funcionalidad de los metodos recursivos de arreglo dato
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaArregloDatosRecursion {
    public static void main(String[] args) {
        ArregloDatos arreglo = new ArregloDatos(4);
        arreglo.agregar("F");
        arreglo.agregar("B");
        arreglo.agregar("C");
        arreglo.agregar("D");


        ArregloDatos arreglo2 = new ArregloDatos(4);
        arreglo2.agregar("A");
        arreglo2.agregar("A");
        arreglo2.agregar("A");
        arreglo2.agregar("L");

        ArregloDatos arreglo3 = new ArregloDatos(4);
        arreglo3.agregar("A");
        arreglo3.agregar("A");
        arreglo3.agregar("D");
        arreglo3.agregar("E");



        arreglo.imprimir();





    }
}
