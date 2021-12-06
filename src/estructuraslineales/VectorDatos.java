package estructuraslineales;

public interface VectorDatos extends ListaDatos {

    public boolean lleno();

    public int capacidad();

    public int cantidadElementos();


    public boolean cambiar(int indice, Object elemento);

    public boolean cambiarArregloDatos(ArregloDatos indicesBusqueda,ArregloDatos elementosNuevos);

    public Object eliminar(int indice);

    public Object redimensionar(int maximo);



}
