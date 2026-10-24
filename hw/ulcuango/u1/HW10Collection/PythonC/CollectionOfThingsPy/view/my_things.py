from model.person import Person

class MyThings:
    """
    @author Josselyn Ulcuango, CodeLex, @ESPE
    """
    @staticmethod
    def main():
        print("===========================")
        print("|    Josselyn ULcuango    |")
        print("|        Collections      |")
        print("===========================")
        
        things = []
        person = Person(1, "Jorge", 2851.27)
        
        print("person -->", person)
        print("Size of my collections at the beginning is -->", len(things))
        
        things.append(19720)
        things.append("Hello OOP programmers from Josselyn")
        things.append(4878.56)
        things.append(person)
        
        print("=================================")
        print("Size of my collections at this point-->", len(things))
        print("things -->", things)
        
        try:
            things.remove(4078.56)
        except ValueError:
            pass 
        
        print("===================")
        print("Size of my collections at this point-->", len(things))
        
        persons = []
        print("Size of my collections of persons -->", len(persons))

        print("==============")
        persons.append(person)
        persons.append(Person(2, "Leslie Cuichan", 1851.25))
        persons.append(Person(3, "Dennis Criollo", 3500.37))
        print("Size of my collections of persons -->", len(persons))
        print("persons -->", persons)
        print("==============")
        
        for p in persons:
            print("person -->", p)
            
        print("persons size-->", len(persons))
        
        print("============== for")
        for p in persons:
            print("person -->", p)
            
        for i in range(4, 15):
            persons.append(Person(i, f"Josselyn{i}", 100.0 + i))
            print("============== for ")
            for p in persons:
                print("person -->", p)

if __name__ == "__main__":
    MyThings.main()