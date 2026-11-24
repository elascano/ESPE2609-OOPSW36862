const mongoose = require("mongoose");

const customerSchema = new mongoose.Schema(
  {
    id: {
      type: Number,
      required: [true, "Customer id is required"],
      unique: true,
    },
    name: {
      type: String,
      required: [true, "Customer name is required"],
      trim: true,
      minlength: [2, "Customer name must be at least 2 characters long"],
    },
    email: {
      type: String,
      required: [true, "Customer email is required"],
      trim: true,
      lowercase: true,
      match: [/^[^\s@]+@[^\s@]+\.[^\s@]+$/, "Please provide a valid email address"],
    },
    cellphoneNumber: {
      type: String,
      required: [true, "Customer cellphoneNumber is required"],
      trim: true,
    },
  },
  {
    collection: "customers",
    versionKey: false,
    timestamps: true,
  }
);

const Customer = mongoose.model("Customer", customerSchema);

module.exports = Customer;
