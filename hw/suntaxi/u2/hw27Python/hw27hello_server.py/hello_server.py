from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer

hostname = "127.0.0.1"
port = 5021


class HelloHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-Type", "text/html; charset=utf-8")
        self.end_headers()

        body = "hello, <b>Web Developers!</b> from <i>Anghelo Suntaxi</i>"
        self.wfile.write(body.encode("utf-8"))

    def log_message(self, format, *args):
        return


if __name__ == "__main__":
    server = ThreadingHTTPServer((hostname, port), HelloHandler)
    print(f"Anghelo Suntaxi running at http://{hostname}:{port}/")
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\nServidor detenido.")
        server.server_close()