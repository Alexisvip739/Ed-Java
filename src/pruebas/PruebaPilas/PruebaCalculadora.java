package pruebas;

import Herramientas.matematicas.CalculadoraPila;
import entradasalida.SalidaTerminal;

/**
 * clase en donde se hace el llamado a la clase calculadora para poder obtener el resultado de una
 * exprecion aritmetica dada
 *
 * @author Alexis Ulteras Sotelo
 * @version 1.0
 */
public class PruebaCalculadora {
    public static void main(String args[]){
        CalculadoraPila calculadora = new CalculadoraPila("(a-2)*y/2^(sumar-9)");
        SalidaTerminal.consola("Operacion: '(a-2)*y/2^(sumar-9)' es: "+ calculadora.calcularExpresion()+"\n");
    }
}
