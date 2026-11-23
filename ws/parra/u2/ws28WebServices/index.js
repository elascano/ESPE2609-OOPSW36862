require("dotenv").config();
//port 3017
const port = 3017
const express = require("express");
const app = express();
const mongoose = require("mongoose");
    mongoose.connect(process.env.MONGODB_URI); 
const db = mongoose.connection;
db.on("error",(error) => console.error(error));
db.once("open", () => console.log("System connected to MongoDB Database"));
app.use(express.json());
const customerRouter = require("./routes/customerRoutes");
app.use("/computerstore",customerRouter);
app.listen(port,() => console.log("Carlos Computers Store Server is running on port -> 3017 "));