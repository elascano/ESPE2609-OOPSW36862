import express from "express";
const app = express();
const port = 4024;

app.get('/', (req, res) => {
  res.send('Welcome to Yandry server');
});

app.listen(port, () => {
  console.log(`Yandry server running at http://localhost:${port}/`);
});