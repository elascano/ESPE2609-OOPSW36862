from datetime import datetime

from ec.edu.espe.animalfarmapp.model.chicken import Chicken
from ec.edu.espe.animalfarmapp.model.cow import Cow
from ec.edu.espe.animalfarmapp.model.farm_animal import FarmAnimal


def main() -> None:
    print("Hello This is Mateo's Animal Farm!")

    farm_animal = FarmAnimal(1, 'holstein', datetime.now())
    print('my farm animal --> ' + str(farm_animal))

    chicken = Chicken(True, 10, True, 2, 'Paleesh', datetime.now())
    print('chicken --> ' + str(chicken))

    cow = Cow(True, 100.5, 3, 'holstein', datetime.now())
    print('cow --> ' + str(cow))


if __name__ == '__main__':
    main()
