package registros.cosecha;

import estructuraslineales.ArregloDatos;

/**
 * Clae que implementa toda la informacion necesaria sobre el cultivo
 * @author Alexis Ultreras Sotelo
 * @version  1.0
 */
public class Cultivo {
    private  String NombreCosecha;
    private ArregloDatos ListaToneladas;
    private  Double FechaMenorCosecha;
    private  Double FechaMayorCosecha;


    public Cultivo(String nombreCosecha, int capacidad) {
        NombreCosecha = nombreCosecha;
        ListaToneladas= new ArregloDatos(capacidad);
    }

    public String getNombreCosecha() {
        return NombreCosecha;
    }

    public void setNombreCosecha(String nombreCosecha) {
        NombreCosecha = nombreCosecha;
    }

    public ArregloDatos getListaToneladas() {
        return ListaToneladas;
    }

    public void setListaToneladas(ArregloDatos listaToneladas) {
        ListaToneladas = listaToneladas;
    }

    public Double getFechaMenorCosecha() {
        return FechaMenorCosecha;
    }

    public void setFechaMenorCosecha(Double fechaMenorCosecha) {
        FechaMenorCosecha = fechaMenorCosecha;
    }

    public Double getFechaMayorCosecha() {
        return FechaMayorCosecha;
    }

    public void setFechaMayorCosecha(Double fechaMayorCosecha) {
        FechaMayorCosecha = fechaMayorCosecha;
    }


    public Double obtenerTonelada(int indice){
        return (Double) ListaToneladas.obtener(indice);
    }

    public void imprimirToneladas(){
        if(ListaToneladas!=null){ListaToneladas.imprimir();}
    }


    /**
     * obtiene el promedio de dicho cultivo
     * @return sumaPromio/ListaToneladas.cantidadElementos()
     */
    public  Double obtenerPromedio(){
        double sumaPromedio=0.0;
        if(!ListaToneladas.vacia()){
            for (int i=0;i<ListaToneladas.cantidadElementos();i++){
                sumaPromedio=sumaPromedio+ (double) ListaToneladas.obtener(i);// suma los elementos de la posicion que va recorriendo

            }
            // Despues de sumar los datos se dividen entre la cantidad que son para poder sacar el promedio
            return  sumaPromedio/ListaToneladas.cantidadElementos();
        }else {
            return  null;
        }
    }

    /**
     * Agrega el peso en toneladas de los cultivos
     * @param cantidad
     * @return true o false
     */
    public  boolean AgregarTonelada(Double cantidad){
            Integer AgregarTonelada= (Integer) ListaToneladas.agregar(cantidad);
            if (AgregarTonelada==1){
                if (ListaToneladas.cantidadElementos()==1){ // si es 1 si se agrego
                    FechaMenorCosecha=cantidad; // al ingresar el primer dato lo asigna a cantidad
                    FechaMayorCosecha=cantidad; // si entra otro dato despues de ese lo asigna a cantidad
                }else {
                    FechaMenorCosecha=menorTonelada();
                    FechaMayorCosecha=mayorTonelada();
                }
                return  true;
            }else {
                return false;
            }

    }

    /**
     * Regresa el dato que tiene la mayor tonelada
     * @return MayorTon
     */
    public  Double mayorTonelada(){
        Double MayorTon=0.0;
        if(ListaToneladas.vacia()==false){
            Double cantidadActual=null;
            for(int i=0;i<ListaToneladas.cantidadElementos();i++){
                cantidadActual=Double.parseDouble(ListaToneladas.obtener(i).toString());// obtiene la cantidad Actual en toneladas de la lista
                if (cantidadActual>MayorTon){// verifica de que si la cantidad actual es mayor a la toneladaMayor
                    MayorTon=cantidadActual;// se le asigna a la variable para  obtener el mayor dato
                }
            }
        }
        return MayorTon;
    }

    /**
     * Verifica el dato que tiene menor tonelada
     * @return MayorTon
     */
    public  Double menorTonelada(){
        Double MayorTon=null;
        if(ListaToneladas.vacia()==false){
            MayorTon=Double.parseDouble(ListaToneladas.obtener(0).toString());
            Double cantidadActual=null;
            for(int i=1;i<ListaToneladas.cantidadElementos();i++){
                cantidadActual=Double.parseDouble(ListaToneladas.obtener(i).toString());
                if (cantidadActual<MayorTon){// verifica de que si la cantidad actual es menor a la toneladaMayor
                    MayorTon=cantidadActual;// se le asigna a la variable para  obtener el menor dato
                }
            }
        }
        return MayorTon;
    }

    /**
     * obtiene el numero total de los elementos que estan en toneladas
     * @return regreso la cantidad de toneladas en el arreglo
     */
    public int ObtenerCantidadDeTonelada(){
        return ListaToneladas.cantidadElementos();
    }


    /**
     * obtiene el anio en espesivico de una cantidad dada
     * @param cantidad
     * @return posicion
     */
    public Integer obterAnio(Double cantidad){
        Integer posicion = (Integer)ListaToneladas.buscar(cantidad);
        if (posicion!=null){
            return posicion;
        }else return null;
    }

    /**
     * obtiene la ultima posicion de el arreglo agregado
     * @return
     */
    public Double ObtenerUltimaPosicionCultivo(){
        return (Double) ListaToneladas.obtener(ListaToneladas.cantidadElementos()-1);
    }
    @Override
    public String toString() {
        return NombreCosecha;
    }
}
