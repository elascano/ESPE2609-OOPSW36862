package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Marcos Mamarandi,ByteWorks,@ESPE
 */
public abstract class FarmAnimal {

    private int id;
    private String breed;
    private Date bornOnDate;
    private boolean isMeat;

    public FarmAnimal(int id, String breed, Date bornOnDate, boolean isMeat) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
        this.isMeat = isMeat;
    }

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + getId() + ", breed=" + getBreed() + ", is meat=" + isIsMeat()
                + ", bornOnDate=" + getBornOnDate().getDate() + " / "
                + (getBornOnDate().getMonth() + 1) + " / " + (getBornOnDate().getYear() + 1900)
                + "}, ";
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
