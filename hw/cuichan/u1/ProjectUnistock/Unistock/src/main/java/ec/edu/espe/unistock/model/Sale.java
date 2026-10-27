package ec.edu.espe.unistock.model;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.structure.SimpleList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Sale {

    public static final BigDecimal TAX_RATE = new BigDecimal("0.15");

    private final int id;
    private final LocalDateTime date;
    private final User seller;
    private final SimpleList<SaleDetail> items = new SimpleList<>();

    public Sale(int id, User seller) {
        this.id = id;
        this.seller = seller;
        this.date = LocalDateTime.now();
    }

    public void addItem(SaleDetail item) {
        items.add(item);
    }

    public BigDecimal subtotal() {
        BigDecimal total = BigDecimal.ZERO;
        items.resetIteration();
        while (items.hasNext()) {
            total = total.add(items.next().subtotal());
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal tax() {
        return subtotal().multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal total() {
        return subtotal().add(tax());
    }

    public BigDecimal getTotal() {
        return total();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getSeller() {
        return seller;
    }

    public SimpleList<SaleDetail> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}