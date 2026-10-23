package ec.edu.espe.collections.view;

import ec.edu.espe.collections.model.Person;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class MyThings {

    public static void main(String[] args) {
        System.out.println("=================");
        System.out.println("|  Armas Demian |");
        System.out.println("|  Collections  |");
        System.out.println("=================");

        Collection things;
        Person person;
        things = new ArrayList();
        person = new Person(1, "Jorge", 2851.27F);
        System.out.println("person --> " + person);

        System.out.println("Size of my collections at the beginning is --> " + things.size());

        things.add(19720);
        things.add("Hello OOP programers from Demian Armas");
        things.add(4078.56F);
        things.add(person);
        System.out.println("============");
        System.out.println("Size of my collections at this point " + things.size());
        System.out.println("things --> " + things);

        things.remove(4078.56F);
        System.out.println("============");
        System.out.println("Size of my collections at this point " + things.size());

        Collection<Person> persons;
        persons = new ArrayList<>();
        System.out.println("Size of my collections at this point " + persons.size());

        System.out.println("============");

        persons.add(person);
        persons.add(new Person(2, "Leslie Cuichan", 1851.25F));
        persons.add(new Person(3, "Demnis Criollo", 3500.37F));
        System.out.println("Size of my collections at this point " + persons.size());

        System.out.println("persons --> " + persons);
        System.out.println("============");
        persons.forEach(p -> {
            System.out.println("person ->" + p);
        });

        System.out.println("persons size -->" + persons.size());
        System.out.println("============== for ");
        for (Person p : persons) {
            System.out.println("person -->" + p);
        }

        for (int i = 4; i < 15; i++) {
            persons.add(new Person(i, "Demian" + i, 1000.F + i));
        }
        System.out.println("============== for ");
        for (Person p : persons) {
            System.out.println("person -->" + p);
        }

    }
}
