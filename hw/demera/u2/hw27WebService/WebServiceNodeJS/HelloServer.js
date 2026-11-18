const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5009;
const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader(`Content-Type`, `text/html`);
  res.end(`Hello, <b>Web Developers!</b> from <i>Francisco Demera</i>`);
});
server.listen(port, hostname, () => {
  console.log(`Francisco's Server running at http://${hostname}:${port}/`)
});