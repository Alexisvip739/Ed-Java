package Herramientas.matematicas.pearsnon;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ListaEncadenada;
import estructuraslineales.ListaEncadenadaDoble;


import java.io.*;
import java.util.Locale;

/**
 * Clase principal en donde podemos obtener el coeficiente de pearson con la formula orignal
 * @author Alexis Ultreas Sotelo
 * @version 1.0
 */
public class CoeficienteDePearson extends ListaEncadenada {
    protected static ListaEncadenadaDoble Datosx;// datos almacenados en x
    protected static ListaEncadenadaDoble Datosy;// datos almacenados en y
    protected  double mediaX;
    protected  double mediaY;

    protected double r;

    /**
     * contructor que inicializa los datos
     */
    public CoeficienteDePearson() {
        this.Datosx = Datosx;
        this.Datosy = Datosy;
    }


    /**
     * este metodo nos permite poder leer los datos de un txt a una lista ligada
     * estos datos seran los que correspondan a datos x
     * @param archivo
     * @return DatosX
     */
    public static ListaEncadenadaDoble leerX(String archivo){
        FileReader input=null;
        BufferedReader buffer = null;
        try {
            String cadena=null;// obtendra la cadena de los datos
            input = new FileReader(archivo);// leemos el archivo txt
            buffer = new BufferedReader(input);
            Datosx=new ListaEncadenadaDoble();
            buffer.close();// para concluir con los ciclos de la lectura cerramos
            input.close();
            input = new FileReader(archivo);
            buffer = new BufferedReader(input);
            while((cadena = buffer.readLine())!=null) {
                Datosx.agregar(cadena);// agergamos los datos de el archivo txt a una lista ligada
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                input.close();
                buffer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return Datosx;
    }

    /**
     * este metodo nos permite poder leer los datos de un txt a una lista ligada
     * estos datos seran los que correspondan a datos x
     * @param archivo2
     * @return Datosy
     */
    public static ListaEncadenadaDoble leerY(String archivo2){
        FileReader input2=null;
        BufferedReader buffer2 = null;
        try {
            String cadena2=null;
            input2 = new FileReader(archivo2);// abrimos el archivo que tendra los datos para la lista de datos
            buffer2 = new BufferedReader(input2);
            Datosy=new ListaEncadenadaDoble();
            buffer2.close();
            input2.close();
            input2 = new FileReader(archivo2);
            buffer2 = new BufferedReader(input2);
            while((cadena2 = buffer2.readLine())!=null) {
                Datosy.agregar(cadena2);// cada recorrido dentro de la cadena agregara los datos en la lista de datoss
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                input2.close();
                buffer2.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return Datosy;
    }

    /**
     * Calcula el coeficiente de Pearson.
     * @return retorna el resultado del coeficiente.
     */
    public double calcularCoeficienteMuestral(){
        if (Datosx.NumElementos()==Datosy.NumElementos()){// verificamos que la longitud de datos sea igual
            ListaEncadenadaDoble xy = MultiplicarlistaXY(Datosx,Datosy);// multiplicamos las dos listas
            double sigmaXY = SumaAritmetica(xy); //  hacemos la suma aritemica de la lista de los datos xy
            double sigmaX = SumaAritmetica(Datosx);//sumamos los datos aritmeticos de  la lista que tiene los datos en x
            double sigmaY = SumaAritmetica(Datosy);// sumamos los datos en y
            ListaEncadenadaDoble x2 = PotenciaListasEncadenadas(Datosx);// obtenemos el resultado de la potencia con la lista x
            double sigmaX2 = SumaAritmetica(x2);// sumamos los datos
            ListaEncadenadaDoble y2 = PotenciaListasEncadenadas(Datosy);
            double sigmaY2 = SumaAritmetica(y2);
            double dividendo = (Datosx.NumElementos()*sigmaXY) - (sigmaX*sigmaY);
            double divisor = Math.sqrt(((Datosx.NumElementos()*sigmaX2)-(Math.pow(sigmaX,2))) ) * Math.sqrt(((Datosx.NumElementos()*sigmaY2)-(Math.pow(sigmaY,2)))) ;// resolvemos las ultimas sentencias de la formula de pearson
            return dividendo/divisor;
        }
        return 0.0;
    }



    /**
     * Obtiene la covarianza.
     * @param mediaX dato numerico media aritmetica.
     * @param mediaY dato numerico media aritmetica.
     * @return retorna el resultado de la operacion covarianza.
     */
    private double covarianza(double mediaX, double mediaY){
        double sumatoria = 0.0; // suma de varios sumados.

        Datosx.Iteradorinicio();
        Datosy.Iteradorinicio();

        while (Datosx.hayMas() && Datosy.hayMas()){
            // se obtiene cada dato de las 2 listas.
            Double datoX = Double.parseDouble(Datosx.obtenerSigiuenteFrente().toString());
            Double datoY = Double.parseDouble(Datosy.obtenerSigiuenteFrente().toString());
            // se aplica la formula de covarianza y el resultado se suma a la variable de sumados.
            sumatoria+= (datoX-mediaX)*(datoY-mediaY);
        }
        return sumatoria;
    }

    /**
     * metodo que nos ayuda a calcular el coefiente de pearson poblacional
     * @return r
     */
    public double CalcularCoeficientePearson(){
        if(Datosx.NumElementos()==Datosy.NumElementos()) {// verificamos la longitud de los datos para que se puda ejecutar los datos
            mediaX = mediaAritmetica(Datosx);// sacamos la media de los datos en x
            mediaY = mediaAritmetica(Datosy);// sacamos la media de los datos en y
            ListaEncadenadaDoble SumaX1 = RestaTotal(Datosx, mediaX);// restamos cada posicion de los datos en x con la media de x que se dio
            ListaEncadenadaDoble Sumay2 = RestaTotal(Datosy, mediaY);// restamos cada posicoin de los datos en y con la media que se dio en y
            ListaEncadenadaDoble xy = MultiplicarlistaXY(SumaX1, Sumay2);// los dartos de los resultados los multiplicamos
            SumaX1 = PotenciaListasEncadenadas(SumaX1);// la suma de x1 le sacamos su potencia
            Sumay2 = PotenciaListasEncadenadas(Sumay2);// la suma de y2 le sacamos su potencia
            double MediaX = mediaAritmetica(SumaX1);// volvemos a sacar la media de la suma
            double MediaY = mediaAritmetica(Sumay2);// volvemos a sacar la media de la suma pero ahora con los datos en y
            double sumaMediaXY = mediaAritmetica(xy);// sacamos la media aritmetica de los datos finales de xy
            double den = Math.sqrt(MediaX * MediaY);// sacamos su raiz cuadrada de la formula

            return r = (sumaMediaXY / den);// regresamos el resultado
        }
        return 0.0;
    }
    /**
     * esto nos ayuda a saber que valor de coeficiente nos da r al momento de su resultado, dependiendo de los casos que se den
     */
    public void  valorCoeficiente(){
        if (r == 0) {
            SalidaTerminal.consola("Ninguna relacion" + "\n");
        }
        else if (r == 1) {
            SalidaTerminal.consola("Correlacion positiva Perfecta" + "\n");
        }
        else if (r < 1 && 0 < r) {
            SalidaTerminal.consola("Correlacion positiva " + "\n");
        }
        else if (r == -1) {
            SalidaTerminal.consola("Correlacion Negativa Perfecta" + "\n");
        }
        else{
            SalidaTerminal.consola("Correlacion Negativa " + "\n");
        }
    }


    /**
     * Calcula el coeficiente de Pearson poblacional.
     * @return retorna el resultado del coeficiente.
     */
    public double calcularCoeficientePoblacional(){
        // calculamos el coeficiente de Pearson paso por paso.
        // se obtiene la media aritmetica de las 2 listas.
        double mediaX = mediaAritmetica(Datosx);
        double mediaY = mediaAritmetica(Datosy);

        double dividendo = covarianza(mediaX,mediaY);
        // se obtiene la desviacion estandar de las 2 listas.
        double deX = desviacionEstandar(Datosx,mediaX);
        double deY = desviacionEstandar(Datosy,mediaY);

        double divisor = deX*deY;

        double resultado = dividendo/divisor;
        // resultado de la operacion.
        if (resultado==0) SalidaTerminal.consola( "Asociacion nula\t" );
        else if (resultado>=0.1 && resultado<0.3) SalidaTerminal.consola( "Asociacion pequeña\t" );
        else if (resultado>=0.3 && resultado<0.5) SalidaTerminal.consola( "Asociacion media\t" );
        else if (resultado>=0.5 && resultado<0.7) SalidaTerminal.consola( "Asociacion moderada\t" );
        else if (resultado>=0.7 && resultado<0.9) SalidaTerminal.consola( "Asociacion alta\t" );
        else if (resultado>=0.9) SalidaTerminal.consola( "Asociacion muy alta\t" );

        return resultado;
    }
    /**
     * metodo que nos permite poder obtener la media aritmetica de los datos de una lista
     * @param lista
     * @return regresamos el resultado de suma/lista
     */
    private double mediaAritmetica(ListaEncadenadaDoble lista) {
        Double suma= 0.0;
        for (int i=0;i<lista.NumElementos();i++){// recorremos la lista de los numeros
            suma+=Double.parseDouble(lista.obtener(i).toString());// obtenemos posicion por posicion los datos y los vamos sumando entre ellos
        }
        return  suma/lista.NumElementos();// dividimos entre el numero de datos que son la sumatoria de los datos dentro
    }

    /**
     * metodo que nos ayuda solamente a obtener la sumatoria de una lista de datos
     * @param lista
     * @return suna
     */
    private double SumaAritmetica(ListaEncadenadaDoble lista) {
        Double suma= 0.0;
        for (int i=0;i<lista.NumElementos();i++){// recorremos el numero de datos que se encuentran en la lista actual
            suma+=Double.parseDouble(lista.obtener(i).toString());// obtenemos dato por dato cada una de las posiciones de la lista y las sumamos enter ellas
        }
        return  suma;
    }


    /**
     * metodo que nos permite poder ibtener la potencia dentro de una lista ligada en 2
     * @param lista
     * @return listaEncadenada
     */
    private ListaEncadenadaDoble PotenciaListasEncadenadas(ListaEncadenadaDoble lista){
        ListaEncadenadaDoble listaEstadistica= new ListaEncadenadaDoble();// esta lista obtendra los datos nuevos de la lista actual pero ya con la potencia
        for (int i=0;i< lista.NumElementos();i++){// recorremos la lista actual de los datos
            listaEstadistica.agregar(Math.pow(Double.parseDouble(lista.obtener(i).toString()),2));// obtenemos cada una de las posiciones de los datos ya elevandolos al cuadrado y metiendolos a la lista nueva
        }
        return listaEstadistica;
    }

    /**
     * multiplica las dos listas con valores
     * @param listaX
     * @param listaY
     * @return XY
     */
    private  ListaEncadenadaDoble MultiplicarlistaXY(ListaEncadenadaDoble listaX,ListaEncadenadaDoble listaY){
        ListaEncadenadaDoble ResultadoListaXY=new ListaEncadenadaDoble();// creamos una lista nueva que obtendra el resultado
        for (int i=0;i<listaX.NumElementos() && i< listaY.NumElementos();i++){// recorremos las dos listas a la misma vez
            ResultadoListaXY.agregar(Double.parseDouble(listaX.obtener(i).toString())*Double.parseDouble(listaY.obtener(i).toString())); // multiplicamos los datos obteniendo su posicion y los agregamos a la nueva lista
        }
        return ResultadoListaXY;

    }

    /**
     * este metodo nos permite poder restar los datos de una lista con un valor en espesfico o estatico
     * @param lista
     * @param valor
     * @return nuevo valor
     */
    private  ListaEncadenadaDoble RestaTotal(ListaEncadenadaDoble lista, double valor){
        ListaEncadenadaDoble nuevoValor= new ListaEncadenadaDoble();
        for (int i=0;i<lista.NumElementos();i++){// recorremos la lista de datos
            nuevoValor.agregar(Double.parseDouble(lista.obtener(i).toString())-valor);// restamos cada una de las posiciones de los datos  con el valor, y ese resultado los agregamos dentro de una lista nueva
        }
        return nuevoValor;
    }

    /**
     * Obtiene la desviacion estandar de la lista de datos.
     * @param lista TDA ListaEncadenadaDoble con datos.
     * @param media dato numerico media aritmetica de la lista.
     * @return retorna el resultado de la operacion desviacion estandar.
     */
    private double desviacionEstandar(ListaEncadenadaDoble lista, double media){
        double sumatoria = 0.0; // suma de varios sumados.

        lista.Iteradorinicio();
        while (lista.hayMas()){
            // se obtiene cada dato de la lista
            Double dato = Double.parseDouble(lista.obtenerSigiuenteFrente().toString());
            // se aplica la formula de la desviacion estandar, el resultado sera sumado a la variable de sumados.
            sumatoria+= Math.pow( (dato-media), 2 );
        }

        return Math.sqrt(sumatoria);

    }



















}
