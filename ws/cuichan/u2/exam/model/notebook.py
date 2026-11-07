class Notebook:
    def __init__(self, id: int, brand: str, ram_gb: int, price: float):
        self.id = id
        self.brand = brand
        self.ram_gb = ram_gb
        self.price = price

    def to_dict(self) -> dict:
        return {
            "id": self.id,
            "brand": self.brand,
            "ram_gb": self.ram_gb,
            "price": self.price
        }

    @staticmethod
    def from_dict(data: dict):
        return Notebook(
            id=data["id"],
            brand=data["brand"],
            ram_gb=data["ram_gb"],
            price=data["price"]
        )