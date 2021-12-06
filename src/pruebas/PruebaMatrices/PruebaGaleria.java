package pruebas;


import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import registros.Galerias.Actividades.Autografos;
import registros.Galerias.Actividades.Exponer;
import registros.Galerias.Actividades.Pintar;
import registros.Galerias.Actividades.Viajar;
import registros.Galerias.GaleriaInfo;
import registros.Galerias.Pintor;

/**
 * CLASE DONDE SE HACE EL LLAMADO PARA OBTENER TODOS LOS DATOS DE LA CLASE DONDE SE TIENE TODA LA LOGICA DE ELLA
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 *
 */

public class PruebaGaleria {
    public static void main(String[] args) {
        /**
         * Se crean las respetctivas tareas y el pintor
         */
        ArregloDatos arregloPintor= new ArregloDatos(4);
        ArregloDatos arregloTareas= new ArregloDatos(4);
        ArregloDatos arregloTareas2= new ArregloDatos(4);
        ArregloDatos arregloTareas3= new ArregloDatos(4);


        /**
         * Se asignan los datos de cada una de las clases de Actividades
         */

        Pintar t1= new Pintar("Mona Lisa");
        Autografos  a1= new Autografos ("Zacatecas","20/12/2012");
        Exponer e1= new Exponer ("Jerez","Artistas Nuevos","10/12/2021",20);
        Exponer e2= new Exponer ("Jerez","Artistas Nuevos2","10/12/2021",90);

        Viajar v1= new Viajar("Mexico");


        /**
         * Se asignan los datos para almacenarlo en el arreglo
         */
        arregloTareas3.agregar(a1);//1
        arregloTareas3.agregar(a1);//1
        arregloTareas3.agregar(e2);//3
        arregloTareas3.agregar(a1);//4

        arregloTareas2.agregar(e1);//1
        arregloTareas2.agregar(v1);//1
        arregloTareas2.agregar(v1);//3
        arregloTareas2.agregar(v1);//4

        arregloTareas.agregar(v1);//1
        arregloTareas.agregar(v1);//1
        arregloTareas.agregar(v1);//3
        arregloTareas.agregar(v1);//4
        arregloTareas.agregar(v1);//5
        arregloTareas.agregar(v1);//6
        arregloTareas.agregar(v1);//7

        /**
         * Se crean los pintores
         */
        Pintor pintor1= new Pintor("Jose","10/12/1220","23ASDS12","CALLE MINA #3","Ingeniero ",30);
        Pintor pintor2= new Pintor("Brayan","10/22/1220","21ASDS12","CALLE MINA #13","Lisenciado",30);
        Pintor pintor3= new Pintor("Lalo","10/15/1220","23FQDS12","CALLE MINA #1","Ingeniero ",40);
        Pintor pintor4= new Pintor("Pepe","10/14/1220","23FFDS12","CALLE MINA #5","Ingeniero ",32);

        /**
         * se asignan los pintores
         */
        arregloPintor.agregar(pintor1);
        arregloPintor.agregar(pintor2);
        arregloPintor.agregar(pintor3);
        arregloPintor.agregar(pintor4);

        GaleriaInfo galeria= new GaleriaInfo(arregloPintor,4);
        /**
         * Se asignan los datos conrrespondientes a la tabla 3d que esta en la galerias info
         */
        galeria.AgregarActividadPintor(arregloTareas2,"Jose",1);
        galeria.AgregarActividadPintor(arregloTareas,"Jose",2);
        galeria.AgregarActividadPintor(arregloTareas,"Jose",3);
        galeria.AgregarActividadPintor(arregloTareas2,"Jose",4);

        galeria.AgregarActividadPintor(arregloTareas,"Brayan",1);
        galeria.AgregarActividadPintor(arregloTareas2,"Brayan",2);
        galeria.AgregarActividadPintor(arregloTareas,"Brayan",3);
        galeria.AgregarActividadPintor(arregloTareas3,"Brayan",4);

        galeria.AgregarActividadPintor(arregloTareas,"Lalo",1);
        galeria.AgregarActividadPintor(arregloTareas2,"Lalo",2);
        galeria.AgregarActividadPintor(arregloTareas,"Lalo",3);
        galeria.AgregarActividadPintor(arregloTareas3,"Lalo",4);

        galeria.AgregarActividadPintor(arregloTareas,"Pepe",1);
        galeria.AgregarActividadPintor(arregloTareas2,"Pepe",2);
        galeria.AgregarActividadPintor(arregloTareas,"Pepe",3);
        galeria.AgregarActividadPintor(arregloTareas2,"Lalo",4);



        //SalidaTerminal.consola(galeria.actividadMasPopular(2)+"\n");

        //SalidaTerminal.consola(galeria.pintorConMasFirmas()+"\n");

        //galeria.anioYEvento("Lalo");

        //galeria.PintoresConPresentaciones();

        //SalidaTerminal.consola(galeria.dedicoMasTiempoAviajar("Pepe")+"\n");

        //galeria.eventoMayorAforo();

        //SalidaTerminal.consola(galeria.AnioConMenosPinturas()+"\n");

    }
}
