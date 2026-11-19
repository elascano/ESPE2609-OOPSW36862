from http.server import HTTPServer, BaseHTTPRequestHandler

class HelloServer(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()

        mensaje = "Hello, <b>Web OOP </b> from <i> Isaac Acurio</i>"
        self.wfile.write(mensaje.encode("utf-8"))


hostname = "127.0.0.1"
port = 5001

server = HTTPServer((hostname, port), HelloServer)

print(f"Isaac server running at http://{hostname}:{port}/")
server.serve_forever()
