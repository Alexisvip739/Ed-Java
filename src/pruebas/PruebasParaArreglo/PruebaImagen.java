package pruebas;

import Herramientas.TipoImagenTamanio;
import entradasalida.SalidaTerminal;
import estructurasnolineales.ImagenArreglo.ColoresImagen;
import estructurasnolineales.ImagenArreglo.ImagenPixel;

import java.awt.*;

/**
 * clase en donde se hace llamar todos los metodos de la clase ImagenPixel y crea una imagen para cada punto en espesifico
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PruebaImagen {
    public static void main(String[] args) {
        try {
            ImagenPixel imagen= new ImagenPixel("C:\\Users\\ultre\\Documents\\Trabajos\\Java\\edylab_2021_2\\src\\estructurasnolineales\\ImagenArreglo\\Imagenes\\descarga .jpg");
            SalidaTerminal.consola(imagen.leerImagen()+"\n");
            imagen.BlancoYnegro();
            imagen.brillo(10);
            imagen.vertical();
            imagen.Horizontal();
            imagen.transpuestaImagen();
            imagen.redimensionar(20,20);

            SalidaTerminal.consola(imagen.EscribirArchivo("Imagen2")+"\n");
        }catch (Exception e){

        }
    }
}
