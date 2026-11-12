/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import java.util.Date;

/**
 *
 * @author Andres Pachacama, Byte Works, ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        System.out.println("Andres Farm Animal");
        System.out.println("==================");
        
        
        FarmAnimal farmAnimal;
        farmAnimal = new FarmAnimal(1, "Holstein", new Date());
        System.out.println("My Farm Animal is  -->"+farmAnimal);
        
        
        Chicken chicken = new Chicken(true, 10, false, 2, "palish", new Date(2022,10,10) );
        System.out.println("chicken --> "+chicken );


        Cow cow = new Cow(25.5F, false, 650.0F, true, 3, "Holstein", new Date(2021 - 1900, 4, 15));
        System.out.println("cow --> "+cow );
        
        
        
    }
}
