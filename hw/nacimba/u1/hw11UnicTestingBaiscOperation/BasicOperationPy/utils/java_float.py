"""Emulación del tipo `float` (32 bits, IEEE 754) de Java."""
import math
import struct


def to_float32(value: float) -> float:
    """Redondea a float32 (desbordes -> infinito, igual que Java)."""
    try:
        return struct.unpack(">f", struct.pack(">f", value))[0]
    except OverflowError:
        return math.copysign(math.inf, value)


def divide(dividend: float, divisor: float) -> float:
    """División IEEE 754: x/0 -> ±Infinity, 0/0 -> NaN (no lanza excepción)."""
    a, b = to_float32(dividend), to_float32(divisor)
    try:
        return to_float32(a / b)
    except ZeroDivisionError:
        if a == 0 or math.isnan(a):
            return math.nan
        return math.copysign(math.inf, a) * math.copysign(1.0, b)


def format_float(value: float) -> str:
    """Equivale a Float.toString(): 4.6000004, 1.0, NaN, Infinity, 1.0E7 ..."""
    v = to_float32(value)
    if math.isnan(v):
        return "NaN"
    if math.isinf(v):
        return "Infinity" if v > 0 else "-Infinity"
    sign = "-" if math.copysign(1.0, v) < 0 else ""
    a = abs(v)
    if a == 0:
        return sign + "0.0"
    for precision in range(1, 10):
        text = f"{a:.{precision - 1}e}"
        if to_float32(float(text)) == a:
            break
    mantissa, exp = text.split("e")
    exp = int(exp)
    digits = mantissa.replace(".", "").rstrip("0") or "0"
    if 1e-3 <= a < 1e7:
        if exp >= 0:
            int_part = digits[: exp + 1].ljust(exp + 1, "0")
            frac = digits[exp + 1:] or "0"
        else:
            int_part = "0"
            frac = "0" * (-exp - 1) + digits
        return f"{sign}{int_part}.{frac}"
    return f"{sign}{digits[0]}.{digits[1:] or '0'}E{exp}"
