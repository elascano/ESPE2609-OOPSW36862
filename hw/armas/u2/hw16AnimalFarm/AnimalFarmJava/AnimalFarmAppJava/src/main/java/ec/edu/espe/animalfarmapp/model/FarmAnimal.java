package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author LABS-ESPE
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

    @Override
    public String toString() {
        return  "id=" + id + ", breed=" + breed +
                ", bornOnDate=" + bornOnDate.getDate() + 
                "/" + bornOnDate.getMonth() + "/" + (bornOnDate.getYear() + 1900) + ", ";
    }
   
    
    private int getAgeInMonths(){
        return 0;
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
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * @return the bornonDate
     */
    public Date getBornOnDate() {
        return bornOnDate;
    }

    /**
     * @param bornOnDate the bornonDate to set
     */
    public void setBornOnDate(Date bornOnDate) {
        this.bornOnDate = bornOnDate;
    }

    
            
}
