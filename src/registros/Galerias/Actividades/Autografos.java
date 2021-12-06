package registros.Galerias.Actividades;

public class Autografos {
    private Object lugar;
    private Object fecha;

    public Autografos(Object lugar, Object fecha) {
        this.lugar = lugar;
        this.fecha = fecha;


    }

    public Object getLugar() {
        return lugar;
    }

    public void setLugar(Object lugar) {
        this.lugar = lugar;
    }

    public Object getFecha() {
        return fecha;
    }

    public void setFecha(Object fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return lugar.toString();
    }
}
