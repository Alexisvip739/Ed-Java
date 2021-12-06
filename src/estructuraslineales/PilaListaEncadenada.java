package estructuraslineales;

import entradasalida.SalidaTerminal;
import estructuraslineales.registros.Nodo;

/**
 * Clase que implementa toda la funcionalidad de una pila en una pila de lista ligada
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class PilaListaEncadenada implements LoteDatos {

    protected ListaEncadenada pila;


    public PilaListaEncadenada() {
        pila = new ListaEncadenada();

    }
    /**
     * verifica si la pila esta  vacia
     *
     * @return true o false
     */
    @Override
    public boolean vacio() {
        return pila.vacia();
    }


    /**
     * verifica que este llena. (una lista ligada nunca estara llena)
     *
     * @return
     */
    @Override
    public boolean lleno() {
        return false;
    }

    /**
     * asigna una posicion dentro de la pila lista
     *
     * @param elemento
     * @return true o false
     */
    @Override
    public boolean poner(Object elemento) {
        if (elemento != null) {
            pila.agregar(elemento);
            return true;
        }
        return false;
    }


    /**
     * quita la posicion que va dentro de la pila lista
     *
     * @return datoEliminado
     */
    @Override
    public Object quitar() {
        return pila.eliminar();

    }


    /**
     * imprime el orden de las datos dentro de la pila
     */
    @Override
    public void imprimir() {
        pila.imprimir();


    }


    /**
     * metodo que nos ayuda a localizar la ultima posicion dentro de la pila
     * @return pila
     */
    @Override
    public Object verTope() {
        return pila.verTope();
    }

    /**
     * metodo que nos ayuda a verificar si una expresion esta correctamente alineada con los parentesis
     * @param pilaDatos
     * @return true o false
     */
    public Boolean validarExpresion(PilaListaEncadenada pilaDatos){
        int cont1=0;// representa (
        int cont2=0;// representa )
        String dato="";
        Nodo temp=pilaDatos.pila.frente;// obtenemos el nodo de frente en la pila de los datos
        while (temp!=null){
            dato=pilaDatos.quitar().toString();// quitamos la posicion de la pila
            if(dato.equals("(")){// en caso de que el dato quitado sea un parentecis inicial
                cont1++;// aumentamos la posicion en 1
            }else if(dato.equals(")")){// en caso contrario
                cont2++;// aumentamos la posicion de el otro parentesis
            }
            temp=temp.getDirMemDer();// seguimos avanzando
        }
        if(cont1==cont2){// al momento de que termine el recorrimiento de los datos y los contadores de los dos
            // parentesis sea iguales, entonces los parentesis estan alineados
            return true;
        }else{
            return false;
        }
    }
}
