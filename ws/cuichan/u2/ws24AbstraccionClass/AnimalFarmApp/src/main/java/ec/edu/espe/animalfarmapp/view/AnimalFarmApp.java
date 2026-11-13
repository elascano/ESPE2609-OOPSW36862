package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.util.Date;

public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Leslye's Farm Animal");
        System.out.println("====================");
        Date bornOnDate = new Date();
        String breed = "Palish";
        FarmAnimal farmAnimal;
        farmAnimal = new Chicken(true, 0, true, 0, breed, bornOnDate);
        System.out.println("farm animal -->" + farmAnimal);
        System.out.println("farm Animal is a -->" + farmAnimal.getClass());
        
        farmAnimal = new Chicken(false, 20, false, 1, "layer", new Date());
        System.out.println("farmAnimal is a -->" + farmAnimal.getClass().getSimpleName());
        System.out.println("my Farm Animal -->" + farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());
        System.out.println("Chicken -->" + chicken); 
        System.out.println("chicken is a -->" + chicken.getClass());
        System.out.println("chicken is a -->" + chicken.getClass().getSimpleName());
        
        Cow cow = new Cow(true, 100.5F, 3, "holstein", new Date());
        System.out.println("Cow -->" + cow);
        System.out.println("Cow is a -->" + cow.getClass());
        System.out.println("Cow is a -->" + cow.getClass().getSimpleName());
        
        Pig pig = new Pig(4, "Landrace", new Date(2011, 5, 20), 85.0f, true);
        System.out.println("Pig -->" + pig);
        System.out.println("Pig is a -->" + pig.getClass());
        System.out.println("Pig is a -->" + pig.getClass().getSimpleName());

        Sheep sheep = new Sheep(new Date(124, 8, 1), true, true, 5, "Merino", new Date(1989, 11, 5));
        System.out.println("Sheep -->" + sheep);
        System.out.println("Sheep is a -->" + sheep.getClass());
        System.out.println("Sheep is a -->" + sheep.getClass().getSimpleName());

        chicken.layAnEgg();
        cow.milk();
    }
}
