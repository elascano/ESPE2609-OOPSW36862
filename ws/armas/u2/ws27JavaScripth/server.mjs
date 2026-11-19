import express from "express";
const app = express();
const port = 4002;

app.get('/', (req, res) => {
  res.send('Welcome to Demian server!');
});

app.listen(port, () => {
  console.log(`Demian's Server is running at http://127.0.0.1:${port}`);
});
