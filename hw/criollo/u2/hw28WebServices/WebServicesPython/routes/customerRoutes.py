from fastapi import APIRouter, HTTPException
from models.customer import CustomerCreate, CustomerUpdate
from services import customerService

router = APIRouter()


@router.get("/customers")
def getAllCustomers():
    return customerService.findAllCustomers()


@router.get("/customers/analytics")
def getSpendingSummary():
    return customerService.getSpendingAnalytics()


@router.get("/customers/{customerId}")
def getCustomerById(customerId: int):
    customer = customerService.findCustomerById(customerId)
    if not customer:
        raise HTTPException(status_code=404, detail="Customer not found")
    return customer


@router.post("/customers", status_code=201)
def createCustomer(customer: CustomerCreate):
    return customerService.createCustomer(customer)


@router.put("/customers/{customerId}")
def updateCustomer(customerId: int, customer: CustomerUpdate):
    updatedCustomer = customerService.updateCustomerById(customerId, customer)
    if not updatedCustomer:
        raise HTTPException(status_code=404, detail="Customer not found")
    return updatedCustomer


@router.delete("/customers/{customerId}")
def deleteCustomer(customerId: int):
    wasDeleted = customerService.deleteCustomerById(customerId)
    if not wasDeleted:
        raise HTTPException(status_code=404, detail="Customer not found")
    return {"message": "Customer deleted"}