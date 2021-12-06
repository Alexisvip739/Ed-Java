package estructurasnolineales.ImagenArreglo;

/**
 * imagen que representa los colores  de la imagen
 * ya que en el metodo.
 * Este metodo lo tiene java dentro de el pero decidi hacerlo asi ya que se me hacia un poco mas sencillo para mi
 *@author Alexis Ultreras Sotelo
 *@version 1.0
 */
public enum ColoresImagen {
    Red("ROJO",1),// color rojo
    Green("VERDE",2),// color verde
    Blue("AZUL",3);// color azul

    private String color;
    private int valor;

    private ColoresImagen(String color, int valor) {
        this.color = color;
        this.valor = valor;
    }

    public String getColor() {
        return color;
    }

    public int getValor() {
        return valor;
    }
}
