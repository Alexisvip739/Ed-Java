package pruebas;

import entradasalida.SalidaTerminal;
import registros.catalogos.diccionario.LibrebroPalabra;
import registros.catalogos.diccionario.Palabra;
import Herramientas.Tipo_Palabra;

/**
 * @author Alexis
 * @version 1.0
 * clase que implementa todo la presentacion en pantalla de los datos en Diccionario, ademas que aqui se crea y se manda informaciona
 */

public class PruebaDiccionario {
    public static void main(String[] args) {
        LibrebroPalabra palabraArreglo= new LibrebroPalabra(6);
        Palabra palabra1= new Palabra("Lalo","Nombre de persona",Tipo_Palabra.verbo);
        Palabra palabra2= new Palabra("Celular","Aparato Electronico",Tipo_Palabra.verbo);
        Palabra palabra3= new Palabra("Animal","Ser vivo que habita en el planeta tierra",Tipo_Palabra.verbo);
        Palabra palabra4= new Palabra("Guitarra","Instrumento Musical",Tipo_Palabra.verbo);


        palabraArreglo.AgregarPalabra(palabra1);
        palabraArreglo.AgregarPalabra(palabra2);
        palabraArreglo.AgregarPalabra(palabra3);
        palabraArreglo.AgregarPalabra(palabra4);


        palabraArreglo.BuscarConFrase("habita");





    }
}
