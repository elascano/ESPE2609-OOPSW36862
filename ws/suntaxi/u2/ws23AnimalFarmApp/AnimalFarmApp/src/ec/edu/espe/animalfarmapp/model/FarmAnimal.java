package ec.edu.espe.animalfarmapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        return 0;
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

    @Override
    public String toString() {
        return "FarmAnimal{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", bornOnDate=" + bornOnDate.getDate() + "/" + (bornOnDate.getMonth() + 1) + "/" + (bornOnDate.getYear() + 1900) +
                '}';
    }
}
