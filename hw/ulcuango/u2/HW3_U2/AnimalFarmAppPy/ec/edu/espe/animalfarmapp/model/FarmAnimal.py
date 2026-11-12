from datetime import datetime


class FarmAnimal:

  def __init__(self, id: int, breed: str, bornOnDate: datetime):
    self.id = id
    self.breed = breed
    self.bornOnDate = bornOnDate

  def get_age_in_months(self) -> int:
    # TODO compute the age in months
    return 0

  def __str__(self):
    day = self.bornOnDate.day
    month = self.bornOnDate.month
    year = self.bornOnDate.year
    return f"id={self.id}, breed={self.breed}, bornOnDate={day}/{month}/{year}, "

  def get_id(self):
    return self.id

  def set_id(self, id: int):
    self.id = id

  def get_breed(self):
    return self.breed

  def set_breed(self, breed: str):
    self.breed = breed

  def get_born_on_date(self):
    return self.bornOnDate

  def set_born_on_date(self, bornOnDate: datetime):
    self.bornOnDate = bornOnDate