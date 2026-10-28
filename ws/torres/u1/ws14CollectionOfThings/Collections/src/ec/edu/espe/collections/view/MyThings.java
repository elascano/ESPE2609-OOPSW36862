package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Ostin Torres, CodeLex, @ESPE
 */

    public class MyThings {
        public static void main(String[] args) {
        System.out.println(" =================");
        System.out.println("| Ostin Torres'   |");
        System.out.println("| Collections     |");
        System.out.println(" =================");
        
        Person person;
        Collection things;
        things =new ArrayList();
        person =new Person(1, "Ostin", 2851.27F);
        
        System.out.println("person --> " + person);
        
        System.out.println("size of my collection at the beginning -->" + things.size());
        
        things.add(18000);
        things.add("Hello OOP Programmers from Ostin");
        things.add(4073.78F);
        things.add(person);
        System.out.println("=================");
        System.out.println("size of my collection at this point -->" + things.size()); 
        System.out.println("things -->" + things);
                       
        things.remove(4073.78F);
        System.out.println("=================");
        System.out.println("size of my collection at this point -->" + things.size()); 
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("size of my collection of person -->" + persons.size());
                
        persons.add(person);
        persons.add(new Person(3, "Erick Revelo", 480.87F));
        persons.add(new Person(4, "Fabian Torres", 4480.87F));
        System.out.println("size of my collection of person -->" + persons.size());
        
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
            persons.add(new Person(i,"Ostin "+i, 1000.F+i));
        }
        System.out.println("======== for");
        for (Person p : persons) {
            System.out.println("person-->" + p);
        }
    }
}
