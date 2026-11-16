package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Cristopher's Farm Animals");
        System.out.println("=================================");
        
        Date today = new Date();
        
        // 1. FarmAnimal genérico
        FarmAnimal farmAnimal = new FarmAnimal(1, "Holstein", today);
        System.out.println("My Farm Animal is ---> " + farmAnimal);
        
        // 2. Chicken
        Chicken chicken = new Chicken(true, 2, "polish", today);
        System.out.println("Chicken --> " + chicken);
        
        // 3. Cow
        Cow cow = new Cow(true, 3, "Angus", today);
        System.out.println("Cow --> " + cow);
        
        // 4. Pig
        Pig pig = new Pig(4, "Duroc", today);
        System.out.println("Pig --> " + pig);
        
        // 5. Sheep
        Sheep sheep = new Sheep(today, 5, "Merino", today);
        System.out.println("Sheep --> " + sheep);
    }
}