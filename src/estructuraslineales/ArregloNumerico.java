package estructuraslineales;

/**@author Alexis Ultreras Sotelo
 * @version 1.0
 * Clase donde se implementa todo lo necesario para hacer uso de la clase ArregloNumerio
 */
public class ArregloNumerico extends ArregloDatos {
    public ArregloNumerico(int capacidad) {
        super(capacidad);
    }


    /**
     * metodo que nos agrega solamente datos numericos a la lista
     *
     * @param info
     * @return info agregada
     */
    public int agregar(Object info) {
        if (verificacionD(info.toString())) {// verifica de que sea un dato numerico
            Double infoA = Double.parseDouble(info.toString()); // convertimos el dato a double
            return super.agregar(infoA); // agregamos el dato

        } else { // no  se agrego
            return -1;
        }

    }



    /**
     * esta clase nos ayuda  a verificar de que todo lo que se vaya agregando al arreglo sea solamente datos numerico
     *
     * @param info
     * @return true o false
     */
    public Boolean verificacionD(Object info) {
        try {
            Double.parseDouble(info.toString());
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * imprime los datos numericos
     */
    public void imprimir() {
        super.imprimir();
    }

    /**
     * busca algun dato agregado numerico, en caso de que si lo encuentre, regresa ese mismo dato
     *
     * @param info
     * @return info
     */
    public Double buscar(Object info) {
        if (verificacionD(info.toString())) { // verifica de que el dato sea entero
            Double infoBuscar = Double.parseDouble(info.toString());
            if (super.buscar(infoBuscar) != null) { // busca el dato agregado
                return infoBuscar; //regresa el numero inetero buscado
            }
        }
        return 0.0;
    }

    /**
     * modifica una posicion por un dato numerico actual
     *
     * @param info
     * @param posicion
     * @return regresa
     */
    public Object modificar(Object info, int posicion) {
        if (verificacionD(info.toString())) {
            Double infoM = Double.parseDouble(info.toString());
            return lote[posicion] = infoM;
        } else {
            return null;
        }
    }


    /**
     * multiplica el arreglo origina por un numero en espesifico
     *
     * @param escalar
     * @return true o false
     */
    public boolean xEscalar(Number escalar) {
        Double numeroEscalar = Double.parseDouble(escalar.toString()); // pasa el dato original a double
        if (!vacia()) {
            for (int i = 0; i < cantidadElementos(); i++) {
                Double Numerico_nuevo = (Double.parseDouble(lote[i].toString())) * escalar.doubleValue(); // multiplica la posicion que se de por el escalar
                lote[i] = Numerico_nuevo;
            }
            return true;
        }

        return false;
    }

    /**
     * suma el arreglo origina por un numero en espesifico
     *
     * @param escalar
     * @return true o false
     */
    public boolean sumaEscalar(Number escalar) {
        Double numeroEscalar = Double.parseDouble(escalar.toString()); // pasa el dato original a double
        if (!vacia()) {
            for (int i = 0; i < cantidadElementos(); i++) {
                Double Numerico_nuevo = (Double.parseDouble(lote[i].toString())) + escalar.doubleValue();// sumamos la posicion que se de por el escalar
                lote[i] = Numerico_nuevo; // mete el resultado a cada posicion de el arreglo
            }
            return true;
        }

        return false;
    }

    /**
     * suma dos arreglos en especifico con sus posiciones
     *
     * @param arreglo2
     * @return true o false
     */
    public boolean sumar(ArregloNumerico arreglo2) {
        if (cantidadElementos() == arreglo2.cantidadElementos()) {
            for (int i = 0; i < cantidadElementos(); i++) {
                Double elementoActual = (Double) obtener(i); // obtenemos las posiciones de el arreglo actual
                Double elementoSegundo = (Double) arreglo2.obtener(i);// obtenemos las posiciones de el segundo arreglo
                cambiar(i, elementoActual + elementoSegundo); // cambiamos las posiciones por la suma de ambas
            }
            return true;
        }
        return false;


    }

    /**
     * Multiplica el arreglo original por uno nuevo en cada una de sus posiciones
     *
     * @param arreglo2
     * @return true o false
     */
    public boolean Multiplicar(ArregloNumerico arreglo2) {
        if (cantidadElementos() == arreglo2.cantidadElementos()) {
            for (int i = 0; i < cantidadElementos(); i++) {
                Double elementoActual = (Double) obtener(i); // obtenemos las posiciones de el arreglo actual
                Double elementoSegundo = (Double) arreglo2.obtener(i);// obtenemos las posiciones de el segundo arreglo
                cambiar(i, elementoActual * elementoSegundo); // cambiamos las posiciones por la suma de ambas
            }
            return true;
        }
        return false;
    }


    /**
     * sacamos la potencia de las posiciones dadas por un numero a escalar
     * @param escalar
     * @return true o false
     */
    public boolean potencia(Number escalar){
        Double numeroEscalar=Double.parseDouble(escalar.toString()); // pasa el dato original a double
        if (!vacia()){
            for (int i=0;i<cantidadElementos();i++){
                Double Numerico_nuevo = (Math.pow(Double.parseDouble(lote[i].toString()),escalar.doubleValue()));// sumamos la posicion que se de por el escalar
                lote[i]=Numerico_nuevo; // mete el resultado a cada posicion de el arreglo
            }
            return true;
        }
        return false;
    }

    /**
     * metodo que nos ayuda a sacar la potencia de dos arreglos, obteniendola multiplicando sus potencias en cada posicion dada
     * @param arreglo2
     * @return true o false
     */
    public boolean potencia(ArregloNumerico arreglo2){
        if (cantidadElementos()==arreglo2.cantidadElementos()){
            for (int i=0; i<cantidadElementos();i++){
                Double elementoActual=(Double) obtener(i); // obtenemos las posiciones de el arreglo actual
                Double elementoSegundo=(Double) arreglo2.obtener(i);// obtenemos las posiciones de el segundo arreglo
                cambiar(i,Math.pow(elementoActual,elementoSegundo)); // cambiamos las posiciones por la suma de ambas
            }
            return true;
        }
        return false;
    }

    /**
     *  metodo que nos ayuda a multiplicar los datos de dos arreglos y al
     *  resultado se le va sumando el dato
     * @param arreglo2
     * @return sumaPunto
     */
    public double productoPunto(ArregloNumerico arreglo2){
        Double sumaPunto=0.0;
        if (cantidadElementos()==arreglo2.cantidadElementos()){ // verificamos de que  sea de misma longitud
            for (int i=0;i<cantidadElementos();i++){
                sumaPunto+=Double.parseDouble(arreglo2.lote[i].toString())*Double.parseDouble(lote[i].toString());// obtenemos la multiplicacion y la suma de los dos arreglos

            }
            return sumaPunto; // regresamos el resultado
        }
        return 0.0;

    }

    /**
     * obtenemos la magnitud / módulo / normal  de el arreglo orignal
     * @return raiz de sumaMagnitud;
     */
    public  Double modulo(){
        if (!vacia()){
            double sumaMagnitud=0.0;
            for(int i=0;i<cantidadElementos();i++){
                sumaMagnitud+=Math.pow(Double.parseDouble(lote[i].toString()),2);// obtenemos la potencia de los numeros por 2 y despues sumamos sus resultados
            }
            return  Math.sqrt(sumaMagnitud); // obtenemos la raiz de el dato
        }
        return 0.0;
    }

    /**
     * metodo que nos ayuda a obtener la norma euclidiana a partir de otro arreglo numerico
     * @param arreglo2
     * @return  Math.sqrt(sumaNormaEuclidiana)
     */
    public double normaEuclidiana(ArregloNumerico arreglo2) {
        Double sumaNormaEuclidiana = 0.0;
        Double potencia=0.0;
        if (cantidadElementos()==arreglo2.cantidadElementos()){ // verificamos de que el arreglo sea igual al original en longitud

            for (int i=0;i<cantidadElementos();i++) {
                Double cantidad1 = (Double) obtener(i);
                Double cantidad2 = (Double) arreglo2.obtener(i);
                potencia =Math.pow(cantidad2-cantidad1,2);// obtenemos la potencia para despues poder resta los datos
                sumaNormaEuclidiana+=potencia; // sumamos los resultados

            }
            return Math.sqrt(sumaNormaEuclidiana); // obtenemos la raiz
        }return 0.0;

    }

    /**
     * Suma todas las posiciones de un conjunto de arreglo actual con un conjunto de arreglo enviado por parametro
     * @param arreglos
     * @return suma
     *
     */
    public double sumarArreglosDatos(ArregloDatos arreglos){
        double suma = 0;
        if (arreglos.cantidadElementos()==cantidadElementos()){
            ArregloNumerico arregloNumerico = (ArregloNumerico) arreglos; //converitimos el arreglo origional a Numerico
            for(int i = 0; i<arregloNumerico.cantidadElementos(); i++){
                Double elemento1= Double.parseDouble(obtener(i).toString()); // obtenemos los datos de el arreglo 1
                Double elemento2= Double.parseDouble(arregloNumerico.obtener(i).toString()); // obtenemos los datos de el arreglo 2
                suma+=elemento1+elemento2;
            }
            return suma;
        }
        return 0.0;

    }

    /**
     *  debe sumar de uno por un un conjunto de arreglos dados de tipo ArregloNumeros almacenados en la variabl arreglos al arreglo actua
     * @param escalar
     * @return sumaEsc
     */
    public double sumarEscalar(ArregloNumerico escalar) {
        Double sumaEsc=0.0;
        if (escalar.cantidadElementos() >= 0 && escalar.cantidadElementos() <=cantidadElementos()) {
            for (int i = 0; i <escalar.cantidadElementos(); i++) {
                cambiar(i, Double.parseDouble(escalar.obtener(i).toString()) * // cambiamos los datos asignados mientras se multiplican entre ellos
                        Double.parseDouble(obtener(i).toString()));
                sumaEsc+=Double.parseDouble(escalar.obtener(i).toString()) // sumamos los datos que ya estan multiplicados
                        +
                        Double.parseDouble(obtener(i).toString());
            }return sumaEsc;
        }
        return 0.0;

    }


    /**
     * metodo que nos ayuda a obtener la  sumar del arreglo
     * actual todas las posiciones del que indica el arreglo llamado arregloIndices
     * @param arregloIndices
     * @return sumaArreglo
     */
    public double SumarIndices(ArregloNumerico arregloIndices){
        if (!arregloIndices.vacia() && !vacia()){
            double sumaArerglo=0.0;
            for (int i=0; i<cantidadElementos();i++) {
                double posicionActualArreglo = (double)arregloIndices.obtener(i); // obtenemos la posicion
                if ((enLimites((int)posicionActualArreglo))) sumaArerglo += (Double) obtener((int)posicionActualArreglo); // verificamos de que esten en el mismo limite para despues sumar los datos


            } return sumaArerglo;
        }else {
            return 0.0;
        }
    }


    /* metodo que nos ayuda a  regresar un arreglo conteniendo los elementos del arreglo
     *  actual que se obtienen del arreglo de índices arregloIndices, este es el que  contiene las
     *  posiciones de los índices de donde se obtendrán los datos a retornar.
     *
     * @param arregloIndice
     * @return arregloIndice
     */
    public ArregloDatos subLista(ArregloNumerico arregloIndice){
        return super.subLista(arregloIndice);
    }



    public boolean sonLinealmenteDep(ArregloDatos arreglosVectores){
        return false;
    }

    /**
     * metodo que verifica el resultado de las matrices, si el resultado es sero los vectores son ortogonales
     * en caso contrario no lo son
     * @param arreglo2
     * @return true o false
     */
    public boolean esOrtogonal(ArregloNumerico arreglo2){
        Double productoPunto = productoPunto(arreglo2);
        if(productoPunto!=null){//Se pudo calcular el producto punto
            return productoPunto.doubleValue()==0.0;
        }else{//No se pudo calcular el producto punto
            return false;
        }
    }

    /**
     * metodo que verifico si el arreglo actual es paralelo al arreglo pasado como argumento.
     * @param arreglo2
     * @return
     */
    public boolean esParalelo(ArregloNumerico arreglo2){
        if(arreglo2.cantidadElementos() == cantidadElementos() && !vacia()){//Ambos vectores tienen la misma cardinalidad
            double resDivicion = ((double)arreglo2.obtener(0))/((double) lote[0]);
            for(int pos=1; pos<=tope; pos++){//Recorremos nuestro vector desde la posicion 1
                double resDivicioni = ((double)arreglo2.obtener(pos))/((double) lote[pos]);
                if(resDivicioni!=resDivicion){//El escalar k no existe
                    return false;
                }
            }
            return true;//Existe el escalar k
        }else{
            return false;
        }
    }

    /**
     * Devuelve el indice del elemento mas grande.
     * @return Regresa el indice del elemento mas grande y regresa null enc aso de que la lista este vacia.
     * */
    public Integer obtenerIndiceMax(){
        if(!vacia()){
            int indice=0;
            double elemento = (double) lote[indice];
            for(int pos=1; pos<=tope; pos++){
                double elementoActual = (double) obtener(pos);
                if(elementoActual>elemento){
                    elemento = elementoActual;
                    indice=pos;
                }
            }
            return indice;
        }else{
            return null;
        }
    }


    /**
     * Devuelve el indice del elemento mas pequenio.
     * @return Regresa el indice del elemento mas pecenio y regresa null enc aso de que la lista este vacia.
     * */
    public Integer obtenerIndiceMin(){
        if(!vacia()){
            int indice=0;
            double elemento = (double) lote[indice];
            for(int pos=1; pos<=tope; pos++){
                double elementoActual = (double) lote[pos];
                if(elemento>elementoActual){
                    elemento = elementoActual;
                    indice=pos;
                }
            }
            return indice;
        }else{
            return null;
        }
    }

    /**
     * este metodo nos permite poder clonar un arreglo de puros numeros
     * @return
     */
    @Override
    public Object clonar() {
        ArregloNumerico clon = new ArregloNumerico(CAPACIDAD);
        //Recorro mi arreglo actual y agrega cada elemento en el clon
        for(int pos=0; pos<=tope; pos++){
            Object elementoActual = lote[pos];
            clon.agregar(elementoActual);
        }
        return clon;
    }



}
