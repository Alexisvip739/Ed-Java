package registros.catalogos.productoDigital;

/**@author ALEXIS ULTRERAS SOTELO
 * @version 1.0
 *Esta clase nos da los datos que tienen sierto productos agregados en la lista de la ferreteria
 *
 * */
public class Producto {
    private String nombreProducto;
    private  String descripcionProducto;
    private  float precioProducto;
    private  String marcaProducto;
    private  Object fotgrafiaProducto;
    private  int paginaDelProducto;

    public Producto(String nombreProducto, String descripcionProducto, float precioProducto, String marcaProducto, Object fotgrafiaProducto, int paginaDelProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.marcaProducto = marcaProducto;
        this.fotgrafiaProducto = fotgrafiaProducto;
        this.paginaDelProducto = paginaDelProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public Object getFotgrafiaProducto() {
        return fotgrafiaProducto;
    }

    public void setFotgrafiaProducto(Object fotgrafiaProducto) {
        this.fotgrafiaProducto = fotgrafiaProducto;
    }

    public int getPaginaDelProducto() {
        return paginaDelProducto;
    }

    public void setPaginaDelProducto(int paginaDelProducto) {
        this.paginaDelProducto = paginaDelProducto;
    }

    @Override
    public String toString() {
        return nombreProducto;
    }
}
