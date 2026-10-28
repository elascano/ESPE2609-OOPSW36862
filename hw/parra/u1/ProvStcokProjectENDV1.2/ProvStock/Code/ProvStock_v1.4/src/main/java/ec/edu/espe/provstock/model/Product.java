package ec.edu.espe.provstock.model;

import java.util.Arrays;

/**
 * An electrical material sold by the company (Table B2). (RF03)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class Product {

    //N2: allowed values of category and unitOfMeasure
    public static final String[] CATEGORIES = {"CABLES", "LIGHTING", "SWITCHES_AND_OUTLETS",
        "PROTECTION_DEVICES", "TOOLS", "OTHER"};
    public static final String[] UNITS_OF_MEASURE = {"UNIT", "METER", "ROLL", "BOX"};

    private int id;
    private String code;
    private String name;
    private String description;
    private String category;
    private String brand;
    private String unitOfMeasure;
    private String location;
    private double purchaseCost;
    private double salePrice;
    private double currentStock;
    private double minimumStock;
    private boolean active;

    /**
     * The stock starts in 0. The initial stock is loaded with
     * Inventory.adjustStock() and the reason "Inventario inicial" (AS-05, FR-05.4).
     */
    public Product(int id, String code, ProductInfo info) {
        this.id = id;
        this.code = code;
        this.currentStock = 0;
        this.active = true;
        apply(info);
    }

    /**
     * Changes the data of the product. The key (code) and the stock never change here (BR-08, FR-03.3).
     */
    public void update(ProductInfo info) {
        String error = findDataError(info);
        if (!error.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
        apply(info);
    }

    public boolean validate() {
        return code != null && !code.isBlank() && findDataError(getInfo()).isEmpty();
    }

    /**
     * @return the data of the product that the user can change
     */
    public ProductInfo getInfo() {
        return new ProductInfo(name, category, unitOfMeasure).withDescription(description).withBrand(brand)
                .withLocation(location).withPrices(purchaseCost, salePrice).withMinimumStock(minimumStock);
    }

    private void apply(ProductInfo info) {
        this.name = info.getName();
        this.description = info.getDescription();
        this.category = info.getCategory();
        this.brand = info.getBrand();
        this.unitOfMeasure = info.getUnitOfMeasure();
        this.location = info.getLocation();
        this.purchaseCost = info.getPurchaseCost();
        this.salePrice = info.getSalePrice();
        this.minimumStock = info.getMinimumStock();
    }

    public boolean hasStock(double quantity) {
        return currentStock >= quantity;
    }

    public boolean isLowStock() {
        return currentStock <= minimumStock;
    }

    public double getStockValue() {
        return Math.round(currentStock * purchaseCost * 100.0) / 100.0;
    }

    /**
     * N4: only Inventory must call this method; the current stock has no public setter (FR-03.3).
     * A positive quantity adds stock and a negative quantity takes stock out.
     */
    public void updateStock(double quantity) {
        if (currentStock + quantity < 0) {
            throw new IllegalArgumentException("El stock de " + name + " no puede quedar negativo");
        }
        currentStock = Math.round((currentStock + quantity) * 1000.0) / 1000.0;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    /**
     * @return the message of the first rule that the data breaks, or an empty text if the data is valid
     */
    private static String findDataError(ProductInfo info) {
        if (info.getName() == null || info.getName().isBlank()) {
            return "El nombre es obligatorio";
        }
        if (!Arrays.asList(CATEGORIES).contains(info.getCategory())) {
            return "Categoría no válida: " + info.getCategory();
        }
        if (!Arrays.asList(UNITS_OF_MEASURE).contains(info.getUnitOfMeasure())) {
            return "Unidad de medida no válida: " + info.getUnitOfMeasure();
        }
        if (info.getPurchaseCost() < 0 || info.getSalePrice() <= 0 || info.getMinimumStock() < 0) {
            return "Los costos, precios y el stock mínimo no pueden ser negativos";
        }
        return "";
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", code=" + code + ", name=" + name + ", category=" + category
                + ", brand=" + brand + ", unitOfMeasure=" + unitOfMeasure + ", location=" + location
                + ", purchaseCost=" + purchaseCost + ", salePrice=" + salePrice + ", currentStock=" + currentStock
                + ", minimumStock=" + minimumStock + ", active=" + active + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return the unitOfMeasure
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the purchaseCost
     */
    public double getPurchaseCost() {
        return purchaseCost;
    }

    /**
     * @return the salePrice
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * @return the currentStock
     */
    public double getCurrentStock() {
        return currentStock;
    }

    /**
     * @return the minimumStock
     */
    public double getMinimumStock() {
        return minimumStock;
    }
}
