package estructurasnolineales;

import Herramientas.TipoOrden;
import Herramientas.general.EtiquetaGrafoPonderado;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloCola;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ArregloPila;
import estructuraslineales.ListaEncadenada;
import estructurasnolineales.registros.Vertice;

/**
 * clase que implementa la funcionalidad de el grafo de matriz
 * @author Alexis Ultreras Sotelo
 * @version 3.0
 */

public class GrafoMatriz {
    //Dónde guardar los vértices
    protected ArregloDatos vertices;
    //Dónde guardar las aristas
    protected Tabla2D aristas;
    //Define el orden de importancia de los pesos
    protected TipoOrden orden;

    public GrafoMatriz(int cantidadVertices) {
        vertices = new ArregloDatos(cantidadVertices);
        aristas = new Tabla2D(cantidadVertices, cantidadVertices, 0.0);
    }

    public GrafoMatriz(int cantVertices, TipoOrden orden) {
        vertices = new ArregloDatos(cantVertices);
        this.orden = orden;
        if (this.orden == TipoOrden.Desordenado) { //menor es mejor
            aristas = new Tabla2D(cantVertices, cantVertices, Double.MAX_VALUE); //+ infinito
        } else { //mayor es mejor, ASC
            aristas = new Tabla2D(cantVertices, cantVertices, Double.MIN_VALUE); //- infinito
        }
        aristas.diagonal(0.0); //ponemos la diagonal en ceros
    }

    public boolean agregarVertice(Object datoVertice) {
        //Cuando se desea agregar un nuevo vértice, se debe verificar que ese dato no exista ya.
        //Para eso nos basaremos en el toString de datoVertice comparado contra lo que está guardado en
        // el arreglo de vértices.
        Integer posicionVertice = (Integer) vertices.buscar(datoVertice);

        if (posicionVertice == null) { //no existe
            //crear el vértice y agregarlo
            Vertice verticeNuevo = new Vertice();

            verticeNuevo.setDescripcion(datoVertice);
            verticeNuevo.setIndice(vertices.cantidadElementos()); //posicion de cada vertice en el arreglo
            //y por lo tanto en la matriz
            int retornoInsercion = vertices.agregar(verticeNuevo);
            if (retornoInsercion >= 0) { //si se pudo agregar, porque me regresó la psoción de agregado
                return true;
            } else { //no se pudo agregar, fue un -1
                return false;
            }
        } else { //si existe
            return false; // ya existe un vértice con esa descripción
        }
    }

    public boolean agregarArista(Object origen, Object destino) {
        return agregarArista(origen, destino, 1.0);
    }

    public boolean agregarArista(Object origen, Object destino, double peso) {
        //verificar que existan esos dos vértices
        Integer posicionOrigen = (Integer) vertices.buscar(origen);
        Integer posicionDestino = (Integer) vertices.buscar(destino);

        if (posicionOrigen != null && posicionDestino != null) { //si existen los dos
            //si ya existen, podemos agregar la arista
            return aristas.asignarCelda(posicionOrigen, posicionDestino, peso);
        } else { //por lo menos uno no existe
            return false;
        }
    }

    public void imprimir() {
        SalidaTerminal.consola("Listado de vértices: \n");
        vertices.imprimir();

        SalidaTerminal.consola("La tabla de aristas: \n");
        aristas.imprimir();
    }


    //----------Métodos de la ordenación topológica

    //Método auxiliar para el paso 1
    private int dependenciasXVertice(int verticeDestino) {
        int dependencias = 0;
        //Recorrer todos los renglones (orígenes) que puede ser que lleguen al destino
        for (int cadaOrigen = 0; cadaOrigen < aristas.getFilas(); cadaOrigen++) {
            //con base en la matriz (aristas) sabremos si hay o no flecha  que llegue al destino
            Double flecha = (Double) aristas.obtenerInfo(cadaOrigen, verticeDestino);
            if (flecha != null && flecha.doubleValue() > 0) { //hay flecha válida del origen a este destino
                dependencias++;
            }
        }
        return dependencias;
    }

    //Método del paso 1
    private void inicializarDependencias(ArregloDatos dependencias) {
        //Recorrer todos los posibles vértices (destinos en la matriz), para calcular en cada uno de ellos
        //el número de dependencias que tiene (flechas que le llegan)
        for (int cadaDestino = 0; cadaDestino < aristas.getColumnas(); cadaDestino++) {
            //para cada uno de estos destinos, calculamos las incidencias, recorrer todos los renglones (origenes)
            //que mandan flechas a este vértice destino.
            int dependenciasXDestino = dependenciasXVertice(cadaDestino);
            dependencias.agregar(dependenciasXDestino); //se agregan al arreglo, que estaba vacío
            //se guardan en la misma posición que el vértice definido por cadaDestino
        }
    }

