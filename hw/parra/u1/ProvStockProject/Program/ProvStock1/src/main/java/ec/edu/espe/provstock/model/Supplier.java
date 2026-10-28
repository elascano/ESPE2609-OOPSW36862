package ec.edu.espe.provstock.model;

import ec.edu.espe.provstock.utils.IdentificationValidator;

/**
 * A company from which the business buys products (Table B3). (RF06)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class Supplier {

    private int id;
    private String ruc;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private boolean active;

    public Supplier(int id, String ruc, String name, String contactPerson, String phone,
            String email, String address) {
        this.id = id;
        this.ruc = ruc;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.active = true;
    }

    public boolean validate() {
        return name != null && !name.isBlank()
                && validateRuc()
                && IdentificationValidator.isValidPhone(phone)
                && IdentificationValidator.isValidEmail(email);
    }

    public boolean validateRuc() {
        return IdentificationValidator.isValidRuc(ruc);
    }

    /**
     * Changes the contact data. The key (ruc) never changes (BR-08).
     */
    public void update(String name, String phone, String email, String address) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (!IdentificationValidator.isValidPhone(phone)) {
            throw new IllegalArgumentException("El teléfono no es válido");
        }
        if (!IdentificationValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("El correo no es válido");
        }
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public String getIdentification() {
        return "RUC " + ruc;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", ruc=" + ruc + ", name=" + name + ", contactPerson=" + contactPerson
                + ", phone=" + phone + ", email=" + email + ", address=" + address + ", active=" + active + '}';
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @param contactPerson the contactPerson to set
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
}
