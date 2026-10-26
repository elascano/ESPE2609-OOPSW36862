package ec.edu.edpe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class ChickenFarmApp {
  public static void main(String args[]){
      System.out.println("Yandry Velez Chicken Farm");
      System.out.println("==========================");
      Chicken chicken;
      Chicken chicken1;
      Egg egg;
      Poop poop;
      chicken = new Chicken(1, "Lucy", "brown and white", new Date(), 0, true);
      egg = new Egg(1, 'M');
      poop = new Poop(1,"bronw", 'S');
      chicken1 = new Chicken(2,"Renata ", "White ", new Date(), 0, true);
      System.out.println("Chicken -->" + chicken);
      System.out.println("Egg -->" + egg);
      System.out.println("poop -->" + poop);
      System.out.println("Chicken2--->"+ chicken1);
  }  
}
