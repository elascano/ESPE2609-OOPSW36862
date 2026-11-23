from flask import Flask
app = Flask(__name__)
port = 4002

@app.route('/')
def home():
    return 'Welcome to Demian´s server'

if __name__ == '__main__':
    print(f"Demian´s server running at http://localhost:{port}/")
    app.run(port=port)