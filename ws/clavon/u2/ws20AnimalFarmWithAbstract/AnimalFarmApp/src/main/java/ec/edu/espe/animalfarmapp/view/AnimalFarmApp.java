
package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import java.util.Date;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("David's Farm Animal");
        System.out.println("====================");
        Date bornOnDate = new Date();
        String breed = "Palish";
        
        FarmAnimal farmAnimal = new FarmAnimal(1, breed, bornOnDate);
        
        
        
        System.out.println("FarmAnimalis -->" + farmAnimal.getClass());
        farmAnimal = new Chicken(true, 0, true, 0, breed, bornOnDate);
        System.out.println("farm animal --> " + farmAnimal);
        System.out.println("farm Animal is a --> " + farmAnimal.getClass());
        
        farmAnimal = new Chicken(false, 20, false, 1, "layer", new Date());
        System.out.println("farmAnimal is a -->" + farmAnimal.getClass().getSimpleName());
        
        System.out.println("my Farm Animal -->" + farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, true, 2, "Palish", new Date());
        System.out.println("Chicken -->" + chicken); 
        System.out.println("chicken is a -->" + chicken.getClass());
        
        Cow cow = new Cow(true, 100.5F, 3, "holstein", new Date());
        System.out.println("Cow -->" + cow);
        System.out.println("Cow is a -->" + cow.getClass());
        cow.milk();
        chicken.layAnEgg();
        
    }
}
