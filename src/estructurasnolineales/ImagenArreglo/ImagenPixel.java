package estructurasnolineales.ImagenArreglo;
import Herramientas.TipoImagenTamanio;
import entradasalida.SalidaTerminal;
import estructurasnolineales.Tabla2D;
import estructurasnolineales.Tabla2DNumerica;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Esta clase nos permite mas que nada obtener la ruta de en donde se hubica la imagen y tambien es donde se almacena toda
 * la logica de programacion para obtener cada una de las imagenes
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class ImagenPixel {
    private BufferedImage imagen;
    private BufferedImage imagen2;
    private Tabla2DNumerica imagenMatriz;
    private File archivo;
    private String file;
    private String nomRuta;
    private Color color;
    private Pixel pixel;

    private String nombreArchivo;


    /**
     * constructor en donde asignamos el url de la imagen como global
     * @param file
     */
    public ImagenPixel(String file){
        this.file=file;
        try {
            archivo = new File(file);
            nombreArchivo=archivo.getName();
            nomRuta=archivo.getParent();
        }catch (Exception e){

        }

    }

    /**
     * Metodo que nos ayuda a resivir la imagen y posteriormente la lee como archivo
     * @return true o false
     */
    public boolean leerImagen(){
        try {
            imagen = ImageIO.read(archivo); // leemos la imagen

            int w = imagen.getWidth();// obtenemos el largo
            int h = imagen.getHeight();// obtenemos el ancho
            imagenMatriz= new Tabla2DNumerica(w,h);// los datos los asignamos a una matriz 2d
            for (int i=0;i<imagenMatriz.getFilas();i++){
                for (int j=0;j<imagenMatriz.getColumnas();j++){
                    int dato=imagen.getRGB(i,j);// obtenemos los pixeles de la imagen original
                    imagenMatriz.asignarCelda(i,j,dato); // para asignarselos a la tabla 2d     que sera la segunda imagen
                }
            }

            return true;
        }catch (Exception e){
            SalidaTerminal.consola(e+"\n");
        }
        return false;

    }

    /**
     * metodo que nos ayuda a crear una nueva imagen
     * @param ImagenSecundaria
     * @return true o false
     * @throws Exception
     */
    public  boolean EscribirArchivo(String ImagenSecundaria) throws  Exception{
        try {
            imagen2= new BufferedImage(imagenMatriz.getFilas(),imagenMatriz.getColumnas(),BufferedImage.TYPE_INT_RGB);// asignamos los datos a la imagen2
            for (int i=0;i<imagenMatriz.getFilas();i++){
                for (int j=0;j<imagenMatriz.getColumnas();j++){
                    int pixeles= (int) imagenMatriz.obtenerInfo(i,j);// obtenemos los pixeles
                    imagen2.setRGB(i,j,pixeles);//establecemos en la imagen 2 los pixeles en su posicion
                }
            }
            File nuevaImagen= new File(nomRuta+"/2"+ImagenSecundaria+nombreArchivo);// creamos una nueva ruta con la imagen nueva
            ImageIO.write(imagen2,"JPG",nuevaImagen);// le asiganmos el nombre de la imagen y el tipo que es
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * metodo que convierte todos los colores de la imagen en blanco y negro
     * @return true o false
     */
    public  boolean BlancoYnegro(){
        for(int i=0; i<imagenMatriz.getFilas();i++){
            for(int j=0; j<imagenMatriz.getColumnas();j++){
                int datos= (int)imagenMatriz.obtenerInfo(i,j);// recorremos la matriz en las posiciones que contienen los pixeles
                pixel= new Pixel(datos); // asignamos los pixeles a la clase
                int rojo=pixel.getRed();// obtenemos el color rojo
                int verde=pixel.getGreen();//obtenemos el color verde
                int azul=pixel.getBlue();// obtenemos el color azul
                // una vex obtenido  los datos, los datos se suman y se saca su promedio entre los tres
                // para asi poder crear una distorcion en los colores de la imagen y se configuren a blanco  y negro
                pixel.setRed((rojo+verde+azul)/3);
                pixel.setBlue((rojo+verde+azul)/3);
                pixel.setGreen((rojo+verde+azul)/3);
                pixel.setAlpha((rojo+verde+azul)/3);


                imagenMatriz.asignarCelda(i,j,pixel.unir());// unimos los pixeles nuevamente con <<

            }
        }
        return false;
    }


    /**
     * metodo que cambia el brillo de la imagen a mas alto
     * @param subir
     * @return true o false
     */
    public  boolean brillo(int subir){
        for (int i=0;i<imagenMatriz.getFilas();i++){
            for (int j=0;j<imagenMatriz.getColumnas();j++){
                int datos= (int)imagenMatriz.obtenerInfo(i,j);
                pixel= new Pixel(datos);// asignamos las posiciones de los pixeles en la clase
                // obtenemos cada uno de los colores de los pixeles
                int rojo=pixel.getRed();
                int verde=pixel.getGreen();
                int azul=pixel.getBlue();


                // para poder subir de brillo, a los canales de la imagen les sumamos el dato entero que se
                // manda como parametro a cada uno de el rgba
                pixel.setRed(pixel.canal(pixel.getRed(),subir));
                pixel.setBlue(pixel.canal(pixel.getBlue(),subir));
                pixel.setGreen(pixel.canal(pixel.getGreen(),subir));
                pixel.setAlpha(pixel.canal(pixel.getAlpha(),subir));
                imagenMatriz.asignarCelda(i,j,pixel.unir());
            }
        }
        return false;

    }

    /**
     * metodo que pasa la imagen de forma normal a forma vertical
     */
    public void vertical(){
        for (int i=0;i<imagenMatriz.getFilas();i++){
            for (int j=0;j<imagenMatriz.getColumnas()/2;j++){
                Object obtenerDato= imagenMatriz.obtenerInfo(i,(imagenMatriz.getColumnas()-j)-1);
                Object obtenerDatos2= imagenMatriz.obtenerInfo(i,j);
                imagenMatriz.asignarCelda(i,(imagenMatriz.getColumnas()-j)-1,obtenerDatos2);// asignamos los datos obtenidos a las celdas
                imagenMatriz.asignarCelda(i,j,obtenerDato);// asignamos los datos de la primera variable

            }
        }

    }

    /**
     * este metodo nos permite poder crear la imagen de forma horizontal
     * @return true o false
     */
    public  boolean Horizontal(){
        for (int i = 0 ; i<imagenMatriz.getFilas()/2 ; i++){// dividimos en dos las filas
            for (int j = 0 ; j<imagenMatriz.getColumnas() ; j++){// obtenemos las columnas
                Object horizontal = imagenMatriz.obtenerInfo((imagenMatriz.getFilas()-i)-1,j);// obtenemos datos menos la posicion y -1 para que se vaya creando
                // una imagen horizontal
                Object obtenerhorizontal = imagenMatriz.obtenerInfo(i,j);
                imagenMatriz.asignarCelda((imagenMatriz.getFilas()-i)-1,j,obtenerhorizontal);// asignamos las filas menos las posiciones de los datos
                imagenMatriz.asignarCelda(i,j,horizontal);// asignamos los datos a la matriz
            }
        }
        return true;
    }


    /**
     * Gira los pixeles de la imagen a 90, 180 y 270 grados.
     * desde la matriz en donde se en cuentra almacenado los datos
     */
    public void girarImagen (){
        try {
            imagenMatriz.transpuesta();
            Horizontal();
            EscribirArchivo("girarA90");

            imagenMatriz.transpuesta();
            Horizontal();
            EscribirArchivo("giraA180");

            imagenMatriz.transpuesta();
            Horizontal();
            EscribirArchivo("giraA270");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *Redimensione el tamaño de una imagen proporcionada como base
     * @param redimensionar
     */
    public void redimensionar(TipoImagenTamanio redimensionar){
        double valor;
        if (redimensionar.getValor()==1) valor=2;// asignamos nuevo valor si es 1
        else if (redimensionar.getValor()==2)valor=3; // o tambien si es 2
        else valor=0.5;
        Tabla2DNumerica nuevoDimension = new Tabla2DNumerica((int)(imagenMatriz.getFilas()*valor),(int)(imagenMatriz.getColumnas()*valor),0);
        if (nuevoDimension.getFilas()<imagenMatriz.getFilas()){
            for(int i = 0; i < imagenMatriz.getFilas(); i++){
                for(int j = 0; j < imagenMatriz.getColumnas(); j++){
                    if(j%valor==0 && i%valor==0){// obtenemos el modulo de las filas y columnas de la imagen
                        Object pixel = imagenMatriz.obtenerInfo(i,j);// obtenemos las posiciones de la matrix
                        nuevoDimension.asignarCelda((int)(i*valor)+1,(int)(j*valor)+1,pixel);
                    }else {
                        Object pixel = imagenMatriz.obtenerInfo(i,j);// obtenemos los pixeles de la imagen
                        nuevoDimension.asignarCelda((int)(i*valor),(int)(j*valor),pixel);
                    }
                }
            }
        }else {
            for(int i = 0; i < nuevoDimension.getFilas(); i++){
                for(int j = 0; j < nuevoDimension.getColumnas(); j++){
                    if(j%valor==0 && i%valor==0){
                        Object pixel = imagenMatriz.obtenerInfo((int)(i/valor)+1,(int)(j/valor)+1);
                        nuevoDimension.asignarCelda(i,j,pixel);
                    }else {
                        Object pixel = imagenMatriz.obtenerInfo((int)(i/valor),(int)(j/valor));
                        nuevoDimension.asignarCelda(i,j,pixel);
                    }
                }
            }
        }imagenMatriz = nuevoDimension;
    }

    /**
     * metodo que pasa la matriz a una matriz transpuesta
     * @return true
     */
    public  boolean transpuestaImagen(){
        imagenMatriz.transpuesta();
        return true;
    }

    /**
     * metodo que cambia el tamaño de la imagen dependiendo de que ancho o largo se de
     * @param w
     * @param h
     */
    public void redimensionar(int w, int h){
        if (w==imagenMatriz.getFilas()/2 && h==imagenMatriz.getColumnas()){
            Tabla2DNumerica nuevoDimension = new Tabla2DNumerica(w,h,0);
            for(int i = 0; i < nuevoDimension.getFilas(); i++){
                for(int j = 0; j < nuevoDimension.getColumnas(); j++){
                    Object pixel = imagenMatriz.obtenerInfo(i*2,j);// obtenemos los datos en la variable
                    nuevoDimension.asignarCelda(i,j,pixel);// asignamos los datos nuevos de la imagen a la matriz
                }
            }
            imagenMatriz = nuevoDimension;// declaramos la nueva dimencion de la imagen
        }else if (w==imagenMatriz.getFilas() && h==imagenMatriz.getColumnas()/2){
            Tabla2DNumerica nuevoDimension = new Tabla2DNumerica(w,h,0);

            for(int i = 0; i < nuevoDimension.getFilas(); i++){
                for(int j = 0; j < nuevoDimension.getColumnas(); j++){
                    Object pixel = imagenMatriz.obtenerInfo(i,j*2);// multiplicamos a el doble el dato de las columnas
                    nuevoDimension.asignarCelda(i,j,pixel); // asignamos la obtencion de los datos a la nueva dimencinon de la matriz
                }
            }
            imagenMatriz = nuevoDimension;
        }

    }

    /**
     * este metodo nos ayuda a crear un marcho con un ancho en el dentro de la imagen dependiendo de el  color que se cree
     * @param color
     * @param anchoImagen
     */
    public void ImagenMarco(ColoresImagen color, int anchoImagen){
        Color pixelColor;
        if (color.getValor()==1){
            pixelColor = new Color(255,0,0);// sacamos el dato maximo de el rgb (255)
        }
        else if (color.getValor()==2){
            pixelColor = new Color(0,255,0);// sacamos el dato maximo de el rgb (255)
        }else{
            pixelColor = new Color(0,0,255);// sacamos el dato maximo de el rgb (255)
        }
        Tabla2DNumerica nuevoTamaño = new Tabla2DNumerica(imagenMatriz.getFilas()+(anchoImagen*2),imagenMatriz.getColumnas()+(anchoImagen*2), pixelColor.getRGB()); // el dato asignado de picel se pasa como dato ya que sera lo que se agregara como nuevo dentro de la imagen
        for(int i = 0; i < imagenMatriz.getFilas(); i++){
            for(int j = 0; j < imagenMatriz.getColumnas(); j++){
                Object pixel = imagenMatriz.obtenerInfo(i,j);
                nuevoTamaño.asignarCelda(i+anchoImagen,j+anchoImagen,pixel);// aumentamos las posiciones de la imagen mas el ancho y asignamos los pixeles
            }
        }
        imagenMatriz = nuevoTamaño;
    }


    /**
     * Vuelve a la imagen negativa.
     */
    public boolean imagenNegativa(){
        for(int i = 0; i < imagenMatriz.getFilas(); i++){// recorremos las filas de la imagen
            for(int j = 0; j < imagenMatriz.getColumnas(); j++){ //recorremos las columnas de la imagen
                pixel = new Pixel( (int)imagenMatriz.obtenerInfo(i,j) ) ;// obtenemos las posiciones de el pixel
                imagenMatriz.asignarCelda(i,j,pixel.getPixel()*(-1) );// como sob datos negativos se pueden representar como datos negativos numericos

            }
        }
        return true;
    }



}
