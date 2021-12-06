import entradasalida.SalidaTerminal;
import registros.BaseDeDatosABB.BaseDeDatos;
import registros.BaseDeDatosABB.TablasBaseDeDatos;
import registros.BaseDeDatosABB.TipoCategorias.Tipo__categories_tab;

import java.io.IOException;

/**
 * esta clase nos permite hacer pruebas al momento de ingresar los datos a las rutas de excel o dentro de los nodos.
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaBD {
    public static void main(String[] args) throws IOException {
        TablasBaseDeDatos tabla1= new TablasBaseDeDatos();

        // indicamos la ruta de los archivos en excel
        // estos se iran directamente a la lectura ya que estan dentro de el cotructor
        BaseDeDatos Bd= new BaseDeDatos("C:\\Users\\ultre\\Desktop\\datos_ordenes\\categories_tab.csv");// archivo de excel
        BaseDeDatos Bd2= new BaseDeDatos("C:\\Users\\ultre\\Desktop\\datos_ordenes\\customers.csv");//archivo de excel
        Tipo__categories_tab tipo=new Tipo__categories_tab("software","i am a software engenner xd :3 :D uwu ","34","345");
        tabla1.agregarTipoDeTabla(1,56,tipo);// aqui asignamos a que tabla se ira, que id tiene el dato y el dato


        Bd.leerArchivo("C:\\Users\\ultre\\Desktop\\datos_ordenes\\categories_tab.csv");














    }
}
