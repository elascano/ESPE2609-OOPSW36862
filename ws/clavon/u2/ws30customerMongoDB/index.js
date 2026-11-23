const port = 3006;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect('mongodb+srv://davidclavon2007_db_user:de27yC1b@cluster0.ys86oqp.mongodb.net/oop');

const db = mongoose.connection;

db.on("error", (error) => console.error(error));
db.once("open", () => console.log("System connected to MongoDb Database"));

app.use(express.json());

const customerRouter = require("./routes/customerRoutes");
app.use("/computerstore", customerRouter);

app.listen(port, () => console.log(`David Computers Store Server is ${port}`));