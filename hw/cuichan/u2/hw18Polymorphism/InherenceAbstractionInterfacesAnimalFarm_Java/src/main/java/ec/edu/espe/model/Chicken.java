
package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLaidEggsInAMonth;
    private boolean isMeat;

    public Chicken(boolean isMolting, int numberOfLaidEggsInAMonth, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
        this.isMeat = isMeat;
    }

    @Override
    public String toString() {
        return "Chicken{" +super.toString()
                + "isMolting=" + isMolting 
                + ", numberOfLaidEggsInAMonth=" + numberOfLaidEggsInAMonth 
                + ", isMeat=" + isMeat + '}';
    }
    
    
    public boolean isIsMolting() {
        return isMolting;
    }

    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    public int getNumberOfLaidEggsInAMonth() {
        return numberOfLaidEggsInAMonth;
    }

    public void setNumberOfLaidEggsInAMonth(int numberOfLaidEggsInAMonth) {
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
    }

    public boolean isIsMeat() {
        return isMeat;
    }

    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
    
    public void layInEgg(){
        
        System.out.println("Chicken is laying egg");
    }

    /**
     * feeding chickens with grains
     * @param food
     * @param amountOfFood
     */
    @Override
    public void feed(Food food, int amountOfFood) {
        System.out.println("Feeding a Chicken with "+amountOfFood +" pound of " +food  );
    }
    
}
