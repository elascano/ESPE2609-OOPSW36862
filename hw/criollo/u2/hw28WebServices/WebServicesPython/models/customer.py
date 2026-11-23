from pydantic import BaseModel
from typing import Optional


class CustomerCreate(BaseModel):
    id: int
    name: str
    age: int
    moneySpent: float


class CustomerUpdate(BaseModel):
    name: Optional[str] = None
    age: Optional[int] = None
    moneySpent: Optional[float] = None