package Herramientas.matematicas.recursionMatematica;

import entradasalida.SalidaTerminal;
import estructuraslineales.ListaEncadenada;

/**
 * clase recursiva que hace operaciones matematicas dependiendo de su caso que se pida
 * @author Alexis Ulteras Sotelo
 * @version 1.0
 */
public class recursion{

    /**
     * metodo que nos ayuda a multiplicar dos numero por medio de sumas
     * @param a
     * @param b
     * @return (a+multiplicacion(a,b-1)) resultado que se obtiene de esa suma al retirmar la recursino
     */
    public static int multiplicacion(int a, int b){
        if(b==0)
            return 0;
        else{
            return (a+multiplicacion(a,b-1));
        }
    }

    /**
     * metodo que nos ayuda a poder calcular el resultado de la serie
     * @param x
     * @param n
     * @param m
     * @return calcularSerie(lista, Double.valueOf(String.valueOf(lista.obtener(1))), 1) resultado de la ecuacion final
     */
    public static  double calcularSerie(int x, int n, int m){
        ListaEncadenada lista = new ListaEncadenada();
        lista.agregar(1);
        lista.agregar('-');// agregamos el char
        lista = generarSerie(x,n,m,1,lista, '-');
        return calcularSerie(lista, Double.valueOf(String.valueOf(lista.obtener(1))), 1);//
    }

    /**
     * calcula los datos que estan en la lista ligada  con los numeros dentro
     * @param lista
     * @param resultados
     * @param contador
     * @return calcularSerie(lista, resultados, contador+2) calculamos cada y uno de los datos
     */
    private static double calcularSerie(ListaEncadenada lista, double resultados, int contador){
        if(contador== lista.numElementos()){// verificamos las longitudes de los datos
            return resultados;
        }else{
            char obtenerChar = (char) lista.obtener(contador+1);// obtenermos la posicoin de la  lista
            if(obtenerChar == '-'){
                resultados = resultados -(double)lista.obtener(contador+2);
            }else{
                resultados = resultados +(double) lista.obtener(contador+2);
            }
            return calcularSerie(lista, resultados, contador+2);//  generamos el metodo recursivo
        }
    }

    /**
     * metodo privado que genera la serie que se encuentra en la lista de datos
     * @param x
     * @param n
     * @param m
     * @param fac
     * @param lista
     * @param s
     * @return
     */
    private static  ListaEncadenada generarSerie(int x, int n, int m, int fac, ListaEncadenada lista, char s){
        if(fac > n){
            Double resultadoDouble = Double.valueOf(((double) n/ (double)m) + n);// generamos la operacion asignada
            lista.agregar(resultadoDouble);// agregamos a la lista ligada el resultado de esa operacion
            return lista;
        }else{
            Double resultadoOperacionFac = (Double) (Math.pow(x, fac)/factorial(fac));// generamos el factorial de el dato y lo elevamos a la potencia
            fac+=2;
            lista.agregar(resultadoOperacionFac);
            char temp = 'x';
            if(s == '-'){
                temp = '+';
                lista.agregar('+');
            }else{
                temp = '-';
                lista.agregar('-');
            }
            return generarSerie(x,n,m,fac,lista, temp);// generamos el metodo recursivo
        }
    }

    /**
     * metodo que nos ayuda a obtener el factorial de un numero determinado
     * @param num
     * @return FactorialRR regresamos el dato que esta en el metodo
     */
    private static int factorial(int num){
        return FactorialRR(num, num);
    }


    /**
     * metodo recursivo que genera el resultado de el fatocria
     * @param fact
     * @param num
     * @return
     */
    private static  int FactorialRR(int fact, int num){
        if(num == 1){
            return fact;
        }else{
            num = num -1;// quiramos un dato de el numero
            fact = fact * num;// multiplicamos ese numero
            return FactorialRR(fact, num);
        }
    }



    /**
     * metodo  donde convierte un valor normal, en base 10 a un valor en hexadecimal con recurcion  de los datos
     * @param info
     * @return Hexadecimal(info / 16, getChar(info % 16) + hexa) (la letra solicitada jungo con su String
     */
    public String Hexadecimal(int info){
        return HexadecimalRR(info," ");
    }
    public String HexadecimalRR(int info, String hexa) {
        if (info == 0) {
            return hexa;
        } else {
            return HexadecimalRR(info / 16, getChar(info % 16) + hexa);
        }
    }




    /**
     * metodo que hace la Factorial de el numero que se le ingrese como parametro
     *
     * @param parametro
     */
    public static void DecimalABinario(int parametro) {
        if (parametro<2){
            SalidaTerminal.consola(parametro+" ");
            return;// este return nos ayuda a detener la ejecucion
        }else {
            DecimalABinario(parametro/2);// mandamos llamar el metodo recursivo
            SalidaTerminal.consola(parametro%2+" ");// sacamos el modulo de el parametro de 2
        }
    }
    /**
     * metodo que se manda llamar al momento de hacer recurcion en el metodo Hexadecimal
     * Concatena el valor necesario al sacar el modulo 16 de la info
     * @param numero
     * @return datosHexa en la posicion solicitada de el numero
     */
    private char getChar(int numero) {
        char datosHexa[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return datosHexa[numero];
    }

    /**
     * Este metodo obtiene el MCD de los datos pasados por parametro, donde regresa el mcd mas chico que tiene
     *
     * @param a
     * @param b
     * @return b y a junto con su modulo
     */

    public int obtenerMCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return obtenerMCD(b, a % b);// OBTENEMOS EL MODULO DE A Y B
        }

    }












}
