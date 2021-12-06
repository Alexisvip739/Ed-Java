package Herramientas;

public class Comparador {

    /**
     * este metodo nos permite a comparar dos objetos dependiendo tambien de su tipo de orden que tengan
     * @param objeto1
     * @param objeto2
     * @param tipoOrden
     * @return diferencia
     * @author Alexis Ultreras Sotelo
     * @version 1.0
     */
    public static int compararObjetos(Object objeto1, Object objeto2){
        if(objeto1 instanceof Number && objeto2 instanceof Number){ //son números
            Double numero1=Double.parseDouble(objeto1.toString());
            //Number numero1N=(Number)objeto1;
            //double numero1=numero1N.doubleValue();
            Double numero2=Double.parseDouble(objeto2.toString());

            if(numero1.doubleValue()>numero2.doubleValue()){ //o1>o2
                return 1;
            }else if(numero1.doubleValue()<numero2.doubleValue()){ //o1<o2
                return -1;
            }else{ //o1=o2
                return 0;
            }
        }else{//es otro tipo de objeto, como cadenas, Personas, Perros, Casas, etc...
            return objeto1.toString().compareToIgnoreCase(objeto2.toString());
        }
    }
}
