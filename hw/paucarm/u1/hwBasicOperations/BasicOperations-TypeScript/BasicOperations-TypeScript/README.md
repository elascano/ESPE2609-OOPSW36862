# BasicOperations (TypeScript)

Autor: Paucar Matias, ByteWorks, @ESPE

## Estructura
```
src/model/Operation.ts      -> clase Operation (add, subtract, multiply, divide)
src/model/Square.ts         -> clase Square (computeArea)
src/view/BasicOperations.ts -> main
src/controller/  src/utils/ -> vacíos (igual que en el proyecto Java)
test/model/                 -> pruebas con Vitest (equivalente a JUnit)
```

## Cómo correrlo en VS Code
1. Tener instalado **Node.js** (versión 18 o superior): https://nodejs.org
2. Abrir esta carpeta en VS Code (Archivo > Abrir carpeta).
3. En la terminal:
   ```
   npm install
   ```
4. Ejecutar el programa:
   ```
   npm start
   ```
   (o F5, ya viene configurado en `.vscode/launch.json`)
5. Ejecutar las pruebas:
   ```
   npm test
   ```
