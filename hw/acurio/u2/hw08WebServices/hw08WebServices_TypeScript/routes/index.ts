import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import customerRoutes from "./customerRoutes";

dotenv.config();

const app = express();
const port = 3001;

app.use("/computerstore", customerRoutes);

const uri = process.env.MONGODB_URI;

if (!uri) {
    throw new Error("MongoDB connection is missing in .env");
}

mongoose.connect(uri, { dbName: "oop" })
    .then(() => {
        console.log("Connected to MongoDB");

        app.listen(port, () => {
            console.log("TypeScript Server running on port 3001");
        });
    })
    .catch((error) => {
        console.log("Error connecting to MongoDB");
        console.log(error.message);
    });
