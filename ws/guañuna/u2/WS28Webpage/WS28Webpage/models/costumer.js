const mongoose = require("mongoose");

const costumerSchema = new mongoose.Schema(
    {
        id: { type: Number },
        name: { type: String },
        age: { type: Number },
        moneyspend: { type: Number }
    },
    { collection: "Costumers" }
);

module.exports = mongoose.model("Costumer", costumerSchema);