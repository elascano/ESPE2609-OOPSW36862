/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Marcos Mamarandi, Binary Bosses,@ESPE
 */
public class MyThings {
    public static void main (String[]args){
            System.out.println("=========================");
            System.out.println("|    Marcos Mamarandi's |");
            System.out.println("|       Collections     | ");
            System.out.println("=========================");
    Collection things;  
    things = new ArrayList();
    Person person;
    person = new Person(1, "Marcos", 2851.27F);
    
        System.out.println("person -->" + person);
    
        System.out.println("Size of my collections at the beginning is --> "+things.size());
        
        things.add(19720);
        things.add("Hello OOP programmers from Marcos");
        things.add(4078.56F);
        things.add(person);
        
        System.out.println("Size of my collections at this point --> "+things.size());
        System.out.println("things -->" + things);
        
        things.remove(4078.56F);
        System.out.println("=====================");
        System.out.println("Size of my collections at this point --> " + things.size());
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collections of persons --> " +persons.size());
        
        System.out.println("=====================");
        
         persons.add(person);
         persons.add(new Person(2, "Lesly Cuichan", 1851.25F));
         persons.add(new Person(3, "Dennis Criollo", 3500.37F));
         System.out.println("Size of my collections of persons -->" +persons.size());
         System.out.println("persons -->" + persons);
         
         System.out.println("=====================");
         
        persons.forEach((p) -> {
    System.out.println("person --> " + p);
});
        System.out.println("persons size -->" +persons.size());
        System.out.println("============= for");
        for(Person p: persons){
        
            System.out.println("person --> " +p);
        }
        for(int i=4; i<15; i++){
        persons.add(new Person(i, "Marcos" +i, 1000.F + i));
    
    }
        System.out.println("=============== for ");
        for(Person p : persons){
            System.out.println("person --> " + p);
        }
       
        
    }
}
