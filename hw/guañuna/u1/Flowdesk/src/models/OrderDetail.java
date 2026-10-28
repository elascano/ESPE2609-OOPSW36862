package models;

import utils.Validation;

public class OrderDetail {
    private String detailId;
    private String itemName;
    private int quantity;
    private String description;
    private double unitPrice;

    private transient CatalogItem catalogItem;

    public OrderDetail(String detailId, String itemName, int quantity, String description, double unitPrice) {
        this.detailId = detailId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.description = description;
        this.unitPrice = unitPrice;
        validate();
    }

    public double calculateSubtotal() {
        validate();
        double subtotal = quantity * unitPrice;
        Validation.requireNonNegativeAmount(subtotal, "subtotal");
        return subtotal;
    }

    void validate() {
        Validation.requireText(detailId, "identificador del detalle");
        Validation.requireText(itemName, "nombre del elemento");
        Validation.requireText(description, "descripcion");
        Validation.requirePositiveInt(quantity, "cantidad");
        Validation.requireNonNegativeAmount(unitPrice, "precio unitario");
    }

    public String getDetailId() { return detailId; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public String getDescription() { return description; }
    public double getUnitPrice() { return unitPrice; }

    public CatalogItem getCatalogItem() { return catalogItem; }
    public void setCatalogItem(CatalogItem catalogItem) { this.catalogItem = catalogItem; }

    public void setProduct(Product product) { 
        this.catalogItem = product; 
    }

    public void setService(Service service) { 
        this.catalogItem = service; 
    }

    public Product getProduct() { 
        return (catalogItem instanceof Product) ? (Product) catalogItem : null; 
    }

    public Service getService() { 
        return (catalogItem instanceof Service) ? (Service) catalogItem : null; 
    }
}