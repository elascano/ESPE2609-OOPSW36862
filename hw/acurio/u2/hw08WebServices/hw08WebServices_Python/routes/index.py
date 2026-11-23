from flask import Flask
from models.customer import client
from routes.customer_routes import router

app = Flask(__name__)

app.register_blueprint(router, url_prefix="/computerstore")

if __name__ == "__main__":
    client.admin.command("ping")

    print("Connected to MongoDB")
    print("Python Server running on port 3001")

    app.run(port=3001)