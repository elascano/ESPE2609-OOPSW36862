# hw23InheritanceExercises

Java is the original, unchanged source of truth. Python and TypeScript reproduce
its inheritance structure, constructor arguments, properties, accessors, sample
values, and **legacy Java Date behavior**, including the historical year/month
interpretation of `new Date(2022, 10, 10)`. The unusual resulting dates are
intentional to match the class assignment.

## Java / NetBeans
Open `AnimalFarmAppJava` as a Maven project. All included Java files are copied
**byte-for-byte from the original ZIP** without corrections.

## Python / Visual Studio Code
From `AnimalFarmAppPython`, run:

```bash
python view/AnimalFarmApp.py
```

Only the Python standard library is required.

## TypeScript / Visual Studio Code
From `AnimalFarmAppTS`, run:

```bash
npm install
npm start
```

`npm install` regenerates `package-lock.json` locally if missing. `npm start`
compiles TypeScript into ignored `dist/` and runs the compiled main program.

## File organization
`model/` contains `FarmAnimal`, `Chicken`, `Cow`, `Pig`, `Sheep`, and a
`JavaDate` date-compatibility helper. `view/` contains the entry point. Runtime
artifacts and dependency folders are excluded from version control.
