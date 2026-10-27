package ec.edu.espe.systemliquorstore.model;

import ec.edu.espe.systemliquorstore.util.Validation;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;

public class Sale {
    private int saleId;
    private String date;
    private String seller;
    private String paymentType;
    private int customerId;
    private ArrayList<SaleItem> items;
    private double subtotal;
    private double discountPercentage;
    private double discountAmount;
    private double totalAmount;
    private boolean ageVerified;
    private boolean completed;
    private static final Validation validation = new Validation();

    public Sale(int saleId, String date, String seller, String paymentType) {
        if (saleId <= 0 || !validation.isValidDate(date)
                || !validation.isText(seller) || !validation.isText(paymentType)) {
            throw new IllegalArgumentException("Los datos de la venta no son validos.");
        }
        String selectedSeller = seller.trim().toUpperCase(Locale.ROOT);
        String selectedPaymentType = paymentType.trim().toUpperCase(Locale.ROOT);
        if ((!selectedSeller.equals("OWNER") && !selectedSeller.equals("EMPLOYEE"))
                || (!selectedPaymentType.equals("CASH") && !selectedPaymentType.equals("CREDIT"))) {
            throw new IllegalArgumentException("Seleccione un vendedor y una forma de pago validos.");
        }
        this.saleId = saleId;
        this.date = date;
        this.seller = selectedSeller;
        this.paymentType = selectedPaymentType;
        this.customerId = 0;
        this.items = new ArrayList<SaleItem>();
        this.subtotal = 0;
        this.discountPercentage = 0;
        this.discountAmount = 0;
        this.totalAmount = 0;
        this.ageVerified = false;
        this.completed = false;
    }

    public SaleItem addProduct(Product product, int quantity) {
        requireDraft();
        if (product == null || !product.isValid() || quantity <= 0) {
            throw new IllegalArgumentException("Seleccione un producto valido y una cantidad mayor que cero.");
        }
        long accumulatedQuantity = (long) getProductQuantity(product.getProductCode()) + quantity;
        if (accumulatedQuantity > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("La cantidad acumulada del producto es demasiado grande.");
        }
        SaleItem item = new SaleItem(product.getProductCode(), product.getName(), quantity,
                product.getPurchaseCost(), product.getSellingPrice());
        if (!item.isValid()) {
            throw new IllegalArgumentException("El importe del producto es demasiado grande.");
        }
        double existingPurchaseCost = 0;
        for (SaleItem currentItem : items) {
            existingPurchaseCost += currentItem.calculatePurchaseCost();
        }
        if (!validation.isFinite(existingPurchaseCost + item.calculatePurchaseCost())) {
            throw new IllegalArgumentException("El costo acumulado de la venta es demasiado grande.");
        }
        double newSubtotal = validation.roundMoney(subtotal + item.calculateSubtotal());
        double newDiscountAmount = validation.roundMoney(newSubtotal * (discountPercentage / 100.0));
        double newTotal = validation.roundMoney(newSubtotal - newDiscountAmount);
        items.add(item);
        subtotal = newSubtotal;
        discountAmount = newDiscountAmount;
        totalAmount = newTotal;
        return item;
    }

    public void assignCustomer(Customer customer) {
        requireDraft();
        if (customer == null || !customer.isValid()) {
            throw new IllegalArgumentException("Seleccione un cliente registrado y valido.");
        }
        customerId = customer.getCustomerId();
    }

    public void applyDiscount(double percentage) {
        requireDraft();
        if (!validation.isFinite(percentage) || percentage < 0 || percentage >= 100) {
            throw new IllegalArgumentException("El descuento debe ser un numero valido entre 0 y menos de 100.");
        }
        double newSubtotal = calculateSubtotal();
        double newDiscountAmount = validation.roundMoney(newSubtotal * (percentage / 100.0));
        double newTotal = validation.roundMoney(newSubtotal - newDiscountAmount);
        discountPercentage = percentage;
        subtotal = newSubtotal;
        discountAmount = newDiscountAmount;
        totalAmount = newTotal;
    }

    public double calculateSubtotal() {
        double amount = 0;
        for (SaleItem item : items) {
            amount += item.calculateSubtotal();
        }
        return validation.roundMoney(amount);
    }

    public double calculateTotal() {
        double currentSubtotal = calculateSubtotal();
        double currentDiscount = validation.roundMoney(currentSubtotal * (discountPercentage / 100.0));
        return validation.roundMoney(currentSubtotal - currentDiscount);
    }

    public double calculateGrossProfit() {
        double purchaseCost = 0;
        for (SaleItem item : items) {
            purchaseCost += item.calculatePurchaseCost();
        }
        return validation.roundMoney(calculateTotal());
    }

    public double calculateMargin() {
        double currentTotal = calculateTotal();
        if (currentTotal <= 0) {
            throw new IllegalStateException("La venta debe tener un total mayor que cero para calcular el margen.");
        }
        return validation.roundMoney((calculateGrossProfit() / currentTotal) * 100.0);
    }

     public int calculateAge(String birthDate) {
        if (!validation.isValidDate(birthDate)) {
            return 0;
        }
        LocalDate birth = LocalDate.parse(birthDate);
        LocalDate saleDate = LocalDate.parse(date);
        if (birth.isAfter(saleDate)) {
            return 0;
        }
        int age = saleDate.getYear() - birth.getYear();
        if (saleDate.getMonthValue() < birth.getMonthValue()
                || (saleDate.getMonthValue() == birth.getMonthValue()
                && saleDate.getDayOfMonth() < birth.getDayOfMonth())) {
            age--;
        }
        return age;
    }