    //paso 2, 5
    private void marcarYEncolarVerticesDepdencia0(ArregloDatos dependencias, ArregloDatos marcados, ArregloCola colaProcesamiento) {
        for (int cadaVertice = 0; cadaVertice < dependencias.cantidadElementos(); cadaVertice++) {
            //marcar y encolar solo los vértices con "dependencias en 0 y que no estén marcados"
            if ((int) dependencias.obtener(cadaVertice) == 0 && (boolean) marcados.obtener(cadaVertice) == false) {
                marcados.cambiar(cadaVertice, true); //marcamos ese vértice con dependencia en 0
                colaProcesamiento.poner(cadaVertice); //se mete en la cola
            }
        }
    }

    //paso 4
    private void recalcularDependenciasVertice(ArregloDatos dependencias, ArregloDatos marcados, int indiceVerticeActualmenteProcesado) {
        for (int cadaDestino = 0; cadaDestino < aristas.getColumnas(); cadaDestino++) {
            //recorremos todos los destinos posibles que son susceptibles que tener una flecha a partir de el origen
            //del vértice actualmente procesado

            //obtener la celda de la tabla2d que me diga si hay flecha o no (solo vértices no marcados).
            Double flecha = (Double) aristas.obtenerInfo(indiceVerticeActualmenteProcesado, cadaDestino);
            if (flecha != null && flecha.doubleValue() > 0 && (boolean) marcados.obtener(cadaDestino) == false) {
                //en caso que si haya flecha a ese vértice destino
                //actualizamos sus depedencias, reduciendo su valor en 1
                int dependenciaVerticeDestino = (int) dependencias.obtener(cadaDestino); //obtenemos su valor de dependencia
                dependencias.cambiar(cadaDestino, dependenciaVerticeDestino - 1); //lo reducimos en 1
            }
        }
    }

    //Métdo principal
    public ListaEncadenada ordenacionTopologica() {
        //Recordar que para que una ordenación topológica funcione, se requiere que
        //el grafo en cuestión no tenga ciclos.
        //Lo recomendado es que antes de invocar a este método, se debe validar con algún otro
        //método, la no existencia de ciclos.
        ListaEncadenada ordenacionTopologica = new ListaEncadenada();
        ArregloCola colaProcesamiento = new ArregloCola(vertices.cantidadElementos());
        ArregloDatos dependencias = new ArregloDatos(vertices.cantidadElementos());
        ArregloDatos marcados = new ArregloDatos(vertices.cantidadElementos());

        //Pasos:

        //0.- Checar no existencia de ciclos.

        //1.- Inicializar dependencias (incidencias).
        inicializarDependencias(dependencias);
        //2.- Los procesos con dependencias en 0 (no marcados)* se
        //colocan en una cola de procesamiento y se marcan como ya analizados.

        //Inicializar el arreglo de marcados con valores en FALSE
        marcados.rellenar(false, vertices.cantidadElementos());
        //Invocar al método que realice la función de marcar y encolar vértices con depedencia en 0
        marcarYEncolarVerticesDepdencia0(dependencias, marcados, colaProcesamiento);

        while (colaProcesamiento.vacio() == false) {
            //3.- Sacar un proceso de la cola y ejecutarlo
            //        (mientras haya datos en la cola).
            int indiceVerticeActualmenteProcesado = (int) colaProcesamiento.quitar();
            Vertice verticeActualmenteProcesado = (Vertice) vertices.obtener(indiceVerticeActualmenteProcesado);
            ordenacionTopologica.agregar(verticeActualmenteProcesado.getDescripcion()); //ejecutarlo significa agregarlo a nuestra ordenación topológica (lista)

            //4.- Recalcular dependencia dado el paso 3.
            recalcularDependenciasVertice(dependencias, marcados, indiceVerticeActualmenteProcesado);

            //5.- Los procesos con dependencias en 0 (no marcados) se colocan en la cola y se
            // marcan como analizados.
            marcarYEncolarVerticesDepdencia0(dependencias, marcados, colaProcesamiento);
        }
        return ordenacionTopologica;
    }
    //----------

    //---------- Recorrido en profundidad

    //paso 3 de recorrido en profundidad
    private void marcarYEnpilarVerticesAdyacentes(ArregloDatos marcados, ArregloPila pila, int indiceVerticeActualmenteProceado) {
        for (int cadaDestino = 0; cadaDestino < aristas.getColumnas(); cadaDestino++) {
            //checamos si hay flecha de adyacencia y además no está marcado ese vecino
            Double flecha = (Double) aristas.obtenerInfo(indiceVerticeActualmenteProceado, cadaDestino);
            if (flecha != null && flecha.doubleValue() > 0 && ((boolean) marcados.obtener(cadaDestino)) == false) {
                marcados.cambiar(cadaDestino, true); //lo marcamos
                pila.poner(cadaDestino); //lo enpilamos
            }
        }
    }

