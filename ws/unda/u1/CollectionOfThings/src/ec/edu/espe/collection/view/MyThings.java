package ec.edu.espe.collection.view;

import Person.Person;
import java.util.ArrayList;
import java.util.Collection;
/**
 *
 * @author Mateo Unda, CodeLex, @ESPE
 */
public class MyThings {
    public static void main(String[] args) {
        System.out.println("==================");
        System.out.println("|  Mateo Unda's  |");
        System.out.println("|   Collections  |");
        System.out.println("==================");
        
        Collection things;
        Person person;
        things = new ArrayList();
        person = new Person(1, "Jorge", 2851.27F);
        
        System.out.println("person -->" + person);
        System.out.println("Size of my collections at the beginning is -->" + things.size());
        
        things.add(19720);
        things.add("Hello OOP programmers from Mateo Unda");
        things.add(4078.56F);
        things.add(person);
        System.out.println("==================");
        System.out.println("Size of my collections at this point -->" + things.size());
        System.out.println("things -->" + things);
        
        things.remove(4078.56F);
        System.out.println("==================");
        System.out.println("Size of my collections at this point -->" + things.size());
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collections of persons -->" + persons.size());
        
        System.out.println("==================");
        persons.add(person);
        persons.add(new Person(2, "Leslie Cuichan", 1851.25F));
        persons.add(new Person(3, "Dennis Criollo", 3500.37F));
        System.out.println("Size of my collections of persons -->" + persons.size());
        System.out.println("persons -->" + persons);
        
        System.out.println("============== for each del array list");
        persons.forEach((p) ->{
            System.out.println("person --> " + p);
        });
        System.out.println("persons size -->" + persons.size());
        
        System.out.println("================ for ");
        for(Person p: persons){
            System.out.println("person --> "+ p);
        }
        System.out.println("================ for ");
        for(int i=4; i<15;i++){
            persons.add(new Person(i, "Mateo"+i,1000.F +i));
        }
        System.out.println("================ for ");
        for(Person p: persons){
            System.out.println("person --> "+ p);
        }
    }
}
