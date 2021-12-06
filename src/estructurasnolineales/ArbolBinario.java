package estructurasnolineales;

import entradasalida.EntradaTerminal;
import entradasalida.SalidaTerminal;
import estructuraslineales.ListaEncadenada;
import estructuraslineales.ListaEncadenadaHash;
import estructuraslineales.registros.NodoDoble;

import javax.print.DocFlavor;

/**
 * Esta clase crear un árbol binario a petición del usuario.
 * @author Alexis Ultreras Sotelo
 * @version 2.0.
 */

public class ArbolBinario {
    protected NodoDoble raiz;
    protected int altura;

    public ArbolBinario(){
        raiz=null;
    }

    public boolean crearArbol(){
        SalidaTerminal.consola("Introduce la raíz del árbol: ");
        String datoNodo=EntradaTerminal.consolaCadena();
        NodoDoble nuevoNodo=new NodoDoble(datoNodo);
        if(nuevoNodo!=null){ //hay espacio
            raiz=nuevoNodo;
            crearArbol(raiz); //La raiz se vuelve subraiz para comenzar a agegar los hijos de ella
            return true;
        }else{ //no hay espacio
            return false;
        }
    }

    private void crearArbol(NodoDoble subRaiz) {
        //Agregar hijo izquierdo
        SalidaTerminal.consola("¿El nodo " + subRaiz.getDato() + " tiene hijo izquierdo? [S/N] ");
        String respuestaHijoIzquierdo = EntradaTerminal.consolaCadena();
        if (respuestaHijoIzquierdo.equalsIgnoreCase("S")) { //quiere agregar un hijo izquierdo
            SalidaTerminal.consola("Introduce el dato del hijo izquierdo de "+ subRaiz.getDato()+" : ");
            String datoNodo = EntradaTerminal.consolaCadena();
            NodoDoble nuevoNodoIzquierdo = new NodoDoble(datoNodo);
            if (nuevoNodoIzquierdo != null) { //si hay espacio
                subRaiz.setDirMemIzq(nuevoNodoIzquierdo);
                crearArbol(subRaiz.getDirMemIzq());
            }
        } //si no entra, no quiere agregar hijo izquierdo

        //Agregar hijo derecho
        SalidaTerminal.consola("¿El nodo " + subRaiz.getDato() + " tiene hijo derecho? [S/N] ");
        String respuestaHijoDerecho = EntradaTerminal.consolaCadena();
        if (respuestaHijoDerecho.equalsIgnoreCase("S")) { //quiere agregar un hijo derecho
            SalidaTerminal.consola("Introduce el dato del hijo derecho de "+ subRaiz.getDato()+": ");
            String datoNodo = EntradaTerminal.consolaCadena();
            NodoDoble nuevoNodoDerecho = new NodoDoble(datoNodo);
            if (nuevoNodoDerecho != null) { //si hay espacio
                subRaiz.setDirMemDer(nuevoNodoDerecho);
                crearArbol(subRaiz.getDirMemDer());
            }
        } //si no entra, no quiere agregar hijo derecho
    }

    public void inOrden(){
        inOrden(raiz);
    }

    private void inOrden(NodoDoble subRaiz){
        //IRD
        if(subRaiz!=null){ //hay alguna subRaiz válida
            //I
            inOrden(subRaiz.getDirMemIzq());
            //R
            SalidaTerminal.consola(subRaiz.getDato() + " ");
            //D
            inOrden(subRaiz.getDirMemDer());
        } //en caso contrario, es deci la subRaiz es nula, no hacemos nada, CASO BASE
    }

    public void preOrden(){
        preOrden(raiz);
    }

    private void preOrden(NodoDoble subRaiz){
        //RID
        if(subRaiz!=null){ //hay alguna subRaiz válida
            //R
            SalidaTerminal.consola(subRaiz.getDato() + " ");
            //I
            preOrden(subRaiz.getDirMemIzq());
            //D
            preOrden(subRaiz.getDirMemDer());
        } //en caso contrario, es deci la subRaiz es nula, no hacemos nada, CASO BASE
    }

    public void postOrden(){
        postOrden(raiz);
    }
    private void postOrden(NodoDoble subRaiz){
        //IDR
        if(subRaiz!=null){ //hay alguna subRaiz válida
            //I
            postOrden(subRaiz.getDirMemIzq());
            //D
            postOrden(subRaiz.getDirMemDer());
            //R
            SalidaTerminal.consola(subRaiz.getDato() + " ");
        } //en caso contrario, es deci la subRaiz es nula, no hacemos nada, CASO BASE
    }

    /**
     * regresa el resultado de al altura de el arbol
     * @return
     */
    public int retornarAltura() {
        altura = 0;
        retornarAltura(raiz, 1);// mandamos el nivel y la raiz al metodo recursivo

        return altura;
    }

