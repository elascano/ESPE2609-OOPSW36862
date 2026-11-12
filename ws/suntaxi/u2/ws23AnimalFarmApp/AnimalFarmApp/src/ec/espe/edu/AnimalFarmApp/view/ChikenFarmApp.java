package ec.espe.edu.AnimalFarmApp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import java.util.Date;

public class ChikenFarmApp {
    public static void main(String[] args) {
        System.out.println("David's farm animal");
        System.out.println("======================");
        
        FarmAnimal farmAnimal;
        farmAnimal = new FarmAnimal(1, "Alex", new Date());
        
        System.out.println("My farm animal is -> " + farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, false, 2, "Palish", new Date(2020,10,10));
        System.out.println("chicken -->" + chicken);
    }
}
