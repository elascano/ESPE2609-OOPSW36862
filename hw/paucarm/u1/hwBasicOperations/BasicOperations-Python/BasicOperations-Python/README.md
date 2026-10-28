# BasicOperations (Python)

Autor: Paucar Matias, ByteWorks, @ESPE

## Estructura
```
basicoperations/
  model/operation.py      -> clase Operation (add, subtract, multiply, divide)
  model/square.py         -> clase Square (compute_area)
  view/basic_operations.py-> main
  controller/  utils/     -> vacíos (igual que en el proyecto Java)
tests/model/              -> pruebas con pytest (equivalente a JUnit)
```

## Cómo correrlo en VS Code
1. Abrir esta carpeta en VS Code (Archivo > Abrir carpeta).
2. Instalar la extensión **Python** de Microsoft.
3. En la terminal:
   ```
   pip install -r requirements.txt
   ```
4. Ejecutar el programa:
   ```
   python -m basicoperations.view.basic_operations
   ```
   (o F5, ya viene configurado en `.vscode/launch.json`)
5. Ejecutar las pruebas:
   ```
   python -m pytest -v
   ```
   (o desde el ícono del matraz "Testing" en la barra lateral)
