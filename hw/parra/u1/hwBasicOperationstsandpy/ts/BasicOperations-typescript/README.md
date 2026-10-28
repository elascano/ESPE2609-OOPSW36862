# BasicOperations (TypeScript)

Port del proyecto Java `ec.edu.espe.basicoperations` (Operation + BasicOperations).

## Estructura
- `src/model/Operation.ts` – clase `Operation` (add, substract, multiply, divide)
- `src/view/BasicOperations.ts` – `main()`
- `test/` – pruebas con el runner nativo de Node (`node:test`)

## Uso
```bash
npm install
npm start       # ejecuta main
npm test        # corre las pruebas
npm run build   # compila a dist/
```
Requiere Node 20+.
