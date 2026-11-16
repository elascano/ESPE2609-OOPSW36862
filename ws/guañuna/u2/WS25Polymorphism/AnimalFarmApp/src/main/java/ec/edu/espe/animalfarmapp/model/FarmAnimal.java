package ec.edu.espe.animalfarmapp.model;

import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic @ESPE
 */
public abstract class FarmAnimal {
    private int id;
    private String breed;
    private Date bornOnDate;
    
    public abstract void feed(Food food, int amount);
    
    public FarmAnimal(int id, String breed, Date bornOnDate) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    @Override
    public String toString() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(bornOnDate);
        
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        
        return "id = " + id + ", breed = " + breed +
               ", bornOnDate = " + day + "/" + month + "/" + year + ", ";
    }
    
    public int getAgeInMonths(){
        if (bornOnDate == null) return 0;
        
        Calendar birth = Calendar.getInstance();
        birth.setTime(bornOnDate);
        Calendar now = Calendar.getInstance();
        
        int years = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        int months = now.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
        
        return (years * 12) + months;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public Date getBornOnDate() { return bornOnDate; }
    public void setBornOnDate(Date bornOnDate) { this.bornOnDate = bornOnDate; }
}