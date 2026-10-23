package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

public class MyThings {
    public static void main(String[] arg) {
        System.out.println("======================");
        System.out.println("|   Mateo Nacimba´s  |");
        System.out.println("|     Collections    |");
        System.out.println("======================");

        Collection<Object> things = new ArrayList<>();
        Person person = new Person(1, "jirge", 2851.57F);

        System.out.println("person --->" + person);
        System.out.println("Size of my collections at the begining is ---> " + things.size());

        things.add(19720);
        things.add("Hello OPP programmers from Mateo Nacimba");
        things.add(1078.56F);
        things.add(person);

        System.out.println("================");
        System.out.println("size of my collections at this point-->" + things.size());

        Collection<Person> persons = new ArrayList<>();
        System.out.println("Size of my collections of person -->" + persons.size());

        persons.add(person);
        persons.add(new Person(2, "Leslie Cuichan", 1851.23F));
        persons.add(new Person(3, "Dennis criollo", 3500.23F));

        System.out.println("Size of my collection of persons -->" + persons.size());
        System.out.println("persons -->" + persons);
        System.out.println("================");

        persons.forEach(p -> {
            System.out.println("person -->" + p);
        });

        System.out.println("persons size -->" + persons.size());
        System.out.println("================ for ");
        for (Person p : persons) {
            System.out.println("person -->" + p);
        }

        for (int i = 4; i < 15; i++) {
            Person newPerson = new Person(i, "Mateo" + i, 1880.0F + i);
            persons.add(newPerson);
            System.out.println("person--> " + newPerson);
        }
    }
}