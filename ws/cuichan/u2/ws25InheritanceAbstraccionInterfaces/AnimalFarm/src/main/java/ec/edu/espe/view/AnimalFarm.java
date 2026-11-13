
package ec.edu.espe.view;

import ec.edu.espe.model.Chicken;
import ec.edu.espe.model.Cow;
import ec.edu.espe.model.FarmAnimal;
import ec.edu.espe.model.Food;
import ec.edu.espe.model.Location;
import java.util.Date;

/**
 *
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
public class AnimalFarm {

    public static void main(String[] args) {
        
        Food food;
        Date bornOnDate = new Date(2022,9,12);
        System.out.println("Hello This is Leslye's Animal Farms!");
        FarmAnimal farmAnimal;
        farmAnimal = new Chicken(true, 10, true, 1, "Palish", new Date());
        System.out.println("FarmAminal is a ---> " + farmAnimal.getClass());
        System.out.println("farm animal ---> " + farmAnimal );
        Cow cow;
        cow = new Cow(true, 150f, 7, "holsteing", bornOnDate);
        System.out.println("cow is " + cow);
        System.out.println("cow is a  " + cow.getClass().getSimpleName());
        Chicken chicken = new Chicken(true, 20, false, 2, "Palish", new Date());
        System.out.println("My Chicken " + chicken);
        Location location = new Location(2, "PASTURE 1");
        FarmAnimal farmAnimalForFeeding; 
        farmAnimalForFeeding = new Chicken(true, 10, true, 5, "Palish", new Date());
        System.out.println("cow is a " + farmAnimalForFeeding.getClass().getSimpleName());
        farmAnimalForFeeding= new Cow(true, 100.5F, 3, "holstein", new Date());
        System.out.println("cow is a " + farmAnimalForFeeding.getClass().getSimpleName());
        farmAnimalForFeeding = new Chicken(true, 4, true, 4, "PALEESH", new Date());
        System.out.println("cow is a " + farmAnimalForFeeding.getClass().getSimpleName());
        cow.milk(3.4f);
        
        food = new Food("hay");
         cow.milk(3.5F);
        chicken.layInEgg();
         cow.milk(20.5F);
         cow.milk();
         
         chicken.feed(food, 2);
         cow.sendtoGraze(location);
         

        
    }
}
