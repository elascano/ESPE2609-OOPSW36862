import express from "express";
const app = express();
const port = 4014; // Corregido de 'por' a 'port'

app.get('/', (req, res) => {
    res.send('Welcome to Mateo´s server');
});

app.listen(port, () => {
    console.log(`server is running at ${port}`); // Quitado el '$' sobrante
});