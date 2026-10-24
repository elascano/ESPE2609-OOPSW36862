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
 * @author Francisco Demera, Byte and Fabric Devs, @ESPE
 */
public class MyThings {

    public static void main(String[] args) {
        System.out.println("========================");
        System.out.println("|  Dennis Criollo's  |");
        System.out.println("|     Collections      |");
        System.out.println("========================");
        
        Person person;
        Collection things;
        things = new ArrayList();
        person = new Person(1, "Cataleya Criollo", 2851.27F);
        
        System.out.println("size of my collection at the beggining is ------->" + things.size());
        
        System.out.println("person----->" + person);
        things.add(19720);
        things.add("Hello OOP programmers from Dennis Criollo");
        things.add(4078.56F);
        things.add(person);
        
        System.out.println("===============");
        System.out.println("size of my collection at this point is ------->" + things.size());
        System.out.println("things---->" + things);
        
        things.remove(4078.56F);
        
        System.out.println("===============");
        System.out.println("size of my collection at this point is ------->" + things.size());
        System.out.println("things---->" + things);
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        
        System.out.println("===============");
        System.out.println("size of my collection of persons ------->" + persons.size());
        
        persons.add(person);
        persons.add(new Person(2, "Dennis Criollo", 1254.25F));
        persons.add(new Person(3, "Dennis Criollo", 1376.45F));
        
        System.out.println("=============== forEach in array List");
        System.out.println("size of my collection of persons ------->" + persons.size());
        System.out.println("things---->" + persons);
        
        System.out.println("===============");
        persons.forEach((p)->{
        System.out.println("person---->" + p);
        });
        
        System.out.println("persons size--->" + persons.size());
        
        System.out.println("=============== for");
        for(Person p: persons){
            System.out.println("person---->" + p);
        }
        
        for(int i = 4; i < 15; i++){
            persons.add(new Person(i, "Dennis " + i, 1000.F + i));
        }
        System.out.println("=========");
        for(Person p: persons){
            System.out.println("person---->" + p);
        }
    }
}
