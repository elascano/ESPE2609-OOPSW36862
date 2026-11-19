
const http = require('node:http');
const hostname = '127.0.0.1';
const port = 5020;

const server = http.createServer((req,res) => {
    res.statusCode = 200;
    res.setHeader('Content-Type','text/html');
    res.end('Hello, <b> web Developers!</b> from <i> Daniel Sangoquiza</i> ');

});

server.listen(port, hostname,() => {
    console.log(`Daniel Sangoquiza Server running at http://${hostname}:${port}/`);
});
