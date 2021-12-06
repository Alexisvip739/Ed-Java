package estructurasnolineales.registros;

public class NodoBusquedaBinaria {
    /**
     * Metodo de un nodo de busquedaBinaria, este metodo crea un indice junto con sus direccionnes
     * Este metodo se ulitiza para sacar valores en Empledos y el archivo txt pasarlos a nodo
     */
    private Object indice;// sera la clave
    private Object diriccion;// este sera el dato a agregar dentro de el archivo o nodo

    public NodoBusquedaBinaria(Object indice, Object diriccion) {
        this.indice = indice;
        this.diriccion = diriccion;
    }

    public Object getIndice() {
        return indice;
    }

    public void setIndice(Object indice) {
        this.indice = indice;
    }

    public Object getDiriccion() {
        return diriccion;
    }

    public void setDiriccion(Object diriccion) {
        this.diriccion = diriccion;
    }

    @Override
    public String toString() {
        return  indice.toString();
    }
}