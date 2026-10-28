"""Modelo de dominio: cálculo y clasificación del Índice de Masa Corporal (IMC / BMI).

Port de ec.edu.espe.bodymassindex.model.BodyMassIndex (Java).
"""

from __future__ import annotations

import math
from enum import Enum


class BmiCategory(str, Enum):
    """Categorías de la OMS. El valor coincide con los textos del original en Java."""

    UNDERWEIGHT = "Underweight"
    NORMAL_WEIGHT = "Normal weight"
    OVERWEIGHT = "Overweight"
    OBESITY = "Obesity"


class BodyMassIndex:
    """Calcula el IMC (kg / m²) y lo clasifica."""

    UNDERWEIGHT_LIMIT = 18.5
    NORMAL_LIMIT = 25.0
    OVERWEIGHT_LIMIT = 30.0

    def calculate_bmi(self, weight_kg: float, height_m: float) -> float:
        """Devuelve el IMC redondeado a 2 decimales (half-up, como Math.round de Java).

        Raises:
            ValueError: si peso o altura no son números finitos mayores que 0.
        """
        self._validate_positive("weight", weight_kg)
        self._validate_positive("height", height_m)

        bmi = weight_kg / (height_m * height_m)
        # round() de Python usa "banker's rounding"; Java usa half-up.
        return math.floor(bmi * 100.0 + 0.5) / 100.0

    def classify(self, bmi: float) -> BmiCategory:
        """Clasifica un IMC. Los límites inferiores son inclusivos (18.5 -> Normal)."""
        if bmi < self.UNDERWEIGHT_LIMIT:
            return BmiCategory.UNDERWEIGHT
        if bmi < self.NORMAL_LIMIT:
            return BmiCategory.NORMAL_WEIGHT
        if bmi < self.OVERWEIGHT_LIMIT:
            return BmiCategory.OVERWEIGHT
        return BmiCategory.OBESITY

    @staticmethod
    def _validate_positive(name: str, value: float) -> None:
        # bool es subclase de int: se rechaza explícitamente.
        if isinstance(value, bool) or not isinstance(value, (int, float)):
            raise TypeError(f"The {name} must be a number")
        # not isfinite cubre NaN e infinito, que en Java pasaban el chequeo "<= 0".
        if not math.isfinite(value) or value <= 0:
            raise ValueError(f"The {name} must be greater than 0")
