package registros.BaseDeDatosABB.TipoCategorias;

/**
 * clase que representan las celdas en donde se le asignara a dicha tabla con el archivo excel
 * @author Alexis Ultreras Sotelo
 * @version 1.0
 */
public class Tipo__categories_tab {
    protected  Object category_name;
    protected  Object Category_description;
    protected  Object CATEGORY_ID;
    protected  Object PARENT_CATEGORY_ID;

    public Tipo__categories_tab(Object category_name, Object category_description, Object CATEGORY_ID, Object PARENT_CATEGORY_ID) {
        this.category_name = category_name;
        Category_description = category_description;
        this.CATEGORY_ID = CATEGORY_ID;
        this.PARENT_CATEGORY_ID = PARENT_CATEGORY_ID;
    }

    public Object getCategory_name() {
        return category_name;
    }

    public void setCategory_name(Object category_name) {
        this.category_name = category_name;
    }

    public Object getCategory_description() {
        return Category_description;
    }

    public void setCategory_description(Object category_description) {
        Category_description = category_description;
    }

    public Object getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(Object CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public Object getPARENT_CATEGORY_ID() {
        return PARENT_CATEGORY_ID;
    }

    public void setPARENT_CATEGORY_ID(Object PARENT_CATEGORY_ID) {
        this.PARENT_CATEGORY_ID = PARENT_CATEGORY_ID;
    }

    @Override
    public String toString() {
        return  category_name.toString() +"    " + Category_description.toString() +"    "+ CATEGORY_ID.toString()+"    "+ PARENT_CATEGORY_ID.toString()+"\n";
    }
}
