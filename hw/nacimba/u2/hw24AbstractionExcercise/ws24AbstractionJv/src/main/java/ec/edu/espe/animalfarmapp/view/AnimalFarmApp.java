package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.util.Date;

/**
 *
 * @author Mateo, Group5, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Hello This is Mateo's Animal Farm!");
        System.out.println("==================================");
        
        FarmAnimal farmAnimal;

farmAnimal = new Chicken(true, 10, 2, "Paleesh", new Date(), true);
Chicken chicken = new Chicken(true, 10, 2, "Paleesh", new Date(), true);
System.out.println("chicken --> " + farmAnimal);
System.out.println("chicken is a --> " + farmAnimal.getClass().getSimpleName());

Cow cow = new Cow(true, 100.5F, 3, "holstein", new Date(), false);
System.out.println("cow --> " + cow);

Pig pig = new Pig(true, 30.23F, 4, "Mangalitsa", new Date(), true);
System.out.println("pig --> " + pig);

Sheep sheep = new Sheep(true, new Date(), 5, "Merino", new Date(), true);
System.out.println("sheep --> " + sheep);
chicken.layAnEgg();
cow.milk();
        
        
        
        
    }
}
