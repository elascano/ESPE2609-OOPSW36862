from Chicken import Chicken
from datetime import date
class ChickenCoop:
    def __init__(self, chickenCoopId=1, chickens=None):
        self.chickenCoopId = chickenCoopId
        self.chickens = chickens if chickens is not None else [None] * 100
        self.currentIndex = 0

    def add(self):
        position = -1
        for i in range(len(self.chickens)):
            if self.chickens[i] is None:
                position = i
                break

        if position == -1:
            print("The chicken coop is full.")
            return

        try:
            id = int(input("Enter chicken ID: "))
            name = input("Enter chicken name: ")
            color = input("Enter chicken color: ")

            bornOnDate = date.fromisoformat(input("Enter birth date (YYYY-MM-DD): "))

            moltingInput = input("Is the chicken molting? (true/false): ").lower()

            if moltingInput not in ("true", "false"):
                print("Please enter true or false.")
                return

            molting = moltingInput == "true"

            for chicken in self.chickens:
                if chicken is not None and chicken.id == id:
                    print("A chicken with this ID already exists.")
                    return

            chicken = Chicken(id, name, color, bornOnDate, molting)
            self.chickens[position] = chicken
            print("Chicken added successfully!")

        except ValueError:
            print("Invalid input. Check the ID and date format.")
            
    def remove(self):
        try:
            chickenId = int(input("Enter chicken ID to remove: "))
            for i in range(len(self.chickens)):
                chicken = self.chickens[i]

                if chicken is not None and chicken.id == chickenId:
                    print("Removing chicken:", chicken.name)
                    self.chickens[i] = None
                    print("Chicken removed successfully!")
                    return
            print("Chicken not found.")
        except ValueError:
            print("The chicken ID must be a number.")
            
    def saveChickenCoop(self):
        try:
            with open("chickenCoop.txt", "w", encoding="utf-8") as file:
                file.write(str(self))
            print("Chicken Coop saved successfully!")

        except OSError as error:
            print("Error saving Chicken Coop:", error)
            
    def showChickenCoop(self):
        print("\n===== CHICKEN COOP =====")
        print("Coop ID:", self.chickenCoopId)
        found = False
        for chicken in self.chickens:
            if chicken is not None:
                print(chicken)
                found = True
        if not found:
            print("There are no chickens in the coop.")
            
    def resetIteration(self):
        self.currentIndex = 0
        
    def next(self):
        while self.currentIndex < len(self.chickens):
            chicken = self.chickens[self.currentIndex]
            self.currentIndex += 1
            if chicken is not None:
                return chicken
        return None
    
    def __str__(self):
        result = (f"ChickenCoop{{chickenCoopId={self.chickenCoopId}, "f"chickens=[\n")
        for chicken in self.chickens:
            if chicken is not None:
                result += f"    {chicken}\n"
        result += "]}"
        return result  