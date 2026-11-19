import express from "express";
const app = express();
const port = 4013;

app.get('/', (req, res) => {
    res.send('Welcome to my server, Marcos server');
});

app.listen(port, () => {
    console.log(`Marcos's Server is running at http://127.0.0.1:${port}`);
});