class Person:

  def __init__(self, id: int, name: str, salary: float):
    self._id = id
    self._name = name
    self._salary = salary

  @property
  def id(self):
    return self._id

  @id.setter
  def id(self, id: int):
    self._id = id

  @property
  def name(self):
    return self._name

  @name.setter
  def name(self, name: str):
    self._name = name

  @property
  def salary(self):
    return self._salary

  @salary.setter
  def salary(self, salary: float):
    self._salary = salary

  def __str__(self):
    return f"Person{{id={self._id}, name={self._name}, salary={self._salary}}}"


def main():
  print("===================")
  print("Denis Paucar")
  print("Collection")
  print("===================")

  things = []

  person = Person(1, "Jorge", 2851.27)

  print(f"person----{len(things)}")
  print(f"Size of my collections at the begining is-----{len(things)}")

  things.append(19720)
  things.append("Hello OOP programmers from Anghelo")
  things.append(1978.56)
  things.append(person)

  print("===================")
  print(f"Size of my collections at this point --{len(things)}")

  persons = []
  print(f"Size of my collections at this point --{len(things)}")

  print("===================")

  persons.append(person)
  persons.append(Person(2, "Leslie Cuichan", 1851.25))
  persons.append(Person(3, "Dennis Criollo", 3500.37))

  print(f"Size of my collections of persons --> {len(persons)}")

  # Representación de la lista de personas
  print(f"persons --> {[str(p) for p in persons]}")

  print("============ for each del array list")
  for p in persons:
    print(f"person --> {p}")

  print(f"persons size --> {len(persons)}")

  print("============ for ")
  for p in persons:
    print(f"person --> {p}")

  for i in range(4, 15):
    persons.append(Person(i, f"Daniel Sangoquiza {i}", 1000.0 + i))

  print("============== for ")
  for p in persons:
    print(f"person --> {p}")


if __name__ == "__main__":
  main()