    public void confirmAge(String birthDate) {
        requireDraft();
        ageVerified = false;
        if (calculateAge(birthDate) < 18) {
            throw new IllegalStateException("El cliente debe tener al menos 18 años para realizar la compra.");
        }
        ageVerified = true;
    }

    public void complete(ArrayList<Product> products, ArrayList<StockMovement> movements) {
        requireDraft();
        if (!isValid()) {
            throw new IllegalStateException("Revise los productos, el total, el cliente y la verificacion de edad antes de completar la venta.");
        }
        if (products == null || movements == null) {
            throw new IllegalArgumentException("No se encontraron los registros de productos o movimientos.");
        }
        ArrayList<Product> selectedProducts = new ArrayList<Product>();
        for (SaleItem item : items) {
            Product selectedProduct = null;
            for (Product product : products) {
                if (product == null || !product.isValid()) {
                    throw new IllegalArgumentException("El inventario contiene un producto no valido.");
                }
                if (product.getProductCode().equalsIgnoreCase(item.getProductCode())) {
                    if (selectedProduct != null) {
                        throw new IllegalArgumentException("El inventario contiene un codigo de producto repetido.");
                    }
                    selectedProduct = product;
                }
            }
            if (selectedProduct == null) {
                throw new IllegalArgumentException("El producto " + item.getProductCode() + " ya no esta registrado.");
            }
            if (!selectedProducts.contains(selectedProduct)) {
                selectedProducts.add(selectedProduct);
            }
        }
        for (Product product : selectedProducts) {
            int requestedQuantity = getProductQuantity(product.getProductCode());
            if (requestedQuantity > product.getStock()) {
                throw new IllegalStateException("Stock insuficiente para " + product.getName()
                        + ". Disponible: " + product.getStock() + ", solicitado: " + requestedQuantity + ".");
            }
        }
        long maximumId = 0;
        for (int i = 0; i < movements.size(); i++) {
            StockMovement movement = movements.get(i);
            if (movement == null || !movement.isValid()) {
                throw new IllegalArgumentException("Existe un movimiento de inventario no valido.");
            }
            for (int j = 0; j < i; j++) {
                if (movements.get(j).getMovementId() == movement.getMovementId()) {
                    throw new IllegalArgumentException("Existe un identificador de movimiento repetido.");
                }
            }
            if (movement.getMovementId() > maximumId) {
                maximumId = movement.getMovementId();
            }
        }
        if (maximumId + selectedProducts.size() > Integer.MAX_VALUE) {
            throw new IllegalStateException("No se pueden generar mas identificadores de movimiento.");
        }
        ArrayList<StockMovement> newMovements = new ArrayList<StockMovement>();
        int nextId = (int) maximumId;
        for (Product product : selectedProducts) {
            nextId++;
            newMovements.add(product.createStockMovement(nextId, date, "EXIT",
                    getProductQuantity(product.getProductCode())));
        }
        for (Product product : selectedProducts) {
            product.decreaseStock(getProductQuantity(product.getProductCode()));
        }
        for (StockMovement movement : newMovements) {
            movements.add(movement);
        }
        completed = true;
    }

    public int getProductQuantity(String productCode) {
        if (!validation.isText(productCode)) {
            throw new IllegalArgumentException("El codigo del producto no puede estar vacio.");
        }
        long quantity = 0;
        for (SaleItem item : items) {
            if (item.getProductCode().equalsIgnoreCase(productCode.trim())) {
                quantity += item.getQuantity();
            }
        }
        if (quantity > Integer.MAX_VALUE) {
            throw new IllegalStateException("La cantidad acumulada del producto es demasiado grande.");
        }
        return (int) quantity;
    }

    public boolean isValid() {
        if (saleId <= 0 || !validation.isValidDate(date)
                || (!seller.equals("OWNER") && !seller.equals("EMPLOYEE"))
                || (!paymentType.equals("CASH") && !paymentType.equals("CREDIT"))
                || customerId < 0 || (paymentType.equals("CREDIT") && customerId == 0)
                || items.isEmpty() || !ageVerified
                || !validation.isFinite(discountPercentage)
                || discountPercentage < 0 || discountPercentage >= 100) {
            return false;
        }
        for (SaleItem item : items) {
            if (item == null || !item.isValid()) {
                return false;
            }
        }
        try {
            double expectedSubtotal = calculateSubtotal();
            double expectedDiscount = validation.roundMoney(expectedSubtotal * (discountPercentage / 100.0));
            double expectedTotal = validation.roundMoney(expectedSubtotal - expectedDiscount);
            return expectedTotal > 0 && validation.isMoney(subtotal)
                    && validation.isMoney(discountAmount) && validation.isMoney(totalAmount)
                    && Math.abs(subtotal - expectedSubtotal) < 0.001
                    && Math.abs(discountAmount - expectedDiscount) < 0.001
                    && Math.abs(totalAmount - expectedTotal) < 0.001;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private void requireDraft() {
        if (completed) {
            throw new IllegalStateException("Una venta completada no puede modificarse ni completarse de nuevo.");
        }
    }

    public int getSaleId() {
        return saleId;
    }

    public String getDate() {
        return date;
    }

    public String getSeller() {
        return seller;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<SaleItem> getItems() {
        return new ArrayList<SaleItem>(items);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isAgeVerified() {
        return ageVerified;
    }

    public boolean isCompleted() {
        return completed;
    }
}
