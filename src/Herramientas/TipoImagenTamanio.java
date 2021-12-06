package Herramientas;

/**
 * clase enumerada  que asinga el tamaño de la imagen por mitad, double  y triple
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public enum TipoImagenTamanio {
    DOBLE("DOBLE",1),TRIPLE("TRIPLE",2),MITAD("MITAD",3);

    private String nombre;
    private int valor;

    private TipoImagenTamanio(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }
    public int getValor() {
        return valor;
    }
}
