package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class MyThings {
    public static void main(String[] args) {
        System.out.println("=====================");
        System.out.println("|   Yandry Velez |");
        System.out.println("|    Collections    |");
        System.out.println("=====================");

        Collection things;
        Person person;
        things = new ArrayList();
        person = new Person(1, "Jorge", 2851.27F);

        System.out.println("person --> " + person);

        System.out.println("Size of my collections at the beginnig is --> " + things.size());

        things.add(19720);
        things.add("Hello OOP programmers from Yandry ");
        things.add(4078.56F);
        things.add(person);
        things.remove(4078.56F);
        System.out.println("Size of my collections at this point --> " + things.size());
        System.out.println("Thing --- >" + things);
     Collection<Person> persons;
     persons = new ArrayList<>();
     System.out.println("Size of my Collection persosn -->" + persons.size());
     
     persons.add(person);
     persons.add(new Person(1,"Josselyn Ulcuando ", 1851.25F));
     persons.add(new Person(2,"Priscila Morales ", 1851.25F));
     System.out.println("Size of my Collection persosn -->" + persons.size());
     System.out.println("Persons--->" + person);
     persons.forEach((p) ->{
     System.out.println("person -->" + p); 
     });
     System.out.println("Person size -->" + persons.size());
     
     for (Person p: persons){
          System.out.println("person--->" + p);
     }
     for (int i = 4; i < 15; i++) {
            persons.add(new Person(i, "Yandry " + i, 1000.F + i));
        }

        System.out.println("============== for ");
        for (Person p : persons) {
            System.out.println("person --> " + p);
        }
    }
}

