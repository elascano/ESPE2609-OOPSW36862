from model.person import Person

print("======================")
print("|   Mateo Nacimba´s  |")
print("|     Collections    |")
print("======================")

things = []
person = Person(1, "jirge", 2851.57)

print("person --->", person)
print("Size of my collections at the begining is --->", len(things))

things.append(19720)
things.append("Hello OPP programmers from Mateo Nacimba")
things.append(1078.56)
things.append(person)

print("================")
print("size of my collections at this point-->", len(things))

persons: list[Person] = []
print("Size of my collections of person -->", len(persons))

persons.append(person)
persons.append(Person(2, "Leslie Cuichan", 1851.23))
persons.append(Person(3, "Dennis criollo", 3500.23))

print("Size of my collection of persons -->", len(persons))
print("persons -->", persons)
print("================")

# Equivalente al forEach con lambda
for p in map(lambda p: p, persons):
    print("person -->", p)

print("persons size -->", len(persons))
print("================ for ")
for p in persons:
    print("person -->", p)

for i in range(4, 15):
    new_person = Person(i, f"Mateo{i}", 1880.0 + i)
    persons.append(new_person)
    print("person--> ", new_person)
