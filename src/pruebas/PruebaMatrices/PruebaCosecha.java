package pruebas;

import entradasalida.SalidaTerminal;
import registros.cosecha.Campesino;
import registros.cosecha.Cultivo;
import registros.cosecha.Ejido;

/**
 * Clase donde se manda a llamar todos los datos de el ejido
 * @author  Alexis Ultreras Sotelo
 * @version  1.0
 */
public class PruebaCosecha {
    public static void main(String[] args) {
        Ejido ejido1 = new Ejido(6);
        // creacion de los campesinos con sus nombres
        Campesino campesino1 = new Campesino("Jose");
        Campesino campesino2 = new Campesino("Ramiero");
        Campesino campesino3 = new Campesino("Juan");
        Campesino campesino4 = new Campesino("Pedro");
        Campesino campesino5 = new Campesino("Miguel");
        Cultivo cultivo1 = new Cultivo("Frijol", 3);

        // agregacion de las toneladas  a los cultivos
        cultivo1.AgregarTonelada(1.0);
        cultivo1.AgregarTonelada(20.0);
        cultivo1.AgregarTonelada(4.0);

        Cultivo cultivo2 = new Cultivo("Maiz", 3);
        cultivo2.AgregarTonelada(8.0);
        cultivo2.AgregarTonelada(2.0);
        cultivo2.AgregarTonelada(1.0);

        Cultivo cultivo3 = new Cultivo("Zanahoria", 3);
        cultivo3.AgregarTonelada(7.0);
        cultivo3.AgregarTonelada(9.0);
        cultivo3.AgregarTonelada(1.0);

        Cultivo cultivo5 = new Cultivo("Frijol", 3);
        cultivo3.AgregarTonelada(9.0);
        cultivo3.AgregarTonelada(2.0);
        cultivo3.AgregarTonelada(3.0);

        Cultivo cultivo4 = new Cultivo("Maiz", 3);
        cultivo4.AgregarTonelada(9.0);
        cultivo4.AgregarTonelada(2.0);
        cultivo4.AgregarTonelada(3.0);
        // se agrega a el campesino las toneladas con sos cultivos
        campesino1.AgregarCultivo(cultivo1);
        campesino1.AgregarCultivo(cultivo2);
        campesino1.AgregarCultivo(cultivo5);

        campesino2.AgregarCultivo(cultivo3);
        campesino2.AgregarCultivo(cultivo4);

        campesino3.AgregarCultivo(cultivo1);
        campesino4.AgregarCultivo(cultivo5);
        campesino4.AgregarCultivo(cultivo4);
        campesino5.AgregarCultivo(cultivo1);

        // se le asigna a el ejido de la propiedad todos los campesinos existentes ahi con sus cultivos
        ejido1.AgregarCampesino(campesino1);
        ejido1.AgregarCampesino(campesino2);
        ejido1.AgregarCampesino(campesino3);
        ejido1.AgregarCampesino(campesino4);
        ejido1.AgregarCampesino(campesino5);


        SalidaTerminal.consola("RESULTADDO DE CADA UNA DE LOS PUNTOS A REALIZAR \n");
        //Prueba A)
        //SalidaTerminal.consola("Promedio Actual de El campesino " + cultivo1.obtenerPromedio() + "\n");

        //Prueba B)
        //SalidaTerminal.consola(ejido1.menorToneladaCultivo("Frijol", 3) + "\n");

        //Prueba C)
        //SalidaTerminal.consola(ejido1.campesinoMejorFavoresido(1) + "\n");

        //Prueba D)
        //SalidaTerminal.consola(campesino1.ProductoConMayorToneladas(2)+"\n");

        //Prueba E)
        //SalidaTerminal.consola(cultivo1+": "+campesino1);

        //Prueba F)
        //SalidaTerminal.consola(ejido1.MenosCantidadDeProducto(campesino1+"Jose","Frijol")+"\n");
        // Prueba G)
        //SalidaTerminal.consola("Persona    que menos cosecho este mismo año"+ejido1.menorCosechaDelUltimoAnio()+"\n");

        //Prueba H)
        //campesino1.imprimirToneladasCultivo("Maiz");
        //campesino2.imprimirToneladasCultivo("Maiz");
        //campesino3.imprimirToneladasCultivo("Maiz");
        //campesino4.imprimirToneladasCultivo("Maiz");
        //campesino5.imprimirToneladasCultivo("Maiz");

        // Prueba I)
        //SalidaTerminal.consola(campesino2.productoMasProducidoEnPromedio(50.0)+"\n");

        //Prueba J)
       SalidaTerminal.consola(ejido1.menorProduccion(2,40)+"\n");

    }
}
