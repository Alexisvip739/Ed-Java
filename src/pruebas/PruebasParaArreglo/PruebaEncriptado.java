package pruebas;

import Herramientas.Encriptado.Desencriptar_Encriptar;
import Herramientas.Encriptado.Palindromo;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;

/**
 * clase que nos permite dar el resultado de cada una de las operaciones creadadas para poder encriptar y desencriptar los datos de la
 * cadena de texto
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaEncriptado {
    public static void main(String[] args) {
        ArregloDatos posicion= entradasalida.Archivos.ArchivoTexto.leer("C:\\Users\\ultre\\Documents\\Trabajos\\Java\\edylab_2021_2\\src\\Herramientas\\Encriptado\\Posicion.txt");
        Desencriptar_Encriptar encriptar= new Desencriptar_Encriptar("Estructura De Dato",posicion);

        //encriptar.Encriptado();
        //SalidaTerminal.consola("\n");
        //encriptar.Desencriptacion();

        Palindromo palindromo= new Palindromo("reconocer");
        palindromo.obtenPalindromo();
    }
}
