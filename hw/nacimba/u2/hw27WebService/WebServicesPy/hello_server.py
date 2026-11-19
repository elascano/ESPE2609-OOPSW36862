from http.server import BaseHTTPRequestHandler, HTTPServer

HOSTNAME = "127.0.0.1"
PORT = 5014


class HelloHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        body = "<h1>Hola, <b>Web Developers!</b> from <i>Mateo Nacimba</i></h1>".encode("utf-8")
        self.send_response(200)
        self.send_header("Content-Type", "text/html; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)


if __name__ == "__main__":
    server = HTTPServer((HOSTNAME, PORT), HelloHandler)
    print(f"Servidor corriendo en http://{HOSTNAME}:{PORT}/")
    server.serve_forever()
