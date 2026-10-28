package ec.edu.espe.collection.view;

import ec.edu.espe.collection.model.Person;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author Cristopher Guañuna @ESPE
 */
public class MyThings {
    public static void main(String[]args){
        System.out.println("======================");
        System.out.println("! Cristopher Guanuna !");
        System.out.println("!     Collections    !");
        System.out.println("=======================");
        
        Collection things;
        Person person;
        things = new ArrayList();
        person= new Person(1, "Jorge", 2851.27F);
        
        System.out.println("person -->" + person);
        
        System.out.println("Size of my collections at the beggining is --> " + things.size());
        

        
        things.add(19720);
        things.add("Hello OOP programmers from Cristopher Guanuna");
        things.add(4078.56f);
        things.add(person);
        
        System.out.println("===========");
        System.out.println("Size of my collections at this point is --> " + things.size());
        System.out.println("things --> " + things);
        
        things.remove(4078.56F);
        System.out.println("===========");
        System.out.println("Size of my collections at this point is --> " + things.size());
        System.out.println("===========");
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collections at this point is --> " + persons.size());
        System.out.println("===========");
        
        persons.add(person);
        persons.add(new Person(2, "Leslie Cuichian", 1851.25F));
        persons.add(new Person(3, "Dennis Criollo", 3500.37F));
        System.out.println("Size of my collections at this point is --> " + persons.size());
        
        System.out.println("persons -->" + persons);
                System.out.println("=========== for each del array list");
        
        persons.forEach((p)->{
            System.out.println("person --> " +p );
        });
        
        System.out.println("persons size--> " + persons.size());
        
        System.out.println("=========== for");
        for(Person p: persons){
            System.out.println("person --> " + p);
        }
        for (int i = 4; i < 15; i++) {
            persons.add(new Person(i,"Cristopher"+ 1, 1000.F+i));
        }
        System.out.println("=========== for");
        for(Person p: persons){
            System.out.println("person --> " + p);
        }   
    }
}