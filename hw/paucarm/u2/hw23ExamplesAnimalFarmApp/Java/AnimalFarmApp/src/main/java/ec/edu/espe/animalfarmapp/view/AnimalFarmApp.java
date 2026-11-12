package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import java.util.Date;

/**
 *
 * @author Matias Paucar, ByteWorks, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println(" Matias's Animal Farm");
        System.out.println("=====================");
        FarmAnimal farmAnimal;
        farmAnimal = new FarmAnimal(1,"holstein", new Date());
        
        System.out.println("my farm animal is --> "+ farmAnimal);
        
    Chicken chicken = new Chicken(true, 10, false, 2, "Palish", new Date(2022,10,10));
        System.out.println("chicken --> "+ chicken);
        
     FarmAnimal cow = new Cow(true, 100.5F , 3, "holstein", new Date());
        System.out.println("cow --> "+ cow);
    
    }
}