    public ListaEncadenada recorridoProfundidad(Object origen) {
        ListaEncadenada recorridoProfundidad = new ListaEncadenada();
        ArregloPila pila = new ArregloPila(vertices.cantidadElementos());
        ArregloDatos marcados = new ArregloDatos(vertices.cantidadElementos());

        //Pasos:
        //0.- Verificar que el origen si exista
        Integer indiceVerticeOrigen = (Integer) vertices.buscar(origen);

        if (indiceVerticeOrigen == null) { //el origen no existem no se puede hacer un reocrrido
            return null;
        } else {
            //1.- Partiremos de un vértice origen. Este vértice se marca y se
            //mete en una pila.
            //Crear un lote de marcados con todo en falso
            marcados.rellenar(false, vertices.cantidadElementos());
            //Mqrcar este vértice
            marcados.cambiar(indiceVerticeOrigen, true);
            //Metemos en la pila este vértice
            pila.poner(indiceVerticeOrigen);

            while (pila.vacio() == false) {
                //2.- Mientras existan vértices en la pila, se van a extraer de uno por uno y se procesarán (imprimirlos).
                int indiceVerticeActualmenteProceado = (int) pila.quitar(); //sacar de la pila el que sigue
                Vertice verticeActualmenteProcesado = (Vertice) vertices.obtener(indiceVerticeActualmenteProceado); //obtengo el vértice
                recorridoProfundidad.agregar(verticeActualmenteProcesado.getDescripcion()); //obtengo su descripción

                //3.- Los vértices adyacentes (no marcados) al nodo que actualmente se procesa (del paso 2)
                // se marcan y se meten en la pila.
                marcarYEnpilarVerticesAdyacentes(marcados, pila, indiceVerticeActualmenteProceado);
            }
            return recorridoProfundidad;
        }
    }
    //----------

    //----------Código de Primero la ruta más corta: Dijkstra
    public Double metricaOptima(Object origen, Object destino) {
        Integer indiceDestino = (Integer) vertices.buscar(destino);
        ArregloDatos etiquetasOptimas = (ArregloDatos) etiquetasOptimas(origen);
        if (indiceDestino == null || etiquetasOptimas == null) { //no existe el destino o el origen
            return null; //no se puede calcular
        } else { //sacamos la métrica óptima hacia ese destino
            EtiquetaGrafoPonderado etiquetaDestino = (EtiquetaGrafoPonderado) etiquetasOptimas.obtener(indiceDestino);
            return etiquetaDestino.getMetricaAcumulada();
        }
    }

    public ListaEncadenada rutaOptima(Object origen, Object destino) {
        ListaEncadenada ruta = new ListaEncadenada();

        Integer indiceDestino = (Integer) vertices.buscar(destino);
        ArregloDatos etiquetasOptimas = (ArregloDatos) etiquetasOptimas(origen);
        if (indiceDestino == null || etiquetasOptimas == null) { //no existe el destino o el origen
            return null; //no se puede calcular
        } else { //sacamos la ruta óptima hacia ese destino usando el backtrace

            int indiceVerticeProcesado = indiceDestino; //es dodne empezamos de acuerdo al backtrace, empezando al último

            do {
                Vertice verticeProcesado = (Vertice) vertices.obtener(indiceVerticeProcesado);
                ruta.agregarInicio(verticeProcesado.getDescripcion()); //agregamos a la ruta la descripcón del vértice procesado

                EtiquetaGrafoPonderado etiquetaVerticeProcesado = (EtiquetaGrafoPonderado) etiquetasOptimas.obtener(indiceVerticeProcesado);
                //actualizamos el nuevo vértice procesado al anterior del vertice procesado actual
                indiceVerticeProcesado = etiquetaVerticeProcesado.getVerticeAnterior();
            } while (indiceVerticeProcesado != -1); //el -1 significa la ausencia de vérticeAnterior (-), ese solo lo tiene le vérticeOrigen
            return ruta;
        }
    }

