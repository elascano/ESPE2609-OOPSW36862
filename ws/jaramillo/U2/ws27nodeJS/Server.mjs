import express from "express";
const app = express();
const port = 4010;

app.get('/', (req, res) => {
    res.send('Welcome to Julian Server!');
});

app.listen(port, () => {
    console.log(`Julian's server is running at http://localhost:${port}/`);
});