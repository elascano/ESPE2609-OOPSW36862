import express from "express";
import { connectToDatabase } from "./config/database";
import customerRouter from "./routes/customerRoutes";
const port = 3009;
const app = express();
connectToDatabase();
app.use(express.json());
app.use("/computerstore", customerRouter);
app.listen(port, () => console.log(`Computer Store running on port ${port}`));
