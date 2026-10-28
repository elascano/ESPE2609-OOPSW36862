package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;
import ec.edu.espe.systemliquorstore.exception.BusinessRuleException;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;

public class Sale {
    @SerializedName("idVenta")
    private int saleId;
    @SerializedName("fecha")
    private String date;
    @SerializedName("vendedor")
    private String seller;
    @SerializedName("tipoPago")
    private String paymentType;
    @SerializedName("idCliente")
    private int customerId;
    @SerializedName("detalles")
    private ArrayList<SaleItem> items;
    @SerializedName("subtotal")
    private double subtotal;
    @SerializedName("porcentajeDescuento")
    private double discountPercentage;
    @SerializedName("valorDescuento")
    private double discountAmount;
    @SerializedName("total")
    private double totalAmount;
    @SerializedName("edadVerificada")
    private boolean ageVerified;
    @SerializedName("completada")
    private boolean completed;

    public Sale(int saleId, String date, String seller, String paymentType) {
        if (saleId <= 0 || !Validation.isValidDate(date)
                || !Validation.isText(seller) || !Validation.isText(paymentType)) {
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
        if (!Validation.isFinite(existingPurchaseCost + item.calculatePurchaseCost())) {
            throw new IllegalArgumentException("El costo acumulado de la venta es demasiado grande.");
        }
        double newSubtotal = Validation.roundMoney(subtotal + item.calculateSubtotal());
        double newDiscountAmount = Validation.roundMoney(newSubtotal * (discountPercentage / 100.0));
        double newTotal = Validation.roundMoney(newSubtotal - newDiscountAmount);
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
        if (!Validation.isFinite(percentage) || percentage < 0 || percentage >= 100) {
            throw new IllegalArgumentException("El descuento debe ser un numero valido entre 0 y menos de 100.");
        }
        double newSubtotal = calculateSubtotal();
        double newDiscountAmount = Validation.roundMoney(newSubtotal * (percentage / 100.0));
        double newTotal = Validation.roundMoney(newSubtotal - newDiscountAmount);
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
        return Validation.roundMoney(amount);
    }

    public double calculateTotal() {
        double currentSubtotal = calculateSubtotal();
        double currentDiscount = Validation.roundMoney(currentSubtotal * (discountPercentage / 100.0));
        return Validation.roundMoney(currentSubtotal - currentDiscount);
    }

    public double calculateGrossProfit() {
        double purchaseCost = 0;
        for (SaleItem item : items) {
            purchaseCost += item.calculatePurchaseCost();
        }
        return Validation.roundMoney(calculateTotal() - purchaseCost);
    }

    public double calculateMargin() {
        double currentTotal = calculateTotal();
        if (currentTotal <= 0) {
            throw new BusinessRuleException("La venta debe tener un total mayor que cero para calcular el margen.");
        }
        return Validation.roundMoney((calculateGrossProfit() / currentTotal) * 100.0);
    }

     public int calculateAge(String birthDate) {
        String value = birthDate == null ? "" : birthDate.trim();
        if (!Validation.isValidDate(value)) {
            throw new IllegalArgumentException("La fecha de nacimiento no es válida.");
        }
        LocalDate birth = LocalDate.parse(value);
        LocalDate saleDate = LocalDate.parse(date);
        if (birth.isAfter(saleDate)) {
            throw new IllegalArgumentException("El nacimiento no puede ser posterior a la venta.");
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
            throw new BusinessRuleException("El cliente debe tener al menos 18 años para realizar la compra.");
        }
        ageVerified = true;
    }

    public void complete(ArrayList<Product> products, ArrayList<StockMovement> movements) {
        requireDraft();
        if (!isValid()) {
            throw new BusinessRuleException("Revise los productos, el total, el cliente y la verificacion de edad antes de completar la venta.");
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
                throw new BusinessRuleException("Stock insuficiente para " + product.getName()
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
            throw new BusinessRuleException("No se pueden generar mas identificadores de movimiento.");
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
        if (!Validation.isText(productCode)) {
            throw new IllegalArgumentException("El codigo del producto no puede estar vacio.");
        }
        long quantity = 0;
        for (SaleItem item : items) {
            if (item.getProductCode().equalsIgnoreCase(productCode.trim())) {
                quantity += item.getQuantity();
            }
        }
        if (quantity > Integer.MAX_VALUE) {
            throw new BusinessRuleException("La cantidad acumulada del producto es demasiado grande.");
        }
        return (int) quantity;
    }

    public boolean isValid() {
        if (saleId <= 0 || !Validation.isValidDate(date)
                || (!seller.equals("OWNER") && !seller.equals("EMPLOYEE"))
                || (!paymentType.equals("CASH") && !paymentType.equals("CREDIT"))
                || customerId < 0 || (paymentType.equals("CREDIT") && customerId == 0)
                || items.isEmpty() || !ageVerified
                || !Validation.isFinite(discountPercentage)
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
            double expectedDiscount = Validation.roundMoney(expectedSubtotal * (discountPercentage / 100.0));
            double expectedTotal = Validation.roundMoney(expectedSubtotal - expectedDiscount);
            return expectedTotal > 0 && Validation.isMoney(subtotal)
                    && Validation.isMoney(discountAmount) && Validation.isMoney(totalAmount)
                    && Math.abs(subtotal - expectedSubtotal) < 0.001
                    && Math.abs(discountAmount - expectedDiscount) < 0.001
                    && Math.abs(totalAmount - expectedTotal) < 0.001;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private void requireDraft() {
        if (completed) {
            throw new BusinessRuleException("Una venta completada no puede modificarse ni completarse de nuevo.");
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
