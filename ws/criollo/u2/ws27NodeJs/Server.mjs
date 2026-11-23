import express from "express";
const app = express();
const port = 4007;

app.get('/', (req, res) => {
  res.send("Welcome to Dennis's server!");
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});