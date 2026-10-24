/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.collection.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Enrique Ayala, Byte and LogicalLords, @ESPE
 */
public class MyThings {
    public static void main(String[] args) {
        System.out.println("==================");
        System.out.println("Francisco Demera's");
        System.out.println("    Collection    ");
        System.out.println("==================");
        
        Collection things;
        Person person;
        things = new ArrayList();
        person = new Person(1,"Jorge Lascano", 2851.27F);
        System.out.println("size of my collection at the beggining is -------" + things.size());
        
        things.add(1920);
        things.add("Hello OOP programmers from Edison");
        things.add(4078.56F);
        things.add(person);
        System.out.println("Size of my collections at the begginning is-->"+ things.size());
        System.out.println("things-->" + things);
        
        things.remove(4078.56F);
        System.out.println("========");
        System.out.println("Size of my collections at this point--->" + things.size());
        
        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collections of persons-->"+ persons.size());
        
        persons.add(person);
        persons.add(new Person(2,"Leslie Cuicahan", 1851.25F));
        persons.add(new Person(3,"Dennis Criollo", 3050.37F));
        
        System.out.println("persons-->"+persons);
        
        System.out.println("=============");
        persons.forEach((p) -> {
            System.out.println("person -->"+p);
        });
        
        System.out.println("persons size -->"+persons.size());
        
        System.out.println("=========== for");
        for(Person p:persons){
            System.out.println("person -->"+p);
        }
        
        for(int i=0; i<15;i++){
            persons.add(new Person(i,"Edison"+i,1000.F+i));
        }
        
        System.out.println("=========== for");
        for(Person p:persons) {
            System.out.println("person -->"+p);
        }
        
    }
}