    //paso 1
    private void inicializarEtiquetasOptimas(ArregloDatos etiquetasOptimas, int indiceVerticeOrigen, double metricaOrigen,
                                             double metricaVertices, int verticeAnteriorVertices) {
        for (int cadaVertice = 0; cadaVertice < vertices.cantidadElementos(); cadaVertice++) {
            EtiquetaGrafoPonderado etiquetaNueva = new EtiquetaGrafoPonderado();
            etiquetaNueva.setIteracion(0);
            etiquetaNueva.setMetricaAcumulada(metricaVertices); //por ejemplo ponerles infinito
            etiquetaNueva.setVerticeAnterior(verticeAnteriorVertices); //por ejemplo ponerle el -
            etiquetasOptimas.agregar(etiquetaNueva); //agregamos al etiqueta
        }
        //al final, solo al vértice origen, cambiarle el valor de métrica acumulada a 0, por ejemplo
        EtiquetaGrafoPonderado etiquetaVerticeOrigen = (EtiquetaGrafoPonderado) etiquetasOptimas.obtener(indiceVerticeOrigen);
        etiquetaVerticeOrigen.setMetricaAcumulada(metricaOrigen); //por ejmeplo 0
    }

    //paso 2, Calcular los nuevos valores de las etiquetas a partir de las métricas acumuladas hacia los vecinos
    //no marcados como permanentes, partiendo del vértice actual (si se mejora la métrica, se actualiza la etiqueta).
    private void actualizarMetriculasAcumuladasOptimas(ArregloDatos etiquetasOptimas, ArregloDatos marcadosPermanentes,
                                                       int verticeActual, int iteracion, double infinito) {
        for (int cadaVestino = 0; cadaVestino < aristas.getColumnas(); cadaVestino++) {
            //recorrer todos los nodos del grafo y checar quién es adyacente al vérttice actual y que no esté marcado
            Double metricaOrigenActualDestino = (Double) aristas.obtenerInfo(verticeActual, cadaVestino);
            if (metricaOrigenActualDestino != null && metricaOrigenActualDestino != 0 && metricaOrigenActualDestino != infinito &&
                    ((boolean) marcadosPermanentes.obtener(cadaVestino)) == false) {
                //dado que es vecino adyacente y no marcado, calcularemos las métricas acumuladas desde el vértice actual
                //a ese adyacente y si resulta mejor que la métrica que tiene acumulada ese vértice adyacente, se substituye

                //sacamos la métrica acumulada del vértice actual
                EtiquetaGrafoPonderado etiquetaVerticeActual = (EtiquetaGrafoPonderado) etiquetasOptimas.obtener(verticeActual);
                double metricaVerticeActual = etiquetaVerticeActual.getMetricaAcumulada();
                //sumamos la métrica del vértice actual + la métrica del vertice actual hacia el destino
                double metricaAcumuladaOrigenActualDestino = metricaVerticeActual + metricaOrigenActualDestino;

                //Obtener la métrica de la etiqueta del destino
                EtiquetaGrafoPonderado etiquetaVerticeDestino = (EtiquetaGrafoPonderado) etiquetasOptimas.obtener(cadaVestino);
                double metricaVerticeDestino = etiquetaVerticeDestino.getMetricaAcumulada();
                //Comparar si es mejor la metrica acumulada del origen actual hacia el destino que la métrica del detino
                //Solo checar a partir de si es DESC o ASC
                boolean actualizarEtiquetaDestino = false;
                if (orden == TipoOrden.Desordenado) { //significa que mientras más pequeño es mejor
                    if (metricaAcumuladaOrigenActualDestino < metricaVerticeDestino) {
                        // es más óptima, se debe actualizar la etiqueta del destino
                        actualizarEtiquetaDestino = true;
                    }
                } else { //ASC, significa que el valor más grande es mejor
                    if (metricaAcumuladaOrigenActualDestino > metricaVerticeDestino) {
                        // es más óptima, se debe actualizar la etiqueta del destino
                        actualizarEtiquetaDestino = true;
                    }
                }
                if (actualizarEtiquetaDestino == true) { //actualizar etiqueta destino
                    etiquetaVerticeDestino.setMetricaAcumulada(metricaAcumuladaOrigenActualDestino);
                    etiquetaVerticeDestino.setVerticeAnterior(verticeActual);
                    etiquetaVerticeDestino.setIteracion(iteracion);
                } //if
            }//if de adycencia y no marcado
        } //del ciclo for
    }

