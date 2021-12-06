package pruebas;

import entradasalida.SalidaTerminal;
import estructuraslineales.PilaListaEncadenada;


/** clase en donde mandamos llamar a el uso de las pilas ligadas
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaPilaEncadenada {
    public static void main(String[] args) {

        PilaListaEncadenada pilaLista= new PilaListaEncadenada();
        PilaListaEncadenada pilaLista2= new PilaListaEncadenada();
        pilaLista2.poner("90");
        pilaLista2.poner("+");
        pilaLista2.poner("h");
        pilaLista2.poner("*");
        pilaLista2.poner("(");
        pilaLista2.poner("h");
        pilaLista2.poner("-");
        pilaLista2.poner("2");
        pilaLista2.poner(")");
        pilaLista2.poner("+");
        pilaLista2.poner("(");
        pilaLista2.poner("(");
        pilaLista2.poner("a");
        pilaLista2.poner("/");
        pilaLista2.poner("b");
        pilaLista2.poner(")");
        pilaLista2.poner("-");
        pilaLista2.poner("c");
        pilaLista2.poner(")");

        SalidaTerminal.consola(pilaLista.poner("A")+"\n");
        SalidaTerminal.consola(pilaLista.poner("B")+"\n");
        SalidaTerminal.consola(pilaLista.poner("C")+"\n");



    }
}
