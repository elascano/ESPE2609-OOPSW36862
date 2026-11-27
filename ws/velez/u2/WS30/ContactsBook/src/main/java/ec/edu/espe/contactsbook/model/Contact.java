package ec.edu.espe.contactsbook.model;

import java.text.SimpleDateFormat;
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
    private String type; // Family, HighSchool, Job...
    private char gender;
    private ArrayList<String> hobbies;
    private Date birtDate;
    private String comments;

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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = (birtDate != null) ? sdf.format(birtDate) : "";
        return "Contact{" + "\nid=" + id + "\nfullName=" + fullName
                + "\nemail=" + email + "\ncellPhoneNumber=" + cellPhoneNumber
                + "\ninstagram=" + instagram + "\nFacebook=" + Facebook
                + "\nisKNown=" + isKNown + "\ntype=" + type
                + "\ngender=" + gender + "\nhobbies=" + hobbies
                + "\nbirtDate=" + dateStr + "\ncomments=" + comments + "\n}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String Facebook) {
        this.Facebook = Facebook;
    }

    public boolean isIsKNown() {
        return isKNown;
    }

    public void setIsKNown(boolean isKNown) {
        this.isKNown = isKNown;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Date getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
