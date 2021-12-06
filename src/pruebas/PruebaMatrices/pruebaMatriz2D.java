package pruebas;

import Herramientas.TipoColumna;
import Herramientas.TipoRenglon;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructurasnolineales.Tabla2D;

/**
 * clase que nos ayuda a obtener toda la inforacino de la clase original y mostrarla en pantalla
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class pruebaMatriz2D {
    public static void main(String[] args) {
        Tabla2D matriz = new Tabla2D(4,4);// creamos un objeto de tipo tabla
        matriz.asignarCelda(0,0,"A");// asiganmos los datos en la posicion que queramos que se establezcan
        matriz.asignarCelda(0,1,"B");
        matriz.asignarCelda(0,2,"C");
        matriz.asignarCelda(0,3,"D");
        matriz.asignarCelda(1,0,"E");
        matriz.asignarCelda(1,1,"F");
        matriz.asignarCelda(1,2,"G");
        matriz.asignarCelda(1,3,"H");
        matriz.asignarCelda(2,0,"I");
        matriz.asignarCelda(2,1,"J");
        matriz.asignarCelda(2,2,"K");
        matriz.asignarCelda(2,3,"L");
        matriz.asignarCelda(3,0,"M");
        matriz.asignarCelda(3,1,"N");
        matriz.asignarCelda(3,2,"O");
        matriz.asignarCelda(3,3,"P");


        //creamos un arreglo con capasidad 4
        ArregloDatos arreglo=  new ArregloDatos(4);
        arreglo.agregar("1");// asignamos datos de el arreglo actual
        arreglo.agregar("2");
        arreglo.agregar("3");
        arreglo.agregar("4");
        Tabla2D matriz2 = new Tabla2D(2,2);
        matriz2.asignarCelda(0,0,"A");
        matriz2.asignarCelda(0,1,"B");
        matriz2.asignarCelda(1,0,"C");
        matriz2.asignarCelda(1,1,"D");

        matriz.imprimirC();







    }
}
