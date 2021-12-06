package registros.Galerias;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructurasnolineales.Tabla3D;
import registros.Galerias.Actividades.Autografos;
import registros.Galerias.Actividades.Exponer;
import registros.Galerias.Actividades.Pintar;
import registros.Galerias.Actividades.Viajar;

/**
 * Esta clase almacena toda la logica de programacion para obter los datos que desee el usuario para el pintor
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class GaleriaInfo {
    private Tabla3D TablaGaleria;
    private ArregloDatos pintores;


    /**
     * constructor que nos  inicializa los datos de pintores y los años que tiene
     * @param pintores
     * @param anio
     *
     */
    public GaleriaInfo(ArregloDatos pintores,int anio) {
        this.pintores=pintores;
        TablaGaleria = new Tabla3D(pintores.cantidadElementos(),4,anio);
    }

    /**
     * constructor que inicializa el mismo pintor
     * @param pintores
     */
    public GaleriaInfo(ArregloDatos pintores) {
        this.pintores=pintores;
        TablaGaleria = new Tabla3D(pintores.cantidadElementos(),4,10);
    }

    /**
     * metodo que nos ayuda a agregar las actividades de el pintor
     * @param tarea
     * @param NombreDePintor
     * @return si se agrega el dato regresa 1, si no regresa -1
     */
    public boolean AgregarActividadPintor(ArregloDatos tarea,String NombreDePintor,int anio){
            Integer obtenNombre= (Integer) pintores.buscar(NombreDePintor.toString());
            if (obtenNombre!=null){
                for (int i=0;i<tarea.cantidadElementos();i++){
                    if (!TablaGaleria.asignarCelda(obtenNombre,i,anio-1,tarea.obtener(i))){
                        return false;
                    }
                }
                return true;
            }
            return false;



    }

    /**
     * este metodo nos ayuda a verificar la actividad mas popular en el año que se de por parametro
     * @param anio
     * @return regresa la actividad pintar,exponer...
     */
    public Object actividadMasPopular(int anio) {
        int contPintar=0;
        int contExponer=0;
        int contFirmarA=0;
        int contViajar=0;
        for (int i=0;i<TablaGaleria.getFilas();i++){
            for (int j=0;j<TablaGaleria.getColumnas();j++){
                Object elementoObtenido = TablaGaleria.obtenerCelda(i,j,anio-1);// se sacan los datos de la tabla3d
                if (elementoObtenido!=null){
                    if (elementoObtenido instanceof Pintar && ((Pintar) elementoObtenido).getNombre()!=null) contPintar++;// en caso de que el dato sea pintor se asigna un contador mas
                    if (elementoObtenido instanceof Exponer && ((Exponer) elementoObtenido).getNombreEvento()!=null) contExponer++; // se asigna un contador mas
                    if (elementoObtenido instanceof Autografos && ((Autografos) elementoObtenido).getLugar()!=null) contFirmarA++; // se asigna un contador mas
                    if (elementoObtenido instanceof Viajar && ((Viajar) elementoObtenido).getLugar()!=null) contViajar++; // se asigna un contador mas
                }
            }
        }
        if (contPintar>contExponer && contPintar>contFirmarA && contPintar>contViajar) return "Pintar"; // si es mayor entonces el dato es de pintor
        else if (contExponer>contPintar && contExponer>contFirmarA && contExponer>contViajar) return "Exponer"; // el dato mayor es Exponer
        else if (contFirmarA>contPintar && contFirmarA>contExponer && contFirmarA>contViajar) return "Firmar Autografos"; // el dato mayor es Autografo
        else if (contViajar>contPintar && contViajar>contExponer && contViajar>contFirmarA) return "Viajar";// el dato mayor es Viajar
        else return null;
    }






    /**
     * Indica el pintor que le gusta firmar más autógrafos.
     * @return regresa el pintor que hizo mas firmas, en caso contrario regresa null.
     */
    public Pintor pintorConMasFirmas(){
        int pintorConMasFirmas=0;
        Pintor pintorFrimas=null;
        for (int posFila=0;posFila<pintores.cantidadElementos();posFila++){// recorremos la lista de pintores
            int firmas=0;
            for (int posAnio=0;posAnio<TablaGaleria.getProfundidad();posAnio++){// recorremos la lista de la tabla en profundidad
                for (int posColumna=0;posColumna<TablaGaleria.getColumnas();posColumna++) {// recorremos la tabla en columnas
                    Object elementoActual = TablaGaleria.obtenerCelda(posFila, posColumna, posAnio);//obtenemos las celdas por cada año que pasa
                    if (elementoActual != null && elementoActual instanceof Autografos) { // verificamos de que el elemento sea de tipo Autografo
                        Autografos firmaActual = (Autografos)elementoActual;// casteamos el dato a Autogramos
                        if (firmaActual.getLugar() != null) {// si el lugar es diferente a null
                            firmas++;// aumentamos una posicion mas
                        }
                    }
                }
            }
            if (firmas>pintorConMasFirmas){// si la firmas son mayor que el pintor
                pintorConMasFirmas=firmas;//asignamos el pintor con mas firmas
                pintorFrimas = (Pintor) pintores.obtener(posFila);// regresamos el dato
            }



        }return pintorFrimas; // regresa el dato pintor

    }




    /**
     * Indica el anio y en qué evento el pintor X pintó o expuso.
     * @param pintor nombre del pintor a buscar.
     */
    public void anioYEvento(String pintor){
        Integer posicionPintor = (Integer)pintores.buscar(pintor); // obtenemos la posicion respecto a el pintor
        if (posicionPintor!=null){
            SalidaTerminal.consola("El pintor "+pintores.obtener(posicionPintor)+" expuso:\n");
            for (int posProfundidad=0;posProfundidad<TablaGaleria.getProfundidad();posProfundidad++){// obtenemos posiciones
                for (int posColumna=0;posColumna<TablaGaleria.getColumnas();posColumna++) {// obtenemos posiciones
                    Object elementoActual = TablaGaleria.obtenerCelda(posicionPintor, posColumna, posProfundidad);// obtenemos la posicion de toda la matriz
                    if (elementoActual != null && elementoActual instanceof Exponer) {// si el dato es de tipo Exponer
                        Exponer pintorExpuso = (Exponer) elementoActual; // casteamos el dato a Exponer
                        if (pintorExpuso.getNombreEvento() != null)// si el dato es diferente de null
                            SalidaTerminal.consola("Anio " + (posProfundidad + 1) + " en " + pintorExpuso.getLugar() + "\n"); // imprimimos el año junto con el lugar dado
                    }
                }
            }
            SalidaTerminal.consola("\n");
        }else SalidaTerminal.consola("No existe el pintor\n");// en caso de que no exista se regresa el mensaje
    }


    /**
     * este año nos ayuda a identificar el año cdonde no se obtuvo  pinturas en la galeria
     * @return regresa el anio
     */
    public Integer AnioConMenosPinturas(){
        int pintorMenor=0;
        Integer anio=null;
        for (int k = 0; k < TablaGaleria.getProfundidad(); k++){
            int contador=0;
            for (int i=0;i<pintores.cantidadElementos();i++){
                for (int j = 0; j < TablaGaleria.getColumnas(); j++) {
                    Object ElementoActual=TablaGaleria.obtenerCelda(i,j,k);// obtenemos las posiciones de la tabla 3d
                    if (ElementoActual instanceof  Pintar){
                        Pintar elementoPintura= (Pintar) ElementoActual;// casteamos los datos que sean obtenidos  si es que son pintores
                        if (elementoPintura.getNombre()!=null){// si no es null
                            contador++; // aumentamos posicion
                        }
                    }
                }
            }
            if (pintorMenor<contador){
                pintorMenor=contador; // dato con menor anio
                anio=k+1;
            }
        }
        return anio;

    }

    /**
     * Indica el anio en el que todos los pintores expusieron.
     * @return regresa el anio en el que todos los pintores expusieron, en caso contrario regresa false.
     */
    public Integer todosLosPintoresExpusieron(){
        int PintoresExpusieron=0;
        Integer anio=null;

        for (int posProfundidad=0;posProfundidad<TablaGaleria.getProfundidad();posProfundidad++){
            int cont=0;
            for (int posFila=0;posFila<pintores.cantidadElementos();posFila++){
                for (int posColumna=0;posColumna<TablaGaleria.getColumnas();posColumna++) {
                    Object elementoActual = TablaGaleria.obtenerCelda(posFila, posColumna, posProfundidad);// obtenemos las posiciones de la tabla
                    if (elementoActual != null && elementoActual instanceof Exponer) {// en caso de que sea exponer
                        Exponer exposicionActual = (Exponer) elementoActual; // casteamos los datos
                        if (exposicionActual.getLugar() != null) cont++; // asignamos un contador donde aumenta cada posicion
                    }
                }
            }
            if (cont>PintoresExpusieron && cont>=TablaGaleria.getProfundidad()){// si es mayor
                PintoresExpusieron=cont;
                anio=posProfundidad+1;
                // regresamos el dato  que es el año
            }
        }
        return anio;
    }


    /**
     * este metodo nos indica cual fue el pintor que viajo mas mediante su año
     * @param pintorNombre
     * @return  anio
     */

    public  Object dedicoMasTiempoAviajar(Object pintorNombre){
        int posicionViaje=0;
        Integer posicionPintor = (Integer)pintores.buscar(pintorNombre.toString());
        Integer anio=null;
        for (int j=0;j<TablaGaleria.getProfundidad();j++){
            int cont=0;
            for (int i=0;i<pintores.cantidadElementos();i++){
                for (int k=0;k<TablaGaleria.getColumnas();k++){
                    Object ElemntoBuscar=TablaGaleria.obtenerCelda(i,k,j); // obtenemos los datos de profundidad en la matriz 3d
                    if (ElemntoBuscar instanceof  Viajar){
                        Viajar viajeActual= (Viajar) ElemntoBuscar;
                        if (viajeActual.getLugar()!=null){
                            cont++;// aumentar  cada vex que no sea null
                        }
                    }
                }
            }
            if (cont>posicionViaje){
                posicionViaje=cont;// es el dato mayor
                anio=j+1;
            }

        }
        return anio;

    }


    /**
     * obtiene en la clase exponer el  pintor con mas aforo
     */
    public void eventoMayorAforo(){
        Exponer contador=null;
        for (int i=0;i<pintores.cantidadElementos();i++){
            for (int j=0;j< TablaGaleria.getColumnas();j++){
                for (int k=0; k< TablaGaleria.getProfundidad();k++){
                    Object elememtnEvento=TablaGaleria.obtenerCelda(i,j,k);
                    if (elememtnEvento instanceof Exponer){
                        Exponer nuevaEx=(Exponer) elememtnEvento;
                        if (nuevaEx.getNombreEvento()!=null){
                           if (contador==null || nuevaEx.getAforo()>contador.getAforo()){// verfivicamos cual es el mayor de los dos datos
                               contador=nuevaEx;// asignamos en posicion 1 el dato de cada uno
                           }
                        }
                    }
                }
            }
        }
        SalidaTerminal.consola(contador+"\n");// regresamos el contador


    }

    /**
     * este metodo nos ayuda a imprimir todos los datos que se encuentran dentro de la tabla 3d
     */
    public  void imprimir(){
        TablaGaleria.imprimirxColumnas();
    }





}