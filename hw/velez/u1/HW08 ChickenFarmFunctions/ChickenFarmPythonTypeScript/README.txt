CHICKEN FARM APP - VERSIONES PYTHON Y TYPESCRIPT

Contenido:
- python/: aplicación y clases en Python.
- typescript/: aplicación y clases en TypeScript.

EJECUTAR PYTHON (desde la carpeta python):
Windows:
  set PYTHONPATH=.
  python -m chickenfarmapp.view.chicken_farm_app

EJECUTAR TYPESCRIPT (desde la carpeta typescript):
  npm install
  npm start

Notas:
- ChickenFarmer está vacía porque la clase original Java también está vacía.
- Los archivos mantienen la lógica principal de Chicken, ChickenCoop, Egg y Poop.
- La edad se calcula de forma aproximada usando días / 365, como en el Java original.
- En Java, Date(year - 1900, month, day) usa meses desde cero; las fechas se han adaptado a los constructores de Python y JavaScript.
