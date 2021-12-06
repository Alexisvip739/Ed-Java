package registros.restaurante;

import Herramientas.TipoPlatillo;
import Herramientas.TipoProcedimientoDeCoicina;
import estructuraslineales.ListaEncadenada;

/**
 * Clase que prepara toda la comida para un cliente en especial
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Comida {
    protected String nombre;
    protected int presio;
    protected Ingredientes ingrediente1;
    protected Chef chef;
    protected TipoProcedimientoDeCoicina tipoProcedimientoDeCoicina;

    protected TipoPlatillo tipoPlatillo;

    public Comida(String nombre, int presio, Ingredientes ingrediente1, Chef chef, TipoProcedimientoDeCoicina tipoProcedimientoDeCoicina, TipoPlatillo tipoPlatillo) {
        this.nombre = nombre;
        this.presio = presio;
        this.ingrediente1 = ingrediente1;
        this.chef = chef;
        this.tipoProcedimientoDeCoicina = tipoProcedimientoDeCoicina;
        this.tipoPlatillo = tipoPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPresio() {
        return presio;
    }

    public void setPresio(int presio) {
        this.presio = presio;
    }

    public Ingredientes getIngrediente1() {
        return ingrediente1;
    }

    public void setIngrediente1(Ingredientes ingrediente1) {
        this.ingrediente1 = ingrediente1;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public TipoProcedimientoDeCoicina getTipoProcedimientoDeCoicina() {
        return tipoProcedimientoDeCoicina;
    }

    public void setTipoProcedimientoDeCoicina(TipoProcedimientoDeCoicina tipoProcedimientoDeCoicina) {
        this.tipoProcedimientoDeCoicina = tipoProcedimientoDeCoicina;
    }


    public TipoPlatillo getTipoPlatillo() {
        return tipoPlatillo;
    }

    public void setTipoPlatillo(TipoPlatillo tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "nombre='" + nombre + '\'' +
                ", presio=" + presio +
                ", ingrediente1=" + ingrediente1 +
                ", chef=" + chef +
                ", tipoProcedimientoDeCoicina=" + tipoProcedimientoDeCoicina +
                ", tipoPlatillo=" + tipoPlatillo +
                '}';
    }
}
