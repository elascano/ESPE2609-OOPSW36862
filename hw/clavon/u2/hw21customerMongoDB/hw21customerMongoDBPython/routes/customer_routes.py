from flask import Blueprint, jsonify
from models.customer import customer_collection

customer_bp = Blueprint("customer_bp", __name__)

@customer_bp.route("/customers", methods=["GET"])
def get_customers():
    try:
        customers = list(customer_collection.find())
        for c in customers:
            c["_id"] = str(c["_id"])
        return jsonify(customers)
    except Exception as e:
        return jsonify({"message": str(e)}), 500

@customer_bp.route("/customers/stats", methods=["GET"])
def get_customer_stats():
    try:
        customers = list(customer_collection.find())

        if not customers:
            return jsonify({"message": "No hay clientes registrados para calcular estadísticas."}), 404

        total_spent = sum(c.get("moneySpent", 0) for c in customers)
        average_spent = total_spent / len(customers)

        top_spender = max(customers, key=lambda c: c.get("moneySpent", 0))

        total_age = sum(c.get("age", 0) for c in customers)
        average_age = total_age / len(customers)

        oldest_customer = max(customers, key=lambda c: c.get("age", 0))
        youngest_customer = min(customers, key=lambda c: c.get("age", 0))

        return jsonify({
            "totalCustomers": len(customers),
            "totalSpent": round(total_spent, 2),
            "averageSpent": round(average_spent, 2),
            "topSpender": {
                "id": top_spender.get("id"),
                "name": top_spender.get("name"),
                "moneySpent": top_spender.get("moneySpent")
            },
            "averageAge": round(average_age, 2),
            "oldestCustomer": {
                "id": oldest_customer.get("id"),
                "name": oldest_customer.get("name"),
                "age": oldest_customer.get("age")
            },
            "youngestCustomer": {
                "id": youngest_customer.get("id"),
                "name": youngest_customer.get("name"),
                "age": youngest_customer.get("age")
            }
        })
    except Exception as e:
        return jsonify({"message": str(e)}), 500