package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class MyThings {
    
    public static void main(String[] args) {
        System.out.println("=====================");
        System.out.println("|  Andres Pachacama |");
        System.out.println("|     Collections   |");
        System.out.println("=====================");
        
        
        Collection things;
        Person person;
        things = new ArrayList();
        person = new Person(1, "Andres Pachacama", 2851.27F);
        System.out.println("person -->" +person);
        System.out.println("Size of my collection at the beginning is ---> "+ things.size());
        
        
        things.add(19720);
        things.add("Hello OOP programmers from Andres");
        things.add(4078.56F);
        things.add(person);
        System.out.println("==========================");
        System.out.println("Size of my collection at this point --->" + things.size());
        System.out.println("things -->" + things) ;
        
        things.remove(4078.56F);
        System.out.println("==========================");
        System.out.println("Size of my collection at this point --->" + things.size());
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collection at persons --->" + persons.size());
        System.out.println("==========================");
        
        persons.add(person);
        persons.add(new Person(2, "Leslie Cuichan", 1851.25F));
        persons.add(new Person(3, "Denis Criollo", 3500.37F));
        System.out.println("Size of my collection at persons --->" + persons.size());
        
        System.out.println("persons --->" + persons);
        
        System.out.println("========================== for each del array list");
        persons.forEach((p) -> {
            System.out.println("person -->" + p);
        });
        
        System.out.println("========================== for");
        System.out.println("persons size -->"+persons.size());
        for(Person p: persons) {
            System.out.println("person -->" + p);
        }
        
        for (int i=4; i<15; i++){
            persons.add(new Person(i, "Andres"+i, 1000.F+i));
        }
         System.out.println("========================== for");
        System.out.println("persons size -->"+persons.size());
        for(Person p: persons) {
            System.out.println("person -->" + p);
        }
        
        
    }
}
