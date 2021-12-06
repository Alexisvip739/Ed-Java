package registros.BaseDeDatosABB;



import entradasalida.SalidaTerminal;
import estructurasnolineales.ABB;
import estructurasnolineales.registros.NodoBusquedaBinaria;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase que nos ayuda a poder implementar y agregar los datos que viene de excel a un arbol  binario de busqueda
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */

public class BaseDeDatos extends ABB {

    /**
     * declaramos la inicializacion de la ruta en donde estara el archivo
     *
     */
    protected String archivo;

    public BaseDeDatos(String archivo) {
        this.archivo = archivo;
    }


    /**
     * Este metodo nos ayudara a poder leer los datos externos de un excel hacia el codigo y pasarlos a un arbol binario de busqueda
     * @param cadena
     * @throws IOException
     */
    public void leerArchivo(String cadena) throws IOException {
        this.archivo = cadena;// Obtenemos la cadena
        boolean finArchivo = false;
        RandomAccessFile archivo = null;
        int clave = 0;// contador que representara el id
        try {
            archivo = new RandomAccessFile(this.archivo, "r");// leemos el dato
            SalidaTerminal.consola("El tamaño es " + archivo.length() + " ");// obtenemos la longitud de los datos dentro de el archivo
            String cad = "";
            while (true) {
                cad = archivo.readLine();// leemos la cadena y asiganomos los datos
                if (cad == null) {// en caso de que no se tenga nada en la cadena
                    break;// salimos de el metodo
                } else if (finArchivo == false) {// en caso de que aun no se termine el archivo
                    finArchivo = true;
                } else {
                    SalidaTerminal.consola(archivo.getFilePointer()+" ");
                    SalidaTerminal.consola(archivo.readLine()+"\n");
                    NodoBusquedaBinaria nodoNuevo = new NodoBusquedaBinaria(clave++, archivo.getFilePointer());// obtenemos cada uno de los elementos de el el archivo y los pasamos a un nodo
                    if (nodoNuevo != null) {// en caso de que aun tenga datos
                        agregar(nodoNuevo);// agregamos
                    }
                }
            }
        } catch (FileNotFoundException f) {
            SalidaTerminal.consola("Error, dato no encontrado");
        }


    }


    /**
     * este metodo nos permite poder agregar datos directamente a los nodos y directamente a el excel
     * @param indice
     * @param dato
     * @return
     * @throws IOException
     */
    public  boolean insertar(Object indice,Object dato) throws IOException {
        if (dato!=null){
            RandomAccessFile archivoConDatos=null;
            try {
                archivoConDatos= new RandomAccessFile(archivo,"rw");// el rw significa (escribir y leer) donde podremos leer los datos y a su vez escribirlos dentro de el excel
                if (archivoConDatos!=null){
                    long longitudDeArchivo=archivoConDatos.length();// obtenemos la longitud
                    archivoConDatos.seek(longitudDeArchivo);// obtenemos posiciones con el sekk
                    archivoConDatos.writeBytes(dato.toString());// escribirmos el dato por medio de bytes a el archivo en excel
                    archivoConDatos.seek(longitudDeArchivo);// asignamos los nuevos datos (actualizamos)

                    if (indice!=null){
                        NodoBusquedaBinaria nodoNuevo= new NodoBusquedaBinaria(indice,dato);// agregamos los datos a el nodo binairo
                        if (nodoNuevo!=null){
                            agregar(nodoNuevo);
                        }
                    }else {
                        return false;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            archivoConDatos.close();// una vez terminado todo entonces cerramos el archivo
            return true;
        }

       return false;

    }




}