from flask import Flask

app = Flask(__name__)
port = 4007


@app.route("/")
def home():
  return "Welcome to Leslye's server!"

@app.route("/favicon.ico")
def favicon():
  return "", 204


if __name__ == "__main__":
  print(f"Leslye's server listening on port {port}")
  app.run(host="127.0.0.1", port=port)