package ec.edu.espe.provstock.model;

import ec.edu.espe.provstock.utils.IdentificationValidator;
import java.util.Arrays;

/**
 * A person or organization that buys from the company (Table B1). (RF02)
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class Customer {

    //N2: allowed values of identificationType
    public static final String CEDULA = "CEDULA";
    public static final String RUC = "RUC";
    public static final String PASSPORT = "PASSPORT";
    public static final String[] IDENTIFICATION_TYPES = {CEDULA, RUC, PASSPORT};

    //FR-02.6: the "Consumidor Final" uses this identification number
    public static final String FINAL_CONSUMER_ID = "9999999999999";

    private int id;
    private String identificationType;
    private String identificationNumber;
    private String name;
    private String phone;
    private String email;
    private String address;
    private boolean active;

    public Customer(int id, String identificationType, String identificationNumber, String name,
            String phone, String email, String address) {
        this.id = id;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.active = true;
    }

    public boolean validate() {
        return name != null && !name.isBlank()
                && validateIdentification()
                && IdentificationValidator.isValidPhone(phone)
                && IdentificationValidator.isValidEmail(email);
    }

    public boolean validateIdentification() {
        if (isFinalConsumer()) {
            return true;
        }
        if (identificationType == null || !Arrays.asList(IDENTIFICATION_TYPES).contains(identificationType)) {
            return false;
        }
        switch (identificationType) {
            case CEDULA:
                return IdentificationValidator.isValidCedula(identificationNumber);
            case RUC:
                return IdentificationValidator.isValidRuc(identificationNumber);
            default:
                return IdentificationValidator.isValidPassport(identificationNumber);
        }
    }

    public boolean isFinalConsumer() {
        return FINAL_CONSUMER_ID.equals(identificationNumber);
    }

    /**
     * Changes the contact data. The key (identificationNumber) never changes (BR-08).
     */
    public void update(String name, String phone, String email, String address) {
        if (isFinalConsumer()) {
            throw new IllegalStateException("El Consumidor Final no puede modificarse");
        }
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
        if (isFinalConsumer()) {
            throw new IllegalStateException("El Consumidor Final no puede desactivarse");
        }
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public String getIdentification() {
        return identificationType + " " + identificationNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", identification=" + getIdentification() + ", name=" + name
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
     * @return the identificationType
     */
    public String getIdentificationType() {
        return identificationType;
    }

    /**
     * @return the identificationNumber
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
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
