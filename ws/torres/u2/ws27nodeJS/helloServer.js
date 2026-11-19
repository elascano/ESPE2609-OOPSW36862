const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5018;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');
  res.end('hello, <b>Web Developers!</b> from <i>Ostin</i>');
});

server.listen(port, hostname, () => {
  console.log(`Matias running at http://${hostname}:${port}/`);
});