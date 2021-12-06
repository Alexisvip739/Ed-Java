package registros.restaurante;

import Herramientas.TipoPlatillo;
import Herramientas.TipoProcedimientoDeCoicina;
import entradasalida.SalidaTerminal;
import estructuraslineales.ListaEncadenada;

/**
 * Clase que implementa todo la creacion de los platillos para el usuario dado
 * En esta clase podemos agregar, eliminar, modificar o actualizar cualquier dato de los platillos y sus ingredientes
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Platillo {

    protected ListaEncadenada platillo;// lista lifada

    public Platillo() {
        platillo = new ListaEncadenada();

    }


    /**
     * Este metodo nos permite poder mostrar todos los platillos con sus ingredientes
     */
    public void imprimir(){
        platillo.imprimir();
    }

    /**
     * metodo que nos ayuda a mostrar solamente las comidas agregadas
     */
    public void imprimirComida(){
        for (int i=0;i<platillo.numElementos();i++){
            Comida nombre=(Comida) platillo.obtener(i);//obtenemos los datos de la lista que son los platillos
            if (nombre instanceof  Comida){// verificamos de que sea una comida el dato
                SalidaTerminal.consola(nombre.getNombre()+"\n");// imprimimos los nombres de las comidas
            }
        }
    }

    /**
     * Este metodo nos permite poder mostrar todos los ingredientes de una comida en espesifico
     * @param comida
     */
    public  void ImprimirIngredientes(Object comida){
        for (int i=0;i<platillo.numElementos();i++){// recorremos la lista
            Comida ingredientesComida=(Comida) platillo.obtener(i);// obtenemos cada una de las posiciones de los platillos
            if (ingredientesComida.getNombre().toString().equalsIgnoreCase(comida.toString())){// en caso de que la comida sea igual al pasado como parametro
                if (ingredientesComida instanceof  Comida){// y que sea de tipo comida
                    SalidaTerminal.consola(ingredientesComida.ingrediente1+"\n");// mostramos en pantañña el ingrediente de la comida
                }
            }

        }
    }

    /**
     * metodo que nos permite asignarle nueva comida a un plato en especial
     * @param comida
     * @param ingerdiente
     * @return 1 o -1
     */
    public int AgregarIngredientes(Object comida, Ingredientes ingerdiente){
       for (int i=0;i<platillo.numElementos();i++){
           Comida comida1=(Comida) platillo.obtener(i);// obtenemos los datos de los platillos
           if (comida1.getNombre().toString().equalsIgnoreCase(comida.toString())){// verificamos de que sea iguales
               if (comida1 instanceof  Comida){
                   // asignamos en cada uno de los valores de los ingredientes sus nuevos datos
                   comida1.ingrediente1.setNombreIngrediente(ingerdiente.getNombreIngrediente());// AGREGAMOS EL NOMBRE DEL INGREDIENTE
                   comida1.ingrediente1.setcantidadGramos(ingerdiente.getcantidadGramos());// AGREGAMOS LOS GRAMOS
                   comida1.ingrediente1.setDescripcion(ingerdiente.getDescripcion());// AGREGAMOS LA DESCRIPCION

               }
               return 1;
           }
       }
       return -1;
    }

    /**
     * Metodo que nos permite poder eliminar los ingredientes de una comida en espesifico
     * @param comida
     * @return
     */
    public int EliminarIngredientes(Object comida){
        for (int i=0;i<platillo.numElementos();i++){// recorremos todos los elementos de la lista
            Comida comida1=(Comida) platillo.obtener(i);// obtenemos sus posiciones
            if (comida1.getNombre().toString().equalsIgnoreCase(comida.toString())){// verificamos de que sea el mismo dato
                if (comida1 instanceof  Comida){
                    // borramos los datos de esa comida
                    comida1.ingrediente1.setNombreIngrediente(null);
                    comida1.ingrediente1.setcantidadGramos(0);
                    comida1.ingrediente1.setDescripcion(null);

                }
                return 1;
            }
        }
        return -1;
    }

    /**
     * Metodo que nos permite agregar un nuevo platillo a la lista encadenada
     * @param comida
     * @return 1 o -1
     */
    public int agregarComidas(Comida comida){
        if (comida!=null){
            platillo.agregar(comida);

        }
        return -1;
    }

    /**
     * Metodo que nos permite sacar o dar de baja a un platillo en especial
     * @param comida
     * @return
     */
    public Object EliminarComida(Comida comida){
        return platillo.eliminar(comida);

    }


    /**
     * Metodo que nos permite buscar una comida en especial dada por el usuario
     * @param ingrediente
     */
    public void BuscarComida(Object ingrediente){
        for (int i=0;i<platillo.numElementos();i++) {
            Comida ingredienteAbuscar = (Comida) platillo.obtener(i);//obtenemos las posiciones
            if (ingredienteAbuscar.ingrediente1.getNombreIngrediente().equalsIgnoreCase(ingrediente.toString())) {// verificamos de que los datos sean iguales
                 SalidaTerminal.consola(ingredienteAbuscar.getNombre()+"\n");// regresamos el dato buscado
            }

        }

    }


    /**
     * Metodo que nos permite poder saber que platillos ha realizado un chef
     * @param nombre
     */
    public void BuscarPlatillosChef(Object nombre){
        for (int i=0;i<platillo.numElementos();i++){// recorremos la lista
            Comida platillosChef=(Comida) platillo.obtener(i);//obtenemos los datos de la lista
            if (platillosChef.chef.NombreChef.equalsIgnoreCase(nombre.toString())){// verificamos que este el nombre del chef
                SalidaTerminal.consola(platillosChef.getNombre()+"\n");// mostramos los platillos que ha creado el
            }
        }
    }


    /**
     * Metodo que nos muestra los platillos con un tipo de cocinado en espesifico
     * @param tipo
     */
    public void ProcedimientoDeCocinado(TipoProcedimientoDeCoicina tipo){
        for (int i=0;i<platillo.numElementos();i++){
            Comida PlatilloProcesado=(Comida) platillo.obtener(i);// obtenemos los datos de la lista
            if (PlatilloProcesado.tipoProcedimientoDeCoicina.equals(tipo)){// verificamos que tipo de cocinado es
                SalidaTerminal.consola(PlatilloProcesado.getNombre()+"\n");// obtenemos todos los nombres de los platillos con ese tipo
            }
        }
    }

    /**
     * Metodo que nos muestra todos los platillos que sean mayor o igual a 100 gramos en los ingredientes
     */
    public void PlatilloGramos(){
        for (int i=0;i<platillo.numElementos();i++){
            Comida gramos=(Comida) platillo.obtener(i);
            if(gramos.ingrediente1.cantidadGramos>=100){// verificamos que los ingredientes puestos sean mayor a 100 o iguaels
                SalidaTerminal.consola(gramos.getNombre()+"\n");// obtenemos el nombre de ese platillo
            }

        }

    }

    /**
     * Metodo que nos ayuda a mostrar todos los platillos que tengan un liquido en ellos
     */
    public void MostrarLiquidos(){
        for (int i=0;i<platillo.numElementos();i++){
            Comida liquidos=(Comida) platillo.obtener(i);
            if (liquidos.tipoPlatillo.equals(TipoPlatillo.Tipo_liquido)){// verifamos de que sea  la comida de tipo Liquido
                SalidaTerminal.consola(liquidos.getNombre()+"\n");// mostramos el nombre de ese platillo
            }
        }
    }

    /**
     * Metodo que nos muestra los platillos que solo tienen un procedimiento de cuchillo
     */
    public  void MostrarPlatillosConCuchillo(){

        for (int i=0;i< platillo.numElementos();i++) {
            Comida conCuchillo = (Comida) platillo.obtener(i);
            if (conCuchillo.tipoProcedimientoDeCoicina.equals(TipoProcedimientoDeCoicina.CUCHILLO)) {// verificamos de que sea la comida de tipo cuchillo
                SalidaTerminal.consola(conCuchillo.getNombre() + "\n");// mostramos la comida con ese dato

            }
        }
    }

    /**
     * Metodo que nos muestra la comida que no necesitan electricidad para crearlo
     *
     */
    public  void NoRequiereElectricidad(){
        for (int i=0;i< platillo.numElementos();i++) {
            Comida Electricidad = (Comida) platillo.obtener(i);//obtenemos los datos de las posiciones de cada platillo
            if (Electricidad.tipoProcedimientoDeCoicina.equals(TipoProcedimientoDeCoicina.aMANO) || Electricidad.tipoProcedimientoDeCoicina.equals(TipoProcedimientoDeCoicina.CUCHILLO)) {
                SalidaTerminal.consola(Electricidad.getNombre() + "\n");// regresamos el dato en caso de que no tenga electricidad ese platillo

            }
        }
    }

    /**
     * Metodo que nos ayuda a poder verificar o mostrar cuales platillos tienen vegetales o estan implementados con vegetales
     */
    public  void PlatilloVegetal(){
        for (int i=0;i< platillo.numElementos();i++) {// recorremos todos los datos de la lista ligada
            Comida PlatilloVegetal= (Comida) platillo.obtener(i);// obtenemos sus posiciones
            if (PlatilloVegetal.tipoPlatillo.equals(TipoPlatillo.TIPO_VEGETAL)) {// verificamos que la comida tenga el tipo vegetal
                SalidaTerminal.consola(PlatilloVegetal.getNombre() + "\n");//mostramos el nombre de el platiññp

            }
        }
    }


    /**
     * Metodo que nos ayuda a poder elimianr todos los platillos que tengan carne en ellos
     * @return   platillo Elminado
     */
    public Object EliminarPlatilloCarnivoro(){
        for (int i=0;i< platillo.numElementos();i++){
            Comida PlatoCarnivoro=(Comida) platillo.obtener(i);
            if (PlatoCarnivoro.tipoPlatillo.equals(TipoPlatillo.TIPO_conCarne)){// verificamos de que la comida tenga el tipo Con Carne
                platillo.eliminar(PlatoCarnivoro);// en caso de que si lo tenga eliminamos el dato de la lista
            }
        }
        return null;// si sale algo mal regresamos null
    }

}
