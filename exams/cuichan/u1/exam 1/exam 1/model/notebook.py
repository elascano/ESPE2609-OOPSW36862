class Notebook:
    def __init__(self, id, brand, ram_gb, price):
        self.id = id
        self.brand = brand
        self.ram_gb = ram_gb
        self.price = price

    def to_dict(self):
        """Convert the object to a dictionary so it can be written as JSON."""
        return {
            "id": self.id,
            "brand": self.brand,
            "ram_gb": self.ram_gb,
            "price": self.price,
        }

    @staticmethod
    def from_dict(data):
        """Build a Notebook object from a dictionary read from the JSON file."""
        return Notebook(data["id"], data["brand"], data["ram_gb"], data["price"])

    def __str__(self):
        return f"[{self.id}] {self.brand} - {self.ram_gb} GB RAM - ${self.price}"