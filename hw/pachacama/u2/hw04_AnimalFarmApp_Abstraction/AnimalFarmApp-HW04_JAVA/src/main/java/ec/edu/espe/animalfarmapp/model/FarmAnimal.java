package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
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

    public boolean isIsMeat() {
        return isMeat;
    }

    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
}
