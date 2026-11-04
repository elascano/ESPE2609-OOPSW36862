class Goalkeeper:
    def __init__(self, id, nombre, edad, equipo):
        self.id = id
        self.nombre = nombre
        self.edad = edad
        self.equipo = equipo

    def mostrar(self):
        print("ID:", self.id)
        print("Nombre:", self.nombre)
        print("Edad:", self.edad)
        print("Equipo:", self.equipo)
        print("--------------------")
