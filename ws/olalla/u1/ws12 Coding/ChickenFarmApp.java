package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Renata Olalla, Binary Bosses, @ESPE
 */
public class ChickenFarmApp {
 public static void main(String args[]){
     System.out.println("Renata Olalla's Chicken Farm App");
     System.out.println("=================================");
     Chicken chicken;
     Chicken chicken1;
     Egg egg;
     Poop poop;
     poop= new Poop(1, "brown", 'S');
     egg = new Egg(1, 'M');
     chicken= new Chicken(1, "Lucy", "brown and white", newDate(), 0, true);
     chicken1= new Chicken(2, "Renata", "white", newDate(), 0, false);
     System.out.println("chickem --> " + chicken);
     System.out.println("egg--> "+ egg);
     System.out.println("poop--> "+ poop);
     System.out.println("chicken1 -->" +chicken1);
 }   

    private static Date newDate() {
    return new Date();
}
    }

