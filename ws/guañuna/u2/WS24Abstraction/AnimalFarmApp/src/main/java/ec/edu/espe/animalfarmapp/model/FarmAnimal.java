package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, ESPE
 */
public class FarmAnimal {
    private int id;
    private String breed;
    private Date bornOnDate;

    public FarmAnimal(int id, String breed, Date bornOnDate) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    public int getAgeInMonths() {
        Date today = new Date();
        int months = (today.getYear() - bornOnDate.getYear()) * 12;
        months += today.getMonth() - bornOnDate.getMonth();
        return months;
    }

    @Override
    public String toString() {
        return "id=" + id + ", breed=" + breed + 
               ", bornOnDate=" + bornOnDate.getDate() + "/" + 
               (bornOnDate.getMonth() + 1) + "/" + 
               (bornOnDate.getYear() + 1900) + ", ";
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    
    public Date getBornOnDate() { return bornOnDate; }
    public void setBornOnDate(Date bornOnDate) { this.bornOnDate = bornOnDate; }
}