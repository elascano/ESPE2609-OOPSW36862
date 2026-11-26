from flask import Flask
from pymongo import MongoClient
from routes.costumer_routes import costumer_bp

app = Flask(__name__)
port = 3010

try:
    uri = 'mongodb+srv://cjguanuna_db_user:YKE0slhtagPBw2YD@cluster0.ncpcmb6.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0'
    client = MongoClient(uri)
    client.admin.command('ping')
    print("Cristopher system connected to mongoDb Database")
except Exception as e:
    print(f"Error connecting to MongoDB: {e}")

app.register_blueprint(costumer_bp, url_prefix='/computerstore')

if __name__ == '__main__':
    print(f"Cristopher's Computers Store Server is running on port --> {port}")
    app.run(host='0.0.0.0', port=port, debug=True)