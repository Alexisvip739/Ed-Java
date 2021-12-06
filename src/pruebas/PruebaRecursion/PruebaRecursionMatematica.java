package pruebas.PruebaRecursion;

import Herramientas.matematicas.recursionMatematica.recursion;
import entradasalida.SalidaTerminal;

/**
 * clase prueba que nos ayuda a poder obtener la informacion o el resultado de cada uno de esos metodos creados recursivos
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaRecursionMatematica {
    public static void main(String[] args) {
        recursion rec= new recursion();
        SalidaTerminal.consola(rec.obtenerMCD(412,184)+"\n");
        rec.Hexadecimal(150);
        rec.DecimalABinario(39250);
    }
}
