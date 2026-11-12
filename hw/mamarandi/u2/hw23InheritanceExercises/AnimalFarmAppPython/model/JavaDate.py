from datetime import datetime, timedelta
import calendar


def java_date(year: int, month: int, day: int) -> datetime:
    """Equivalent to Java's deprecated new Date(year, month, day) constructor.

    The year is counted from 1900 and months start at zero. Out-of-range
    month/day values are normalized rather than corrected.
    """
    year_value = year + 1900 + month // 12
    month_value = month % 12 + 1
    return datetime(year_value, month_value, 1) + timedelta(days=day - 1)


def java_date_text(value: datetime) -> str:
    return f"{value.day} / {value.month} / {value.year}"


def java_date_to_string(value: datetime) -> str:
    # Java Date.toString() used by Sheep; using the host's local timezone.
    return value.astimezone().strftime('%a %b %d %H:%M:%S %Z %Y')


def java_bool(value: bool) -> str:
    return str(value).lower()
