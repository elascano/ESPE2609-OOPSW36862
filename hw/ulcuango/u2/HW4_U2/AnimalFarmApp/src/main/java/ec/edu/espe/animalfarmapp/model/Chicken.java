package ec.edu.espe.animalfarmapp.model;

import java.util.Date;
/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLaidEggPerWeek;
    private boolean isMeat;

    public Chicken(boolean isMolting, int numberOfLaidEggPerWeek, boolean isMeat, int id, String bred, Date bornOnDate) {
        super(id, bred, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggPerWeek = numberOfLaidEggPerWeek;
        this.isMeat = isMeat;
    }
    public void assignCoop(){
    
    }
    public void layAnEgg(){
    System.out.println("The chicken is layin an Egg");
    }
    
    @Override
    public String toString() {
        return "Chicken{" +super.toString() +  "isMolting=" + isMolting + ", numberOfLaidEggPerWeek=" + numberOfLaidEggPerWeek + ", isMeat=" + isMeat + '}';
    }

    /**
     * @return the isMolting
     */
    public boolean isIsMolting() {
        return isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    /**
     * @return the numberOfLaidEggPerWeek
     */
    public int getNumberOfLaidEggPerWeek() {
        return numberOfLaidEggPerWeek;
    }

    /**
     * @param numberOfLaidEggPerWeek the numberOfLaidEggPerWeek to set
     */
    public void setNumberOfLaidEggPerWeek(int numberOfLaidEggPerWeek) {
        this.numberOfLaidEggPerWeek = numberOfLaidEggPerWeek;
    }

    /**
     * @return the isMeat
     */
    public boolean isIsMeat() {
        return isMeat;
    }

    /**
     * @param isMeat the isMeat to set
     */
    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
   
}
