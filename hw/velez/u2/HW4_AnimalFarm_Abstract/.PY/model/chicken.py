from model.farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, is_molting, laid_eggs_per_week, animal_id, breed, born_on_date):
        super().__init__(animal_id, breed, born_on_date, False)
        self._is_molting = is_molting
        self._laid_eggs_per_week = laid_eggs_per_week

    def lay_an_egg(self):
        print("chicken is laying an egg")

    def __str__(self):
        return f"Chicken{{{super().__str__()}, isMolting={self._is_molting}, eggsPerWeek={self._laid_eggs_per_week}}}"