import datetime

class Poop:
    def __init__(self, id, color, consistency):
        self.id = id
        self.color = color
        self.consistency = consistency

    def __str__(self):
        return f"Poop{{id={self.id}, color={self.color}, consistency={self.consistency}}}"

class Egg:
    def __init__(self, id, size):
        self.id = id
        self.size = size

    def __str__(self):
        return f"Egg{{id={self.id}, size={self.size}}}"

class ChickenFarmer:
    pass # Clase vacía, tal como en Java

class Chicken:
    def __init__(self, id, name, color, bornOnDate, molting):
        self.id = id
        self.name = name
        self.color = color
        self.bornOnDate = bornOnDate
        self.molting = molting
        self.ageInYears = self.computeAgeInYears()

    def doStuff(self):
        self.cluck()
        self.wander()
        self.drink(1.3)
        print(f"poop --> {self.poop()}")
        print(f"egg --> {self.layAnEgg()}")

    def cluck(self):
        print(f"The chicken {self.name} is clucking ...")

    def wander(self):
        print(f"The chicken {self.name} is wandering ...")

    def eat(self):
        print(f"The chicken {self.name} is eating ...")

    def drink(self, amountOfWater):
        print(f"The chicken {self.name} is drinking {amountOfWater} ml of water")

    def poop(self):
        return Poop(2, "green", 'M')

    def layAnEgg(self):
        return Egg(2, 'S')

    def computeAgeInYears(self):
        today = datetime.date.today()
        # Cálculo de edad
        age = today.year - self.bornOnDate.year
        # Restamos 1 si aún no ha cumplido años este año
        if today.month < self.bornOnDate.month or (today.month == self.bornOnDate.month and today.day < self.bornOnDate.day):
            age -= 1
        return age

    def __str__(self):
        return f"Chicken{{id={self.id}, name={self.name}, color={self.color}, bornOnDate={self.bornOnDate}, age={self.ageInYears}, molting={self.molting}}}"

class ChickenCoop:
    def __init__(self, chickenCoopId):
        self.chickenCoopId = chickenCoopId
        self.chickens = [None] * 10 
        self.currentIndex = 0
        self.numberOfChickens = 0

    def add(self, chicken):
        if self.numberOfChickens < len(self.chickens):
            self.chickens[self.numberOfChickens] = chicken
            self.numberOfChickens += 1
            print(f"The chicken {chicken.name} was added to the coop")
        else:
            print("The coop is full, we can not add any more chickens")

    def remove(self, chickenId):
        position = -1
        
        for i in range(self.numberOfChickens):
            if self.chickens[i].id == chickenId:
                position = i
                break
                
        if position == -1:
            print(f"There is no chicken with id {chickenId}")
            return
            
        removedName = self.chickens[position].name
        
        # Desplazar elementos a la izquierda para no dejar huecos
        for i in range(position, self.numberOfChickens - 1):
            self.chickens[i] = self.chickens[i + 1]
            
        self.chickens[self.numberOfChickens - 1] = None
        self.numberOfChickens -= 1
        print(f"The chicken {removedName} was removed from the coop")

    def resetIteration(self):
        self.currentIndex = 0

    def hasNext(self):
        return self.currentIndex < self.numberOfChickens

    def next(self):
        if self.hasNext():
            chicken = self.chickens[self.currentIndex]
            self.currentIndex += 1
            return chicken
        return None

    def __str__(self):
        chickens_str = "[" + ", ".join([str(c) if c is not None else "None" for c in self.chickens]) + "]"
        return f"ChickenCoop{{chickenCoopId={self.chickenCoopId}, chickens={chickens_str}}}"




class ChickenFarmApp:
    nextChickenId = 1
    nextEggId = 1
    nextPoopId = 1
    nextCoopId = 1

    @classmethod
    def readChicken(cls):
        id_chicken = cls.nextChickenId
        cls.nextChickenId += 1
        print(f"Chicken id: {id_chicken}")
        
        name = input("Name: ")
        color = input("Color: ")
        year = int(input("Year of birth (example 2024): "))
        month = int(input("Month of birth (1-12): "))
        day = int(input("Day of birth: "))
        
        molting_input = input("Is it molting? (true/false): ").strip().lower()
        molting = molting_input in ['true', 'yes', 't', 'y', '1']
        
        bornOnDate = datetime.date(year, month, day)
        return Chicken(id_chicken, name, color, bornOnDate, molting)

    @staticmethod
    def showChickens(coop, do_stuff):
        if coop.numberOfChickens == 0:
            print("The coop is empty")
            return
            
        coop.resetIteration()
        while coop.hasNext():
            current = coop.next()
            print(f"chickens --> {current}")
            print(f"Chicken's age: {current.computeAgeInYears()}")
            if do_stuff:
                current.doStuff()

    @classmethod
    def main(cls):
        print("Daniel Sangoquiza Chicken Farm App")
        print("==============================")
        
        coopId = cls.nextCoopId
        cls.nextCoopId += 1
        coop = ChickenCoop(coopId)
        print(f"Chicken coop id: {coopId}")
        
        # Bucle do-while para validar cantidad a añadir
        while True:
            quantityToAdd = int(input("\nHow many chickens do you want to add? (1-10): "))
            if 1 <= quantityToAdd <= 10:
                break
            print("Invalid quantity, it must be between 1 and 10")
            
        for i in range(quantityToAdd):
            print(f"\n--- Chicken {i + 1} ---")
            chicken = cls.readChicken()
            coop.add(chicken)
            
        print("\n--- Egg ---")
        eggId = cls.nextEggId
        cls.nextEggId += 1
        print(f"Egg id: {eggId}")
        eggSize = input("Egg size (S/M/L): ").strip().upper()[0]
        egg = Egg(eggId, eggSize)
        
        print("\n--- Poop ---")
        poopId = cls.nextPoopId
        cls.nextPoopId += 1
        print(f"Poop id: {poopId}")
        poopColor = input("Poop color: ")
        poopSize = input("Poop size (S/M/L): ").strip().upper()[0]
        poop = Poop(poopId, poopColor, poopSize)
        
        print(f"\negg --> {egg}")
        print(f"poop --> {poop}")
        
        print("\n=== Chickens in the coop ===")
        cls.showChickens(coop, True)
        print(f"number of chickens --> {coop.numberOfChickens}")
        
        # Bucle do-while para validar cantidad a remover
        while True:
            quantityToRemove = int(input(f"\nHow many chickens do you want to remove? (0-{coop.numberOfChickens}): "))
            if 0 <= quantityToRemove <= coop.numberOfChickens:
                break
            print(f"Invalid quantity, it must be between 0 and {coop.numberOfChickens}")
            
        for i in range(quantityToRemove):
            idToRemove = int(input("Enter the id of the chicken to remove: "))
            coop.remove(idToRemove)
            
        print("\n=== Chickens in the coop after removing ===")
        cls.showChickens(coop, False)
        print(f"number of chickens --> {coop.numberOfChickens}")

if __name__ == "__main__":
    ChickenFarmApp.main()