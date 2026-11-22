# Mateo Nacimba's port 3014
import os

from flask import Blueprint, Flask, jsonify, request
from pymongo import MongoClient, ReturnDocument
from pymongo.errors import PyMongoError

port = 3014


MONGODB_URI = os.environ.get(
    "MONGODB_URI",
    "mongodb+srv://mjnacimba_db_user:@cluster0.uaiahls.mongodb.net/oop",
)


client = MongoClient(MONGODB_URI)
db = client["oop"]
customers = db["Customer"]  

app = Flask(__name__)
router = Blueprint("customers", __name__, url_prefix="/computerstore")

NUMBER_FIELDS = ("id", "age", "moneySpent")


def to_number(value):
    if isinstance(value, bool):
        raise ValueError("Invalid number")
    number = float(value)
    return int(number) if number.is_integer() else number


def clean(data):
    result = {}
    for key in ("id", "name", "age", "moneySpent"):
        value = data.get(key)
        if value is None:
            continue
        if key in NUMBER_FIELDS:
            try:
                value = to_number(value)
            except (TypeError, ValueError):
                raise ValueError(f"Cast to Number failed for field '{key}'")
        else:
            value = str(value)
        result[key] = value
    return result


def serialize(doc):
    doc["_id"] = str(doc["_id"])
    return doc


@router.get("/customers")
def get_customers():
    try:
        return jsonify([serialize(c) for c in customers.find()])
    except PyMongoError as err:
        return jsonify({"message": str(err)}), 500


@router.get("/customers/analytics")
def get_analytics():
    try:
        all_customers = list(customers.find())
        highest = find_highest_spender(all_customers)
        return jsonify(
            {
                "totalSpent": calculate_total_spent(all_customers),
                "averageSpent": calculate_average_spent(all_customers),
                "highestSpender": serialize(highest) if highest else None,
            }
        )
    except PyMongoError as err:
        return jsonify({"message": str(err)}), 500


@router.get("/customers/<int:customer_id>")
def get_customer(customer_id):
    try:
        found = customers.find_one({"id": customer_id})
        if not found:
            return jsonify({"message": "Customer not found"}), 404
        return jsonify(serialize(found))
    except PyMongoError as err:
        return jsonify({"message": str(err)}), 500


@router.post("/customers")
def create_customer():
    try:
        new_customer = clean(request.get_json(silent=True) or {})
        customers.insert_one(new_customer)
        return jsonify(serialize(new_customer)), 201
    except (ValueError, PyMongoError) as err:
        return jsonify({"message": str(err)}), 400


@router.put("/customers/<int:customer_id>")
def update_customer(customer_id):
    try:
        data = clean(request.get_json(silent=True) or {})
        if data:
            updated = customers.find_one_and_update(
                {"id": customer_id},
                {"$set": data},
                return_document=ReturnDocument.AFTER,
            )
        else:
            updated = customers.find_one({"id": customer_id})
        if not updated:
            return jsonify({"message": "Customer not found"}), 404
        return jsonify(serialize(updated))
    except (ValueError, PyMongoError) as err:
        return jsonify({"message": str(err)}), 400


@router.delete("/customers/<int:customer_id>")
def delete_customer(customer_id):
    try:
        deleted = customers.find_one_and_delete({"id": customer_id})
        if not deleted:
            return jsonify({"message": "Customer not found"}), 404
        return jsonify({"message": "Customer deleted"})
    except PyMongoError as err:
        return jsonify({"message": str(err)}), 500


def calculate_total_spent(all_customers):
    return sum(c.get("moneySpent") or 0 for c in all_customers)


def calculate_average_spent(all_customers):
    if len(all_customers) == 0:
        return 0
    return calculate_total_spent(all_customers) / len(all_customers)


def find_highest_spender(all_customers):
    if len(all_customers) == 0:
        return None
    return max(all_customers, key=lambda c: c.get("moneySpent") or 0)


app.register_blueprint(router)

if __name__ == "__main__":
    try:
        client.admin.command("ping")
        print("Mateo Nacimba´s System connected to MOngoDB Database")
    except PyMongoError as error:
        print(error)
    print(f"Mateo Nacimba's Computer Store running on port {port}")
    app.run(port=port)
