import express from "express";
const app = express();
const port = 4001;

app.get('/', (req, res) => {
    res.send('Welcome to Edison server!!');
});

app.listen(port, () => {
    console.log(`Edison Server is running at http://127.0.0.1:${port}`);
});