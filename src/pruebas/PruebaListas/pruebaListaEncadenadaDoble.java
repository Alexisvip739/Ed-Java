package pruebas;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ListaEncadenadaDoble;

/**
 * clase que muestra en pantalla todos los metodos de la clase de lista encadenada Doble
    @author Alexis Ultreras Sotelo
    @version 1.0
 */
public class pruebaListaEncadenadaDoble {
    public static void main(String[] args) {
        ListaEncadenadaDoble lista= new ListaEncadenadaDoble();
        ArregloDatos arreglo= new ArregloDatos(5);
        arreglo.agregar("F");
        arreglo.agregar("G");
        arreglo.agregar("H");
        arreglo.agregar("I");
        arreglo.agregar("J");
        arreglo.agregar("K");
        lista.agregar("A");
        lista.agregar("A");
        lista.agregar("C");
        lista.agregar("D");

        ListaEncadenadaDoble lista2= new ListaEncadenadaDoble();


        lista2.agregar("A");
        lista2.agregar("B");
        lista2.agregar("H");

        ListaEncadenadaDoble lista3= new ListaEncadenadaDoble();


        lista3.agregar("F");
        lista3.agregar("D");
        lista3.agregar("A");








    }
}
