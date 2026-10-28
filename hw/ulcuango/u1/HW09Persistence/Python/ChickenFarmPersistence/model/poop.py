class Poop:
    def __init__(self, size, consistency):
        self.size = size
        self.consistency = consistency

    def to_dict(self):
        return {
            "size": self.size,
            "consistency": self.consistency
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            data["size"],
            data["consistency"]
        )

    def __str__(self):
        return f"Poop [Size: {self.size}, Consistency: {self.consistency}]"