package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.math.BigDecimal;

public class Stationery extends Product {

    private final String brand;
    private final String type;

    public Stationery(int id, String name, BigDecimal price, int stock, String brand, String type) {
        super(id, name, price, stock, ProductCategory.STATIONERY);
        this.brand = brand;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getDetails() {
        return "Marca: " + brand + " | Tipo: " + type;
    }
}
