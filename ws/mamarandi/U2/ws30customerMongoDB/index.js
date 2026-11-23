const port = 3013;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect('mongodb+srv://mamamarandi_db_user:9IYHeIVGOBk5iixB@cluster0.iwnlpw7.mongodb.net/oop');

const db = mongoose.connection;

db.on("error", (error) => console.error(error));
db.once("open", () => console.log("System connected to MongoDb Database"));

app.use(express.json());

const customerRouter = require("./routes/customerRoutes");
app.use("/computerstore", customerRouter);

app.listen(port, () => console.log(`Marcos Computers Store Server is ${port}`));