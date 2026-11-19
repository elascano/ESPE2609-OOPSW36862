class Location:
    def __init__(self, id: int, description: str):
        self.id = id
        self.description = description

    @property
    def location_id(self):
        return self.id

    @location_id.setter
    def location_id(self, id: int):
        self.id = id

    @property
    def description_text(self):
        return self.description

    @description_text.setter
    def description_text(self, description: str):
        self.description = description

    def __str__(self):
        return f"Location{{id={self.id}, description={self.description}}}"