from pymongo import MongoClient

client = MongoClient("mongodb+srv://mamamarandi_db_user:9IYHeIVGOBk5iixB@cluster0.iwnlpw7.mongodb.net/oop")
db = client["oop"]
customer_collection = db["Customer"]