from flask import Flask

app = Flask(__name__)
PORT = 4014


@app.get("/")
def home():
    return "Welcome to Mateo´s server"


if __name__ == "__main__":
    print(f"server is running at {PORT}")
    app.run(port=PORT)
