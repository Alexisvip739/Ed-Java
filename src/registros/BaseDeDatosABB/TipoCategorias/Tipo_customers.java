package registros.BaseDeDatosABB.TipoCategorias;

/**
 * clase que representan las celdas en donde se le asignara a dicha tabla con el archivo excel
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Tipo_customers {
    protected  Object CUST_FIRST_NAME;
    protected  Object CUST_LAST_NAME;
    protected  Object CUST_ADDRESS;
    protected  Object PHONE_NUMBERS;
    protected  Object NLS_LANGUAGE;
    protected  Object NLS_TERRITORY;
    protected  Object CREDIT_LIMIT;
    protected  Object CUST_EMAIL;
    protected  Object ACCOUNT_MGR_ID;
    protected  Object CUST_GEO_LOCATION;
    protected  Object DATE_OF_BIRTH;
    protected  Object MARITAL_STATUS;
    protected  Object GENDER;
    protected  Object INCOME_LEVEL;


    public Tipo_customers(Object CUST_FIRST_NAME, Object CUST_LAST_NAME, Object CUST_ADDRESS, Object PHONE_NUMBERS, Object NLS_LANGUAGE, Object NLS_TERRITORY, Object CREDIT_LIMIT, Object CUST_EMAIL, Object ACCOUNT_MGR_ID, Object CUST_GEO_LOCATION, Object DATE_OF_BIRTH, Object MARITAL_STATUS, Object GENDER, Object INCOME_LEVEL) {
        this.CUST_FIRST_NAME = CUST_FIRST_NAME;
        this.CUST_LAST_NAME = CUST_LAST_NAME;
        this.CUST_ADDRESS = CUST_ADDRESS;
        this.PHONE_NUMBERS = PHONE_NUMBERS;
        this.NLS_LANGUAGE = NLS_LANGUAGE;
        this.NLS_TERRITORY = NLS_TERRITORY;
        this.CREDIT_LIMIT = CREDIT_LIMIT;
        this.CUST_EMAIL = CUST_EMAIL;
        this.ACCOUNT_MGR_ID = ACCOUNT_MGR_ID;
        this.CUST_GEO_LOCATION = CUST_GEO_LOCATION;
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
        this.MARITAL_STATUS = MARITAL_STATUS;
        this.GENDER = GENDER;
        this.INCOME_LEVEL = INCOME_LEVEL;
    }

    public Object getCUST_FIRST_NAME() {
        return CUST_FIRST_NAME;
    }

    public void setCUST_FIRST_NAME(Object CUST_FIRST_NAME) {
        this.CUST_FIRST_NAME = CUST_FIRST_NAME;
    }

    public Object getCUST_LAST_NAME() {
        return CUST_LAST_NAME;
    }

    public void setCUST_LAST_NAME(Object CUST_LAST_NAME) {
        this.CUST_LAST_NAME = CUST_LAST_NAME;
    }

    public Object getCUST_ADDRESS() {
        return CUST_ADDRESS;
    }

    public void setCUST_ADDRESS(Object CUST_ADDRESS) {
        this.CUST_ADDRESS = CUST_ADDRESS;
    }

    public Object getPHONE_NUMBERS() {
        return PHONE_NUMBERS;
    }

    public void setPHONE_NUMBERS(Object PHONE_NUMBERS) {
        this.PHONE_NUMBERS = PHONE_NUMBERS;
    }

    public Object getNLS_LANGUAGE() {
        return NLS_LANGUAGE;
    }

    public void setNLS_LANGUAGE(Object NLS_LANGUAGE) {
        this.NLS_LANGUAGE = NLS_LANGUAGE;
    }

    public Object getNLS_TERRITORY() {
        return NLS_TERRITORY;
    }

    public void setNLS_TERRITORY(Object NLS_TERRITORY) {
        this.NLS_TERRITORY = NLS_TERRITORY;
    }

    public Object getCREDIT_LIMIT() {
        return CREDIT_LIMIT;
    }

    public void setCREDIT_LIMIT(Object CREDIT_LIMIT) {
        this.CREDIT_LIMIT = CREDIT_LIMIT;
    }

    public Object getCUST_EMAIL() {
        return CUST_EMAIL;
    }

    public void setCUST_EMAIL(Object CUST_EMAIL) {
        this.CUST_EMAIL = CUST_EMAIL;
    }

    public Object getACCOUNT_MGR_ID() {
        return ACCOUNT_MGR_ID;
    }

    public void setACCOUNT_MGR_ID(Object ACCOUNT_MGR_ID) {
        this.ACCOUNT_MGR_ID = ACCOUNT_MGR_ID;
    }

    public Object getCUST_GEO_LOCATION() {
        return CUST_GEO_LOCATION;
    }

    public void setCUST_GEO_LOCATION(Object CUST_GEO_LOCATION) {
        this.CUST_GEO_LOCATION = CUST_GEO_LOCATION;
    }

    public Object getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }

    public void setDATE_OF_BIRTH(Object DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH = DATE_OF_BIRTH;
    }

    public Object getMARITAL_STATUS() {
        return MARITAL_STATUS;
    }

    public void setMARITAL_STATUS(Object MARITAL_STATUS) {
        this.MARITAL_STATUS = MARITAL_STATUS;
    }

    public Object getGENDER() {
        return GENDER;
    }

    public void setGENDER(Object GENDER) {
        this.GENDER = GENDER;
    }

    public Object getINCOME_LEVEL() {
        return INCOME_LEVEL;
    }

    public void setINCOME_LEVEL(Object INCOME_LEVEL) {
        this.INCOME_LEVEL = INCOME_LEVEL;
    }

    @Override
    public String toString() {
        return CUST_FIRST_NAME.toString() +" "+ CUST_LAST_NAME.toString() +" "+ CUST_ADDRESS +" "+ PHONE_NUMBERS.toString()+" "+ NLS_LANGUAGE.toString() +" "+ NLS_TERRITORY.toString()+" "+ CREDIT_LIMIT.toString() +" "+ CUST_EMAIL.toString() +" "+ ACCOUNT_MGR_ID.toString()
                +" "+CUST_GEO_LOCATION.toString() +" "+ DATE_OF_BIRTH.toString() +" "+
                MARITAL_STATUS.toString() +" "+ GENDER.toString() +" "+
                INCOME_LEVEL.toString()+"\n";
    }
}
