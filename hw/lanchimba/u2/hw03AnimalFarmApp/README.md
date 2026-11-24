# hw03AnimalFarmApp

Implementación del sistema **Animal Farm App** aplicando el pilar de **Herencia (Generalización/Especialización)** y **Polimorfismo** en **Java**, **Python** y **TypeScript**, siguiendo las convenciones de Programación Orientada a Objetos de la ESPE.

## Estructura del Proyecto

```text
hw03AnimalFarmApp/
├── AnimalFarmApp/                  # Java
│   └── src/ec/edu/espe/animalfarmapp/
│       ├── model/
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
│   │   ├── farm_animal.py
│   │   ├── chicken.py
│   │   ├── cow.py
│   │   ├── pig.py
│   │   └── sheep.py
│   └── animal_farm_app.py
├── AnimalFarmAppTypeScript/        # TypeScript
│   ├── model/
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

## Modelo UML

- **`FarmAnimal`** (superclase): `id`, `breed`, `bornOn`, getters/setters, `getAgeInMonths()` (`get_age_in_months()` en Python) y `toString()` (`__str__()` en Python).
- **`Chicken`** extends `FarmAnimal`: añade `isMolting`.
- **`Cow`** extends `FarmAnimal`: añade `isProducingMilk`.
- **`Pig`** extends `FarmAnimal`: reutiliza el constructor padre con `super` y sobrescribe la representación en texto.
- **`Sheep`** extends `FarmAnimal`: añade `lastSheering`.

---

## Instrucciones de Ejecución

### 1. Java

Desde la carpeta `AnimalFarmApp/`:

```bash
cd AnimalFarmApp
javac -d build/classes src/ec/edu/espe/animalfarmapp/model/*.java src/ec/edu/espe/animalfarmapp/view/*.java
java -cp build/classes ec.edu.espe.animalfarmapp.view.AnimalFarmApp
```

### 2. Python

Desde la carpeta `AnimalFarmAppPython/`:

```bash
cd AnimalFarmAppPython
python animal_farm_app.py
```

### 3. TypeScript

Desde la carpeta `AnimalFarmAppTypeScript/`:

```bash
cd AnimalFarmAppTypeScript
npm install        # Solo si no tienes TypeScript (tsc) instalado globalmente
npm run build      # O directamente: tsc
npm start          # O directamente: node dist/AnimalFarmApp.js
```
