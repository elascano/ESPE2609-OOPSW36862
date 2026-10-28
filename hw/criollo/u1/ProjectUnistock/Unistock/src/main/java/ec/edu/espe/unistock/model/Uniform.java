package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.math.BigDecimal;

public class Uniform extends Product {

    private final String size;
    private final String school;

    public Uniform(int id, String name, BigDecimal price, int stock, String size, String school) {
        super(id, name, price, stock, ProductCategory.UNIFORM);
        this.size = size;
        this.school = school;
    }

    public String getSize() {
        return size;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public String getDetails() {
        return "Talla: " + size + " | Colegio: " + school;
    }
}
