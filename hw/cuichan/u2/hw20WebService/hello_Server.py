from http.server import BaseHTTPRequestHandler, HTTPServer

class Handler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-Type", "text/html; charset=utf-8")
        self.end_headers()
        self.wfile.write("Hello, <b>Web Developers!</b> from <i>Leslye Cuichan</i>".encode("utf-8"))

server = HTTPServer(("127.0.0.1", 5008), Handler)
print("Leslye's server running at http://127.0.0.1:5008/")
server.serve_forever()