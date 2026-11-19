from flask import Flask

app = Flask(__name__)

@app.route("/")
def inicio():
    return "Welcome to Edison server!!"

print("Edison Server is running at http://127.0.0.1:4001")

app.run(port=4001)
