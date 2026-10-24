package unistock.model;

public enum ProductCategory {
    UNIFORM("Uniforme"),
    STATIONERY("Papelería");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
