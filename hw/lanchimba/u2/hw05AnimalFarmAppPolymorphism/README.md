# hw05AnimalFarmAppPolymorphism

Implementación del sistema **Animal Farm App** aplicando **Polimorfismo completo** —**Sobrescritura (Overriding)**, **Sobrecarga (Overloading)** y **Subtipado mediante Interfaces y Clases Abstractas**— en **Java**, **Python** y **TypeScript**, siguiendo los estándares de Programación Orientada a Objetos de la ESPE.

## Cómo se Aplicó el Polimorfismo

### 1. Subtipado e Interfaces (`IFarmAnimal`)
- **`IFarmAnimal`** define el contrato público común (`computeFoodCost()`, `feed()`, `produce()`):
  - **Java:** `public interface IFarmAnimal`
  - **Python:** `class IFarmAnimal(ABC)` con `@abstractmethod`
  - **TypeScript:** `export interface IFarmAnimal`
- **`FarmAnimal`** implementa `IFarmAnimal` como clase abstracta y sirve de tipo base para la colección polimórfica.
- **Principio de Sustitubilidad (Liskov):** Un objeto `Chicken` *es un* `FarmAnimal` y *es un* `IFarmAnimal`, por lo que puede almacenarse en una colección de `FarmAnimal` o asignarse a una referencia `IFarmAnimal`. En cambio, un `FarmAnimal` genérico no puede asignarse a una colección de `Chicken`.

### 2. Polimorfismo Estático (Sobrecarga / Overloading)
Aplicado en `FarmAnimal` (y sus subclases) mediante múltiples firmas:
- **Constructores sobrecargados:** constructor por defecto / mínimo (`id`, `breed`) y constructor completo (`id`, `breed`, `bornOn`). En Python se utilizan `@overload` y valores por defecto; en TypeScript, firmas de sobrecarga explícitas; en Java, múltiples constructores encadenados con `this(...)`.
- **Método `feed` sobrecargado:**
  - `feed()`: alimentación con ración estándar diaria.
  - `feed(foodType, quantity)`: alimentación especificando tipo de alimento y cantidad en kilogramos.

### 3. Polimorfismo Dinámico (Sobrescritura / Overriding)
Cada subclase concreta (`Chicken`, `Cow`, `Pig`, `Sheep`) sobrescribe (`@Override` / `override`):
- `computeFoodCost()` (`compute_food_cost()` en Python): cálculo mensual diferenciado por especie y estado (`isMolting`, `isProducingMilk`, edad).
- `produce()`: comportamiento productivo propio de cada animal (huevos, leche, carne, lana).
- `toString()` (`__str__()` en Python): representación textual especializada.

En `AnimalFarmApp`, la colección polimórfica se recorre invocando estos métodos sin usar `if` ni `instanceof`/`isinstance`: el despacho dinámico selecciona automáticamente la implementación de cada subclase en tiempo de ejecución.

---

## Estructura del Proyecto

```text
hw05AnimalFarmAppPolymorphism/
├── AnimalFarmApp/                  # Java
│   └── src/ec/edu/espe/animalfarmapp/
│       ├── model/
│       │   ├── IFarmAnimal.java
│       │   ├── FarmAnimal.java
│       │   ├── Chicken.java
│       │   ├── Cow.java
│       │   ├── Pig.java
│       │   └── Sheep.java
│       └── view/
│           └── AnimalFarmApp.java
├── AnimalFarmAppPython/            # Python
│   ├── model/
│   │   ├── __init__.py
│   │   ├── i_farm_animal.py
│   │   ├── farm_animal.py
│   │   ├── chicken.py
│   │   ├── cow.py
│   │   ├── pig.py
│   │   └── sheep.py
│   └── animal_farm_app.py
├── AnimalFarmAppTypeScript/        # TypeScript
│   ├── model/
│   │   ├── IFarmAnimal.ts
│   │   ├── FarmAnimal.ts
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
