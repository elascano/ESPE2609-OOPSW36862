package ec.edu.espe.animalfarmapp.model;

import java.util.Date;
/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class FarmAnimal {
    private int id;
    private String bred;
    private Date bornOnDate;

    public FarmAnimal(int id, String bred, Date bornOnDate) {
        this.id = id;
        this.bred = bred;
        this.bornOnDate = bornOnDate;
    }
    
    
    
    public int getAgeInMonths(){
        return 0;
    }

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + id + ", bred=" + bred + ", bornOnDate=" + bornOnDate + '}';
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
     * @return the bred
     */
    public String getBred() {
        return bred;
    }

    /**
     * @param bred the bred to set
     */
    public void setBred(String bred) {
        this.bred = bred;
    }

    /**
     * @return the bornOnDate
     */
    public Date getBornOnDate() {
        return bornOnDate;
    }

    /**
     * @param bornOnDate the bornOnDate to set
     */
    public void setBornOnDate(Date bornOnDate) {
        this.bornOnDate = bornOnDate;
    }
    
}

