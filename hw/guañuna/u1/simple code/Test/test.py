# ==========================================
# 1. DEFINICIÓN DE LA CLASE (El molde)
# ==========================================
class Function2:
    
    # ==========================================
    # 2. CONSTRUCTOR (__init__)
    # ==========================================
    # 'self' es el equivalente a 'this' en Java.
    # Los valores por defecto (= "") simulan el constructor vacío.
    def __init__(self, nombre="", edad=0):
        # ATRIBUTOS: Se definen con self.atributo
        self.nombre = nombre
        self.edad = edad

    # ==========================================
    # 3. MÉTODO __str__ (Equivalente a toString)
    # ==========================================
    # Se ejecuta automáticamente cuando hacemos print(objeto)
    def __str__(self):
        return f"Function2{{nombre={self.nombre}, edad={self.edad}}}"

    # ==========================================
    # 4. GETTERS Y SETTERS
    # ==========================================
    # En Python no son obligatorios (se puede acceder directo con obj.nombre),
    # pero los ponemos para mantener la lógica de POO estricta.
    
    def get_nombre(self):
        return self.nombre

    def set_nombre(self, nombre):
        self.nombre = nombre

    def get_edad(self):
        return self.edad

    def set_edad(self, edad):
        self.edad = edad


# ==========================================
# 5. MAIN (Se ejecuta directamente)
# ==========================================
if __name__ == "__main__":
    print("=== CREANDO OBJETOS ===")

    # Crear objeto CON datos (no se usa 'new')
    obj1 = Function2("Cristopher", 22)
    
    # Crear objeto VACÍO (usa los valores por defecto del constructor)
    obj2 = Function2()
    
    # Asignar datos usando SETTERS
    obj2.set_nombre("Ana")
    obj2.set_edad(20)

    print("\n=== MOSTRANDO OBJETOS ===")
    
    # print llama automáticamente al método __str__
    print(f"Objeto 1: {obj1}")  
    print(f"Objeto 2: {obj2}")
    
    # Usar GETTERS
    print(f"\nNombre del Objeto 1 (usando getter): {obj1.get_nombre()}")
    print(f"Edad del Objeto 2 (usando getter): {obj2.get_edad()}")