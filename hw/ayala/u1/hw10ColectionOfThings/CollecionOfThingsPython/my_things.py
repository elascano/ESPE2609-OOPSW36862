"""
@author Enrique Ayala, Byte and LogicalLords, @ESPE
"""

from typing import List, Any
from person import Person


def main():
    print("==================")
    print("Enrique Ayala's")
    print("    Collection    ")
    print("==================")

    things: List[Any] = []
    person = Person(1, "Jorge Lascano", 2851.27)

    print(f"size of my collection at the beggining is -------{len(things)}")

    things.append(1920)
    things.append("Hello OOP programmers from Edison")
    things.append(4078.56)
    things.append(person)

    print(f"Size of my collections at the begginning is-->{len(things)}")

    formatted_things = [str(item) for item in things]
    print(f"things-->[{', '.join(formatted_things)}]")

    if 4078.56 in things:
        things.remove(4078.56)

    print("========")
    print(f"Size of my collections at this point--->{len(things)}")

    persons: List[Person] = []
    print(f"Size of my collections of persons-->{len(persons)}")

    persons.append(person)
    persons.append(Person(2, "Leslie Cuicahan", 1851.25))
    persons.append(Person(3, "Dennis Criollo", 3050.37))

    formatted_persons = [str(p) for p in persons]
    print(f"persons-->[{', '.join(formatted_persons)}]")

    print("=============")
    for p in persons:
        print(f"person -->{p}")

    print(f"persons size -->{len(persons)}")

    print("=========== for")
    for p in persons:
        print(f"person -->{p}")

    for i in range(15):
        persons.append(Person(i, f"Edison{i}", 1000.0 + i))

    print("=========== for")
    for p in persons:
        print(f"person -->{p}")


if __name__ == "__main__":
    main()