    /**
     * Calcula la altura que tiene el arbol binario y regresa el resutlado
     * @param raiz
     * @param nivel
     */
    private void retornarAltura(NodoDoble raiz, int nivel) {
        if (raiz != null) {// en caso de que la raiz sea diferente a null
            retornarAltura(raiz.getDirMemIzq(), nivel + 1);// generamos el metodo recursivo en la liga izquierda y aumentamos uno
            if (nivel > altura) {
                altura = nivel;
            }
            retornarAltura(raiz.getDirMemDer(), nivel + 1);// generamos el metodo recursivo en la liga derecha y aumentamos uno

        }
    }




    /**
     *Método que permite encontrar el nivel donde se encuentra un nodo.
     * @param subRaiz NodoDoble con la información actual.
     * @param nodoBusqueda raíz a buscar
     * @param posicion el número de posicion actual
     */
    private void nivelNodo(NodoDoble subRaiz, Object nodoBusqueda, int posicion){
        if (subRaiz != null && !subRaiz.getDato().equals(nodoBusqueda)) {
            nivelNodo(subRaiz.getDirMemIzq(),nodoBusqueda, posicion+1);// seguimos avanzando y asignamos una posicion mas
            nivelNodo(subRaiz.getDirMemDer(),nodoBusqueda, posicion+1);// seguimos avanzando y adignamos una posicion mas
        }
        if(subRaiz != null && subRaiz.getDato().equals(nodoBusqueda)){
            altura= posicion+1;// obtenemos la altura de el nodo
        }
    }

    int alto=0;
    /**
     * Este metodo devuelve el numero de elementos de un nivel
     * @param nivel nivel
     * @return numero de elementos
     */
    public int obtenerTotalDeElementosEnNivel(int nivel){
        alto=0;
        return obtenerTotalDeElementosEnNivel1(raiz,1,0,false,0);
    }

    /**
     * Este metodo recursivo busca en el arbol y cuenta los elementos de un dicho nivel
     * @param subRaiz raiz
     * @param altura altrura 1
     * @param nivel nivel 0
     * @param bandera bandera false
     * @param cont cont 0
     * @return numero de elementos nivel
     */
    private int obtenerTotalDeElementosEnNivel1(NodoDoble subRaiz,int altura,int nivel,Boolean bandera,int cont){
        if(subRaiz!=null) {
            obtenerTotalDeElementosEnNivel1(subRaiz.getDirMemIzq(),altura+1,nivel,bandera,cont++);// mandamos llamar el metodo recursivo
            if(altura>alto && bandera==false){
                alto=altura;// asignamos los datos de altura en alto
            }
            obtenerTotalDeElementosEnNivel1(subRaiz.getDirMemDer(),altura+1,nivel,bandera,cont++);
            if(alto==nivel){// en caso de que sean iguales
                cont++;// aumentamos los datos
                alto=0;

            }
            return cont;
        }else {
            return 0;
        }

    }

    /**
     * Método que permite comprobar que tipo de nodo es, si raíz, hoja, o intermitente.
     * @param nodoB Nodo a buscar.
     */
    public void tipoNodo(Object  nodoB){
        if(raiz.getDato().toString().equalsIgnoreCase(nodoB.toString())){
            SalidaTerminal.consola("Nodo raíz");
        }else{
            tipoNodo(nodoB,raiz,raiz);
        }
    }


    /**
     * Método que permite ver el tipo de nodo que existe.
     * @param nodoB Nodo a buscar.
     * @param subRaiz Nodo actual.
     * @param padre Nodo padre actual.
     */
    private void tipoNodo(Object nodoB, NodoDoble subRaiz,NodoDoble padre){
        if(subRaiz!=null && !subRaiz.getDato().equals(nodoB)){
            tipoNodo(nodoB,subRaiz.getDirMemIzq(), subRaiz);
            tipoNodo(nodoB,subRaiz.getDirMemDer(), subRaiz);
        }else{
            if(subRaiz!=null){
                if(subRaiz.getDato().toString().equalsIgnoreCase(nodoB.toString())){
                    if(subRaiz.getDirMemIzq()==null && subRaiz.getDirMemDer()==null){// si no hay datos de bajo de el nodo actual en donde se encuentra
                        SalidaTerminal.consola("Nodo  hoja y su padre es: "+padre.getDato());// entonces es un nodo con padre tal
                    }
                    else if(subRaiz.getDirMemIzq()!= null && subRaiz.getDirMemDer()!=null){
                        SalidaTerminal.consola("Nodo  intermedio y su padre es: "+padre.getDato());// entonces es un nodo intermidio
                    }
                }
            }
        }
    }

}
