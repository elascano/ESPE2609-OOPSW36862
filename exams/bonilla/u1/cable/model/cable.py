class Cable:
    def __init__(self, cable_id: int, cable_type: str, length: float, price: float):
        self.id = cable_id
        self.type = cable_type
        self.length = length
        self.price = price

    def to_dict(self) -> dict:
        return {
            "id": self.id,
            "type": self.type,
            "length": self.length,
            "price": self.price
        }

    @staticmethod
    def from_dict(data: dict):
        return Cable(
            cable_id=data["id"],
            cable_type=data["type"],
            length=data["length"],
            price=data["price"]
        )

    def __str__(self) -> str:
        return f"ID: {self.id} | Type: {self.type} | Length: {self.length}m | Price: ${self.price:.2f}"