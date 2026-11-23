import mongoose from "mongoose";

const mongoUri =
  "mongodb+srv://drarmas_db_user:fHLt4IhdY14VuCX3@cluster0.0lokqex.mongodb.net/oop";

export function connectToDatabase(): void {
  mongoose.connect(mongoUri);
  const database = mongoose.connection;
  database.on("error", (error) => console.error(error));
  database.once("open", () => console.log("Connected to MongoDB Database"));
}
