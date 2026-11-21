from flask import Flask

app = Flask(__name__)
port = 4020


@app.get("/")
def home():
    return "Welcome to Daniel server"


if __name__ == "__main__":
    print(f"Daniel Server is running on http://127.0.0.1:{port}")
    app.run(host="127.0.0.1", port=port)