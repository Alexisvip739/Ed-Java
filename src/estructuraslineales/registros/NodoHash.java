package estructuraslineales.registros;

/**
 * Clase que nos permite poder asifnarle a un objeto un nuevo valor que es ahora un nodo de tipo hash
 * este nodo se podra agregar a una lista de hash para poder hacer los metodos correspondientes asignados
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */

public class NodoHash {
    protected Object clave;
    protected Object valor;
    protected NodoHash ligaDer;

    public NodoHash(Object clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
        ligaDer=null;
    }

    public NodoHash(Object clave, Object valor, NodoHash ligaDer) {
        this.clave = clave;
        this.valor = valor;
        this.ligaDer = ligaDer;
    }

    public Object getClave() {
        return clave;
    }

    public void setInfo(Object clave) {
        this.clave = clave;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public NodoHash getLigaDer() {
        return ligaDer;
    }

    public void setLigaDer(NodoHash ligaDer) {
        this.ligaDer = ligaDer;
    }

    @Override
    public String toString() {
        return clave +" " +valor;
    }
}