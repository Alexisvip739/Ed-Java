package estructuraslineales;

public class ArregloColaPrioridad{
    protected int CAPACIDAD;
    protected   Object[] lote;
    protected int tope;

    public ArregloColaPrioridad(int CAPACIDAD) {
        this.CAPACIDAD = CAPACIDAD;
        lote= new Object[CAPACIDAD];
        tope=0;

    }
    public boolean vacia(){
        if (tope==0){
            return true;
        }else{
            return false;
        }
    }


    public boolean lleno(){
        if (tope==(CAPACIDAD)){
            return true;
        }else{
            return false;
        }
    }


}
