package registros.BaseDeDatosABB.TipoCategorias;

/**
 * clase que representan las celdas en donde se le asignara a dicha tabla con el archivo excel
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Tipo_product_information {
    protected  String PRODUCT_ID;
    protected  String PRODUCT_NAME;
    protected  String PRODUCT_DESCRIPTION;
    protected  String CATEGORY_ID;
    protected  String WEIGHT_CLASS;
    protected  String WARRANTY_PERIOD;
    protected  String SUPPLIER_ID;
    protected  String PRODUCT_STATUS;
    protected  String LIST_PRICE;
    protected  String MIN_PRICE;
    protected  String CATALOG_URL;

    public Tipo_product_information(String PRODUCT_ID, String PRODUCT_NAME, String PRODUCT_DESCRIPTION, String CATEGORY_ID, String WEIGHT_CLASS, String WARRANTY_PERIOD, String SUPPLIER_ID, String PRODUCT_STATUS, String LIST_PRICE, String MIN_PRICE, String CATALOG_URL) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
        this.CATEGORY_ID = CATEGORY_ID;
        this.WEIGHT_CLASS = WEIGHT_CLASS;
        this.WARRANTY_PERIOD = WARRANTY_PERIOD;
        this.SUPPLIER_ID = SUPPLIER_ID;
        this.PRODUCT_STATUS = PRODUCT_STATUS;
        this.LIST_PRICE = LIST_PRICE;
        this.MIN_PRICE = MIN_PRICE;
        this.CATALOG_URL = CATALOG_URL;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCT_DESCRIPTION() {
        return PRODUCT_DESCRIPTION;
    }

    public void setPRODUCT_DESCRIPTION(String PRODUCT_DESCRIPTION) {
        this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
    }

    public String getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(String CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getWEIGHT_CLASS() {
        return WEIGHT_CLASS;
    }

    public void setWEIGHT_CLASS(String WEIGHT_CLASS) {
        this.WEIGHT_CLASS = WEIGHT_CLASS;
    }

    public String getWARRANTY_PERIOD() {
        return WARRANTY_PERIOD;
    }

    public void setWARRANTY_PERIOD(String WARRANTY_PERIOD) {
        this.WARRANTY_PERIOD = WARRANTY_PERIOD;
    }

    public String getSUPPLIER_ID() {
        return SUPPLIER_ID;
    }

    public void setSUPPLIER_ID(String SUPPLIER_ID) {
        this.SUPPLIER_ID = SUPPLIER_ID;
    }

    public String getPRODUCT_STATUS() {
        return PRODUCT_STATUS;
    }

    public void setPRODUCT_STATUS(String PRODUCT_STATUS) {
        this.PRODUCT_STATUS = PRODUCT_STATUS;
    }

    public String getLIST_PRICE() {
        return LIST_PRICE;
    }

    public void setLIST_PRICE(String LIST_PRICE) {
        this.LIST_PRICE = LIST_PRICE;
    }

    public String getMIN_PRICE() {
        return MIN_PRICE;
    }

    public void setMIN_PRICE(String MIN_PRICE) {
        this.MIN_PRICE = MIN_PRICE;
    }

    public String getCATALOG_URL() {
        return CATALOG_URL;
    }

    public void setCATALOG_URL(String CATALOG_URL) {
        this.CATALOG_URL = CATALOG_URL;
    }

    @Override
    public String toString() {
        return  PRODUCT_ID.toString()+" "+ PRODUCT_NAME.toString()+" "+  PRODUCT_DESCRIPTION.toString()+" "+  CATEGORY_ID.toString()+" "+ WEIGHT_CLASS.toString()+" "+ WARRANTY_PERIOD.toString()+" "+ SUPPLIER_ID.toString()+" "+ PRODUCT_STATUS.toString()+" "+  LIST_PRICE.toString() +" "+ MIN_PRICE.toString()+" "+ CATALOG_URL.toString()+"\n";


    }
}
