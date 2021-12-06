package registros.restaurante;

/**
 * Clase que contiene los datos personales de un chef en especial
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Chef {
    protected  String NombreChef;
    protected  int  Edad;

    public Chef(String nombreChef, int edad) {
        NombreChef = nombreChef;
        Edad = edad;
    }

    public String getNombreChef() {
        return NombreChef;
    }

    public int getEdad() {
        return Edad;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "NombreChef='" + NombreChef + '\'' +
                ", Edad=" + Edad +
                '}';
    }
}
