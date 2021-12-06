package registros.catalogos.productoDigital;

import Herramientas.TipoOrden;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArreglOrdenado;
import estructuraslineales.ArregloDatos;

/**
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 * Clase que implementa toda la logica de los metodos agregados para despues poder llamarlos
 */
public class Ferreteria {

    private ArreglOrdenado listaProducto;

    /**
     * declaracion de el arreglo
     *
     * @param CAPACIDAD
     */
    public Ferreteria(int CAPACIDAD) {
        listaProducto = new ArreglOrdenado(CAPACIDAD, TipoOrden.Ordenado);
    }

    /**
     * agrega un producto a la lista de la ferreteria
     *
     * @param producto
     * @return validar
     */
    public int agregaProducto(Producto producto) {
        Integer validar = (Integer) listaProducto.buscar(producto); // busca el elemento
        if (validar != null) { // si se encontro
            listaProducto.agregar(producto); // lo agrega
            ordenaProducto();
        }
        return validar;
    }


    /**
     * imprime los datos de la ferreteria
     */

    public void imprimirProductos() {
        for (int i=0;i<listaProducto.cantidadElementos();i++){
            SalidaTerminal.consola(listaProducto.obtener(i)+"\n");
        }
    }

    /**
     * metodo para poder buscar un producto mediante la marca que se le de a el usuario
     *
     * @param marca
     * @return listaProducto
     */
    public Object buscarProductoMedianteMarca(Object marca) {
        for (int i = 0; i < listaProducto.cantidadElementos(); i++) {
            Producto productoMarca = (Producto) listaProducto.obtener(i); // buscamos el producto y depsues se castea hacia una variable
            if (productoMarca.getMarcaProducto().equalsIgnoreCase(marca.toString())) { // se verifica de que los datos sean correspondidos
                return listaProducto.obtener(i); // si son correspondidos entonces obtenemos los datos
            }
        }
        return null;
    }

    /**
     * metodo para poder buscar un producto mediante una frase dada
     *
     * @param frase
     */
    public void buscarProductoPorDescripcion(String frase) {
        ArregloDatos frases;

        // recorremos el arreglo
        for (int i = 0; i < listaProducto.cantidadElementos(); i++) {
            Producto productoActual = (Producto) listaProducto.obtener(i); // obtenemos la posicion y la agregamos a una nueva variable
            frases = new ArregloDatos(productoActual.getDescripcionProducto().length()); // agregamos la longitud de la palabra a un arreglo comun
            String fraseBuscar = " ";
            for (int j = 0; j < productoActual.getDescripcionProducto().length(); j++) {
                if (productoActual.getDescripcionProducto().charAt(j) != ' ') {
                    fraseBuscar = fraseBuscar + productoActual.getDescripcionProducto().charAt(j);
                } else {
                    frases.agregar(frase);
                    fraseBuscar = "";
                }
            }
            Integer posicionFrase = (Integer) frases.buscar(frase); // buscamos el dato  y lo pasamos a enteri
            if (posicionFrase != null) // se encontro la palabra en el arreglo

                SalidaTerminal.consola(productoActual.getNombreProducto() + "\n");

        }


    }

    /**
     * metodo que nos ayuda a elimninar un producto agregado
     *
     * @param producto
     * @return productoEliminado
     */
    public Object eliminarProducto(String producto) {
        Object productoEliminado = null;
        Integer posicion = (Integer) listaProducto.buscar(producto); // obteniemos la busqueda en entero
        if (posicion >= 0) {
            productoEliminado = listaProducto.eliminar(posicion - 1);// eliminamos el dato que esta gregado
        }
        return productoEliminado;
    }

    /**
     * Metodo que nos ayuda a poder cambiar el precio de un producto en espesico
     *
     * @param producto
     * @param PrecioActual
     * @return productoObtenido
     */
    public float cambiarPrecio(String producto, float PrecioActual) {

        Integer posicion = (Integer) listaProducto.buscar(producto); // se busca el dato ty lo agregamos a una variable entera
        if (posicion > 0) {
            Producto productoObtenido = (Producto) listaProducto.obtener(posicion - 1);
            productoObtenido.setPrecioProducto(PrecioActual); // establesemos el dato nuevo en la variable de precio
            return productoObtenido.getPrecioProducto();
        }
        return 0;

    }

    /**
     * Cambia la descripcion de el producto por una nueva
     *
     * @param producto
     * @param Descripcion
     * @return productoObtenido
     */
    public Object CambiarDescripcion(String producto, String Descripcion) {
        Integer posicion = (Integer) listaProducto.buscar(producto);
        if (posicion > 0) {
            Producto productoObtenido = (Producto) listaProducto.obtener(posicion - 1); // obtenemos la posicion de el dato
            productoObtenido.setDescripcionProducto(Descripcion); // cambiamos la informacion de el produto por una nueva
            return productoObtenido.getDescripcionProducto();
        }
        return null;
    }

    /**
     * nos da un rango en espesicop de productos que solamente tengan la pagina igual
     *
     * @param pagina
     */

    public void productosPorPagina(int pagina) {
        for (int i = 0; i < listaProducto.cantidadElementos(); i++) {
            Producto productoObtenido = (Producto) listaProducto.obtener(i); // obtenemos los datos y los agregamos a una variable de tipo persona

            if (productoObtenido.getPaginaDelProducto() == pagina) { //
                SalidaTerminal.consola(productoObtenido.getNombreProducto() + ": " + productoObtenido.getDescripcionProducto() + "\n");
            }
        }
    }

    /**
     * Metodo que nos ayuda a agregar los datos de forma ordenada pero con mas datos que tengan la misma letra
     */
    private void ordenaProducto() {

            if (listaProducto.cantidadElementos()>1){ // tiene mas de q elemento guardado

                while (true) {
                    // contador que incrementara si todos los elementos estan en su lugar correspondiente
                    int cont=1;
                    for (int posProducto= 0; posProducto<listaProducto.cantidadElementos()-1 ; posProducto++){
                        Producto producto1 = (Producto) listaProducto.obtener(posProducto);
                        Producto producto2 = (Producto) listaProducto.obtener(posProducto+1);

                        // obtenemos la menor longitud de caracteres de las 2 productos.
                        int cantCaracteres = (producto1.getNombreProducto().length()<producto2.getNombreProducto().length())?
                                producto1.getNombreProducto().length():producto2.getNombreProducto().length();

                        for (int posCaracter=0;posCaracter<cantCaracteres;posCaracter++){

                            // si el caracter del producto1 es mayor al caracter del producto2, cambian de posicion este paso se hace mediate la creasion de el ascii
                            if (producto1.getNombreProducto().charAt(posCaracter)>producto2.getNombreProducto().charAt(posCaracter)){
                                listaProducto.cambiar(posProducto+1,producto1); // asigna  el producto 1, que es la palabra ortignal a la posicion +1
                                listaProducto.cambiar(posProducto,producto2);
                                break;
                            }
                            else if (producto1.getNombreProducto().charAt(posCaracter)<producto2.getNombreProducto().charAt(posCaracter)){
                                cont++;
                                break;
                            }// los caracteres son iguales
                        }
                    }
                    // todos los elementos estan en su lugar, salimos del ciclo while
                    if (cont>=listaProducto.cantidadElementos()) break;
                }
            }




    }
}