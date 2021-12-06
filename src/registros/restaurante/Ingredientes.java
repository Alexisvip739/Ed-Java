package registros.restaurante;

/**
 * Clase que contiene todos los atributos que tiene un ingrediente para una Comida o platillo
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Ingredientes {
    protected String NombreIngrediente;
    protected String descripcion;
    protected int cantidadGramos;


    public Ingredientes(String nombreIngrediente, String descripcion, int cantidadGramos) {
        NombreIngrediente = nombreIngrediente;
        this.descripcion = descripcion;
        this.cantidadGramos = cantidadGramos;
    }

    public String getNombreIngrediente() {
        return NombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        NombreIngrediente = nombreIngrediente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int  getcantidadGramos() {
        return cantidadGramos;
    }

    public void setcantidadGramos(int cantidadGramos) {
        this.cantidadGramos = cantidadGramos;
    }

    @Override
    public String toString() {
        return "Ingredientes{" +
                "NombreIngrediente='" + NombreIngrediente + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidadGramos='" + cantidadGramos + '\'' +
                '}';
    }
}


