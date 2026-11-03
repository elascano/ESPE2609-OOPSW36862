# Chicken Farm App en TypeScript

Versión de consola equivalente al programa Java, con las clases `Chicken`, `ChickenCoop`, `Egg`, `Poop` y el menú original 0 a 6.

## Preparación

Abre la carpeta `typescript` en VS Code y ejecuta estos comandos en PowerShell:

```powershell
npm install
npm run build
npm start
```

Para recompilar después de un cambio y ejecutar: `npm run dev`.

## Pruebas

```powershell
npm test
```

Necesitas Node.js y npm. Las dependencias son herramientas de desarrollo: TypeScript y tipos de Node.js.

`chickens.json` se genera en la carpeta `typescript` cuando guardas datos. No se sube por defecto a Git. El JSON conserva los campos `id`, `name`, `color`, `bornOnDate`, `ageInYears` y `molting` para compatibilidad con la versión de Java y Python.

**Nota:** La clase `Chicken.java` original no se compartió, así que `doStuff()` es provisional y el formato exacto de `toString()` queda pendiente de comparación.
