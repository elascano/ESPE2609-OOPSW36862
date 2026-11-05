# Actividad 33 – SD Card (Tarjeta SD) – TypeScript

**Asignación:** número 15 de la lista → **SD card – TypeScript**.

## Requisitos
- `id` es el atributo obligatorio de identificación.
- Se agregaron tres atributos: `capacityGB`, `brand` y `speedClass`.
- Se utiliza `SDCard[]`, un arreglo dinámico de TypeScript (equivalente funcional a `ArrayList`).
- Se guardan y leen los objetos desde un único archivo JSON: `data/sd_cards.json`.

## Estructura del proyecto
```text
Actividad_33_SD_Card_TypeScript/
├── data/
│   └── sd_cards.json
├── docs/
│   ├── diagrama_clases.md
│   └── evidencias/
│       └── ejecucion.png
├── src/
│   ├── SDCard.ts
│   ├── SDCardManager.ts
│   └── index.ts
├── dist/                  # JavaScript compilado (se genera al ejecutar)
├── package.json
├── tsconfig.json
└── README.md
```

## Cómo ejecutar en Visual Studio Code o NetBeans
Se requiere Node.js y npm instalados. Abre esta carpeta en VS Code y ejecuta en la terminal:

```bash
npm install
npm run start
```

`npm run start` compila TypeScript y ejecuta el programa. En NetBeans se puede abrir la carpeta como proyecto Node.js/JavaScript si está habilitado el soporte correspondiente; VS Code es la opción directa.

## Funcionamiento
1. `index.ts` crea tres tarjetas SD de ejemplo.
2. `SDCardManager` almacena los objetos en un arreglo dinámico.
3. `saveToJson()` escribe el arreglo en `data/sd_cards.json`.
4. `readFromJson()` lee el mismo archivo, reconstruye los objetos y los muestra en consola.
5. Se valida que el ID sea positivo y único, y que los otros atributos tengan valores válidos.

## Diagrama de clases
Consulta `docs/diagrama_clases.md`. La evidencia de ejecución está en `docs/evidencias/ejecucion.png` y se incluye también en el documento de evidencias.
