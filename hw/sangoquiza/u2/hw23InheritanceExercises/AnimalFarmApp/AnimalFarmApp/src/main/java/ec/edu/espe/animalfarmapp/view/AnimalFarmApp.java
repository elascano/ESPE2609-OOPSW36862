/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Cow;
import java.util.Date;

/**
 *
 *  @author Daniel Sangoquiza, SaborCode, @ESPE
 */
public class AnimalFarmApp {

    public static void main(String[] args) {
        FarmAnimal farmAnimal;

        System.out.println("Daniel Sangoquiza's Farm Animal");
        System.out.println("=======================");

        farmAnimal = new FarmAnimal(1, "Holstein", new Date(), false);
        System.out.println("My Farm Animal is--->" + farmAnimal);

        Chicken chicken = new Chicken(false, 10, 2, "palish", new Date(2022, 10, 10));
        System.out.println("chicken --->" + chicken);

        Cow cow = new Cow(true, 23.5F, 3, "Holstein", new Date(2024, 12, 23), false);
        System.out.println("cow --->" + cow);
    }
}