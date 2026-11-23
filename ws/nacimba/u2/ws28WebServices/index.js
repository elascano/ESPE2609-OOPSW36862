const port = 3014;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect("mongodb+srv://mjnacimba_db_user:@cluster0.uaiahls.mongodb.net/oop");

const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () => console.log("System connected to MongoDB Database"));

app.use(express.json());

const customerRouter = require("./routes/customerRoutes");
app.use("/customers", customerRouter);

app.listen(port, () =>
  console.log(`Mateo's computer store server is running on port ${port}`)
);
