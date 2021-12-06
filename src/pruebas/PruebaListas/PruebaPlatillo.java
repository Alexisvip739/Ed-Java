package pruebas;

import Herramientas.TipoPlatillo;
import Herramientas.TipoProcedimientoDeCoicina;
import entradasalida.SalidaTerminal;
import registros.restaurante.Chef;
import registros.restaurante.Comida;
import registros.restaurante.Ingredientes;
import registros.restaurante.Platillo;

/**
 * Esta clase nos ayuda a poder  mostrar todo en pantalla de la clase de Platilo
 * @author  Alexis Ultreras Sotelo
 */
public class PruebaPlatillo {
    public static void main(String[] args) {
        Platillo platillo1= new Platillo();
        // CREACION DE LOS CHEFS
        Chef chef1= new Chef("Joel",30 );
        Chef chef2= new Chef("Eliazar",60);


        // CREACION Y ASIGNACIN DE CADA Y UNO DE LOS DATOS PARA LA COMIDA Y LOS INGREDIENTES DE DICHA COMIDA QUE SE MANDARAN A AGREGAR A UN PLATO
        Ingredientes ingrediente1= new Ingredientes("Arina,Salsa,aceite","Dejar cosinando por 1 hora",200);
        Comida comida1 = new Comida("Pollo empanizado",200,ingrediente1,chef1,TipoProcedimientoDeCoicina.HORNO, TipoPlatillo.TIPO_conCarne);

        Ingredientes ingrediente2= new Ingredientes("Verduras, Mayonesa","mezclar hasta que este listo",100);
        Comida comida2 = new Comida("Ensalada",100,ingrediente2,chef2, TipoProcedimientoDeCoicina.aMANO,TipoPlatillo.TIPO_VEGETAL);

        Ingredientes ingrediente3= new Ingredientes("Sal","Mezclar con la cerveza",2);
        Comida comida3 = new Comida("Cerveza con camaron",120,ingrediente3,chef1,TipoProcedimientoDeCoicina.aMANO, TipoPlatillo.Tipo_liquido);

        Ingredientes ingrediente4= new Ingredientes("Carne,salsa","Colocarla en forma de pila",180);
        Comida comida4 = new Comida("hamburguesa",100,ingrediente4,chef2, TipoProcedimientoDeCoicina.CUCHILLO,TipoPlatillo.TIPO_conCarne);

        platillo1.agregarComidas(comida1);
        platillo1.agregarComidas(comida2);
        platillo1.agregarComidas(comida3);
        platillo1.agregarComidas(comida4);

        platillo1.imprimirComida();



    }
}
