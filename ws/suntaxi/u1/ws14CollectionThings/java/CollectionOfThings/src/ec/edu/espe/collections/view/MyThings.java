/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author Javi
 */
public class MyThings {
    public static void main(String[] args){
        System.out.println("===================");
        System.out.println("Anghelo Suntaxi");
        System.out.println("Collection");
        System.out.println("===================");
        Collection things;
        
        Person person;
        things = new ArrayList();
        person = new Person(1,"Jorge", 2851.27F);
        
        System.out.println("person----" + things.size());
        System.out.println("Size of my collections at the begining is-----" + things.size());
        
        things.add(19720);
        things.add("Hello OOP programmers from Anghelo");
        things.add(1978.56F);
        things.add(person);
        System.out.println("===================");
        System.out.println("Size of my collections at this point --" + things.size());
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collections at this point --" + things.size());
        
        System.out.println("===================");
        
        persons.add(person);
        persons.add(new Person(2, "Leslie Cuichan", 1851.25F));
        persons.add(new Person(3, "Dennis Criollo", 3500.37F));
        System.out.println("Size of my collections of persons --> " + persons.size());

        System.out.println("persons --> " + persons);

        System.out.println("============ for each del array list");
        persons.forEach((p) -> {
        System.out.println("person --> "+p);
        });

        System.out.println("persons size --> " + persons.size());

        System.out.println("============ for ");
        for(Person p: persons){
         System.out.println("person --> " + p);
        }
        for (int i = 4; i < 15; i++) {
            persons.add(new Person(i, "Anghelo Suntaxi " + i, 1000.F + i));
        }
        
        System.out.println("============== for ");
        for (Person p : persons) {
            System.out.println("person --> " + p);
        }
    }
}