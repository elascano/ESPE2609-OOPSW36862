
const port = 3002;
const express = require("express");
const app = express();
const mongoose = require("mongoose");
mongoose.connect(`mongodb+srv://drarmas_db_user:fHLt4IhdY14VuCX3@cluster0.0lokqex.mongodb.net/oop`);

const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () =>console.log("Demian´s System connected to mongoDB Database"));
app.use(express.json());
const customerRouter = require("./routes/customerRoutes");
app.use("/computerstore", customerRouter);
app.listen(port, () => console.log(`Demian's Computer Store running`));