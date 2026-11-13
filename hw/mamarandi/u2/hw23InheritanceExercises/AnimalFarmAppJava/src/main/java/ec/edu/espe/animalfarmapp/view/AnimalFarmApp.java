package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.util.Date;

/**
 *
 * @author Francisco Demera,Byte & Fabric Devs,@ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        FarmAnimal farmAnimal;

        System.out.println("Francisco's Farm Animal");
        System.out.println("=======================");

        farmAnimal = new FarmAnimal(1, "Holstein", new Date(), false);
        System.out.println("My Farm Animal is--->" + farmAnimal);

        Chicken chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 10, 10));
        System.out.println("chicken --->" + chicken);

        Cow cow = new Cow(true, 23.5F, 3, "Holstein", new Date(2024, 12, 23), false);
        System.out.println("cow --->" + cow);

        Pig pig = new Pig(true, 30.23F, 4, "Mangalitsa", new Date(2019, 11, 30), false);
        System.out.println("pig --->" + pig);

        Sheep sheep = new Sheep(true, new Date(2025, 11, 24), 5, "Manchega", new Date(2024, 9, 27), true);
        System.out.println("sheep --->" + sheep);
    }
}
