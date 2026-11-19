package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.*;
import java.util.Date;

/**
 *
 * @author Dennis Criollo,Byte & Fabric Devs,@ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        FarmAnimal farmAnimal;
        Food food = new Food("corn");
        Location location = new Location("pasture 1");
        Date date = new Date();

        System.out.println("Dennis's Farm Animal");
        System.out.println("=======================");

        farmAnimal = new Chicken(false, 10, 2, "palish", new Date(2022, 10, 10));
        System.out.println("chicken --->" + farmAnimal);
        System.out.println("chicken is a --->" + farmAnimal.getClass());
        System.out.println("chicken is a --->" + farmAnimal.getClass().getSimpleName());

        Chicken chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 10, 10));
        System.out.println("chicken --->" + chicken);
        System.out.println("chicken is a --->" + chicken.getClass().getSimpleName());

        Cow cow = new Cow(true, 23.5F, 3, "Holstein", new Date(2024, 12, 23), false);
        System.out.println("cow --->" + cow);
        System.out.println("cow is a --->" + cow.getClass().getSimpleName());

        chicken.layAnEgg();
        
        cow.feed(food, 1);
        cow.sendToGraze(location, date);
        
        cow.milk();
        cow.milk(3.5F);
        cow.milk(30);

        chicken.feed(food, 2);
        
 
        
    }
}
