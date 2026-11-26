package ec.edu.espe.contactsbook.model;

import java.util.Date;
import java.util.ArrayList;

public class Contact {
    private int id;
    private String fullName;
    private String email;
    private String cellPhoneNumber;
    private String instagram;
    private String facebook;
    private boolean isKnow;
    private String type;
    private char gender;
    private ArrayList<String> hobbies;
    private Date birthDate;
    private String comments;

    public Contact(int id, String fullName, String email, String cellPhoneNumber,
                   String instagram, String facebook, boolean isKnow, String type,
                   char gender, ArrayList<String> hobbies, Date birthDate, String comments) {
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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCellPhoneNumber() { return cellPhoneNumber; }
    public void setCellPhoneNumber(String cellPhoneNumber) { this.cellPhoneNumber = cellPhoneNumber; }

    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }

    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }

    public boolean isIsKnow() { return isKnow; }
    public void setIsKnow(boolean isKnow) { this.isKnow = isKnow; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    public ArrayList<String> getHobbies() { return hobbies; }
    public void setHobbies(ArrayList<String> hobbies) { this.hobbies = hobbies; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    @Override
    public String toString() {
        return "id=" + id + "\n fullName=" + fullName +
                "\n email=" + email + "\n cellPhoneNumber=" + cellPhoneNumber +
                "\n instagram=" + instagram + "\n facebook=" + facebook +
                "\n isKnow=" + isKnow + "\n type=" + type +
                "\n gender=" + gender + "\n hobbies=" + hobbies +
                "\n birthDate=" + birthDate + "\n comments=" + comments;
    }
}