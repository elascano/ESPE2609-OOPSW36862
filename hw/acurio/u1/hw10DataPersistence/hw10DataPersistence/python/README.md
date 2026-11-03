# Chicken Farm App en Python

Proyecto de consola convertido desde Java. Contiene las clases `Chicken`, `ChickenCoop`, `Egg` y `Poop`, y un menú con las opciones 0 a 6.

## Ejecutar

Abre la carpeta `python` en VS Code. En la terminal:

```powershell
python main.py
```

Si tu Windows usa el lanzador `py`, puedes usar `py main.py`.

## Pruebas

```powershell
python -m unittest discover -s tests -v
```

No requiere paquetes externos. Guarda los registros en `chickens.json` dentro de esta carpeta. No se sube por defecto a Git (`.gitignore`).

**Nota:** No se recibió la clase original `Chicken.java`; `Chicken` se reconstruyó según sus usos en `ChickenFarmApp.java`. El contenido de `do_stuff()` es provisional.
