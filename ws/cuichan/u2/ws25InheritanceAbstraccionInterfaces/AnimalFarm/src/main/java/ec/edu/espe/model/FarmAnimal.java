
package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
public abstract class  FarmAnimal {
    private int id;
    private String breed;
    private Date bornOnDate;

    
    
    public FarmAnimal(int id, String breed, Date bornOnDate) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }
    /**
     * Feed the animals with an amount of food
     * @param food 
     * @param amountOfFood 
     */
    public abstract void feed(Food food, int amountOfFood);
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Date getBornOnDate() {
        return bornOnDate;
    }

    public void setBornOnDate(Date bornOnDate) {
        this.bornOnDate = bornOnDate;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", breed=" + breed + 
                ", bornOnDate=" + bornOnDate.getDate() +"/"+(bornOnDate.getMonth()+1)
                +"/" + (bornOnDate.getYear()+1900) 
                + ",";
    }
    
    public int getAgeInMonths(){
        //Complete
        return 0;
    }
    
}
