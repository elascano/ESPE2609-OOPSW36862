package ec.edu.espe.animalfarm.view;

import ec.edu.espe.animalfarm.model.Chicken;
import ec.edu.espe.animalfarm.model.Cow;
import ec.edu.espe.animalfarm.model.FarmAnimal;
import ec.edu.espe.animalfarm.model.Pig;
import ec.edu.espe.animalfarm.model.Sheep;
import java.util.Date;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class AnimalFarm {

    public static void main(String[] args) {
        System.out.println("Hello This is David's Anima Farm");
        FarmAnimal farmAnimal;
        farmAnimal = new FarmAnimal(1, "holstein", new Date());
        
        System.out.println("my farm animal --->" + farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, true, 2, "Paleesh", new Date());
        System.out.println("chicken --->" + chicken);
        
        FarmAnimal cow = new Cow(true, 100.5F, 3, "lola", new Date(2021,10,11));
        System.out.println("cow --> " + cow);
        
        Pig pig = new Pig(85.5F, true, 4, "David", new Date());
        System.out.println("pig --> " + pig);

        Sheep sheep = new Sheep(new Date(), true, 4.2F, 5, "Alexander", new Date());
        System.out.println("sheep --> " + sheep);
    }
}
