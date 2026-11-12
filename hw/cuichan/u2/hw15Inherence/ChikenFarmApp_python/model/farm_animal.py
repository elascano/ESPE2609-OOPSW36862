from datetime import date

class FarmAnimal:
    def __init__(self, id, breed, bornOnDate):
        self.id = id
        self.breed = breed
        self.bornOnDate = bornOnDate

    def __str__(self):
        date_str = self.bornOnDate.strftime("%d/%m/%Y") if isinstance(self.bornOnDate, date) else str(self.bornOnDate)
        return f"id={self.id}, breed={self.breed}, bornOnDate={date_str}"