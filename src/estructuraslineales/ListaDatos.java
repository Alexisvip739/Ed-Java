package estructuraslineales;

/**
 * Esta interface administra la funcionalidad de una lista de datos.
 * @author Clase de ED.
 * @version 2.0
 */
public interface ListaDatos {

    /**
     * Determina si una lista de datos esta vacia.
     * @return Regresa <b>true</b> si la lista esta vacia, <b>false</b> en caso contrario.
     */
    public boolean vacia();

    /**
     * Inserta al final de la lista un elemento proporcionado.
     * @param elemento Es el dato que se desea agregar a la lista.
     * @return Regresa la posicion de memoria (indice) en donde se agrega el elemento, o -1 en caso contrario.
     */

    public int agregar(Object elemento);

    public Object verTope();

    public void imprimir();

    public void imprimirOrdenInverso();

    public Object buscar(Object elemento);

    public Object eliminar(Object elemento);
//________

    /**
     * verifica de que dos listas sean iguales
     * @param listaDatos2
     * @return true o false
     */
    public boolean esIgual(Object listaDatos2);

    /**
     * obtiene el dato de una posicion dada dentro de el arreglo
     * @param indice
     * @return datoBuscado
     */
    public Object obtener(int indice);


    /**
     * Cambia el elemento  por un nuevo elemento y el numero de veces que se repite
     * @param elementoViejo
     * @param elementoNuevo
     * @param numVeces
     * @return true o false
     */
    public boolean cambiar(Object elementoViejo, Object elementoNuevo, int numVeces);

    /**
     * busca un valor dentro de el arreglo
     * @param elemento
     * @return ocurrencias
     */
    public ArregloDatos buscarValores(Object elemento);

    /**
     * elimina la ultima posicion de el arreglo
     * @return info Borrada
     * */
    public Object eliminar();

    /**
     * agrega una nueva lista a la lista actual
     * @param listaDatos2
     * @return true o false
     */
    public boolean agregarLista(Object listaDatos2);

    /*
    invierte los datos de la lista actual
     */
    public void  invertir();

    /**
     * vacia los datos actuales de la lista
     */
    public void vaciar();

    /**
     * cuenta los numero de veces que se repite un dato
     * @param elemento
     * @return elemento
     */

    public int contar(Object elemento);

    /**
     * elimina la lista agregada
     * @param listaDatos2
     * @return listaDatos2
     */
    public boolean eliminarLista(Object listaDatos2);

    /**
     * rellena los datos por un elemento y la cantidad deceada
     * @param elemento
     * @param cantidad
     */
    public void rellenar(Object elemento, int cantidad);

    /**
     * clona el arreglo original
     * @return
     */
    public Object clonar();

    /**
     * genera una sublista de el mismo arreglo actual con un muestreo de algun inicio y hasta cierto final
     * @param indiceInicial
     * @param indiceFinal
     * @return Contador
     */
    public Object subLista(int indiceInicial, int indiceFinal);

    /**
     * Verifica de que una sierta parte de objetos agregados en una lista nueva sean iguales
     * a la esa misma cantidad de objetos pero que esten en el arreglo actual de datos
     * @param lista2
     * @return true || false
     */
    public  boolean esSublista(ListaDatos lista2);


    /**
     *  Debe dejar en la lista actual solo los elementos que se encuentran
     * @param lista2
     * @return true o false
      */
    public boolean retenerLista(ListaDatos lista2);

    /**
     * Este método insertará en la posición indice el contenido de
     * elemento.
     * @param indice
     * @param elemento
     * @return true o false
     */
    public boolean insertar(int indice, Object elemento);

    /**
     * Cambia la lista original por la lista 2 agregada
     * @param listaDatos2
     * @param listaDatos2Nuevos
     * @return true o false
     */
    public boolean cambiarLista(ListaDatos listaDatos2, ListaDatos listaDatos2Nuevos);
    /**
     * este metodo nos ayuda a Copiar el contenido de listaDatos2 a la lista actual.
     * @param listaDatos2
     * @return true o false
     */
    public boolean copiarLista(ListaDatos listaDatos2);


    /**
     *indica el límite superior en los valores a rellenar, siempre y cuando
     * sea numérico
     * @param elemento
     */
    public void rellenar(Object elemento);

    public Object regresarFrente();

    public Object regresarFin();






}
