package ec.edu.espe.animalfarm.view;

/**
 *
 * @author Enrique Ayala, LogicalLords, ESPE
 */

import ec.edu.espe.animalfarm.model.Chicken;
import ec.edu.espe.animalfarm.model.Cow;
import ec.edu.espe.animalfarm.model.FarmAnimal;
import java.util.Date;

public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Enrique's Anima Farm");
        System.out.println("========================");
        FarmAnimal farmAnimal;
        Date bornOnDate = new Date (2020, 10, 10);
        farmAnimal = new FarmAnimal(1, "holstein", new Date());

        System.out.println("my farm animal --->" + farmAnimal);
        System.out.println("FarmAnimal --> is a " + farmAnimal.getClass().getSimpleName());

        System.out.println("FarmAnimal --> " + farmAnimal.getClass());

        farmAnimal = new Chicken(true, 10, true, 2, "Paleesh", new Date());
        System.out.println("FarmAnimal --> is a " + farmAnimal.getClass().getSimpleName());

        Chicken chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());
        System.out.println("chicken --->" + chicken);

        FarmAnimal cow = new Cow(true, 100.5F, 3, "holstein", new Date(222,20,20));

        cow = new Chicken (true, 4, true, 0, "paleesh", bornOnDate);
        System.out.println("cow --> " + cow);

        Cow cow2 = new Cow(true, 0, 0, "palich", bornOnDate);
        cow2.milk();
        chicken.layAnEgg();
    }
}