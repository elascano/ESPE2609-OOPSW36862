from flask import Flask
app = Flask(__name__)
port = 4003

@app.route('/')
def home():
    return 'Welcome to Enrique´s server'

if __name__ == '__main__':
    print(f"Enrique´s server running at http://localhost:{port}/")
    app.run(port=port)