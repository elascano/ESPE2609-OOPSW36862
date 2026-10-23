package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author David Clavón, Infinite loops, @ESPE
 */
public class MyThings {
    public static void main(String[] args){
        System.out.println("===========");
        System.out.println("David Clavon");
        System.out.println("Collections");
        System.out.println("===========");
        
        Collection things;
        Person person;
        things = new ArrayList();
        person = new Person (1,"jorge", 2851.27F);
        
        System.out.println("person: "+person);
        System.out.println("Size of my collections at the beginning is: " + things.size());
        
        things.add(197207);
        things.add("Hello OOP programers from David");
        things.add(4078.56F);
        things.add(person);
        
         System.out.println("Size of my collections at the beginning is: " + things.size());
         System.out.println("things: "+things);
         
         things.remove(4078.56F);
         System.out.println("=======");
         System.out.println("Size of collections at this point: "+things.size());
         
         Collection <Person> persons;
         persons = new ArrayList<>();
         System.out.println("Size of my colection of persons: "+persons.size());
         
         persons.add(person);
         persons.add(new Person(2,"Lesley Cuichan", 1851.25F));
         persons.add(new Person(3,"Denis Criollo", 1851.37F));
         
         System.out.println("Size of my colection of persons: "+persons.size());

         System.out.println("persons: "+persons);
         
         persons.forEach((p) -> {
            System.out.println("person --> " + p);
         });

         System.out.println("persons size --> " + persons.size());

         System.out.println("============== for ");
         for (Person p : persons) {
            System.out.println("person --> " + p);
         }

         for (int i = 4; i < 15; i++) {
            persons.add(new Person(i, "Lesley " + i, 1000.F + i));
         }

         System.out.println("============== for ");
        for (Person p : persons) {
            System.out.println("person --> " + p);
         }    
    }
}
