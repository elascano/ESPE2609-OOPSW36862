from flask import Flask

app = Flask(__name__)
port = 4010

@app.route('/')
def home():
    return 'Welcome to Cristopher Server!'

if __name__ == '__main__':
    print(f"Server is running at http://localhost:{port}/")
    app.run(host='127.0.0.1', port=port)