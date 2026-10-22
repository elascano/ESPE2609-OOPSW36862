class Egg:
    def __init__(self, weight, color):
        self.weight = weight
        self.color = color

    def to_dict(self):
        return {
            "weight": self.weight,
            "color": self.color
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            data["weight"],
            data["color"]
        )

    def __str__(self):
        return f"Egg [Weight: {self.weight}, Color: {self.color}]"