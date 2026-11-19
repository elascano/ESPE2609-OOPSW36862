package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.*;
import java.util.Calendar;
import java.util.Date;

public class AnimalFarmApp {

    public static void main(String[] args) {

        System.out.println("Hello. This is Cristopher Animal Farm\n");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.OCTOBER, 10);
        Date bornOnDate = calendar.getTime();

        Food corn = new Food("corn");
        Food grass = new Food("grass");
        Location northPasture = new Location(1, "North Pasture");

        // 1. CHICKEN
        Chicken chicken = new Chicken(true, 10, true, 2, "Paleesh", bornOnDate);
        System.out.println("Chicken --> " + chicken);
        chicken.feed(corn, 2);
        chicken.senToGraze(northPasture, new Date());
        chicken.layAnEgg();
        chicken.layAnEgg(5); 
        System.out.println();

        // 2. COW
        Cow cow = new Cow(true, 100.5F, 3, "holstein", bornOnDate);
        System.out.println("Cow --> " + cow);
        cow.feed(grass, 5);
        cow.senToGraze(northPasture, new Date());
        cow.milk();
        cow.milk(3.2F);
        cow.milk(100);
        cow.milk(2.5F, new Date());
        System.out.println("Total liters of milk per week: " + cow.getLitersOfMilkPerWeek());
        System.out.println();

        // 3. PIG
        Pig pig = new Pig(85.5F, true, 4, "Duroc", bornOnDate);
        System.out.println("Pig --> " + pig);
        pig.feed(corn, 10);
        pig.senToGraze(northPasture, new Date());
        pig.eat();
        pig.eat(5.5F);
        System.out.println();

        // 4. SHEEP
        Sheep sheep = new Sheep(bornOnDate, 5.5F, 5, "Merino", bornOnDate);
        System.out.println("Sheep --> " + sheep);
        sheep.feed(grass, 3);
        sheep.senToGraze(northPasture, new Date());
        sheep.shear();
        sheep.shear(2.5F);
        System.out.println();
    }
}