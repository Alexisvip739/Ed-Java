package registros.cosecha;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;

/**
 * Clase que implementa los datos e informacion de Campesino
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Campesino {
    private  String NombreCampesino;
    private ArregloDatos listaCultivo;

    public Campesino(String NombreCampesino) {
       this.NombreCampesino=NombreCampesino;
       listaCultivo= new ArregloDatos(6);
    }



    /**
     * Metodo que agrega cultivos hacia un Campesino en particular
     * @param cultivo
     * @return true o false
     */
    public  boolean AgregarCultivo(Cultivo cultivo){
        Integer agregarCultivo=(Integer) listaCultivo.agregar(cultivo);
        if(agregarCultivo==1){
            return true;// si agrego el cultivo
        }else {
            return false; // no agrego el cultivo
        }
    }



    /**
     * busca un cultivo dentro de la lista de los cultivos de un campesino
     * @param cultivo
     * @return posicion
     */
    public  Cultivo buscarCultivo(String cultivo){
        if (!listaCultivo.vacia()){ // verifica de que la lista no este vacia
            Integer posicion=(Integer) listaCultivo.buscar(cultivo);
            if (posicion!=null){
                return  (Cultivo) listaCultivo.obtener(posicion); // regresa el cultivo en la posicion que se encuentre en la lista
            }else{
                return null;
            }
        }else{
            return  null;
        }

    }

    /**
     * Imprime las toneladas de los cultivos de un campesino en espesifico
     * @param cultivo
     */
    public void imprimirToneladasCultivo(String cultivo){
        Cultivo cultivo_A_Imprimir = buscarCultivo(cultivo);
        if (cultivo_A_Imprimir!=null){
            cultivo_A_Imprimir.imprimirToneladas();
            SalidaTerminal.consola("\n");
        }
    }


    /**
     * imprime los campesinos agregados
     */
    public void imprimirCampesino(){
         listaCultivo.imprimir();
    }


    /**
     * obtiene las toneladas que se produjeron por año de un cultivo en espesifico
     * @param cultivo
     * @param anio
     * @return tonelada
     */
    public  Double obtenerToneladaPorAnio(String cultivo,int anio){
        Double tonelada=null;
        Cultivo CultivoCampesino=buscarCultivo(cultivo);
        if (CultivoCampesino!=null){
            tonelada=CultivoCampesino.obtenerTonelada(anio);

        }
        return  tonelada;
    }


    /**
     * regresa la cantidad de cultivos dentro de el arreglo que cultivos que contiene un campesino
     * @return listaCultivo
     */
    public int cantidadDeElementosCampesino(){
        return listaCultivo.cantidadElementos();
    }

    /**
     * Obtiene un cultivo en dicha posicion que se almacene
     * @param indice
     * @return listaCultivo
     */
    public  Cultivo obtenerCultivo(int indice){
        return (Cultivo) listaCultivo.obtener(indice);
    }


    /**
     * Este metodo nos indica las toneladas mas grandes que tiene el camesino respecto a un anio en particular
     * @param anio
     * @return cultivoMayorTonelada
     */
    public Cultivo ProductoConMayorToneladas(int anio){
        Cultivo cultivoMayorTonelada=null;
        if (!listaCultivo.vacia()){
            Double mayorToneladaActual=null;
            for (int i=0;i<listaCultivo.cantidadElementos();i++) { // recorre los elementos de el arreglo
                Cultivo cultivoActual = (Cultivo) listaCultivo.obtener(i);// obtiene la posicion de dicho cultivo
                Double toneladaActual = obtenerToneladaPorAnio(cultivoActual.toString(), anio); // obtiene las tonaladas actuales de el dato y junto en el anio que esta
                if (mayorToneladaActual == null && toneladaActual != null) {
                    mayorToneladaActual = toneladaActual; // al primer dato se le asigna el valor
                    cultivoMayorTonelada = cultivoActual; // despues de que se ingrese el primer dato se le asigna al segundo, y asi hasta que se termine el ciclo
                } else if (toneladaActual != null) {
                    if (toneladaActual > mayorToneladaActual) {
                        mayorToneladaActual = toneladaActual;
                        cultivoMayorTonelada = cultivoActual;
                    }
                }
            }
        }
        return  cultivoMayorTonelada;
        
    }


    public Double promedioCultivo(String cultivo){
        // Buscamos el cultivo en la lista  de el arreglo
        Cultivo cultivoCampesino = buscarCultivo(cultivo);
        if (cultivoCampesino!=null){// si esta agregado en la lista
            return cultivoCampesino.obtenerPromedio(); // obtiene el promedio de dicho cultivo
        }else{ return null;}
    }



    /**
     * Este metodo nos indica  el produce que tiene más de 50 toneladas en promedio por año
     * @param tonelada
     * @return
     */
    public Cultivo productoMasProducidoEnPromedio(double tonelada){
        ArregloDatos cultivosProducidos = new ArregloDatos(listaCultivo.cantidadElementos()-1);

        if (!listaCultivo.vacia()){
            // Recorremos la lista, por cada elemento obtenido
            for (int posicion=0;posicion<listaCultivo.cantidadElementos();posicion++){
                Cultivo cultivoTemp = (Cultivo)listaCultivo.obtener(posicion); // obtiene el dato en dicha posicion

                Double promedioTemp= cultivoTemp.obtenerPromedio();
                cultivosProducidos.agregar(promedioTemp); // agrega el promedio de el cultivo
            }
            Integer posicionDelProducto = (Integer) obtenPosicionMayor(cultivosProducidos);
            if (posicionDelProducto!=null){
                if ((Double)cultivosProducidos.obtener(posicionDelProducto)>tonelada){
                    return (Cultivo)listaCultivo.obtener(posicionDelProducto); // regresa el dato con el cultivo
                }
            }
        }
        return null;
    }

    /**
     * obtiene la poscion mayor de el producto dado en productoMasProducidoEnPromedio
     * @param arreglo
     * @return Mayor
     */
    private Integer obtenPosicionMayor(ArregloDatos arreglo) {
        Double Mayor = null;
        for (int i=0;i<arreglo.cantidadElementos();i++){
            Double posicionActual = (Double) arreglo.obtener(i); // recorre las posiciones de el arreglo
            if (posicionActual!=null){
                if (Mayor==null) {Mayor=posicionActual;}
                else if (posicionActual>Mayor){ Mayor=posicionActual;}
            }
        }return (Integer) arreglo.buscar(Mayor);
    }



    @Override
    public String toString() {
        return "Campesino{" +
                "NombreCampesino='" + NombreCampesino + '\'' +
                '}';
    }
}
