"""Model package for the ESPE Animal Farm application (Polymorphism)."""

from .i_farm_animal import IFarmAnimal
from .farm_animal import FarmAnimal
from .chicken import Chicken
from .cow import Cow
from .pig import Pig
from .sheep import Sheep

__all__ = ["IFarmAnimal", "FarmAnimal", "Chicken", "Cow", "Pig", "Sheep"]
