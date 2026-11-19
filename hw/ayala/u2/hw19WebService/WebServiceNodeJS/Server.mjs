import express from "express";
const app = express();
const port = 4003;

app.get('/', (req, res) => {
    res.send('Welcome to my server, Enrique server');
});

app.listen(port, () => {
    console.log(`Enrique's Server is running at http://127.0.0.1:${port}`);
});