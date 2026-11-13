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
        farmAnimal = new FarmAnimal(1,"holstein", new Date());
        
        System.out.println("my farm animal --> "+ farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());
        System.out.println("chicken --> "+ chicken);
        
        Cow cow = new Cow(true, 100.5F , 3, "holstein", new Date());
        System.out.println("cow --> "+ cow);
        
        Pig pig = new Pig(true, 30.23F, 4, "Mangalitsa", new Date(2019, 11, 30));
        System.out.println("pig --> "+pig);
        
        Sheep sheep = new Sheep(true, new Date(2026,10,20), 5, "Merino", new Date(2014,11,25));
        System.out.println("sheep --> "+ sheep);
        
        
        
        
    }
}
