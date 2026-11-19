import express from "express";
const app = express();
const port = 4023;

app.get('/', (req, res) => {
  res.send('Welcome to Josselyn server!');
});

app.listen(port, () => {
  console.log(`Josselyn's Server is running at http://127.0.0.1:${port}`);
});