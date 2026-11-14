package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import java.util.Date;

/**
 *
 * @author Isaac Acurio, Logical Lords, @ESPE
 */
public class AnimalFarmApp {
    public static void main (String[] args){
        System.out.println("Isaac Farm Animal");
        System.out.println("====================");
        
        FarmAnimal farmAnimal = new FarmAnimal(1,"Holstein", new Date());
        System.out.println("My Farm Animal is -->" + farmAnimal);
        Chicken chicken= new Chicken(true, 10, false, 2, "palish", new Date());
        System.out.println("chicken -->" + chicken);
                
    }
}
