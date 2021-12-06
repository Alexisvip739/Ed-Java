package registros.Galerias.Actividades;

public class Exponer {
    private Object lugar;
    private Object nombreEvento;
    private Object Fecha;
    private int Aforo;

    public Exponer(Object lugar, Object nombreEvento, Object fecha, int aforo) {
        this.lugar = lugar;
        this.nombreEvento = nombreEvento;
        Fecha = fecha;
        Aforo = aforo;
    }

    public Object getLugar() {
        return lugar;
    }

    public void setLugar(Object lugar) {
        this.lugar = lugar;
    }

    public Object getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(Object nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getFecha() {
        return Fecha.toString();
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getAforo() {
        return Aforo;
    }

    public void setAforo(int aforo) {
        Aforo = aforo;
    }

    @Override
    public String toString() {
        return nombreEvento.toString();
    }
}
