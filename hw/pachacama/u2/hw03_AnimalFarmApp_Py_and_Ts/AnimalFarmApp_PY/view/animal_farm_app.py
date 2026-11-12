"""
@author Andres Pachacama, Byte Works, ESPE
"""
from datetime import date

from model.chicken import Chicken
from model.cow import Cow
from model.farm_animal import FarmAnimal


def main():
    print("Andres Farm Animal")
    print("==================")

    farm_animal = FarmAnimal(1, "Holstein", date.today())
    print("My Farm Animal is  -->" + str(farm_animal))

    # en Python los meses empiezan en 1: date(anio, mes, dia)
    chicken = Chicken(True, 10, False, 2, "palish", date(2022, 11, 10))
    print("chicken --> " + str(chicken))

    cow = Cow(25.5, False, 650.0, True, 3, "Holstein", date(2021, 5, 15))
    print("cow --> " + str(cow))
