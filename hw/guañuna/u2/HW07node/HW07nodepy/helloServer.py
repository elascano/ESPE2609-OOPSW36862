from http.server import BaseHTTPRequestHandler, HTTPServer

hostname = '127.0.0.1'
port = 5010

class Handler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-Type', 'text/html; charset=utf-8')
        self.end_headers()
        # Escribimos el HTML en bytes
        self.wfile.write(b'Hello, <b>OOP Developers!</b> from <i>Cristopher Guanuna</i>')

if __name__ == '__main__':
    server = HTTPServer((hostname, port), Handler)
    print(f"Cristopher's Server running at http://{hostname}:{port}/")
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\nServidor detenido.")
        server.server_close()