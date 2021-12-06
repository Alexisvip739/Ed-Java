package registros.BaseDeDatosABB;

import entradasalida.SalidaTerminal;
import estructurasnolineales.ABB;
import estructurasnolineales.registros.NodoBusquedaBinaria;
import registros.BaseDeDatosABB.TipoCategorias.*;

import java.io.IOException;

/**
 * clase que nos permite poder agregar las tablas en excel y los datos hacia una tabla
 * espesificando a que tabla seran agregadas
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 *
 *
 */
public class TablasBaseDeDatos extends ABB {

    /**
     * metodo que nos agrega a un nodo una taba de datos con un identificador unico
     * @param clave
     * @param tablaDatos
     */
    public void insertarTablaDeDatos(int clave,BaseDeDatos tablaDatos){
        if (clave>=0){// en caso de que la clave sea mayor o igual a 0 podremos asignarle una clave
            if (buscar(clave)==null){// en caso de que esta clave a un no exista
                if (tablaDatos!=null){// en caso de que la tabla agregada no sea null
                    NodoBusquedaBinaria nodoNuevo= new NodoBusquedaBinaria(clave,tablaDatos);
                    if (nodoNuevo!=null){
                        agregar(nodoNuevo);// podremos agregar los datos
                    }
                }
            }
        }
    }


    /**
     * metodo que nos permite agregar un dato a las casillas y a el codio que esta dentro de los archivos de excel
     * @param claveTabla
     * @param ClaveRegistro
     * @param dato
     * @throws IOException
     */
    public void agregarTipoDeTabla(int claveTabla, int ClaveRegistro,Object dato) throws IOException {
        Object elementoBuscar=buscar(claveTabla);// buscamos la clave de la tabla
        if (elementoBuscar!=null ){// existe
            NodoBusquedaBinaria nodoNuevo= (NodoBusquedaBinaria)elementoBuscar;// casteamos ese dato ya que es un nodo
            BaseDeDatos tablaExistente=(BaseDeDatos)nodoNuevo.getDiriccion();
            if (tablaExistente!=null){
                if (nodoNuevo!=null){
                    switch (claveTabla){// dependiendo de el caso que se de, entonces podremos agregar los datos
                        // el case representa el numero de claves que existen en los archivos, como son 6 archivos excel, hay 6 claves de ellas que indican a cada tabla
                        case 1:// en caso de que se quiera meter datos a la primera tabla
                            if (dato instanceof Tipo__categories_tab) {// verificamos que sea de su tipo

                                tablaExistente.insertar(ClaveRegistro,dato);// agregamos

                            }
                        case 2:// si se quiere agregar datos a la tabla 2
                            if (dato instanceof Tipo_customers){// verificamos que sea de su tipo
                                tablaExistente.insertar(ClaveRegistro,dato);// agregamos los datos
                            }
                        case 3:// en caso de que sea la tabla 3
                            if (dato instanceof Tipo_order_items){// verificamos su tipo
                                tablaExistente.insertar(ClaveRegistro,dato);// agregamos los datos
                            }
                        case 4:// tabla 4
                            if (dato instanceof Tipo_orders){// verificamos
                                tablaExistente.insertar(ClaveRegistro,dato);//agregamos
                            }
                        case 5://tabla 5
                            if (dato instanceof Tipo_product_information){//verificamos
                                tablaExistente.insertar(ClaveRegistro,dato);//agregamos
                            }
                        case 6:// tabla 6
                            if (dato instanceof  Tipo_product_description){//verificamos
                                tablaExistente.insertar(ClaveRegistro,dato); //agregamos
                            }

                    }
                }
            }
        }

    }
}
