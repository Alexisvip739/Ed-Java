package pruebas;

import entradasalida.SalidaTerminal;
import herramientas.matematicas.ExpresionAritmetica;

/**
 *clase en donde se implementa el uso de  las expreciones aritmeticas
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 *
 */
public class PruebaExpresionA {

    public static void main(String[] args) {
        String operacion=ExpresionAritmetica.pasarEInfijaAPrefija("A+B*C");
        SalidaTerminal.consola(operacion+"\n");

    }
}
