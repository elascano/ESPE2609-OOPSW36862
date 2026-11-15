class Food:
    def __init__(self, description: str):
        self.description = description

    def __str__(self):
        return self.description

    def get_description(self):
        return self.description

    @property
    def description_text(self):
        return self.description

    @description_text.setter
    def description_text(self, description: str):
        self.description = description