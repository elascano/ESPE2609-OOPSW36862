"""
@author Mateo, Group5, @ESPE
"""
from datetime import datetime

from ec.edu.espe.animalfarmapp.model.chicken import Chicken
from ec.edu.espe.animalfarmapp.model.cow import Cow
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal
from ec.edu.espe.animalfarmapp.model.pig import Pig
from ec.edu.espe.animalfarmapp.model.sheep import Sheep


def java_class_name(obj: object) -> str:
    """Imita getClass() de Java: 'class paquete.Clase'."""
    t = type(obj)
    package = t.__module__.rsplit('.', 1)[0]
    return f'class {package}.{t.__name__}'


def main() -> None:
    print("Mateo's Farm Animal")
    print('=======================')

    # Polimorfismo: variable de tipo padre, objeto de tipo hijo
    farm_animal: FarmAnimal
    farm_animal = Chicken(False, 10, 2, 'palish', datetime(2022, 11, 10))
    print('chicken --->' + str(farm_animal))
    print('chicken is a --->' + java_class_name(farm_animal))
    print('chicken is a --->' + type(farm_animal).__name__)

    chicken = Chicken(False, 10, 2, 'palish', datetime(2022, 11, 10))
    print('chicken --->' + str(chicken))
    print('chicken is a --->' + type(chicken).__name__)

    cow = Cow(True, 23.5, 3, 'Holstein', datetime(2025, 1, 23), False)
    print('cow --->' + str(cow))
    print('cow is a --->' + type(cow).__name__)

    pig = Pig(True, 30.23, 4, 'Mangalitsa', datetime(2019, 12, 30), False)
    print('pig --->' + str(pig))
    print('pig is a --->' + type(pig).__name__)

    sheep = Sheep(True, datetime(2025, 12, 24), 5, 'Manchega', datetime(2024, 10, 27), True)
    print('sheep --->' + str(sheep))
    print('sheep is a --->' + type(sheep).__name__)

    chicken.lay_an_egg()
    cow.milk()


if __name__ == '__main__':
    main()
