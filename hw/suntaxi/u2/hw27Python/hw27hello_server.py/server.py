from flask import Flask

app = Flask(__name__)
port = 4021


@app.route("/")
def home():
    return "Welcome to Anghelo server!"


if __name__ == "__main__":
    print(f"Server is running at http://127.0.0.1:{port}")
    app.run(host="127.0.0.1", port=port)