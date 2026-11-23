from pymongo import MongoClient

client = MongoClient("mongodb+srv://davidclavon2007_db_user:de27yC1b@cluster0.ys86oqp.mongodb.net/oop")
db = client["oop"]
customer_collection = db["Customer"]