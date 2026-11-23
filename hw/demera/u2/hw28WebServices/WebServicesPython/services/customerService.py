from typing import Optional
from config.database import customerCollection
from models.customer import CustomerCreate, CustomerUpdate


def serializeCustomer(document: dict) -> dict:
    return {
        "id": document.get("id"),
        "name": document.get("name"),
        "age": document.get("age"),
        "moneySpent": document.get("moneySpent", 0.0),
    }


def findAllCustomers() -> list[dict]:
    return [serializeCustomer(doc) for doc in customerCollection.find()]


def findCustomerById(customerId: int) -> Optional[dict]:
    document = customerCollection.find_one({"id": customerId})
    if not document:
        return None
    return serializeCustomer(document)


def createCustomer(customerData: CustomerCreate) -> dict:
    customerDict = customerData.model_dump()
    customerCollection.insert_one(customerDict.copy())
    return customerDict


def updateCustomerById(customerId: int, updatedData: CustomerUpdate) -> Optional[dict]:
    fieldsToUpdate = {k: v for k, v in updatedData.model_dump().items() if v is not None}
    updatedDocument = customerCollection.find_one_and_update(
        {"id": customerId},
        {"$set": fieldsToUpdate},
        return_document=True,
    )
    if not updatedDocument:
        return None
    return serializeCustomer(updatedDocument)


def deleteCustomerById(customerId: int) -> bool:
    deletedDocument = customerCollection.find_one_and_delete({"id": customerId})
    return deletedDocument is not None


def calculateTotalSpent(customers: list[dict]) -> float:
    return sum(customer.get("moneySpent", 0.0) for customer in customers)


def calculateAverageSpent(customers: list[dict]) -> float:
    if not customers:
        return 0.0
    return calculateTotalSpent(customers) / len(customers)


def findHighestSpender(customers: list[dict]) -> Optional[dict]:
    if not customers:
        return None
    return max(customers, key=lambda customer: customer.get("moneySpent", 0.0))


def getSpendingAnalytics() -> dict:
    customers = findAllCustomers()
    return {
        "totalSpent": calculateTotalSpent(customers),
        "averageSpent": calculateAverageSpent(customers),
        "highestSpender": findHighestSpender(customers),
    }