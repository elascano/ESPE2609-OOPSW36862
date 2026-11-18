class Food:
    """
    Clase Food traducida de ec.edu.espe.model.Food
    @author Daniel Sangoquiza, SaborCode
    """
    def __init__(self, description: str):
        self._description = description

    @property
    def description(self) -> str:
        return self._description

    @description.setter
    def description(self, value: str):
        self._description = value

    def __str__(self) -> str:
        return self._description