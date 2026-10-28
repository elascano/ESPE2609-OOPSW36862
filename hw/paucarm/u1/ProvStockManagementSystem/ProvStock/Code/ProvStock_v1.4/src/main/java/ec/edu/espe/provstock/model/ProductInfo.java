package ec.edu.espe.provstock.model;

/**
 * Argument object with the data of a product that the user can change (Table B2).
 * It replaces the long lists of parameters of the constructor and of update() in Product.
 * The required data goes in the constructor and the optional data in the with...() methods.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class ProductInfo {

    private final String name;
    private final String category;
    private final String unitOfMeasure;
    private String description = "";
    private String brand = "";
    private String location = "";
    private double purchaseCost;
    private double salePrice;
    private double minimumStock;

    public ProductInfo(String name, String category, String unitOfMeasure) {
        this.name = name;
        this.category = category;
        this.unitOfMeasure = unitOfMeasure;
    }

    public ProductInfo withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductInfo withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductInfo withLocation(String location) {
        this.location = location;
        return this;
    }

    public ProductInfo withPrices(double purchaseCost, double salePrice) {
        this.purchaseCost = purchaseCost;
        this.salePrice = salePrice;
        return this;
    }

    public ProductInfo withMinimumStock(double minimumStock) {
        this.minimumStock = minimumStock;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getLocation() {
        return location;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getMinimumStock() {
        return minimumStock;
    }
}
