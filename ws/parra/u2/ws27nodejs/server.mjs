import express from "express";
const app = express();
const port = 4017;

app.get("/", (req, res) => {
    res.send('Welcome to Carlos Parra server!');
    
});
app.listen(port, () => {
    console.log(`server is running on ${port}`);
});

