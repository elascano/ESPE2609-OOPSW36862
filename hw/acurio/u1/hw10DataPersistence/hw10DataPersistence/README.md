# Chicken Farm App — Python y TypeScript

Conversión del proyecto de consola `ChickenFarmApp` de Java a dos lenguajes, con una carpeta independiente para cada versión.

## Estructura

```text
ChickenFarmApp_Python_TypeScript/
├── python/
│   ├── main.py                 # menú y lectura/escritura JSON
│   ├── model/
│   │   ├── chicken.py          # Chicken (reconstruida provisionalmente)
│   │   ├── chicken_coop.py     # ChickenCoop
│   │   ├── egg.py              # Egg
│   │   └── poop.py             # Poop
│   ├── tests/
│   └── README.md
├── typescript/
│   ├── src/
│   │   ├── main.ts
│   │   ├── model/
│   │   │   ├── Chicken.ts      # Chicken (reconstruida provisionalmente)
│   │   │   ├── ChickenCoop.ts
│   │   │   ├── Egg.ts
│   │   │   └── Poop.ts
│   │   ├── view/ChickenFarmApp.ts
│   │   └── test.ts
│   ├── package.json
│   ├── tsconfig.json
│   └── README.md
└── README.md
```

## Ejecución desde VS Code (PowerShell)

### Python

```powershell
cd python
python main.py
```

Para comprobar el código:

```powershell
python -m unittest discover -s tests -v
```

### TypeScript

Vuelve a la carpeta principal y entra a `typescript`:

```powershell
cd ..\typescript
npm install
npm run build
npm start
```

Para ejecutar las pruebas:

```powershell
npm test
```

Nota: primero debes tener Python, Node.js y npm instalados. `npm install` descarga TypeScript y los tipos de Node definidos en `package.json`.

## ¿Qué funciones se conservaron?

- Menú: agregar gallina (1), eliminar (2), siguiente (3), reiniciar recorrido (4), mostrar gallinero (5), demostración (6), salir (0).
- Gallinero con capacidad de 10 gallinas, sin IDs duplicados.
- Al eliminar, desplaza a la izquierda los elementos posteriores.
- Guarda después de agregar o eliminar, y al salir; carga al iniciar.
- Mismo formato JSON de Java: `id`, `name`, `color`, `bornOnDate`, `ageInYears`, `molting`.
- Valida números, valores booleanos y fechas de calendario.
- La opción 6 no modifica el gallinero guardado; solo muestra una demostración.

**Importante:** La clase Java `Chicken.java` no se adjuntó. Se reconstruyó por las llamadas que aparecen en `ChickenFarmApp.java`. La función `doStuff()` y el texto exacto de `toString()` son provisionales; deben actualizarse según la clase Java original. Asimismo, no se puede garantizar equivalencia total sin revisar esa clase.

## Subir todo a un nuevo repositorio GitHub

Abre en VS Code la carpeta **`ChickenFarmApp_Python_TypeScript`** (la carpeta que contiene `python`, `typescript` y este README). Crea un repositorio **vacío** en GitHub (sin README inicial) y ejecuta en la terminal de esa carpeta:

```powershell
git init
git add .
git commit -m "Add Chicken Farm App in Python and TypeScript"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/TU_REPOSITORIO.git
git push -u origin main
```

Sustituye `TU_USUARIO` y `TU_REPOSITORIO` por los tuyos. Si trabajas sobre un repositorio que ya clonaste, **no** repitas `git init` ni `git remote add origin`; usa `git add .`, `git commit ...` y `git push` dentro del repositorio existente.

Los archivos `chickens.json` quedan fuera de Git por los `.gitignore` para que los datos de prueba no se suban accidentalmente. Las dos versiones usan el mismo formato, pero cada una guarda su propio archivo JSON en su carpeta.
