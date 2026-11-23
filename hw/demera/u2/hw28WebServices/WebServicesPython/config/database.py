from pymongo import MongoClient

mongoUri = "mongodb+srv://wfdemera_db_user:k1TyMEA36C5Cg9FN@cluster0.dnxy4qu.mongodb.net/OOP"
databaseName = "OOP"
collectionName = "Customer"

client = MongoClient(mongoUri)
database = client[databaseName]
customerCollection = database[collectionName]