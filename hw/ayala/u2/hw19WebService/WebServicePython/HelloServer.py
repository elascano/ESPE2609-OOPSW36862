from http.server import HTTPServer, BaseHTTPRequestHandler

hostname = "127.0.0.1"
port = 5003

class CreateServer(BaseHTTPRequestHandler):
        def do_GET(self):
            self.send_response(200)
            self.send_header('Content-Type', 'text/html')
            self.end_headers()
            output = "Hello, <b>Web Developers!</b> from <i>Enrique Ayala</i>"
            self.wfile.write(output.encode('utf-8'))
server = HTTPServer((hostname, port), CreateServer)
print(f"Enrique's Server running at http://{hostname}:{port}/")
server.serve_forever()