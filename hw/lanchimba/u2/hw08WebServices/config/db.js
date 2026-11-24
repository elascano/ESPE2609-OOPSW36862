const mongoose = require("mongoose");

/**
 * Establishes connection to MongoDB Atlas using Mongoose.
 */
async function connectDB() {
  const mongoUri = process.env.MONGO_URI;

  if (!mongoUri) {
    throw new Error(
      "MONGO_URI is not defined in the .env file. Copy .env.example to .env and configure it."
    );
  }

  try {
    const connection = await mongoose.connect(mongoUri, {
      serverSelectionTimeoutMS: 5000,
    });
    console.log(
      `[DB OK] Connected to MongoDB Atlas -> Host: ${connection.connection.host} | Database: ${connection.connection.name}`
    );
    return connection;
  } catch (error) {
    console.error("[DB ERROR] Failed to connect to MongoDB Atlas:", error.message);
    throw error;
  }
}

module.exports = connectDB;
