from pymongo import MongoClient

def get_costumer_collection():
    uri = 'mongodb+srv://cjguanuna_db_user:YKE0slhtagPBw2YD@cluster0.ncpcmb6.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0'
    client = MongoClient(uri)
    db = client['oop']
    return db['Costumers']

def create_costumer_doc(id=None, name=None, age=None, moneySpend=None):
    return {
        "id": id,
        "name": name,
        "age": age,
        "moneySpend": moneySpend
    }