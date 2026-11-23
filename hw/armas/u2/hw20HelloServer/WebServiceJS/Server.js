import express from "express";
const app = express();
const port = 4002;

app.get('/', (req, res) => {
  res.send('Welcome to Demian´s server');
});

app.listen(port, () => {
  console.log(`Demian´s server running at http://localhost:${port}/`);
});