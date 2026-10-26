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
    public Product(int id, String code, String name, String description, String category, String brand,
            String unitOfMeasure, String location, double purchaseCost, double salePrice, double minimumStock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.unitOfMeasure = unitOfMeasure;
        this.location = location;
        this.purchaseCost = purchaseCost;
        this.salePrice = salePrice;
        this.minimumStock = minimumStock;
        this.currentStock = 0;
        this.active = true;
    }

    /**
     * Changes the data of the product. The key (code) never changes (BR-08).
     */
    public void update(String name, String description, String category, String brand, String unitOfMeasure,
            String location, double purchaseCost, double salePrice, double minimumStock) {
        checkData(name, category, unitOfMeasure, purchaseCost, salePrice, minimumStock);
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.unitOfMeasure = unitOfMeasure;
        this.location = location;
        this.purchaseCost = purchaseCost;
        this.salePrice = salePrice;
        this.minimumStock = minimumStock;
    }

    public boolean validate() {
        if (code == null || code.isBlank()) {
            return false;
        }
        try {
            checkData(name, category, unitOfMeasure, purchaseCost, salePrice, minimumStock);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
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

    private static void checkData(String name, String category, String unitOfMeasure,
            double purchaseCost, double salePrice, double minimumStock) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (!Arrays.asList(CATEGORIES).contains(category)) {
            throw new IllegalArgumentException("Categoría no válida: " + category);
        }
        if (!Arrays.asList(UNITS_OF_MEASURE).contains(unitOfMeasure)) {
            throw new IllegalArgumentException("Unidad de medida no válida: " + unitOfMeasure);
        }
        if (purchaseCost < 0 || salePrice <= 0 || minimumStock < 0) {
            throw new IllegalArgumentException("Los costos, precios y el stock mínimo no pueden ser negativos");
        }
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
