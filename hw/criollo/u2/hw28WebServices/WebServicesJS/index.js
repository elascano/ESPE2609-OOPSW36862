// Dennis Criollo's Computer Store
const port = 3007;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

// Conexión con el nuevo usuario dennis_5
mongoose.connect(
  "mongodb+srv://dennis_5:dennis1234@cluster0.dkq3h62.mongodb.net/OOP"
);

const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () =>
  console.log("Dennis Criollo System connected to MongoDB Database")
);

app.use(express.json());

app.get("/", (req, res) => {
  res.json({ project: "WebServicesJS", owner: "Dennis Criollo", port });
});

const customerRouter = require("./routes/customerRoutes");
app.use("/computerstore", customerRouter);

app.listen(port, () =>
  console.log(`Dennis Criollo's Computer Store running on port ${port}`)
);