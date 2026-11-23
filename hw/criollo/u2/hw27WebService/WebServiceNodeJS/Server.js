import express from "express";
const app = express();
const port = 4007;

app.get('/', (req, res) => {
  res.send("Welcome to Dennis Criollo's server!");
});

app.listen(port, () => {
  console.log(`Dennis Criollo's server running at http://localhost:${port}/`);
});