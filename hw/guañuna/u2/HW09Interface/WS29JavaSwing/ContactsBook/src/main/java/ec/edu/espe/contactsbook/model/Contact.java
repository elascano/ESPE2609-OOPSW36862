/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.contactsbook.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna Runtime Dynamics @ESPE
 */
public class Contact {
    private int id;
    private String fullName;
    private String email;
    private String cellPhoneNumber;
    private String instagram;
    private String facebook;
    private boolean isKnow;
    private String type; //family/Highschool/Job/Team mate/Gym/Friend
    private char gender; //male/female
    private String hobbies[];
    private Date birthDate;
    private String comments;

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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
     * @return the cellPhoneNumber
     */
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    /**
     * @param cellPhoneNumber the cellPhoneNumber to set
     */
    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    /**
     * @return the instagram
     */
    public String getInstagram() {
        return instagram;
    }

    /**
     * @param instagram the instagram to set
     */
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the isKnow
     */
    public boolean isIsKnow() {
        return isKnow;
    }

    /**
     * @param isKnow the isKnow to set
     */
    public void setIsKnow(boolean isKnow) {
        this.isKnow = isKnow;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * @return the hobbies
     */
    public String[] getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    public Contact(int id, String fullName, String email, String cellPhoneNumber, String instagram, String facebook, boolean isKnow, String type, char gender, String[] hobbies, Date birthDate, String comments) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
        this.instagram = instagram;
        this.facebook = facebook;
        this.isKnow = isKnow;
        this.type = type;
        this.gender = gender;
        this.hobbies = hobbies;
        this.birthDate = birthDate;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "id=" + id + "\n fullName=" + fullName + 
                "\n email=" + email + "\n cellPhoneNumber=" + cellPhoneNumber + 
                "\n instagram=" + instagram + "\n facebook=" + facebook + 
                "\n isKnow=" + isKnow + "\n type=" + type + 
                "\n gender=" + gender + "\n hobbies=" + hobbies + 
                "\n birthDate=" + birthDate + "\n comments=" + comments ;
    }
    
    
    
}
