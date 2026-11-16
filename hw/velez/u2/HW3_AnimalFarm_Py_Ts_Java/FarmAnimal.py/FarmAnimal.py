from datetime import date

class FarmAnimal:
    def __init__(self, id: int, breed: str, born_on_date: date):
        self.id = id
        self.breed = breed
        self.born_on_date = born_on_date

    def get_age_in_months(self) -> int:
        return 0

    def __str__(self):
        return f"id={self.id}, breed='{self.breed}', bornOnDate={self.born_on_date}"

class Chicken(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on_date: date, is_molting: bool, number_of_laid_egg_per_week: int, is_meat: bool):
        super().__init__(id, breed, born_on_date)
        self.is_molting = is_molting
        self.number_of_laid_egg_per_week = number_of_laid_egg_per_week
        self.is_meat = is_meat

    def __str__(self):
        return f"Chicken{{{super().__str__()}, isMolting={self.is_molting}, numberOfLaidEggPerWeek={self.number_of_laid_egg_per_week}, isMeat={self.is_meat}}}"

class Cow(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on_date: date, is_producing_milk: bool):
        super().__init__(id, breed, born_on_date)
        self.is_producing_milk = is_producing_milk

    def __str__(self):
        return f"Cow{{{super().__str__()}, isProducingMilk={self.is_producing_milk}}}"

class Pig(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on_date: date):
        super().__init__(id, breed, born_on_date)

    def __str__(self):
        return f"Pig{{{super().__str__()}}}"

class Sheep(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on_date: date, last_sheering: date):
        super().__init__(id, breed, born_on_date)
        self.last_sheering = last_sheering

    def __str__(self):
        return f"Sheep{{{super().__str__()}, lastSheering={self.last_sheering}}}"


if __name__ == "__main__":
    print("Yandry Farm Animal")
    print("==================")
    
    farm_animal = FarmAnimal(1, "Holstein", date.today())
    print(f"My farm Animal is --> FarmAnimal{{{farm_animal}}}")
    
    chicken = Chicken(2, "Cresta Roja", date(2022, 10, 10), True, 10, False)
    print(f"Chicken --> {chicken}")
    
    cow = Cow(3, "Nelor", date(2021, 5, 20), True)
    print(f"Cow --> {cow}")
    
    pig = Pig(4, "Pietran", date(2023, 1, 15))
    print(f"Pig --> {pig}")
    
    sheep = Sheep(5, "Merino", date(2020, 8, 12), date(2023, 10, 5))
    print(f"Sheep --> {sheep}")