"""
@author Mateo, Group5, @ESPE
"""
from datetime import datetime

from ec.edu.espe.animalfarmapp.model.chicken import Chicken
from ec.edu.espe.animalfarmapp.model.cow import Cow
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal
from ec.edu.espe.animalfarmapp.model.pig import Pig
from ec.edu.espe.animalfarmapp.model.sheep import Sheep


def main() -> None:
    print("Hello This is Mateo's Animal Farm!")
    print('==================================')

    farm_animal: FarmAnimal
    farm_animal = FarmAnimal(1, 'holstein', datetime.now())

    print('my farm animal --> ' + str(farm_animal))

    chicken = Chicken(True, 10, True, 2, 'Paleesh', datetime.now())
    print('chicken --> ' + str(chicken))

    cow = Cow(True, 100.5, 3, 'holstein', datetime.now())
    print('cow --> ' + str(cow))

    # Java: new Date(2019, 11, 30) -> el mes de Java empieza en 0, o sea 30 de diciembre
    pig = Pig(True, 30.23, 4, 'Mangalitsa', datetime(2019, 12, 30))
    print('pig --> ' + str(pig))

    sheep = Sheep(True, datetime(2026, 11, 20), 5, 'Merino', datetime(2014, 12, 25))
    print('sheep --> ' + str(sheep))


if __name__ == '__main__':
    main()
