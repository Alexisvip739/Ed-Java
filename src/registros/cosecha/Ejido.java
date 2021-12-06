package registros.cosecha;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;

/**
 * Clase Donde se estructura toda la informacion sobre el Campesino y sus cultivos
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Ejido {

    private ArregloDatos listaCampesino;

    public Ejido(int capacidad) {
        listaCampesino= new ArregloDatos(capacidad);
    }


    /**
     * Agrega a la lista de campesinos los nuevos campesinos
     * @param campesino
     * @return true o false
     */
    public Object AgregarCampesino(Campesino campesino){
        Integer agregarCampesino=(Integer) listaCampesino.agregar(campesino);
        if(agregarCampesino==1){
            return true;// si agrego el cultivo
        }else {
            return false; // no agrego el cultivo
        }
    }



    /**
     * verifica que Campesinos tuvieron menor cultivo por toneladas en el anio
     * @param cultivo
     * @param anio
     * @return campesinoMenor_Tonelada
     */
    public  Campesino menorToneladaCultivo(String cultivo, int anio){
        Double menorTonelada= null;
        Campesino campesinoMenor_Tonelada=null;
        Double toneladaActual= null;
        if (!listaCampesino.vacia()){// verifica que la lista este vacia
            for (int i=0; i<listaCampesino.cantidadElementos();i++){ // recorre lo que es los elementos de la lista de campesinos
                Campesino campesinoActual= (Campesino) listaCampesino.obtener(i);
                toneladaActual=campesinoActual.obtenerToneladaPorAnio(cultivo,anio);
                if (menorTonelada==null){
                    menorTonelada=toneladaActual; // asigna el primer dato agregado
                    campesinoMenor_Tonelada=campesinoActual; // despues de el siguiente dato se asigna
                }else {
                    if (toneladaActual<menorTonelada){
                        menorTonelada=toneladaActual;
                        campesinoMenor_Tonelada=campesinoActual;
                    }
                }
            }
        }
        return campesinoMenor_Tonelada;

    }

    /**
     * Este Indique cuál campesino fue el más favorecido en sus siembras en un año en
     * particular
     * @param anio
     * @return campesinoFavoresido
     */
    public  Campesino campesinoMejorFavoresido(int anio){
        Campesino campesinoFavoresido= null;
        Double cantidadTotalConCosecha= 0.0;
        if (!listaCampesino.vacia()){ // verifica que la lista de los campesinos es vacia
            for (int i=0;i<listaCampesino.cantidadElementos();i++){ // recorre los elementos de la lista

                Campesino campesinoActual=(Campesino) listaCampesino.obtener(i); // obtiene las posiciones de el Campesino (los anios)
                Double cantidadActual=cantidadToneladaPorCampesino(campesinoActual,anio);// esos anios los manda con su campesino actual  a un metodo en especial)

                if (cantidadActual==0.0){
                    if (cantidadActual>cantidadTotalConCosecha) {
                        cantidadTotalConCosecha = cantidadActual; // si es el primer dato se asigan a cantidad Actual
                        campesinoFavoresido=campesinoActual;// despues de que se ingresen datos nuevos y no solo sea uno ya se agrega a la variable campesinoActual
                    }
                }else if(cantidadActual !=null){ // en caso de que el dato sea diferente de null ( osea haya mas datos agregados=
                    // se hace la misma espesificacion que cuando es 0.0
                    cantidadTotalConCosecha=cantidadActual;
                    campesinoFavoresido=campesinoActual;
                }
            }
        }
        return campesinoFavoresido;
    }


    public Campesino menorCosechaDelUltimoAnio(){
        Double menorTonelada=null;
        Campesino campesino = null;
        if (!listaCampesino.vacia()){
            for (int posicionCampesino = 0 ; posicionCampesino< listaCampesino.cantidadElementos() ; posicionCampesino++) {
                Campesino campesinoActual = (Campesino) listaCampesino.obtener(posicionCampesino);// obtiene las posiciones de la lista de los campesinos y la asigna en una Variable
                Double toneladaTemp=0.0;
                for (int i=0;i<campesinoActual.cantidadDeElementosCampesino();i++){
                    //obtiene los cultivos  de dicho campesino y lo almacena en una variable de tipo campesino
                    Cultivo cultivoDeCampesino = campesinoActual.obtenerCultivo(i);
                    // Obtenemos la tonelada del ultimo anio de la lista
                    Double toneladaActual = cultivoDeCampesino.ObtenerUltimaPosicionCultivo();
                    // Si es diferente a null, podemos sumarla a tonelada temporal
                    if (toneladaActual!=null) toneladaTemp+= toneladaActual;
                }
                if (toneladaTemp!=null){
                    if (menorTonelada==null){ // primera vez que asigna un elemento se asinga a toneladaTemp= posteriormente de que sean mas datos
                        // se asigna a campesino actual y asi sigue iterando
                        menorTonelada=toneladaTemp;
                        campesino = campesinoActual;
                    }else if (toneladaTemp<menorTonelada){
                        // asignamos la tonelaad temporal a el  nueva menor tonelada
                        menorTonelada=toneladaTemp;
                        campesino = campesinoActual;
                    }
                }
            }
        }return campesino;
    }

    /**
     * metodo que nos indica la cantidad de toneladas por un campesino en espesifico mediante el anio y su campesino
     * @param campesino
     * @param anio
     * @return cantidadActual
     */
    private  Double cantidadToneladaPorCampesino(Campesino campesino, int anio){
        Double cantidadActual= 0.0;
        for (int i=0;i<campesino.cantidadDeElementosCampesino();i++){ // recorre lo que es la cantidadElementos de el campesino agregado
            Cultivo cultivoActual= campesino.obtenerCultivo(i); // obtiene el anio de el cultico actual
            if (anio<cultivoActual.ObtenerCantidadDeTonelada()){
                if (cantidadActual==0.0){
                    cantidadActual=cultivoActual.obtenerTonelada(anio); // obtiene la tonelada de el anio
                }else{
                    cantidadActual=cantidadActual+ cultivoActual.obtenerTonelada(anio); // en caso de que sean mas datos entonces se suman las toneladas de ese anio
                }
            }
        }return cantidadActual;


    }

    /**
     * Este metodo nos ayuda a Buscar un campesino en espesifioco con sus datos
     * @param campesino
     * @return Campesino en su posicion
     */
    public Campesino BuscarCampesino(String campesino){

            if (!listaCampesino.vacia()){ // verifica de que la lista no este vacia
                Integer posicion=(Integer) listaCampesino.buscar(campesino);
                if (posicion!=null){
                    return  (Campesino) listaCampesino.obtener(posicion); // regresa el campesino en la posicion que se encuentre en la lista
                }else{
                    return null;
                }
            }else{
                return  null;
            }


    }

    // falta J


    /**
     * este metodo nos ayuda a verificar y saber cual fue el anio con mejor productividad para el campesino
     * @param campesino
     * @param cultivo
     * @return cultivoCampesinoBuscado
     */
    public Integer anioConMejorCantidad(String campesino, String cultivo){
        // busca el campesino y lo agrega a una nueva variabnle de tipo campesino
        Campesino campesinoBuscado = BuscarCampesino(campesino);
        if (campesinoBuscado!=null){ // el dato si esta almacenado
            Cultivo cultivoCampesinoBuscado= campesinoBuscado.buscarCultivo(cultivo);
            if (cultivoCampesinoBuscado!=null){
                return cultivoCampesinoBuscado.obterAnio(cultivoCampesinoBuscado.mayorTonelada())+1;// obtiene la mejor cantidad de el campesino
            }
        }return null;
    }


    /**
     * Indica el campesino que tenga menos toneladas del producto por cierto campesino en espesifico por el usuario
     * @param campesino
     * @param cultivo
     * @return campesinoNumero2
     */
    public Campesino MenosCantidadDeProducto(String campesino, String cultivo){
        // Busca si el campesino se encuentra en la lista.
        Campesino campesinoNumero1 = BuscarCampesino(campesino);
        Campesino campesinoNumero2 = null;
        Cultivo cultivoTemp = null;
        if (campesinoNumero1!=null){// si esta en la lista
           // busca el cultivo y lo asigna en una variable de tipo Cultivo
            Cultivo cultivo1 = campesinoNumero1.buscarCultivo(cultivo);
            if (cultivo1!=null){
                // Sacamos el promedio del cultivo enviado por campesino enviado
                Double toneladaporCultivoCampesino = cultivo1.obtenerPromedio();

                for (int i = 0 ; i<listaCampesino.cantidadElementos();i++){

                    Campesino campesinoTemp = (Campesino)listaCampesino.obtener(i);

                    cultivoTemp = campesinoTemp.buscarCultivo(cultivo);
                    if (cultivoTemp != null) { // si esta en la lista
                        // Sacamos el promedio y guardamos en una variable temporal
                        Double promedioTemp = cultivoTemp.obtenerPromedio();

                        if (!campesinoTemp.toString().equalsIgnoreCase(campesinoNumero1.toString())){ // si el campesino enviado es diferente al campesino temporal, entra
                            // si el promedio temporal es menor al promerio actual,
                            // asignamos el promedio temporal como el nuevo promedio actual, y asi con los datos que vayan entrando
                            if (promedioTemp<toneladaporCultivoCampesino ){
                                toneladaporCultivoCampesino=promedioTemp;
                                campesinoNumero2 = campesinoTemp;
                            }
                        }
                    }
                }
            }// no se encontro el cultivo

        }
        SalidaTerminal.consola("Ninguno \n");
        return campesinoNumero2;
    }

    /**
     * indica la produccion en toneladas mas menor de el campesino
     * @param anio
     * @param toneladas
     * @return cultivoTemp
     */
    public  Campesino menorProduccion(int anio,double toneladas){
        ArregloDatos CampesinoPorAnio = new ArregloDatos(listaCampesino.cantidadElementos()-1); // obtiene la cantidad de elementos de el anio
        for (int i=0;i<listaCampesino.cantidadElementos();i++){
            Campesino CampesinoTemp = (Campesino) listaCampesino.obtener(i); // obtiene el dato en dicha posicion de el campesino
            Cultivo cantidadCampesino=CampesinoTemp.ProductoConMayorToneladas(anio);
            if (cantidadCampesino.mayorTonelada()<toneladas){// si la cantidad de el campesino es menor a las toneladas indicadas
                return CampesinoTemp; // regresa al campesino
            }

        }
       return null;

    }


}
