package pruebas.PruebaGrafoMatriz;

import entradasalida.SalidaTerminal;
import estructurasnolineales.GrafoMatriz;

/**
 * Clase pruerba en donde verificamos el uso de los pesos minimos dentro de el grafo predeterminado
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaAlgoritmoKruskal {
    public static void main(String[] args) {
        GrafoMatriz grafo=new GrafoMatriz(5);
        grafo.agregarVertice("1");
        grafo.agregarVertice("2");
        grafo.agregarVertice("3");
        grafo.agregarVertice("4");
        grafo.agregarVertice("5");

        grafo.agregarArista("1","2",1.0);

        grafo.agregarArista("1","3",3.0);

        grafo.agregarArista("2","1",1.0);
        grafo.agregarArista("2","3",4.0);
        grafo.agregarArista("2","4",6.0);

        grafo.agregarArista("3","1",3.0);
        grafo.agregarArista("3","2",4.0);
        grafo.agregarArista("3","4",4.0);
        grafo.agregarArista("3","5",2.0);

        grafo.agregarArista("4","3",4.0);
        grafo.agregarArista("4","2",6.0);
        grafo.agregarArista("4","5",5.0);

        grafo.agregarArista("5","3",2.0);
        grafo.agregarArista("5","4",5.0);


        grafo.imprimir();

        SalidaTerminal.consola("\n");
        grafo.grafoMinimo("1").imprimir();


    }
}
