class Function2:
    def __init__(self, nombre="", edad=0):
        self.nombre = nombre
        self.edad = edad

    def __str__(self):
        return f"Function2{{nombre={self.nombre}, edad={self.edad}}}"

    def get_nombre(self): return self.nombre
    def set_nombre(self, nombre): self.nombre = nombre
    def get_edad(self): return self.edad
    def set_edad(self, edad): self.edad = edad


def main():
    # AQUÍ ESTÁ EL CAMBIO: Creamos una lista vacía
    lista_objetos = [] 

    while True:
        print("\n=== MENÚ PYTHON ===")
        print("1. Crear Objeto")
        print("2. Mostrar Todos los Objetos")
        print("3. Salir")
        
        opcion = input("Seleccione: ")

        if opcion == "1":
            nombre = input("Ingrese nombre: ")
            try:
                edad = int(input("Ingrese edad: "))
                # Creamos el objeto y lo añadimos a la lista con append()
                nuevo_objeto = Function2(nombre, edad)
                lista_objetos.append(nuevo_objeto) 
                print("¡Objeto agregado a la lista!")
            except ValueError:
                print("Error: La edad debe ser un número entero.")
                
        elif opcion == "2":
            if len(lista_objetos) == 0:
                print("No hay objetos creados aún.")
            else:
                print("--- LISTA DE OBJETOS ---")
                # Recorremos la lista
                for obj in lista_objetos:
                    print(obj) # Llama a __str__
                
        elif opcion == "3":
            print("Saliendo...")
            break
        else:
            print("Opción inválida.")

if __name__ == "__main__":
    main()