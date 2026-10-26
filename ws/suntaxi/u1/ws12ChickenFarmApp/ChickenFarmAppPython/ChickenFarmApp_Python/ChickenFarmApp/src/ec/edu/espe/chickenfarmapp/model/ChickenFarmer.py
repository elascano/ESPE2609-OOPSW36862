class ChickenFarmer:
    def __init__(self, id_farmer: int, name: str, level: str):
        self.id_farmer = id_farmer
        self.name = name
        self.level = level

    def __str__(self):
        return f"ChickenFarmer(id={self.id_farmer}, name='{self.name}', level='{self.level}')"
