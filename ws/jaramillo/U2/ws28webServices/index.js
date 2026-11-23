const port = 3010;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect('mongodb+srv://jajaramillo23_db_user:VrL9wefDS9EHSFT4@cluster0.vgzoi5z.mongodb.net/oop');
const db = mongoose.connection;

db.on("error", (error) => console.error(error));
db.once("open", () => console.log("Julian system connected to mongoDb Database"));

app.use(express.json());

const customerRouter = require("./routes/costumerRoutes");
app.use("/computerstore", customerRouter);

app.listen(port, () => console.log("Julian's Computers Store Server is running on port --> " + port));