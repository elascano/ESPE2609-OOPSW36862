package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.util.Date;

/**
 *
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println(" Leslye's Animal Farm!");
        System.out.println("=======================");
        
        FarmAnimal farmAnimal = new FarmAnimal(1, "holstein", new Date());
        System.out.println("My farm animal -->" + farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, false, 2, "Palish", new Date(2022, 10, 10));
        System.out.println("chicken -->" + chicken); 

        Cow cow = new Cow(true, 50.5f, 3, "Holstein", new Date(2009, 1, 15));
        System.out.println("cow --> " + cow);

        Pig pig = new Pig(4, "Landrace", new Date(2011, 5, 20), 85.0f, true);
        System.out.println("pig --> " + pig);

       Sheep sheep = new Sheep(new Date(124, 8, 1), true, true, 5, "Merino", new Date(1989, 11, 5));
        System.out.println("sheep --> " + sheep);
    }
}