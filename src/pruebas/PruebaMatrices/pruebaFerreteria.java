package pruebas;

import entradasalida.SalidaTerminal;
import registros.catalogos.productoDigital.Ferreteria;
import registros.catalogos.productoDigital.Producto;

/**
 * @author  Alexis Ultreas Sotelo
 * @version 1.0
 * clase que implementa el funcionamiento de la ferreteria, aqui se puede mandar a llamar cualquier metodo para poder probarlo
 */
public class pruebaFerreteria {
    public static void main(String[] args) {
        Ferreteria ferreteria = new Ferreteria(5);
        Producto producto1 = new Producto("Taladro", "Taladro mecanico con brocas de concreto incluidas", 290, "TerMex", "photo.png", 2);
        Producto producto2 = new Producto("Martillo", "Martillo muy resistente", 290, "TerMex", "photo2.png", 4);
        Producto producto3 = new Producto("Cortadora Electrica", "cortadora para poder cortar mas facil la madera", 590, "TerMex", "photo4.png", 1);
        Producto producto4 = new Producto("Liquido para frenos", "liquido para el uso de los frenos de tu  auto", 9090, "TerMex", "photo5.png", 2);

        ferreteria.agregaProducto(producto1);
        ferreteria.agregaProducto(producto2);
        ferreteria.agregaProducto(producto3);
        ferreteria.agregaProducto(producto4);

        //buscar UN producto mediante la marca
        //SalidaTerminal.consola(ferreteria.buscarProductoMedianteMarca("TerMex")+"\n");
        //ferreteria.buscarProductoPorDescripcion("termex");
        //ferreteria.imprimirProductos();
        // SalidaTerminal.consola(ferreteria.cambiarPrecio("Martillo",30)+"\n");
        //SalidaTerminal.consola(ferreteria.CambiarDescripcion("Cortadora Electrica","Cortadora Nueva")+"\n");
        // ferreteria.productosPorPagina(2);
        //ferreteria.imprimirProductos();
        //SalidaTerminal.consola(ferreteria.eliminarProducto("Taladro")+"\n");
        ferreteria.productosPorPagina(2) ;
    }
}