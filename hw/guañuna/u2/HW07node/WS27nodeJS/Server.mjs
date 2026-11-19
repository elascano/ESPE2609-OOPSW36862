import express from "express";
const app = express();
const port = 4010;

app.get('/', (req, res) => {
    res.send('Welcome to Cristopher Server!');
});

app.listen(port, () => {
    console.log(`Server is running at http://localhost:${port}/`);
});