
package ec.edu.espe.view;

import ec.edu.espe.model.*;
import java.util.Calendar;
import java.util.Date;

/**
 *
* @author Demian Armas, Logical Lords, @ESPE
*/
public class AnimalFarmApp {

    public static void main(String[] args) {

        System.out.println("Hello. This is Demian's Animal Farm - Abstraction Exercise");

        FarmAnimal farmAnimal;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.NOVEMBER, 12);
        Date bornOnDate = calendar.getTime();

        Food food = new Food("grass");

        farmAnimal = new Chicken(true, 4, true, 3, "Poultry", bornOnDate);

        System.out.println("farmAnimal is a --> " + farmAnimal.getClass());
        System.out.println("My farm animal --> " + farmAnimal);

        Chicken chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());

        System.out.println("Chicken --> " + chicken);
        chicken.layAnEgg();
        System.out.println(chicken.layEggs(3));
        System.out.println("Total eggs laid this month: " + chicken.getNumberOfLaidEggsInAMonth());

        System.out.println("farmAnimal is a --> " + chicken.getClass());
        System.out.println("farmAnimal is a --> " + chicken.getClass().getSimpleName());

        Cow cow = new Cow(true, 100.5F, 3, "holstein", new Date());
        food = new Food("grass");
        cow.feed(food, 1);

        System.out.println("Cow --> " + cow);
        System.out.println("Cow is --> " + cow.getClass());
        System.out.println("Cow is --> " + cow.getClass().getSimpleName());

        cow.milk();

        FarmAnimal chicken1 = new Chicken(true, 4, true, 0, "palish", bornOnDate);
        System.out.println("Chicken --> " + chicken1);

        Cow cow1 = new Cow(true, 0.0F, 0, "Molestein", bornOnDate);

        cow.feed(food, 5);
        cow.milk(6);
        System.out.println("Cow is --> " + cow1.getClass());

        chicken.feed(food, 1);

        food = new Food("grass");

        cow1.feed(food, 1);

        cow1.milk();
        cow1.milk(3.5F);
        cow1.milk(100);
        System.out.println("Total liters of milk per week: "
                + cow1.getLitersOfMilkPerWeek());

        Sheep sheep = new Sheep(bornOnDate, true, 2.5, 4, "Merino", bornOnDate);
        sheep.feed(new Food("hay"), 3);
        System.out.println(sheep.shear());
        System.out.println(sheep.shear(1.5));
        System.out.println(sheep.shear(2));
        System.out.println("Total wool collected this month: " + sheep.getKgOfWoolForMonth() + " kg");

        Pig pig = new Pig(80.5, true, 5, "Yorkshire", bornOnDate);
        pig.feed(new Food("pellets"), 4);
        System.out.println(pig.recordCut("pork loin", 2.5));
        System.out.println(pig.recordCut("ribs", 1.5));
        System.out.println("Total pork cuts recorded: " + pig.getTotalCutWeight() + " kg");
    }
}
