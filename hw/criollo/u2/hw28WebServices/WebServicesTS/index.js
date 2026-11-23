import express from "express";
import { connectToDatabase } from "./config/database";
import customerRouter from "./routes/customerRoutes";

const port = 3007;
const app = express();

connectToDatabase();

app.use(express.json());

app.get("/", (req, res) => {
  res.json({ project: "WebServicesTS", owner: "Dennis Criollo", port });
});

app.use("/computerstore", customerRouter);

app.listen(port, () =>
  console.log(`Dennis Criollo's Computer Store running on port ${port}`)
);