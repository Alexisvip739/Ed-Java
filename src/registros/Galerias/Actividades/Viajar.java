package registros.Galerias.Actividades;

/**
 * aActividad de viajes que puede realizar cualquier pintor
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Viajar {
    private  Object lugar;

    public Viajar(Object lugar) {
        this.lugar = lugar;
    }

    public Object getLugar() {
        return lugar;
    }

    public void setLugar(Object lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return lugar.toString();
    }
}
