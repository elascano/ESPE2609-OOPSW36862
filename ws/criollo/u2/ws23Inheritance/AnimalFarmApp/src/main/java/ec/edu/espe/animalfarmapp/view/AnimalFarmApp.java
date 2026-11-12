package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import java.util.Date;

/**
 *
 * @author  Dennis Criollo,Byte & Fabric Devs,@ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Dennis's Farm Animal");
        System.out.println("=======================");

        FarmAnimal farmAnimal;
        farmAnimal = new FarmAnimal(1, "Holstein", new Date());
        System.out.println("My Farm Animal is--->" + farmAnimal);
        Chicken chicken= new Chicken(true, 10, false, 2, "palish", new Date(2022,10, 10));
        System.out.println("chicken --->" + chicken);

    }
}
