from pymongo import MongoClient

mongoUri = "mongodb+srv://drarmas_db_user:fHLt4IhdY14VuCX3@cluster0.0lokqex.mongodb.net/oop"
databaseName = "oop"
collectionName = "Customer"

client = MongoClient(mongoUri)
database = client[databaseName]
customerCollection = database[collectionName]