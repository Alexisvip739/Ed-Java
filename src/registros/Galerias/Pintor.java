package registros.Galerias;

import estructuraslineales.ArregloDatos;

/**
 * Clase que contiene todod los datos de la persona   que es pitnor
 */
public class Pintor {
    private String nombre;
    private String FechaN;
    private String RFC;
    private  String domicilio;
    private String NivelEducativo;
    private  int Edad;

    public Pintor(String nombre, String fechaN, String RFC, String domicilio, String nivelEducativo, int edad) {
        this.nombre = nombre;
        FechaN = fechaN;
        this.RFC = RFC;
        this.domicilio = domicilio;
        NivelEducativo = nivelEducativo;
        Edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaN() {
        return FechaN;
    }

    public void setFechaN(String fechaN) {
        FechaN = fechaN;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNivelEducativo() {
        return NivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        NivelEducativo = nivelEducativo;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    @Override
    public String toString() {
        return  nombre ;
    }
}
