from http.server import BaseHTTPRequestHandler, HTTPServer

HOSTNAME = "127.0.0.1"
PORT = 7018 


class HelloHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        body = "hello, <b>Web Developers!</b> from <i>Matias</i>"

        self.send_response(200)                                          
        self.send_header("Content-Type", "text/html; charset=utf-8")     
        self.end_headers()
        self.wfile.write(body.encode("utf-8"))                          
def main():
    server = HTTPServer((HOSTNAME, PORT), HelloHandler)                  
    print(f"Matias running at http://{HOSTNAME}:{PORT}/")                
    try:
        server.serve_forever()                                           
    except KeyboardInterrupt:
        print("\nServidor detenido")
        server.server_close()


if __name__ == "__main__":
    main()