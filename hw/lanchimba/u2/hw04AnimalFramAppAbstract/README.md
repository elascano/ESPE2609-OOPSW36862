# hw04AnimalFramAppAbstract

Evolución del sistema **Animal Farm App** aplicando **Abstracción (Clases Abstractas y Métodos Abstractos)**, **Herencia** y **Polimorfismo** en **Java**, **Python** y **TypeScript**, siguiendo los estándares de Programación Orientada a Objetos de la ESPE.

## Cómo se Aplicó la Abstracción

- **`FarmAnimal` como clase abstracta:**
  - **Java:** `public abstract class FarmAnimal` con métodos `public abstract`.
  - **Python:** `class FarmAnimal(ABC)` utilizando el módulo estándar `abc` y el decorador `@abstractmethod`.
  - **TypeScript:** `export abstract class FarmAnimal` con métodos `public abstract`.
- **Prohibición de instanciación directa:** Al ser un concepto general (un animal genérico de granja), `FarmAnimal` no puede instanciarse directamente; solo pueden crearse instancias de sus subclases concretas (`Chicken`, `Cow`, `Pig`, `Sheep`).
- **Contrato obligatorio de comportamiento:** Toda subclase concreta está obligada a sobrescribir e implementar los métodos abstractos:
  - `feed()`: define la alimentación específica de cada especie.
  - `makeSound()` (`make_sound()` en Python): retorna el sonido característico del animal.
  - `produce()`: modela la producción propia de cada especie (huevos si la gallina no está mudando, leche si la vaca está activa, carne para el cerdo y lana para la oveja).
- **Polimorfismo:** La vista `AnimalFarmApp` almacena todas las instancias concretas en una única colección tipada como `FarmAnimal` e invoca uniformemente `getAgeInMonths()`, `feed()`, `makeSound()` y `produce()`.

## Estructura del Proyecto

```text
hw04AnimalFramAppAbstract/
├── AnimalFarmApp/                  # Java
│   └── src/ec/edu/espe/animalfarmapp/
│       ├── model/
│       │   ├── FarmAnimal.java     # Clase abstracta
│       │   ├── Chicken.java
│       │   ├── Cow.java
│       │   ├── Pig.java
│       │   └── Sheep.java
│       └── view/
│           └── AnimalFarmApp.java
├── AnimalFarmAppPython/            # Python
│   ├── model/
│   │   ├── __init__.py
│   │   ├── farm_animal.py          # Clase abstracta (ABC)
│   │   ├── chicken.py
│   │   ├── cow.py
│   │   ├── pig.py
│   │   └── sheep.py
│   └── animal_farm_app.py
├── AnimalFarmAppTypeScript/        # TypeScript
│   ├── model/
│   │   ├── FarmAnimal.ts           # Clase abstracta
│   │   ├── Chicken.ts
│   │   ├── Cow.ts
│   │   ├── Pig.ts
│   │   └── Sheep.ts
│   ├── AnimalFarmApp.ts
│   ├── package.json
│   └── tsconfig.json
├── .gitignore
└── README.md
```

---

## Instrucciones de Ejecución

### 1. Java

```bash
cd AnimalFarmApp
javac -d build/classes src/ec/edu/espe/animalfarmapp/model/*.java src/ec/edu/espe/animalfarmapp/view/*.java
java -cp build/classes ec.edu.espe.animalfarmapp.view.AnimalFarmApp
```

### 2. Python

```bash
cd AnimalFarmAppPython
python animal_farm_app.py
```

### 3. TypeScript

```bash
cd AnimalFarmAppTypeScript
npm install        # Solo si no tienes TypeScript (tsc) instalado globalmente
npm run build      # O directamente: tsc
npm start          # O directamente: node dist/AnimalFarmApp.js
```
