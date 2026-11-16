package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import java.util.Date;

/**
 *
 * @author Matias Paucar, ByteWroks, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Matias's Animal Farm!");
        System.out.println("=====================================");
        FarmAnimal farmAnimal;

        Date bornOnDate = new Date(2020, 10 ,10);
        farmAnimal = new Cow(true, 6, 10, "houlstein", bornOnDate);
        
        System.out.println("farmAnimal is a -->" + farmAnimal.getClass().getSimpleName());
        
        System.out.println("my farm animal -->" + farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, true, 2, "Palish", new Date());
        System.out.println("Chicken -->" + chicken); 
       chicken.layAnEgg();
        
        
        FarmAnimal farAnimalForFeeding = new Chicken(true, 20, true, 30, "Palish", bornOnDate);
        
        
        System.out.println("farAnimalForFeeding is a -->" + farAnimalForFeeding.getClass());
        System.out.println("farAnimalForFeeding is a -->" + farAnimalForFeeding.getClass().getSimpleName());
        
        farAnimalForFeeding = new Cow(true, 7, 5, "houlstein", bornOnDate);
        System.out.println("farAnimalForFeeding is a -->" + farAnimalForFeeding.getClass().getSimpleName());
        
        farAnimalForFeeding = new Chicken(true, 5, true, 0, "Paliish", bornOnDate);
        System.out.println("farAnimalForFeeding is a -->" + farAnimalForFeeding.getClass().getSimpleName());
        
        System.out.println("Cow -->" + farAnimalForFeeding);
        
        Cow cow = new Cow(true, 10, 20, "hlstein", bornOnDate);
        cow.milk();
        

    }
}