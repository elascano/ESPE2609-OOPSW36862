"""Pequeñas utilidades de formato compartidas por el modelo."""
from datetime import date


def format_date(value: date) -> str:
    """Formatea una fecha como ``d / m / yyyy`` (igual que el toString() original en Java)."""
    return f"{value.day} / {value.month} / {value.year}"


def bool_str(value: bool) -> str:
    """Devuelve ``true`` / ``false`` en minúscula, como imprime Java."""
    return "true" if value else "false"
