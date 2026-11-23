//Francisco's port 3009
const port = 3009;
const express = require("express");
const app = express();
const mongoose = require("mongoose");
mongoose.connect(`mongodb+srv://wfdemera_db_user:k1TyMEA36C5Cg9FN@cluster0.dnxy4qu.mongodb.net/OOP`
);

const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () =>console.log("Francisco´s System connected to MOngoDB Database"));
app.use(express.json());
const customerRouter = require("./routes/customerRoutes");
app.use("/computerstore", customerRouter);
app.listen(port, () => console.log(`Francisco's Computer Store running on port ${port}`));