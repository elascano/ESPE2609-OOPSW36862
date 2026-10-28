package ec.edu.ecpe.chickenfarmapp.view;
import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
/**
 *
 * @author Francisco Demera, Byte and Fabric Devs, @ESPE
 */
public class ChickenFarmApp {
    public static void main(String args[]){
        System.out.println("Hello, Francisco Demera's Chicken Farm App");
        System.out.println("------------------------------------------");
        
        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        
        chicken = new Chicken(1, "Lucy", "brown and white", java.sql.Date.valueOf("2020-03-28"), true);
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown",'S');
        chicken1 = new Chicken(2, "Arturita", "white", java.sql.Date.valueOf("2007-04-28"), true);
        Chicken[] chickens = new Chicken[10];
        System.out.println("chicken --->" + chicken);
        System.out.println("egg --->" + egg);
        System.out.println("poop--->" + poop);
        System.out.println("chicken 2 ---" + chicken1);
        
        int localAge = chicken.computeAgeInYears();
        System.out.println("the age of the chicken " + chicken.getName() + " is " + localAge);
        chicken.doStuff();
        
        chickens[0] = new Chicken(3, "Pepita", "Gray", java.sql.Date.valueOf("2020-02-02"), true);
        chickens[1] = chicken;
        chickens[2] = chicken1;
         for (int i = 3; i < chickens.length; i++) {
            chickens[i] = new Chicken(i + 1, "chicken", "black", java.sql.Date.valueOf(java.time.LocalDate.of(2020 - i + 1, 5, 27)), true);
        }
        ChickenCoop chickenCoop = new ChickenCoop(1, chickens);
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null) {
                System.out.println("chicken ---->" + chickens[i]);
        }
    }
    }
}    
