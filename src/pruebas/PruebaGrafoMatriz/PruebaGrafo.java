package pruebas.PruebaGrafoMatriz;

import entradasalida.SalidaTerminal;
import estructurasnolineales.GrafoMatriz;

/**
 * clase que nos ayuda a mostrar el resultado de cada uno de los metodos creados para la
 * funcionalidad de grafos matriz
 * @author Alexis Ultreras Sotelo
 * @version 2.0
 */
public class PruebaGrafo {
    public  static void  main(String[] argumentos){
        GrafoMatriz grafo1= new GrafoMatriz(5);

        //aristas que representarn los vertices de el grafo matriz
        grafo1.agregarVertice("1");
        grafo1.agregarVertice("2");
        grafo1.agregarVertice("3");
        grafo1.agregarVertice("4");
        grafo1.agregarVertice("5");
        // aristas que son conexas
        grafo1.agregarArista("1","4");
        grafo1.agregarArista("1","2");
        grafo1.agregarArista("4","1");
        grafo1.agregarArista("4","5");
        grafo1.agregarArista("5","4");
        grafo1.agregarArista("5","2");
        grafo1.agregarArista("2","1");
        grafo1.agregarArista("2","3");
        grafo1.agregarArista("3","2");



        grafo1.imprimir();
        grafo1.eliminarVertice("3");




    }
}
