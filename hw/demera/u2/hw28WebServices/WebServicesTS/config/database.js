import mongoose from "mongoose";
const mongoUri = "mongodb+srv://wfdemera_db_user:k1TyMEA36C5Cg9FN@cluster0.dnxy4qu.mongodb.net/OOP";
export function connectToDatabase() {
    mongoose.connect(mongoUri);
    const database = mongoose.connection;
    database.on("error", (error) => console.error(error));
    database.once("open", () => console.log("Connected to MongoDB Database"));
}
