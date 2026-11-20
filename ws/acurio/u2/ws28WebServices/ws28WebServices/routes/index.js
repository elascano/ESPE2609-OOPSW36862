const port = 3001;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect('mongodb+srv://isacurio_db_user:7YREZGQCUTkh86mz@cluster0.opd3pfc.mongodb.net/oop');

const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () => console.log("Isaac's System connected to MongoDb Database"));

app.use(express.json());

const customerRouter = require("./customerRoutes");

app.use("/computerstore", customerRouter);
app.listen(port, () => console.log("Isaac's Computers Store Server is running on " + port));