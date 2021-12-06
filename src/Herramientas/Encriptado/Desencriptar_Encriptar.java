package Herramientas.Encriptado;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ArregloPila;

/**
 * Esta clase nos permite manipular el archivo de texto para asi poder modificarlo he Encriptarlo o desencriptarlo
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Desencriptar_Encriptar {


    private String cadena;
    private ArregloDatos arregloCadena;
    private String nuevaCadena;
    private ArregloDatos archivo;

    /**
     * Contructor para la encriptacion
     * @param cadena
     * @param archivo
     */
    public Desencriptar_Encriptar(String cadena,ArregloDatos archivo) {
        this.cadena = cadena;
        this.archivo = archivo;// posiciones que se modificaran en la cadena
        arregloCadena = new ArregloDatos( cadena.length() );// longitud de la cadena de caracteres
        nuevaCadena="";
    }

    /**
     *Metodo que nos ayuda a obtener la encriptacion de una cadena de texto dada
     */
    public void Encriptado(){
        for (int i=0 ; i<cadena.length() ; i++){
            arregloCadena.agregar( cadena.charAt( i ) );// agregapos las posiciones en char de la cadena
        }
        int n=0;
        while ( n<archivo.cantidadElementos() ) {// en caso de que n sea menor seguiremos ciclando
            nuevaCadena+=ObtenerEncriptado(Integer.parseInt( archivo.obtener( n ).toString()),Integer.parseInt(archivo.obtener(n+1).toString()));// obtenemos las posiciones de el archivo en donde nos
            //indica los numero que se modificaran
            n+=2;
        }
        SalidaTerminal.consola(nuevaCadena);// imprimimos la cadena obtenida
    }

    /**
     * Este metodo nos permite poder dar el rango de inicio a fin que se modificara la cadena de texto para
     * poder encriptarla
     * @param inicio
     * @param maximo
     * @return cadena
     */
    public String ObtenerEncriptado(int inicio,int maximo) {
        ArregloPila pilaArreglo=new ArregloPila( maximo );// le damos el rango a la lista de pilas
        String cadena="";

        cadena+=arregloCadena.obtener( inicio-1 );// agregamos cada posicion obtenida a la cadena
        cadena+='<';// cada vez que se inicie la obtencion de los datos de la cadena se le asiganra un < a la encriptacion
        int n = inicio;
        do {
            pilaArreglo.poner( arregloCadena.obtener( n ) );//obtiene el numero de inicio en el arreglo
            n+=1;
        }while (!pilaArreglo.lleno());// esto seguira ciclando mientras no este lleno

        while (!pilaArreglo.vacio()){// mientras no este vacuio
            cadena+=pilaArreglo.quitar();// quitaremos la obtencion de los datos encriptados y la asignaremos de nuevo a cadena
        }
        cadena+='>';// al terminal la encriptacion finalizaremos con un > dentro de la cadena
        return cadena;// regresamos la cadena encriptada en el rango espesifico
    }

    /**
     * Metodo que nos ayuda a Desencripta el mismo String Encriptado
     */
    public void Desencriptacion(){
        String desencriptacion="";// aqui se almacenara la cadena desencriptada
        for (int i = 0 ; i<nuevaCadena.length() ; i++){// recorremos la longitud de la cadena
            if (nuevaCadena.charAt( i ) == '<'){// nos indica la primera posicion en donde estan los datos encriptados
                // al momento de encontrar ese signo los datos que se encuentran en ese rango seran los encriptados
                int maximo=0;
                int n = i+1;
                while( nuevaCadena.charAt( n ) != '>' ){// mientras sea el caracter char diferente a >( final de los caracteres)
                    maximo++;// aumentamos maximo
                    n++;// aumentamos el numero
                }
                ArregloPila pilaArreglo = new ArregloPila( maximo );// asignamos el rango maximo a la pila
                //obtenemos los datos que estaban encriptados y los regresamos a como estaban normalmente
                //quitando lo que es ya los signos de >,<
                while ( !pilaArreglo.lleno() ){
                    pilaArreglo.poner( nuevaCadena.charAt( 1+(i++) ) );// ponemos en la pila el caracter de la cadena
                }
                while (!pilaArreglo.vacio()){
                    desencriptacion+=pilaArreglo.quitar();//quitamos posicion de la pila y la asignamos a la desencriptacion
                }i++;//aumentamos posicion
            }else desencriptacion+=nuevaCadena.charAt( i );
        }
        SalidaTerminal.consola( desencriptacion );
    }




}
