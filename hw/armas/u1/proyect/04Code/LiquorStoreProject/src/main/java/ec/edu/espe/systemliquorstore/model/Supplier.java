package ec.edu.espe.systemliquorstore.model;

import com.google.gson.annotations.SerializedName;
import ec.edu.espe.systemliquorstore.util.Validation;

public class Supplier {
    @SerializedName("idProveedor")
    private int supplierId;
    @SerializedName("nombre")
    private String name;
    @SerializedName("contacto")
    private String contact;

    public Supplier(int supplierId, String name, String contact) {
        this.supplierId = supplierId;
        this.name = name == null ? "" : name.trim();
        this.contact = contact == null ? "" : contact.trim();
        if (this.supplierId <= 0 || !Validation.isText(this.name)
                || !Validation.isText(this.contact)) {
            throw new IllegalArgumentException("Revise el identificador, nombre y contacto del proveedor.");
        }
    }

    public PurchaseOrder createPurchaseOrder(int id, String date) {
        return new PurchaseOrder(id, supplierId, date);
    }

    public boolean isValid() {
        return supplierId > 0 && Validation.isText(name) && Validation.isText(contact);
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Validation.isText(name)) {
            throw new IllegalArgumentException("El nombre del proveedor no puede estar vacio.");
        }
        this.name = name.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (!Validation.isText(contact)) {
            throw new IllegalArgumentException("El contacto del proveedor no puede estar vacio.");
        }
        this.contact = contact.trim();
    }
}
