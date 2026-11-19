const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5021;
const server = http.createServer((req, res)=> {
    res.statusCode = 200
    res.setHeader('Content-Type', 'text/html');
    res.end('hello, <b>Web Developers!</b> from <i>Anghelo Suntaxi</i>');
})
server.listen(port, hostname, () => {
    console.log(`Anghelo Suntaxi running at http://${hostname}:${port}/`);
})