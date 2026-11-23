from pymongo import MongoClient

mongoUri = "mongodb+srv://denniscriollo:denniscriollo@cluster0.dkq3h62.mongodb.net/"
databaseName = "OOP"
collectionName = "Customer"

client = MongoClient(mongoUri)
database = client[databaseName]
customerCollection = database[collectionName]

print("Conexión exitosa a la base de datos de Dennis Criollo")