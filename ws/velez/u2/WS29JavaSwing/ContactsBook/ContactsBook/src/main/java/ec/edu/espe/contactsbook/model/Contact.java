package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class Contact {
    private int id;
    private String fullName;
    private String email;
    private String cellPhoneNumber;
    private String instagram;
    private String Facebook;
    private boolean isKNown;
    private String type; // Family, HisgScool,Job...
    private char gender;
    private ArrayList<String> hobbies;
    private Date birtDate;
    private String comments;

    public int getId() {
        return id;
    }

@Override
    public String toString() {
        return "Contact{" + "\nid=" + id + "\nfullName=" + fullName
                + "\nemail=" + email + "\ncellPhoneNumber=" + cellPhoneNumber
                + "\ninstagram=" + instagram + "\nFacebook=" + Facebook
                + "\nisKNown=" + isKNown + "\ntype=" + type
                + "\ngender=" + gender + "\nhobbies=" + hobbies
                + "\nbirtDate=" + birtDate + "\ncomments=" + comments + "\n}";
    }

    public Contact(int ide, String fullName, String email, String cellPhoneNumber, String instagram, String Facebook, boolean isKNown, String type, char gender, ArrayList<String> hobbies, Date birtDate, String comments) {
        this.id = ide;
        this.fullName = fullName;
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
        this.instagram = instagram;
        this.Facebook = Facebook;
        this.isKNown = isKNown;
        this.type = type;
        this.gender = gender;
        this.hobbies = hobbies;
        this.birtDate = birtDate;
        this.comments = comments;
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
     * @return the Facebook
     */
    public String getFacebook() {
        return Facebook;
    }

    /**
     * @param Facebook the Facebook to set
     */
    public void setFacebook(String Facebook) {
        this.Facebook = Facebook;
    }

    /**
     * @return the isKNown
     */
    public boolean isIsKNown() {
        return isKNown;
    }

    /**
     * @param isKNown the isKNown to set
     */
    public void setIsKNown(boolean isKNown) {
        this.isKNown = isKNown;
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
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * @return the birtDate
     */
    public Date getBirtDate() {
        return birtDate;
    }

    /**
     * @param birtDate the birtDate to set
     */
    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
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
}
