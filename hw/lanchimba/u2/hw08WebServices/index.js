require("dotenv").config();

const express = require("express");
const cors = require("cors");
const connectDB = require("./config/db");
const customerRoutes = require("./routes/customerRoutes");

const app = express();
const PORT = Number(process.env.PORT) || 3005;

// Middlewares
app.use(cors());
app.use(express.json());

// Root status route
app.get("/", (_req, res) => {
  res.status(200).json({
    service: "ESPE OOP hw08WebServices - Customers REST API",
    status: "running",
    endpoints: {
      listAll: "GET /api/customers",
      getById: "GET /api/customers/:id",
      create: "POST /api/customers",
      update: "PUT /api/customers/:id",
      delete: "DELETE /api/customers/:id",
    },
  });
});

// Resource routes
app.use("/api/customers", customerRoutes);

// 404 fallback for unknown routes
app.use((_req, res) => {
  res.status(404).json({ message: "Endpoint not found." });
});

async function startServer() {
  try {
    await connectDB();
    app.listen(PORT, () => {
      console.log(`[SERVER OK] REST API listening at http://localhost:${PORT}`);
      console.log(`[SERVER OK] Customers endpoint: http://localhost:${PORT}/api/customers`);
    });
  } catch (_error) {
    console.error("[SERVER FATAL] Unable to start server due to database connection error.");
    process.exit(1);
  }
}

startServer();