    //paso 3
    private int obtenerVerticeMetricaOptima(ArregloDatos etiquetasOptimas, ArregloDatos marcadosPermanentes, double infinito) {
        //Lo primero que se hace cuando se quiere obtener el más chico o el más grande de un arreglo
        //es asumir que el PRIMERO (que el infinito va a ser el óptimo inicial) es el más chico o más grande y
        // después todos los comparamos con ese
        //si es mejor alguno de ellos, este se vuelve el nuevo mejor
        double mejorMetrica = infinito; //es la métrica inicial óptima
        int verticeOptimo = -1; //no lo conozco por ahora

        //Obtener el mejor (más pequeño o grande) del arreglo de TODAS las etiquetas no marcadas
        for (int cadaVertice = 0; cadaVertice < vertices.cantidadElementos(); cadaVertice++) {
            if (((boolean) marcadosPermanentes.obtener(cadaVertice)) == false) { //no está marcado es destino
                EtiquetaGrafoPonderado etiquetaCadaVertice = (EtiquetaGrafoPonderado) etiquetasOptimas.obtener(cadaVertice);
                double metricCadaVertice = etiquetaCadaVertice.getMetricaAcumulada(); //obtengo la métrica del destino
                //comapramos esta métrica paraver si es mejor que la que yo ya tenia como la mejor
                //no olvidar considerar el DESC o ASC
                if (orden == TipoOrden.Desordenado) { //más chico es mejor
                    if (metricCadaVertice < mejorMetrica) { //significa que la métrica del vértice es mejor que la de antes
                        mejorMetrica = metricCadaVertice; //cambiamos la mejor métrica
                        verticeOptimo = cadaVertice;//cambiamos el vértice óptimo
                    }
                } else { //ASC, más grande es mejor
                    if (metricCadaVertice > mejorMetrica) { //significa que la métrica del vértice es mejor que la de antes
                        mejorMetrica = metricCadaVertice; //cambiamos la mejor métrica
                        verticeOptimo = cadaVertice;//cambiamos el vértice óptimo
                    }
                }
            }
        } //del for
        return verticeOptimo; //regresamos el vértice on la métrica más chica o más grande según TipoOrden
    }

    //Método que calcula las etiquetas
    public ArregloDatos etiquetasOptimas(Object origen) {
        ArregloDatos etiquetasOptimas = new ArregloDatos(vertices.cantidadElementos());
        ArregloDatos marcadosPermanentes = new ArregloDatos(vertices.cantidadElementos());
        double infinito = 0.0;

        if (orden == TipoOrden.Desordenado) { //quiere decir que mientras más chico mejor, por ejemplo tiempo o distancia
            infinito = Double.MAX_VALUE; //+ infinito
        } else { //ASC, quiere decir que mientras más grande mejor, por ejemplo la velocidad en un enlace de una red de comp.
            infinito = Double.MIN_VALUE; // -infinito
        }

        //0. Validar que el vértice origen exista
        Integer indiceVerticeOrigen = (Integer) vertices.buscar(origen);
        if (indiceVerticeOrigen == null) { //no existe ese vértice
            return null;
        } else { //si existe el vértice origen
            //1.- Inicializar etiquetas, partiendo de un nodo origen, marcar como permanente ese nodo.
            inicializarEtiquetasOptimas(etiquetasOptimas, indiceVerticeOrigen, 0.0, infinito, -1);
            int verticeActual = indiceVerticeOrigen; //donde comenzamos
            marcadosPermanentes.rellenar(false, marcadosPermanentes.capacidad()); //rellenamos todo como no marcado
            marcadosPermanentes.cambiar(indiceVerticeOrigen, true); //marcamos el vértice origen

            for (int iteracion = 1; iteracion < vertices.cantidadElementos(); iteracion++) { //el número de vértices -1
                //Repetir pasos 2 y 3
                //2.-Calcular los nuevos valores de las etiquetas a partir de las métricas acumuladas hacia los vecinos
                //no marcados como permanentes, partiendo del vértice actual (si se mejora la métrica, se actualiza la etiqueta).
                actualizarMetriculasAcumuladasOptimas(etiquetasOptimas, marcadosPermanentes, verticeActual, iteracion, infinito);

                //3.- Elegir el vértice con la mejor métrica acumulada que no esté marcado,
                // se marca y se vuelve el vértice actual.
                verticeActual = obtenerVerticeMetricaOptima(etiquetasOptimas, marcadosPermanentes, infinito); //el óptimo se vuelve el actual
                marcadosPermanentes.cambiar(verticeActual, true); //lo marcamos
            }
            return etiquetasOptimas;
        }
    }


    /**
     * Este metodo elimina por completo un vertice del grafo
     *
     * @param vertice
     * @return
     */
    public Object eliminarVertice(Object vertice) {
        aristas.quitarColumna((Integer) vertices.buscar(vertice)); //Eliminamos la columna de la matriz que le tiene al vértice
        aristas.quitarFila((Integer) vertices.buscar(vertice)); // Eliminamos el renglón de la matriz que le tiene al vértice
        return vertices.eliminar(vertice);// eliminamos el dato y lo regresamos
    }

