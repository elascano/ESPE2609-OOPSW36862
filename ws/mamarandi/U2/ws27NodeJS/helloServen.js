const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5013;
const server = http.createServer((req, res) => {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello, <b>Web Developers!</b> from <i>Marcos Mamarandi</i>');
});

server.listen(port, hostname, () => {
    console.log(`Marcos's Server running at http://${hostname}:${port}/`);
});