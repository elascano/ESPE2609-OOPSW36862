from flask import Flask
from routes.customer_routes import customer_bp

port = 3013
app = Flask(__name__)

app.register_blueprint(customer_bp, url_prefix="/computerstore")

if __name__ == "__main__":
    print(f"Marcos Computers Store Server is {port}")
    app.run(port=port)