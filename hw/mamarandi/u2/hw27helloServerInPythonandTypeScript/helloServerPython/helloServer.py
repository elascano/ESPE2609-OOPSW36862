from flask import Flask

app = Flask(__name__)
port = 4013

@app.route('/')
def home():
    return 'Welcome to my server, Marcos server'

if __name__ == '__main__':
    print(f"Marcos's Server is running at http://127.0.0.1:{port}")
    app.run(host='127.0.0.1', port=port)