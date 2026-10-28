/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

/**
 *
 * @author CodeLex, @ESPE
 */
public class Lawyer extends User {

    private String position;
    private String licenseNumber;

    public Lawyer(int userId, String name, String username, String password, Role role,
            String position, String licenseNumber) {
        super(userId, name, username, password, role);
        this.position = position;
        this.licenseNumber = licenseNumber;
    }

    public boolean isPartner() {
        return getRole() == Role.PARTNER;
    }

    public void restrictUserAccess(User user) {
        if (!isPartner()) {
            System.out.println("Only a partner can restrict other users");
            return;
        }
        if (user == null || user.getUsername().equals(getUsername())) {
            System.out.println("You can not restrict this user");
            return;
        }
        user.setActive(false);
        System.out.println("The access of " + user.getUsername() + " was restricted");
    }

    public String getDetails() {
        return "Lawyer: " + getName() + " | Position: " + position + " | License: " + licenseNumber
                + " | Role: " + getRole();
    }

    @Override
    public String toString() {
        return "Lawyer{" + "name=" + getName() + ", position=" + position + ", licenseNumber=" + licenseNumber + '}';
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the licenseNumber
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * @param licenseNumber the licenseNumber to set
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
