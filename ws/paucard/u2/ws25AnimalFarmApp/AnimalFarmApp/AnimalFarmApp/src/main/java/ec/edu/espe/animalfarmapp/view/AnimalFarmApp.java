package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.Cow;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import ec.edu.espe.animalfarmapp.model.Food;
import ec.edu.espe.animalfarmapp.model.Pig;
import ec.edu.espe.animalfarmapp.model.Sheep;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @ESPE LAB
 */
public class AnimalFarmApp {

    /**
     * Crea una fecha a partir de año, mes (1-12) y día.
     * Reemplaza al constructor obsoleto new Date(year, month, day), cuyo año
     * se cuenta desde 1900 y cuyo mes empieza en 0.
     */
    private static Date createDate(int year, int month, int day) {
        return new GregorianCalendar(year, month - 1, day).getTime();
    }

    public static void main(String[] args) {
        FarmAnimal farmAnimal;
        Food food = new Food("corn");
        System.out.println("Farm Animal");
        System.out.println("=======================");

        farmAnimal = new Chicken(false, 10, 2, "palish", createDate(2022, 10, 10), true);
        System.out.println("chicken --->" + farmAnimal);
        System.out.println("chicken is a --->" + farmAnimal.getClass());
        System.out.println("chicken is a --->" + farmAnimal.getClass().getSimpleName());

        Chicken chicken = new Chicken(false, 10, 2, "palish", createDate(2022, 10, 10), true);
        System.out.println("chicken --->" + chicken);
        System.out.println("chicken is a --->" + chicken.getClass().getSimpleName());

        Cow cow = new Cow(true, 23.5F, 3, "Holstein", createDate(2024, 12, 23), false);
        System.out.println("cow --->" + cow);
        System.out.println("cow is a --->" + cow.getClass().getSimpleName());

        Pig pig = new Pig(true, 30.23F, 4, "Mangalitsa", createDate(2019, 11, 30), false);
        System.out.println("pig --->" + pig);
        System.out.println("pig is a --->" + pig.getClass().getSimpleName());

        Sheep sheep = new Sheep(true, createDate(2025, 11, 24), 5, "Manchega", createDate(2024, 9, 27), true);
        System.out.println("sheep --->" + sheep);
        System.out.println("sheep is a --->" + sheep.getClass().getSimpleName());

        chicken.layAnEgg();
        cow.milk(3.5F);
        cow.milk(20.5F);
        cow.milk(100F);

        chicken.feed(food, 2);
        
    }
}
