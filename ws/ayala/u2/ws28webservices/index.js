const port = 3003
const express = require("express");
const app = express();
app.set("json spaces", 2);
const mongoose = require("mongoose");


mongoose.connect('mongodb+srv://emayala3_db_user:v1AHzynvY4f4FTKP@cluster0.y9tj0jr.mongodb.net/oop?retryWrites=true&w=majority&appName=Cluster0');
const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () => console.log("Enrique System connected to MongoDB Database"));

app.use(express.json());

const customerRoutes = require("./routes/customerRoutes");
app.use("/Computerstore", customerRoutes);

app.listen(port, () => console.log("Enrique's Computers Store Server "));