from typing import Any

from ec.edu.espe.collections.model.person import Person


def main() -> None:
    print("======================")
    print("|   Mateo Nacimba´s  |")
    print("|     Collections    |")
    print("======================")

    # Colección heterogénea (equivale a Collection<Object> en Java)
    things: list[Any] = []

    person = Person(1, "jirge", 2851.57)
    print(f"person --->{person}")
    print(f"Size of my collections at the begining is ---> {len(things)}")

    things.append(19720)
    things.append("Hello OPP programmers from Mateo Nacimba")
    things.append(1078.56)
    things.append(person)
    print("================")
    print(f"size of my collections at this point-->{len(things)}")

    # Colección tipada (equivale a Collection<Person> en Java)
    persons: list[Person] = []
    print(f"Size of my collections of person -->{len(persons)}")

    persons.append(person)
    persons.append(Person(2, "Leslie Cuichan", 1851.23))
    persons.append(Person(3, "Dennis criollo", 3500.23))
    print(f"Size of my collection of persons -->{len(persons)}")
    print(f"persons -->{persons}")

    print("================")
    for p in persons:  # equivale a persons.forEach(...)
        print(f"person -->{p}")
    print(f"persons size -->{len(persons)}")

    print("================ for ")
    for p in persons:
        print(f"person -->{p}")

    for i in range(4, 15):
        new_person = Person(i, f"Mateo{i}", 1880.0 + i)
        persons.append(new_person)
        print(f"person--> {new_person}")


if __name__ == "__main__":
    main()
