const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5023
const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');
  res.end('Hello, <b>OPP Programers </b> from <i> Josselyn ULcuango </i>');
});
server.listen(port, hostname, () => {
  console.log(`Josselyn's server running at http://${hostname}:${port}/`);
});