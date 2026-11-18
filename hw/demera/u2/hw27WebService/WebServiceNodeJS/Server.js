import express from "express";
const app = express();
const port = 4009;

app.get('/', (req, res) => {
  res.send('Welcome to Francisco´s server');
});

app.listen(port, () => {
  console.log(`Francisco´s server running at http://localhost:${port}/`);
});