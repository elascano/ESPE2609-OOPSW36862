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
public class Defendant {

    private int defendantId;
    private String name;
    private String idNumber;
    private String contactInfo;
    private String address;

    public Defendant(int defendantId, String name, String idNumber, String contactInfo, String address) {
        this.defendantId = defendantId;
        this.name = name;
        this.idNumber = idNumber;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public boolean register() {
        if (Validator.isEmpty(name)) {
            return false;
        }
        if (!Validator.isEmpty(idNumber) && !Validator.isValidIdNumber(idNumber)) {
            return false;
        }
        return true;
    }

    public boolean update() {
        return register();
    }

    public String getDetails() {
        return "Defendant: " + name + " | ID: " + idNumber + " | Contact: " + contactInfo
                + " | Address: " + address;
    }

    @Override
    public String toString() {
        return "Defendant{" + "defendantId=" + defendantId + ", name=" + name + ", idNumber=" + idNumber + '}';
    }

    /**
     * @return the defendantId
     */
    public int getDefendantId() {
        return defendantId;
    }

    /**
     * @param defendantId the defendantId to set
     */
    public void setDefendantId(int defendantId) {
        this.defendantId = defendantId;
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
     * @return the contactInfo
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo the contactInfo to set
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
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
