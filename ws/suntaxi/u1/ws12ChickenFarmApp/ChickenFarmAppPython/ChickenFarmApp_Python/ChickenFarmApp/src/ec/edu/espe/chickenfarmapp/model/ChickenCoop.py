from typing import List
from ec.edu.espe.chickenfarmapp.model.Chicken import Chicken
from ec.edu.espe.chickenfarmapp.model.Egg import Egg
from ec.edu.espe.chickenfarmapp.model.Poop import Poop

class ChickenCoop:
    def __init__(self, id_coop: int, chickens: List[Chicken] = None, eggs: List[Egg] = None, poops: List[Poop] = None):
        self.id_coop = id_coop
        self.chickens = chickens if chickens is not None else []
        self.eggs = eggs if eggs is not None else []
        self.poops = poops if poops is not None else []

    def add_chicken(self, chicken: Chicken):
        self.chickens.append(chicken)

    def __str__(self):
        return (f"ChickenCoop(id={self.id_coop}, "
                f"chickens={len(self.chickens)}, eggs={len(self.eggs)}, poops={len(self.poops)})")
