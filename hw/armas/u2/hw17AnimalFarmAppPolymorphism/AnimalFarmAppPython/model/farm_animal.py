from datetime import date

# Demian Armas, Logical Lords, @ESPE

class FarmAnimal:
    def __init__(self, animal_id: int, breed: str, born_on_date: date):
        self.animal_id = animal_id
        self.breed = breed
        self.born_on_date = born_on_date

    def get_age_in_months(self) -> int:
        today = date.today()
        months = (today.year - self.born_on_date.year) * 12
        months += today.month - self.born_on_date.month
        if today.day < self.born_on_date.day:
            months -= 1
        return months

    def __str__(self) -> str:
        return (
            f"ID: {self.animal_id}, Breed: {self.breed}, "
            f"Birth date: {self.born_on_date:%d/%m/%Y}"
        )
