from flask import Blueprint, jsonify
from models.customer import customers

router = Blueprint("router", __name__)

@router.route("/customers")
def get_customers():
    data = list(customers.find())

    for customer in data:
        customer["_id"] = str(customer["_id"])

    return jsonify(data)


# Buscar un cliente por ID
@router.route("/customer/<int:id>")
def get_customer(id):
    customer = customers.find_one({"id": id})

    if customer is None:
        return jsonify({"message": "Customer not found"}), 404

    customer["_id"] = str(customer["_id"])

    return jsonify(customer)
