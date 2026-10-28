# Basic Operations en Python y TypeScript

## Python
Requiere Python 3. Ejecuta desde la carpeta `python`:

```bash
python main.py
python -m unittest
```

Incluye `Operation`, `BodyMassIndex` y pruebas unitarias con `unittest`.

## TypeScript
Requiere Node.js y npm. Ejecuta desde la carpeta `typescript`:

```bash
npm install
npm start
npm test
```

Incluye `Operation`, `BodyMassIndex` y pruebas con Jest.

## Nota
- La suma redondea a dos decimales, como en el código Java.
- Dividir para cero lanza un error explícito en ambos lenguajes. En Java, los `float` pueden producir `NaN` o infinito; aquí se valida para que el comportamiento sea claro y comprobable.
- El IMC conserva la fórmula original: peso / altura². No se añadieron validaciones de peso negativo porque las pruebas originales esperan esos resultados.
