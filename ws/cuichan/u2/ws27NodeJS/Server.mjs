import express from "express";
const app = express();
const port = 4008;

app.get("/", (req, res) => {
  res.send("Welcome to Leslye's server!");
});

app.listen(port, () => {
  console.log(`Leslye's server listening on port ${port}`);
});