package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Cristopher's Animal Farm");
        System.out.println("========================");
        
        Date bornOnDate = new Date(120, 10, 10);
        FarmAnimal farmAnimal;

        farmAnimal = new FarmAnimal(1, "Holstein", bornOnDate);
        System.out.println("FarmAnimal --> is a " + farmAnimal.getClass().getSimpleName());
        System.out.println("FarmAnimal --> " + farmAnimal.getClass());
        System.out.println("farmAnimal ---> " + farmAnimal);
        System.out.println();

        farmAnimal = new Chicken(true, 10, false, 2, "Paleesh", bornOnDate);
        System.out.println("FarmAnimal --> is a " + farmAnimal.getClass().getSimpleName());
        System.out.println("FarmAnimal --> " + farmAnimal.getClass());
        System.out.println("chicken ---> " + farmAnimal);
        ((Chicken) farmAnimal).layAnEgg();
        System.out.println();

        farmAnimal = new Cow(true, 100.5F, 3, "Holstein", bornOnDate);
        System.out.println("FarmAnimal --> is a " + farmAnimal.getClass().getSimpleName());
        System.out.println("FarmAnimal --> " + farmAnimal.getClass());
        System.out.println("cow ---> " + farmAnimal);
        ((Cow) farmAnimal).milk();
        System.out.println();

        farmAnimal = new Pig(85.5F, true, 4, "Duroc", bornOnDate);
        System.out.println("FarmAnimal --> is a " + farmAnimal.getClass().getSimpleName());
        System.out.println("FarmAnimal --> " + farmAnimal.getClass());
        System.out.println("pig ---> " + farmAnimal);
        ((Pig) farmAnimal).eat();
        System.out.println();

        farmAnimal = new Sheep(bornOnDate, 5.5F, 5, "Merino", bornOnDate);
        System.out.println("FarmAnimal --> is a " + farmAnimal.getClass().getSimpleName());
        System.out.println("FarmAnimal --> " + farmAnimal.getClass());
        System.out.println("sheep ---> " + farmAnimal);
        ((Sheep) farmAnimal).shear();
    }
}