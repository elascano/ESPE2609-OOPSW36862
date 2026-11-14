package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.util.Date;
/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Josselyn' Farm Animal");
        System.out.println("==================");
        
        
        FarmAnimal farmAnimal = new FarmAnimal(1, "Holstein", new Date());
        System.out.println("My farm Animal is --> " + farmAnimal);
        
        System.out.println("My farm Animal is a --> " + farmAnimal.getClass().getSimpleName());
        

       
        Chicken chicken = new Chicken(true, 10, false, 2, "Palish", new Date(2022 - 1900, 10 - 1, 10));
        System.out.println("Chicken --> " + chicken);
         System.out.println("Farm animal is a  --> " + farmAnimal.getClass().getSimpleName());
        System.out.println("Chicken is a --> " + chicken.getClass());
        
        Cow cow = new Cow(3, "Lola", new Date(), true);
        System.out.println("Cow --> " + cow);
        
        System.out.println("cow is  a --> " + chicken.getClass());  
        
        Pig pig = new Pig(4, "Pumba", new Date());
        System.out.println("Pig --> " + pig);

        Sheep sheep = new Sheep(5, "Cotton", new Date(), new Date());
        System.out.println("Sheep --> " + sheep);
        
        cow.milk();
        chicken.layAnEgg();
    }
}