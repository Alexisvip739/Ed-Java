package pruebas.GrafoListaA;

import entradasalida.SalidaTerminal;
import estructurasnolineales.GrafoListaAdyacencia;

/**
 * clase que nus ayuda a poder obtener la informacion mostrada de la clase grafoListaAdyacente
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaGrafoLA {
    public  static  void main(String args[]){
        GrafoListaAdyacencia grafo= new GrafoListaAdyacencia();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");

        grafo.agregarArista("A","B");
        grafo.agregarArista("A","E");

        grafo.agregarArista("B","D");
        grafo.agregarArista("B","E");

        grafo.agregarArista("C","A");

        grafo.agregarArista("E","C");
        grafo.agregarArista("E","D");

        grafo.componentesConexosGrafo();


    }
}
