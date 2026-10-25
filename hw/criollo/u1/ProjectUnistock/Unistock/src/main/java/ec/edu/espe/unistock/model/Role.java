package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

public enum Role {
    ADMINISTRATOR("Administrador", true, true, true),
    SALESPERSON("Vendedor", false, true, false),
    CUSTOMER("Cliente", false, false, false);

    private final String label;
    private final boolean canManageProducts;
    private final boolean canRegisterSales;
    private final boolean canManageUsers;

    Role(String label, boolean canManageProducts, boolean canRegisterSales, boolean canManageUsers) {
        this.label = label;
        this.canManageProducts = canManageProducts;
        this.canRegisterSales = canRegisterSales;
        this.canManageUsers = canManageUsers;
    }

    public String getLabel() {
        return label;
    }

    public boolean canManageProducts() {
        return canManageProducts;
    }

    public boolean canRegisterSales() {
        return canRegisterSales;
    }

    public boolean canManageUsers() {
        return canManageUsers;
    }
}
