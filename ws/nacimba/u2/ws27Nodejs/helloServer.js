const http = require('http');
const hostname = '127.0.0.1';
const port = 5014;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html'); 
  res.end('<h1>Hola, <b>Web Developers!</b> from <i>Mateo Nacimba</i></h1>');
});

server.listen(port, hostname, () => {
  console.log(`Servidor corriendo en http://${hostname}:${port}/`);
});