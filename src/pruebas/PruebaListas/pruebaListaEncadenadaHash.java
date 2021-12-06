package pruebas;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ListaEncadenada;
import estructuraslineales.ListaEncadenadaHash;
import estructurasnolineales.Tabla2D;

/**
 * Clase que nos ayuda a representar los metodos mostrados en pantalla que corresonden  una lista de tipo hash
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class pruebaListaEncadenadaHash {
    public static void main(String[] args) {
        ListaEncadenadaHash listaHash= new ListaEncadenadaHash();
        ListaEncadenada listaEncadenada= new ListaEncadenada();
        ListaEncadenada listaEncadenada2= new ListaEncadenada();
        Tabla2D tabla2D= new Tabla2D(2,2);





        listaEncadenada.agregar("T");
        listaEncadenada.agregar("Z");
        listaEncadenada.agregar("M");


        listaEncadenada2.agregar(55);
        listaEncadenada2.agregar(35);
        listaEncadenada2.agregar(45);
        ArregloDatos arregloClave = new ArregloDatos(2);
        ArregloDatos arregloValor= new ArregloDatos(2);

        arregloClave.agregar("T");
        arregloClave.agregar("Z");
        arregloClave.agregar("M");

        arregloValor.agregar(66);
        arregloValor.agregar(86);
        arregloValor.agregar(96);
        listaHash.insertar("A",4);
        listaHash.insertar("B",9);
        listaHash.insertar("C",3);
        listaHash.insertar("D",2);
        ListaEncadenadaHash listaHash2= new ListaEncadenadaHash();

        listaHash2.insertar("A",7);
        listaHash2.insertar("G",9);
        listaHash2.insertar("H",3);
        listaHash2.insertar("I",2);
        tabla2D.asignarCelda(0,0,"Q");
        tabla2D.asignarCelda(0,1,"W");
        tabla2D.asignarCelda(1,0,"R");
        tabla2D.asignarCelda(1,1,"T");

        listaHash.agregarTabla(tabla2D);
        listaHash.imprimir();



    }
}
