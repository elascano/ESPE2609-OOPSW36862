import os
from pymongo import MongoClient
from dotenv import load_dotenv

load_dotenv()

uri = os.getenv("MONGODB_URI")

if not uri:
    raise ValueError("The connection is missing in .env")

client = MongoClient(uri)

db = client["oop"]
customers = db["Customer"]
