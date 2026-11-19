import express from 'express';

const app = express();
const port = 4021;

app.get('/', (req, res) => {
    res.send('Welcome to Anghelo server!');
});

app.listen(port, () => {
    console.log(`Server is running at http://127.0.0.1:${port}`);
});