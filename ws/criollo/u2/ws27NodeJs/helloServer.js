const http = require('node:http');
const hostname = '127.0.0.1';
const port = 4007;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/plain'); //THINK ABOUT THIS LINE Later
  res.end('Hello, <b>Web Developers!</b> from <i>Dennis</i>');
});

server.listen(port, hostname, () => {
  console.log(`Dennis's Server running at http://${hostname}:${port}/`);
});
