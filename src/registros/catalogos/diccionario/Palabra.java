package registros.catalogos.diccionario;

import Herramientas.Tipo_Palabra;

/**
 * clase que con datos de la palabra a usar
 * @author Alexis Ultereras Sotelo
 * @version  1.0
 */
public class Palabra {
    private String NombrePalabra;
    private String Descripcion;
    private Tipo_Palabra tipoPalabra;


    public Palabra(String nombrePalabra, String descripcion, Tipo_Palabra tipoPalabra) {
        NombrePalabra = nombrePalabra;
        Descripcion = descripcion;
        this.tipoPalabra = tipoPalabra;
    }

    public String getNombrePalabra() {
        return NombrePalabra;
    }

    public void setNombrePalabra(String nombrePalabra) {
        NombrePalabra = nombrePalabra;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Tipo_Palabra getTipoPalabra() {
        return tipoPalabra;
    }

    public void setTipoPalabra(Tipo_Palabra tipoPalabra) {
        this.tipoPalabra = tipoPalabra;
    }

    @Override
    public String toString() {
        return  NombrePalabra;
    }
}
