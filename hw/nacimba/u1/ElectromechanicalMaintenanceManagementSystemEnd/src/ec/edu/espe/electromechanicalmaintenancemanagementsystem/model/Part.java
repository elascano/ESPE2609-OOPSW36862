package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

public class Part {
    private int partId;
    private String name;
    private int stockQuantity;
    private float unitCost;
    private String supplier;

    public Part(int partId, String name, int stockQuantity, float unitCost, String supplier) {
        this.partId = partId;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unitCost = unitCost;
        this.supplier = supplier;
    }

    public int getPartId() {
        return partId;
    }
    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public float getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(float unitCost) {
        this.unitCost = unitCost;
    }

    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Part{" + "partId=" + partId + ", " + "name=" + name + ", " + "stockQuantity=" + stockQuantity + ", " + "unitCost=" + unitCost + ", " + "supplier=" + supplier + "}";
    }
}
