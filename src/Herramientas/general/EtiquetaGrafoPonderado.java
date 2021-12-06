package Herramientas.general;
/**
 * Clase que nos ayuda a implementar la funcionalidad de grafos matriz
 * @profesor_Aldonso
 * @version 1
 */
public class EtiquetaGrafoPonderado {
    protected double metricaAcumulada;
    protected int verticeAnterior;
    protected int iteracion;

    public double getMetricaAcumulada() {
        return metricaAcumulada;
    }

    public int getVerticeAnterior() {
        return verticeAnterior;
    }

    public int getIteracion() {
        return iteracion;
    }

    public void setMetricaAcumulada(double metricaAcumulada) {
        this.metricaAcumulada = metricaAcumulada;
    }

    public void setVerticeAnterior(int verticeAnterior) {
        this.verticeAnterior = verticeAnterior;
    }

    public void setIteracion(int iteracion) {
        this.iteracion = iteracion;
    }
    @Override
    public String toString(){
        return "["+ metricaAcumulada + ", "+ verticeAnterior +"] " + iteracion;
    }
}
