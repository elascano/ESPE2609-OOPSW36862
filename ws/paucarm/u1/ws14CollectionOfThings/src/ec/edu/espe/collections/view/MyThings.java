package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class MyThings {
    public static void main(String[] args) {
        System.out.println("=======================");
        System.out.println("|   Matias Paucar's   |");
        System.out.println("|     Collections     |");
        System.out.println("=======================");
        
       
        Collection things;
        Person person;
        things = new ArrayList();
        person = new Person(1, "Matias Paucar", 2851.27F);
        
        System.out.println("person --> " + person);
       
        System.out.println("Size of my collection at the beginning -->" + things.size());
        
        
        things.add(19720);
        things.add("Hello OOP programmers from Matias Paucar");
        things.add(4078.56F);
        things.add(person);
        System.out.println("=============");
        System.out.println("Size of my collection at this point -->" + things.size());
        System.out.println("things -->" + things);

        things.remove(4078.56F);
        System.out.println("=============");
        System.out.println("Size of my collection at this point -->" + things.size());
        
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collection of persons -->" + persons.size());
        
        System.out.println("=============");

        persons.add(person);
        persons.add(new Person(2, "Leslie Cuichan", 1851.25F));
        persons.add(new Person(3, "Dennis Criollo", 3500.37F));
        System.out.println("Size of my collection of persons -->" + persons.size());

        
        System.out.println("persons ->" + persons);
       
        System.out.println("======== for each of array List");
        persons.forEach((p) -> {
            System.out.println("person --> " + p);
        });
        
        System.out.println("persons size -->" + persons.size());

       
        System.out.println("======== for");
        for (Person p : persons) {
            System.out.println("person-->" + p);
        }
        
        for (int i=4; i<15;i++){
            persons.add(new Person(i,"Matias "+i, 1000.F+i));
        }
        System.out.println("======== for");
        for (Person p : persons) {
            System.out.println("person-->" + p);
        }
    }
}
