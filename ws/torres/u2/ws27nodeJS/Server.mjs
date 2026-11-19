import express from "express";
const app = express();
const port = 4018;

app.get('/' ,(req,res) =>{
    res.send('Welcome to Ostin server!');

});
app.listen(port,()=>{
    console.log(`Matias's Server is runing at http://127.0.0.1 $(port)`);
});