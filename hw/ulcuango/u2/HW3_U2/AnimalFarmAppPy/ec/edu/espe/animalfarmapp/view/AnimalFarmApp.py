from datetime import datetime
from ec.edu.espe.animalfarmapp.model.Chicken import Chicken
from ec.edu.espe.animalfarmapp.model.Cow import Cow
from ec.edu.espe.animalfarmapp.model.FarmAnimal import FarmAnimal


def main():
  print("Josselyn's Animal Farm!")

  farmAnimal = FarmAnimal(1, "holstein", datetime.now())
  print(f"my farm animal --> {farmAnimal}")

  chicken = Chicken(True, 10, True, 2, "Palish", datetime.now())
  print(f"chicken --> {chicken}")

  cow = Cow(True, 100.5, 3, "holstein", datetime.now())
  print(f"cow --> {cow}")


if __name__ == "__main__":
  main()