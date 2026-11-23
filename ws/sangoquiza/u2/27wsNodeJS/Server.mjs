import express from 'express';

const app = express();
const port = 4020;

app.get('/', (req, res) => {
    res.send('Welcome to Daniel server');
});

app.listen(port, () => {
    console.log(`Daniel Server is running on http://127.0.0.1:${port}`);
});
