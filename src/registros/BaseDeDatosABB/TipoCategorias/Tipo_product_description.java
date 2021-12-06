package registros.BaseDeDatosABB.TipoCategorias;

/**
 * clase que representan las celdas en donde se le asignara a dicha tabla con el archivo excel
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Tipo_product_description {
    protected String PRODUCT_ID;
    protected String LANGUAGE_ID;
    protected String TRANSLATED_NAME;
    protected String TRANSLATED_DESCRIPTION;

    public Tipo_product_description(String PRODUCT_ID, String LANGUAGE_ID, String TRANSLATED_NAME, String TRANSLATED_DESCRIPTION) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.LANGUAGE_ID = LANGUAGE_ID;
        this.TRANSLATED_NAME = TRANSLATED_NAME;
        this.TRANSLATED_DESCRIPTION = TRANSLATED_DESCRIPTION;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getLANGUAGE_ID() {
        return LANGUAGE_ID;
    }

    public void setLANGUAGE_ID(String LANGUAGE_ID) {
        this.LANGUAGE_ID = LANGUAGE_ID;
    }

    public String getTRANSLATED_NAME() {
        return TRANSLATED_NAME;
    }

    public void setTRANSLATED_NAME(String TRANSLATED_NAME) {
        this.TRANSLATED_NAME = TRANSLATED_NAME;
    }

    public String getTRANSLATED_DESCRIPTION() {
        return TRANSLATED_DESCRIPTION;
    }

    public void setTRANSLATED_DESCRIPTION(String TRANSLATED_DESCRIPTION) {
        this.TRANSLATED_DESCRIPTION = TRANSLATED_DESCRIPTION;
    }

    @Override
    public String toString() {
        return  PRODUCT_ID.toString()+" "+LANGUAGE_ID.toString() +" "+TRANSLATED_NAME.toString() +" "+ TRANSLATED_DESCRIPTION.toString()+"\n";
    }
}