    /**
     * Este metodo indica si un vector con otro es Adyasente o no
     *
     * @param inicio
     * @param destino
     * @return true o false
     */
    public boolean esAdyacente(Object inicio, Object destino) {
        Integer posicion1 = (Integer) vertices.buscar(inicio);// busca el inicio
        Integer posicion2 = (Integer) vertices.buscar(destino); // busca el destno
        if (posicion1 != null && posicion2 != null) {
            Double arista = Double.parseDouble(aristas.obtenerInfo(posicion1, posicion2).toString());
            boolean EsAdyacente = (arista != 0.0 && arista != Double.MAX_VALUE && arista != Double.MIN_VALUE) ? true : false;
            return EsAdyacente;
        } else {
            return false;
        }

    }

    /**
     * Este metodo noa ayuda a poder eliminar algua arista dentro de el grafo asignado
     *
     * @param inicio
     * @param destino
     * @return true o false
     */
    public boolean EliminarArista(Object inicio, Object destino) {
        if (esAdyacente(inicio, destino) == true) {// verificamos de que sean adyacentes los datos
            int eliminarInicio = (int) vertices.buscar(inicio);// buscamos el dato
            int eliminarDestino = (int) vertices.buscar(destino);// buscamos el dato
            aristas.asignarCelda(eliminarInicio, eliminarDestino, 0.0);// cambiamos la informacion por un 0.0
            aristas.asignarCelda(eliminarDestino, eliminarInicio, 0.0);// indicando que ahora los datos no existen
            return true;
        }
        return false;
    }

    /**
     * metodo que nos ayuda a obtener el resultado si es que existe un vertice dentro de el grafo
     *
     * @param vertice
     * @return vertice
     */
    public Vertice buscarVertice(Object vertice) {
        Integer buscarVertice = (Integer) vertices.buscar(vertice);// obtenemos la posicion de el vertice
        if (buscarVertice != null) {// verificamos de que no sea null
            return (Vertice) vertices.obtener(buscarVertice);
        } else {
            return null;
        }
    }


