from flask import Flask

app = Flask(__name__)                                   
PORT = 6018

@app.get("/")                                           
def home():
    return "Welcome to Ostin server"                   


def main():
    print(f"Server is running on port {PORT}")          
    app.run(port=PORT)                                  


if __name__ == "__main__":
    main()