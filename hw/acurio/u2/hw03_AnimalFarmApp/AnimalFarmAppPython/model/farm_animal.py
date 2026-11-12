class FarmAnimal:
    def __init__(self, id, breed, born_on_date, is_meat):
        self._id = id
        self._breed = breed
        self._born_on_date = born_on_date
        self._is_meat = is_meat

    def __str__(self):
        return (
            f"FarmAnimal{{id={self._id}, "
            f"breed={self._breed}, "
            f"isMeat={self._is_meat}, "
            f"bornOnDate={self._born_on_date:%d/%m/%Y}}}, "
        )

    def get_id(self):
        return self._id

    def set_id(self, id):
        self._id = id

    def get_breed(self):
        return self._breed

    def set_breed(self, breed):
        self._breed = breed

    def get_born_on_date(self):
        return self._born_on_date

    def set_born_on_date(self, born_on_date):
        self._born_on_date = born_on_date

    def get_is_meat(self):
        return self._is_meat

    def set_is_meat(self, is_meat):
        self._is_meat = is_meat
