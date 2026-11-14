
package ec.edu.espe.view;

import ec.edu.espe.model.Chicken;
import ec.edu.espe.model.Cow;
import ec.edu.espe.model.FarmAnimal;
import ec.edu.espe.model.Food;
import java.util.Date;

/**
 *
 * @author David Clavón, Infinite loops, @ESPE
 */
public class AnimalFarm {

    public static void main(String[] args) {
        
        Food food;
        Date bornOnDate = new Date(2022,9,12);
        System.out.println("Hello This is David's Animal Farms!");
        FarmAnimal farmAnimal;
        farmAnimal = new Chicken(true, 10, true, 1, "Paleesh", new Date());
        System.out.println("FarmAminal is a ---> " + farmAnimal.getClass());
        System.out.println("farm animal ---> " + farmAnimal );
        Cow cow;
        cow = new Cow(true, 150f, 7, "holsteing", bornOnDate);
        System.out.println("cow is " + cow);
        System.out.println("cow is a  " + cow.getClass().getSimpleName());
        Chicken chicken = new Chicken(true, 20, false, 2, "Paleesh", new Date());
        
        System.out.println("==============================================================");
        food = new Food("corn");
        chicken.feed(food, 2);
        
        chicken.layInEgg();
        food = new Food("grass");
        cow.feed(food, 1);
        Date hourMilking = new Date();
        cow.milk(15, hourMilking);
        
        cow.milk(3.5F);
        chicken.layInEgg();
        cow.milk(20.5F);
        
        chicken.feed(food, 1);
        cow.milk();
        cow.milk(1.5F);
        cow.milk(100);
        
    }
}
