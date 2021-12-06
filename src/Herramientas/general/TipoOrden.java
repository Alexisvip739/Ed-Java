package Herramientas.general;

/**
 * Clase que nos ayuda a implementar la funcionalidad de grafos matriz
 * @Profesor_Aldonso
 * @version 1
 */
public enum TipoOrden {
    ASC("ASCEDENTE",1),DESC("DESCENDENTE",0);

    private String nombre;
    private int valor;

    private TipoOrden(String nombre, int valor){
        this.nombre=nombre;
        this.valor=valor;
    }
    public String getNombre() {
        return nombre;
    }
    public int getValor() {
        return valor;
    }
}
