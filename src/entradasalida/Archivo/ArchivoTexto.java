package entradasalida.Archivos;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import java.io.*;

/**
 * Archivo que nos permite leer los datos de un archivo txt
 * @author  Aldonso
 * @version 1.0
 */
public class ArchivoTexto {
    /**
     * Metodo que nos ayuda a leer un archivo de texto
     * @param archivo
     * @return  datos
     */
    public static ArregloDatos leer(String archivo){
        FileReader input=null;
        int registro=0;
        ArregloDatos datos=null;
        BufferedReader buffer = null;

        try {
            String cadena=null;
            input = new FileReader(archivo);
            buffer = new BufferedReader(input);
            datos=new ArregloDatos((int)buffer.lines().count());
            buffer.close();
            input.close();
            input = new FileReader(archivo);
            buffer = new BufferedReader(input);
            while((cadena = buffer.readLine())!=null) {
                datos.agregar(cadena);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                input.close();
                buffer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return datos;
    }

    /**
     * Este metodo nos ayuda a escribir un nuevo archivo de texto
     * @param arreglo
     * @param archivo
     */
    public static void escribir(ArregloDatos arreglo, String archivo){
        FileWriter output=null;
        try {
            output = new FileWriter(archivo);
            for(int posicion=0;posicion<arreglo.cantidadElementos() -1 ;posicion++) {
                output.write((String)arreglo.obtener(posicion)+ "\n");
            }
            output.write((String)arreglo.obtener(arreglo.cantidadElementos() - 1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                output.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}