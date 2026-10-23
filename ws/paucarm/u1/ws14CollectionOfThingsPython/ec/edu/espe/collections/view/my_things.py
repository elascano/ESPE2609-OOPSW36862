"""
@author Paucar Matias, ByteWorks, @ESPE
"""
import os
import sys

# Permite ejecutar este archivo directamente (boton Run de VS Code)
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), "..", "..", "..", "..", "..")))

from ec.edu.espe.collections.model.person import Person  # noqa: E402


def to_string(collection: list) -> str:
    """Imprime la colección igual que el toString() de ArrayList en Java."""
    return "[" + ", ".join(str(item) for item in collection) + "]"


def main() -> None:
    print("=======================")
    print("|   Matias Paucar's   |")
    print("|     Collections     |")
    print("=======================")

    things: list = []
    person = Person(1, "Matias Paucar", 2851.27)

    print("person --> " + str(person))

    print("Size of my collection at the beginning -->" + str(len(things)))

    things.append(19720)
    things.append("Hello OOP programmers from Matias Paucar")
    things.append(4078.56)
    things.append(person)
    print("=============")
    print("Size of my collection at this point -->" + str(len(things)))
    print("things -->" + to_string(things))

    things.remove(4078.56)
    print("=============")
    print("Size of my collection at this point -->" + str(len(things)))

    persons: list[Person] = []
    print("Size of my collection of persons -->" + str(len(persons)))

    print("=============")

    persons.append(person)
    persons.append(Person(2, "Leslie Cuichan", 1851.25))
    persons.append(Person(3, "Dennis Criollo", 3500.37))
    print("Size of my collection of persons -->" + str(len(persons)))

    print("persons ->" + to_string(persons))

    print("======== for each of array List")
    list(map(lambda p: print("person --> " + str(p)), persons))

    print("persons size -->" + str(len(persons)))

    print("======== for")
    for p in persons:
        print("person-->" + str(p))

    for i in range(4, 15):
        persons.append(Person(i, "Matias " + str(i), 1000.0 + i))
    print("======== for")
    for p in persons:
        print("person-->" + str(p))


if __name__ == "__main__":
    main()
