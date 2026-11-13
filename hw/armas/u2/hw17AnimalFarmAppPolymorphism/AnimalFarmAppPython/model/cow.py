from datetime import date

from model.farm_animal import FarmAnimal

# Demian Armas, Logical Lords, @ESPE

class Cow(FarmAnimal):
    def __init__(
        self,
        animal_id: int,
        breed: str,
        born_on_date: date,
        is_producing_milk: bool,
        liters_of_milk_per_week: float,
    ):
        super().__init__(animal_id, breed, born_on_date)
        self.is_producing_milk = is_producing_milk
        self.liters_of_milk_per_week = liters_of_milk_per_week

    def milk(self) -> str:
        return "The cow is being milked."

    def __str__(self) -> str:
        return (
            f"{super().__str__()}, Producing milk: {self.is_producing_milk}, "
            f"Liters of milk per week: {self.liters_of_milk_per_week}"
        )
