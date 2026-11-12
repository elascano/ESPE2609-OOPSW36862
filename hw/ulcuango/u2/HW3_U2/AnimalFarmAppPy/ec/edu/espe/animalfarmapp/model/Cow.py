from datetime import datetime
from ec.edu.espe.animalfarmapp.model.FarmAnimal import FarmAnimal

class Cow(FarmAnimal):

  def __init__(
      self,
      isProducingMilk: bool,
      litersOfMilkPerWeek: float,
      id: int,
      breed: str,
      bornOnDate: datetime,
  ):
    super().__init__(id, breed, bornOnDate)
    self.isProducingMilk = isProducingMilk
    self.litersOfMilkPerWeek = litersOfMilkPerWeek

  def milk(self):
    print("Milking the cow ")

  def __str__(self):
    prod_milk = str(self.isProducingMilk).lower()
    return (
        f"Cow{{{super().__str__()}isProducingMilk={prod_milk}, "
        f"litersOfMilkPerWeek={self.litersOfMilkPerWeek}}}"
    )

  def is_is_producing_milk(self):
    return self.isProducingMilk

  def set_is_producing_milk(self, isProducingMilk: bool):
    self.isProducingMilk = isProducingMilk

  def get_liters_of_milk_per_week(self):
    return self.litersOfMilkPerWeek

  def set_liters_of_milk_per_week(self, litersOfMilkPerWeek: float):
    self.litersOfMilkPerWeek = litersOfMilkPerWeek