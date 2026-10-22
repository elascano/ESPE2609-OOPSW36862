from model.chicken import Chicken

class ChickenCoop:
    def __init__(self, coop_id):
        self.coop_id = coop_id
        self.chickens = []

    def add_chicken(self, chicken):
        self.chickens.append(chicken)

    def get_chickens(self):
        return self.chickens

    def to_dict(self):
        return {
            "coop_id": self.coop_id,
            "chickens": [c.to_dict() for c in self.chickens]
        }

    @classmethod
    def from_dict(cls, data):
        coop = cls(data["coop_id"])
        coop.chickens = [Chicken.from_dict(c) for c in data.get("chickens", [])]
        return coop