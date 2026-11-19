package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.*;
import java.util.Date;

/**
 *
 * @author Enrique Ayala, LogicaLords,@ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {

        Food food = new Food("corn");
        Location location = new Location("graze");
        Chicken chicken = new Chicken(false, 10, 2, "palish", new Date(2023, 07, 20));
        Cow cow = new Cow(true, 23.5F, 3, "Holstein", new Date(2026, 01, 13), false);
        Pig pig = new Pig(true, 30.23F, 4, "Mangalitsa", new Date(2021, 12, 01), false);
        Sheep sheep = new Sheep(true, new Date(2024, 01, 15), 2, "Manchega", new Date(2019,04, 27), true);

        System.out.println("Enrique's Farm Animal");
        System.out.println("============");
        System.out.println("Chicken: ");
        System.out.println(chicken);
        chicken.layAnEgg();
        chicken.feed(food, 3);
        chicken.sellAnimal(12.50F);

        food = new Food("hay");
        System.out.println("============");
        System.out.println("Cow:");
        System.out.println(cow);
        cow.feed(food, 2);
        cow.sendToGraze(location, new Date());
        cow.milk();
        cow.milk(23.4F);
        cow.milk(14);
        cow.getLitersOfMilkPerWeek();

        food = new Food("corn");
        System.out.println("============");
        System.out.println("Pig:");
        System.out.println(pig);
        pig.feed(food, 12);
        pig.showPigMeatCuts();
        pig.getWeightGainPerWeek(32);
        pig.sellAnimal(130.4F);

        food = new Food("grass");
        System.out.println("============");
        System.out.println("Sheep:");
        System.out.println(sheep);
        sheep.feed(food, 3);
        sheep.getLastSheering();
        sheep.sendToGraze(location, new Date());
        sheep.shearSheep();

    }
}
