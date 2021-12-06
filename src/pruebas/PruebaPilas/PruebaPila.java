package pruebas;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloPila;

public class PruebaPila {
    public static void main(String argumentos[]){
        ArregloPila pila=new ArregloPila(3);

        pila.poner("A");
        pila.poner("B");
        pila.poner("C");

        pila.imprimir();

        SalidaTerminal.consola("\n");

        SalidaTerminal.consola("Sacando de la pila: " + pila.quitar()+ "\n");

        pila.imprimir();

    }
}
