
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
    }
}
