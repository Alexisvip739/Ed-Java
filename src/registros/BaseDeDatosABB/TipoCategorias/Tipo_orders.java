package registros.BaseDeDatosABB.TipoCategorias;

/**
 * clase que representan las celdas en donde se le asignara a dicha tabla con el archivo excel
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Tipo_orders {
    protected  String ORDER_ID;
    protected  String ORDER_DATE;
    protected  String ORDER_MODE;
    protected  String CUSTOMER_ID;
    protected  String ORDER_STATUS;
    protected  String ORDER_TOTAL;
    protected  String  SALES_REP_ID;
    protected  String PROMOTION_ID;


    public Tipo_orders(String ORDER_ID, String ORDER_DATE, String ORDER_MODE, String CUSTOMER_ID, String ORDER_STATUS, String ORDER_TOTAL, String SALES_REP_ID, String PROMOTION_ID) {
        this.ORDER_ID = ORDER_ID;
        this.ORDER_DATE = ORDER_DATE;
        this.ORDER_MODE = ORDER_MODE;
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.ORDER_STATUS = ORDER_STATUS;
        this.ORDER_TOTAL = ORDER_TOTAL;
        this.SALES_REP_ID = SALES_REP_ID;
        this.PROMOTION_ID = PROMOTION_ID;
    }


    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getORDER_DATE() {
        return ORDER_DATE;
    }

    public void setORDER_DATE(String ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }

    public String getORDER_MODE() {
        return ORDER_MODE;
    }

    public void setORDER_MODE(String ORDER_MODE) {
        this.ORDER_MODE = ORDER_MODE;
    }

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(String ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }

    public String getORDER_TOTAL() {
        return ORDER_TOTAL;
    }

    public void setORDER_TOTAL(String ORDER_TOTAL) {
        this.ORDER_TOTAL = ORDER_TOTAL;
    }

    public String getSALES_REP_ID() {
        return SALES_REP_ID;
    }

    public void setSALES_REP_ID(String SALES_REP_ID) {
        this.SALES_REP_ID = SALES_REP_ID;
    }

    public String getPROMOTION_ID() {
        return PROMOTION_ID;
    }

    public void setPROMOTION_ID(String PROMOTION_ID) {
        this.PROMOTION_ID = PROMOTION_ID;
    }

    @Override
    public String toString() {
        return  ORDER_ID.toString()+" "+  ORDER_DATE.toString()+" "+ ORDER_MODE.toString()+" "+  CUSTOMER_ID.toString()+" "+  ORDER_STATUS.toString()+" "+ ORDER_TOTAL.toString()+" "+  SALES_REP_ID.toString()+" "+ PROMOTION_ID.toString()+"\n";

    }
}
