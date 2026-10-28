class Person:
    def __init__(self, person_id, name, salary):
        self.id = person_id
        self.name = name
        self.salary = salary

    def __str__(self):
        return f"Person{{id={self.id}, name={self.name}, salary={self.salary}}}"

def main():
    print("=================")
    print("Anghelo Suntaxi")
    print("Collection")
    print("=================")
    
    things = [42, "Hola Anghelo", 3.1416, True]
    
    print("person----0")
    print("Size of my collections at the begining is-----0")
    print("=================")
    print("Size of my collections at this point --4")
    print("Size of my collections at this point --4")
    print("=================")
    
    persons = [
        Person(1, "Jorge", 2851.27),
        Person(2, "Leslie Cuichan", 1851.25),
        Person(3, "Dennis Criollo", 3500.37)
    ]
    
    print(f"Size of my collections of persons --> {len(persons)}")
    print(f"persons --> {[str(p) for p in persons]}")
    
    print("====== for each del array list")
    for p in persons:
        print(f"person --> {p}")
        
    print(f"persons size ---> {len(persons)}")
    
    print("========== for")
    for p in persons:
        print(f"person --> {p}")
        
    print("========== for")
    for p in persons:
        print(f"person --> {p}")
        
    for i in range(4, 15):
        persons.append(Person(i, f"Anghelo Suntaxi {i}", 1000.0 + i))
        
    print("========== for")
    for p in persons:
        print(f"person --> {p}")

if __name__ == "__main__":
    main()