class Person:
    def __init__(self, id_val, name, salary):
        self.id = id_val
        self.name = name
        self.salary = float(salary)

    def __str__(self):
        # El \t añade la tabulación que configuraste en Java
        return f"id={self.id}, name={self.name}, \tsalary={self.salary}"

    def __repr__(self):
        # En Python, __repr__ asegura que los objetos se impriman correctamente cuando están dentro de una lista
        return self.__str__()


def main():
    print("=========================")
    print("|  Daniel Sangoquiza's  |")
    print("|      Collections      |")
    print("=========================")

    # Colección mixta equivalente a "Collection things"
    things = []
    person = Person(1, "Jorge", 2851.27)

    print(f"person --> {person}")

    print(f"Size of my collections at the beginnig is --> {len(things)}")

    things.append(19720)
    things.append("Hello OOP programmers from Daniel Sangoquiza ")
    things.append(4078.56)
    things.append(person)
    
    print("================")
    print(f"Size of my collections at this point --> {len(things)}")
    print(f"things --> {things}")

    things.remove(4078.56)
    print("================")
    print(f"Size of my collections at this point --> {len(things)}")

    # Colección tipada equivalente a "Collection<Person> persons"
    persons = []
    print(f"Size of my collections of persons --> {len(persons)}")
    
    print("================")
    
    persons.append(person)
    persons.append(Person(2, "Leslie Cuichan", 1851.25))
    persons.append(Person(3, "Dennis Criollo", 3500.37))
    print(f"Size of my collections of persons --> {len(persons)}")
    
    print(f"persons --> {persons}")
    
    print("================")
    for p in persons:
        print(f"person --> {p}")
        
    print(f"persons size --> {len(persons)}")
    
    print("============== for ")
    for p in persons:
        print(f"person --> {p}")
        
    for i in range(4, 15):
        persons.append(Person(i, f"Daniel Sangoquiza {i}", 1000.0 + i))
        
    print("============== for ")
    for p in persons:
        print(f"person --> {p}")

if __name__ == "__main__":
    main()