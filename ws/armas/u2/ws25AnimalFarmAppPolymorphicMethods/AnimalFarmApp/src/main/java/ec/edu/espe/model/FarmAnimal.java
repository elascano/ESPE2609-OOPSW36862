package ec.edu.espe.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

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
        return "id = " + id + ", breed = " + breed +
                ", bornOnDate = " + (bornOnDate.getDate() + 1) + "/" + bornOnDate.getMonth() +
                "/" + (bornOnDate.getYear() + 1900) + ", ";
    }

    public int getAgeInMonths() {
        if (this.bornOnDate == null) {
            return 0;
        }

        LocalDate birthDate = this.bornOnDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = LocalDate.now();

        if (birthDate.isAfter(currentDate)) {
            return 0;
        }

        return (int) Period.between(birthDate, currentDate).toTotalMonths();
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
}