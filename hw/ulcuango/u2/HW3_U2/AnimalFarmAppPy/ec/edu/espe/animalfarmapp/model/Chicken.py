from datetime import datetime
from ec.edu.espe.animalfarmapp.model.FarmAnimal import FarmAnimal

class Chicken(FarmAnimal):

  def __init__(
      self,
      isMolting: bool,
      numberOfLaidEggsInAMonth: int,
      isMeat: bool,
      id: int,
      breed: str,
      bornOnDate: datetime,
  ):
    super().__init__(id, breed, bornOnDate)
    self.isMolting = isMolting
    self.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth
    self.isMeat = isMeat

  def __str__(self):
    molting = str(self.isMolting).lower()
    meat = str(self.isMeat).lower()
    return (
        f"Chicken{{{super().__str__()}isMolting={molting}, "
        f"numberOfLaidEggsInAMonth={self.numberOfLaidEggsInAMonth}, "
        f"isMeat={meat}}}"
    )

  def is_is_molting(self):
    return self.isMolting

  def set_is_molting(self, isMolting: bool):
    self.isMolting = isMolting

  def get_number_of_laid_eggs_in_a_month(self):
    return self.numberOfLaidEggsInAMonth

  def set_number_of_laid_eggs_in_a_month(
      self, numberOfLaidEggsInAMonth: int
  ):
    self.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth

  def is_is_meat(self):
    return self.isMeat

  def set_is_meat(self, isMeat: bool):
    self.isMeat = isMeat