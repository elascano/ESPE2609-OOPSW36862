from flask import Flask
app = Flask(__name__)
port = 4009

@app.route('/')
def home():
    return 'Welcome to Renata´s server'

if __name__ == '__main__':
    print(f"Renata´s server running at http://localhost:{port}/")
    app.run(port=port)