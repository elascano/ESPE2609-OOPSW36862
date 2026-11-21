from http.server import BaseHTTPRequestHandler, HTTPServer

hostname = "127.0.0.1"
port = 5020


class HelloHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-Type", "text/html; charset=utf-8")
        self.end_headers()
        self.wfile.write(b"Hello, <b> web Developers!</b> from <i> Daniel</i> ")

    # Opcional: evita imprimir cada petición en consola por defecto
    def log_message(self, format, *args):
        return


if __name__ == "__main__":
    server = HTTPServer((hostname, port), HelloHandler)
    print(f"Daniel Server running at http://{hostname}:{port}/")
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\nServidor detenido.")
        server.server_close()
        