    /**
     * Método para poder comprobar si es pseudografo
     *
     * @return true si lo es, false si no lo es.
     */
    public boolean esPseudografo() {
        int contador = 0;// iniciamos contador
        for (int i = 0; i < vertices.cantidadElementos(); i++) {// recorremos los datos que estan en el vertice
            Double arista = Double.parseDouble(aristas.obtenerInfo(i, i).toString());// obtenemos los datos de la fila
            if (arista != 0.0 && arista != Double.MAX_VALUE && arista != Double.MIN_VALUE) { //Existe relación
                contador++;//aumentamos posicion
            }
        }
        if (contador != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este metodo lo que hace es que regresa verdadero solamente si al menos dos de sus vértices están conectados
     * entre sí por medio de dos aristas.
     *
     * @return true o false
     */
    public boolean esMultigrafo() {
        int contador = 0;
        for (int i = 0; i < vertices.cantidadElementos(); i++) {// recorremos
            if (esMultigrafo(vertices.obtener(i))) {// obtenemos los datos de el vertice
                contador++;
            }
        }
        if (contador > 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * metordo recursivo que nos dice y regresa si es un multigrafo o no
     *
     * @param vertice
     * @return true o false
     */
    private boolean esMultigrafo(Object vertice) {
        int contador = 0;
        for (int i = 0; i < vertices.cantidadElementos(); i++) {// recorremos posiciones
            if (esAdyacente(vertice, vertices.obtener(i)) && esAdyacente(vertices.obtener(i), vertice)) { //Comrpueba si hay relación entre los vértices
                contador++;// aumentamos contadoe
            }
        }
        if (contador != 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * este metodo nos permite saber cuantos datos estan agregados dentro de el vertice
     *
     * @param vertice
     * @return contador
     */
    public int grafoVertice(Object vertice) {
        int contador = 0;// iniciamos contados
        Integer verticeEncontrado = (Integer) vertices.buscar(vertice);// buscamos el dato dentro de los vertices agregados
        if (verticeEncontrado != null) {// en caso de que no sea null es de que existe el dato
            for (int i = 0; i < aristas.getColumnas(); i++) {// recorremos las columnas
                Double arista = Double.parseDouble(aristas.obtenerInfo(verticeEncontrado, i).toString());// recorremos los datos que le corresponden
                if (arista != 0.0 && arista != Double.MAX_VALUE && arista != Double.MIN_VALUE) {
                    contador++;// aumentamos posicion
                }
            }
            return contador;

        }
        return contador;
    }

    /**
     * metodo que nos indica si existe una ruta entre los vertices o no
     *
     * @param origen
     * @param destino
     * @return true o faslse
     */

    public boolean hayRuta(Object origen, Object destino) {
        Integer verticeOrigen = (Integer) vertices.buscar(origen);// buscamos el origen
        Integer verticeDestino = (Integer) vertices.buscar(destino);// buscamos el destino

        if (verticeOrigen != null && verticeDestino != null && !origen.toString().equalsIgnoreCase(destino.toString())) {//  si no es null y no son iguales
            return hayRuta(verticeOrigen, verticeDestino, new ArregloDatos(vertices.cantidadElementos()));// mandamos llamar el metodo recursivo
        } else return false;
    }

    /**
     * metodo que nos ayuda a verificar si existe una arista conectaca con dos vertices en especial
     *
     * @param arista
     * @return true o false
     */
    private boolean ExisteCaminoArista(Double arista) {
        if (arista != 0.0 && arista != Double.MAX_VALUE && arista != Double.MAX_VALUE) {// verificamos que no sea 0 ni infinito
            return true;
        } else return false;
    }
    /**
     * metodo recursivo que nos regresa un true o false para saber si el grafo
     * contiene una ruta o no
     *
     * @param origen
     * @param destino
     * @param recorrido
     * @return true o false
     */
    private boolean hayRuta(int origen, int destino, ArregloDatos recorrido) {

        if (origen == destino) // Llego al destino, termina el programa.
            return true;
            // Verifica que no se haya recorrido el vertice anteriormente, evitando ciclos.
        else if (recorrido.buscar(vertices.obtener(origen)) == null) {
            boolean bandera = false; // Cambiara de valor si en dado caso se pudo llegar al destino.
            recorrido.agregar(vertices.obtener(origen)); // Registramos el vertice como ya recorrido/recorriendo.
            // Se recorrera los vertices que esten conectados al vertice actual.
            for (int i = 0; i < aristas.getColumnas(); i++) {
                Double arista = Double.parseDouble(aristas.obtenerInfo(origen, i).toString());
                if (bandera) {
                    return true;
                } // Se llego al destino.


                else if (arista != 0.0 && arista != Double.MAX_VALUE && arista != Double.MIN_VALUE) {
                    bandera = hayRuta(i, destino, recorrido);
                }

            }
            return bandera;
        }
        return false;
    }


    /**
     * metodo que nos muestra si un grafo complemantes es conexo o no
     *
     * @return true o false
     */
    public boolean esConexo() {
        int contador = 0;
        boolean esConexo = true;
        while (esConexo && contador < vertices.cantidadElementos()) {// mientras no sea mayor a la cantidad de elementos
            esConexo = (Double.parseDouble((aristas.obtenerInfo(contador, 0).toString())) != 0.0);// obtenemos los datos
            int j = 1;// asignamos variable
            while (!esConexo && j < vertices.cantidadElementos()) {// mientras no sea mayor a la cantidad de elementos
                esConexo = (Double.parseDouble((aristas.obtenerInfo(contador, j).toString())) != 0.0);// verificamos que no sea 0.0 para poder decir que es conexo
                j += 1;
            }
            contador += 1;
        }
        return esConexo;
    }


    /**
     * este metodo nos ayuda a saber si todos los vertices de el
     * grafo estan en linea recta y no existe ninguno que se cicle
     *
     * @return true o false
     */
    public boolean esDirigido() {
        for (int i = 0; i < aristas.getFilas(); i++) {
            for (int j = 0; j < aristas.getColumnas(); j++) {
                if (i != j) {
                    Object obtenerVertice1 = vertices.obtener(i);
                    Object obtenerVertice2 = vertices.obtener(j);
                    if (!(esAdyacente(obtenerVertice1, obtenerVertice2) && !esAdyacente(obtenerVertice2, obtenerVertice1))) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    /**
     * Metodo que nos ayuda a obtener los verices que estan en cada una de las aristas
     */
    public void listarAristas() {
        for (int i = 0; i < aristas.getFilas(); i++) {// obtenemos las filas
            for (int j = 0; j < aristas.getColumnas(); j++) {//obtenemos las columnas

                Double arista = Double.parseDouble(aristas.obtenerInfo(i, j).toString());// obtenemos los datos dentro de ellas
                if (arista != 0.0 && arista != Double.MAX_VALUE && arista != Double.MIN_VALUE) {// verificamos que la arista a obtener no sea 0
                    SalidaTerminal.consola(vertices.obtener(j) + "\n");// obtenemos los respectivos vertices

                }
            }
        }

    }

    /**
     * Este metodo nos ayuda a  poder obtener todos los vertives de la lista de aristas que le corresponde
     *
     * @param vertice
     */
    public void listarAristas(Object vertice) {
        Integer verticeEncontrado = (Integer) vertices.buscar(vertice);// verificamos que el dato se encuentre
        if (verticeEncontrado != null) {// si se encontro
            for (int i = 0; i < aristas.getColumnas(); i++) {
                Double arista = Double.parseDouble(aristas.obtenerInfo(verticeEncontrado, i).toString());
                if (arista != 0.0 && arista != Double.MAX_VALUE && arista != Double.MIN_VALUE) {

                    SalidaTerminal.consola(vertices.obtener(i) + "\n");// obtenemos los vertives

                }
            }
        }


    }

    /**
     * metodo que nos ayuda a saber cuales son los vertices que estan dentro de el
     * grafo junto con sus aristas/
     */
    public void listarVertices() {
        for (int i = 0; i < vertices.cantidadElementos(); i++) {// recorremos todas las posiciones de los vertices
            SalidaTerminal.consola(vertices.obtener(i) + "\n");// obtenemos los elementos dentro de los vertices
        }
    }



    /**
     * Metodo que nos ayuda a poder obtener el grafo minimo de ponderaciones dentro de el grafo original
     * @param InicioDeNodo_Posicion
     * @return GrafoArregloU (arreglo donde se tiene los nodos mas pequeños recorridos (caminos recorridos))
     */
    public ArregloDatos grafoMinimo(Object InicioDeNodo_Posicion) {
        ArregloDatos GrafoArregloU = new ArregloDatos(vertices.cantidadElementos());// creamos el arreglo en donde estara los datos
        Integer IndiceVerticeBuscado = (Integer) vertices.buscar(InicioDeNodo_Posicion);// verificamos que la primera posicion a mandar exista
        if (IndiceVerticeBuscado != null) {
            GrafoArregloU.agregar(vertices.obtener(IndiceVerticeBuscado));// obtenemos las posiciones de los vertices de el grafo y las agregamos a el arreglo
            ListaEncadenada lista = new ListaEncadenada();// creamos la lista encadenada
            Object verticeCostoMinimo = null;
            Object ObtenerParVertices;
            Object ObtenerParVertices2_Inverso;
            Object RecolectorParVertice1 = null;
            Object RecolectorParVertice2 = null;
            while (vertices.cantidadElementos() != GrafoArregloU.cantidadElementos()) {// mientras sea diferente en cuando a cantidades
                Double AristaGrafiVertice;
                Double CostoRecorridoMinimo;// obtenemos el costo minimo de los datos
                CostoRecorridoMinimo = Double.MAX_VALUE;// asignamos infinito
                for (int i = 0; i < GrafoArregloU.cantidadElementos(); i++) {// recorremos los datos de el arreglo
                    IndiceVerticeBuscado = (Integer) vertices.buscar(GrafoArregloU.obtener(i));// obtenemos las posiciones para poder buscarlas dentro de los vertices actuales
                    for (int j = 0; j < aristas.getColumnas(); j++) {// recorremos las columnas de las aristas
                        AristaGrafiVertice = Double.parseDouble(aristas.obtenerInfo(IndiceVerticeBuscado, j).toString());// obtenemos los datos de cada una de estas
                        if (ExisteCaminoArista(AristaGrafiVertice)==true) {// en caso de que al momento de obtener los datos estas tengan una arista en especial
                            ObtenerParVertices = IndiceVerticeBuscado + "," + j;// entonces esos dos valores los asignamos a una nueva variable
                            ObtenerParVertices2_Inverso = j + "," + IndiceVerticeBuscado;// al igual que su inverso (1,2)=normal , invertida=(2,1)
                            if (lista.buscar(ObtenerParVertices) == null && lista.buscar(ObtenerParVertices2_Inverso) == null) {
                                if (AristaGrafiVertice <= CostoRecorridoMinimo) {// ahora verificamos que esos datos su ruta sea la menos pesada
                                    CostoRecorridoMinimo = AristaGrafiVertice;// cambiamos los datos para que vayan los datos menores
                                    verticeCostoMinimo = vertices.obtener(j);// obtenemos los vertivesd de cada una de sus posiciones para despues para sacar su Costo minimo
                                    RecolectorParVertice1 = IndiceVerticeBuscado + "," + j;// obtenemos los verices
                                    RecolectorParVertice2 = j + "," + IndiceVerticeBuscado;// obtenemos los vertices
                                }
                            }
                        }
                    }
                }
                // terminando los castos minimos de cada una de las posiciones entonces agregamos los resultados a cada una de las listas y las agregamos a el arreglo de datos
                lista.agregar(RecolectorParVertice1);
                lista.agregar(RecolectorParVertice2);
                GrafoArregloU.agregar(verticeCostoMinimo);
            }
        }//posteriormente retormanos los datos dentro que estan de el arreglo
        return GrafoArregloU;
    }

}