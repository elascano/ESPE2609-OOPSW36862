from flask import Blueprint, jsonify
from models.costumer import get_costumer_collection
import traceback

costumer_bp = Blueprint('costumer_bp', __name__)

@costumer_bp.route("/costumers", methods=['GET'])
def get_costumers():
    try:
        collection = get_costumer_collection()
        costumers = list(collection.find({}, {'_id': 0}))
        return jsonify(costumers), 200
    except Exception as err:
        print(traceback.format_exc())
        return jsonify({"message": str(err)}), 500