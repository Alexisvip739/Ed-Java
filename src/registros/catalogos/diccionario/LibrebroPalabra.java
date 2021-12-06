package registros.catalogos.diccionario;

import Herramientas.TipoOrden;
import Herramientas.Tipo_Palabra;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArreglOrdenado;
import estructuraslineales.ArregloDatos;

/**
 * @author Alexis Ultreras Sotelo
 * @version  1.0
    Clase que implementa la logica de el diccionario
 */
public class LibrebroPalabra {
    private ArreglOrdenado listaPababras;

    public LibrebroPalabra(int Maximo) {
        listaPababras= new ArreglOrdenado(Maximo, TipoOrden.Ordenado);
    }

    /**
     * metodo que agrega un dato a la lista de palabras
     * @param palabra
     * @return return 1
     */
    public int AgregarPalabra(Palabra palabra){
      Integer palabraAgregada=(Integer) listaPababras.buscar(palabra); //busca la palabra
      if (palabraAgregada!=null){ // si se encontro
          listaPababras.agregar(palabra); // agrega la palabra
          ordenaPalabras(); // como es arreglo ordenado no se puede agregar dos datos repetidos  a si que se ordenan aun que esten repetidos
          return palabraAgregada; // regresa la posicion
      }return -1;

    }

    /**
     * metodo privado que ordena los datos de el arreglo
     */
    private void ordenaPalabras(){
        if (listaPababras.cantidadElementos()>1){ // tiene mas de un elemento guardado

            while (true) {
                // contador que incrementara si todos los elementos estan en su lugar correspondiente
                int cont=1;
                for (int posPalabra= 0; posPalabra<listaPababras.cantidadElementos()-1 ; posPalabra++){
                    Palabra palabra1 = (Palabra) listaPababras.obtener(posPalabra);
                    Palabra palabra2 = (Palabra) listaPababras.obtener(posPalabra+1);

                    // obtenemos la menor longitud de caracteres de las 2 productos.
                    int cantCaracteres = (palabra1.getNombrePalabra().length()<palabra2.getNombrePalabra().length())?
                            palabra1.getNombrePalabra().length():palabra2.getNombrePalabra().length();

                    for (int posCaracter=0;posCaracter<cantCaracteres;posCaracter++){
                        // en codigo ASCII
                        // si el caracter del producto1 es mayor al caracter del producto2, cambian de posicion
                        if (palabra1.getNombrePalabra().charAt(posCaracter)>palabra2.getNombrePalabra().charAt(posCaracter)){
                            listaPababras.cambiar(posPalabra+1,palabra1);
                            listaPababras.cambiar(posPalabra,palabra2);
                            break;
                        }// si el caracter del producto1 es menor al caracter del producto2, salimos del ciclo
                        else if (palabra1.getNombrePalabra().charAt(posCaracter)<palabra2.getNombrePalabra().charAt(posCaracter)){
                            cont++;
                            break;
                        }// los caracteres son iguales
                    }
                }
                // todos los elementos estan en su lugar, salimos del ciclo while
                if (cont>=listaPababras.cantidadElementos()) break;
            }
        }
    }

    /**
     * imprime la lista con los datos asignados en el arreglo
     */
    public  void mostrarPalabras(){
        listaPababras.imprimir();

    }

    /**
     *
     * @param elemento
     * @return  descripcion
     */
    public Object DescripcionDePalabra(Object elemento){
        if (elemento!=null){
            for (int i=0;i< listaPababras.cantidadElementos();i++){ // recorremos el arreglo
                Palabra obtenerPalabra=(Palabra) listaPababras.obtener(i); //obtenemos las posiciones en donde estan los datos y los casteamos a palabras
                if (obtenerPalabra.toString().equalsIgnoreCase(elemento.toString())){// si es igual
                    return obtenerPalabra.getDescripcion(); // regresamos el significado de la palabra
                }
            }
        }
        return null;
    }


    /**
     * buscamos una palabra agregada por medio de una letra
     *
     * @param letra
     * @return
     */
    public  Object BuscarConPrimeraLetra(Object letra){
        for (int i=0;i<listaPababras.cantidadElementos();i++){
            Object obtener=(Object) listaPababras.obtener(i); // OBTIENE EL DATO Y LO ASIGNA A UNA VARIABLE DE TIPO OBJECT
            if (letra.toString().charAt(0)==obtener.toString().charAt(0)){
                return listaPababras.obtener(i); // devolvemos los datos que son iguales
            }
        }
        return false;
    }

    /**
     * este metodo nos ayuda a saber las palabras que tiene solo sierto tipo de palabra (verbo...)
     * @param tipo
     */
    public void PalabraEspesificada(Tipo_Palabra tipo){
            for (int i=0;i<listaPababras.cantidadElementos();i++){
                Palabra palabra=(Palabra) listaPababras.obtener(i); // obtenemos la palabra y la casteamos a Palabra
                if (palabra.getTipoPalabra().equals(tipo)){
                    SalidaTerminal.consola(listaPababras.obtener(i)+"\n"); // imprimimos las palabras que tienen ese tipo de palabra
                }
            }
    }

    /**
     * Buscar una palabra con sus datos mediante una frase en espesifico que la identifique
     * @param definicion
     */
    public void BuscarConFrase(String definicion){
        ArregloDatos frases;
        for (int i=0; i<listaPababras.cantidadElementos();i++){
            Palabra palabraActual= (Palabra) listaPababras.obtener(i); // obtenemos la palabra y la casteamos a el tipo Palabra
            frases= new ArregloDatos(palabraActual.getDescripcion().length());
            String frase="";
            for (int j=0;j<palabraActual.getDescripcion().length();j++){ // recorremos la longitud de dicha longitud de la palabra
                if (palabraActual.getDescripcion().charAt(j)!=' '){
                        frase+=palabraActual.getDescripcion().charAt(j);  // sumamons un lugar mas de la descripcion
                }else {
                    frases.agregar(frase);
                    frase="";
                }
            }
            Integer posicionFrase=(Integer)  frases.buscar(definicion);
            if (posicionFrase!=null){
                SalidaTerminal.consola("Palabra: "+palabraActual.getNombrePalabra()+": "+palabraActual.getDescripcion()+"\n");
            }
        }

    }




}
