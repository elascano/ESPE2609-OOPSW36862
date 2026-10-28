# BasicOperations (Python)

Port del proyecto Java `ec.edu.espe.basicoperations` (Operation + BasicOperations).

## Estructura
- `src/ec/edu/espe/basicoperations/model/operation.py` – clase `Operation` (add, substract, multiply, divide)
- `src/ec/edu/espe/basicoperations/view/basic_operations.py` – `main()`
- `tests/` – pruebas con `unittest`

## Ejecutar
```bash
PYTHONPATH=src python -m ec.edu.espe.basicoperations.view.basic_operations
```

## Probar
```bash
PYTHONPATH=src python -m unittest discover -s tests -v
```
(o `pip install -e .` y luego `python -m unittest discover -s tests`)

Nota: `divide` imita a Java (`float`): `1/0 -> inf`, `0/0 -> nan`, sin lanzar excepción.
