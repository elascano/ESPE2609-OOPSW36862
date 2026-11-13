
class Speaker:

    def __init__(self, id, brand, power, price):
        self.id = id
        self.brand = brand
        self.power = power
        self.price = price

    def to_dict(self):
        return {
            "id": self.id,
            "brand": self.brand,
            "power": self.power,
            "price": self.price
        }

    def display_info(self):
        print(f"ID: {self.id}")
        print(f"Brand: {self.brand}")
        print(f"Power: {self.power} W")
        print(f"Price: ${self.price:.2f}")
        print("------------------------")
