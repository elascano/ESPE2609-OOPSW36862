package ec.edu.espe.provstock.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * One of the three fixed roles and its permissions (SRS Table 3). (RF01)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class Role {

    //N2: allowed values of Role.name
    public static final String SALESPERSON = "SALESPERSON";
    public static final String WAREHOUSE_CLERK = "WAREHOUSE_CLERK";
    public static final String OWNER = "OWNER";

    //N7: official list of permissions
    public static final String MANAGE_USERS = "MANAGE_USERS";
    public static final String MANAGE_CUSTOMERS = "MANAGE_CUSTOMERS";
    public static final String DEACTIVATE_CUSTOMERS = "DEACTIVATE_CUSTOMERS";
    public static final String VIEW_PRODUCTS = "VIEW_PRODUCTS";
    public static final String MANAGE_PRODUCTS = "MANAGE_PRODUCTS";
    public static final String REGISTER_SALE = "REGISTER_SALE";
    public static final String VOID_SALE = "VOID_SALE";
    public static final String UPDATE_VAT_RATE = "UPDATE_VAT_RATE";
    public static final String ADJUST_STOCK = "ADJUST_STOCK";
    public static final String VIEW_INVENTORY = "VIEW_INVENTORY";
    public static final String MANAGE_SUPPLIERS = "MANAGE_SUPPLIERS";
    public static final String REGISTER_PURCHASE = "REGISTER_PURCHASE";
    public static final String VIEW_SALES_HISTORY = "VIEW_SALES_HISTORY";
    public static final String RECEIVE_ALERTS = "RECEIVE_ALERTS";
    public static final String GENERATE_REPORTS = "GENERATE_REPORTS";

    private int id;
    private String name;
    private List<String> permissions;

    public Role(int id, String name, List<String> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = new ArrayList<>(permissions);
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + ", permissions=" + permissions + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the permissions (needed to save the role in a file)
     */
    public List<String> getPermissions() {
        return Collections.unmodifiableList(permissions);
    }
}
