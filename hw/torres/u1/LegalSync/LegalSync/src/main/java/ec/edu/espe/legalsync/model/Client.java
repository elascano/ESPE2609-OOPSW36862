/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.Validator;

/**
 *
 * @author CodeLex, @ESPE
 */
public class Client {

    private int clientId;
    private String name;
    private String idNumber;
    private String phone;
    private String email;
    private String address;

    public Client(int clientId, String name, String idNumber, String phone, String email, String address) {
        this.clientId = clientId;
        this.name = name;
        this.idNumber = idNumber;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public boolean register() {
        if (Validator.isEmpty(name)) {
            return false;
        }
        if (!Validator.isValidIdNumber(idNumber)) {
            return false;
        }
        if (!Validator.isEmpty(email) && !Validator.isValidEmail(email)) {
            return false;
        }
        return true;
    }

    public boolean update() {
        return register();
    }

    public String getDetails() {
        return "Client: " + name + " | ID: " + idNumber + " | Phone: " + phone
                + " | Email: " + email + " | Address: " + address;
    }

    @Override
    public String toString() {
        return "Client{" + "clientId=" + clientId + ", name=" + name + ", idNumber=" + idNumber + '}';
    }

    /**
     * @return the clientId
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
