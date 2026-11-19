const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5009;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');
  res.end('Hello, <b>Web Developers!</b> from <i>Dennis Criollo</i>');
});

server.listen(port, hostname, () => {
  console.log(`Dennis Criollo's Server running at http://${hostname}:${port}/`);
});