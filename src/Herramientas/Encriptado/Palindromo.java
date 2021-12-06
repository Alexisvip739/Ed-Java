package Herramientas.Encriptado;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloPila;

/**
 * clase que nos permite localizar una palabra y saber si es un palindromo o no
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Palindromo {
    private String cadena;
    private String cadena2;
    private ArregloPila pila;

    /**
     * Constructor que nos ayuda a inicializar las variables
     * @param cadena
     */
    public Palindromo(String cadena) {
        this.cadena = cadena.replace( " ","" ).toLowerCase();
        cadena2="";
    }

    /**
     * metodo que nos ayuda a verificar si la palabra agregada es un palindromo o no
     */
    public void obtenPalindromo(){
        pila = new ArregloPila(cadena.length());// obtenemos la longitud de la cadena
        for (int i=0 ; i<cadena.length() ; i++) pila.poner( cadena.charAt( i ) );/// recorremos las posiciones obtenidas en ella
        while ( !pila.vacio() ) cadena2+=pila.quitar();// en caso de que sea diferente a vacio quitaremos la posicion y la agregaremos en la cadena

        boolean bandera2= false;
        for (int j=0 ; j<cadena.length() ; j++){// recorremos la longitud de los datos
            if (cadena.length() == cadena2.length()){// en caso de que las dos cadenas sean iguales  en longitud pasamos al otro if
                if (cadena.charAt( j ) != cadena2.charAt( j )){// si la cadean normal en cada posicion es diferente a la cadena i
                    bandera2=false;
                    break;
                }else {
                    bandera2=true;
                }

            }else {
                bandera2=false;
                break;
            }
        }
        SalidaTerminal.consola( (bandera2)?"Palindromo":"No es palindromo" );//dependiendo de el caso de que sea palindromo o no regresara true
    }
}
