package estructurasnolineales.ImagenArreglo;

/**
 * Esta clase nos permite interactuar directamente con los pixeles de la imagen
 * en donde se consideran los rgba de los pixeles
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Pixel {
    private  int pixel;
    private  int Alpha;
    private  int Red;
    private  int green;
    private  int blue;

    public Pixel(int pixel) {
        this.pixel = pixel;
        Alpha = (pixel>>24)&0xff;//
        Red = (pixel>>16)&0xff;
        green =(pixel>>0)&0xff;
        blue = pixel&0xff;
    }

    public int getPixel() {
        return pixel;
    }

    public void setPixel(int pixel) {
        this.pixel = pixel;
    }

    public int getAlpha() {
        return Alpha;
    }

    public void setAlpha(int alpha) {
        Alpha = alpha;
    }

    public int getRed() {
        return Red;
    }

    public void setRed(int red) {
        Red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int unir() {
        pixel = (Alpha<<24) | (Red <<16) | (green<<8) | blue;
        return pixel;
    }

    public int canal(int canal, int brillo){
        canal+=brillo;
        if (canal>=255){// en caso que sea mayor ( no puede ser mayor el rgba a 255)
            canal=255;// dato maximo de los rgba
        }else if (canal<=0){// si es menor ( tampoco puede ser menor ya que seria una imagen negativa)
            canal=0;
        }
        return canal;
    }
}
