const port = 3025;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect('mongodb+srv://yjvelez_db_user:5TySIn0bAmjKWPPZ@cluster0.w9lbtum.mongodb.net/oop');
const db = mongoose.connection;

db.on("error", (error) => console.error(error));
db.once("open", () => console.log("System connects to MongoDB Database"));

app.use(express.json());
const customerRouter = require("./routes/customerRoutes.js");
app.use("/computerstore", customerRouter);

app.listen(port, () => console.log("Yandry Computers Store Server is running"));