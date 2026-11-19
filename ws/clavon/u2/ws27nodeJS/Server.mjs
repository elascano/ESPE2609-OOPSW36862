import express from "express";
const app = express();
const port = 4006;

app.get('/', (req, res) => {
    res.send('Welcome to my server, David server');
});

app.listen(port, () => {
    console.log(`David's Server is running at http://127.0.0.1:${port}`);
});