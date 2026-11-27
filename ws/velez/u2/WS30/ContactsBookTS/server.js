const http = require("http");
const fs = require("fs");
const path = require("path");
const { exec } = require("child_process");

const PORT = 3000;
const MIME = {
    ".html": "text/html; charset=utf-8",
    ".css": "text/css; charset=utf-8",
    ".js": "application/javascript; charset=utf-8"
};

const server = http.createServer((req, res) => {
    const reqPath = req.url === "/" ? "/index.html" : req.url;
    const filePath = path.join(__dirname, reqPath);
    const ext = path.extname(filePath);

    fs.readFile(filePath, (err, content) => {
        if (err) {
            res.writeHead(404);
            res.end("Not found");
            return;
        }
        res.writeHead(200, { "Content-Type": MIME[ext] || "text/plain" });
        res.end(content);
    });
});

server.listen(PORT, () => {
    const url = `http://localhost:${PORT}`;
    console.log(`ContactsBook TypeScript running at ${url}`);
    exec(`start "" "${url}"`);
});
