package registros.Galerias.Actividades;

public class Pintar {

    private  Object nombre;

    public Pintar(Object nombre) {
        this.nombre = nombre;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  nombre.toString();
    }
}
