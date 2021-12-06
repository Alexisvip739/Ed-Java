package registros.BaseDeDatosABB.TipoCategorias;

/**
 * clase que representan las celdas en donde se le asignara a dicha tabla con el archivo excel
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Tipo_order_items {
    protected String ORDER_ID;
    protected String  LINE_ITEM_ID;
    protected String PRODUCT_ID;
    protected String UNIT_PRICE;
    protected String QUANTITY;

    public Tipo_order_items(String ORDER_ID, String LINE_ITEM_ID, String PRODUCT_ID, String UNIT_PRICE, String QUANTITY) {
        this.ORDER_ID = ORDER_ID;
        this.LINE_ITEM_ID = LINE_ITEM_ID;
        this.PRODUCT_ID = PRODUCT_ID;
        this.UNIT_PRICE = UNIT_PRICE;
        this.QUANTITY = QUANTITY;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getLINE_ITEM_ID() {
        return LINE_ITEM_ID;
    }

    public void setLINE_ITEM_ID(String LINE_ITEM_ID) {
        this.LINE_ITEM_ID = LINE_ITEM_ID;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getUNIT_PRICE() {
        return UNIT_PRICE;
    }

    public void setUNIT_PRICE(String UNIT_PRICE) {
        this.UNIT_PRICE = UNIT_PRICE;
    }

    public String getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    @Override
    public String toString() {
        return ORDER_ID.toString() + " "+ LINE_ITEM_ID.toString() + " "+ PRODUCT_ID.toString() +" "+ UNIT_PRICE.length() +" "+ QUANTITY.toString()+"\n" ;
    }
}
