class Egg:
    def __init__(self, id_egg: int, color: str):
        self.id_egg = id_egg
        self.color = color

    def __str__(self):
        return f"Egg(id={self.id_egg}, color='{self.color}')"
