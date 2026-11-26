const port = 3010;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect('mongodb+srv://cjguanuna_db_user:YKE0slhtagPBw2YD@cluster0.ncpcmb6.mongodb.net/oop?retryWrites=true&w=majority&appName=Cluster0');

const db = mongoose.connection;

db.on("error", (error) => console.error(error));
db.once("open", () => console.log("Cristopher system connected to mongoDb Database"));

app.use(express.json());

const customerRouter = require("./routes/costumerRoutes");
app.use("/computerstore", customerRouter);

app.listen(port, () => console.log("Cristopher's Computers Store Server is running on port --> " + port));